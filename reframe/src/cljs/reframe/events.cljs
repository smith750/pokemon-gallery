(ns reframe.events
    (:require [re-frame.core :as re-frame]
              [reframe.db :as db]
              [day8.re-frame.http-fx]
              [reagent.core :as reagent]
              [reframe.utils :as utils]))

(re-frame/reg-event-db
 :initialize-db
 (fn [db [_ a]]
   db/default-db))

(re-frame/reg-event-fx
  :load-pokemon-data
  (fn  [{:keys [db]} [_ uri]]
    (println "loading pokemon data, uri = " uri)
    { :http-xhrio (utils/build-http-request uri)
      :db db}))

(re-frame/reg-event-fx
  :process-pokemon-list
  (fn [{:keys [db]} [_ pokemon-data]]
    (println "processing data...")
    (let [curr-count (:pokemon-count @db)
          new-count (:count pokemon-data)
          new-pokemon-records (map #(utils/build-basic-pokemon-record %) (:results pokemon-data))
          new-pokemon (concat (:pokemon @db) new-pokemon-records)
          next-uri (:next pokemon-data)
          updated-db (swap! db assoc :pokemon new-pokemon)]
    (println "really processing data..., db = " db)
    (if (nil? next-uri)
        { :db db }
        { :db db
        ;{ :db (swap! db assoc :pokemon new-pokemon)}
        ;{ :db (swap! db assoc :pokemon new-pokemon)
          :dispatch [:load-pokemon-data next-uri] }
    )
    )))

(re-frame/reg-event-db
  :process-pokemon-list-failure
  (fn [db _]
    (println "could not retrieve pokemon data")
    db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (swap! db assoc :active-panel active-panel)
   db))
