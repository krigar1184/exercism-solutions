(ns armstrong-numbers
  (:require [clojure.string :as str]))

(defn armstrong? [num] ;; <- arglist goes here
  (let [digits (str/split (str num) #"")]
    (println digits)
    (=
     (reduce #(+ %1 (Math/pow (Integer/parseInt %2) (count digits))) 0 digits)
     (float num))))
