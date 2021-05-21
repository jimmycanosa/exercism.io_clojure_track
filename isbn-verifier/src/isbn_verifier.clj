(ns isbn-verifier)

(defn isbn? [isbn]
  (let [isbn (->> (clojure.string/replace isbn "-" "")
                  (re-matches #"^\d{9}[\dX]$"))]
    (if (= 10 (count isbn))
      (->> isbn
           (map #(if (= \X %) 10 (Character/digit % 10)))
           (map * (range 10 0 -1))
           (reduce +)
           (#(mod % 11))
           zero?)
      false)))
