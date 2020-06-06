#!/bin/bash

for benchmark in sp2b bsbm
do
mkdir tmp
for query in $(cat benchmark_results/$benchmark/*.csv | sed "s/sparql.*/sparql/g" | sort | uniq | sed "s-resources/$benchmark/--g")
do
	for f in $(find benchmark_results/$benchmark -name "*.csv")
	do
		target=$(echo $f | sed "s#benchmark_results/$benchmark/#tmp/$query.#g")
		touch $target
		truncate -s0 $target
		for l in $(cat $f  | grep $query)
		do
			number=$(echo $l | sed "s/.*sparql,//g" | sed "s/,.*//g")
			triples=$(cat /mnt/luposdate-testdata/$benchmark/stat.csv | grep "^$number" | sed "s/^$number,//g" | sed "s/,.*//g")
			echo $l | sed "s/.sparql,$number/.sparql,$triples/g" >> $target
		done
	done
echo $query
gnuplot<<EOF
set terminal png size 800,600
set output 'tmp/$benchmark-$query.png'
set datafile separator ","
set key inside right top
set logscale x
set logscale y
set title "$query"
plot \
 'tmp/$query.luposdate-029-Multi_BPlusTree_Empty-30e88f5f5f449b2300e69aaee0f89194a666f54d-internal.csv' using 2:6 title "luposdate3000 029" with linespoints, \
 'tmp/$query.jena-Multi_BPlusTree_Empty-cf220df24c808d908415a94727a6e4b8bc8a7554-internal.csv' using 2:6 title "jena" with linespoints
EOF
done
mv tmp/*.png benchmark_results/
rm -rf tmp
done



