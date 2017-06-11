(ns components.teams.stats.teamLocationStats
  (:require [components.teams.stats.commonStats :as commonStats]
            [state.state :as state :refer [splits]]))

(defn table [split]
  [:div.row.teamSplits
   [:div.groupSet
    [commonStats/tableHeader (@splits "locationTeamDashboard")]
    (when-let [groupSet (@splits "locationTeamDashboard")]
      [commonStats/tableStats groupSet]
      )
    ]
   ]
  )