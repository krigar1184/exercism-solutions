(ns phone-number)

(defn clean [number]
  (->> number
       (filter Character/isDigit)))

(defn valid [number]
  (let [invalid "0000000000"
        [area local] (split-at 3 number)]
    (cond
      (= (first area) \1) (recur (drop 1 number))
      (or (= (first area) \0) (= (first area) \1)) invalid
      (or (= (first local) \0) (= (first local) \1)) invalid
      (not= 10 (count number)) invalid
      :else number)))

(defn number
  [raw-number] ;; <- arglist goes here
  (->> raw-number
       ((comp valid clean))
       (apply str)))
