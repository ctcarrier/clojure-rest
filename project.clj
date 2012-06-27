(defproject clojure-rest "0.1.0-SNAPSHOT"
  :description "My first compojure app"
  :dependencies [[org.clojure/clojure "1.3.0"]
		[compojure "1.1.0"]
		[com.novemberain/monger "1.0.0-rc2"]		
		[org.clojure/data.json "0.1.2"]]
  :plugins [[lein-ring "0.7.1"]]
  :ring {:handler hello-www.core/app})
