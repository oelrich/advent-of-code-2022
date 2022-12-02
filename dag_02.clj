(require '[clojure.string :as str]
         '[clojure.core.match :refer [match]])

(defn get-values [input]
  (->> (slurp input)
       str/split-lines
       (map (fn [line] (mapv keyword (str/split line #" "))))
       (into [])))

(defn score-a
  ":A :X Rock, :B :Y Paper, :C :Z Scissors"
  [game]
  (match game
    [:A :X] 4 ; 1 + 3
    [:A :Y] 8 ; 2 + 6
    [:A :Z] 3 ; 3 + 0
    [:B :X] 1 ; 1 + 0
    [:B :Y] 5 ; 2 + 3
    [:B :Z] 9 ; 3 + 6
    [:C :X] 7 ; 1 + 6
    [:C :Y] 2 ; 2 + 0 
    [:C :Z] 6 ; 3 + 3
    :else 0))

(def part-a
  (->> (get-values "dag_02.input")
       (map score-a)
       (apply + 0)))

(defn score-b
  ":A Rock, :B Paper, :C Scissors, :X lose, :Y draw, :Z win"
  [game]
  (match game
    [:A :X] 3 ; 3 + 0
    [:A :Y] 4 ; 1 + 3
    [:A :Z] 8 ; 2 + 6
    [:B :X] 1 ; 1 + 0
    [:B :Y] 5 ; 2 + 3
    [:B :Z] 9 ; 3 + 6
    [:C :X] 2 ; 2 + 0
    [:C :Y] 6 ; 3 + 3 
    [:C :Z] 7 ; 1 + 6
    :else 0))

(def part-b
  (->> (get-values "dag_02.input")
       (map score-b)
       (apply + 0)))

