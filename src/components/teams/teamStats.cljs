(ns components.teams.teamStats
  (:require [components.teams.stats.teamOverallStats :as teamOverallStats]
            [components.teams.stats.teamMonthStats :as teamMonthStats]
            [components.teams.stats.teamLocationStats :as teamLocationStats]
            [state.state :as state]))

(defn team-stats [team]
  (state/loadSplits team)
  [:div
   [teamOverallStats/table]
   [teamMonthStats/table]
   [teamLocationStats/table]
   ]
  )