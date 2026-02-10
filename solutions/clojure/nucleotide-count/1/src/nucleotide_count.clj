(ns nucleotide-count)

(def allowed-chars [\A \C \G \T])

(defn validate-strand [s]
  (loop [i 0]
    (if (= i (count s)) true
        (let [cur (nth s i)]
          (if (empty? (filter #(= % cur) allowed-chars)) false
              (recur (inc i)))))))

(defn count-of-nucleotide-in-strand [nucleotide strand] ;; <- Arglist goes here
  (when-not (validate-strand [nucleotide]) (throw Exception))
  (count (filter #(= % nucleotide) strand)))

(defn nucleotide-counts [strand] ;; <- Arglist goes here
  (reduce (fn [acc cur]
            (when-not (validate-strand [cur]) (throw Exception))
            (assoc acc cur (inc (get acc cur)))) {\A 0 \C 0 \G 0 \T 0} strand))
