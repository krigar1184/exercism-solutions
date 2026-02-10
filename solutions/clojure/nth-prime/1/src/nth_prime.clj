(ns nth-prime)

(defn prime? [x primes]
  (loop [n 0]
    (let [div (nth primes n nil)]
      (cond
        (nil? div) true
        (= (rem x div) 0) false
        (= x div) true
        :else (recur (inc n))))))

(defn nth-prime
  "Returns the prime number in the nth position."
  [n]
  (when-not (pos? n) (throw (.IllegalArgumentException "n should be positive")))
  (cond (= 1 n) 2
        (= 2 n) 3
        (= 3 n) 5
        (= 4 n) 7
        (= 5 n) 11
        :else
        (loop [cur 13 i 6 acc [3 5 7 11]]
          (cond
            (even? cur) (recur (inc cur) i acc)
            (prime? cur acc)
            (if (= i n) cur
                (recur (+ cur 2) (inc i) (conj acc cur)))
            :else (recur (+ cur 2) i acc)))))

