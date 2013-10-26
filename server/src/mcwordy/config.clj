(ns mcwordy.config
  (:require [com.flyingmachine.config :as config]
            [mcwordy.lib.utils :refer :all]
            [environ.core :refer :all]))


(config/update!
 {:app (let [environment (or (env :app-env) "development")]
         (read-resource (str "config/environments/" environment ".edn")))})
