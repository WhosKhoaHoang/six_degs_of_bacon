(ns six-degs-of-bacon.main
  (:gen-class)
  (:require [Moov.core :as TMDB])
  (:require [cheshire.core :as json])
)


(defn remove-indexed
    "
    Removes an element in a vector
    at the specified index.
    @v: The vector to remove from
    @n: The index of the element to remove
    "
    [v n]
    (into (subvec v 0 n) (subvec v (inc n)))
)


;NOTE: concat is being used to put characters at the
;      end of my-q. Eventually consider a more general approach?
(defn concat-children
    [my-q g visited]
    (concat (rest my-q) (for [child (get g (str (first my-q)))
                         :when (not (contains? visited child))] child))
)
(defn update-accesses
    [my-q g accesses]
    (into accesses (for [e (get g (str (first my-q)))] [e (str (first my-q))]))
)
(defn make-path
    [accesses start path]
    (if (= (last path) start)
        (let [res (reverse path)] res)
        (recur accesses start (conj path (get accesses (str (last path)))))
    )
)
(defn base-bfs
    [g start targ]
    """
    Searches for a target node in a graph (represented by an
    adjacency map) using a breadth-first search. Use this im-
    plementation as a baseline reference for any other BFS
    implementations.
    @g: The graph (represented by an adjacency map) to search
    @start: The start node to begin searching from
    @target: The target node to search for
    type g: map
    type start: str
    type target: str
    return: The path taken to reach the target node
    rtype: vector
    """
    (loop [my-q (concat () start)
           visited #{}
           accesses {}
           cnt 0]
        (if (= (str (first my-q)) targ)
            ;Note how targ is NOT added to visited.
            ;This may not actually be a problem.
            (make-path accesses start [targ])
            (recur (concat-children my-q g visited)
                   (conj visited (first my-q))
                   (update-accesses my-q g accesses)
                   (inc cnt))
        )
    )
)


(defn -main
    "I don't do a whole lot ... yet."
    [& args]

    (do
        (print "Actor: ")
        (flush) ;"Flush" the "Actor" string to stdout
        (def actor (read-line))
    )
    (println (str "The name of the actor is " actor))

    (comment
    (let [bacon-films (TMDB/personSearch "kevin-bacon")]
        ;FIRST STEPS:
        ;. Get all of Bacon's films and all of actor's films.
        ;. Represent each list of films as a vector and bind those
        ;  vectors to a variable
        ;. When any of the films from actor or any of the films from
        ;  actor's co-stars is also a film of Bacon's, then stop.
        (println
          (count (get (json/decode bacon-films) "results"))
        )
        (println (get (json/decode bacon-films) "results"))
        ;(println (get (get (json/decode bacon-films) "results") "known-for"))
    )
    comment)

    (def res (base-bfs { "a" ["b" "c"] "b" [] "c" ["d"] "d" []} "a" "d"))
    ;(def res (base-bfs { "a" ["b" "c"] "b" ["d"] "c" ["b"] "d" ["e"] "e" []} "a" "e"))
    ;b has two parent nodes here^
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
