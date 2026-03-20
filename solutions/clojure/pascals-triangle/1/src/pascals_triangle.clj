(ns pascals-triangle)

(defn- generate [row]
  (loop [acc [1] cur row]
    (if (<= (count cur) 1) (conj acc 1)
        (recur (conj acc (+ (first cur) (second cur))) (drop 1 cur)))))

(defn row [prev]
  (cond
    (= [1] prev) [1 1]
    :else (-> (generate prev) rseq)))

(def triangle (iterate row [1]))
