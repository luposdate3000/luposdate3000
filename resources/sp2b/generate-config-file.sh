#!/bin/bash
touch config.csv2
rm config.csv2

for triples2 in 1 200 500 900 1000 1400 32768
do
	(
		cd /opt/sp2b/bin
		./sp2b_gen -t $triples2 > /dev/null 2>&1
	)
	mkdir tmpdata
	cat /opt/sp2b/bin/sp2b.n3 | ./../../exec-compress-chunked-n3.kts tmpdata
	mv tmpdata/data0.n3 sp2b-$triples2.n3
	rm -rf tmpdata
	triples=$(wc -l sp2b-$triples2.n3 | sed "s/ .*//g")
	mv sp2b-$triples2.n3 sp2b-$triples.n3
	for q in $(find -name "q*.sparql" | sed "s/.sparql//g" | sed "s/.*q/q/g")
	do
		echo $triples,$q.sparql,sp2b-$triples.n3,$q-$triples.srx >> config.csv2
	done
done

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

