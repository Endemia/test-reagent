(ns components.home.home
  (:require [components.team_card.card :as team_card]
            [state.state :as state :refer [conferences homeSort teamsPage]]
            [accountant.core :as accountant]))

(defn getConfRanking [team]
  (-> team
      (get-in ["stats" "teamInfoCommon"])
      (first)
      (get "confRank")
      )
  )

(def sortings {"ConfRank"    (fn [x y] (compare (getConfRanking x) (getConfRanking y)))
               "revConfRank" (fn [x, y] (compare (getConfRanking y) (getConfRanking x)))}
  )

(defn sorted-team-list [teams homeSort]
  (sort (sortings homeSort) teams)
  )

(defn goToTeam [team]
  (swap! teamsPage assoc "selectedTeam" (team "abbreviation"))
  (accountant/navigate! "/teams")
  )

(defn conference-team-list [homeSort conference]
  [:div.col-md-6 {:class (str (conference "conf") "Conf") :key (conference "conf")}
   [:div.row
    (map #(team_card/card % goToTeam) (sorted-team-list (conference "teams") homeSort))
    ]
   ]
  )

(defn changeSorting [newHomeSort]
  (reset! homeSort newHomeSort)
  )

(defn toggleSorting [homeSort]
  (if (re-matches #"^rev.*" @homeSort) (changeSorting (subs @homeSort 3)) (changeSorting (str "rev" @homeSort)))
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

