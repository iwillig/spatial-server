(ns dev
  (:require
   [spatial-server.routes :as routes]
   [spatial-server.database :as database]
   [spatial-server.components :as ss.comp]
   [integrant.core :as ig]
   [integrant.repl.state :as state]
   [integrant.repl :as ig.repl :refer [go]]
   [clj-commons.pretty.repl :as pretty-repl]
   [next.jdbc :as jdbc]))

(def sys state/system)

(defn db
  []
  (::ss.comp/database state/system))

(defn run-sql
  [db sql-string]
  (jdbc/execute! db sql-string))

(pretty-repl/install-pretty-exceptions)

(integrant.repl/set-prep!
 #(ig/expand ss.comp/config))

(comment

  (database/execute! (db) '{select :* from (pg_class)}))
