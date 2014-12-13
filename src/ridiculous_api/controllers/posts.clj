(ns ridiculous-api.controllers.posts
  (:require [ridiculous-api.models.post :as post]
            [ring.util.response :as ring]
            [compojure.core :refer [GET POST PUT DELETE defroutes]]
            [clojure.string :as str]
            [ridiculous-api.lib.core :refer [try-parse-int]]
            [ridiculous-api.lib.mangler :refer [mangle]]))

(defn mangle-post [{content :content}]
  {:content (mangle content)})

(defn index []
  (ring/response (map mangle-post (post/all))))

(defn create [{:keys [:content], :as args}]
  (if (str/blank? content)
    (ring/response {:error "Content cannot be blank" :original-args args})
    (ring/response (->
                     (post/create args)
                     (mangle-post)))))

(defn show [_id]
  (ring/response
    (try-parse-int [id _id]
                   (->
                     (post/find (Integer. id))
                     (mangle-post)))))

(defn update [_id {:keys [:content], :as args}]
  (ring/response
    (try-parse-int [id _id]
                   (post/change id args)
                   (-> (post/find id)
                       (mangle-post)))))

(defroutes routes
  (POST "/posts" {post :body} (create post))
  (GET "/" [] (index))
  (GET "/posts/:id" {{id :id} :params} (show id))
  (PUT "/posts/:id" {{id :id} :params post :body} (update id post)))
