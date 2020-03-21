#!/bin/bash
./tool-format-sort-imports.sh
for f in $(find src -type f -name "*.kt")
do
cat $f | egrep -v "^[[:space:]]*$|^#" > $f.tmp
mv $f.tmp $f
done
/opt/idea-IC-193.5662.53/bin/format.sh $(find src -type f -name "*.kt")
/opt/idea-IC-193.5662.53/bin/format.sh $(find . -type f -name "*.kts")
