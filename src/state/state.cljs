(ns state.state
  (:require [reagent.core :as reagent]
            [restApi.api :as restApi])
  )

(defonce appState (reagent/atom
                    {
                     :conferences {}
                     :homeSort    "ConfRank"
                     :teamsPage   {
                                   "subView"      "roster"
                                   "selectedTeam" "ATL"
                                   "roster"       {}
                                   }
                     :splits      {}
                     :last-game   {}
                     :players     {}
                     :playersPage {
                                   :selectedPlayer 203518
                                   :playerInfo     nil
                                   }
                     }
                    ))

(defonce conferences (reagent/cursor appState [:conferences]))
(defonce homeSort (reagent/cursor appState [:homeSort]))
(defonce teamsPage (reagent/cursor appState [:teamsPage]))
(defonce splits (reagent/cursor appState [:splits]))
(defonce lastGame (reagent/cursor appState [:last-game]))
(defonce players (reagent/cursor appState [:players]))
(defonce playersPage (reagent/cursor appState [:playersPage]))

(defn loadAllPlayers []
  (restApi/get-all-players (fn [players] (swap! appState assoc :players players)))
  )

(defn loadSplits [team]
  (swap! appState assoc :splits {})
  (restApi/get-team-splits (team "teamId")
                           (fn [splits] (swap! appState assoc :splits splits)))
  )

(defn loadLastGame [team]
  (swap! appState assoc :last-game {})
  (restApi/get-team-last-game (team "teamId")
                              (fn [lastGame] (swap! appState assoc :last-game lastGame)))
  )

(defn loadRoster [team]
  (restApi/get-team-roster (team "teamId") (fn [roster] (swap! teamsPage assoc "roster" roster)))
  )

(defn loadPlayer [playerId]
  (restApi/get-player playerId
                      (fn [playerInfo] (swap! playersPage assoc :playerInfo playerInfo)))
  )