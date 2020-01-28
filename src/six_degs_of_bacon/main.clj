(ns six-degs-of-bacon.main
  (:gen-class)
  (:require [Moov.core :as TMDB])
)

(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  ;"Verify if there's a request limit
  (comment dotimes [i 10]
      (comment TMDB/personSearch "Arnold" comment)
   comment)

  (do
      (print "Actor: ")
      (flush)
      (def actor (read-line))
  )
  (println actor)
 
  ;TODO: Provide some validation?
  ;TODO: Allow users to find path between any two actors
  ;      (not just to Kevin Bacon)
  ;CONSIDER: Somehow make the solution to the 6 Degrees
  ;          of Kevin Bacon problem reusable for other
  ;          similar problems.
  ;TARGET OUTPUT:
  ;Actor's Bacon number and their path towards Bacon

  (try
     ;(println (TMDB/personSearch "Arnold"))
     (catch Exception e (println e)))
)
