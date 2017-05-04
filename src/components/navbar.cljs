(ns components.navbar
  (:require [state.state :as state :refer [conferences]]))



(defn teams []

  [:li
   [:a {:href "/teams"}
    "Teams"
    ]
   ]
  )

(defn navbar []
  [:nav.navbar.navbar-nba
   [:div.container-fluid
    [:div.navbar-header [:a.navbar-nba-brand {:href "/"}]]
    [:div#bs-example-navbar-collapse-1.collapse.navbar-collapse
     [:ul.nav.navbar-nav
      [teams]
      ]
     ]
    ]
   ]
  )



