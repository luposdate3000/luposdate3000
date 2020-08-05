#!/bin/bash

rm $1/*.javap
for f in $(find $1 -name "*.class")
do
	javap -c $f > $(echo $f | sed 's/\$/_/g').javap
done
