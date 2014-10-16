(ns automatic-writing.core
  (:require [reagent.core :as r]
            [automatic-writing.pages.write :as write]))

(defn main []
  (js/setInterval update-wpm! 500)
  (r/render-component
   [write/writing]
   (.getElementById js/document "app")))
