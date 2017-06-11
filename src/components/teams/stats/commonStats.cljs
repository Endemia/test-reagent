(ns components.teams.stats.commonStats
  (:require [reagent.core :as reagent]))

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

(def hoverStat (reagent/atom nil))
(def sortStat (reagent/atom nil))


(defn tableHeader [groupSet]
  (if groupSet
    (let [groupSet (get (first groupSet) "groupSet")]
      [:div.groupSetHeader
       [:div.groupSetItem.split groupSet]
       (doall
         (map (fn [stat] [:div.groupSetItem
                          {:key           (str groupSet (:label stat))
                           :on-mouse-over (fn [e] (reset! hoverStat (str groupSet "/" (:stat stat))))
                           :on-mouse-out  (fn [e] (reset! hoverStat nil))
                           :class         (if (= (str groupSet "/" (:stat stat)) @hoverStat) "active")
                           :on-click      (fn [e] (reset! sortStat (:stat stat)))
                           }
                          (:label stat)])
              stats
              )
         )
       ]
      )
    [:div.groupSetHeader.loading
     [:div.groupSetItem.split "Loading " [:img {:src (str "/img/loading-bars.svg")}]]
     ]
    )
  )

(defn oneStat [groupValue stat groupSet maxByStat]
  [:div.groupSetItem
   {:on-mouse-over (fn [e] (reset! hoverStat (str (get groupSet "groupSet") "/" (:stat stat))))
    :on-mouse-out  (fn [e] (reset! hoverStat nil))
    :class         (str (if (= (str (get groupSet "groupSet") "/" (:stat stat)) @hoverStat) "active ")
                        (if (= (get groupSet (:stat stat)) (get maxByStat (:stat stat))) "max "))
    }
   (get groupSet (:stat stat))]
  )

(defn lineStats [groupSet maxByStat]
  (let [groupValue (get groupSet "groupValue")]
    [:div.groupSetSplit
     [:div.groupSetItem.split groupValue]
     (doall
       (map (fn [stat] ^{:key (str groupValue (:stat stat))} [oneStat groupValue stat groupSet maxByStat])
            stats
            )
       )
     ]
    )
  )

(defn sortByStat [groupSet]
  (if @sortStat
    (sort (fn [x y] (compare (get y @sortStat) (get x @sortStat))) groupSet)
    groupSet
    )
  )

(defn tutu [groupSet stat]
  {(:stat stat) (get (apply max-key (fn [a] (get a (:stat stat))) groupSet) (:stat stat))
   }
  )

(defn getMaxByStat [groupSet]
  (doall
    (into {} (map (fn [stat] (tutu groupSet stat)) stats))
    )
  )

(defn tableStats [groupSet]
  (let [sortedGroupSet (sortByStat groupSet) maxByStat (getMaxByStat groupSet)]
    [:div
     (->> sortedGroupSet
          (map (fn [groupSet] ^{:key (str (get groupSet "groupValue") "split")} [lineStats groupSet maxByStat]))
          (doall)
          )
     ]
    )
  )