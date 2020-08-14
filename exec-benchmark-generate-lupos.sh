#!/bin/bash


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

for t in 1024 8192 65536 524288 4194304
do
	for s in $(seq 0 20)
	do
		generate $t 2 1 $s
	done

done
