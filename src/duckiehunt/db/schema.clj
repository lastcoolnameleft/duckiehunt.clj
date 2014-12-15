(ns duckiehunt.db.schema
  (:require [clojure.java.jdbc :as sql]
            [noir.io :as io]))

(def db-store "site.db")

(def db-spec {:classname "org.h2.Driver"
              :subprotocol "h2"
              :subname (str (io/resource-path) db-store)
              :user "sa"
              :password ""
              :make-pool? true
              :naming {:keys clojure.string/lower-case
                       :fields clojure.string/upper-case}})
(defn initialized?
  "checks to see if the database schema is present"
  []
  (.exists (new java.io.File (str (io/resource-path) db-store ".mv.db"))))

(defn create-users-table
  []
  (sql/db-do-commands
    db-spec
    (sql/create-table-ddl
      :users
      [:id "varchar(20) PRIMARY KEY"]
      [:first_name "varchar(30)"]
      [:last_name "varchar(30)"]
      [:email "varchar(30)"]
      [:admin :boolean]
      [:last_login :time]
      [:is_active :boolean]
      [:pass "varchar(100)"])))

(defn create-activity-table
  []
  (sql/db-do-commands
    db-spec
    (sql/create-table-ddl
      :activity
      [:id "int(11) PRIMARY KEY"]
      [:user_agent "varchar(200)"]
      [:action "varchar(10)"]
      [:client_ip "int(10)"]
      [:duck_id "int(10)"]
      [:location "varchar(100)"]
      [:comments "varchar(300)"]
      )))

(defn create-duck-table
  []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :duck
    [:id "int(11) PRIMARY KEY"]
    [:create_time "int(11)"]
    [:name "varchar(128)"]
    [:current_owner_id "int(11)"]
    [:comments "text"]
    [:total_distance "float"]
    [:approved "varchar(10) "]
    )))

(defn create-duck-assign-table
  []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :duck_assign
    [:duck_id "int(11)"]
    [:user_id "int(11)"]
    [:duck_history_id "int(11)"]
    )))

(defn create-duck-history-table
  []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :duck_history
    [:duck_history_id "int(11) PRIMARY KEY"]
    [:duck_id "int(11)"]
    [:user_id "int(11)"]
    [:action_id "int(11)"]
    [:timestamp "timestamp"]
    [:user_ip "int(11)"]
    )))

(defn create-duck-history-action-table
  []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :duck_history_action
    [:duck_history_action_id "int(11) PRIMARY KEY"]
    [:action "varchar(30)"]
    )))

(defn create-duck-location-table
  []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :duck_location
    [:duck_location_id "int(11) PRIMARY KEY"]
    [:duck_id "int(11)"]
    [:user_id "int(11)"]
    [:link "text"]
    [:latitude "double"]
    [:longitude "double"]
    [:comments "text"]
    [:flickr_photo_id "bigint(20)"]
    [:duck_history_id "int(11)"]
    [:date_time "datetime"]
    [:location "text"]
    [:approved "varchar(10)"]
    [:distance_to "float"]
    )))


(defn create-duck-location-link-table
  []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :duck_location_link
    [:duck_location_link_id "int(11) PRIMARY KEY"]
    [:duck_location_id "int(11)"]
    [:link "varchar(100)"]
    )))

(defn create-duck-track-table
  []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :duck_track
    [:user_id "int(11)"]
    [:duck_id "int(11)"]
    [:duck_history_id "int(11)"]
    )))

(defn create-user-table
  []
  (sql/db-do-commands
   db-spec
   (sql/create-table-ddl
    :user
    [:user_id "int(11)"]
    [:username "varchar(20)"]
    [:email "varchar(100)"]
    )))

(defn create-tables
  "creates the database tables used by the application"
  []
  (create-users-table)
  (create-activity-table)
  (create-duck-table)
  (create-duck-assign-table)
  (create-duck-history-table)
  (create-duck-history-action-table)
  (create-duck-location-table)
  (create-duck-location-link-table)
  (create-duck-track-table)
  (create-user-table)
  )
