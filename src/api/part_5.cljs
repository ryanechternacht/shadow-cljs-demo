(ns part-5
  (:require ["@aws-sdk/client-dynamodb" :as dynamo]))

;; ^ that's a JS library pulled straight from npm

;; Let's do some REPL Driven Dev!

;; All of the exports!
(keys (js->clj dynamo))

;; doesn't work as expected (I made this mistake while learning!)
(def client (new dynamo/DynamoDBClient
                 {:endpoint "http://localhost:8000"}))

;; remember to clj-js when working with raw js!
(def client (new dynamo/DynamoDBClient
                 (clj->js {:endpoint "http://localhost:8000"})))













;; client.send(new ListTablesCommand {}) // a promise!!
;;   .then(x => console.log("success") <capture x into an atom>)
;;   .catch(x => console.log("failure") <capture x into an atom>)

(def result (atom nil))

;; This is native promises
(defn handle-promise [my-atom promise]
  (-> promise
      (.then (fn [x] (println "success") (reset! my-atom (js->clj x :keywordize-keys true))))
      (.catch (fn [x] (println "failure") (reset! my-atom (js->clj x :keywordize-keys true))))))
















;; client.send(new ListTablesCommand())

;; wrong
(handle-promise result (.send client (new dynamo/ListTablesCommand)))

@result



;; client.send(new ListTablesCommand({}))

;; correct (constructor takes a param)
(handle-promise result (.send client (new dynamo/ListTablesCommand {})))


(keys @result)
(:TableNames @result)











;; create a table (error!)
(handle-promise result
                (.send client
                       (new dynamo/CreateTableCommand
                            (clj->js {:AttributeDefinitions
                                      [{:AttributeName "PK"
                                        :AttributeType "N"}]
                                      :KeySchema
                                      [{:AttributeName "PK"
                                        :KeyType "HASH"}]
                                      :TableName "My_Table"}))))

@result

;; create a table (correct!)
(handle-promise result
                (.send client
                       (new dynamo/CreateTableCommand
                            (clj->js {:AttributeDefinitions
                                      [{:AttributeName "PK"
                                        :AttributeType "N"}]
                                      :KeySchema
                                      [{:AttributeName "PK"
                                        :KeyType "HASH"}]
                                      :TableName "My_Table_3"
                                      :ProvisionedThroughput
                                      {:ReadCapacityUnits 1
                                       :WriteCapacityUnits 1}}))))

@result

;; Check our work
(handle-promise result (.send client (new dynamo/ListTablesCommand {})))

(:TableNames @result)

