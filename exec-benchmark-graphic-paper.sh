#!/bin/bash

version=8784e318ac72248d585e1503ea3a70096a2fea1d
query=q1.sparql
rm -rf tmp
mkdir tmp
for f in benchmark_results/lupos/*
do
	source=/mnt/luposdate-testdata/lupos_$(echo $f | sed "s/.*v_//g" | sed "s/_.*//g")
	target=tmp/$(echo $f | sed "s/.*v_/v_/g")
	touch $target
	truncate -s0 $target
	for l in $(cat $f/*${version}*.csv | grep ${query})
	do
		number=$(echo $l | sed "s#.*sparql,##g" | sed "s#,.*##g")
		triples=$(cat $source/stat.csv | grep "^$number," | sed "s#^$number,##g" | sed "s#,.*##g")
		echo $l | sed "s#.sparql,$number#.sparql,$triples#g" >> $target
	done
done

cat <<EOF > tmp/gnu.plot
set terminal png size 800,600
set output 'tmp/lupos.png'
set datafile separator ","
set key inside right top
set logscale x
set logscale y
set title "$query"
EOF
s=""
for f in tmp/*
do
	s="$s, '$f' using 2:6 title \"$(echo $f | sed 's-tmp/--g' | sed 's/_/-/g')\" with linespoints"
done
echo "plot${s:1}" >> tmp/gnu.plot
cat tmp/gnu.plot | gnuplot
#mv tmp/*.png benchmark_results/
#rm -rf tmp
