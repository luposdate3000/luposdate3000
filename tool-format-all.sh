#!/bin/bash
for f in $(find . -type f -name "*.kt" | grep -v "/build-cache/")
do
        cat $f | grep "^@file" > tmp2
        cat $f | grep "^package " >> tmp2
        cat $f | grep "^import " | sort | uniq >>tmp2
        cat $f | grep -v "^@file" | grep -v "^package " | grep -v "^import " | egrep -v "^[[:space:]]*$|^#" >>tmp2
        mv tmp2 $f
done
ktlint -F "*.kt"
ktlint -F "*.kts"
