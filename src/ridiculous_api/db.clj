(ns ridiculous-api.db
  (:require [ridiculous-api.lib.env :as env]
            [korma.db :refer :all]
            [clojure.string :refer [split]])
  (:import (java.net.URI)))

(let [prod-options (postgres
                     (let [env-url (System/getenv "DATABASE_URL")]
                       (if (nil? env-url)
                         {:host "localhost"
                          :db "ridiculous"
                          :user "ridiculous"
                          :password "password"}
                         (let [uri (new java.net.URI env-url)
                               [user password] (split (.getUserInfo uri) #":")]
                           {:host     (.getHost uri)
                            :user     user
                            :password password
                            :port     (.getPort uri)}))))

      test-options prod-options
      development-options prod-options]

  (def db-options
    (case (env/env)
      :production  prod-options
      :test        test-options
      :development development-options))

  (defdb app db-options))
