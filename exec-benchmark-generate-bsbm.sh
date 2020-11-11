#!/bin/bash

products=1
while true
do
	productsfolder=/mnt/luposdate-testdata/bsbm/${products}
	rm -rf $productsfolder
	mkdir -p $productsfolder
	triplesfile=${productsfolder}/complete.n3
	(
		cd /opt/bsbmtools-0.2
		./generate -s ttl -pc $products > /dev/null 2>&1
		mv /opt/bsbmtools-0.2/dataset.ttl $triplesfile
	)
	size=$(du -sb ${triplesfile} | sed -E "s/([0-9]+).*/\1/g")
	./exec-import.sh ${triplesfile}
	countBytes=$(du -sb ${triplesfile}.triples | cut -f1)
	count=$((countBytes/12))
	sizeIntermediate=$(du -sbc ${triplesfile}.* | grep total | cut -f1)
	echo "$triples,$count,$size,$sizeIntermediate">>/mnt/luposdate-testdata/bsbm/stat.csv
        products=$(($products * 2))
	if [[ $products -le 0 ]]
	then
		break
	fi
	if [[ $products -gt 2097152 ]]
	then
		break
	fi
done
