(ns clj-chrome-devtools.commands.cast
  "A domain for interacting with Cast, Presentation API, and Remote Playback API\nfunctionalities."
  (:require [clojure.spec.alpha :as s]
            [clj-chrome-devtools.impl.command :as cmd]
            [clj-chrome-devtools.impl.connection :as c]))

(s/def
 ::sink
 (s/keys
  :req-un
  [::name
   ::id]
  :opt-un
  [::session]))
(defn
 enable
 "Starts observing for sinks that can be used for tab mirroring, and if set,\nsinks compatible with |presentationUrl| as well. When sinks are found, a\n|sinksUpdated| event is fired.\nAlso starts observing for issue messages. When an issue is added or removed,\nan |issueUpdated| event is fired.\n\nParameters map keys:\n\n\n  Key               | Description \n  ------------------|------------ \n  :presentation-url | null (optional)"
 ([]
  (enable
   (c/get-current-connection)
   {}))
 ([{:as params, :keys [presentation-url]}]
  (enable
   (c/get-current-connection)
   params))
 ([connection {:as params, :keys [presentation-url]}]
  (let
   [id__69750__auto__
    (cmd/next-command-id!)
    method__69751__auto__
    "Cast.enable"
    ch__69752__auto__
    (clojure.core.async/chan)
    payload__69753__auto__
    (cmd/command-payload
     id__69750__auto__
     method__69751__auto__
     params
     {:presentation-url "presentationUrl"})]
   (c/send-command
    connection
    payload__69753__auto__
    id__69750__auto__
    (fn*
     [p1__69749__69754__auto__]
     (clojure.core.async/go
      (clojure.core.async/>!
       ch__69752__auto__
       p1__69749__69754__auto__))))
   (let
    [result__69755__auto__ (clojure.core.async/<!! ch__69752__auto__)]
    (if-let
     [error__69756__auto__ (:error result__69755__auto__)]
     (throw
      (ex-info
       (str
        "Error in command "
        method__69751__auto__
        ": "
        (:message error__69756__auto__))
       {:request payload__69753__auto__, :error error__69756__auto__}))
     (:result result__69755__auto__))))))

(s/fdef
 enable
 :args
 (s/or
  :no-args
  (s/cat)
  :just-params
  (s/cat
   :params
   (s/keys
    :opt-un
    [::presentation-url]))
  :connection-and-params
  (s/cat
   :connection
   (s/?
    c/connection?)
   :params
   (s/keys
    :opt-un
    [::presentation-url])))
 :ret
 (s/keys))

(defn
 disable
 "Stops observing for sinks and issues."
 ([]
  (disable
   (c/get-current-connection)
   {}))
 ([{:as params, :keys []}]
  (disable
   (c/get-current-connection)
   params))
 ([connection {:as params, :keys []}]
  (let
   [id__69750__auto__
    (cmd/next-command-id!)
    method__69751__auto__
    "Cast.disable"
    ch__69752__auto__
    (clojure.core.async/chan)
    payload__69753__auto__
    (cmd/command-payload
     id__69750__auto__
     method__69751__auto__
     params
     {})]
   (c/send-command
    connection
    payload__69753__auto__
    id__69750__auto__
    (fn*
     [p1__69749__69754__auto__]
     (clojure.core.async/go
      (clojure.core.async/>!
       ch__69752__auto__
       p1__69749__69754__auto__))))
   (let
    [result__69755__auto__ (clojure.core.async/<!! ch__69752__auto__)]
    (if-let
     [error__69756__auto__ (:error result__69755__auto__)]
     (throw
      (ex-info
       (str
        "Error in command "
        method__69751__auto__
        ": "
        (:message error__69756__auto__))
       {:request payload__69753__auto__, :error error__69756__auto__}))
     (:result result__69755__auto__))))))

(s/fdef
 disable
 :args
 (s/or
  :no-args
  (s/cat)
  :just-params
  (s/cat :params (s/keys))
  :connection-and-params
  (s/cat
   :connection
   (s/?
    c/connection?)
   :params
   (s/keys)))
 :ret
 (s/keys))

(defn
 set-sink-to-use
 "Sets a sink to be used when the web page requests the browser to choose a\nsink via Presentation API, Remote Playback API, or Cast SDK.\n\nParameters map keys:\n\n\n  Key        | Description \n  -----------|------------ \n  :sink-name | null"
 ([]
  (set-sink-to-use
   (c/get-current-connection)
   {}))
 ([{:as params, :keys [sink-name]}]
  (set-sink-to-use
   (c/get-current-connection)
   params))
 ([connection {:as params, :keys [sink-name]}]
  (let
   [id__69750__auto__
    (cmd/next-command-id!)
    method__69751__auto__
    "Cast.setSinkToUse"
    ch__69752__auto__
    (clojure.core.async/chan)
    payload__69753__auto__
    (cmd/command-payload
     id__69750__auto__
     method__69751__auto__
     params
     {:sink-name "sinkName"})]
   (c/send-command
    connection
    payload__69753__auto__
    id__69750__auto__
    (fn*
     [p1__69749__69754__auto__]
     (clojure.core.async/go
      (clojure.core.async/>!
       ch__69752__auto__
       p1__69749__69754__auto__))))
   (let
    [result__69755__auto__ (clojure.core.async/<!! ch__69752__auto__)]
    (if-let
     [error__69756__auto__ (:error result__69755__auto__)]
     (throw
      (ex-info
       (str
        "Error in command "
        method__69751__auto__
        ": "
        (:message error__69756__auto__))
       {:request payload__69753__auto__, :error error__69756__auto__}))
     (:result result__69755__auto__))))))

