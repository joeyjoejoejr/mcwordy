(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers)))

(defmigration add-users-table
  (up []
      (create
       (tbl :user
            (varchar :username 50 :unique)
            (check :username (> (length :username) 1))

            (varchar :display_name 255)

            (varchar :email 255 :unique)
            (check :email (> (length :email) 1))

            (varchar :password 255)

            (text :roles)
            (text :about)))
      (create (index :user [:username]))
      (create (index :user [:email])))
  (down [] (drop (table :user))))

(defmigration add-mooshes-table
  (up [] (create
          (tbl :moosh
               (text :words)
               (text :response)
               (refer-to :user))))
  (down [] (drop (table :moosh))))

(defmigration add-sessions-table
  (up [] (create
          (tbl :user_session
               (varchar :key 255 :unique)
               (text :data)))
      (create (index :user_session [:key])))
  (down [] (drop (table :user_session))))