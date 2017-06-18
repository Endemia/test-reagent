(ns main.core
  (:require [reagent.core :as reagent]
            [reagent.session :as session]
            [secretary.core :as secretary :include-macros true]
            [accountant.core :as accountant]
            [components.home.home :as home]
            [components.teams.teams :as teams]
            [components.players.players :as players]
            [components.navbar :as navbar]
            [restApi.api :as restApi]
            [state.state :as state :refer [conferences teamsPage]]))

(defn home []
  [home/team-list]
  )

(defn teams []
  [teams/page]
  )

(defn players []
  [players/page]
  )

(defn load-conferences []
  (restApi/get-conferences (fn [newConferences] (reset! conferences newConferences)))
  )

(defn current-page []
  [:div [navbar/navbar] [:div.mainContent [(session/get :current-page) ]] ]
  )

(secretary/defroute "/" []
                    (session/put! :current-page home)
                    )

(secretary/defroute "/teams" []
                    (swap! teamsPage assoc "subView" "roster")
                    (session/put! :current-page teams)
                    )

(secretary/defroute "/teams/stats" []
                    (swap! teamsPage assoc "subView" "stats")
                    (session/put! :current-page teams)
                    )

(secretary/defroute "/players" []
                    (session/put! :current-page players)
                    )

;; -------------------------
;; Initialize app
(defn mount-root []
  (load-conferences) ;; prechargement des conferences + teams + stats
  (reagent/render [current-page] (.getElementById js/document "app"))
  )

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root)
  )
