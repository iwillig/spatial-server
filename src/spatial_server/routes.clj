(ns spatial-server.routes
  (:require
   [reitit.ring :as ring]))

(defn routes
  []
  [["/" {:name :index
         :handler (fn [_] {:status 200 :body "okay"})}]])

(defn router
  []
  (ring/router (routes)))

(def app
  (-> (router)
      (ring/ring-handler
       (ring/create-default-handler))))
