(ns components.teams.teams
  (:require [components.teams.teamsList :as teamsList]
            [components.teams.teamPage :as teamPage]
            ))

(defn page []
  [:div
   (teamsList/team-list)
   (teamPage/team-page)
   ]
  )