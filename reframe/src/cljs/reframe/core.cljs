(ns reframe.core
    (:require [reagent.core :as reagent]
              [re-frame.core :as re-frame]
              [reframe.events]
              [reframe.subs]
              [reframe.routes :as routes]
              [reframe.views :as views]
              [reframe.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (dev-setup)
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))
