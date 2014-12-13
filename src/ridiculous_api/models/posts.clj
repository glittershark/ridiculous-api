(ns ridiculous-api.models.posts
  (:require [ridiculous-api.db :as db])
  (:require [korma.core :refer :all]))

(defentity posts)

(defn all
  "Select all posts"
  [] (select posts))

(defn create
  "Create a new post"
  [{:keys [:content], :as args}]
  (insert posts (values args)))
