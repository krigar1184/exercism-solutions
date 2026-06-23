(ns dnd-character)

(defrecord DndCharacter [hitpoints
                         strength
                         dexterity
                         constitution
                         intelligence
                         wisdom
                         charisma])

(defn score-modifier
  "Calculates the modifier of the given score."
  [score]
  (int (Math/floor (/ (- score 10) 2))))

(defn rand-ability
  "Generates a random ability."
  []
  (+ 3 (rand-int 16)))

(defn rand-character
  "Generates a random character."
  []
  (let [scores (->> (for [_ (range 6)] (rand-ability))
                    (zipmap [:strength
                             :dexterity
                             :constitution
                             :intelligence
                             :wisdom
                             :charisma]))
        hitpoints (+ 10 (score-modifier (:constitution scores)))]
    (io! (println scores hitpoints))
    (map->DndCharacter (assoc scores :hitpoints hitpoints))))
