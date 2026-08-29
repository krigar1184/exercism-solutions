(ns saddle-points)

(defn good-enough? [matrix [x y]]
  (let [row (nth matrix y)
        col (map #(nth % x) matrix)
        el ((matrix y) x)]
    (and (every? #(<= el %) col) (every? #(>= el %) row))))

(defn saddle-points
  "Returns the saddle points of a matrix."
  [matrix]
  (let [coords (for [y (range (count matrix))
                     x (range (count (matrix y)))] [x y])]
    (reduce
     #(if (good-enough? matrix %2)
        (conj %1
              (->> %2
                   (map inc)
                   reverse
                   vec)) %1)
     #{} coords)))
