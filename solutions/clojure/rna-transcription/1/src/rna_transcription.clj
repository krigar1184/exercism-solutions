(ns rna-transcription)

(def dna2rna {\G \C
              \C \G
              \T \A
              \A \U})

(defn to-rna [dna] ;; <- arglist goes here
  (apply str (map #(get dna2rna %) dna)))
