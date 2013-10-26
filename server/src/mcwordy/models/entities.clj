(ns mcwordy.models.entities
  (:require [cemerick.friend (credentials :as creds)]
            [korma.core :refer :all]
            [mcwordy.lib.utils :refer :all]))

(declare user)

(defentity moosh)
