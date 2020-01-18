#!/bin/bash

changed=1

while [[ $changed == 1 ]]
do
	changed=0
	failed=$(./tool-build-without-tests.sh 2>&1 | grep "BUILD FAILED in ")
	if [ -z "$failed" ]
	then
		echo success
		for f in $(grep -rw "^import" --include "*.kt" --exclude-dir=".git" --exclude-dir="korio" --exclude-dir="build" | grep -F "*" | sed "s/:import.*//g" | sort | uniq)
		do
			echo $f
			for i in $(grep '^import' $f | grep -F '*' | sed "s/import //g")
			do
				echo $i
				cat $f | grep -v -F "import $i" > tmp
				mv tmp $f
				grep "^package " $f > tmp
				for e in $(./tool-build-without-tests.sh 2>&1 | grep "^e: " | grep "Unresolved reference" | sed "s/.*Unresolved reference: //g" | sort | uniq)
				do
					echo "import ${i::-1}$e" >> tmp
					echo $e
				done
				grep -v "^package " $f >> tmp
				mv tmp $f
				changed=1
				break 2
			done
		done
	fi
done
