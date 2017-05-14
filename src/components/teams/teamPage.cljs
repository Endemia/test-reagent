(ns components.teams.teamPage
  (:require [components.player_card.card :as player-card]
            [state.state :as state :refer [conferences, teamsPage]]
            [utils.numbers :as numbers]
            [restApi.api :as restApi]
            [clojure.string :as str]))

(defn getSelectedTeam []
  (->> @conferences
       (map #(%1 "teams"))
       (flatten)
       (filter (fn [team]
                 (= (team "abbreviation") (@teamsPage "selectedTeam"))
                 )
               )
       (first)
       (doall)
       )
  )

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

(defn getPlayerLastname [player]
  (last (str/split (str/replace (player "player") #"\s(I|II|III|IV|Jr.|Peace)$" "") #"\s+"))
  )

(defn getPlayerList []
  (map player-card/card (sort-by getPlayerLastname (get-in @teamsPage ["roster" "commonTeamRoster"])))
  )

(defn page [team]
  (restApi/get-team-roster (team "teamId") (fn [roster] (swap! teamsPage assoc "roster" roster)))
  [:div.teamPage.scrollbar
   [:div.teamTitle {:class (team "abbreviation")}
    [:img {:src (str "img/" (team "abbreviation") ".svg")}]

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
   [:div (getPlayerList)]
   ]
  )

(defn team-page []
  (page (getSelectedTeam))
  )