(ns reframe.events
    (:require [re-frame.core :as re-frame]
              [reframe.db :as db]
              [day8.re-frame.http-fx]
              [ajax.core :as ajax]))

(re-frame/reg-event-fx
 :initialize-db
 (fn  [{:keys [db]} [_ a]]
   (println "initializing app")
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
    (assoc db :pokemon new-pokemon)
    (if (< curr-count 0) (assoc db :pokemon-count new-count)))))

(re-frame/reg-event-db
  :process-pokemon-list-failure
  (fn [db _]
    (println "could not retrieve pokemon data")))

(re-frame/reg-event-db
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))
