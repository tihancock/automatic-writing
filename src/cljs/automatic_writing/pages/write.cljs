(ns automatic-writing.pages.write
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r]
            [clojure.string :refer [join]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<! take!]]))

(defonce state (r/atom {:start-time nil
                        :wpm        0}))

(def wpm-target 40)

(defn get-colour-class
  [writing succeeding type]
  (let [writing-state (cond
                       (not writing) :default
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
  (http/post "/writing" {:form-params {:automatic-writing (get-current-text)}})
  (set! (-> js/document
            (.getElementById "writing-area")
            (.-value)) "")
  (swap! state (constantly {:start-time nil
                            :wpm        0})))

(defn write []
  (js/setInterval update-wpm! 500)
  (let [wpm        (:wpm @state)
        writing    (and (boolean (:start-time @state))
                        (not (zero? wpm)))
        succeeding (> wpm wpm-target)]
    (set! (-> js/document
              (.getElementById "app")
              (.-className))
          (get-colour-class writing succeeding :background))
    [:div {:class :content}
     [:div {:id      :wpm
            :class   [(get-colour-class writing succeeding :text)]}
      (str (int (:wpm @state)) " wpm")]
     [:textarea {:id        :writing-area
                 :class     [(get-colour-class writing succeeding :border)]
                 :on-change set-start-time!}]
     [:div {:id :button-container}
      [:button {:id       :submit-button
                :on-click submit-writing!} "submit"]]]))
