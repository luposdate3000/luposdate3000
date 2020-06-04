#!/bin/bash
touch config.csv2
rm config.csv2

for triples in 1 200 500 700 900 1000 1400
do
	(
		cd /opt/sp2b/bin
		./sp2b_gen -t $triples > /dev/null 2>&1
	)
	cat /opt/sp2b/bin/sp2b.n3 | sed "s/.$/ ./g" | sed "s/  .$/ ./g" > sp2b-$triples.n3
	for q in $(find -name "q*.sparql" | sed "s/.sparql//g" | sed "s/.*q/q/g")
	do
		echo $triples,$q.sparql,sp2b-$triples.n3,$q-$triples.srx >> config.csv2
	done
done

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

