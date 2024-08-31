(ns spatial-server.routes
  (:require
   [spatial-server.rest-resources :as rest]
   [reitit.ring :as ring]))

(defn routes
  []
  [["/" {:name :index
         :handler rest/index-resource}]

   ["/collections"
    {:name :collections
     :handler rest/collections-resource}]

   ["/collections/:collection-id"
    {:name :collection
     :handler rest/index-resource}]

   ["/collections/:collection-id/items"
    {:name :collection-items
     :handler rest/index-resource}]

   ["/collections/:collectiaon-id/items/:item-id"
    {:name :collection-item
     :handler rest/index-resource}]])

(defn router
  []
  (ring/router (routes)))

(def app
  (-> (router)
      (ring/ring-handler
       (ring/create-default-handler))))
