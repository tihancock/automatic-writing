(ns automatic-writing.core
  (:require [reagent.core :as r]))

(defonce state (r/atom {:start-time nil
                        :wpm        0}))

(def wpm-target 40)

(def keycode->key
  {32 :space})

(defn get-current-text
  []
  (-> js/document
      (.getElementById "writing-area")
      (.-value)))

(defn num-words [text]
  (count (.match text (js/RegExp. "[^ ] [^ ]" "g")))) ;; FIXME - find a better way of doing this when the internet returns

(defn time-in-minutes
  [current-time-ms start-time-ms]
  (/ (float (- current-time-ms start-time-ms))
     60000))

(defn update-wpm! []
  (if-let [start-time (:start-time @state)]
    (swap! state assoc :wpm (/ (num-words (get-current-text))
                               (time-in-minutes (.getTime (js/Date.)) start-time)))))

(defn set-start-time! []
  (when (= 1 (count (get-current-text)))
    (swap! state assoc :start-time (.getTime (js/Date.)))))

(defn writing []
  (let [succeeding (> (:wpm @state) wpm-target)]
    [:div {:id :automatic-writing
           :style {:background-color (if succeeding "#e0ffff" "#ffe4c4")}}
     [:div {:id :wpm} (str (int (:wpm @state)) " wpm")]
     [:textarea {:id        :writing-area
                 :style     {:border-width "5px"
                             :border-color (if succeeding "#32cd32" "#ff4500")}
                 :on-change set-start-time!}]]))

(defn main []
  (js/setInterval update-wpm! 500)
  (r/render-component
   [writing]
   (.getElementById js/document "app")))
