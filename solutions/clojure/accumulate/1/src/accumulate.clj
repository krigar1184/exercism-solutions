(ns accumulate)

(defn accumulate [f coll]
  (loop [acc [] i 0]
    (if (> (inc i) (count coll)) acc
      (recur
       (conj acc (f (nth coll i)))
       (inc i)))))
