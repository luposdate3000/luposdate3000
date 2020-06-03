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
echo 0,x3.sparql,x3.ttl,x3.srx >> config.csv2
echo 0,x4.sparql,x4.ttl,x4.srx >> config.csv2
echo 0,x5.sparql,x5.ttl,x5.srx >> config.csv2
echo 0,x6.sparql,x6.ttl,x6.srx >> config.csv2
echo 0,x7.sparql,x7.ttl,x7.srx >> config.csv2

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

