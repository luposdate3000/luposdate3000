#!/bin/bash

triples=2
while true
do

for j in 1 2 3 4
do
for k in 1 2 3 4
do
if [ "$k" -lt "$j" ]
then
continue
fi
	triplesfolder=/mnt/luposdate-testdata/lupos_${j}T${k}/${triples}
	rm -rf $triplesfolder
	mkdir -p $triplesfolder
	triplesfile=${triplesfolder}/complete.n3
	./generate-benchmark-data.kts $triples 1 1 > /mnt/luposdate-testdata/lupos_${j}T${k}/x
	size=$(du -sb /mnt/luposdate-testdata/lupos_${j}T${k}/x | sed -E "s/([0-9]+).*/\1/g")
	count=$(cat /mnt/luposdate-testdata/lupos_${j}T${k}/x | ./exec-compress-chunked-n3.kts $triplesfolder)
	rm $triplesfolder/bnodes.tmp
	for f in $(find $triplesfolder -name "*.bnodes")
	do
		sort $f -u >> $triplesfolder/bnodes.tmp
		rm $f
	done
	sort $triplesfolder/bnodes.tmp -u > $triplesfolder/bnodes.txt
	rm $triplesfolder/bnodes.tmp
	bnodecount=$(wc -l $triplesfolder/bnodes.txt | sed "s/ .*//g")
	compressed=$(du -sbc $triplesfolder/*.n3 | grep total | sed 's/\t.*//g')
	echo "$triples,$count,$size,$compressed,$bnodecount">>/mnt/luposdate-testdata/lupos_${j}T${k}/stat.csv
done
done
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
