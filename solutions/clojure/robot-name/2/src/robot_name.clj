(ns robot-name)

(def alphabet (seq (for [u (range 65 91)] (char u))))
(def digits (seq (range 10)))

(def taken-names (atom (set {})))

(defn generate-name []
  (let [gen-fn (fn []
                 (let [name
                       (apply str
                              (apply conj
                                     (for [_ (range 3)] (rand-nth digits))
                                     (for [_ (range 2)] (rand-nth alphabet))))]
                   (if (contains? @taken-names name) (recur)
                       (do (swap! taken-names conj name) name))))]
    (gen-fn)))

(defn robot [] ;; <- arglist goes here
  (let [r (atom {:name (generate-name)})]
    (fn ([name] (swap! r assoc :name name))
      ([] (get @r :name)))))

(defn robot-name [robot] ;; <- arglist goes here
  (robot))

(defn reset-name [robot] ;; <- arglist goes here
  (let [new-name (generate-name)]
    (robot new-name)))
