(ns gigasecond)

(def month-to-days
  {1 (fn [_] 31) ,
   2 (fn [leap?] (if leap? 29 28)) ,
   3 (fn [_] 31) ,
   4 (fn [_] 30) ,
   5 (fn [_] 31) ,
   6 (fn [_] 30) ,
   7 (fn [_] 31) ,
   8 (fn [_] 31) ,
   9 (fn [_] 30) ,
   10 (fn [_] 31) ,
   11 (fn [_] 30) ,
   12 (fn [_] 31)})

(def day-in-seconds (* 60 60 24))

(defn is-leap-year?
  [year]
  (and
   (zero? (rem year 4))
   (or (not (zero? (rem year 100))) (zero? (rem year 400)))))

(defn inc-month [month]
  (if (= month 12) [1 1]
      [(inc month) 0]))

(defn inc-day [month day leap?]
  (if (= day ((month-to-days month) leap?)) [1 1]
      [(inc day) 0]))

(defn from
  "Determines the date one gigasecond after the given date."
  [year month day]
  (loop [y year m month d day secs 1000000000]
    (let [leap? (is-leap-year? y)
          ds day-in-seconds]
      (cond (>= secs ds)
            (let [[next-d rm] (inc-day m d leap?)
                  [next-m ry] (if (zero? rm)
                                [m 0]
                                (inc-month m))]
              (recur (+ y ry) next-m next-d (- secs ds)))
            :else [y m d]))))
