(ns proverb
  (:require
   [clojure.string :as str]))

(defn recite [words] ;; <- arglist goes here
  (if (nil? (seq words)) ""
      (loop [prev (first words) cur (second words) data (next words) acc [(str "And all for the want of a " prev ".")]]
        (cond (nil? cur) (str/join "\n" (conj (into [] (next acc)) (first acc)))
              :else (recur (first data) (second data) (next data) (conj acc (str "For want of a " prev " the " cur " was lost.")))))))
