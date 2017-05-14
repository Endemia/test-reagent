(ns components.teams.teamsList
  (:require [state.state :as state :refer [conferences teamsPage]]))

(defn changeSelectedTeam [newSelectedTeam]
  (swap! teamsPage assoc "selectedTeam" newSelectedTeam)
  )

(defn getTeamEntry [team]
  [:li (merge
         {:on-click #(changeSelectedTeam (team "abbreviation"))
          :key      (team "abbreviation")
          }
         (if (= (@teamsPage "selectedTeam") (team "abbreviation")) {:class "active"})
         )
   [:a {:href "#"} [:img {:src (str "img/" (team "abbreviation") ".svg")}] (team "teamName")]
   ]
  )

(defn listTeamEntry []
  (->> @conferences
       (map #(%1 "teams"))
       (flatten)
       (sort (fn [x y] (compare (x "abbreviation") (y "abbreviation"))))
       (map getTeamEntry)
       (doall)
       )
  )

(defn team-list []
  [:div.teamList.scrollbar
   [:ul.nav.nav-pills.nav-stacked.nav-pills-nba
    (listTeamEntry)
    ]
   ]
  )