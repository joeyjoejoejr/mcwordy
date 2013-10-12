(ns mcwordy.middleware.routes
  (:require compojure.route
            compojure.handler
            [ring.util.response :as resp]
            [mcwordy.controllers.posts :as posts]
            [mcwordy.controllers.users :as users]
            [mcwordy.controllers.session :as session]
            [cemerick.friend :as friend])
  (:use [compojure.core :as compojure.core :only (GET PUT POST DELETE ANY defroutes)]
        mcwordy.config))


(defmacro route
  [method path handler]
  `(~method ~path {params# :params}
            (~handler params#)))

(defmacro authroute
  [method path handler]
  (let [params (quote params)]
    `(~method ~path {:keys [~params] :as req#}
              (~handler ~params (friend/current-authentication req#)))))

(defroutes routes
  ;; Serve up angular app
  (apply compojure.core/routes
         (map #(compojure.core/routes
                (compojure.route/files "/" {:root %})
                (compojure.route/resources "/" {:root %}))
              (reverse (config :html-paths))))
  (apply compojure.core/routes
         (map (fn [response-fn]
                (GET "/" [] (response-fn "index.html" {:root "html-app"})))
              [resp/file-response resp/resource-response]))
    
  ;; Posts
  (authroute POST "/posts" posts/create!)
  (authroute PUT  "/posts/:id" posts/update!)
  (authroute POST "/posts/:id" posts/update!)
  (authroute DELETE "/posts/:id" posts/delete!)

  ;; Users
  (authroute POST "/users" users/registration-success-response)
  (route GET "/users/:id" users/show)
  (authroute POST "/users/:id" users/update!)
  (authroute POST "/users/:id/password" users/change-password!)

  ;; auth
  (route POST "/login" session/create!)
  (friend/logout
   (ANY "/logout" []
        (ring.util.response/redirect "/")))

  (ANY "/debug" {:keys [x] :as r}
       (str x))
  
  (compojure.route/not-found "Sorry, there's nothing here."))
