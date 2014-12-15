(defproject
  duckiehunt
  "0.1.0-SNAPSHOT"
  :description
  "FIXME: write description"
  :ring
  {:handler duckiehunt.handler/app,
   :init duckiehunt.handler/init,
   :destroy duckiehunt.handler/destroy}
  :cljsbuild
  {:builds
   [{:source-paths ["src-cljs"],
     :id "dev",
     :compiler
     {:output-dir "resources/public/js/",
      :optimizations :none,
      :output-to "resources/public/js/app.js",
      :source-map true,
      :pretty-print true}}
    {:source-paths ["src-cljs"],
     :id "release",
     :compiler
     {:closure-warnings {:non-standard-jsdoc :off},
      :optimizations :advanced,
      :output-to "resources/public/js/app.js",
      :output-wrapper false,
      :pretty-print false}}]}
  :plugins
  [[lein-ring "0.8.12"]
   [lein-environ "0.5.0"]
   [lein-ancient "0.5.5"]
   [lein-cljsbuild "1.0.3"]]
  :url
  "http://example.com/FIXME"
  :profiles
  {:uberjar {:aot :all},
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}},
   :dev
   {:dependencies
    [[ring-mock "0.1.5"]
     [ring/ring-devel "1.3.1"]
     [pjstadig/humane-test-output "0.6.0"]],
    :injections
    [(require 'pjstadig.humane-test-output)
     (pjstadig.humane-test-output/activate!)],
    :env {:dev true}}}
  :jvm-opts
  ["-server"]
  :dependencies
  [[log4j
    "1.2.17"
    :exclusions
    [javax.mail/mail
     javax.jms/jms
     com.sun.jdmk/jmxtools
     com.sun.jmx/jmxri]]
   [org.clojure/clojurescript "0.0-2356"]
   [prone "0.6.0"]
   [noir-exception "0.2.2"]
   [cljs-ajax "0.3.2"]
   [markdown-clj "0.9.54"]
   [com.taoensso/timbre "3.3.1"]
   [com.h2database/h2 "1.4.181"]
   [reagent-forms "0.2.3"]
   [com.taoensso/tower "3.0.1"]
   [lib-noir "0.9.0"]
   [secretary "1.2.0"]
   [korma "0.4.0"]
   [selmer "0.7.1"]
   [org.clojure/clojure "1.6.0"]
   [environ "1.0.0"]
   [ring-server "0.3.1"]
   [im.chit/cronj "1.4.2"]]
  :repl-options
  {:init-ns duckiehunt.repl}
  :min-lein-version "2.0.0")