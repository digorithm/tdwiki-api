(defproject metosin/compojure-api-examples "0.23.0"
  :description "TDWiki API made with Clojure"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-time "0.11.0"] ;; needed as `lein ring uberwar` is broken.
                 [metosin/compojure-api "0.23.0"]
                 [mysql/mysql-connector-java "5.1.25"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [org.clojure/data.json "0.2.6"]]
  :ring {:handler compojure.api.examples.handler/app}
  :uberjar-name "examples.jar"
  :uberwar-name "examples.war"
  :profiles {:uberjar {:resource-paths ["swagger-ui"]
                       :aot :all}
             :dev {:dependencies [[javax.servlet/servlet-api "2.5"]]
                   :plugins [[lein-ring "0.9.6"]]}})
