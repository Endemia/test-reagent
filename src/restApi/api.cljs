(ns restApi.api
  (:require [ajax.core :refer [GET POST]]
            [config.config :as config :refer [conf]]))

(defn getUrlPrefix []
  ((:apiActive conf) (:apiUrlPrefixes conf))
  )

(defn get-conferences [handler]
  (.log js/console ((:apiActive conf) (:apiUrlPrefixes conf)))
  (GET (str (getUrlPrefix) "/conferences") {:handler         handler
                                            :response-format :json})
  )

(defn get-team-roster [teamId handler]
  (GET (str (getUrlPrefix) "/team/" teamId "/roster") {:handler         handler
                                                       :response-format :json})
  )

(defn get-team-splits
  ([teamId handler finally]
   (GET (str (getUrlPrefix) "/team/" teamId "/splits") {:handler         handler
                                                        :response-format :json
                                                        :finally         finally}))
  ([teamId handler]
   (get-team-splits teamId handler nil)
    )
  )

(defn get-team-last-game [teamId handler]
  (GET (str (getUrlPrefix) "/team/" teamId "/lastGame") {:handler         handler
                                                         :response-format :json})
  )

(defn get-all-players [handler]
  (GET (str (getUrlPrefix) "/players") {:handler         handler
                                        :response-format :json})
  )

(defn get-player [playerId handler]
  (GET (str (getUrlPrefix) "/player/" playerId) {:handler         handler
                                                 :response-format :json})
  )