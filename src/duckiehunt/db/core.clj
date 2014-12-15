(ns duckiehunt.db.core
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [duckiehunt.db.schema :as schema]))

(defdb db schema/db-spec)

(defentity users)
(defentity duck)

(defn create-user [user]
  (insert users
          (values user)))

(defn update-user [id first-name last-name email]
  (update users
  (set-fields {:first_name first-name
               :last_name last-name
               :email email})
  (where {:id id})))

(defn get-user [id]
  (first (select users
                 (where {:id id})
                 (limit 1))))

(defn get-all-ducks []
  (select duck))


(defn update-duck [id duck-name]
  (update duck
          (set-fields {:name duck-name})))

(defn create-duck [duck]
  (insert duck
          (values duck)))

(defn create-duck-id [id name]
  (insert duck (values {:id id :name name})))

(get-all-ducks)
