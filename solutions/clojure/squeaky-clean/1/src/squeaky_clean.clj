(ns squeaky-clean
  (:require [clojure.string :as str]))

(defn- replace-spaces [s]
       (str/replace s #"\s" "_"))

(defn- replace-specials [s]
       (let [patterns [#"[\u0000-\u001F]" #"[\u007F-\u009F]"]]
         (reduce #(str/replace %1 %2 "CTRL") s patterns)))

(defn- capitalize [s]
       (apply str (str/upper-case (first s)) (rest s)))

(defn- replace-kebab [s]
       (let [splitted (str/split s #"-")]
         (str
          (first splitted)
          (str/join (map capitalize (rest splitted))))))

(defn- omit-not-letters [s]
       (str/join
        (filter #(or (Character/isLetter %) (Character/isSpaceChar %) (= % \_)) (seq s))))

(defn- omit-lowercase-greeks [s]
       (str/join
        (filter
         #(not
           (and
                (Character/isLowerCase %)
                (>= (Character/hashCode %) 945)
                (<= (Character/hashCode %) 969))
           )
         s)))

(defn clean
  "TODO: add docstring"
  [s]
  (-> s
    replace-spaces
    replace-specials
    replace-kebab
    omit-not-letters
    omit-lowercase-greeks
    ))
