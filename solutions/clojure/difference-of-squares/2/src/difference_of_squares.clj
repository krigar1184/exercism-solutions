(ns difference-of-squares)

(defn- diff [n]
  (let [[a & bs] (range (inc n))]
    (loop [x a ys bs acc 0]
      (if (nil? x) (* 2 acc)
          (recur (first ys)
                 (next ys)
                 (+ acc (* x (apply + ys))))))))

(defn sum-of-squares
  "Returns the sum of the squares of the numbers up to n."
  [n]
  (int (apply + (map #(Math/pow % 2) (range 1 (inc n))))))

(defn square-of-sum
  "Returns the square of the sum of the numbers up to n."
  [n]
  (+ (sum-of-squares n) (diff n)))

(defn difference
  "Returns the difference between the square of the sum
  and the sum of the squares of the numbers up to n."
  [n]
  (diff n))
