(ns sieve)

(defn- make-sieve [i s]
  (if (>= i (count s)) (reverse s)
      (let [start (nth s i)]
        (recur (inc i) (concat [start] (filter #(> (mod % start) 0) s))))))

(defn sieve
  "Returns the primes that are less than or equal to num."
  [num]
  (loop [s (range 2 (inc num)) i 0]
    (if (>= i (count s)) (reverse s)
        (let [start (nth s i)]
          (recur (concat [start] (filter #(> (mod % start) 0) s)) (inc i))))))
