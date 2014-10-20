(ns automatic-writing.pages.view
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<! take!]]))

(def state (r/atom {:writings nil}))

(defn writings
  []
  (if-let [writings (:writings @state)]
    [:ul
     (for [w writings]
       [:li (:automatic_writing w)])]))

(defn view []
  (go (let [result (<! (http/get "/views"))
            writings (cljs.reader/read-string (:body result))]
        (swap! state assoc :writings writings)
        (println (:writings @state)))) ;; FIXME - check return code
  [:div {:class :content}
   [writings]])
