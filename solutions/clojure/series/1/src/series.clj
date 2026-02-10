(ns series)

(defn slices [string length] ;; <- arglist goes here
  (cond (= length 0) [""]
        (> length (count string)) []
        :else (loop [acc [] coll (vec string)]
                (if (empty? coll) acc
                    (recur (conj acc (apply str (take length coll)))
                           (if (< length (count coll))
                             (subvec coll 1)
                             []))))))
