(ns all-your-base)

(defn enumerate [digits]
  (let [reversed (reverse digits)]
    (loop [i 0 acc []]
      (if (= i (count digits)) (reverse acc)
          (recur (inc i) (conj acc [i (nth reversed i)]))))))

(defn convert-to-decimal [base digits]
  (let [enumerated (enumerate digits)]
    (apply +
           (map
            #(* (get % 1) (Math/pow base (get % 0)))
            enumerated))))

(defn convert-from-decimal [base number]
  (loop [acc (list) cur number]
    (if (== cur 0)
      (if (empty? acc) '(0) acc)
      (recur (conj acc (int (rem cur base))) (int (/ cur base))))))

(defn- validate-base [v]
  (> v 1))

(defn- validate-digits [xs]
  (and (not (nil? (seq xs))) (every? #(>= % 0) xs)))

(defn convert [src-base digits target-base]
  (if (and
       (validate-base src-base)
       (validate-base target-base)
       (validate-digits digits)
       (every? #(> src-base %) digits))
    (let [as-decimal (convert-to-decimal src-base digits)]
      (convert-from-decimal target-base as-decimal)) nil))
