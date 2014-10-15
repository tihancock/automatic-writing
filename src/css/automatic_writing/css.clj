(ns automatic-writing.css
  (:require  [garden.def :refer [defstyles]]))

(defstyles style
  [:body {:margin "0px"
          :padding "0px"}]

  [:#app {:position :absolute
          :height "100%"
          :width "100%"}]

  [:#writing {:resize :none
              :position :relative
              :height "80%"
              :width "75%"
              :display :block
              :margin-left :auto
              :margin-right :auto
              :top "50%"
              :transform "translateY(-50%)"}])
