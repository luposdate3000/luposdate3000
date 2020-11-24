#!/bin/bash
version=d5e43452c72fa2d1fbc8bce49556a3a45d2f9794
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
		var_result_rows=$number
		var_number=$number
		var_no_repetitions=$(echo $l_no | sed "s/.*,0,//g" | sed "s/,.*//g")
		var_no_time=$(echo $l_no | sed "s/.*,0,[^,]*,//g" | sed "s/,.*//g")
		var_no_time_per_repetition=$(bc <<< "scale=10; $var_no_time / $var_no_repetitions")
		var_no_time_per_result_row=$(bc <<< "scale=10; $var_no_time / ( $var_no_repetitions * $var_result_rows)")
		var_no_time_per_triple=$(bc <<< "scale=10; $var_no_time / ($var_no_repetitions * $var_triples)")
		var_with_repetitions=$(echo $l_with | sed "s/.*,0,//g" | sed "s/,.*//g")
		var_with_time=$(echo $l_with | sed "s/.*,0,[^,]*,//g" | sed "s/,.*//g")
		var_with_time_per_repetition=$(bc <<< "scale=10; $var_with_time / $var_with_repetitions")
		var_time_per_optimizer=$(bc <<< "scale=10; $var_with_time_per_repetition - $var_no_time_per_repetition")
		var_1_repetitions=$(echo $l_1 | sed "s/.*,0,//g" | sed "s/,.*//g")
		var_1_time=$(echo $l_1 | sed "s/.*,0,[^,]*,//g" | sed "s/,.*//g")
		var_1_time_per_repetition=$(bc <<< "scale=10; $var_1_time / $var_1_repetitions")
		var_scale_factor=$(bc <<< "scale=10; $var_1_time_per_repetition / $var_no_time_per_repetition")
		s="$var_q" #1
		s+=",$var_trash" #2
		s+=",$var_join" #3
		s+=",$var_process" #4
		s+=",$var_predicates" #5
		s+=",$var_triples" #6
		s+=",$var_result_rows" #7
		s+=",$var_no_repetitions" #8
		s+=",$var_no_time" #9
		s+=",$var_no_time_per_repetition" #10
		s+=",$var_no_time_per_result_row" #11
		s+=",$var_time_per_optimizer" #12
		s+=",$var_scale_factor" #13
		s+=",$var_no_time_per_triple" #14
		s+=",$var_number" #15
		echo $s >> tmp/all.csv
	done
done
done
