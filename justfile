
lint:
    clojure -M:lint --lint src test dev

depot:
    clojure -M:tests:lint:depot
