(ns luhn)

(defn- double-digit [digit]
  (let [double-digit (* digit 2)]
    (if (> double-digit 9) (- double-digit 9)
        double-digit)))

(defn valid?
  "Returns true if the given string is a valid number;
  otherwise, it returns false."
  [s]
  (if (re-seq #"[^0-9\s]" s) false
      (let [digits (->> s (filter Character/isDigit) reverse (map #(Character/digit % 10)))
            edigits (for [i (range (count digits))] [i (nth digits i)])
            double-digits (map
                           (fn [[i d]] (if (odd? i) (double-digit d) d)) edigits)
            sum (apply + double-digits)]
        (and (> (count digits) 1) (zero? (mod sum 10))))))
