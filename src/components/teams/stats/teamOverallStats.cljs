(ns components.teams.stats.teamOverallStats
  (:require [components.teams.stats.commonStats :as commonStats]
            [state.state :as state :refer [splits]]))

(defn table [split]
  [:div.row.teamSplits
   [:div.groupSet
    [commonStats/tableHeader (@splits "overallTeamDashboard")]
    (when-let [groupSet (@splits "overallTeamDashboard")]
      [commonStats/tableStats groupSet]
      )
    ]
   ]
  )