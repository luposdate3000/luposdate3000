#!/bin/bash
touch config.csv2
rm config.csv2

echo $(wc -l x1.ttl | sed "s/ .*//g"),x1.sparql,x1.ttl,x1.srx >> config.csv2
echo $(wc -l x2.ttl | sed "s/ .*//g"),x2.sparql,x2.ttl,x2.srx >> config.csv2
echo $(wc -l x3.ttl | sed "s/ .*//g"),x3.sparql,x3.ttl,x3.srx >> config.csv2
echo $(wc -l x4.ttl | sed "s/ .*//g"),x4.sparql,x4.ttl,x4.srx >> config.csv2
echo $(wc -l x5.ttl | sed "s/ .*//g"),x5.sparql,x5.ttl,x5.srx >> config.csv2
echo $(wc -l x6.ttl | sed "s/ .*//g"),x6.sparql,x6.ttl,x6.srx >> config.csv2
echo $(wc -l x7.ttl | sed "s/ .*//g"),x7.sparql,x7.ttl,x7.srx >> config.csv2

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

