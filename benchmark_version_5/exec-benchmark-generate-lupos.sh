#!/bin/bash


function generate(){
	pattern=lupos_$2T$3T$4
	triplesfolder=/mnt/luposdate-testdata/${pattern}/$1
	rm -rf $triplesfolder

	count=$(./generate-benchmark-data.main.kts $1 $2 $3 $4 $triplesfolder)
	size=$(du -sbc $triplesfolder/*.n3 | grep total | sed 's/\t.*//g')

	bnodecount=$(wc -l $triplesfolder/bnodes.txt | sed "s/ .*//g")
	compressed=$(du -sbc $triplesfolder/*.n3 | grep total | sed 's/\t.*//g')
	echo "$1,$count,$size,$compressed,$bnodecount">>/mnt/luposdate-testdata/${pattern}/stat.csv
}

for t in 32 128 512 2048 8196 32768 131072 524288 2097152
do
	for s in $(seq 0 40)
	do
		generate $t 2 1 $s
	done

done
