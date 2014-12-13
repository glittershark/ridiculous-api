(ns ridiculous-api.lib.mangler
  (:require [clojure.core.match :refer [match]]
            [clojure.string :refer [split join]]))

(defn mangle-word
  "Mangle a single word in an amusing fashion"
  [strn]
  (match (into [] strn)
    [a]        (str a)
    [a b]      (str a b)
    [a & rest] (str a (join (shuffle (butlast rest))) (last rest))))

(defn mangle
  "Mangle the words of `strn` in an amusing fashion"
  [strn] (join " " (map mangle-word (split strn #"\s+"))))
