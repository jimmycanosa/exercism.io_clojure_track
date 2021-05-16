(ns word-count)

(defn word-count [s]
  (frequencies (map #(.toLowerCase %) (re-seq #"\w+" s)))
)
