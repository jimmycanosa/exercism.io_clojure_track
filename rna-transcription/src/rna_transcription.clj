(ns rna-transcription)

(defn to-rna [dna]
  (apply str (map #(if (#{\G \C \T \A} %)
                     ({\G "C" \C "G" \T "A" \A "U"} %)
                     (throw (AssertionError.)))
                  dna)))
