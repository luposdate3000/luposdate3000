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
 'tmp/$query.luposdate-026-Multi_BPlusTree_Empty-dbd5ac3d2c616386ca37991099a82a92fb41c660-internal.csv' using 2:6 title "luposdate3000 026" with linespoints, \
 'tmp/$query.luposdate-Multi_BPlusTree_Empty-e95ab361aa4f0db2e6cefa54a5698ae68c0f0677-internal.csv' using 2:6 title "luposdate3000 027a" with linespoints, \
 'tmp/$query.luposdate-Multi_BPlusTree_Empty-d48396d4c83259377be972cbe82ca2fe8a14846c-internal.csv' using 2:6 title "luposdate3000 027b" with linespoints, \
 'tmp/$query.jena-internal.csv' using 2:6 title "jena" with linespoints
EOF

done
mv tmp/*.png benchmark_results/sp2b/
rm -rf tmp
