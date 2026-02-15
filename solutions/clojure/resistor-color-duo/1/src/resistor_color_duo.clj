(ns resistor-color-duo)

(def colors
  ["black" "brown" "red" "orange" "yellow" "green" "blue" "violet" "grey" "white"])

(defn resistor-value
  "Returns the resistor value based on the given colors."
  [[color1 color2 & _]]
  (+ (* (.indexOf colors color1) 10)
     (.indexOf colors color2)))
