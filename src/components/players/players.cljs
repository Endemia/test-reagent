(ns components.players.players
  (:require [components.players.playersList :as playersList]
            [components.players.playerHeader :as playerHeader]
            [state.state :as state :refer [playersPage]]))

(defn page []
  (state/loadAllPlayers)
  [:div
   [playersList/player-list]
   [:div.playerPage.scrollbar
    [playerHeader/player-header]
    ]
   ]
  )