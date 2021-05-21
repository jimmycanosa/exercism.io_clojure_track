(ns anagram)

(defn anagrams-for [word prospect-list]
  (let [s (sort (.toLowerCase word))]
    (filter #(and (not= (.toLowerCase word) (.toLowerCase %)) 
                  (= (sort (.toLowerCase %)) s)) prospect-list)))
