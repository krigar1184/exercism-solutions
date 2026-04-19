(ns prime-factors)

(defn prime? [x]
  (cond (or (= x 3) (= x 2)) true
        (even? x) false
        :else (let [divisors (for [n (range 5 (inc (Math/ceil (Math/sqrt x))) 2)] n)]
                (if (empty? (filter #(zero? (rem x %)) divisors)) true
                    (recur (dec x))))))

(defn next-prime [n]
  (first (filter prime? (iterate inc (if (even? n) (inc n) (+ n 2))))))

(defn of
  "Returns the prime factors of the given number."
  [num]
  (loop [acc [] prime 2 n num]
    (cond (= prime n) (conj acc prime)
          (> prime n) acc
          :else (let [[nn np na]
                      (if (zero? (rem n prime))
                        [(/ n prime) prime (conj acc prime)]
                        [n (next-prime prime) acc])]
                  (recur na np nn)))))
