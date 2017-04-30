(ns components.navbar)

(defn home []
  [:li
   [:a {:href "#home"}
    "Home"
    ]
   ]
  )

(defn action1 []
  [:li
   [:a {:href "#action1"} "Action1"]
   ]
  )

(defn action2 []
  [:li
   [:a {:href "#action2"} "Action2"]
   ]
  )

(defn actions []
  [:li.dropdown
   [:a.dropdown-toggle {:href "#", :data-toggle "dropdown"}
    "Actions" [:span.caret]
    ]
   [:ul.dropdown-menu
    [action1]
    [action2]
    ]
   ]
  )

(defn navbar []
  [:nav.navbar.navbar-inverse
   [:div.container-fluid
    [:div#bs-example-navbar-collapse-1.collapse.navbar-collapse
     [:ul.nav.navbar-nav
      [home]
      [actions]
      ]
     ]
    ]
   ]
  )



