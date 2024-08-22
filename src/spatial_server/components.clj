(ns spatial-server.components
  (:require
   [spatial-server.routes :as ss.routes]
   [ring.adapter.jetty :as jetty]
   [integrant.core :as ig]))

(def config
  {::http-handler
   {:port  3000
    :join? false}})

(defmethod ig/init-key ::http-handler
  [_ {:as config}]
  (jetty/run-jetty
   ss.routes/app
   config))

(defmethod ig/halt-key! ::http-handler
  [_ server]
  (when server
    (.stop server)))
