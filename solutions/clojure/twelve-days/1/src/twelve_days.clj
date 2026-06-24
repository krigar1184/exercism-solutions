(ns twelve-days
  (:require
   [clojure.string :as s]))

(def days ["first"
           "second"
           "third"
           "fourth"
           "fifth"
           "sixth"
           "seventh"
           "eighth"
           "ninth"
           "tenth"
           "eleventh"
           "twelfth"])

(def items ["a Partridge in a Pear Tree"
            "two Turtle Doves"
            "three French Hens"
            "four Calling Birds"
            "five Gold Rings"
            "six Geese-a-Laying"
            "seven Swans-a-Swimming"
            "eight Maids-a-Milking"
            "nine Ladies Dancing"
            "ten Lords-a-Leaping"
            "eleven Pipers Piping"
            "twelve Drummers Drumming"])

(defn- format-items [coll]
  (if (= (count coll) 1) (first coll)
      (let [[head tail] (partitionv-all (dec (count coll)) coll)]
        ; (println "head" head "tail" tail, "coll" (s/join "|" coll))
        (str (s/join ", " head) ", and " (first tail)))))

(defn- make-verse [n]
  (let [day-number (days n)
        items-by-day (subvec items 0 (inc n))]
    (str "On the " day-number " day of Christmas my true love gave to me: " (format-items (reverse items-by-day)) ".")))

(defn recite
  "Returns the lyrics of the song: 'The Twelve Days of Christmas.'"
  [start-verse end-verse]
  (let [rng (range (dec start-verse) end-verse)
        verses (for [n rng] (make-verse n))]
    (s/join "\n" verses)))
