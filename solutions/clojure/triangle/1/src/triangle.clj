(ns triangle)

(defn is-valid? [& [a b c]] ;; <- arglist goes here
  (and (> a 0) (> b 0) (> c 0) (>= (+ a b) c) (>= (+ a c) b) (>= (+ b c) a)))

(defn equilateral? [a b c] ;; <- arglist goes here
  (and (is-valid? a b c) (= a b c)))

(defn isosceles? [a b c] ;; <- arglist goes here
  (and (is-valid? a b c) (or (= a b) (= a c) (= b c))))

(defn scalene? [a b c] ;; <- arglist goes here
  (and (is-valid? a b c) (false? (isosceles? a b c))))
