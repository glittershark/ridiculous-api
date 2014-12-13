(ns ridiculous-api.web-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [ridiculous-api.web :refer :all]
            [ridiculous-api.models.post :as post]))

(defmacro test-request [method path]
  `(app (mock/request ~method ~path)))

(deftest test-app
  (testing "GET /"
    (post/create {:content "Test"})
    (let [response (test-request :get "/")]
      (is (= (:status response) 200))
      (is (= (:body response) [{:content "Test"}])))))

  (testing "not-found route"
    (let [response (test-request :get "/invalid")]
      (is (= (:status response) 404))))
