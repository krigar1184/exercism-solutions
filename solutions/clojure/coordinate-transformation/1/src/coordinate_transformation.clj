(ns coordinate-transformation)

(def increment
  (let [counter (atom 0)]
    (fn [] (swap! counter inc))))

(defn translate2d
  "Returns a function making use of a closure to
   perform a repeatable 2d translation of a coordinate pair."
  [dx dy]
  (let [val (atom [dx dy])]
    (fn [a b] (swap! val (fn [x] (map + x [a b]))))))

(defn scale2d
  "Returns a function making use of a closure to
   perform a repeatable 2d scale of a coordinate pair."
  [sx sy]
  (let [val (atom [sx sy])]
    (fn [a b] (swap! val (fn [x] (map * x [a b]))))))

(defn compose-transform
  "Create a composition function that returns a function that 
   combines two functions to perform a repeatable transformation."
  [f g]
  (fn [& args]
    (apply g (apply f args))))

(defn memoize-transform
  "Returns a function that memoizes the last result.
   If the arguments are the same as the last call,
   the memoized result is returned."
  [f]
  (let [cache (atom {})]
    (fn [& args]
      (let [cached (get @cache args)]
        (if (= nil cached)
          (let [res (apply f args)]
            (reset! cache {args res})
            res)
          (do (reset! cache {}) cached))))))
