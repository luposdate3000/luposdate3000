#!/bin/bash

for benchmark in bsbm sp2b
do
echo $benchmark
mkdir tmp
for query in $(cat benchmark_results/$benchmark/*.csv | sed "s#sparql.*#sparql#g" | sort | uniq | sed "s#resources/$benchmark/##g")
do
echo $query
	for f in $(find benchmark_results/$benchmark -name "*.csv" | grep \
			-e f17068c716e93706cc5ae72fa050685a187888ea \
			\
			-e 43a53e32c958efbd338747265b5d9877784b91fe \
			-e 6b716f9724074a0f1c0abdd5b0374ed68c9e3f14
		)
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
 'tmp/$query.luposdate-033-Multi_BPlusTree_Empty-43a53e32c958efbd338747265b5d9877784b91fe-internal.csv' using 2:6 title "luposdate3000 033" with linespoints, \
 'tmp/$query.luposdate3000-Multi_BPlusTree_Empty-6b716f9724074a0f1c0abdd5b0374ed68c9e3f14-internal.csv' using 2:6 title "luposdate3000 034" with linespoints, \
 'tmp/$query.jena-Multi_BPlusTree_Empty-f17068c716e93706cc5ae72fa050685a187888ea-internal.csv' using 2:6 title "jena" with linespoints
EOF
done
mv tmp/*.png benchmark_results/
rm -rf tmp
done



