(ns say)

(def say (zipmap `(~@(range 21)
                   30
                   40
                   50
                   60
                   70
                   80
                   90)
                 ["zero"
                  "one"
                  "two"
                  "three"
                  "four"
                  "five"
                  "six"
                  "seven"
                  "eight"
                  "nine"
                  "ten"
                  "eleven"
                  "twelve"
                  "thirteen"
                  "fourteen"
                  "fifteen"
                  "sixteen"
                  "seventeen"
                  "eighteen"
                  "nineteen"
                  "twenty"
                  "thirty"
                  "forty"
                  "fifty"
                  "sixty"
                  "seventy"
                  "eighty"
                  "ninety"]))

(defn hundred [n]
  (let [hundreds (quot n 100)
        tens (- (mod n 100) (mod n 10))
        ones (mod n 10)]
    (str (if (pos? hundreds) (str (say hundreds) " hundred"))
         (if (and (pos? hundreds) (or (pos? tens) (pos? ones))) " ")
         (if (> (mod n 100) 20)
           (str (if (pos? tens) (str (say tens)))
                (if (and (pos? tens) (pos? ones)) "-")
                (if (pos? ones) (say ones)))
           (if (pos? (mod n 100)) (say (mod n 100)))))))

(defn thousand [n]
  (loop [a n
         b []]
    (if (zero? a)
      b
      (recur (quot a 1000) (conj b (mod a 1000))))))

(defn combine [t]
  (let [s (thousand t)]
    (flatten (for [n [3 2 1 0]]
               (if (pos? (get s n 0))
                 [(hundred (s n)) ([nil "thousand" "million" "billion"] n)])))))

(defn not-nil? [x]
  (not (nil? x)))

(defn number [num]
  (if (zero? num)
    (say num)
    (if (and (pos? num) (< num 1000000000000))
      (->> num
           combine
           (filter not-nil?)
           (interpose " ")
           (apply str))
      (throw (IllegalArgumentException.)))))

(comment

  (ns say
    (:require [clojure.pprint :refer [cl-format]]
              [clojure.string :as cs]))

  (defn number [n]
    (if (<= 0 n 999999999999)
      (cs/replace
       (cl-format nil "~R" n)
       "," "")
      (throw (IllegalArgumentException.))))
  
  nil)
