(ns atbash-cipher (:require [clojure.string :as str]))

(def alphabet (seq (for [u (conj (range 97 123))] (char u))))

(def k (zipmap alphabet (reverse alphabet)))

(defn do-encode [s]
  (if (Character/isDigit s) s
      (get k (Character/toLowerCase s))))

(defn encode [text] ;; <- arglist goes here
  (->> text
       (map do-encode)
       (filter #(not (nil? %)))
       (partition-all 5)
       (map #(str/join "" %))
       (str/join " ")))
