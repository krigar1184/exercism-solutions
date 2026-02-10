(ns strain)

(defn retain [f coll] ;; <- arglist goes here
  (loop [acc (list) i 0]
    (if (= i (count coll)) (reverse acc)
        (let [cur (nth coll i)]
          (recur
           (if (f cur) (conj acc cur) acc)
           (inc i))))))

(defn discard [f coll] ;; <- arglist goes here
  (loop [acc (list) i 0]
    (if (= i (count coll)) (reverse acc)
        (let [cur (nth coll i)]
          (recur (if (not (f cur)) (conj acc cur) acc) (inc i))))))
