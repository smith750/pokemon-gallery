(ns reframe.db
  (:require [reagent.core :as reagent]))

(def default-db (reagent/atom
  {:name "re-frame"
   :pokemon []
   :pokemon-loaded? false
   :pokemon-count -1
   :active-panel :home-panel}))
