#!/bin/bash

for benchmark in bsbm sp2b
do
echo $benchmark
mkdir tmp
for query in $(cat benchmark_results/$benchmark/*.csv | sed "s#sparql.*#sparql#g" | sort | uniq | sed "s#resources/$benchmark/##g")
do
echo $query
	for f in $(find benchmark_results/$benchmark -name "*.csv" | grep \
			-e cf220df24c808d908415a94727a6e4b8bc8a7554 \
			-e f17068c716e93706cc5ae72fa050685a187888ea \
			\
			-e d91c0c41b380ec436db7a4bb677d1d5d6709c127 \
			-e 2693848df72a5dc1fa17d5d85cf561fb6e88db9d \
			-e 04d2c408e9542bc8d4b3840c2b1a3e3b0f7e01a5 \
			-e 43a53e32c958efbd338747265b5d9877784b91fe
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
 'tmp/$query.luposdate-030-Multi_BPlusTree_Empty-d91c0c41b380ec436db7a4bb677d1d5d6709c127-internal.csv' using 2:6 title "luposdate3000 030" with linespoints, \
 'tmp/$query.luposdate-031-Multi_BPlusTree_XML-2693848df72a5dc1fa17d5d85cf561fb6e88db9d-internal.csv' using 2:6 title "luposdate3000 031 XML" with linespoints, \
 'tmp/$query.luposdate-032-Multi_BPlusTree_Empty-04d2c408e9542bc8d4b3840c2b1a3e3b0f7e01a5-internal.csv' using 2:6 title "luposdate3000 032" with linespoints, \
 'tmp/$query.luposdate3000-Multi_BPlusTree_Empty-43a53e32c958efbd338747265b5d9877784b91fe-internal.csv' using 2:6 title "luposdate3000 033" with linespoints, \
 'tmp/$query.jena-Multi_BPlusTree_Empty-cf220df24c808d908415a94727a6e4b8bc8a7554-internal.csv' using 2:6 title "jena old" with linespoints, \
 'tmp/$query.jena-Multi_BPlusTree_Empty-f17068c716e93706cc5ae72fa050685a187888ea-internal.csv' using 2:6 title "jena new" with linespoints
EOF
done
mv tmp/*.png benchmark_results/
rm -rf tmp
done



