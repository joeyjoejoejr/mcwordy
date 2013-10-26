(ns mcwordy.models.db
  (:require [com.flyingmachine.config :as config])
  (:use korma.db))

(def db-config (postgres (config/setting :app :db)))
(defdb db db-config)
