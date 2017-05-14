(ns components.player_card.card
  (:require [clojure.string :as str]))

(defn getHeightFt [player]
  (first (str/split (player "height") "-"))
  )
(defn getHeightIn [player]
  (last (str/split (player "height") "-"))
  )

(defn card [player]
  [:div.col-sm-2.col-md-2.col-lg-2 {:key (player "playerId")}
   [:div.playerCard
    [:span.playerName (player "player")]
    [:span.playerNumber \# (player "num")]
    [:span.playerPosition (player "position")]
    [:span.height
     [:span.feets (getHeightFt player)] " ft " [:span.inches (getHeightIn player)] " in"
     ]
    [:span.weight
     [:span.lbs (player "weight")] " lbs "
     ]
    [:div.photo
     [:img {:src (str "//ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/" (player "playerId") ".png")}]
     ]
    ]
   ]
  )
