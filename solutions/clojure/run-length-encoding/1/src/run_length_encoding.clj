(ns run-length-encoding)

(defn run-length-encode
  "encodes a string with run-length-encoding"
  [plain-text]
  (loop [acc (list) i 0 cnt 1]
    (if (= i (count plain-text)) (apply str (reverse (filter #(or (Character/isWhitespace %) (Character/isLetter %) (> % 1)) acc)))
        (let [cur (nth plain-text i)
              nxt (nth plain-text (inc i) nil)]
          (if (= cur nxt) (recur acc (inc i) (inc cnt))
              (recur (conj acc cnt cur) (inc i) 1))))))

(defn parse-int [n]
  (try (Integer/parseInt n) (catch NumberFormatException _ nil)))

(defn run-length-decode
  "decodes a run-length-encoded string"
  [cipher-text]
  (let [data (re-seq #"\d+|\D" cipher-text)]
    (loop [acc (list) i 0]
      (if (>= i (count data)) (apply str (reverse acc))
          (let [cur (nth data i)
                nxt (nth data (inc i) nil)]
            (if-let [n (parse-int cur)]
              (recur (apply conj acc (repeat n nxt)) (+ i 2))
              (recur (conj acc cur) (inc i))))))))
