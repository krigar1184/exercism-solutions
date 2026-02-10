(ns anagram
  (:require [clojure.string :as str]))

(defn anagram? [target candidate]
  (let [ltarget (str/lower-case target)
        lcandidate (str/lower-case candidate)]
    (and
     (not= ltarget lcandidate)
     (= (sort nil ltarget) (sort nil lcandidate)))))

(defn anagrams-for [word prospect-list] ;; <- arglist goes here
  (filter #(anagram? word %) prospect-list))
