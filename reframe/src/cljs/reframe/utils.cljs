(ns reframe.utils
  (:require [ajax.core :as ajax]))

(defn build-http-request [uri]
  (if (nil? uri)
      nil
      { :method :get
        :uri uri
        :response-format (ajax/json-response-format {:keywords? true})
        :on-success [:process-pokemon-list]
        :on-fail    [:process-pokemon-list-failure] }))

(defn extract-pokemon-id [pokemon-uri]
  (nth (re-find #"http://pokeapi.co/api/v2/pokemon/(\d+)/" pokemon-uri) 1))

(defn build-basic-pokemon-record [pokemon-data-row]
  {
    :name (:name pokemon-data-row)
    :pokemon-id (extract-pokemon-id (:url pokemon-data-row))
    :uri (:url pokemon-data-row)
    })
