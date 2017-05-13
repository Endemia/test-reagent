(ns state.state
  (:require [reagent.core :as reagent])
  )

(defonce appState (reagent/atom
                    {:conferences {},
                     :homeSort    "ConfRank"}
                    ))

(defonce conferences (reagent/cursor appState [:conferences]))
(defonce homeSort (reagent/cursor appState [:homeSort]))