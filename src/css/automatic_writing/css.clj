(ns automatic-writing.css
  (:require  [garden.def :refer [defstyles]]))

(defstyles style
  [:body {:margin "0px"
          :padding "0px"}]

  [:#app {:position :absolute
          :height "100%"
          :width "100%"}]

  [:#automatic-writing {:height "100%"
                        :width "100%"}]

  [:#wpm {:position :fixed
          :left "30px"
          :top "10%"
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
                   :border-width "5px"}]

  [:#button-container {:position :fixed
                       :left   "180px"
                       :right  "180px"
                       :bottom "20px"
                       :height "40px"
                       :width  "75%"}]

  [:#submit-button {:position :relative
                    :margin-left "80%"
                    :height "100%"
                    :width "20%"
                    :font-size "14px"}]

  [:.not-writing-background {:background-color "#dcdcdc"}]
  [:.succeeding-background  {:background-color "#e0ffff"}]
  [:.failing-background     {:background-color "#ffe4c4"}]

  [:.not-writing-text       {:color "#696969"}]
  [:.succeeding-text        {:color "#32cd32"}]
  [:.failing-text           {:color "#ff4500"}]

  [:.not-writing-border     {:border-color "#696969"}]
  [:.succeeding-border      {:border-color "#32cd32"}]
  [:.failing-border         {:border-color "#ff4500"}])
