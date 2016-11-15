(ns reframe.events
    (:require [re-frame.core :as re-frame]
              [reframe.db :as db]
              [day8.re-frame.http-fx]
              [reagent.core :as reagent]
              [ajax.core :as ajax]))

(re-frame/reg-event-db
 :initialize-db
 (fn [db [_ a]]
   (println "initializing app")
   db/default-db))

(re-frame/reg-event-fx
  :load-pokemon-data
  (fn  [{:keys [db]} [_ a]]
    (println "initializing app, db = " db)
    { :http-xhrio {
         :method :get
         :uri "http://pokeapi.co/api/v2/pokemon"
         :response-format (ajax/json-response-format {:keywords? true})
         :on-success [:process-pokemon-list]
         :on-fail    [:process-pokemon-list-failure]
       }
      :db db/default-db}))

(re-frame/reg-event-db
  :process-pokemon-list
  (fn [db [_ pokemon-data]]
    (let [curr-count (:pokemon-count @db)
          new-count (:count pokemon-data)
          new-pokemon (concat (:pokemon @db) (:results pokemon-data))]
    (println "got pokemon data " pokemon-data)
    (swap! db assoc :pokemon new-pokemon)
    db)))

(re-frame/reg-event-db
  :process-pokemon-list-failure
  (fn [db _]
    (println "could not retrieve pokemon data")
    db))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (swap! db assoc :active-panel active-panel)
   (println "set active panel, db = " db ", active panel = " active-panel)
   db))
