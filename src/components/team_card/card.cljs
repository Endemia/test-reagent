(ns components.team_card.card)

(defn getConfRank [team]
  (get (first (get-in team ["stats" "teamInfoCommon"])) "confRank")
  )

(defn getWins [team]
  (get (first (get-in team ["stats" "teamInfoCommon"])) "w")
  )

(defn getLosses [team]
  (get (first (get-in team ["stats" "teamInfoCommon"])) "l")
  )

(defn card [team]
  [:div.col-sm-6.col-md-6.col-lg-4 {:key (get team "abbreviation")}
   [:div.teamCard
    [:div.teamCardImg [:img {:src (str "img/" (get team "abbreviation") ".svg")}]]
    [:span.teamCardTitle (get team "teamName")]
    [:span.teamCardIndex (getConfRank team)]
    [:div.teamCardWL (getWins team) " W / " (getLosses team) " L"]
    ]
   ]
  )
