(defproject mcwordy "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :repositories [["central-proxy" "http://repository.sonatype.org/content/repositories/central/"]]
  :jvm-opts ["-Xmx2G"]

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [lobos "1.0.0-beta1"]
                 [postgresql "9.1-901.jdbc4"]
                 [environ "0.4.0"]
                 [ring "1.2.0"]
                 [ring-mock "0.1.4"]
                 [ring-middleware-format "0.3.0"]
                 [compojure "1.1.5"]
                 [liberator "0.9.0"]
                 [com.cemerick/friend "0.1.5"]
                 [com.flyingmachine/webutils "0.1.1"]
                 [markdown-clj "0.9.25"]
                 [clavatar "0.2.1"]
                 [org.clojure/data.json "0.2.2"]]

  :plugins [[lein-environ "0.4.0"]]

  :resource-paths ["resources"]
  
  :profiles {:dev {:dependencies [[midje "1.5.0"]]}}
  
  :main mcwordy.app)
