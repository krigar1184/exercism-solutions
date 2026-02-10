(ns etl
  (:require [clojure.string :as str]))

(defn transform [source] ;; <- arglist goes here
  (let [scores (keys source)]
    (loop [acc {} n 0]
      (let [cur (nth scores n nil)]
        (if (nil? cur) acc
            (let [values (get source cur)]
              (println acc cur n values)
              (recur (reduce #(assoc %1 (str/lower-case %2) cur) acc values)
                     (inc n))))))))
