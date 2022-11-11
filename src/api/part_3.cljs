(ns part-3)

;; Navigating the js/cljs interface

;; Fails
;; [3 2 1].sort() 
(.sort [3 2 1])

;; what are these?
(.table js/console [3 2 1])
(.table js/console {:a "b"})














;; the solution?
{:a "a" :b "b"}
(clj->js {:a "a" :b "b"})

[3 2 1]
(clj->js [3 2 1])


















;; this looks better
(.table js/console (clj->js [3 2 1]))
(.table js/console (clj->js {:a "a" :b "b"}))

;; cljs->js to the rescue
(.sort (clj->js [3 2 1]))


;; There's also a js->clj  to reverse the process
(-> [3 2 1]
    clj->js
    .sort
    js->clj)
