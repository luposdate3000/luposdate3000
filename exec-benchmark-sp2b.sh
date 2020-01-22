#!/bin/bash
pkill java

triples=100

p=$(pwd)/benchmark_results/sp2b
mkdir -p $p
csvfile=$p/fuseki-server-${triples}.csv
echo "query,repititions,time,query per second" > "$csvfile"
triplesfile=$p/sp2b-${triples}.n3
(
	cd /opt/sp2b/bin
	# t parameter specifies amount of tupels
	./sp2b_gen -t $triples
)
cat /opt/sp2b/bin/sp2b.n3 | sed "s/\.$/ ./g" > "$triplesfile"

pkill java
sleep 3

/opt/apache-jena-fuseki-3.14.0/fuseki-server &

sleep 3

curl -X POST --data-urlencode "dbName=sp2b" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets > tmp 2>&1

curl -X POST -d "@${triplesfile}" -H "Content-Type: text/turtle" localhost:3030/sp2b/data > tmp 2>&1

(
	cd common/src/main/resources/sp2b
	for f in *.sparql
	do
		a=$(($(date +%s%N)/1000000))
		n=1
		while true
		do
			curl -X POST --data-urlencode "query=$(cat $f)" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/sp2b/query > tmp 2>&1
			b=$(($(date +%s%N)/1000000))
			c=$((b - a))
			if((c > 1000))
			then
				qps=$(bc <<< "scale=2;$n * 1000 / $c")
				echo "$f,$n,$c,${qps}" >> $csvfile
				break
			fi
			n=$((n + 1))
		done
	done
)
pkill java
