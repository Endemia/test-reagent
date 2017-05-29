(ns config.config)

(defonce conf {:apiActive :local
               :apiUrlPrefixes {
                                :local  "http://localhost:3000"
                                :heroku "https://mynba.herokuapp.com"
                                }
               }
         )