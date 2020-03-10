#!/bin/bash
pkill java

rm y

benchmarkMinimumTime=1000

./tool-gradle-build.sh

for triples in 100 1000 10000 20000 30000 40000 50000 100000 500000
do

p=$(pwd)/benchmark_results/sp2b
mkdir -p $p
triplesfile=$p/sp2b-${triples}.n3
(
	cd /opt/sp2b/bin
	# t parameter specifies amount of tupels
	./sp2b_gen -t $triples
)
cat /opt/sp2b/bin/sp2b.n3 | sed "s/\.$/ ./g" > "$triplesfile"

csvglobal=$p/all.csv
(
	cd resources/sp2b
	l="title,triples"
	for f in *.sparql
	do
		l="$l,$f"
	done
	echo $l >> "$csvglobal"
)
benchmarkJena(){
csvfile=$p/fuseki-server-${triples}.csv
echo "query,repititions,time,query per second" > "$csvfile"
/opt/apache-jena-fuseki-3.14.0/fuseki-server &
pid=$!
sleep 3
curl -X POST --data-urlencode "dbName=sp2b" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets > /dev/null 2>&1
curl -X POST -d "@${triplesfile}" -H "Content-Type: text/turtle" localhost:3030/sp2b/data > /dev/null 2>&1
(
	l="fuseki-server,${triples}"
	cd resources/sp2b
	for f in *.sparql
	do
		a=$(($(date +%s%N)/1000000))
		n=1
		while true
		do
			curl -X POST --data-urlencode "query=$(cat $f)" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/sp2b/query > /dev/null 2>&1
			b=$(($(date +%s%N)/1000000))
			c=$((b - a))
			if((c > benchmarkMinimumTime))
			then
				qps=$(bc <<< "scale=2;$n * 1000 / $c")
				echo "$f,$n,$c,${qps}" >> $csvfile
				l="$l,${qps}"
				break
			fi
			n=$((n + 1))
		done
	done
	echo $l >> "$csvglobal"
)
kill $pid
}

benchmarkLuposdate3000(){
csvfile=$p/luposdate3000-${triples}.csv
echo "query,repititions,time,query per second" > "$csvfile"
./build/executable 127.0.0.1 &
pid=$!
sleep 3
curl -X POST --data-binary "@${triplesfile}" http://localhost:80/import/turtle --header "Content-Type:text/plain"
(
	l="luposdate3000,${triples}"
	cd resources/sp2b
	for f in *.sparql
	do
		a=$(($(date +%s%N)/1000000))
		n=1
		while true
		do
			curl -X POST --data-binary "@$f" http://localhost:80/sparql/query > /dev/null 2>&1
			b=$(($(date +%s%N)/1000000))
			c=$((b - a))
			if((c > benchmarkMinimumTime))
			then
				qps=$(bc <<< "scale=2;$n * 1000 / $c")
				echo "$f,$n,$c,${qps}" >> $csvfile
				l="$l,${qps}"
				break
			fi
			n=$((n + 1))
		done
	done
	echo $l >> "$csvglobal"
)
kill $pid
}

kotlinc -script generate-buildfile.kts jvm commonS00LaunchEndpointMain commonS00SanityChecksOffMain commonS00ResultFlowFastMain commonS00ExecutionParallelMain commonS01HeapMain commonS03DictionaryIntArrayMain commonS12DummyMain jvmS14ServerKorioMain commonS14ClientNoneMain commonS15DistributedMain
./tool-gradle-build.sh
#benchmarkJena > /dev/null 2>&1
benchmarkLuposdate3000
done
