(ns bob
  (:require [clojure.string :as str]))

(defn question? [s] (str/ends-with? (str/trim s) "?"))

(defn yelling? [s]
  (let [letters (filter #(Character/isLetter %) s)]
    (and (seq letters)
         (every? #(Character/isUpperCase %) letters))))

(defn silence? [s] (str/blank? s))

(defn response-for [s] ;; <- arglist goes here
  (cond
    (and (question? s) (yelling? s)) "Calm down, I know what I'm doing!"
    (question? s)  "Sure."
    (yelling? s) "Whoa, chill out!"
    (silence? s) "Fine. Be that way!"
    :else "Whatever."))
