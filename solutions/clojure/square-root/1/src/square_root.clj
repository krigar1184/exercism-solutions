(ns square-root)

(defn heron
  ([n] (heron n 1.0 0.001))
  ([n init delta]
   (loop [cur init]
     (if (<= (abs (- (* cur cur) n)) delta) (int cur)
         (recur (/ (+ cur (/ n cur)) 2))))))

(defn square-root
  "Calculates the square root of a number."
  [num]
  (heron num))
