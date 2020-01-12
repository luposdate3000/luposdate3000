#!/bin/bash
for f in $(find -name "*.kt")
do
	f2=$(echo $f | sed "s-.*/src/-/-g")
	f2=$(echo $f2 | sed "s-/main/-/-g")
	f2=$(echo $f2 | sed "s-/test/-/-g")
	f2=$(echo $f2 | sed "s-/kotlin/-/-g")
	f2=$(echo $f2 | sed "s-/java/-/-g")
	f2=$(echo $f2 | sed "s-/[^/]*\$--g")
	f2=$(echo $f2 | sed "s-^/--g")
	p=$(grep "package" $f)
	p=$(echo $p | sed "s/package //g")
	p=$(echo $p | sed "s-\.-/-g")
	if [ "$f2" != "$p" ]
	then
		echo "$f"
		echo "$f2"
		echo "$p"
		echo ""
	fi
done
