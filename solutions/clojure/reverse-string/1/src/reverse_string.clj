(ns reverse-string)

(defn reverse-string [s]
  (let [[head & tail] s]
    (if (empty? (str head)) s
      (str (reverse-string tail) (str head))))
)
