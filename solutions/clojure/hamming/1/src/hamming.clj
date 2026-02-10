(ns hamming)

(defn distance [strand1 strand2] ; <- arglist goes here
  (if (= (count strand1) (count strand2))
    (loop [acc 0 n 0]
      (if (>= n (count strand1)) acc
          (if (= (nth strand1 n) (nth strand2 n))
            (recur acc (inc n))
            (recur (inc acc) (inc n)))))
    nil))
