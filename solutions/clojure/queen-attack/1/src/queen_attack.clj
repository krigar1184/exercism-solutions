(ns queen-attack
  (:require [clojure.string :as s]))

(defn make-board [{:keys [w b]}]
  (let [[wy wx] w
        [by bx] b
        board (->> (for [_ (range 8) _ (range 8)] "_")
                   (partition 8)
                   (map vec)
                   (into []))]
    (if (and (nil? w) (nil? b)) board
        (-> (update-in board [wy wx] (fn [_] "W"))
            (update-in [by bx] (fn [_] "B"))))))

(defn board-string [board]
  (-> (make-board board)
      ((fn [b] (map #(s/join " " %) b)))
      ((fn [b] (s/join "\n" b)))
      (str "\n")))

(defn can-attack [{:keys [w b]}] ;; <- arglist goes here
  (or (= (w 0) (b 0))
      (= (w 1) (b 1))
      (= (abs (- (w 0) (b 0))) (abs (- (w 1) (b 1))))))
