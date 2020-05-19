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
echo 0,x1.sparql,x1.ttl,x1.srx >> config.csv2
echo 0,x2.sparql,x2.ttl,x2.srx >> config.csv2

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

