#!/bin/bash

triples=1000
while true
do
	triplesfolder=/mnt/sp2b-testdata/${triples}
	mkdir -p $triplesfolder
	triplesfile=${triplesfolder}/complete.n3
	(
		cd /opt/sp2b/bin
		./sp2b_gen -t $triples > /dev/null 2>&1
	)
	size=$(du -sb /opt/sp2b/bin/sp2b.n3 | sed -E "s/([0-9]+).*/\1/g")
	cat /opt/sp2b/bin/sp2b.n3 | ./exec-compress-chunked-n3.kts $triplesfolder
	echo "$triples Triples - $size Bytes"

	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
done
