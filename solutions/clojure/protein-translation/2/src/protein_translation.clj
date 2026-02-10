(ns protein-translation)

(def codons {"AUG" "Methionine"
             "UUU" "Phenylalanine"
             "UUC" "Phenylalanine"
             "UUA" "Leucine"
             "UUG" "Leucine"
             "UCU" "Serine"
             "UCC" "Serine"
             "UCA" "Serine"
             "UCG" "Serine"
             "UAU" "Tyrosine"
             "UAC" "Tyrosine"
             "UGU" "Cysteine"
             "UGC" "Cysteine"
             "UGG" "Tryptophan"})

(defn translate-codon [codon] ;; <- arglist goes here
  (get codons codon))

(defn translate-rna [rna] ;; <- arglist goes here
  (loop [acc [] cur (take 3 rna) tail (nthnext rna 3)]
    ; (println acc cur tail)
    (if (empty? cur) acc
        (if-let [protein (translate-codon (apply str cur))]
          (recur (conj acc protein) (take 3 tail) (nthnext tail 3)) acc))))
