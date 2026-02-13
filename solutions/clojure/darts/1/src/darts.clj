(ns darts)

(defn- hypothenuse [x y]
  (Math/sqrt (+ (Math/pow x 2) (Math/pow y 2))))

(defn score
  "Calculates the score of a dart throw."
  [x y]
  (let [hp (hypothenuse (abs x) (abs y))]
    (cond (<= hp 1) 10
          (<= hp 5) 5
          (<= hp 10) 1
          :else 0)))
