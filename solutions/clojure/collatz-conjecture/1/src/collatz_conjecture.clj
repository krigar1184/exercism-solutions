(ns collatz-conjecture)

(defn collatz [num] ;; <- arglist goes here
  (if (<=  num 0) (throw (Exception. "num should be greater than zero"))
      (loop [acc 0 cur num]
        (println acc cur)
        (if (= cur 1) acc
            (if (even? cur)
              (recur (inc acc) (/ cur 2))
              (recur (inc acc) (inc (* cur 3))))))))
