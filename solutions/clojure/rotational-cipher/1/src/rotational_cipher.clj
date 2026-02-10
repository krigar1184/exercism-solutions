(ns rotational-cipher)

(defn rotate-char [c n]
  (if (not (Character/isLetter c)) c
      (let [[start end]
            (if (>= (int c) (int \a))
              [(int \a) (int \z)]
              [(int \A) (int \Z)])]
        (if (> (+ (int c) n) end)
          (char (dec (+ start (- (+ n (int c)) end))))
          (char (+ (int c) n))))))

(defn rotate [text n]
  (if (or (= n 0) (= n 26)) text
      (apply str (map #(rotate-char % n) text))))

