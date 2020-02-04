(ns six-degs-of-bacon.main
  (:gen-class)
  (:require [Moov.core :as TMDB])
)


(defn bfs
    "Searches for a target in a graph (represented
     by an adjacency map) using a breadth-first-search."
    [g start target]
    ;NOTE: There's a subtlety to Clojure's (contains?) function
    (let [my-q (atom '()) ;Use list because pop() occurs in front
          visited (atom #{})]
         (reset! my-q (conj @my-q start))
         ;conj to my-q because initializing my-q
         ;with start only puts the symbol start, NOT
         ;its value!
         (while (not-empty @my-q)
            (let [cur-node (atom (nth @my-q 0))]
                (reset! my-q (pop @my-q))
                (reset! visited (conj @visited @cur-node))

                (if (= (str @cur-node) target)
                    (do
                        (println "GOALLLLLLL")
                        (println "Done...")
                        ;path = [cur_node]
                        ;p_node = cur_node
                        ;while p_node != start:
                        ;   to_append = accesses[p_node]
                        ;   path.append(to_append)
                        ;   p_node = to_append
                        ;path.reverse()
                        ;return path
                    )
                    (do
                        (println "MORE TRAVERSAL")
                        (println "Visiting children...")
                        ;for child in g[cur_node]:
                        ;   my_q.append(child)
                    )
                )
            )
         )
    )
)


(defn remove-indexed [v n]
    (into (subvec v 0 n) (subvec v (inc n))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  ;"Verify if there's a request limit...Apparently no limit.
  (comment dotimes [i 10]
      (comment TMDB/personSearch "Arnold" comment)
   comment)

  ;do allows you to write multiple instructions within
  ;a single nested block?
  (do
      (print "Actor: ")
      (flush)
      (def actor (read-line))
  )
  (println (str "The name of the actor is " actor))
  (bfs "GRAPH" "a" "d")

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
     (catch Exception e (println e))
  )
)
