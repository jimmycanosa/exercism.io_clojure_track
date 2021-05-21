(ns isbn-verifier)

(defn isbn? [isbn]
  (if (= (count (re-seq #"\d|[X]" isbn)) (count (re-seq #"\d|[X]$" isbn)) 10)
    (let [isbn (into [] (re-seq #"\d|[X]$" isbn))]
      (if (zero? (mod (->> (if (= "X" (last isbn)) (assoc isbn 9 "10") isbn)
                           (map read-string)
                           (map * (range 10 0 -1))
                           (reduce +)) 11)) true false))
    false))
