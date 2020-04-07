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
 'tmp/$query.luposdate-001.csv' using 2:6 title "luposdate3000 001--with-curl" with linespoints, \
 'tmp/$query.luposdate-002-3d7007146ee1a6478c1c18aca37a8cdd4f4f29bb.csv' using 2:6 title "luposdate3000 002-with-curl" with linespoints, \
 'tmp/$query.luposdate-003-06484cc07789d8719c6b83ffd1fdab7b9d137043-internal.csv' using 2:6 title "luposdate3000 003" with linespoints, \
 'tmp/$query.luposdate-007-5bc1cde0ea930aed6208c132d4c21653e0cb2824-internal.csv' using 2:6 title "luposdate3000 007" with linespoints, \
 'tmp/$query.luposdate-008-c5a096ef9278d91c9372d567b076d6bef496172d-internal.csv' using 2:6 title "luposdate3000 008" with linespoints, \
 'tmp/$query.luposdate-009-9bfbbf63581ae66a3a94ea23a07f3ae5c9949e35-internal.csv' using 2:6 title "luposdate3000 009" with linespoints, \
 'tmp/$query.luposdate-010-6f542a3a5e5a84e248fa8d1da14cd92f90a7f586-internal.csv' using 2:6 title "luposdate3000 010" with linespoints, \
 'tmp/$query.luposdate-011-a054a02d2f1996d2464709e2fc6e701d33830f20-internal.csv' using 2:6 title "luposdate3000 011" with linespoints, \
 'tmp/$query.luposdate-012-1bbf7e5e728c3fbbb6dffe9ad421e86f738876d3-internal.csv' using 2:6 title "luposdate3000 012" with linespoints, \
 'tmp/$query.jena.csv' using 2:6 title "jena-with-curl" with linespoints
EOF

done
mv tmp/*.png benchmark_results/sp2b/
rm -rf tmp
