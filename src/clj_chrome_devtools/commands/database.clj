(ns clj-chrome-devtools.commands.database
  (:require [clojure.spec.alpha :as s]
            [clj-chrome-devtools.impl.connection :as c]))
(s/def
 ::database-id
 string?)

(s/def
 ::database
 (s/keys
  :req-un
  [::id
   ::domain
   ::name
   ::version]))

(s/def
 ::error
 (s/keys
  :req-un
  [::message
   ::code]))
(defn
 disable
 "Disables database tracking, prevents database events from being sent to the client."
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
   [id__62694__auto__
    (clj-chrome-devtools.impl.define/next-command-id!)
    method__62695__auto__
    "Database.disable"
    ch__62696__auto__
    (clojure.core.async/chan)
    payload__62697__auto__
    (clj-chrome-devtools.impl.define/command-payload
     id__62694__auto__
     method__62695__auto__
     params
     {})]
   (c/send-command
    connection
    payload__62697__auto__
    id__62694__auto__
    (fn*
     [p1__62693__62698__auto__]
     (clojure.core.async/go
      (clojure.core.async/>!
       ch__62696__auto__
       p1__62693__62698__auto__))))
   (let
    [result__62699__auto__ (clojure.core.async/<!! ch__62696__auto__)]
    (if-let
     [error__62700__auto__ (:error result__62699__auto__)]
     (throw
      (ex-info
       (str
        "Error in command "
        method__62695__auto__
        ": "
        (:message error__62700__auto__))
       {:request payload__62697__auto__, :error error__62700__auto__}))
     (:result result__62699__auto__))))))

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
 enable
 "Enables database tracking, database events will now be delivered to the client."
 ([]
  (enable
   (c/get-current-connection)
   {}))
 ([{:as params, :keys []}]
  (enable
   (c/get-current-connection)
   params))
 ([connection {:as params, :keys []}]
  (let
   [id__62694__auto__
    (clj-chrome-devtools.impl.define/next-command-id!)
    method__62695__auto__
    "Database.enable"
    ch__62696__auto__
    (clojure.core.async/chan)
    payload__62697__auto__
    (clj-chrome-devtools.impl.define/command-payload
     id__62694__auto__
     method__62695__auto__
     params
     {})]
   (c/send-command
    connection
    payload__62697__auto__
    id__62694__auto__
    (fn*
     [p1__62693__62698__auto__]
     (clojure.core.async/go
      (clojure.core.async/>!
       ch__62696__auto__
       p1__62693__62698__auto__))))
   (let
    [result__62699__auto__ (clojure.core.async/<!! ch__62696__auto__)]
    (if-let
     [error__62700__auto__ (:error result__62699__auto__)]
     (throw
      (ex-info
       (str
        "Error in command "
        method__62695__auto__
        ": "
        (:message error__62700__auto__))
       {:request payload__62697__auto__, :error error__62700__auto__}))
     (:result result__62699__auto__))))))

(s/fdef
 enable
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
 execute-sql
 "\n\nParameters map keys:\n\n\n  Key          | Description \n  -------------|------------ \n  :database-id | null\n  :query       | null\n\nReturn map keys:\n\n\n  Key           | Description \n  --------------|------------ \n  :column-names | null (optional)\n  :values       | null (optional)\n  :sql-error    | null (optional)"
 ([]
  (execute-sql
   (c/get-current-connection)
   {}))
 ([{:as params, :keys [database-id query]}]
  (execute-sql
   (c/get-current-connection)
   params))
 ([connection {:as params, :keys [database-id query]}]
  (let
   [id__62694__auto__
    (clj-chrome-devtools.impl.define/next-command-id!)
    method__62695__auto__
    "Database.executeSQL"
    ch__62696__auto__
    (clojure.core.async/chan)
    payload__62697__auto__
    (clj-chrome-devtools.impl.define/command-payload
     id__62694__auto__
     method__62695__auto__
     params
     {:database-id "databaseId", :query "query"})]
   (c/send-command
    connection
    payload__62697__auto__
    id__62694__auto__
    (fn*
     [p1__62693__62698__auto__]
     (clojure.core.async/go
      (clojure.core.async/>!
       ch__62696__auto__
       p1__62693__62698__auto__))))
   (let
    [result__62699__auto__ (clojure.core.async/<!! ch__62696__auto__)]
    (if-let
     [error__62700__auto__ (:error result__62699__auto__)]
     (throw
      (ex-info
       (str
        "Error in command "
        method__62695__auto__
        ": "
        (:message error__62700__auto__))
       {:request payload__62697__auto__, :error error__62700__auto__}))
     (:result result__62699__auto__))))))

(s/fdef
 execute-sql
 :args
 (s/or
  :no-args
  (s/cat)
  :just-params
  (s/cat
   :params
   (s/keys
    :req-un
    [::database-id
     ::query]))
  :connection-and-params
  (s/cat
   :connection
   (s/?
    c/connection?)
   :params
   (s/keys
    :req-un
    [::database-id
     ::query])))
 :ret
 (s/keys
  :opt-un
  [::column-names
   ::values
   ::sql-error]))

(defn
 get-database-table-names
 "\n\nParameters map keys:\n\n\n  Key          | Description \n  -------------|------------ \n  :database-id | null\n\nReturn map keys:\n\n\n  Key          | Description \n  -------------|------------ \n  :table-names | null"
 ([]
  (get-database-table-names
   (c/get-current-connection)
   {}))
 ([{:as params, :keys [database-id]}]
  (get-database-table-names
   (c/get-current-connection)
   params))
 ([connection {:as params, :keys [database-id]}]
  (let
   [id__62694__auto__
    (clj-chrome-devtools.impl.define/next-command-id!)
    method__62695__auto__
    "Database.getDatabaseTableNames"
    ch__62696__auto__
    (clojure.core.async/chan)
    payload__62697__auto__
    (clj-chrome-devtools.impl.define/command-payload
     id__62694__auto__
     method__62695__auto__
     params
     {:database-id "databaseId"})]
   (c/send-command
    connection
    payload__62697__auto__
    id__62694__auto__
    (fn*
     [p1__62693__62698__auto__]
     (clojure.core.async/go
      (clojure.core.async/>!
       ch__62696__auto__
       p1__62693__62698__auto__))))
   (let
    [result__62699__auto__ (clojure.core.async/<!! ch__62696__auto__)]
    (if-let
     [error__62700__auto__ (:error result__62699__auto__)]
     (throw
      (ex-info
       (str
        "Error in command "
        method__62695__auto__
        ": "
        (:message error__62700__auto__))
       {:request payload__62697__auto__, :error error__62700__auto__}))
     (:result result__62699__auto__))))))

(s/fdef
 get-database-table-names
 :args
 (s/or
  :no-args
  (s/cat)
  :just-params
  (s/cat
   :params
   (s/keys
    :req-un
    [::database-id]))
  :connection-and-params
  (s/cat
   :connection
   (s/?
    c/connection?)
   :params
   (s/keys
    :req-un
    [::database-id])))
 :ret
 (s/keys
  :req-un
  [::table-names]))
