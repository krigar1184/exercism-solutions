(ns grade-school)

(defn grade [school g]
  (school g []))

(defn add [school name g]
  (let [students-in-grade (grade school g)]
    (assoc school g (into students-in-grade [name]))))

(defn sorted [school]
  (into (sorted-map) (for [[k v] school] [k (-> v sort vec)])))
