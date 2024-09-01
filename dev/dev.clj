(ns dev
  (:require
   [spatial-server.schema-inspect :as inspect]
   [spatial-server.routes :as routes]
   [spatial-server.database :as database]
   [spatial-server.components :as components]
   [spatial-server.schema-inspect :as schema-inspect]
   [integrant.core :as ig]
   [integrant.repl.state :as state]
   [integrant.repl :as ig.repl :refer [go]]
   [clj-commons.pretty.repl :as pretty-repl]
   [next.jdbc :as jdbc]))

(pretty-repl/install-pretty-exceptions)

(integrant.repl/set-prep!
 #(ig/expand components/config))

(comment

  (schema-inspect/pg-schema-inspect
   (components/db state/system)))
