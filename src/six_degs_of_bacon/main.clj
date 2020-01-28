(ns six-degs-of-bacon.main
  (:gen-class)
  (:require [Moov.core :as TMDB])
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  (comment "Verify if there's a request limit:" comment)
  (comment dotimes [i 10]
      (comment TMDB/personSearch "Arnold" comment)
   comment)

  (try
     (println (TMDB/personSearch "Arnold"))
     (catch Exception e (println e)))
)
