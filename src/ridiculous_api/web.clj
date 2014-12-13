(ns ridiculous-api.web
  (:require [ridiculous-api.controllers.posts :as posts]
            [ring.adapter.jetty :as ring]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [compojure.core :refer [GET POST defroutes]]
            [ring.util.response :refer [resource-response response]]
            [lobos.config :as config]
            [lobos.core :refer [migrate]]
            [ring.middleware.json :as middleware]))

(defroutes app-routes
  posts/routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))

(defn start [port]
  (config/init)
  (migrate)
  (ring/run-jetty app {:port port
                       :join? false}))
