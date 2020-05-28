#!/bin/bash

triples=1024
while true
do
	triplesfolder=/mnt/sp2b-testdata/${triples}
	rm -rf $triplesfolder
	mkdir -p $triplesfolder
	triplesfile=${triplesfolder}/complete.n3
	(
		cd /opt/sp2b/bin
		./sp2b_gen -t $triples > /dev/null 2>&1
	)
	size=$(du -sb /opt/sp2b/bin/sp2b.n3 | sed -E "s/([0-9]+).*/\1/g")
	count=$(cat /opt/sp2b/bin/sp2b.n3 | ./exec-compress-chunked-n3.kts $triplesfolder)
	rm $triplesfolder/bnodes.tmp
	for f in $(find $triplesfolder -name "*.bnodes")
	do
		sort $f -u >> $triplesfolder/bnodes.tmp
		rm $f
	done
	sort $triplesfolder/bnodes.tmp -u > $triplesfolder/bnodes.txt
	rm $triplesfolder/bnodes.tmp
	bnodecount=$(wc -l $triplesfolder/data0.n3 | sed "s/ .*//g")
	compressed=$(du -sbc $triplesfolder/*.n3 | grep total | sed 's/\t.*//g')
	echo "$triples,$count,$size,$compressed,$bnodecount">>/mnt/sp2b-testdata/stat.csv
	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
	if [[ $triples -ge 1000000000 ]]
	then
		break
	fi
done
