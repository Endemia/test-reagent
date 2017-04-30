(ns test-reagent.core
    (:require [reagent.core :as reagent]
              [components.first_component :as firstCompo]
              [components.navbar :as navbar]
              [components.team_card.card :as team_card]
              [ajax.core :refer [GET POST]]))



;; -------------------------
;; Views

(defonce state (reagent/atom
                       {:conferences {}}
))
(def conferences (reagent/cursor state [:conferences]))

(defn load-conferences [state]
  (GET "http://localhost:3000/conferences" {:handler (fn [conferences] (swap! state assoc :conferences conferences))
                                            :response-format :json})
)

(defn team-list []
  (load-conferences state)

  [:div
    (for [conf @conferences]
      (for [team (get conf "teams")]
      [:div {:key (get team "abbreviation")} (get team "abbreviation")]
      ;  (for [team conf.teams]
      ;    [:p "aa"]
      )
    )
  ]

)

(defn home-page []
  ;[:div {:class "row"}
  ; [:div {:class "col-md-12"}
  ;  [:div [firstCompo/title] [:h2 "Welcome to Reagent "]]
  ;  ]
  ; ]
  [:div [navbar/navbar] [team-list]]
)

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
