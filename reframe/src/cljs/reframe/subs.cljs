(ns reframe.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name @db)))

(re-frame/reg-sub
 :active-panel
 (fn [db]
   (:active-panel @db)))

(re-frame/reg-sub
  :pokemon
  (fn [db]
    (:pokemon @db)))

(re-frame/reg-sub
  :pokemon-count
  (fn [db]
    (:pokemon-count @db)))
