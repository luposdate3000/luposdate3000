#!/bin/bash
./tool-format-sort-imports.sh
for f in $(find src.generated -type f -name "*.kt")
do
cat $f | egrep -v "^[[:space:]]*$|^#" > $f.tmp
mv $f.tmp $f
done
/opt/idea-IC-201.7846.76/bin/format.sh $(find src.generated -type f -name "*.kt" | grep -v "src.generated/commonConfig" ) $(find . -type f -name "*.kts" | grep -v "src.generated/commonConfig")
