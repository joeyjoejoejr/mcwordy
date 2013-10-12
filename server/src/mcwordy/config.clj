(ns mcwordy.config
  (:use environ.core))

(def conf {:html-paths ["html-app"
                        "../html-app/app"
                        "../html-app/.tmp"]
           :datomic {:db-uri "datomic:free://localhost:4334/mcwordy"
                     :test-uri "datomic:mem://mcwordy"}})

(defn config
  [& keys]
  (get-in conf keys))
