#!/bin/bash
./generate-buildfile.kts --file=src-generate-buildfile/template-exec-import
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]
then
	exit $ret
fi
cpus=$( ls -d /sys/devices/system/cpu/cpu[[:digit:]]* | wc -w )

### configure all the files here -->>
find /mnt/luposdate-testdata/sp2b/ -mindepth 1 -maxdepth 1 -type d > exec-import.sh.tmp
find /mnt/luposdate-testdata/bsbm/ -mindepth 1 -maxdepth 1 -type d >> exec-import.sh.tmp
find /mnt/luposdate-testdata/lupos*/ -mindepth 1 -maxdepth 1 -type d >> exec-import.sh.tmp
###

for directory in $(cat exec-import.sh.tmp | awk '{ print system("du -sb "$0" | sed \"s/[^0-9].*//g\" | tr -d \"\n\""), $0 }' | sort -n | cut -d" " -f2-)
do
	echo $directory
	if test -f "$directory/intermediate.stat"
	then
		echo "skip because it is done"
		continue
	fi
	rm -rf $directory/out
	find $directory -name "*.n3" | xargs --max-args=1 --max-procs=$cpus ./build/executable IMPORT_STRING
	find $directory -name "*.n3" | xargs --max-args=4 --max-procs=$cpus ./build/executable MERGE_INTERMEDIATE
	while true
	do
		remaining=$(ls -la $directory/out/ | wc -l)
		echo $remaining
		if [ "$remaining" -le "10" ]
		then
			break
		fi
		find $directory/out -name "*.n3" | xargs --max-args=4 --max-procs=$cpus ./build/executable MERGE_INTERMEDIATE
		rm $directory/out/*
		mv $directory/out/out/* $directory/out
		rm -rf $directory/out/out/
	done
	rm $directory/*.n3.triples $directory/*.n3.stat $directory/*.n3.dictionary
	mv $directory/out/out_*.n3.triples $directory/intermediate.triples
	mv $directory/out/out_*.n3.dictionary $directory/intermediate.dictionary
	mv $directory/out/out_*.n3.stat $directory/intermediate.stat
	rm -rf $directory/out
done
rm exec-import.sh.tmp
