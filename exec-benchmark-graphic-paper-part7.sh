#!/bin/bash
#q2,10,1,10,2,1218,1,75520,5000.025618,.0662079663,.0662079663,.1746584181,.8832070514,.0000543579,1024
		for d in $(seq 1 20)
		do
			for e in 1024 32768 2097152
			do
				cat tmp/all.csv | grep "q2,[^,]*,1,$d,2,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,[^,]*,$e" | sed -E "s/q2,(.),/q2,0\1,/g" | sort > tmp/part_d${d}_e${e}.csv
			done
		done
gnuplot_terminal="set terminal epslatex"
gnuplot_terminal_ending="tex"
gnuplot_terminal="set terminal png size 1920,1080"
gnuplot_terminal_ending="png"
			for e in 1024 32768 2097152
			do
				echo "${gnuplot_terminal}" > tmp/graph_7_e${e}.plot
				echo "set output 'graph_7_e${e}.${gnuplot_terminal_ending}'" >> tmp/graph_7_e${e}.plot
				echo "set datafile separator ','" >> tmp/graph_7_e${e}.plot
				echo "set notitle" >> tmp/graph_7_e${e}.plot
				echo "set key inside right top" >> tmp/graph_7_e${e}.plot
#				echo "set logscale x" >> tmp/graph_7_e${e}.plot
#				echo "set logscale y" >> tmp/graph_7_e${e}.plot
#				echo "set yrange [*:*]" >> tmp/graph_7_e${e}.plot
#				echo "set xrange [*:*]" >> tmp/graph_7_e${e}.plot
				echo "plot \\" >> tmp/graph_7_e${e}.plot
			done
		for d in $(seq 1 14)
		do
			for e in 1024 32768 2097152
			do
				#b
				echo "'tmp/part_d${d}_e${e}.csv' using 2:14 title 'd${d}' with linespoints, \\" >> tmp/graph_7_e${e}.plot
			done
		done
for f in $(find tmp -name "graph_7_*.plot")
do
	gnuplot $f
done
mv graph_7_* tmp
#for f in $(find tmp -maxdepth 1 -type f -name '*.png' -empty | sed "s/\..*/.*/g" | sort | uniq)
#do
#	rm $f
#done
