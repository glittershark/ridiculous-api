(defproject ridiculous-api "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[compojure "1.3.1"]
                 [korma "0.4.0"]
                 [lobos "1.0.0-beta3"]
                 [org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [postgresql "9.1-901.jdbc4"]
                 [ring/ring-defaults "0.1.2"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [ring/ring-json "0.2.0"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler ridiculous-api.core.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
