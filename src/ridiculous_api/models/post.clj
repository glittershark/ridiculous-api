(ns ridiculous-api.models.post
  (:refer-clojure :exclude [find])
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

(defn change
  "Update a post with the given id and new params"
  [id {:keys [:content], :as args}]
  (->
    (update posts
            (set-fields args)
            (where {:id id}))))
