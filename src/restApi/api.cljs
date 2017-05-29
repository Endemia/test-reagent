(ns restApi.api
  (:require [ajax.core :refer [GET POST]]))

(defn get-conferences [handler]
  (GET "http://localhost:3000/conferences" {:handler         handler
                                            :response-format :json})
  )

(defn get-team-roster [teamId handler]
  (GET (str "http://localhost:3000/team/" teamId "/roster") {:handler         handler
                                                             :response-format :json})
  )