(ns reverse-string
  (:require [clojure.string :as s :only [reverse]]))

(defn reverse-string [s]
  (s/reverse s)
)
