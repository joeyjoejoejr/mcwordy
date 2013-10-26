(ns mcwordy.models.db
  (:require [com.flyingmachine.config :as config])
  (:use korma.db))

(defdb db (postgres (config/setting :app :db)))
