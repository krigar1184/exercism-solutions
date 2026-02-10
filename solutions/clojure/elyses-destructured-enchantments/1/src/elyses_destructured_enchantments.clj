(ns elyses-destructured-enchantments)

(defn first-card
  "Returns the first card from deck."
  [deck]
  (let [[first _] deck] first))

(defn second-card
  "Returns the second card from deck."
  [deck]
  (let [[_ second _] deck] second))

(defn swap-top-two-cards
  "Returns the deck with first two items reversed."
  [deck]
  (let [a (first-card deck)
        b (second-card deck)
        [_ _ & others] deck]
    (concat [b] [a] others)))

(defn discard-top-card
  "Returns a sequence containing the first card and
   a sequence of the remaining cards in the deck."
  [deck]
  (let [[_ & others] deck]
    (conj [(first-card deck)] others)))

(def face-cards
  ["jack" "queen" "king"])

(defn insert-face-cards
  "Returns the deck with face cards between its head and tail."
  [deck]
  (if (empty? deck) face-cards
      (concat [(first-card deck)] face-cards (rest deck))))
