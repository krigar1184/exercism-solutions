(ns clock)

(defn clock->string [{:keys [hours minutes]}] ;; <- arglist goes here
  (str (format "%02d" hours) ":" (format "%02d" minutes)))

(defn make-clock [hours minutes] ;; <- arglist goes here
  (letfn [(add-hours [value cl]
            (let [new-hours (+ value (cl :hours))]
              (cond (> new-hours 24) (recur (- new-hours 24) cl)
                    (= new-hours 24) (assoc cl :hours 0)
                    :else (assoc cl :hours new-hours))))

          (add-minutes [value cl]
            (let [new-minutes (+ value (cl :minutes))]
              (cond
                (> new-minutes 60) (recur (- new-minutes 60) (add-hours 1 cl))
                (= new-minutes 60) (add-hours 1 cl)
                :else (assoc cl :minutes new-minutes))))]

    (trampoline
     add-minutes minutes (add-hours hours {:hours 0 :minutes 0}))))

(defn adjust-minutes [minutes]
  (if (< minutes 0) (recur (+ (* 24 60) minutes)) minutes))

(defn clock [hours minutes]
  (make-clock 0 (adjust-minutes (+ (* 60 hours) minutes))))

(defn add-time [{:keys [hours minutes]} time]
  (make-clock hours (+ minutes (adjust-minutes time))))
