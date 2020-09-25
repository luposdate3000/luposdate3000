#!/bin/bash
for f in src-generate-buildfile/template*
do
	echo $f
	./generate-buildfile.kts --file=$f
	./tool-gradle-build.sh
	ret=$?
	if [ $ret -ne 0 ]
	then
		echo x
#		exit $ret
	fi
done
