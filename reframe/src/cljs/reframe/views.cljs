(ns reframe.views
    (:require [re-frame.core :as re-frame]))


;; home
(defn pokemon-row [pokemon-record]
  [:tr
    [:td (:name pokemon-record)]])

(defn pokemon-table []
  (let [pokemon (re-frame/subscribe [:pokemon])
        pokemon-count (re-frame/subscribe [:pokemon-count])]
    (fn []
    [:table
      [:thead
      [:tr
        [:th "Pokemon"]]]
      [:tbody
      (for [pokemon-record @pokemon] ^{:key pokemon-record} [pokemon-row pokemon-record])
      ]])))

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div (str "Hello from " @name ". This is the Home Page.")
       [:div [:a {:href "#/about"} "go to About Page"]]
       [pokemon-table]
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
