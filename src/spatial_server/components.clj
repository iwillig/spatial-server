(ns spatial-server.components
  (:require
   [spatial-server.routes :as ss.routes]
   [next.jdbc :as jdbc]
   [ring.adapter.jetty :as jetty]
   [integrant.core :as ig]))

(def
  ^{:component-key true}
  http-server ::http-server)

(def
  ^{:component-key true}
  database    ::database)

(def config
  {http-server
   {:port  3000
    :join? false}

   database
   {:user   "postgres"
    :dbname "spatial_server"
    :dbtype "postgresql"}})

(defmethod ig/init-key database
  [_ {:as config}]
  (let [ds (jdbc/get-datasource config)]
    ds))

(defn db
  "Given a system value
   Returns a datasource value"
  [system]
  (database system))

(defmethod ig/init-key http-server
  [_ {:as config}]
  (jetty/run-jetty
   ss.routes/app
   config))

(defmethod ig/halt-key! http-server
  [_ server]
  (when server
    (.stop server)))
