(ns ^:ogc spatial-server.ogc-features-test
  "Namespace to capture ogc api complaince."
  (:require
   [matcher-combinators.test]
   [spatial-server.routes :as routes]
   ;; [matcher-combinators.matchers :as m]
   [clojure.test :as t :refer [deftest is testing]]
   [ring.mock.request :as mock]))

;; https://developer.ogc.org/api/features/index.html#tag/Capabilities/operation/getLandingPage

(defn test-request
  [& {:keys [method uri params] :or {method :get
                                     params {}}}]

  (routes/app
   (mock/request
    method uri params)))

;; https://developer.ogc.org/conformance
(deftest features-test:conformance
  (testing ""
    (is (match? nil
                (test-request {:uri "/conformance"})))))

(deftest features-test-collections
  (testing "/collections"
    (testing "http:get"
      (is (match? nil
                  (test-request {:uri "/collections"}))))))

(def default-collection-name
  "oakland_buildings")

(deftest features-test-collection

  (testing "/collections/{collectionId}"
    (testing "http:get"
      (is (match?
           nil
           (test-request {:uri
                          (format
                           "/collections/%s" default-collection-name)}))))))

;; https://developer.ogc.org/collections/{collectionId}/items/{featureId}
(deftest feature-by-id-test
  (testing "/collections/{collectionId}/items/{featureId}"

    (testing "http:get"
      (let [id (rand-int 100)]
        (is (match?
             nil
             (test-request
              {:uri
               (format "/collections/%s/items/%s" default-collection-name
                       id)})))))

    (testing "http:post"
      (let [collection-id (rand-int 100)]
        (is (match? nil
                    (test-request
                     {:uri (format "")})))))

    (testing "http:put"
      (is (match? nil [])))

    (testing "http:delete"
      (is (match? nil [])))))
