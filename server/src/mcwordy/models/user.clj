(ns mcwordy.models.user
  (:require mcwordy.models.db
            [mcwordy.models.entities :as e]
            [korma.core :refer :all]
            [mcwordy.lib.utils :refer :all])

  (:import org.mindrot.jbcrypt.BCrypt))

(declare one)

(def validations
  {:username
   ["Your username must be between 4 and 24 characters long"
    #(and
      (>= (count %) 4)
      (<= (count %) 24))
    "That username is already taken"
    #(not (one {:username %}))]
   
   :password
   ["Your password must be at least 4 characters long"
    #(>= (count %) 4)]
   
   :change-password
   ["Your passwords do not match"
    #(= (:new-password %) (:new-password-confirmation %))

    "Your password must be at least 4 characters long"
    #(>= (count (:new-password %)) 4)]
  
   :email
   ["You must enter a valid email address"
    #(and
      (not-empty %)
      (re-find #"@" %))]})

(def validation-contexts
  {:create (select-keys validations [:username :password :email])
   :update-email (select-keys validations [:email])
   :change-password (fn [pw]
                      (update-in
                       (select-keys validations [:change-password])
                       [:change-password]
                       conj
                       "You didn't enter the correct value for your current password"
                       #(BCrypt/checkpw (:current-password %) pw)))})



(defn create!
  [attributes]
  ;; not sure why insert returns serialized roles
  (let [attributes (merge {:receive_comment_notifications false
                           :receive_newsletter false}
                          attributes)]
    (deserialize
     (insert e/user (values attributes))
     :roles)))

(defn update!
  [conditions attributes]
  (update e/user
          (set-fields attributes)
          (where conditions)))

(defn one
  [conditions]
  (first (select e/user
                 (where conditions)
                 (limit 1))))