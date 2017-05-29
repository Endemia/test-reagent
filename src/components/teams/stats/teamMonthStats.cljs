(ns components.teams.stats.teamMonthStats
  (:require [components.teams.stats.commonStats :as commonStats]
            [state.state :as state :refer [splits]]))

(defn table [split]
  [:div.row.teamSplits
   [:div.groupSet
    [commonStats/tableHeader (@splits "monthTeamDashboard")]
    (when-let [groupSet (@splits "monthTeamDashboard")]
      [commonStats/tableStats groupSet]
      )
    ]
   ]
  )