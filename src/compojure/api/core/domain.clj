(ns compojure.api.core.domain
  (:require [schema.core :as s]
            [ring.swagger.schema :refer [coerce!]]))

(require '[clojure.java.jdbc :as j])
(require '[clojure.data.json :as json])


(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/technicaldebtdb"
               :user "root"
               :password "sa"})

(defn get-technicaldebt
  ([]   (j/query mysql-db ["select * from technical_debt"]))
  ([id] (j/query mysql-db ["select * from technical_debt where idtechnical_debt = ?" id])))

(defn get-indicator
  ([]   (j/query mysql-db ["select * from indicator"]))
  ([id] (j/query mysql-db ["select * from indicator where idindicator = ?" id])))
  
(defn get-cause
  ([]   (j/query mysql-db ["select * from cause"]))
  ([id] (j/query mysql-db ["select * from cause where idcause = ?" id])))

(defn get-td-indicators [tdid]
  (j/query mysql-db ["select occurrence, name from td_indicators
                     join indicator on indicator.idindicator = td_indicators.idindicator
                     where td_indicators.idtechnical_debt = ? " tdid]))

(defn get-td-causes [tdid]
  (j/query mysql-db ["select relevance, name from td_causes 
                     join cause on cause.idcause = td_causes.idcause 
                     where td_causes.idtechnical_debt = ? " tdid]))