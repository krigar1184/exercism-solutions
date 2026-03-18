(ns change)

doall
; (defn do-issue [sum coins acc]
;   (cond
;     (= sum 0) [acc]
;     (or (< sum 0) (nil? (seq coins))) nil
;     :else (let [coin (first coins)
;                 left (do-issue (- sum coin) coins (conj acc coin))
;                 right (do-issue sum (rest coins) acc)]
;             (cond
;               (nil? (seq left)) right
;               (nil? (seq right)) left
;               (< (count left) (count right)) left
;               :else right))))
;

(defn update-cache [cache [ak a] [bk b]]
  (let [[k v] (cond
                (nil? (seq a)) [bk b]
                (nil? (seq b)) [ak a]
                (< (count a) (count b)) [ak a]
                :else [bk b])
        current (@cache k)]

    (when (and (seq v) (or (nil? current) (> (count current) (count v))))
      ; (println "putting" v "into" k "instead of" current "mode" m)
      (swap! cache assoc k (sort v)))

    (@cache k)))

(defn do-issue [sum coins acc cache]
  (cond
    (= sum 0) (update-cache cache [(apply + acc) acc] [nil nil])
    (or (< sum 0) (nil? (seq coins))) nil
    :else (let [coin (first coins)
                lk [sum]
                rk [(- sum coin)]
                lc (@cache lk)
                rc (@cache rk)
                left (if (nil? lc)
                       (do-issue sum (rest coins) acc cache)
                       lc)
                right (if (nil? rc)
                        (do-issue (- sum coin) coins (conj acc coin) cache)
                        rc)]

            (update-cache cache [lk left] [rk right])

            (cond (nil? right) left
                  (nil? left) right
                  (> (count left) (count right)) right
                  :else left))))

(defn issue [sum coins]
  (cond (zero? sum) '()
        (neg? sum) (throw (IllegalArgumentException. "cannot change"))
        (< sum (apply min coins)) (throw (IllegalArgumentException. "cannot change"))
        :else (if-let [result (do-issue sum (->> coins (filter #(<= % sum)) sort) '() (atom {}))]
                result
                (throw (IllegalArgumentException. "cannot change")))))
