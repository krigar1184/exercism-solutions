(ns roman-numerals (:require [clojure.string :as str]))

(def romans {0 '()
             1 '(\I)
             4 '(\I \V)
             5 '(\V)
             9 '(\I \X)
             10 '(\X)
             50 '(\L)
             90 '(\X \C)
             100 '(\C)
             500 '(\D)
             900 '(\C \M)
             1000 '(\M)})

(defn numerals [n]
  (str/join ""
            (loop [acc (list) cur n]
              (if (zero? cur) acc
                  (if-let [m (get romans cur)] (apply conj m acc)
                          (let [left (filter #(> cur %) (keys romans))
                                right (filter #(< cur %) (keys romans))
                                prev (if (empty? left) nil (apply max left))
                                nxt (if (empty? right) nil (apply min right))
                                r (int (Math/floor (/ cur prev)))]
                            (println "acc:" acc "cur:" cur "r:" r "prev:" prev "nxt:" nxt)
                            (if (> r 3)
                              (let [els (apply conj (get romans nxt) (get romans prev))]
                                (println "els:" els)
                                ; (println (get romans nxt) (get romans prev))
                                ; (println (+ cur (- prev nxt)))
                                (recur (apply conj acc els) (+ cur (- prev nxt))))
                              (recur (apply conj acc (get romans prev)) (- cur prev)))))))))

