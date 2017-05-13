(ns restApi.api
  (:require [ajax.core :refer [GET POST]]))

(defn get-conferences [handler]
  (GET "http://localhost:3000/conferences" {:handler         handler
                                            :response-format :json})
  )