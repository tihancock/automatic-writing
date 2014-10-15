(ns automatic-writing.core
  (:require [reagent.core :as r]))

(defn writing []
  [:textarea {:id "writing"}])

(defn main []
  (r/render-component 
   [writing]
   (.getElementById js/document "app")))
