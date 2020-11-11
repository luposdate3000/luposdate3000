#!/bin/bash

triples=1024
while true
do
	triplesfolder=/mnt/luposdate-testdata/sp2b/${triples}
	rm -rf $triplesfolder
	mkdir -p $triplesfolder
	triplesfile=${triplesfolder}/complete.n3
	(
		cd /opt/sp2b/bin
		./sp2b_gen -t $triples > /dev/null 2>&1
		mv /opt/sp2b/bin/sp2b.n3 ${triplesfile}
	)
	size=$(du -sb ${triplesfile} | sed -E "s/([0-9]+).*/\1/g")
	./exec-import.sh ${triplesfile}
	countBytes=$(du -sb ${triplesfile}.triples | cut -f1)
	count=$((countBytes/12))
	sizeIntermediate=$(du -sbc ${triplesfile}.* | grep total | cut -f1)
	echo "$triples,$count,$size,$sizeIntermediate">>/mnt/luposdate-testdata/sp2b/stat.csv
	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
	if [[ $triples -gt 536870912 ]]
	then
		break
	fi
done
