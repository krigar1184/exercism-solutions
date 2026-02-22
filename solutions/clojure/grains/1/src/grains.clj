(ns grains)

(defn pow [n p]
  (cond (= p 0) 1
        (= n 1) 1
        (even? p) (recur (* n n) (quot p 2))
        :else (* n (pow n (dec p)))))

(defn square
  "Returns the number of grains on the n-th chessboard square."
  [n]
  (bigint (pow 2N (dec n))))

(defn total
  "Returns the total number of grains on the chessboard."
  []
  (reduce +' 0N (map square (range 1 65))))
