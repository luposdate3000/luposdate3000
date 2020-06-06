#!/bin/bash

triples=1024
while true
do
	triplesfolder=/mnt/luposdate-testdata/bsbm/${triples}
	rm -rf $triplesfolder
	mkdir -p $triplesfolder
	triplesfile=${triplesfolder}/complete.n3
	(
		cd /opt/bsbmtools-0.2
		./generate -s ttl -pc $triples > /dev/null 2>&1
	)
	size=$(du -sb /opt/bsbmtools-0.2/dataset.ttl | sed -E "s/([0-9]+).*/\1/g")
	count=$(cat /opt/bsbmtools-0.2/dataset.ttl | ./exec-compress-chunked-n3.kts $triplesfolder)
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
	echo "$triples,$count,$size,$compressed,$bnodecount">>/mnt/luposdate-testdata/bsbm/stat.csv
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
