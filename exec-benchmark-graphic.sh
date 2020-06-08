#!/bin/bash

for benchmark in bsbm sp2b
do
echo $benchmark
mkdir tmp
for query in $(cat benchmark_results/$benchmark/*.csv | sed "s#sparql.*#sparql#g" | sort | uniq | sed "s#resources/$benchmark/##g")
do
echo $query
	for f in $(find benchmark_results/$benchmark -name "*.csv")
	do
		target=$(echo $f | sed "s#benchmark_results/$benchmark/#tmp/$query.#g")
		touch $target
		truncate -s0 $target
		for l in $(cat $f  | grep $query)
		do
			number=$(echo $l | sed "s#.*sparql,##g" | sed "s#,.*##g")
			triples=$(cat /mnt/luposdate-testdata/$benchmark/stat.csv | grep "^$number," | sed "s#^$number,##g" | sed "s#,.*##g")
			echo $l | sed "s#.sparql,$number#.sparql,$triples#g" >> $target
		done
	done
gnuplot<<EOF
set terminal png size 800,600
set output 'tmp/$benchmark-$query.png'
set datafile separator ","
set key inside right top
set logscale x
set logscale y
set title "$query"
plot \
 'tmp/$query.luposdate-028-Multi_BPlusTree_Empty-2d6131517f75828a200dce7012453f71b09adc14-internal.csv' using 2:6 title "luposdate3000 028" with linespoints, \
 'tmp/$query.luposdate-029-Multi_BPlusTree_Empty-30e88f5f5f449b2300e69aaee0f89194a666f54d-internal.csv' using 2:6 title "luposdate3000 029" with linespoints, \
 'tmp/$query.luposdate-030-Multi_BPlusTree_Empty-d91c0c41b380ec436db7a4bb677d1d5d6709c127-internal.csv' using 2:6 title "luposdate3000 030" with linespoints, \
 'tmp/$query.jena-Multi_BPlusTree_Empty-cf220df24c808d908415a94727a6e4b8bc8a7554-internal.csv' using 2:6 title "jena" with linespoints
EOF
done
mv tmp/*.png benchmark_results/
rm -rf tmp
done



