(ns mcwordy.controllers.posts
  (:require [datomic.api :as d]
            [mcwordy.db.validations :as validations]
            [mcwordy.db.query :as db]
            [mcwordy.db.transactions :as ts]
            [mcwordy.db.maprules :as mr]
            [flyingmachine.cartographer.core :as c])
  (:use [flyingmachine.webutils.validation :only (if-valid)]
        [liberator.core :only [defresource]]
        mcwordy.controllers.shared
        mcwordy.models.permissions
        mcwordy.db.mapification
        mcwordy.utils))

(defmapifier record
  mr/ent->post)

(defresource update! [params auth]
  :allowed-methods [:put :post]
  :available-media-types ["application/json"]

  :malformed? (validator params (:update validations/post))
  :handle-malformed errors-in-ctx

  :authorized? (can-update-record? (record (id)) auth)
  :exists? record-in-ctx
  :put! (ts/update-post params)
  :post! (ts/update-post params)
  :new? false
  :respond-with-entity? true
  :handle-ok (fn [_] (record (id))))

(defresource create! [params auth]
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :authorized? (logged-in? auth)

  :malformed? (validator params (:create validations/post))
  :handle-malformed errors-in-ctx

  :post! (create-content ts/create-post params auth record)
  :handle-created record-in-ctx)

(defresource delete! [params auth]
  :allowed-methods [:delete]
  :available-media-types ["application/json"]
  :authorized? (can-delete-record? (record (id)) auth)
  :exists? exists-in-ctx?
  :delete! delete-record-in-ctx)
