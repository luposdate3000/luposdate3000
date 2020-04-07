#!/bin/bash
mkdir tmp
for query in $(cat benchmark_results/sp2b/*.csv | sed "s/sparql.*/sparql/g" | sort | uniq | sed "s-resources/sp2b/--g")
do
for f in $(find benchmark_results/sp2b -name "*.csv")
do
target=$(echo $f | sed "s-benchmark_results/sp2b/-tmp/$query.-g")
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
 'tmp/$query.luposdate.csv' using 2:6 title "luposdate3000 old" with linespoints, \
 'tmp/$query.luposdate-3d7007146ee1a6478c1c18aca37a8cdd4f4f29bb.csv' using 2:6 title "luposdate3000 friday" with linespoints, \
 'tmp/$query.luposdate-06484cc07789d8719c6b83ffd1fdab7b9d137043-internal.csv' using 2:6 title "luposdate3000 friday-no-curl" with linespoints, \
 'tmp/$query.luposdate-5bc1cde0ea930aed6208c132d4c21653e0cb2824-internal.csv' using 2:6 title "luposdate3000 monday-no-curl" with linespoints, \
 'tmp/$query.luposdate-c5a096ef9278d91c9372d567b076d6bef496172d-internal.csv' using 2:6 title "luposdate3000 new-no-curl" with linespoints, \
 'tmp/$query.jena.csv' using 2:6 title "jena" with linespoints
EOF

done
mv tmp/*.png benchmark_results/sp2b/
rm -rf tmp
