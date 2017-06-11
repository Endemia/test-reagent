(ns state.state
  (:require [reagent.core :as reagent]
            [restApi.api :as restApi])
  )

(defonce appState (reagent/atom
                    {
                     :conferences {}
                     :teamsPage   {
                                   "subView"      "roster"
                                   "selectedTeam" "ATL"
                                   "roster"       {}
                                   }
                     :splits      {}
                     :homeSort    "ConfRank"
                     }
                    ))

(defonce conferences (reagent/cursor appState [:conferences]))
(defonce homeSort (reagent/cursor appState [:homeSort]))
(defonce teamsPage (reagent/cursor appState [:teamsPage]))
(defonce splits (reagent/cursor appState [:splits]))

(defn loadSplits [team]
  (swap! appState assoc :splits {})
  (restApi/get-team-splits (team "teamId")
                           (fn [splits] (swap! appState assoc :splits splits)))
  )