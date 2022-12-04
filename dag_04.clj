(require '[clojure.string :as str]
         '[clojure.set :as set])

(def part-a
  (->>
   (slurp "dag_04.input")
   str/split-lines
   (mapv
    (fn [line]
      (->>
       (str/split line #",")
       (mapv
        (fn [r]
          (let [[a b] (mapv parse-long (str/split r #"-"))]
            (range a (inc b)))))
       ((fn [[a b]] (or (set/subset? (set a) (set b))
                        (set/subset? (set b) (set a))))))))
   (filter identity)
   count))

(def part-a
  (->>
   (slurp "dag_04.input")
   str/split-lines
   (mapv
    (fn [line]
      (->>
       (str/split line #",")
       (mapv
        (fn [r]
          (let [[a b] (mapv parse-long (str/split r #"-"))]
            (range a (inc b)))))
       ((fn [[a b]] (not-empty (set/intersection (set a) (set b))))))))
   (filter identity)
   count))