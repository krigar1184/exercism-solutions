(ns difference-of-squares)

(defn square-of-sum
  "Returns the square of the sum of the numbers up to N."
  [N]
  (int (Math/pow (apply + (range 1 (inc N))) 2)))

(defn sum-of-squares
  "Returns the sum of the squares of the numbers up to N."
  [N]
  (int (apply + (map #(Math/pow % 2) (range 1 (inc N))))))

(defn difference
  "Returns the difference between the square of the sum
  and the sum of the squares of the numbers up to N."
  [N]
  (abs (- (sum-of-squares N) (square-of-sum N))))
