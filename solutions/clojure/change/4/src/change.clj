(ns change)

(defn do-issue [sum coins acc cache]
  (cond
    (= sum 0) acc
    (or (< sum 0) (nil? (seq coins))) nil
    (< sum (apply min coins)) nil
    (not (nil? (cache sum))) (cache sum)
    :else (let [coin (first coins)
                left (do-issue sum (rest coins) acc cache)
                right (do-issue (- sum coin) coins (conj acc coin) (assoc cache (- sum coin) left))]

            (cond (nil? right) left
                  (nil? left) right
                  (> (count left) (count right)) right
                  :else left))))

(defn issue [sum coins]
  (cond (zero? sum) '()
        (neg? sum) (throw (IllegalArgumentException. "target can't be negative"))
        (< sum (apply min coins)) (throw (IllegalArgumentException. "can't make target with given coins"))
        :else (if-let [result (do-issue sum (->> coins (filter #(<= % sum)) sort) '() {})]
                (sort result)
                (throw (IllegalArgumentException. "can't make target with given coins")))))
