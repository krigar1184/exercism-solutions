(ns secret-handshake)

(defn command [i]
  (cond (= i 0) "wink"
        (= i 1) "double blink"
        (= i 2) "close your eyes"
        (= i 3) "jump"))

(defn commands [n] ;; <- arglist goes here
  (let [b (reverse (Integer/toBinaryString n))]
    (loop [acc [] i 0]
      (let [v (nth b i nil)]
        (if (or (>= i 4) (nil? v))
          (if (= v \1) (reverse acc) acc)
          (if (= v \0) (recur acc (inc i))
              (recur (conj acc (command i)) (inc i))))))))
