(ns kindergarten-garden
  (:require [clojure.string :as string]))

(def plants {\R :radishes , \C :clover , \G :grass , \V :violets})
(def children [:alice :bob :charlie :david :eve :fred :ginny :harriet :ileana :joseph :kincaid :larry])

(defn- lookup [codes]
  (into [] (for [c codes] (plants (keyword c)))))

(defn garden [data] ;; <- arglist goes here
  (let [[row1 row2] (string/split-lines data)]
    (loop [acc {} kids children r1 row1 r2 row2]
      (cond (empty? kids) acc
            (empty? r1) acc
            :else (recur (assoc acc (first kids) (lookup (flatten [(take 2 r1) (take 2 r2)])))
                         (rest kids)
                         (drop 2 r1)
                         (drop 2 r2))))))
