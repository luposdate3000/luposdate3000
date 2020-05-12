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
 'tmp/$query.luposdate-016-b2841382ba6cfa5475c773f699e58c3937e14c35-internal.csv' using 2:6 title "luposdate3000 016" with linespoints, \
 'tmp/$query.luposdate-017-b3be67fb75117794500a208aa3b5c700e411b994-internal.csv' using 2:6 title "luposdate3000 017" with linespoints, \
 'tmp/$query.luposdate-018-b1a45f6b37e27080045b19ade26f5a2b81230e62-internal.csv' using 2:6 title "luposdate3000 018" with linespoints, \
 'tmp/$query.luposdate-021-MapMap_BinaryTree_Empty-b52417bab6a0d08968d145ba21c4b69e082d5e43-internal.csv' using 2:6 title "luposdate3000 021MapMap" with linespoints, \
 'tmp/$query.luposdate-021-Single_BinaryTree_Empty-b52417bab6a0d08968d145ba21c4b69e082d5e43-internal.csv' using 2:6 title "luposdate3000 021SingleBinary" with linespoints, \
 'tmp/$query.luposdate-021-Single_HashMap_Empty-b52417bab6a0d08968d145ba21c4b69e082d5e43-internal.csv' using 2:6 title "luposdate3000 021SingleHashEmpty" with linespoints, \
 'tmp/$query.luposdate-021-Single_HashMap_EmptyWithDictionary-b52417bab6a0d08968d145ba21c4b69e082d5e43-internal.csv' using 2:6 title "luposdate3000 021SingleHashDict" with linespoints, \
 'tmp/$query.luposdate-021-Single_HashMap_XML-b52417bab6a0d08968d145ba21c4b69e082d5e43-internal.csv' using 2:6 title "luposdate3000 021SingleHashXml" with linespoints, \
 'tmp/$query.luposdate-Single_BTree_Empty-03384502cd11bff2879728ab9ede22e0efec7ef4-internal.csv' using 2:6 title "luposdate3000 025" with linespoints, \
 'tmp/$query.jena-internal.csv' using 2:6 title "jena" with linespoints
EOF

done
mv tmp/*.png benchmark_results/sp2b/
rm -rf tmp
