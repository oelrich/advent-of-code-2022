(require '[clojure.string :as str]
         '[clojure.core.set :as set])

(defn char-val [c]
  (let [v (inc (int c))]
    (- v (if (Character/isUpperCase c)
           (- (int \A) 26)
           (int \a)))))

(def part-a
  (->> (slurp "dag_03.input")
       str/split-lines
       (mapv (fn [line] (split-at (/ (count line) 2) line)))
       (mapv (fn [[a b]] (set/intersection (set a) (set b))))
       (mapv (fn [s] (apply + 0 (mapv char-val s))))
       (apply + 0)))

(def part-b
  (->> (slurp "dag_03.input")
       str/split-lines
       (partition 3)
       (mapv (fn [[a b c]] (set/intersection (set a) (set b) (set c))))
       (mapv (fn [s] (apply + 0 (mapv char-val s))))
       (apply + 0)))
