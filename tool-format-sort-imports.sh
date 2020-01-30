#!/bin/bash

for f in $(find src/ -name "*.kt")
do
	cat $f | grep "^package " > tmp
	cat $f | grep "^import " | sort | uniq >>tmp
	cat $f | grep -v "^package " | grep -v "^import " >>tmp
	mv tmp $f
done
