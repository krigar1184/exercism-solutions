(ns matrix
  (:require [clojure.string :refer [split-lines split]]))

(defn parse-row [input]
  (into [] (map parse-long (split input #"\s"))))

(defn- rows [matrix]
  (lazy-seq (split-lines matrix)))

(defn get-row
  "Returns the i-th row of the matrix."
  [matrix i]
  (parse-row (nth (rows matrix) (dec i))))

(defn get-column
  "Returns the i-th column of the matrix."
  [matrix i]
  (loop [acc [] rs (rows matrix)]
    (if (empty? rs) acc
        (let [row (parse-row (first rs))]
          (recur (conj acc (nth row (dec i))) (next rs))))))
