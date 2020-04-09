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
 'tmp/$query.luposdate-011-a054a02d2f1996d2464709e2fc6e701d33830f20-internal.csv' using 2:6 title "luposdate3000 011" with linespoints, \
 'tmp/$query.luposdate-012-1bbf7e5e728c3fbbb6dffe9ad421e86f738876d3-internal.csv' using 2:6 title "luposdate3000 012" with linespoints, \
 'tmp/$query.luposdate-013-f53095abed4033102e3393a2d35d4a1365531193-internal.csv' using 2:6 title "luposdate3000 013" with linespoints, \
 'tmp/$query.luposdate-014-9e86bb359e8003a1ff6f04c974f2683ad018e7b9-internal.csv' using 2:6 title "luposdate3000 014" with linespoints, \
 'tmp/$query.luposdate-015-9318ad8651080e14aede8ca36231969b6a8e0c20-internal.csv' using 2:6 title "luposdate3000 015" with linespoints, \
 'tmp/$query.jena.csv' using 2:6 title "jena-with-curl" with linespoints
EOF

done
mv tmp/*.png benchmark_results/sp2b/
rm -rf tmp
