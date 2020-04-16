#!/bin/bash

triples=1000
while true
do
	triplesfolder=/mnt/sp2b-testdata/${triples}
	mkdir -p triplesfolder
	triplesfile=${triplesfolder}/complete.n3
	(
		cd /opt/sp2b/bin
		./sp2b_gen -t $triples
	)
	cat /opt/sp2b/bin/sp2b.n3 | sed "s/\.$/ ./g" > $triplesfile
	size=$(du -sb $triplesfile | sed -E "s/([0-9]+).*/\1/g")

	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
done
