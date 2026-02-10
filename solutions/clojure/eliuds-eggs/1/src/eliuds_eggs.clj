(ns eliuds-eggs)

(defn to-binary [n]
  (loop [acc (list) cur n]
    (if (zero? cur) acc
        (recur (conj acc (int (rem cur 2))) (int (/ cur 2))))))

(defn egg-count [number]
  (reduce + 0 (filter #(= % 1) (to-binary number))))
