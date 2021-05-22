(ns beer-song
  (:require [clojure.string :refer [capitalize join]]))

(defn bottles [n]
  (case n
    0 "no more bottles"
    1 "1 bottle"
    (str n " bottles")))

(defn sub-verse [n]
  (if (> n 0)
    (format "Take %s down and pass it around" (if (= 1 n) "it" "one"))
    "Go to the store and buy some more"))

(defn verse
  "Returns the nth verse of the song."
  [num]
  (format "%s of beer on the wall, %s of beer.\n%s, %s of beer on the wall.\n"
          (capitalize (bottles num)) (bottles num)
          (sub-verse num) (bottles (if (zero? num) 99 (dec num)))))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start] (sing start 0))
  ([start end] (join "\n" (map verse (range start (dec end) -1)))))
