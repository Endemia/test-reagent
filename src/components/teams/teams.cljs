(ns components.teams.teams
  (:require [components.teams.teamHeader :as teamHeader]
            [components.teams.teamsList :as teamsList]
            [components.teams.teamRoster :as teamRoster]
            [components.teams.teamStats :as teamStats]
            [state.state :as state :refer [conferences teamsPage]]
            ))

(defn getSelectedTeam []
  (->> @conferences
       (map #(%1 "teams"))
       (flatten)
       (filter (fn [team]
                 (= (team "abbreviation") (@teamsPage "selectedTeam"))
                 )
               )
       (first)
       (doall)
       )
  )

(defn page []
  [:div
   (teamsList/team-list)
   (let [selectedTeam (getSelectedTeam)]
     [:div.teamPage.scrollbar
      (teamHeader/team-header selectedTeam)
      (case (@teamsPage "subView")
        "roster" (teamRoster/team-roster selectedTeam)
        (teamStats/team-stats selectedTeam)
        )
      ]
     )
   ]
  )
