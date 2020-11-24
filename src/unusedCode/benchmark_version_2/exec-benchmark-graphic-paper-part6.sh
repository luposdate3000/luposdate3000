#!/bin/bash

for a in q1 q2 q3 q4 q5 q6
do
	for b in 0 1 32 128 1024
	do
		for c in 1
		do
			for d in 1 2 3 4 5 6 7 8 9 10 11 12
			do
				cat tmp/all.csv | grep "$a,$b,$c,$d," > tmp/part_a${a}_b${b}_c${c}_d${d}.csv
			done
		done
	done
done
gnuplot_terminal="set terminal epslatex"
gnuplot_terminal_ending="tex"
gnuplot_terminal="set terminal png size 1920,1080"
gnuplot_terminal_ending="png"
for a in q1 q2 q3 q4 q5 q6
do
	for b in 0 1 32 128 1024
	do
		for c in 1
		do
			for d in 1 2 3 4 5 6 7 8 9 10 11 12
			do
				#a
				echo "${gnuplot_terminal}" > tmp/graph_6_b${b}_c${c}_d${d}.plot
				echo "set output 'graph_6_b${b}_c${c}_d${d}.${gnuplot_terminal_ending}'" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
				echo "set datafile separator ','" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
				echo "set notitle" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
				echo "set key inside right top" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
				echo "set logscale x" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
#				echo "set logscale y" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
#				echo "set yrange [0.000007:0.00003]" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
				echo "set xrange [1000:*]" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
				echo "plot \\" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
				#b
				echo "${gnuplot_terminal}" > tmp/graph_6_a${a}_c${c}_d${d}.plot
				echo "set output 'graph_6_a${a}_c${c}_d${d}.${gnuplot_terminal_ending}'" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
				echo "set datafile separator ','" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
				echo "set notitle" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
				echo "set key inside right top" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
				echo "set logscale x" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
#				echo "set logscale y" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
#				echo "set yrange [0.000007:0.00003]" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
				echo "set xrange [1000:*]" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
				echo "plot \\" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
				#c
				echo "${gnuplot_terminal}" > tmp/graph_6_a${a}_b${b}_d${d}.plot
				echo "set output 'graph_6_a${a}_b${b}_d${d}.${gnuplot_terminal_ending}'" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
				echo "set datafile separator ','" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
				echo "set notitle" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
				echo "set key inside right top" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
				echo "set logscale x" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
#				echo "set logscale y" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
#				echo "set yrange [0.000007:0.00003]" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
				echo "set xrange [1000:*]" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
				echo "plot \\" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
				#d
				echo "${gnuplot_terminal}" > tmp/graph_6_a${a}_b${b}_c${c}.plot
				echo "set output 'graph_6_a${a}_b${b}_c${c}.${gnuplot_terminal_ending}'" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
				echo "set datafile separator ','" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
				echo "set notitle" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
				echo "set key inside right top" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
				echo "set logscale x" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
#				echo "set logscale y" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
#				echo "set yrange [0.000007:0.00003]" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
				echo "set xrange [1000:*]" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
				echo "plot \\" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
			done
		done
	done
done
for a in q1 q2 q3 q4 q5 q6
do
	for b in 0 1 32 128 1024
	do
		for c in 1
		do
			for d in 1 2 3 4 5 6 7 8 9 10 11 12
			do
				#a
				echo "'tmp/part_a${a}_b${b}_c${c}_d${d}.csv' using 6:14 title 'a${a}' with linespoints, \\" >> tmp/graph_6_b${b}_c${c}_d${d}.plot
				#b
				echo "'tmp/part_a${a}_b${b}_c${c}_d${d}.csv' using 6:14 title 'b${b}' with linespoints, \\" >> tmp/graph_6_a${a}_c${c}_d${d}.plot
				#c
				echo "'tmp/part_a${a}_b${b}_c${c}_d${d}.csv' using 6:14 title 'c${c}' with linespoints, \\" >> tmp/graph_6_a${a}_b${b}_d${d}.plot
				#d
				echo "'tmp/part_a${a}_b${b}_c${c}_d${d}.csv' using 6:14 title 'd${d}' with linespoints, \\" >> tmp/graph_6_a${a}_b${b}_c${c}.plot
			done
		done
	done
done
for f in $(find tmp -name "graph_6_*.plot")
do
	gnuplot $f
done
mv graph_6_* tmp
