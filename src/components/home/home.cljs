(ns components.home.home
  (:require [components.team_card.card :as team_card]))

(defn team-list [conferences]

  [:div.container-fluid
   [:div.row
     (for [conf @conferences]
       [:div.col-md-6 {:class (str (get conf "conf") "Conf")}
         [:div.row
           (for [team (get conf "teams")]
             [team_card/card team]
           )
          ]
        ]
     )
    ]
  ]
)