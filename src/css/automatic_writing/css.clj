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
          :width "120px"}]

  [:#writing-area {:resize :none
                   :position :relative
                   :height "80%"
                   :width "75%"
                   :display :block
                   :margin-left :auto
                   :margin-right :auto
                   :top "50%"
                   :transform "translateY(-50%)"}])
