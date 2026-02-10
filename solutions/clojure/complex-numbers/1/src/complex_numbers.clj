(ns complex-numbers)

(defn real [[a b]] ;; <- arglist goes here
  a)

(defn imaginary [[a b]] ;; <- arglist goes here
  b)

(defn abs [[a b]] ;; <- arglist goes here
  (Math/sqrt (+ (Math/pow a 2) (Math/pow b 2))))

(defn conjugate [[a b]] ;; <- arglist goes here
  [a (- b)])

(defn add [[a b] [c d]] ;; <- arglist goes here
  [(+ a c) (+ b d)])

(defn sub [[a b] [c d]] ;; <- arglist goes here
  [(- a c) (- b d)])

(defn mul [[a b] [c d]] ;; <- arglist goes here
  [(- (* a c) (* b d)) (+ (* a d) (* b c))])

(defn div [[a b] [c d]] ;; <- arglist goes here
  [(/ (+ (* a c) (* b d)) (+ (Math/pow c 2) (Math/pow d 2)))
   (/ (- (* b c) (* a d)) (+ (Math/pow c 2) (Math/pow d 2)))])
