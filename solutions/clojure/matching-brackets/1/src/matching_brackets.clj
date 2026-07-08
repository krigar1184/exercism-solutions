(ns matching-brackets)

(def opens [\{ \( \[])
(def closes [\} \) \]])
(def pairs (zipmap closes opens))

(defn- check-close [ch stack]
  (if (empty? stack) false
      (= (pairs ch) (last stack))))

(defn valid?
  "Returns true if the given string has properly matched brackets;
  otherwise, it returns false."
  [s]
  (loop [acc [] input s]
    (if-let [ch (first input)]
      (cond
        (some #{ch} opens) (recur (conj acc ch) (next input))
        (some #{ch} closes)
        (if (not (check-close ch acc))
          false
          (recur (pop acc) (next input)))
        :else (recur acc (next input)))
      (empty? acc))))
