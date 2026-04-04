(ns perfect-numbers)

(defn aliquot [n]
  (lazy-seq
   (loop [acc [1] m 2 cur n]
     (let [next-m (if (even? m) (inc m) (+ 2 m))]
       ; (println acc m cur)
       (cond (= cur 1) []
             (>= m cur) acc
             (not= (rem cur m) 0) (recur acc (inc m) cur)
             :else
             (recur (conj acc m) (inc m) cur))))))

(defn classify
  "Classifies the given number as perfect, abundant, or deficient."
  [num]
  (let [aliquot-sum (apply + (aliquot num))]
    (cond (= aliquot-sum num) :perfect
          (< num aliquot-sum) :abundant
          :else :deficient)))
