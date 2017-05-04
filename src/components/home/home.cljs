(ns components.home.home
  (:require [components.team_card.card :as team_card]
            [state.state :as state :refer [conferences homeSort]]))

(defn getConfRanking [team]
  (get (first (get-in team ["stats" "teamInfoCommon"])) "confRank")
  )

(def sortings {"ConfRank"    (fn [x y] (compare (getConfRanking x) (getConfRanking y)))
               "revConfRank" (fn [x, y] (compare (getConfRanking y) (getConfRanking x)))}
  )

(defn sorted-team-list [teams homeSort]
  (sort (get sortings homeSort) teams)
  )

(defn conference-team-list [homeSort conference]
  [:div.col-md-6 {:class (str (get conference "conf") "Conf") :key (get conference "conf")}
   [:div.row
    (map team_card/card (sorted-team-list (get conference "teams") homeSort) (iterate inc 1))
    ]
   ]
  )

(defn changeSorting [homeSort newHomeSort]
  (reset! homeSort newHomeSort)
  )

(defn toggleSorting [homeSort]
  (if (re-matches #"^rev.*" @homeSort) (changeSorting homeSort (subs @homeSort 3)) (changeSorting homeSort (str "rev" @homeSort)))
  )

(defn team-list []
  [:div
   [:div.homeSortButton {:on-click #(toggleSorting homeSort)}
    "Conference Ranking"
    [:i.glyphicon {:class (if (re-matches #"^rev.*" @homeSort) "glyphicon-chevron-up" "glyphicon-chevron-down")}]
    ]
   [:div.container-fluid
    [:div.row
     (map (partial conference-team-list @homeSort) @conferences)
     ]
    ]
   ]
  )

