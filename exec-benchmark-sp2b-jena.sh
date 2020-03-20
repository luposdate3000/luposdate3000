#!/bin/bash

#in milliseconds
timemin=10000
#in seconds
timeout=120
triples=1000
p=$(pwd)/benchmark_results/sp2b
mkdir -p $p
rm log/queries2
find resources/sp2b/ -name "*.sparql" > log/queries
while true
do
triplesfile=$p/sp2b-${triples}.n3
csvfile=$p/jena.csv
(
        cd /opt/sp2b/bin
        # t parameter specifies amount of tupels
        ./sp2b_gen -t $triples > /dev/null 2>&1
)
cat /opt/sp2b/bin/sp2b.n3 | sed "s/\.$/ ./g" > "$triplesfile"
pkill java
sleep 3
(/opt/apache-jena-fuseki-3.14.0/fuseki-server > /dev/null 2>&1)&
sleep 3
curl -X POST --data-urlencode "dbName=sp2b" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets > /dev/null 2>&1
curl -X POST -d "@${triplesfile}" -H "Content-Type: text/turtle" localhost:3030/sp2b/data > /dev/null 2>&1
while read query; do
	a=$(($(date +%s%N)/1000000))
	n=1
	while true
	do
		timeout -s SIGTERM "${timeout}s" curl -X POST --data-urlencode "query=$(cat $query)" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/sp2b/query > /dev/null 2>&1
		code=$?
		b=$(($(date +%s%N)/1000000))
		c=$((b - a))
		if((c > timemin))
		then
			qps=$(bc <<< "scale=2;$n * 1000 / $c")
			echo "$query,$triples,$code,$n,$c,${qps}" >> $csvfile
			if [[ "$code" == "0" ]]
			then
				echo $query >> log/queries2
			fi
			break
		fi
		n=$((n + 1))
	done
	echo "$query"
done < log/queries
mv log/queries2 log/queries
triples=$(($triples * 2))
done
