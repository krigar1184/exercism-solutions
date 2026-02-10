(ns beer-song (:require [clojure.string :as str]))

(defn verse
  "Returns the nth verse of the song."
  [num]
  (cond (> num 1) (str num " bottles of beer on the wall, " num " bottles of beer.
Take one down and pass it around, " (dec num) " bottle" (if (> num 2) "s" "") " of beer on the wall.\n")
        (= num 1) (str num " bottle of beer on the wall, " num " bottle of beer.
Take it down and pass it around, no more bottles of beer on the wall.\n")
        :else "No more bottles of beer on the wall, no more bottles of beer.
Go to the store and buy some more, 99 bottles of beer on the wall.\n"))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start]
   (sing start 0))
  ([start end]
   (loop [acc nil i start]
     (if  (< i end) (str/join "\n" acc)
          (recur (concat acc [(verse i)]) (dec i))))))
