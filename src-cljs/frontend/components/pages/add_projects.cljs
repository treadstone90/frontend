(ns frontend.components.pages.add-projects
  (:require [frontend.components.add-projects :as add-projects]
            [frontend.components.pieces.button :as button]
            [frontend.components.templates.main :as main-template]
            [frontend.utils.launchdarkly :as ld]
            [om.core :as om :include-macros true]))

(defn page [app owner]
  (reify
    om/IRender
    (render [_]
      (main-template/template
       (merge {:app app
               :main-content (om/build add-projects/add-projects app)}
         (if (ld/feature-on? "top-bar-ui-v-1")
           {:crumbs [{:type :projects :projects "Projects"}
                     {:type :add-projects :add-projects "Add Projects"}]}
           {:header-actions (button/button {:on-click #(raise! owner [:refreshed-user-orgs {}])
                                            :kind :primary
                                            :size :small}
                              "Reload Organizations")}))))))
