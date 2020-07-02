#!/bin/bash
touch config.csv2
rm config.csv2

for i in $(seq 1 7)
do
echo $(wc -l x${i}.ttl | sed "s/ .*//g"),x${i}.sparql,x${i}.ttl,x${i}.srx >> config.csv2
done
for f in $(find . -name "ygraphic*.sparql"| sed "s-./--g")
do
echo $(wc -l ygraphic.ttl | sed "s/ .*//g"),$f,ygraphic.ttl,ygraphic_$(echo $f | sed "s/sparql/srx/g") >> config.csv2
echo $(wc -l ygraphic2.ttl | sed "s/ .*//g"),$f,ygraphic2.ttl,ygraphic2_$(echo $f | sed "s/sparql/srx/g") >> config.csv2
done

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

