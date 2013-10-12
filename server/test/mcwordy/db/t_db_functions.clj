(ns mcwordy.db.t-db-functions
  (:require [mcwordy.db.test :as tdb]
            [mcwordy.db.maprules :as mr]
            [flyingmachine.cartographer.core :as c]
            [mcwordy.db.query :as q])
  (:use midje.sweet
        mcwordy.controllers.test-helpers))

(setup-db-background)

(defn watch
  []
  (q/one [:watch/topic]))

(fact "increment-register"
  (q/t [[:increment-watch-count (-> (watch) :watch/topic :db/id) 1]])
  (:watch/unread-count (watch))
  => 1)
