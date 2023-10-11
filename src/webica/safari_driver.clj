(ns webica.safari-driver
  "Clojure binding for Selenium class SafariDriver"
  (:refer-clojure :exclude [get])
  (:require [clojure.core :as clj]
            [webica.core :as w]
            [me.raynes.fs :as fs])
  (:import [org.openqa.selenium.safari
            SafariDriver]))

(w/intern-java SafariDriver *ns*
  {:clear '[quit kill]})

(defn start-safari []
    (instance))
