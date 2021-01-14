#!/bin/bash
find . -type f -name "*.kts" > tmp2
find . -type f -name "*.kt" >> tmp2
for f in $(cat tmp2 | grep -v ".git" | grep -v build-cache)
do
        cat $f | grep "^#!" > tmp3
        cat $f | grep "^@file" >> tmp3
        cat $f | grep "^package " >> tmp3
        cat $f | grep "^import " | sort | uniq >>tmp3
        cat $f | grep -v "^#!" | grep -v "^@file" | grep -v "^package " | grep -v "^import " | egrep -v "^[[:space:]]*$|^#" >>tmp3
        mv tmp3 $f
        ktlint -F $f &
done
wait
rm tmp2
