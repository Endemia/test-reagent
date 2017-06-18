(ns components.players.playerHeader
  (:require [state.state :as state :refer [playersPage]]))

(defn getInfo [info]
  ((first ((@playersPage :playerInfo) "commonPlayerInfo")) info)
  )

(defn header []
  [:div
   [:div.playerTitle {:class (getInfo "teamAbbreviation")}
    [:div.photo
     [:img {:src (str "//ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/" (getInfo "personId") ".png")}]
     ]

    [:div.playerFullName
     [:p.firstname (getInfo "firstName")]
     [:p.lastname (getInfo "lastName")]
     ]
    ]
   ]
  )

(defn player-header []
  (state/loadPlayer (@playersPage :selectedPlayer))
  (if (@playersPage :playerInfo)
    [header]
    )
  )