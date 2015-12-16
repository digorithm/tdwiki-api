(ns compojure.api.core.handler
  (:require [compojure.api.sweet :refer :all]
            [ring.util.http-response :refer :all]
            [compojure.api.core.domain :refer :all]
            [schema.core :as s]))


(defapi app
  (swagger-ui)
  (swagger-docs
    {:info {:title "TDWiki API"
            :description "API made with Clojure for the TDWiki"}
     :tags [{:name "technicaldebt", :description "Technical Debts"}]})


  (context* "/technicaldebt" []
    :tags ["technicaldebt"]

    (GET* "/" []
      :summary "Gets all Technical Debts"
      (ok (get-technicaldebt)))
    
    (GET* "/:id" []
      :summary "Gets Technical Debt by ID"
      :path-params [id :- Long]
      (ok (get-technicaldebt id)))
    
    (GET* "/:id/causes" []
      :summary "Gets the causes of a given Technical Debt"
      :path-params [id :- Long]
      (ok (get-td-causes id)))
    
    (GET* "/:id/indicators" []
      :summary "Gets the indicators of a given Technical Debt"
      :path-params [id :- Long]
      (ok (get-td-indicators id))))
  
  (context* "/cause" []
    :tags ["cause"]

    (GET* "/" []
      :summary "Gets all Technical Debts's causes"
      (ok (get-cause)))
  
    (GET* "/:id" []
      :summary "Gets Technical Debts's cause by ID"
      :path-params [id :- Long]
      (ok (get-cause id))))
  
  (context* "/indicator" []
    :tags ["indicator"]

    (GET* "/" []
      :summary "Gets all Technical Debts's indicators"
      (ok (get-indicator)))
  
    (GET* "/:id" []
      :summary "Gets Technical Debts's indicator by ID"
      :path-params [id :- Long]
      (ok (get-indicator id)))))
