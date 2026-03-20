(ns isogram)

(defn isogram?
  "Returns true if the given string is an isogram;
     otherwise, it returns false."
  [s]
  (let [chars-only (->> s
                        (map Character/toLowerCase)
                        (filter Character/isLetter))]
    (= (count chars-only) (count (set chars-only)))))
