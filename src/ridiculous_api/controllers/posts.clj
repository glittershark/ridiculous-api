(ns ridiculous-api.controllers.posts
  (:require [ridiculous-api.models.post :as post]
            [ring.util.response :as ring]
            [compojure.core :refer [GET POST DELETE defroutes]]
            [clojure.string :as str]))

(defn index []
  (ring/response (post/all)))

(defn create [{:keys [:content], :as args}]
  (if (str/blank? content)
    (ring/response {:error "Content cannot be blank" :original-args args})
    (ring/response (post/create args))))

(defn show [id]
  (ring/response
    (try
      (post/find (Integer. id))
      (catch NumberFormatException e
        {:error (str "Invalid id " id)}))))

(defroutes routes
  (POST "/posts"     {post :params}     (create post))
  (GET  "/"          []                 (index))
  (GET  "/posts/:id" {{id :id} :params} (show id)))
