(ns perfect-numbers)

(defn aliquot [n]
  (if (= n 1) []
      (filter #(zero? (rem n %)) (range 1 (inc (/ n 2.0))))))

(defn classify
  "Classifies the given number as perfect, abundant, or deficient."
  [num]
  (let [aliquot-sum (apply + (aliquot num))]
    (cond (= aliquot-sum num) :perfect
          (< num aliquot-sum) :abundant
          :else :deficient)))
