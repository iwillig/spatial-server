(ns spatial-server.components
  (:require
   [spatial-server.routes :as ss.routes]
   [next.jdbc :as jdbc]
   [ring.adapter.jetty :as jetty]
   [integrant.core :as ig]))

(def config
  {::http-handler
   {:port  3000
    :join? false}

   ::database
   {:user   "postgres"
    :dbname "spatial_server"
    :dbtype "postgresql"}})

(defmethod ig/init-key ::database
  [_ {:as config}]
  (let [ds (jdbc/get-datasource config)]
    (println config ds)
    ds))

(defmethod ig/init-key ::http-handler
  [_ {:as config}]
  (jetty/run-jetty
   ss.routes/app
   config))

(defmethod ig/halt-key! ::http-handler
  [_ server]
  (when server
    (.stop server)))
