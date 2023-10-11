(ns webica.lmgtfy
  (:require [clojure.string :as string]
            [webica.core :as w] ;; must always be first
            [webica.by :as by]
            [webica.web-driver :as driver]
            [webica.chrome-driver :as chrome]
            ;;[webica.firefox-driver :as firefox]
            [webica.web-element :as element]
            [webica.web-driver-wait :as wait]
            [webica.remote-web-driver :as browser]))

(defn lmgtfy [search]
  (browser/get "http://www.google.com")
  (let [q (browser/find-element (by/name "q"))]
    (element/send-keys q search)
    (element/submit q)
    (wait/until (wait/instance 10)
      (wait/condition
        (fn [driver]
          (string/starts-with?
            (string/lower-case (driver/get-title driver))
            (string/lower-case search)))))
    (println "Was that so hard?")
    (w/sleep 10)))

(defn -main
  "Webica example of 'Let me Google that for you'"
  [& args]
  (let [search (if (empty? args) "Clojure Conj 2016"
                   (apply str (interpose " " args)))]
    (chrome/start-chrome "/usr/local/bin/chromedriver")
    (lmgtfy search)
    (browser/quit)))
