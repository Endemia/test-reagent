(ns test-reagent.core
    (:require [reagent.core :as reagent]
              [components.home.home :as home]
              [components.navbar :as navbar]
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

(defn app []
  (load-conferences state)
  [:div [navbar/navbar] [home/team-list conferences]]
)

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [app] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
