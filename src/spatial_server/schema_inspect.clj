(ns spatial-server.schema-inspect
  (:require
   [spatial-server.database :as database]
   [honey.sql :as sql]))

(defn make-pg-schema
  "Given a Raw SQL results record,
   Return a normalized schema object"
  [record]
  {::schema          (:pg_namespace/schema record)
   ::table-name      (:pg_class/table record)
   ::geometry-column (:pg_attribute/geometry_column record)
   ::srid            (:srid record)
   ::geometry_type   (:geometry_type record)})

(def

  ^{:doc "SQL Query to inspect the pg-class table. Keep in sync
  with make-pg-schema"}

  pg-class-select-sql
  '{select
    ((nspname schema)
     (relname table)
     (reltuples tuple-count)
     (relpages  table-size)
     (attname geometry_column)
     ((postgis_typmod_srid atttypmod) srid)
     ((postgis_typmod_type atttypmod)  geometry_type))

    from
    ((pg_class c))
    join
    ((pg_namespace n) (= c.relnamespace n.oid)
                      (pg_attribute a) (= a.attrelid c.oid)
                      (pg_type t) (= a.atttypid t.oid))

    where
    (and (in relkind ["r" "v" "m"])
         (= typname "geometry"))})

(defn pg-schema-inspect
  "Given a DB value
   Returns the result of running the schema query"
  [db]
  (database/execute! db pg-class-select-sql)
  #_(mapv make-pg-schema))
