#!/bin/bash

products=1
while true
do
	productsfolder=/mnt/luposdate-testdata/bsbm/${products}
	rm -rf $productsfolder
	mkdir -p $productsfolder
	productsfile=${productsfolder}/complete.n3
	(
		cd /opt/bsbmtools-0.2
		./generate -s ttl -pc $products > /dev/null 2>&1
	)
	size=$(du -sb /opt/bsbmtools-0.2/dataset.ttl | sed -E "s/([0-9]+).*/\1/g")
	count=$(cat /opt/bsbmtools-0.2/dataset.ttl | ./exec-compress-chunked-n3.kts $productsfolder)
	rm $productsfolder/bnodes.tmp
	for f in $(find $productsfolder -name "*.bnodes")
	do
		sort $f -u >> $productsfolder/bnodes.tmp
		rm $f
	done
	sort $productsfolder/bnodes.tmp -u > $productsfolder/bnodes.txt
	rm $productsfolder/bnodes.tmp
	bnodecount=$(wc -l $productsfolder/bnodes.txt | sed "s/ .*//g")
	compressed=$(du -sbc $productsfolder/*.n3 | grep total | sed 's/\t.*//g')
	echo "$products,$count,$size,$compressed,$bnodecount">>/mnt/luposdate-testdata/bsbm/stat.csv
	products=$(($products * 2))
	if [[ $products -le 0 ]]
	then
		break
	fi
	if [[ $products -ge 1000000000 ]]
	then
		break
	fi
done
