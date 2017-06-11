(ns components.teams.teamHeader
  (:require [utils.numbers :as numbers]))

(defn getStat [team stat]
  (-> team
      (get-in ["stats" "teamSeasonRanks"])
      (first)
      (get stat)
      )
  )

(defn getInfo [team info]
  (-> team
      (get-in ["stats" "teamInfoCommon"])
      (first)
      (get info)
      )
  )

(defn getStatRank [team stat]
  (numbers/countFromNumber (getStat team stat))
  )

(defn getInfoRank [team info]
  (numbers/countFromNumber (getInfo team info))
  )

(defn team-header [team]
  [:div
    [:div.teamTitle {:class (team "abbreviation")}
     [:img {:src (str "/img/" (team "abbreviation") ".svg")}]

     [:div.teamFullName
      [:p.city (team "location")]
      [:p.team (team "simpleName")]
      ]
     [:div.teamConfRank
      [:div.header
       [:span "CONF"]
       ]
      [:div.conf (getInfo team "teamConference")]
      [:div.rank (getInfoRank team "confRank")]
      ]
     [:div.teamDivRank
      [:div.header
       [:span "DIV"]
       ]
      [:div.div (getInfo team "teamDivision")]
      [:div.rank (getInfoRank team "divRank")]
      ]
     ]
    [:div.teamStats.background {:class (team "abbreviation")}
     [:div.stat
      [:div.ppg (getStat team "ptsPg") " PPG"]
      [:div.ppgRank (getStatRank team "ptsRank")]
      ]
     [:div.stat
      [:div.apg (getStat team "astPg") " APG"]
      [:div.apgRank (getStatRank team "astRank")]
      ]
     [:div.stat
      [:div.rpg (getStat team "rebPg") " RPG"]
      [:div.rpgRank (getStatRank team "rebRank")]
      ]
     [:div.stat
      [:div.opg (getStat team "oppPtsPg") " OPPG"]
      [:div.opgRank (getStatRank team "oppPtsRank")]
      ]
     ]
   ]
  )