(ns components.team_card.card)

(defn getStat [team stat]
  (-> team
      (get-in ["stats" "teamInfoCommon"])
      (first)
      (get stat)
      )
  )

(defn card [team onclick]
  [:div.col-sm-6.col-md-6.col-lg-4 {:key (team "abbreviation")}
   [:div.teamCard (if onclick {:class "clickable" :on-click #(onclick team)})
    [:div.teamCardImg
     [:img {:src (str "/img/" (team "abbreviation") ".svg")}]
     ]
    [:span.teamCardTitle (team "teamName") onclick]
    [:span.teamCardIndex (getStat team "confRank")]
    [:div.teamCardWL (getStat team "w") " W / " (getStat team "l") " L"]
    ]
   ]
  )
