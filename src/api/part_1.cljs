(ns part-1)



;; It's clojure!
(+ 1 1)

(->> (range 10)
     (map inc)
     (filter even?)
     (map dec))














;; we even have atoms!
(def my-atom (atom nil))

(->> (range 10)
     (map inc)
     (filter even?)
     (map dec)
     (reduce (fn [x y]
               (reset! my-atom y)
               (+ x y))))

@my-atom

