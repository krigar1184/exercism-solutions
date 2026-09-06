(ns knapsack)

(def calculate
  (memoize (fn [max-weight [item & items]]
             (cond (nil? item) 0
                   (<= max-weight 0) 0
                   (< (- max-weight (:weight item)) 0) (recur max-weight items)
                   :else (let [new-max-weight (- max-weight (:weight item))
                               with-item (calculate new-max-weight items)
                               without-item (calculate max-weight items)]
                           (max (+ (:value item) with-item) without-item))))))

(defn maximum-value
  "Calculates the maximum value that can be packed."
  [maximum-weight items]
  (calculate maximum-weight items))
