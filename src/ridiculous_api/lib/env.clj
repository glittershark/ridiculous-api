(ns ridiculous-api.lib.env)

(defn env
  "Get the current app runtime environment as a Keyword"
  [] (keyword (or (System/getenv "APP_ENV") "development")))

(defn development?
  "Is the current app env development?"
  [] (= env :development))

(defn test?
  "Is the current app env test?"
  [] (= env :test))

(defn production?
  "Is the current app env production?"
  [] (= env :production))
