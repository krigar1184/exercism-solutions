(ns transpose
  (:require [clojure.string :as s]))

(defn make-row [i lines]
  (->> (map #(nth % i nil) lines)
       reverse
       (drop-while nil?)
       (map #(if (nil? %) \space %))
       reverse
       (into [])))

(defn transpose
  "Returns the transposed version of the given string."
  [input]
  (let [lines (->> input s/split-lines)
        max-len (count (apply max-key count lines))
        rows (loop [i 0 acc []]
               (let [row (make-row i lines)]
                 (if (= i max-len) acc
                     (recur (inc i) (conj acc (apply str row))))))]
    (s/join \newline rows)))
