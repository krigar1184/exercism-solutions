(ns allergies)

(def scores {:eggs 1
             :peanuts 2
             :shellfish 4
             :strawberries 8
             :tomatoes 16
             :chocolate 32
             :pollen 64
             :cats 128})

(defn allergic-to?
  "Returns true if the score indicates an allergy to the allergen;
  otherwise, it returns false."
  [score allergen]
  (> (bit-and score (scores allergen)) 0))

(defn allergies
  "Returns all allergens associated with the score."
  [score]
  (->> (seq scores)
       (filter #(> (bit-and score (second %)) 0))
       (map first)))
