
lint:
    clojure -M:lint --lint src test dev

depot:
    clojure -M:dev:tests:lint:depot

test:
    clojure -M:dev:tests
