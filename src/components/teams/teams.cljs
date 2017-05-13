(ns components.teams.teams
  (:require [state.state :as state :refer [conferences]]))


(defn getTeamEntry [team]
  [:li
   [:a {:href "/"} [:img {:src (str "img/" (get team "abbreviation") ".svg")}] (get team "teamName")]
   ]
  )

(defn teams-list []

  (map #(map getTeamEntry (get %1 "teams")) @conferences)

  )

(defn page []
  [:div.teamList.scrollbar
   [:ul.nav.nav-pills.nav-stacked
    (teams-list)
    ]
   ]
  )