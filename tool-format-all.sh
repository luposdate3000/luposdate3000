#!/bin/bash
for f in $(find . -type f -name "*.kt" | grep -v "/korio/" | grep -v "/build-cache/")
do
        cat $f | grep "^package " > tmp2
        cat $f | grep "^import " | sort | uniq >>tmp2
        cat $f | grep -v "^package " | grep -v "^import " >>tmp2
        cat tmp2 | egrep -v "^[[:space:]]*$|^#" > $f
done
rm tmp2
/opt/idea-IC-201.7846.76/bin/format.sh $(find . -type f -name "*.kt" | grep -v "/korio/" | grep -v "/build-cache/") $(find . -type f -name "*.kts" | grep -v "/korio/" | grep -v "/build-cache/")
