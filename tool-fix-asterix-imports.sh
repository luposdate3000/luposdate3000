#!/bin/bash

changed=1
echo "using tool-gradle-build.sh a"
failed=$(./tool-gradle-build.sh 2>&1 | grep "BUILD FAILED in ")
while [[ $changed == 1 ]]
do
	changed=0
	if [ -z "$failed" ]
	then
		echo success
		for f in $(grep -rw "^import" --include "*.kt" src | grep -F "*" | sed "s/:import.*//g" | sort | uniq)
		do
			echo $f
			for i in $(grep '^import' $f | grep -F '*' | sed "s/import //g")
			do
				echo $i
				cat $f | grep -v -F "import $i" > tmp2
				mv tmp2 $f
				grep "^package " $f > tmp2
				echo "using tool-gradle-build.sh b"
				for e in $(./tool-gradle-build.sh 2>&1 | grep "^e: " | grep "Unresolved reference: " | sed "s/.*Unresolved reference: //g" | sort | uniq)
				do
					echo "import ${i::-1}$e" >> tmp2
					echo $e
				done
				echo "using tool-gradle-build.sh e"
				changed=1
				grep -v "^package " $f >> tmp2
				mv tmp2 $f
				failed=$(./tool-gradle-build.sh 2>&1 | grep "BUILD FAILED in ")
echo $failed
echo $changed
				break 2
			done
		done
	fi
done
