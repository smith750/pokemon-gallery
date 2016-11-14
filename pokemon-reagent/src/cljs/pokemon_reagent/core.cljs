(ns pokemon-reagent.core
    (:require [reagent.core :as r :refer [atom]]
              ))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to pokemon-reagent"]
   ])

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [home-page] (.getElementById js/document "app")))

(defn init! []
  (mount-root))
