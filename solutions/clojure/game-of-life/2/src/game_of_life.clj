(ns game-of-life)

(defn get-neighbors-coords [[row col] width height]
  (let [offsets (for [x (range -1 2) y (range -1 2) :when (not= [0 0] [x y])] [y x])]
    [[row col] (->> offsets
                    (map #(mapv + [row col] %))
                    (filter (fn [[r c]] (and (< -1 c width) (< -1 r height)))))]))

(defn- get-alive-neighbor-count [cells neighbors-coords]
  (count (->> neighbors-coords
              (map (fn [[row col]] (get-in cells [row col])))
              (filter #(= 1 %)))))

(defn get-cell-value [[row col] neighbors-coords cells]
  (let [current-value (get-in cells [row col])
        alive-neighbor-count (get-alive-neighbor-count cells neighbors-coords)]
    (cond (= alive-neighbor-count 3) 1
          (and (= current-value 1) (= alive-neighbor-count 2)) 1
          :else 0)))

(defn tick
  "Returns the next generation of the cells."
  [cells]
  (let [height (count cells)
        width (count (first cells))
        coords (for [row (range height) col (range width)] [row col])
        neighbors-coords (into {} (map #(get-neighbors-coords % width height) coords))]
    (->> (map #(get-cell-value % (neighbors-coords %) cells) coords)
         (partitionv width)
         (into []))))
