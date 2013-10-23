(ns mcwordy.models.entities
  (:require [cemerick.friend (credentials :as creds)]
            [korma.core :refer :all]
            [mcwordy.lib.utils :refer :all]))

(declare user)

(defentity moosh
  (belongs-to user)
  (has-many comment)
  (has-many favorite)
  (prepare hidden-text->boolean))

(defentity user
  (has-many moosh)

  ;; todo move user param transform function here
  (prepare
   (fn [attributes]
     (-> (transform-when-key-exists
          attributes
          {:password #(creds/hash-bcrypt %)})
         (assoc :roles (str [:user])))))
  
  (transform #(deserialize % :roles)))

(defentity user_session
  (prepare #(merge % {:data (str (:data %))}))
  (transform #(deserialize % :data)))