(ns lobos.config
  (:require [mcwordy.models.db :as db])
  (:use lobos.connectivity))

(open-global db/db-config)