(ns reframe.views
    (:require [re-frame.core :as re-frame]))


;; home
(defn pokemon-row [pokemon-record]
  [:tr
    [:td [:a {:href (str "#/pokemon-detail/" (:pokemon-id pokemon-record))} (:name pokemon-record)]]])

(defn pokemon-table []
  ;(let [{:keys [pokemon pokemon-count]} (re-frame/subscribe [:pokemon])]
  (let [pokemon-map (re-frame/subscribe [:pokemon])]
    (fn []
      (println "drawing pokemon table " @pokemon-map)
    [:table
      [:thead
      [:tr
        [:th "Pokemon"]]]
      [:tbody
      (for [pokemon-record (:pokemon @pokemon-map)] ^{:key pokemon-record} [pokemon-row pokemon-record])
      ]])))

(defn loading-pokemon []
  [:div "Loading..."])

(defn pokemon-table-display []
  (let [pokemon-loaded (re-frame/subscribe [:pokemon-loaded])]
    (if @pokemon-loaded
      [pokemon-table]
      [loading-pokemon]
      )))

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div (str "Hello from " @name ". This is the Home Page.")
       [pokemon-table-display]
       [:div [:a {:href "#/about"} "go to About Page"]]
      ])))


;; about

(defn about-panel []
  (fn []
    [:div "This is the About Page."
     [:div [:a {:href "#/"} "go to Home Page"]]]))


;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :default [] [:div])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (println "active panel = " active-panel)
    (fn []
      [show-panel @active-panel])))
