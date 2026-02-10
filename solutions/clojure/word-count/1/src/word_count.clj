(ns word-count (:require [clojure.string :as str]))

(defn reducer [words]
  (let [acc (atom {})]
    (loop [i 0]
      (if (>= i (count words)) @acc
          (let [word (nth words i)
                old-val (get @acc word)]
            (if (nil? old-val) (do (swap! acc assoc word 1) (recur (inc i)))
                (swap! acc assoc word (inc old-val) (recur (inc i)))))))))

(defn word-count [s] ;; <- arglist goes here
  (-> s
      (str/split #"[\s\n\W]+")
      (#(map str/lower-case %))
      reducer))
