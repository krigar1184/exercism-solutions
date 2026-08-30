(ns resistor-color-trio)

(def colors ["black" "brown" "red" "orange" "yellow" "green" "blue" "violet" "grey" "white"])

(defn get-value-with-prefix [base-value base-exp]
  (let [m (if (zero? (rem base-value 10)) 1 0)
        exp (+ base-exp m)
        value (/ base-value (Math/pow 10 m))]
    (cond (>= exp 9) ["giga" (- exp 9) value]
          (>= exp 6) ["mega" (- exp 6) value]
          (>= exp 3) ["kilo" (- exp 3) value]
          :else ["" exp value])))

(defn resistor-label
  "Returns the resistor label based on the given color bands."
  [{color1 0 color2 1 color3 2}]
  (let [exp (.indexOf colors color3)
        base-value (+ (* 10 (.indexOf colors color1))
                      (.indexOf colors color2))
        [prefix new-exp new-value] (get-value-with-prefix base-value exp)]
    (str (int (* (Math/pow 10 new-exp) new-value)) " " prefix "ohms")))
