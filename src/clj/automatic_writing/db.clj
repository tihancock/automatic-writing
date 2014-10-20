(ns automatic-writing.db
  (:require [clojure.java.jdbc :as j]))

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2:file"
              :subname "db/automatic-writing"})

(defn add-writing
  [text]
  (j/insert! db-spec :writings {:automatic_writing text}))

(defn get-writings
  []
  (j/query db-spec ["SELECT automatic_writing, score FROM writings ORDER BY created_at"]))

(defn drop-tables
  []
  (j/execute! db-spec [(str "DROP TABLE IF EXISTS writings")]))

(defn create-tables
  []
  (j/execute! db-spec [(str "CREATE TABLE IF NOT EXISTS writings ("
                            "automatic_writing varchar NOT NULL, "
                            "score int NOT NULL DEFAULT 0, "
                            "user varchar"
                            "created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP"
                            ")")]))
