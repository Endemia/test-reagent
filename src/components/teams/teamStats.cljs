(ns components.teams.teamStats
  (:require [components.teams.stats.teamOverallStats :as teamOverallStats]
            [components.teams.stats.teamMonthStats :as teamMonthStats]
            [components.teams.stats.teamLocationStats :as teamLocationStats]
            [state.state :as state :refer [splits]]
            [reagent.core :as reagent]))

(def hoverStat (reagent/atom nil))
(defonce loading (reagent/atom "false"))

(def stats [{:stat "gp" :label "GP"}
            {:stat "min" :label "MIN"}
            {:stat "pts" :label "PTS"}
            {:stat "w" :label "W"}
            {:stat "l" :label "L"}
            {:stat "wPct" :label "WIN%"}
            {:stat "fgm" :label "FGM"}
            {:stat "fga" :label "FGA"}
            {:stat "fgPct" :label "FG%"}
            {:stat "fG3M" :label "3PM"}
            {:stat "fG3A" :label "3PA"}
            {:stat "fg3Pct" :label "3P%"}
            {:stat "ftm" :label "FTM"}
            {:stat "fta" :label "FTA"}
            {:stat "ftPct" :label "FT%"}
            {:stat "oreb" :label "OREB"}
            {:stat "dreb" :label "DREB"}
            {:stat "reb" :label "REB"}
            {:stat "ast" :label "AST"}
            {:stat "tov" :label "TOV"}
            {:stat "stl" :label "STL"}
            {:stat "blk" :label "BLK"}
            {:stat "pf" :label "PF"}
            {:stat "plusMinus" :label "+/-"}])

(defn groupSetHeader [groupSet]
  (let [groupSet (get (first groupSet) "groupSet")]
    [:div.groupSetHeader
     [:div.groupSetItem.split groupSet]
     ;(if (not @loading)
       (doall
         (map (fn [stat] [:div.groupSetItem
                          {:key           (str groupSet (:label stat))
                           :on-mouse-over (fn [e] (reset! hoverStat (str groupSet "/" (:stat stat))))
                           :on-mouse-out  (fn [e] (reset! hoverStat nil))
                           }
                          (:label stat)])
              stats
              )
         )
       ;)
     ]
    )
  )

(defn groupSetStats [groupSet]
  (.log js/console groupSet)
  (let [groupValue (get groupSet "groupValue")]
    [:div.groupSetSplit {:key (str groupValue "split")}
     [:div.groupSetItem.split {:key groupValue} groupValue]
     ;(if (not @loading)
       (doall
         (map (fn [stat] [:div.groupSetItem
                          {:key           (str groupValue (:stat stat))
                           :on-mouse-over (fn [e] (reset! hoverStat (str (get groupSet "groupSet") "/" (:stat stat))))
                           :on-mouse-out  (fn [e] (reset! hoverStat nil))
                           :class         (if (= (str (get groupSet "groupSet") "/" (:stat stat)) @hoverStat) "active")
                           }
                          (get groupSet (:stat stat))])
              stats
              )
         )
       ;)
     ]
    )
  )

(defn table [split]
  [:div.row.teamSplits
   [:div.groupSet
    [groupSetHeader (@splits split)]
    (->> (@splits split)
         (map groupSetStats)
         (doall)
         )
    ]
   ]
  )

(defn team-stats [team]
  (state/loadSplits team)
  [:div
   [teamOverallStats/table]
   ;(table "overallTeamDashboard")
   [teamMonthStats/table]
   ;(table "monthTeamDashboard")
   [teamLocationStats/table]
   ;(table "locationTeamDashboard")
   ]
  )