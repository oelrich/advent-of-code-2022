(require '[clojure.string :as str])

(defn solver [window-size]
  (->> (slurp "dag_06.input")
       str/split-lines
       (mapv
        (fn [text]
          (->> (partition window-size 1 text)
               (#(first
                  (keep-indexed
                   (fn [idx entry]
                     (if
                      (= window-size (count (set entry)))
                       (+ window-size idx)
                       nil))
                   %))))))))

; 7 5 6 10 11
(def part-a (solver 4))
; 19 23 23 29 26
(def part-b (solver 14))
