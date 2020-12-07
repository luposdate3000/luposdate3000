#!/bin/bash

gnuplot_terminal="set terminal epslatex color"
gnuplot_terminal_ending="tex"
#gnuplot_terminal="set terminal png size 1920,1080"
#gnuplot_terminal="set terminal png size 800,600"
#gnuplot_terminal_ending="png"

rm tmp/graph_3*
for var_result_rows in 32 128 512 2048 8196 32768 131072 524288 2097152
do
	for var_query in q2 q3 q4 q5 q6 q7 q8 q9 q10
	do
		echo "${gnuplot_terminal}" > tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "set output 'graph_3_r${var_result_rows}_${var_query}.${gnuplot_terminal_ending}'" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "set datafile separator ','" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "set notitle" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "set logscale y" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "set logscale x 2" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "set key outside below reverse left Left" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "set xlabel 'selectivity'" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "set ylabel 'ms per result row' " >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "set format x \"$\\\\frac{1}{1+2^{%L}}$\"" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
		echo "plot \\" >>tmp/graph_3_r${var_result_rows}_${var_query}.plot
		i=0
		for var_database in blazegraph jena virtuoso
		do
			echo "tmp/makro_filtered_${var_database}_1_${var_query}_r${var_result_rows}.csv"
			if [ -f "tmp/makro_filtered_${var_database}_1_${var_query}_r${var_result_rows}.csv" ]
			then
				echo "  'tmp/makro_filtered_${var_database}_1_${var_query}_r${var_result_rows}.csv' using 1:2:3:4 with yerrorlines title '${var_database}',\\" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
				i=1
			fi
		done
			echo "tmp/makro_filtered_luposdate_1_${var_query}_r${var_result_rows}.csv"
			if [ -f "tmp/makro_filtered_luposdate_1_${var_query}_r${var_result_rows}.csv" ]
			then
				echo "  'tmp/makro_filtered_luposdate_1_${var_query}_r${var_result_rows}.csv' using 1:2:3:4 with yerrorlines title 'luposdate-MEMORY',\\" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
				i=1
			fi
			echo "tmp/makro_filtered_luposdate-parallel_1_${var_query}_r${var_result_rows}.csv"
			if [ -f "tmp/makro_filtered_luposdate-parallel_1_${var_query}_r${var_result_rows}.csv" ]
			then
				echo "  'tmp/makro_filtered_luposdate-parallel_1_${var_query}_r${var_result_rows}.csv' using 1:2:3:4 with yerrorlines title 'luposdate-RDF3X',\\" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
				i=1
			fi
		for var_process in 1 6 12 24
		do
			echo "tmp/makro_filtered_luposdate3000_${var_process}_${var_query}_r${var_result_rows}.csv"
			if [ -f "tmp/makro_filtered_luposdate3000_${var_process}_${var_query}_r${var_result_rows}.csv" ]
			then
				echo "  'tmp/makro_filtered_luposdate3000_${var_process}_${var_query}_r${var_result_rows}.csv' using 1:2:3:4 with yerrorlines title 'luposdate3000($var_process)',\\" >> tmp/graph_3_r${var_result_rows}_${var_query}.plot
				i=1
			fi
		done
		if [ "$i" == "0" ]
		then
			rm tmp/graph_3_r${var_result_rows}_${var_query}.plot
		fi
	done
done
for var_trash in 0 1 2 4 8 16 32 64 128 256 512 1024
do
	for var_query in q2 q3 q4 q5 q6 q7 q8 q9 q10
	do
		echo "${gnuplot_terminal}" > tmp/graph_3_t${var_trash}_${var_query}.plot
		echo "set output 'graph_3_t${var_trash}_${var_query}.${gnuplot_terminal_ending}'" >> tmp/graph_3_t${var_trash}_${var_query}.plot
		echo "set datafile separator ','" >> tmp/graph_3_t${var_trash}_${var_query}.plot
		echo "set notitle" >> tmp/graph_3_t${var_trash}_${var_query}.plot
		echo "set logscale x" >> tmp/graph_3_t${var_trash}_${var_query}.plot
		echo "set logscale y" >> tmp/graph_3_t${var_trash}_${var_query}.plot
		echo "set key outside below reverse left Left" >> tmp/graph_3_t${var_trash}_${var_query}.plot
		echo "set xlabel 'result rows'" >> tmp/graph_3_t${var_trash}_${var_query}.plot
		echo "set ylabel 'ms per result row' " >> tmp/graph_3_t${var_trash}_${var_query}.plot
		echo "plot \\" >>tmp/graph_3_t${var_trash}_${var_query}.plot
		i=0
		for var_database in blazegraph jena virtuoso
		do
			echo "tmp/makro_filtered_${var_database}_1_${var_query}_t${var_trash}.csv"
			if [ -f "tmp/makro_filtered_${var_database}_1_${var_query}_t${var_trash}.csv" ]
			then
				echo "  'tmp/makro_filtered_${var_database}_1_${var_query}_t${var_trash}.csv' using 1:2:3:4 with yerrorlines title '${var_database}',\\" >> tmp/graph_3_t${var_trash}_${var_query}.plot
				i=1
			fi
		done
			echo "tmp/makro_filtered_luposdate_1_${var_query}_t${var_trash}.csv"
			if [ -f "tmp/makro_filtered_luposdate_1_${var_query}_t${var_trash}.csv" ]
			then
				echo "  'tmp/makro_filtered_luposdate_1_${var_query}_t${var_trash}.csv' using 1:2:3:4 with yerrorlines title 'luposdate-MEMORY',\\" >> tmp/graph_3_t${var_trash}_${var_query}.plot
				i=1
			fi
			echo "tmp/makro_filtered_luposdate-parallel_1_${var_query}_t${var_trash}.csv"
			if [ -f "tmp/makro_filtered_luposdate-parallel_1_${var_query}_t${var_trash}.csv" ]
			then
				echo "  'tmp/makro_filtered_luposdate-parallel_1_${var_query}_t${var_trash}.csv' using 1:2:3:4 with yerrorlines title 'luposdate-RDF3X',\\" >> tmp/graph_3_t${var_trash}_${var_query}.plot
				i=1
			fi
		for var_process in 1 6 12 24
		do
			echo "tmp/makro_filtered_luposdate3000_${var_process}_${var_query}_t${var_trash}.csv"
			if [ -f "tmp/makro_filtered_luposdate3000_${var_process}_${var_query}_t${var_trash}.csv" ]
			then
				echo "  'tmp/makro_filtered_luposdate3000_${var_process}_${var_query}_t${var_trash}.csv' using 1:2:3:4 with yerrorlines title 'luposdate3000($var_process)',\\" >> tmp/graph_3_t${var_trash}_${var_query}.plot
				i=1
			fi
		done
		if [ "$i" == "0" ]
		then
			rm tmp/graph_3_t${var_trash}_${var_query}.plot
		fi
	done
done


for f in $(find tmp -name "graph_3_*.plot")
do
        gnuplot $f
done
mv graph_3_* tmp
