for f in src-generate-buildfile/template*
do
	echo $f
	cat $f | ./generate-buildfile.kts
	./tool-gradle-build.sh
	ret=$?
	if [ $ret -ne 0 ]
	then
		exit $ret
	fi
done
