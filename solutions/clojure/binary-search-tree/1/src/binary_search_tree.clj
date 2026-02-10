(ns binary-search-tree)

(defn make-node [v left right]
  {:value v
   :left left
   :right right})

(defn value [node] ;; <- arglist goes here
  (get node :value))

(defn singleton [value] ;; <- arglist goes here
  (make-node value nil nil))

(defn left [node] ;; <- arglist goes here
  (get node :left))

(defn right [node] ;; <- arglist goes here
  (get node :right))

(defn insert [v node] ;; <- arglist goes here
  (let [cur (value node)]
    (cond (nil? node) (singleton v)
          (<= v cur) (make-node cur (insert v (left node)) (right node))
          :else (make-node cur (left node) (insert v (right node))))))

(defn to-list [node] ;; <- arglist goes here
  (if (nil? node) nil
      (->> (conj (to-list (right node)) (list (value node)) (to-list (left node)))
           (filter #(not (nil? %)))
           flatten)))

(defn from-list [l] ;; <- arglist goes here
  (reduce (fn [acc cur]
            (insert cur acc)) (singleton (first l)) (rest l)))
