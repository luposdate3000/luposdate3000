#!/bin/bash
./tool-format-sort-imports.sh
for f in $(find src -type f -name "*.kt")
do
cat $f | egrep -v "^[[:space:]]*$|^#" > $f.tmp2
mv $f.tmp2 $f
done
/opt/idea-IC-201.7846.76/bin/format.sh $(find src -type f -name "*.kt" | grep -v "src/commonConfig" ) $(find . -type f -name "*.kts" | grep -v "src/commonConfig")
