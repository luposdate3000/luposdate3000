#!/bin/bash

for f in $(find src/ -name "*.kt")
do
	cat $f | grep "^package " > tmp2
	cat $f | grep "^import " | sort | uniq >>tmp2
	cat $f | grep -v "^package " | grep -v "^import " >>tmp2
	mv tmp2 $f
done
