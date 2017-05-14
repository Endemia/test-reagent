(ns state.state
  (:require [reagent.core :as reagent])
  )

(defonce appState (reagent/atom
                    {:conferences {},
                     :teamsPage {
                                 "selectedTeam" "ATL"
                                 "roster" {}
                                 },
                     :homeSort    "ConfRank"}
                    ))

(defonce conferences (reagent/cursor appState [:conferences]))
(defonce homeSort (reagent/cursor appState [:homeSort]))
(defonce teamsPage (reagent/cursor appState [:teamsPage]))