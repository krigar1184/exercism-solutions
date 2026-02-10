(ns sum-of-multiples)

(defn find-mults [level item]
  (for [v (range 1 level) :when (zero? (mod v item))] v))

(defn sum-of-multiples [items level]
  (->> items
       (map (fn [x] (find-mults level x)))
       flatten
       (apply hash-set)
       (apply +)))
