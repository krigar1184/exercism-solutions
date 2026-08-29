(ns prime-factors)

(defn prime? [n]
  (loop [cur n]
    (cond (or (= cur 3) (= cur 2)) true
          (even? cur) false
          :else (let [divisors (for [n (range 3 (inc (Math/ceil (Math/sqrt cur))) 2)] n)]
                  (if (empty? (filter #(zero? (rem cur %)) divisors)) true
                      (recur (dec cur)))))))

(defn next-prime [n]
  (first (filter prime? (iterate inc (inc n)))))

(defn of
  "Returns the prime factors of the given number."
  [num]
  (loop [acc [] prime 2 n num]
    (println acc prime n)
    (cond (= prime n) (conj acc prime)
          (> prime n) acc
          :else (let [[nn np na]
                      (if (zero? (rem n prime))
                        [(/ n prime) prime (conj acc prime)]
                        [n (next-prime prime) acc])]
                  (recur na np nn)))))
