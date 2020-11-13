#!/bin/bash
for f in src/generateBuildfile/template*
do
	echo $f
	./generate-buildfile.main.kts --file=$f
	./tool-gradle-build.sh
	ret=$?
	if [ $ret -ne 0 ]
	then
		echo x
#		exit $ret
	fi
done
