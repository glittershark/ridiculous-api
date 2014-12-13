(ns ridiculous-api.lib.core)

(defmacro try-parse-int [[bound id] & body]
  `(try
     (let [~bound (Integer. ~id)]
       ~@body)
     (catch NumberFormatException e#
       {:error (str "Invalid format for integer: " ~id)})))
