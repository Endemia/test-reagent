(ns components.teams.teamHeader
  (:require [utils.numbers :as numbers]
            [state.state :as state :refer [lastGame]]))

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

(defn header [team]
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
    [:div.teamLastGame
     [:div.header
      [:span "LAST GAME"]
      ]
     [:div.game
      (if (@lastGame "LineScore")
        [:div
         [:img.visitor {:src (str "/img/" ((last (@lastGame "LineScore")) "teamAbbreviation") ".svg")}]
         [:div.score
          [:div.visitor ((last (@lastGame "LineScore")) "pts")]
          " - "
          [:div.home ((first (@lastGame "LineScore")) "pts")]
          ]
         [:div.date ((first (@lastGame "GameInfo")) "gameDate")]
         [:img.home {:src (str "/img/" ((first (@lastGame "LineScore")) "teamAbbreviation") ".svg")}]
         ]
        [:div.loading
         [:span.spacer]
         [:img {:src (str "/img/loading-bars.svg")}]
         ]
        )
      ]
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

(defn team-header [team]
  (state/loadLastGame team)
  [header team]
  )