#!/bin/bash
find ./ -type f \( -iname \*.clj -o -iname \*.html -o -iname \*.coffee \) | xargs sed -i '' -e "s/webstack/$1/"
mv server/src/webstack server/src/$1
mv server/test/webstack server/test/$1
