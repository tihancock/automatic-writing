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
                   :height "80%"
                   :width "75%"
                   :display :block
                   :margin-left :auto
                   :margin-right :auto
                   :top "50%"
                   :transform "translateY(-50%)"
                   :font-size "14px"
                   :border-width "5px"}]

  [:.not-writing-background {:background-color "#dcdcdc"}]
  [:.succeeding-background  {:background-color "#e0ffff"}]
  [:.failing-background     {:background-color "#ffe4c4"}]

  [:.not-writing-text       {:color "#696969"}]
  [:.succeeding-text        {:color "#32cd32"}]
  [:.failing-text           {:color "#ff4500"}]

  [:.not-writing-border     {:border-color "#696969"}]
  [:.succeeding-border      {:border-color "#32cd32"}]
  [:.failing-border         {:border-color "#ff4500"}])
