(ns interest-is-interesting)

(defn interest-rate
  "Returns the interest rate based on the specified balance."
  [balance]
  (cond (< balance 0) -3.213
        (< balance 1000) 0.5
        (and (>= balance 1000) (< balance 5000)) 1.621
        :else 2.475))

(defn annual-balance-update
  "Returns the annual balance update, taking into account the interest rate."
  [balance]
  (let [int-rate (bigdec (interest-rate balance))
        op (if (pos? balance) + -)]
    (op balance
     (* balance (bigdec (/ int-rate 100))))))

(defn amount-to-donate
  "Returns how much money to donate based on the balance and the tax-free percentage."
  [balance tax-free-percentage]
  (if (pos? balance)
    (int (* 2(/ (* balance tax-free-percentage) 100)))
    0))