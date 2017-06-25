(ns dbooker.core
  (:require [clojure.pprint :as pp]
            [clojure.math.combinatorics :as combo]))

(defn events-overlap? [pair]
  (let [[start1 end1] (first pair)
        [start2 end2] (second pair)]
    (assert (and (<= start1 end1) (<= start2 end2))
            "Start of the event shoul be earlier than the end!")
    (if (<= start1 start2)
      (> end1 start2)
      (> end2 start1))))


;; VERSION 1: without using external libraries

(defn all-pairs
  "Returns all unique combinations for pairs of the list elements without external libraries"
  [lst]
  (let [tails (take-while next (iterate rest lst))]
    (mapcat (fn [[f & rs]] (map #(vector f %) rs)) tails)))

(defn overlapping-1
  "Returns the sequence of all pairs of overlapping events"
  [lst]
  (filter events-overlap? (all-pairs lst)))


;; VERSION 2: using math.combinatorics library

(defn all-pairs-combo
  "Returns all unique combitations for pairs of the list elements using math.combinatorics"
  [lst]
  (map vec (combo/combinations lst 2)))

(defn overlapping-2
  "Returns the sequence of all pairs of overlapping events"
  [lst]
  (filter events-overlap? (all-pairs-combo lst)))


;; generating the calendar sequence (30 events, each sorted for start time < end time)
(def calendar (map sort (partition 2 (take 30 (repeatedly #(rand-int 12)))))) 

(pp/pprint (set (overlapping-1 calendar)))
(pp/pprint (set (overlapping-2 calendar)))

(= (set (overlapping-1 calendar)) (set (overlapping-2 calendar)))