(s/fdef
 set-sink-to-use
 :args
 (s/or
  :no-args
  (s/cat)
  :just-params
  (s/cat
   :params
   (s/keys
    :req-un
    [::sink-name]))
  :connection-and-params
  (s/cat
   :connection
   (s/?
    c/connection?)
   :params
   (s/keys
    :req-un
    [::sink-name])))
 :ret
 (s/keys))

(defn
 start-tab-mirroring
 "Starts mirroring the tab to the sink.\n\nParameters map keys:\n\n\n  Key        | Description \n  -----------|------------ \n  :sink-name | null"
 ([]
  (start-tab-mirroring
   (c/get-current-connection)
   {}))
 ([{:as params, :keys [sink-name]}]
  (start-tab-mirroring
   (c/get-current-connection)
   params))
 ([connection {:as params, :keys [sink-name]}]
  (let
   [id__69750__auto__
    (cmd/next-command-id!)
    method__69751__auto__
    "Cast.startTabMirroring"
    ch__69752__auto__
    (clojure.core.async/chan)
    payload__69753__auto__
    (cmd/command-payload
     id__69750__auto__
     method__69751__auto__
     params
     {:sink-name "sinkName"})]
   (c/send-command
    connection
    payload__69753__auto__
    id__69750__auto__
    (fn*
     [p1__69749__69754__auto__]
     (clojure.core.async/go
      (clojure.core.async/>!
       ch__69752__auto__
       p1__69749__69754__auto__))))
   (let
    [result__69755__auto__ (clojure.core.async/<!! ch__69752__auto__)]
    (if-let
     [error__69756__auto__ (:error result__69755__auto__)]
     (throw
      (ex-info
       (str
        "Error in command "
        method__69751__auto__
        ": "
        (:message error__69756__auto__))
       {:request payload__69753__auto__, :error error__69756__auto__}))
     (:result result__69755__auto__))))))

(s/fdef
 start-tab-mirroring
 :args
 (s/or
  :no-args
  (s/cat)
  :just-params
  (s/cat
   :params
   (s/keys
    :req-un
    [::sink-name]))
  :connection-and-params
  (s/cat
   :connection
   (s/?
    c/connection?)
   :params
   (s/keys
    :req-un
    [::sink-name])))
 :ret
 (s/keys))

(defn
 stop-casting
 "Stops the active Cast session on the sink.\n\nParameters map keys:\n\n\n  Key        | Description \n  -----------|------------ \n  :sink-name | null"
 ([]
  (stop-casting
   (c/get-current-connection)
   {}))
 ([{:as params, :keys [sink-name]}]
  (stop-casting
   (c/get-current-connection)
   params))
 ([connection {:as params, :keys [sink-name]}]
  (let
   [id__69750__auto__
    (cmd/next-command-id!)
    method__69751__auto__
    "Cast.stopCasting"
    ch__69752__auto__
    (clojure.core.async/chan)
    payload__69753__auto__
    (cmd/command-payload
     id__69750__auto__
     method__69751__auto__
     params
     {:sink-name "sinkName"})]
   (c/send-command
    connection
    payload__69753__auto__
    id__69750__auto__
    (fn*
     [p1__69749__69754__auto__]
     (clojure.core.async/go
      (clojure.core.async/>!
       ch__69752__auto__
       p1__69749__69754__auto__))))
   (let
    [result__69755__auto__ (clojure.core.async/<!! ch__69752__auto__)]
    (if-let
     [error__69756__auto__ (:error result__69755__auto__)]
     (throw
      (ex-info
       (str
        "Error in command "
        method__69751__auto__
        ": "
        (:message error__69756__auto__))
       {:request payload__69753__auto__, :error error__69756__auto__}))
     (:result result__69755__auto__))))))

(s/fdef
 stop-casting
 :args
 (s/or
  :no-args
  (s/cat)
  :just-params
  (s/cat
   :params
   (s/keys
    :req-un
    [::sink-name]))
  :connection-and-params
  (s/cat
   :connection
   (s/?
    c/connection?)
   :params
   (s/keys
    :req-un
    [::sink-name])))
 :ret
 (s/keys))
