#!/bin/bash
gnuplot_terminal="set terminal epslatex"
gnuplot_terminal_ending="tex"
gnuplot_terminal="set terminal png size 1920,1080"
gnuplot_terminal_ending="png"
echo "${gnuplot_terminal}" > tmp/graph_8.plot
echo "set output 'graph_8.${gnuplot_terminal_ending}'" >> tmp/graph_8.plot
echo "set datafile separator ','" >> tmp/graph_8.plot
echo "set notitle" >> tmp/graph_8.plot
echo "set key inside right top" >> tmp/graph_8.plot
#echo "set dgrid3d 1000,20 qnorm 10" >> tmp/graph_8.plot
#echo "set hidden3d" >> tmp/graph_8.plot
echo "set xlabel 'selectivity'" >> tmp/graph_8.plot
echo "set ylabel 'triples'" >> tmp/graph_8.plot
echo "set zlabel 'triples/second'" >> tmp/graph_8.plot
echo "set logscale y" >>tmp/graph_8.plot
echo "splot \\" >> tmp/graph_8.plot
#for d in $(seq 1 6)
for d in 1 2 6
do
	cat tmp/all.csv | grep "q2,[^,]*,1,$d," | sed -E "s/q2,(.),/q2,0\1,/g" | sort > tmp/part_d${d}.csv
#	echo "'tmp/part_d${d}.csv' using (1/(1+\$2)):15:(1/\$14) title 'd${d}' with linespoints, \\" >> tmp/graph_8.plot
	echo "'tmp/part_d${d}.csv' using (1/(1+\$2)):15:(1/\$14) title 'd${d}' with points, \\" >> tmp/graph_8.plot
done
for f in $(find tmp -name "graph_8*.plot")
do
	gnuplot $f
done
mv graph_8* tmp
for f in $(find tmp -maxdepth 1 -type f -name '*.png' -empty | sed "s/\..*/.*/g" | sort | uniq)
do
	rm $f
done
