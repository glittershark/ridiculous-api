(ns lobos.config
  (:require [ridiculous-api.db :refer [db-options]])
  (:use lobos.connectivity))

(defn init [] (open-global db-options))
