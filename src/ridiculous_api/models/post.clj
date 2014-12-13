(ns ridiculous-api.models.post
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

(defn find
  "Find a post with the given ID"
  [id]
  (->
    (select posts
            (where {:id id})
            (limit 1))
    (first)))
