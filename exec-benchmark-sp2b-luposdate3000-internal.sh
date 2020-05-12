#!/bin/bash
export JAVA_OPTS="-Xmx30g"

triples=1024

rm -rf log/benchtmp
mkdir -p log/benchtmp

./generate-buildfile.kts 1.4.255-SNAPSHOT jvm Benchmark Off Fast Sequential Heap MultiMap BPlusTree Dummy Korio None Local Off BTree BTree None Empty 8196 1024 512 512
./tool-gradle-build.sh
ln -s $(readlink -f build/executable) log/benchtmp/Single_BTree_Empty.x

versions=( "Single_BTree_Empty" )

p=$(pwd)/benchmark_results/sp2b
mkdir -p $p


#find resources/sp2b/ -name "*.sparql" > log/queries
echo "resources/sp2b/q1.sparql" > log/queries
echo "resources/sp2b/q3a.sparql" >> log/queries
echo "resources/sp2b/q3b.sparql" >> log/queries
echo "resources/sp2b/q3c.sparql" >> log/queries
echo "resources/sp2b/q6.sparql" >> log/queries
echo "resources/sp2b/q10.sparql" >> log/queries
echo "resources/sp2b/q12a.sparql" >> log/queries
echo "resources/sp2b/q12b.sparql" >> log/queries
echo "resources/sp2b/q12c.sparql" >> log/queries

#!!!!!!!!!!!!
echo "resources/sp2b/q12c.sparql" > log/queries
#!!!!!!!!!!!!

export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64

for version in "${versions[@]}"
do
	cp log/queries log/benchtmp/$version.queries
done
while true
do
	triplesfolder=/mnt/sp2b-testdata/${triples}
	size=$(du -sbc ${triplesfolder}/*.n3 | grep total | sed 's/\t.*//g')
	i=0
	for version in "${versions[@]}"
	do
		queries=$(paste -s -d ';' log/benchtmp/$version.queries)
		if [ -n "$queries" ]
		then
			if [ $i -eq 0 ]
			then
				./log/benchtmp/$version.x "IMPORT" "$triplesfolder/data" "$(find $triplesfolder/*.n3 | paste -s -d ';')" "$queries" "10" "$triples" "$size" > log/benchtmp/x
			else
				./log/benchtmp/$version.x "IMPORT" "$triplesfolder/data" "$(find $triplesfolder/*.n3 | paste -s -d ';')" "$queries" "10" "$triples" "$size" > log/benchtmp/x
#				./log/benchtmp/$version.x "LOAD" "$triplesfolder/data" "" "$queries" "10" "$triples" "$size" > log/benchtmp/x
			fi
			cat log/benchtmp/x | grep "sparql,$triples," >> $p/luposdate-$version-$(git rev-parse HEAD)-internal.csv
			cat log/benchtmp/x | grep "sparql,$triples," | grep -v "sparql,$triples,0,.," | sed "s/,.*//" > log/benchtmp/$version.queries
		fi
		i=1
	done
	triples=$(($triples * 2))
	if [[ $triples -le 0 ]]
	then
		break
	fi
done
rm -rf log/benchtmp
