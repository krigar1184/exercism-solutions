(ns leap)

(defn leap-year?
  "Returns true if the given year is a leap year;
  otherwise, it returns false."
  [year]
  (and
   (zero? (rem year 4))
   (or (not (zero? (rem year 100))) (zero? (rem year 400)))))
