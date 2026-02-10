(ns bird-watcher)

(def last-week 
  [0 2 5 3 7 8 4])

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (let [new-count (inc (get birds 6))]
    (assoc birds 6 new-count)))

(defn day-without-birds? [birds]
  (> (count (filter #(= 0 %) birds)) 0))

(defn n-days-count [birds n]
  (apply + (take n birds)))

(defn busy-days [birds]
  (count (filter #(>= % 5) birds)))

(defn odd-week? [birds]
  (let [pattern [1 0 1 0 1 0 1]]
    (= birds pattern)
  ))
