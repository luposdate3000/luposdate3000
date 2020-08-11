#!/bin/bash

version=b339cf5d4afa78476a5da4ff4f83d61705429845
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
	for l_no in $(cat $f/*${version}*.csv | grep ${query} | grep "NoOptimizer" | sed "s/,NoOptimizer//g")
	do
		number=$(echo $l_no | sed "s#.*sparql,##g" | sed "s#,.*##g")
		triples=$(cat $source/stat.csv | grep "^$number," | sed "s#^$number,##g" | sed "s#,.*##g")
		l_with=$(cat $f/*${version}*.csv | grep ${query} | grep "WithOptimizer" | sed "s/,WithOptimizer//g" | grep ",$number,")
		l_1=$(cat $(echo $f| sed "s/[0-9][0-9]*P/1P/g")/*${version}*.csv | grep ${query} | grep "NoOptimizer" | sed "s/,NoOptimizer//g" | grep ",$number,")
		echo $l_no | sed "s#.sparql,$number#.sparql,$triples#g" >> $target
var_q=$(echo $query | sed "s-.*/--g" | sed "s/\.sparql//g")
var_trash=$(echo $f | sed "s-.*v_[^T]*T[^T]T--g" | sed "s/_.*//g")
var_join=$(echo $f | sed "s-.*v_[^T]T--g" | sed "s/T.*//g")
var_process=$(echo $f | sed "s-.*_--g" | sed "s/P//g")
var_predicates=$(echo $f | sed "s-.*v_--g" | sed "s/T.*//g")
var_triples=$triples
var_result_rows=$(cat $source/$number/data*.n3 | grep "<p0>" | grep ";" | wc -l)

var_no_repetitions=$(echo $l_no | sed "s/.*,0,//g" | sed "s/,.*//g")
var_no_time=$(echo $l_no | sed "s/.*,0,[^,]*,//g" | sed "s/,.*//g")
var_no_time_per_repetition=$(bc <<< "scale=10; $var_no_time / $var_no_repetitions")
var_no_time_per_result_row=$(bc <<< "scale=10; $var_no_time / ( $var_result_rows * $var_no_repetitions)")

var_with_repetitions=$(echo $l_with | sed "s/.*,0,//g" | sed "s/,.*//g")
var_with_time=$(echo $l_with | sed "s/.*,0,[^,]*,//g" | sed "s/,.*//g")
var_with_time_per_repetition=$(bc <<< "scale=10; $var_with_time / $var_with_repetitions")
var_time_per_optimizer=$(bc <<< "scale=10; $var_with_time_per_repetition - $var_no_time_per_repetition")

var_1_repetitions=$(echo $l_1 | sed "s/.*,0,//g" | sed "s/,.*//g")
var_1_time=$(echo $l_1 | sed "s/.*,0,[^,]*,//g" | sed "s/,.*//g")
var_1_time_per_repetition=$(bc <<< "scale=10; $var_1_time / $var_1_repetitions")
var_scale_factor=$(bc <<< "scale=10; $var_1_time_per_repetition / $var_no_time_per_repetition")

s="$var_q"
s+=",$var_trash"
s+=",$var_join"
s+=",$var_process"
s+=",$var_predicates"
s+=",$var_triples"
s+=",$var_result_rows"
s+=",$var_no_repetitions"
s+=",$var_no_time"
s+=",$var_no_time_per_repetition"
s+=",$var_no_time_per_result_row"
#s+=",$var_time_per_optimizer"
s+=",$var_scale_factor"
echo $s >> tmp/all.csv
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


cat <<EOF > tmp/legend.plot
set terminal epslatex
set output 'legend.tex'
set key inside right top
set noborder
set noxtics
set noytics
set notitle
set noxlabel
set noylabel
set xrange [-10:10]
set yrange [-10:10]
EOF
s=""
for f in tmp/*_$query
do
        x=$(echo $f | sed 's-tmp/--g' | sed "s/_${query}//g" | sed 's/_/-/g')
        s="$s, 20 title \"$x\" with linespoints"
done
echo "plot${s:1}" >> tmp/legend.plot
cat tmp/legend.plot | gnuplot
mv legend.tex tmp/legend.tex
mv legend.eps tmp/legend.eps


cat <<EOF > tmp/${query}_2.plot
set terminal epslatex
set output '$(echo $query | sed "s/.sparql//g").tex'
set datafile separator ","
set nokey
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
