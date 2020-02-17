#!/bin/bash

changed=1
./tool-fix-asterix-imports.sh
while [[ $changed == 1 ]]
do
	changed=0
	failed=$(./tool-gradle-build-without-tests-jvm-all.sh 2>&1 | grep "BUILD FAILED in ")
	if [ -z "$failed" ]
	then
		echo success
		for f in $(grep -rw "^import" --include "*.kt" src | sed "s/ /./g"| sort | uniq)
		do
			imp=$(echo $f | sed "s/.*\.//g")
			file=$(echo $f | sed "s/:.*//g")
			count=$(cat $file | grep -v "^import" | grep $imp | wc -l)
			if [ "$count" -eq "0" ]; then
				echo $file
				echo $imp
				echo $count
				cat $file | grep -v "import.*$imp" > tmp3
				mv tmp3 $file
				changed=1
			fi
		done
	fi
done
