(ns dev
  (:require
   [spatial-server.routes :as routes]
   [spatial-server.components :as components]
   [integrant.core :as ig]
   [integrant.repl :as ig.repl :refer [go]]
   [clj-commons.pretty.repl :as pretty-repl]))

(pretty-repl/install-pretty-exceptions)

(integrant.repl/set-prep!
 #(ig/expand components/config))
