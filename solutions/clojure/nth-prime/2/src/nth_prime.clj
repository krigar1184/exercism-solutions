(ns nth-prime)

(defn
  prime?
  ([x]
   (or (= x 2)
       (loop [cur x]
         (if (or (even? cur) (< cur 0)) false
             (let [divisors (for [n (range 3 (inc (Math/ceil (Math/sqrt cur))) 2)] n)]
               (if (empty? (filter #(zero? (rem cur %)) divisors)) true
                   (recur (dec cur)))))))))

(defn next-prime [start]
  (loop [i (inc start)]
    (cond (prime? i) i
          :else (recur (inc i)))))

(def gen (iterate next-prime 1))

(defn nth-prime
  "Returns the prime number in the nth position."
  [n]
  (when-not (pos? n) (throw (.IllegalArgumentException "n should be positive")))
  (nth gen n))

