(ns raindrops)

(defn convert [n] ;; <- arglist goes here
  (if-not (or (zero? (rem n 3)) (zero? (rem n 5)) (zero? (rem n 7)))
    (str n)
    (let [data {3 "Pling"
                5 "Plang"
                7 "Plong"
                nil nil}]
      (loop [[div v] (first data) tail (next data) acc ""]
        (if (nil? div) acc
            (if (zero? (rem n div))
              (recur (first tail) (next tail) (str acc v))
              (recur (first tail) (next tail) acc)))))))
