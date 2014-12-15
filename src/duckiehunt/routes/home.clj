(ns duckiehunt.routes.home
            (:require [duckiehunt.layout :as layout]
                      [duckiehunt.util :as util]
                      [duckiehunt.db.core :as db]
                      [compojure.core :refer :all]
                      [noir.response :refer [edn]]
                      [clojure.pprint :refer [pprint]]))

(defn home-page []
      (layout/render
        "home.html" {:docs (util/md->html "/md/docs.md")}))

(defn mark-page [& [id name error]]
      (layout/render
        "mark.html" {:id id :name name :error error :docs (util/md->html "/md/docs.md")}))

(defn mark-page-id [& [id name error]]
      (layout/render
        "mark.html" {:id id :name name :error error :docs (util/md->html "/md/docs.md")}))

(defn created-page [& [id name error]]
      (layout/render
        "created.html" {:id id :name name :error error :docs (util/md->html "/md/docs.md")}))

(defn stat-page []
      (layout/render
        "stat.html" {:docs (util/md->html "/md/docs.md")}))

(defn faq-page []
      (layout/render
        "faq.html" {:docs (util/md->html "/md/docs.md")}))

(defn save-document [doc]
      (pprint doc)
      {:status "ok"})

(defn save-duck [body-params]
  (cond
     (empty? (:id body-params))
     (mark-page body-params "Don't forget an id")

     (empty? (:name body-params))
     (mark-page body-params "Don't forget a name")

     :else
     (do
       (db/create-duck body-params)
       (mark-page body-params))))

(defn save-duck-id [id name]
  (cond
     (empty? id)
     (mark-page-id id name "Don't forget an id")

     (empty? name)
     (mark-page-id id name "Don't forget a name")

     :else
     (do
       (db/create-duck-id id name)
       (mark-page-id id name))))


(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/mark" [] (mark-page))
  (GET "/stat" [] (stat-page))
  (GET "/faq" [] (faq-page))
  (POST "/mark" [id name] (save-duck-id id name))
  (POST "/mark2" {:keys [body-params]}
    (edn (save-duck body-params))))
