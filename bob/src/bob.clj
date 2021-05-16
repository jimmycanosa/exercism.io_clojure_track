(ns bob)

(defn remove-space [s]
  (apply str (remove #{\space} s)))

(defn question? [s]
  (= \? (last (remove-space s))))

(defn yell? [s]
  (if (re-seq #"[A-Z]" s)
    (= (remove-space s) (.toUpperCase (remove-space s)))
    false))

(defn nothing? [s]
  (if (= "" (remove-space s))
    true
    (if (.contains s "\t")
      true
      false)))

(defn response-for [s]
  (cond
    (nothing? s) "Fine. Be that way!"
    (and (yell? s) (question? s)) "Calm down, I know what I'm doing!"
    (question? s) "Sure."
    (yell? s) "Whoa, chill out!"
    :else "Whatever."))
