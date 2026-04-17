(ns perfect-numbers)

(defn aliquot [n]
  (filter #(zero? (rem n %)) (range 1 n)))

(defn classify
  "Classifies the given number as perfect, abundant, or deficient."
  [num]
  (let [aliquot-sum (apply + (aliquot num))]
    (cond (= aliquot-sum num) :perfect
          (< num aliquot-sum) :abundant
          :else :deficient)))
