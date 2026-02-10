(ns pangram
  (:require [clojure.string :as str]))

(def alphabet (seq (for [u (range 97 123)] (char u))))

(defn pangram? [sentence] ;; <- arglist goes here
  (let [filtered (apply hash-set (filter #(Character/isLetter %) (str/lower-case sentence)))]
    (= (sort (seq filtered)) alphabet)))
