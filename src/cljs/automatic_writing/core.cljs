(ns automatic-writing.core
  (:require [reagent.core :as r]
            [automatic-writing.pages.write :as write]
            [automatic-writing.pages.view :as view]))

(def pages
  {"write" write/write
   "view"  view/view})

(defonce app-state (r/atom {:current-page write/write}))

(defn header []
  [:div {:id :header}
   (for [[name page] pages]
     [:button {:class :header-button
               :on-click #(swap! app-state assoc :current-page page)} name])])

(defn app []
  [:div {:id :container}
   [header]
   [(:current-page @app-state)]])

(defn main []
  (r/render-component
   [app]
   (.getElementById js/document "app")))
