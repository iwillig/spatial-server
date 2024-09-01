(ns spatial-server.rest-resources
  (:require
   [rum.core :as rum]
   [liberator.core :as liberator]))

(def available-media-type
  ["text/html"])

(def index-resource
  (liberator/resource
   :available-media-types available-media-type
   :handle-ok (fn [_request]
                (rum/render-static-markup
                 [:div "hello"]))))

(def collections-resource
  (liberator/resource

   :available-media-types available-media-type

   :allowed-methods [:post :get]

   :handle-created (fn [_ctx]
                     "created-collections")

   :post-redirect? true

   :location (fn [_ctx]
               "")

   :handle-ok (fn [_ctx]
                "list-collections")))

(def collection-items
  (liberator/resource

   :available-media-types available-media-type

   :allowed-methods [:post :get]

   :handle-created (fn [_ctx]
                     "created-item")

   :post-redirect? true

   :location (fn [_ctx]
               "")

   :handle-ok (fn [_ctx]
                "collection-items")))

(def collection-item
  (liberator/resource

   :available-media-types available-media-type

   :respond-with-entity? true
   :can-put-to-missing? false

   :allowed-methods [:get :patch :delete]

   :patch-content-types available-media-type

   :malformed? (fn [_ctx]
                 false)

   :exists? (fn [ctx]
              [true ctx])

   :handle-not-found (fn [_] "not-found")

   :patch! (fn [ctx]
             ctx)

   :handle-ok "item"))
