(ns six-degs-of-bacon.main
  (:gen-class)
  (:require [Moov.core :as TMDB])
)


(defn remove-indexed
    "
    Removes an element in a vector
    at the specified index.
    @v: The vector to remove from
    @n: The index of the element to remove
    "
    [v n]
    (into (subvec v 0 n) (subvec v (inc n))))


(defn bfs
    "
    Searches for a target in a graph (represented
    by an adjacency map) using a breadth-first-search.
    @g: The graph (represented by an adjacency map) to search
    @start: The start node to begin searching from
    @target: The target node to search for
    "
    [g start target]

    ;TODO: Use a more Clojurey approach to this implementation
    ;      (feels more imperative than functional)

    (let [my-q (atom '()) ;Use list because pop() occurs in front
          visited (atom #{})
          accesses (atom {})
          path (atom [])]
         (reset! my-q (conj @my-q start))
         ;conj to my-q because initializing my-q
         ;with start only puts the SYMBOL start, NOT
         ;its value!
         (while (not-empty @my-q)
            (let [cur-node (atom (nth @my-q 0))]
                (reset! my-q (pop @my-q))
                (reset! visited (conj @visited @cur-node))
                (if (= (str @cur-node) target)
                    (do
                        (let [p-node (atom @cur-node)]        
                            (reset! path (conj @path @p-node))
                            (while (not (= @p-node start))
                                (do
                                    (reset! path (conj @path (@accesses @p-node)))
                                    (reset! p-node (@accesses @p-node))
                                )
                            )
                            ;Empty out my-q in order to prevent
                            ;outermost while loop from executing
                            ;again (a break would be good, but
                            ;Clojure doesn't give you a break lol)
                            (reset! my-q (empty @my-q))
                            (reset! path (reverse @path))
                        )
                    )
                    ;Else
                    (do
                        (doseq [child (g @cur-node)]
                        ;for is "lazy" and won't run so use doseq instead.
                            (do
                                (reset! accesses (assoc @accesses child @cur-node))
                                (reset! my-q (conj @my-q child))
                            )
                        )
                    )
                )
            )
         )
        (let [res @path] res)
        ;In Clojure, the return value of a function
        ;is the last form evaluated inside the function.
        ;Make this line the function's last form evaluated
        ;by writing it here.
    )
)


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

  (def res (bfs { "a" ["b" "c"] "b" [] "c" ["d"] "d" []} "a" "d"))
  (println res)

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
