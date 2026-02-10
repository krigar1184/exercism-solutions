(ns acronym
  (:require [clojure.string :as str]))

(defn acronym
  "Converts phrase to its acronym."
  [phrase]
  (if (str/blank? phrase) ""
      (->> phrase
           (re-seq #"[A-Z]+[a-z]*|[a-z]+")
           (map first)
           (apply str)
           str/upper-case)))
