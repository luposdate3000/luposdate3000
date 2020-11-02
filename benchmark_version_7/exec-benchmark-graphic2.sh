#!/bin/bash
mkdir tmp
for query in $(cat benchmark_results/sp2b/*.csv | sed "s/sparql.*/sparql/g" | sort | uniq | sed "s-resources/sp2b/--g")
do
for f in $(find benchmark_results/sp2b -name "*.csv")
do
target=$(echo $f | sed "s#benchmark_results/sp2b/#tmp/$query.#g")
cat $f  | grep $query > $target
done

done


gnuplot<<EOF
set terminal png size 800,600
set output 'tmp/import.png'
set datafile separator ","
set key inside left top

set logscale x
set logscale y

set title "import"
plot \
 'tmp/persistence-import.sparql.helper-luposdate-021-MapMap_BinaryTree_Empty-b52417bab6a0d08968d145ba21c4b69e082d5e43-internal.csv' using 2:5 title "luposdate3000 021" with linespoints, \
 'tmp/persistence-import.sparql.luposdate-023-Single_BTree_Empty-a3625ec3927ceb6a87fa5962a3a1e2ae1c5767ce-internal.csv' using 2:5 title "luposdate3000 023" with linespoints, \
 'tmp/persistence-import-dict.sparql.luposdate-023-Single_BTree_Empty-a3625ec3927ceb6a87fa5962a3a1e2ae1c5767ce-internal.csv' using 2:5 title "luposdate3000 023 dict" with linespoints, \
 'tmp/persistence-import-merge.sparql.luposdate-023-Single_BTree_Empty-a3625ec3927ceb6a87fa5962a3a1e2ae1c5767ce-internal.csv' using 2:5 title "luposdate3000 023 merge" with linespoints, \
 'tmp/persistence-import-parse.sparql.luposdate-023-Single_BTree_Empty-a3625ec3927ceb6a87fa5962a3a1e2ae1c5767ce-internal.csv' using 2:5 title "luposdate3000 023 parse" with linespoints, \
 'tmp/persistence-import-rebuild.sparql.luposdate-023-Single_BTree_Empty-a3625ec3927ceb6a87fa5962a3a1e2ae1c5767ce-internal.csv' using 2:5 title "luposdate3000 023 rebuild" with linespoints, \
 'tmp/persistence-import-sort.sparql.luposdate-023-Single_BTree_Empty-a3625ec3927ceb6a87fa5962a3a1e2ae1c5767ce-internal.csv' using 2:5 title "luposdate3000 023 sort" with linespoints, \
 'tmp/persistence-import.sparql.luposdate-Single_BTree_Empty-f788f3cb70e4cd629939cdc481fd24cea22cad3b-internal.csv' using 2:5 title "luposdate3000 024" with linespoints, \
 'tmp/persistence-import-dict.sparql.luposdate-Single_BTree_Empty-f788f3cb70e4cd629939cdc481fd24cea22cad3b-internal.csv' using 2:5 title "luposdate3000 024 dict" with linespoints, \
 'tmp/persistence-import-merge.sparql.luposdate-Single_BTree_Empty-f788f3cb70e4cd629939cdc481fd24cea22cad3b-internal.csv' using 2:5 title "luposdate3000 024 merge" with linespoints, \
 'tmp/persistence-import-parse.sparql.luposdate-Single_BTree_Empty-f788f3cb70e4cd629939cdc481fd24cea22cad3b-internal.csv' using 2:5 title "luposdate3000 024 parse" with linespoints, \
 'tmp/persistence-import-rebuild.sparql.luposdate-Single_BTree_Empty-f788f3cb70e4cd629939cdc481fd24cea22cad3b-internal.csv' using 2:5 title "luposdate3000 024 rebuild" with linespoints, \
 'tmp/persistence-import-sort.sparql.luposdate-Single_BTree_Empty-f788f3cb70e4cd629939cdc481fd24cea22cad3b-internal.csv' using 2:5 title "luposdate3000 024 sort" with linespoints, \
 'tmp/import.sparql.helper-jena-internal.csv' using 2:5 title "jena" with linespoints
EOF


mv tmp/*.png benchmark_results/sp2b/
rm -rf tmp

