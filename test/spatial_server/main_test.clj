(ns spatial-server.main-test
  (:require
   [matcher-combinators.test]
   ;; [matcher-combinators.matchers :as m]
   [clojure.test :as t :refer [deftest is testing]]))

(deftest  test-basic-test
  (testing "Context of the test assertions"
    (is (match? nil {}))))
