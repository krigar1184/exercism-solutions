(ns game-of-life)

(defn get-neighbors-coords [yx size]
  (let [deltas (for [x (range -1 2) y (range -1 2) :when (not= [0 0] [y x])] [x y])]
    [yx (->> deltas
             (map #(vec (map + yx %)))
             (filter (fn [v] (every? #(< -1 % size) v))))]))

(defn get-cell-value [yx neighbors-coords cells]
  (let [current-value (get-in cells yx)
        count-ones (count (->> neighbors-coords
                               (map #(get-in cells %))
                               (filter #(= 1 %))))]
    (cond (and (= current-value 1) (<= 2 count-ones 3)) 1
          (and (= current-value 0) (= count-ones 3)) 1
          :else 0)))

(defn tick
  "Returns the next generation of the cells."
  [cells]
  (let [size (count cells)
        coords (for [x (range size) y (range size)] [x y])
        neighbors-coords (into {} (map #(get-neighbors-coords % size) coords))]
    (->> (map #(get-cell-value % (neighbors-coords %) cells) coords)
         (partitionv size)
         (into []))))
