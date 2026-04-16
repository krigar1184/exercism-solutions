(ns largest-series-product
  (:require [clojure.string :refer [split]]))

(defn largest-product
  "Returns the largest product of any consecutive digits of length span
  in the string s."
  [span s]
  (when (> span (count s))
    (throw (IllegalArgumentException. "span must not exceed string length")))
  (when (re-seq #"\D" s)
    (throw (IllegalArgumentException. "digits input must only contain digits")))
  (when (<= span 0)
    (throw (IllegalArgumentException. "span must not be negative")))
  (loop [input (split s #"") acc []]
    (let [group (take span input)]
      (if (< (count group) span)
        (apply max (->> acc
                        (map #(map parse-long %))
                        (map #(reduce * %))))
        (recur (drop 1 input) (conj acc group))))))
