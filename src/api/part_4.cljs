(ns part-4)

;; Navigating the js/cljs interface pt 2

(def my-obj (clj->js
             {:location "world"
              :sayHello (fn [] "hello, world")
              :areYouAt #(= % "world")}))


;; should just return the [k v] pairs
(map identity my-obj)

;; ^ this will (probably?) work in clj

;; succeeds!
(map identity (js->clj my-obj))












;; where are my keywords?!?
(js->clj my-obj)

;; keywords rock!
(js->clj my-obj :keywordize-keys true)


(map identity (js->clj my-obj :keywordize-keys true))

