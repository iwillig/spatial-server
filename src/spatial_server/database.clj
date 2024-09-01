(ns spatial-server.database
  (:require
   [honey.sql :as sql]
   [next.jdbc :as jdbc]))

["SELECT
	nspname AS SCHEMA,
	relname AS TABLE,
	attname AS geometry_column,
	postgis_typmod_srid (atttypmod) AS srid,
	postgis_typmod_type (atttypmod) AS geometry_type
FROM
	pg_class c
	JOIN pg_namespace n ON (c.relnamespace = n.oid)
	JOIN pg_attribute a ON (a.attrelid = c.oid)
	JOIN pg_type t ON (a.atttypid = t.oid)
WHERE
	relkind IN('r', 'v', 'm')
	AND typname = 'geometry'
    AND postgis_typmod_srid (atttypmod) != 0
	AND relname = '<mytable>';"]

(defn execute!
  "Given a DB value and a SQL Map
   Returns the results of executing the SQL Statement"
  [db sql-stmt]
  (let [sql-string (sql/format sql-stmt {:pretty true})]

    (println sql-string)

    (jdbc/execute!
     db
     sql-string)))
