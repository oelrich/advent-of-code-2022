(require '[clojure.string :as str])

(defn stack-entries [text]
  (->> (partition 4 4 nil text)
       (map (fn [chars]
              (let [entry (str/replace (str/join chars) #"\[(.)\]\s*|\s+" "$1")]
                (if (empty? entry) nil (keyword entry)))))))

(defn build-stacks [input]
  (let [entries (map stack-entries (reverse input))]
    (->> (apply mapv vector entries)
         (mapv (fn [v] [(first v) (->> (rest v) (keep identity) reverse vec)]))
         (into {}))))

(defn get-message [state]
  (str/join
   (map #(->> (get state %) first name)
        (sort (keys state)))))

(defn parse-instruction [line]
  (let [matches (re-find #"move (\d+) from (\d+) to (\d+)" line)]
    {:count (parse-long (nth matches 1))
     :from (keyword (nth matches 2))
     :to (keyword (nth matches 3))}))

(defn build-instructions [input]
  (mapv parse-instruction (filter not-empty input)))

(defn move [from to state]
  (let [item (first (state from))
        new-to (cons item (state to))
        new-from (rest (state from))]
    (-> state
        (assoc from new-from)
        (assoc to new-to))))

(defn perform-a [state {:keys [count from to]}]
  (if
   (zero? count)
    state
    (perform-a (move from to state) {:count (dec count) :from from :to to})))

(def part-a
  (let
   [[state-lines instruction-lines]
    (->> (slurp "dag_05.input")
         str/split-lines
         (split-with not-empty))
    state (build-stacks state-lines)
    instructions (build-instructions instruction-lines)]
    (get-message (reduce perform-a state instructions))))

(defn perform-b [state {:keys [count from to]}]
  (let [items (take count (state from))
        new-to (concat items (state to))
        new-from (drop count (state from))]
    (-> state
        (assoc from new-from)
        (assoc to new-to))))

(def part-b
  (let
   [[state-lines instruction-lines]
    (->> (slurp "dag_05.input")
         str/split-lines
         (split-with not-empty))
    state (build-stacks state-lines)
    instructions (build-instructions instruction-lines)]
    (get-message (reduce perform-b state instructions))))