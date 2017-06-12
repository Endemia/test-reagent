(ns components.teams.teamRoster
  (:require [components.player_card.card :as player-card]
            [state.state :as state :refer [teamsPage]]
            [clojure.string :as str]))

(defn getPlayerLastname [player]
  (last (str/split (str/replace (player "player") #"\s(I|II|III|IV|Jr.|Peace)$" "") #"\s+"))
  )

(defn getPlayerList []
  (map player-card/card (sort-by getPlayerLastname (get-in @teamsPage ["roster" "commonTeamRoster"])))
  )

(defn roster []
  [:div.row.teamRoster (getPlayerList)]
  )

(defn team-roster [team]
  (state/loadRoster team)
  [roster]
  )