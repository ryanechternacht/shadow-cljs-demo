(ns api.part_6
  (:require ["@slack/bolt" :as bolt]))

(def signing-secret "76a2306b45bd5707756268a132c2426b")
(def bot-token "xoxb-4263016632995-4256491291334-aou9RrZH8RsbP3YPMAywhDnp")

(def app (new bolt/App (clj->js {:token bot-token
                                 :signingSecret signing-secret})))


(comment
  (.start app 3001)
  ;
  )

















;; register a handler to respond whenever someone types "hello"
;; app.message("hello", ({ message, say }) => { say( ... ) })

(.message app
          "hello"
          (fn [obj] ;; can't destructure this
            (let [{:keys [message say]} (js->clj obj :keywordize-keys true)]
              (say (clj->js
                    {:blocks [{:type "section"
                               :text {:type "mrkdwn"
                                      :text (str "Hi there <@" (:user message) ">!")}}]
                     :text "hello, world!"})))))


















;; We want to be able to define and redefine a function, and have
;; bolt always use the latest version

(defn my-handler [obj]
  (let [{:keys [say]} (js->clj obj :keywordize-keys true)]
    (say (clj->js {:blocks [{:type "section"
                             :text {:type "mrkdwn"
                                    :text (str "Look ma, no hands!")}}]
                   :text "hello, world!"}))))

;; our ring  handlers look like this
;; (.message app "howdy" #'my-handler)

;; This is what I found works best
(.message app "howdy" #(my-handler %))

