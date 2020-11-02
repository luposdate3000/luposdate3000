#!/bin/bash

total=$(grep -rw "^import" --include "*.kt" src | sed "s/ /./g"| sort | uniq | wc -l)
i=0
for f in $(grep -rw "^import" --include "*.kt" src | sed "s/ /./g"| sort | uniq)
do
	i=$(($i+1))
	echo "$i :: $total"
	imp=$(echo $f | sed "s/.*\.//g")
	file=$(echo $f | sed "s/:.*//g")
	count=$(cat $file | grep -v "^import" | grep $imp | wc -l)
	asterixCount=$(cat $file | grep -i "import .*\*" | wc -l)
	if [ "$asterixCount" -eq "0" ]; then
		if [ "$count" -eq "0" ]; then
			echo $file
			echo $imp
			echo $count
			cat $file | grep -v "import.*$imp" > tmp3
			mv tmp3 $file
		fi
	fi
done
