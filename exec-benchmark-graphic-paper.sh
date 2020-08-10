#!/bin/bash

version=627105881359c10685b6921a5c71fd6d2c782204
rm -rf tmp
mkdir tmp
for query in $(find resources/lupos/ -type f | sed "s-.*/--g")
do
for f in benchmark_results/lupos/*
do
	source=/mnt/luposdate-testdata/lupos_$(echo $f | sed "s/.*v_//g" | sed "s/_.*//g")
	target=tmp/$(echo $f | sed "s/.*v_/v_/g")_$query
	touch $target
	truncate -s0 $target
	for l in $(cat $f/*${version}*.csv | grep ${query})
	do
		number=$(echo $l | sed "s#.*sparql,##g" | sed "s#,.*##g")
		triples=$(cat $source/stat.csv | grep "^$number," | sed "s#^$number,##g" | sed "s#,.*##g")
		echo $l | sed "s#.sparql,$number#.sparql,$triples#g" >> $target
	done
done

cat <<EOF > tmp/${query}_1.plot
set terminal png size 1920,1080
set output 'tmp/$(echo $query | sed "s/.sparql//g").png'
set datafile separator ","
set key inside right top
set logscale x
set logscale y
set title "$query"
EOF
s=""
for f in tmp/*_$query
do
	x=$(echo $f | sed 's-tmp/--g' | sed "s/_${query}//g" | sed 's/_/-/g')
	s="$s, '$f' using 2:6 title \"$x\" with linespoints"
done
echo "plot${s:1}" >> tmp/${query}_1.plot
cat tmp/${query}_1.plot | gnuplot

cat <<EOF > tmp/${query}_2.plot
set terminal epslatex
set output '$(echo $query | sed "s/.sparql//g").tex'
set datafile separator ","
set key inside right top
set logscale x
set logscale y
set title "$query"
EOF
s=""
for f in tmp/*_$query
do
	x=$(echo $f | sed 's-tmp/--g' | sed "s/_${query}//g" | sed 's/_/-/g')
	s="$s, '$f' using 2:6 title \"$x\" with linespoints"
done
echo "plot${s:1}" >> tmp/${query}_2.plot
cat tmp/${query}_2.plot | gnuplot
mv $(echo $query | sed "s/.sparql//g").tex tmp/$(echo $query | sed "s/.sparql//g").tex
mv $(echo $query | sed "s/.sparql//g").eps tmp/$(echo $query | sed "s/.sparql//g").eps


done
#mv tmp/*.png benchmark_results/
#rm -rf tmp
