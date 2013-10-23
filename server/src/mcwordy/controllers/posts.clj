(ns mcwordy.controllers.posts
  (:require [flyingmachine.webutils.validation :refer (if-valid)]
            [liberator.core :refer (defresource)]
            [mcwordy.controllers.shared :refer :all]
            [mcwordy.models.permissions :refer :all]
            [mcwordy.lib.utils :refer :all]))


(defresource update! [params auth]
  :allowed-methods [:put :post]
  :available-media-types ["application/json"]

  ;; :malformed? (validator params (:update validations/post))
  :handle-malformed errors-in-ctx

  ;; :authorized? (can-update-record? (record (id)) auth)
  :exists? record-in-ctx
  :put! (fn [] "TODO")
  :post! (fn [] "TODO")
  :new? false
  :respond-with-entity? true
  ;; :handle-ok (fn [_] (record (id)))
  )

(defresource create! [params auth]
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :authorized? (logged-in? auth)

  ;; :malformed? (validator params (:create validations/post))
  :handle-malformed errors-in-ctx

  ;; :post! (create-content ts/create-post params auth record)
  :handle-created record-in-ctx)

(defresource delete! [params auth]
  :allowed-methods [:delete]
  :available-media-types ["application/json"]
  ;; :authorized? (can-delete-record? (record (id)) auth)
  :exists? exists-in-ctx?
  ;; :delete! delete-record-in-ctx
  )
