#!/bin/bash
touch config.csv2
rm config.csv2

for i in $(seq -f "%03g" 1 16)
do
	echo $i.sparql
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

