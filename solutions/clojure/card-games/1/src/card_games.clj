(ns card-games)

(defn rounds
  "Takes the current round number and returns 
   a `list` with that round and the _next two_."
  [n]
  (let [o (inc n)]
    `(~n ~o ~(inc o))))

(defn concat-rounds 
  "Takes two lists and returns a single `list` 
   consisting of all the rounds in the first `list`, 
   followed by all the rounds in the second `list`"
  [l1 l2]
  (concat l1 l2))

(defn contains-round? 
  "Takes a list of rounds played and a round number.
   Returns `true` if the round is in the list, `false` if not."
  [l n]
  (>= (count (filter #(= n %) l)) 1))

(defn card-average
  "Returns the average value of a hand"
  [hand]
  (if (> (count hand) 0) (double (/ (apply + hand) (count hand)))
    0))

(defn- fl-average
       [hand]
       (double (/ (+ (first hand) (last hand)) 2)))

(defn- median
       [hand]
       (double (nth hand (int (/ (count hand) 2)))))

(defn approx-average?
  "Returns `true` if average is equal to either one of:
  - Take the average of the _first_ and _last_ number in the hand.
  - Using the median (middle card) of the hand."
  [hand]
  (let [true-average (card-average hand)]
    (or (= true-average (median hand)) (= true-average (fl-average hand))))
    )

(defn average-even-odd?
  "Returns true if the average of the cards at even indexes 
   is the same as the average of the cards at odd indexes."
  [hand]
  (let [indexes (range (count hand))
        hand-as-vector (apply vector hand)
        odds (filter odd? indexes)
        evens (filter even? indexes)]
    (= (card-average (map #(get hand-as-vector %) odds)) (card-average (map #(get hand-as-vector %) evens)))))

(defn maybe-double-last
  "If the last card is a Jack (11), doubles its value
   before returning the hand."
  [hand]
  (if
    (= (last hand) 11) (concat (take (dec (count hand)) hand) (list (* 2 (last hand))))
    hand))
