(ns diamond
  (:require
   [clojure.string :refer [join]]))

(defn make-line [[letter count-spaces] width]
  (let [side (repeat (/ (- width count-spaces) 2) " ")
        l (str (char letter))]
    (if (= 65 letter)
      (concat side ["A"] side)
      (concat side [l] (repeat (- count-spaces 2) " ") [l] side))))

(defn diamond
  "Returns a diamond shape pattern for the given letter."
  [letter]
  (if (= letter \A) "A"
      (let [letters (range (int letter) 64 -1)
            nums (filter odd? (range (* (count letters) 2) 0 -1))
            letters-with-spaces (sort-by (fn [[_ x]] (- x)) (zipmap letters nums))
            [_ width] (first letters-with-spaces)
            m (into [] (map #(make-line % width) letters-with-spaces))
            result (map join (concat (reverse (drop 1 m)) m))]
        (join "\n" result))))
