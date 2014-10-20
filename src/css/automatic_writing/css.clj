(ns automatic-writing.css
  (:require  [garden.def :refer [defstyles]]))

(defstyles style
  [:body {:margin "0px"
          :padding "0px"}]

  [:#app {:position :absolute
          :height "100%"
          :width "100%"}]

  [:#header {:position :fixed
             :height "40px"
             :width "100%"}]

  [:#wpm {:position :fixed
          :left "30px"
          :top "80px"
          :width "120px"
          :font-size "30px"}]

  [:#writing-area {:resize :none
                   :position :fixed
                   :left "180px"
                   :right "180px"
                   :bottom "80px"
                   :top "80px"
                   :min-height "5em"
                   :min-width  "75%"
                   :max-height "80%"
                   :max-width "75%"
                   :font-size "14px"
                   :border-width "5px"
                   :padding "0px"}]

  [:#button-container {:position :fixed
                       :left   "180px"
                       :right  "180px"
                       :bottom "20px"
                       :height "40px"
                       :width  "75%"
                       :padding "5px"
                       :margin "5px"}]

  [:#submit-button {:position :relative
                    :margin-left "80%"
                    :height "100%"
                    :width "20%"
                    :font-size "14px"}]

  [:#container {:class :default-background
                :height "100%"
                :width "100%"}]

  [:.content {:position :fixed
              :top "40px"
              :height "95%"
              :width "100%"}]

  [:.header-button {:height "100%"
                    :min-width "10%"
                    :margin "0px"
                    :font-size "14px"}]

  [:.default-background    {:background-color "#dcdcdc"}]
  [:.succeeding-background {:background-color "#e0ffff"}]
  [:.failing-background    {:background-color "#ffe4c4"}]

  [:.default-text    {:color "#696969"}]
  [:.succeeding-text {:color "#32cd32"}]
  [:.failing-text    {:color "#ff4500"}]

  [:.default-border    {:border-color "#696969"}]
  [:.succeeding-border {:border-color "#32cd32"}]
  [:.failing-border    {:border-color "#ff4500"}])
