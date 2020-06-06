#!/bin/bash
mkdir tmp
for query in $(cat benchmark_results/sp2b/*.csv | sed "s/sparql.*/sparql/g" | sort | uniq | sed "s-resources/sp2b/--g")
do
for f in $(find benchmark_results/sp2b -name "*.csv")
do
target=$(echo $f | sed "s#benchmark_results/sp2b/#tmp/$query.#g")
cat $f  | grep $query > $target
done

gnuplot<<EOF
set terminal png size 800,600
set output 'tmp/$query.png'
set datafile separator ","
set key inside right top

set logscale x
set logscale y

set title "$query"
plot \
 'tmp/$query.luposdate-029-Multi_BPlusTree_Empty-30e88f5f5f449b2300e69aaee0f89194a666f54d-internal.csv' using 2:6 title "luposdate3000 029" with linespoints, \
 'tmp/$query.jena-internal.csv' using 2:6 title "jena" with linespoints
EOF

done
mv tmp/*.png benchmark_results/sp2b/
rm -rf tmp
