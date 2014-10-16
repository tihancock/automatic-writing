(ns automatic-writing.core
  (:require [reagent.core :as r]
            [clojure.string :refer [join]]))

(defonce state (r/atom {:start-time nil
                        :wpm        0}))

(def wpm-target 40)

(def keycode->key
  {32 :space})

(defn get-colour-class
  [writing succeeding type]
  (let [writing-state (cond
                       (not writing) :not-writing
                       succeeding    :succeeding
                       :else         :failing)]
    (join "-" (map name [writing-state type]))))

(defn get-current-text
  []
  (-> js/document
      (.getElementById "writing-area")
      (.-value)))

(defn num-words [text]
  (count (.split text (js/RegExp. " " "g"))))

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

(defn submit-writing! []
  ;; FIXME - POST current text
  (set! (-> js/document
            (.getElementById "writing-area")
            (.-value)) "")
  (swap! state (constantly {:start-time nil
                            :wpm        0})))

(defn writing []
  (let [wpm        (:wpm @state)
        writing    (and (boolean (:start-time @state))
                        (not (zero? wpm)))
        succeeding (> wpm wpm-target)]
    [:div {:id      :automatic-writing
           :class   [(get-colour-class writing succeeding :background)]}
     [:div {:id      :wpm
            :class   [(get-colour-class writing succeeding :text)]}
      (str (int (:wpm @state)) " wpm")]
     [:textarea {:id        :writing-area
                 :class     [(get-colour-class writing succeeding :border)]
                 :on-change set-start-time!}]
     [:div {:id :button-container}
      [:button {:id       :submit-button
                :on-click submit-writing!} "Submit"]]]))

(defn main []
  (js/setInterval update-wpm! 500)
  (r/render-component
   [writing]
   (.getElementById js/document "app")))
