(ns flatten-array)

(defn flatten
  "Flattens the given sequential collection.
  Nil values are excluded from the result."
  [coll]
  (reduce (fn [acc cur]
            (cond (nil? cur) acc
                  (not (vector? cur)) (conj acc cur)
                  :else (into [] (concat acc (flatten cur))))) [] coll))
