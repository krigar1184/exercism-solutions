(ns pig-latin (:require [clojure.string :as str]))

(defn rule1 [word & _]
  (str word "ay"))

(defn rule2 [_ right left & _]
  (str left right "ay"))

(defn rule3 [_ right left & _]
  (str left right "qu" "ay"))

(defn rule4 [_ right left & _]
  (str "y" left right "ay"))

(defn translator [word]
  (let [regexes {#"^([^aeiou]?)qu(.*)$" rule3
                 #"^([^aeiou]+)y(.*)$" rule4
                 #"^(a|e|i|o|u|xr|yt).*$" rule1
                 #"^([^aeiou]+)(.*)$" rule2}]
    (loop [item (first regexes) tail (next regexes)]
      (if (nil? item) word
          (let [[r f] item]
            (if-let [matches (re-matches r word)] (apply f matches)
                    (recur (first tail) (next tail))))))))

(defn translate [phrase] ;; <- arglist goes here
  (let [words (str/split phrase #"\s")]
    (str/join " " (map translator words))))
