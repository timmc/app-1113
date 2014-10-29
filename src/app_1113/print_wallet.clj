(ns app-1113.print-wallet
  (:require [clojure.string :as str]))

(defn csv-to-map
  [csv]
  ;; assumes headers are present
  (let [lines (-> csv (str/split-lines) (rest))]
    (into {}
          (for [l lines]
            (let [[lbl-enc addr] (rest (re-find #"^\"(.*)\",\"(.+)\"" l))]
              ;; "" becomes "
              [(.replace lbl-enc "\"\"" "\"") addr])))))

(defn print-lines
  [addrs]
  (doseq [[lbl addr] (sort-by val addrs)]
    (println (format "%-34s %s" addr lbl))))

(defn -main
  "csv-path: path to CSV file from bitcoin core receive addresses export"
  [csv-path & args]
  (let [csv (slurp csv-path :encoding "UTF-8")
        addrs (csv-to-map csv)]
    (print-lines addrs)))
