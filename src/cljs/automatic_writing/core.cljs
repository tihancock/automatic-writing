(ns automatic-writing.core
  (:require [reagent.core :as r]))

(defonce state (r/atom {:start-time nil
                        :wpm        0}))

(def keycode->key
  {32 :space})

(defn get-current-text
  []
  (-> js/document
      (.getElementById "writing-area")
      (.-value)))

(defn num-words []
  (let [current-text (get-current-text)]
    (count (.match current-text (js/RegExp. "[^ ] [^ ]" "g"))))) ;; FIXME - find a better way of doing this when the internet returns

(defn time-in-minutes
  [current-time-ms start-time-ms]
  (/ (float (- current-time-ms start-time-ms))
     60000))

(defn update-wpm! []
  (if (:start-time @state)
    (swap! state
           (fn [s] (update-in s
                              [:wpm]
                              (fn [old-wpm] (/ (num-words)
                                               (time-in-minutes (.getTime (js/Date.)) (:start-time s)))))))))

(defn set-start-time! []
  (let [current-text (get-current-text)]
    (when (= 1 (count current-text))
      (swap! state assoc :start-time (.getTime (js/Date.))))))

(defn wpm []
  (fn []
    (js/setInterval update-wpm! 500)
    [:div {:id :wpm} (str (int (:wpm @state)) " wpm")]))

(defn writing []
  [:div {:id :automatic-writing}
   [wpm]
   [:textarea {:id        :writing-area
               :on-change set-start-time!}]])

(defn main []
  (r/render-component
   [writing]
   (.getElementById js/document "app")))
