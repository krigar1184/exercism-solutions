(ns isbn-verifier
  (:require [clojure.string :as s]))

(defn- check [digits]
  (loop [acc 0 nums digits m 10]
    (if (empty? nums) acc
        (let [d (first nums)]
          (cond
            (and (= d "X") (> m 1)) -1
            (= d "X") (recur (+ acc (* m 10)) (rest nums) (dec m))
            :else (recur (+ acc (* m (parse-long d))) (rest nums) (dec m)))))))
(defn isbn?
  "Returns true if the given isbn is valid;
  otherwise, it returns false."
  [isbn]
  (if (nil? (re-matches #"\d{1}-?\d{3}-?\d{5}-?[\d{1}|X]" isbn)) false
      (let [digits (-> (s/replace isbn #"-" "") (s/split #""))]
        (= 0 (mod (check digits) 11)))))
