(ns hello-www.core
  (:use [compojure.core]
	[clojure.data.json :only (read-json json-str)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
	    [monger.core :as mg]
	    [monger.collection :as mc]
	    [monger.json])
  (:import  [org.bson.types ObjectId]
	    [com.mongodb DB MongoOptions ServerAddress]))

;; localhost, default port
(mg/connect!)
(mg/set-db! (mg/get-db "cl_test"))

(defn handle-post [body]
    (mc/insert "documents" (read-json body))
    {:status 201
     :headers {"Content-Type" "application/json"}
     :body body})

(defroutes main-routes
  (GET "/" [] "<h1>Hello World Wide Web!</h1>")
  (GET "/resource/:id" [id] (json-str (mc/find-map-by-id "documents" (ObjectId. id))))
  (POST "/resource" {body :body} (handle-post (slurp body)))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (handler/site main-routes))
