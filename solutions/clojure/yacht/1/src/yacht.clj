(ns yacht)

(defmulti calculate (fn [_ category] category))
(defmethod calculate "ones" [{ones 1} _] (apply + ones))
(defmethod calculate "twos" [{twos 2} _] (apply + twos))
(defmethod calculate "threes" [{threes 3} _] (apply + threes))
(defmethod calculate "fours" [{fours 4} _] (apply + fours))
(defmethod calculate "fives" [{fives 5} _] (apply + fives))
(defmethod calculate "sixes" [{sixes 6} _] (apply + sixes))
(defmethod calculate "full house" [dice _]
  (let [groups (filter (fn [[_ v]] (not (empty? v))) dice)]
    (if (and (= (count groups) 2) (some (fn [[_ v]] (= (count v) 2)) groups))
      (apply + (flatten (vals dice)))
      0)))
(defmethod calculate "four of a kind" [dice _]
  (if-let [[[_ group] & _] (filter (fn [[_ v]] (>= (count v) 4)) dice)]
    (apply + (take 4 group))
    0))
(defmethod calculate "little straight" [dice _]
  (if (every? (fn [[_ group]] (not (empty? group))) (take 5 dice))
    30 0))
(defmethod calculate "big straight" [dice _]
  (if (every? (fn [[_ group]] (not (empty? group))) (drop 1 dice))
    30 0))
(defmethod calculate "choice" [dice _]
  (apply + (flatten (vals dice))))
(defmethod calculate "yacht" [dice _]
  (if (apply = (flatten (vals dice))) 50 0))

(defn score
  "Given five dice and a category, it returns the score of the dice
  for that category."
  [dice category]
  (let [nums (range 1 7)
        groups (group-by identity dice)
        dicefull (for [n nums] (or (groups n) []))
        dicemap (zipmap nums dicefull)]
    (calculate dicemap category)))
