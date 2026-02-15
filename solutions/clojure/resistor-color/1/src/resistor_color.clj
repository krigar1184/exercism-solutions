(ns resistor-color)

(def colors
  ["black" "brown" "red" "orange" "yellow" "green" "blue" "violet" "grey" "white"])

(defn color-code
  "Returns the numerical value associated with the given color."
  [color]
  (loop [i (dec (count colors))]
    (cond (< i 0) nil
          (= (colors i) color) i
          :else (recur (dec i)))))
