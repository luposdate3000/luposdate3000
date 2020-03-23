#!/bin/bash
for query in $(cat benchmark_results/sp2b/luposdate.csv | sed "s/sparql.*/sparql/g" | sort | uniq | sed "s-resources/sp2b/--g")
do
cat benchmark_results/sp2b/luposdate.csv | grep $query > tmp/$query.luposdate3000.csv
cat benchmark_results/sp2b/jena.csv | grep $query > tmp/$query.jena.csv

gnuplot<<EOF
set terminal png size 800,600
set output 'tmp/$query.png'
set datafile separator ","
set key inside right top

set logscale x

set title "$query"
plot 'tmp/$query.luposdate3000.csv' using 2:6 title "luposdate3000" with lines, \
 'tmp/$query.jena.csv' using 2:6 title "jena" with lines
EOF

done
