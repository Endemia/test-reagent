(ns components.team_card.card)

(defn card [team]
  [:div.col-md-3 {:key (get team "abbreviation")}
   [:div.teamCard
    [:img.teamCardImg {:src (str "img/" (get team "abbreviation") ".svg")}]
    [:span.teamCardTitle (get team "teamName")]
    ]
   ]
)
