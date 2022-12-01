(require '[clojure.string :as str])

(defn get-values [input]
  (->> (slurp input)
       str/split-lines
       (map parse-long)
       (partition-by (complement nil?))
       (filter #(not (nil? (first %))))))

(defn max-calories [input]
  (->> (get-values input)
       (mapv #(reduce + 0 %))
       (sort)
       (reverse)))

(def test-data (max-calories "dag_01.test.input"))

(def first-part (first (max-calories "dag_01.input")))
(def second-part (apply + 0 (take 3 (max-calories "dag_01.input"))))
