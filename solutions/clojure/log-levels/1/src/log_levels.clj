(ns log-levels
  (:require [clojure.string :as str]))

(defn parse
  [s]
  (str/split s #": "))

(defn message
  "Takes a string representing a log line
   and returns its message with whitespace trimmed."
  [s]
  (str/trim (second (parse s))))

(defn log-level
  "Takes a string representing a log line
   and returns its level in lower-case."
  [s]
  (str/lower-case
   (str/replace (first (parse s)) #"[\[\]]" "")))

(defn reformat
  "Takes a string representing a log line and formats it
   with the message first and the log level in parentheses."
  [s]
  (str (message s) " (" (log-level s) ")"))
