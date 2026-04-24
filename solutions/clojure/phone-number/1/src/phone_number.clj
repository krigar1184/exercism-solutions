(ns phone-number
  (:require [clojure.string :refer [replace-first]]))

(defn clean [number]
  (->> number
       (filter Character/isDigit)))

(defn valid [number]
  (println "\n\nnumber" number)
  (let [invalid "0000000000"
        [area local] (split-at 3 number)
        [exchange subscriber] (split-at 3 local)]
    (println "area" area "exchange" exchange "subscriber" subscriber)
    (cond
      (= (first area) \1) (recur (drop 1 number))
      (or (= (first area) \0) (= (first area) \1)) invalid
      (or (= (first exchange) \0) (= (first exchange) \1)) invalid
      (not= 10 (count number)) invalid
      :else number)))

(defn number
  [raw-number] ;; <- arglist goes here
  (->> raw-number
       ((comp valid clean))
       (apply str)))
