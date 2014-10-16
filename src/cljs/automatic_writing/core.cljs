(ns automatic-writing.core
  (:require [reagent.core :as r]))

(defonce state (r/atom {:start-time nil
                        :wpm        0}))

(def wpm-target 40)

(def keycode->key
  {32 :space})

(def colours
  {:succeeding-foreground "#32cd32"
   :succeeding-background "#e0ffff"
   :failing-foreground    "#ff4500"
   :failing-background    "#ffe4c4"})

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
  (swap! state assoc :wpm (if-let [start-time (:start-time @state)]
                            (/ (num-words (get-current-text))
                               (time-in-minutes (.getTime (js/Date.)) start-time))
                            0)))

(defn set-start-time! []
  (case (count (get-current-text))
    0 (swap! state assoc :start-time nil)
    1 (swap! state assoc :start-time (.getTime (js/Date.)))
    nil))

(defn writing []
  (let [writing    (boolean (:start-time @state))
        succeeding (> (:wpm @state) wpm-target)]
    [:div {:id :automatic-writing
           :style {:background-color (cond
                                      (not writing) :white
                                      succeeding    (:succeeding-background colours)
                                      :else         (:failing-background colours))}}
     [:div {:id :wpm
            :style {:color (cond
                            (not writing) :grey
                            succeeding    (:succeeding-foreground colours)
                            :else         (:failing-foreground colours))}} 
      (str (int (:wpm @state)) " wpm")]
     [:textarea {:id        :writing-area
                 :style     {:border-width "5px"
                             :border-color (cond
                                            (not writing) :grey
                                            succeeding    (:succeeding-foreground colours)
                                            :else         (:failing-foreground colours))}
                 :on-change set-start-time!}]]))

(defn main []
  (js/setInterval update-wpm! 500)
  (r/render-component
   [writing]
   (.getElementById js/document "app")))
