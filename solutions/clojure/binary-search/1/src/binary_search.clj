(ns binary-search)

(defn middle [nums] ;; <- arglist goes here
  (if (empty? nums) '(nil nil)
      (nth nums (/ (count nums) 2) '(nil nil))))

(defn enumerate [x]
  (for [v (range (count x))] (list v (nth x v))))

(defn search-for [n nums]
  (let [numbers (enumerate nums)]
    (loop [pairs numbers]
      (let [[i mid] (middle pairs)]
        (cond (nil? mid) (throw (Exception. "not found"))
              (= mid n) i
              (= 1 (count pairs)) (throw (Exception. "not found"))
              (> mid n) (recur (take (/ (count pairs) 2) pairs))
              :else (recur (take-last (/ (count pairs) 2) pairs)))))))
