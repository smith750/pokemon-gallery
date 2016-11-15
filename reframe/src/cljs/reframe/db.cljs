(ns reframe.db
  (:require [reagent.core :as reagent]))

(def default-db (reagent/atom
  {:name "re-frame"
   :pokemon []
   :pokemon-count -1
   :active-panel :home-panel}))
