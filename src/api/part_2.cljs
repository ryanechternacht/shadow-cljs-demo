(ns part-2)

;; Simple Interop



(println "hello")

js/String

(type "hello")

(= (type "hello") js/String)

;; Only work in a browser (but they would work!)
js/Document
js/Window











;; Similar interop as clojure <-> java
(def my-obj 
  (clj->js 
             {:location "world"
                      :sayHello (fn [] "hello, world")
                      :areYouAt #(= % "world")}))

;; my-obj = {
;;  location: "world"
;;  sayHello () { return "hello, world" }
;;  areYouAt (loc) { return loc === "world" }
;; }

;; property access
(.-location my-obj)

;; calling a function
(.sayHello my-obj)
(.areYouAt my-obj "home")
(.areYouAt my-obj "world")

;; it compiles straight to JS (so it works exactly like JS)
(.sayHello my-obj 1 2 3 4)
(.areYouAt my-obj)












;; You can use this on the globals as well

;; another way to print
(.log js/console my-obj)

;; Date.now
(.now js/Date)

;; new Date()
(new js/Date)

;; new Array(1, 2, 3)
(new js/Array 1 2 3)

;; (only works in Browser)
;; window.document.getElementById("my-elem")
(-> js/Window
    .-document
    (.getElementById "my-elem"))
