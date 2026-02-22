(ns bank-account)

(def ^:private accounts- (atom {}))

(defn open-account []
  (let [account-id random-uuid
        new-account {:id account-id :balance 0, :state :open}]
    (swap! accounts- assoc account-id new-account)
    new-account))

(defn close-account [{:keys [id]}]
  (let [account (@accounts- id)
        updated-account (assoc account :state :closed)]
    (swap! accounts- assoc id updated-account)
    nil))

(defn get-balance [{:keys [id]}]
  (let [{:keys [state balance]} (@accounts- id)]
    (if (= state :closed) nil
        balance)))

(defn update-balance [{:keys [id status]} value]
  (when (= status :closed) nil)
  (locking id
    (let [account (@accounts- id)
          new-balance (+ (account :balance) value)
          updated-account (assoc account :balance new-balance)]
      (swap! accounts- assoc id updated-account))))
