(ns sublist)

(defn equals [list1 list2]
  (if (not= (count list1) (count list2)) false
      (loop [i 0]
        (if (= i (count list1)) true
            (if  (not= (nth list1 i) (nth list2 i)) false
                 (recur (inc i)))))))

(defn sublist [list1 list2]
  (if (empty? list1) true
      (let [parts (partition (count list1) 1 list2)]
        (loop [i 0]
          (if (= i (count parts)) false
              (if (equals list1 (nth parts i)) true
                  (recur (inc i))))))))

(defn superlist [list1 list2]
  (if (empty? list2) true
      (let [parts (partition (count list2) 1 list1)]
        (loop [i 0]
          (if (= i (count parts)) false
              (if (equals list2 (nth parts i)) true
                  (recur (inc i))))))))

(defn classify [list1 list2] ;; <- arglist goes here
  (cond (equals list1 list2) :equal
        (sublist list1 list2) :sublist
        (superlist list1 list2) :superlist
        :else :unequal))
