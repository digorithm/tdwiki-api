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
      (ok (get-td-indicators id)))
    
    (POST* "/" [td-name td-definition people-on-it]
      :body-params  [td-name        :- s/Str,
                     td-definition  :- s/Str,
                     people-on-it   :- Integer]
      :summary      "Insert a new technical debt in the database"
      (ok (add-technical-debt {:name td-name
                               :definition td-definition
                               :people-on-it people-on-it}))))
  
  (context* "/cause" []
    :tags ["cause"]

    (GET* "/" []
      :summary "Gets all Technical Debts's causes"
      (ok (get-cause)))
  
    (GET* "/:id" []
      :summary "Gets Technical Debts's cause by ID"
      :path-params [id :- Long]
      (ok (get-cause id)))
    
    (POST* "/" [cause-name]
      :body-params  [cause-name :- s/Str]
      :summary      "Insert a new cause in the database"
      (ok (add-cause {:name cause-name}))))
  
  (context* "/indicator" []
    :tags ["indicator"]

    (GET* "/" []
      :summary "Gets all Technical Debts's indicators"
      (ok (get-indicator)))
  
    (GET* "/:id" []
      :summary "Gets Technical Debts's indicator by ID"
      :path-params [id :- Long]
      (ok (get-indicator id)))
  
    (POST* "/" [indicator-name]
      :body-params  [indicator-name :- s/Str]
      :summary      "Insert a new indicator in the database"
      (ok (add-indicator {:name indicator-name})))))