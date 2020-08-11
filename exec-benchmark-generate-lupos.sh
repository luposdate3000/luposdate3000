#!/bin/bash

triples=1024
triples=16777216

function generate(){
	pattern=lupos_$2T$3T$4
	triplesfolder=/mnt/luposdate-testdata/${pattern}/$1
	rm -rf $triplesfolder
#	mkdir -p $triplesfolder
#	./generate-benchmark-data.kts $1 $2 $3 $4 > /mnt/luposdate-testdata/${pattern}/x
#	size=$(du -sb /mnt/luposdate-testdata/${pattern}/x | sed -E "s/([0-9]+).*/\1/g")
#	count=$(cat /mnt/luposdate-testdata/${pattern}/x | ./exec-compress-chunked-n3.kts $triplesfolder)
#	rm $triplesfolder/bnodes.tmp
#	for f in $(find $triplesfolder -name "*.bnodes")
#	do
#		sort $f -u >> $triplesfolder/bnodes.tmp
#		rm $f
#	done
#	sort $triplesfolder/bnodes.tmp -u > $triplesfolder/bnodes.txt
#	rm $triplesfolder/bnodes.tmp

	count=$(./generate-benchmark-data.kts $1 $2 $3 $4 $triplesfolder)
	size=$(du -sbc $triplesfolder/*.n3 | grep total | sed 's/\t.*//g')

	bnodecount=$(wc -l $triplesfolder/bnodes.txt | sed "s/ .*//g")
	compressed=$(du -sbc $triplesfolder/*.n3 | grep total | sed 's/\t.*//g')
	echo "$1,$count,$size,$compressed,$bnodecount">>/mnt/luposdate-testdata/${pattern}/stat.csv
}

while true
do

	generate $triples 4 1 0
	generate $triples 4 1 1
	generate $triples 4 1 32
	generate $triples 4 1 128
	generate $triples 4 1 1024

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
