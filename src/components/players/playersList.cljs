(ns components.players.playersList
  (:require [state.state :as state :refer [players playersPage]]))

(defn changeSelectedPlayer [newSelectedPlayer]
  (swap! playersPage assoc :selectedPlayer newSelectedPlayer)
  )

(defn getPlayerEntry [player]
  [:li (merge
         {
          :on-click #(changeSelectedPlayer (player "playerId"))
          :key      (player "playerId")
          }
         (if (= (@playersPage :selectedPlayer) (player "playerId")) {:class "active"})
         )
   [:a {:href "#"} [:img {:src (str "/img/" (player "teamAbbreviation") ".svg")}] (player "lastName") ", " (player "firstName")]
   ]
  )

(defn listPlayerEntry []
  (->> @players
       (sort (fn [x y] (compare (if (empty? (x "lastName")) (x "firstName") (x "lastName")) (if (empty? (y "lastName")) (y "firstName") (y "lastName")))))
       (map getPlayerEntry)
       (doall)
       )
  )

(defn player-list []
  [:div.playerList.scrollbar
   [:ul.nav.nav-pills.nav-stacked.nav-pills-nba
    (listPlayerEntry)
    ]
   ]
  )