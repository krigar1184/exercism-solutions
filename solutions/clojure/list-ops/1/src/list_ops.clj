(ns list-ops)

(defn append
  "Given two vectors, it adds all the items in the second vector to the
  end of the first vector."
  [coll1 coll2]
  (if (empty? coll2) coll1
      (recur (concat coll1 [(first coll2)]) (rest coll2))))

(defn concatenate
  "Given a vector of vectors, it combines all the vectors into one flattened
  vector."
  [colls]
  (loop [acc [] c colls]
    (if (empty? c) acc
        (recur (into acc (first c)) (next c)))))

(defn select-if
  "Given a predicate and a vector, it returns the vector of all items for
  which predicate(item) is true."
  [pred coll]
  (loop [acc [] c coll]
    (if-let [cur (first c)]
      (if (true? (apply pred [cur]))
        (recur (conj acc cur) (next c))
        (recur acc (next c)))
      acc)))

(defn length
  "Given a vector, it returns the number of items within it."
  [coll]
  (loop [acc 0 c coll]
    (if (empty? c) acc
        (recur (inc acc) (next c)))))

(defn apply-to-each
  "Given a function and a vector, it returns the vector of the results of
  applying function(item) on all items."
  [f coll]
  (loop [acc [] c coll]
    (if (empty? c) acc
        (recur (into acc [(apply f [(first c)])]) (next c)))))

(defn foldl
  "Given a function, a vector, and initial accumulator, it folds (reduces)
  each item into the accumulator from the left."
  [f coll acc]
  (loop [a acc c coll]
    (if (empty? c) a
        (recur (apply f [a (first c)]) (next c)))))

(defn foldr
  "Given a function, a vector, and an initial accumulator, it folds (reduces)
  each item into the accumulator from the right."
  [f coll acc]
  (loop [a acc c coll]
    (if (empty? c) a
        (recur (apply f [a (peek c)]) (into [] (butlast c))))))

(defn reverse-order
  "Given a vector, it returns a vector with all the original items, but in
  reverse order."
  [coll]
  (loop [acc [] c coll]
    (if (empty? c) acc
        (recur (conj acc (peek c)) (into [] (butlast c))))))
