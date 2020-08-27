#!/bin/bash
column_x=2
column_z=7
column_y=11

rm tmp/part_d* tmp/v*
gnuplot_terminal="set terminal epslatex"
gnuplot_terminal_ending="tex"
gnuplot_terminal="set terminal png size 1920,1080"
gnuplot_terminal_ending="png"
echo "${gnuplot_terminal}" > tmp/graph_11.plot
echo "set output 'graph_11.${gnuplot_terminal_ending}'" >> tmp/graph_11.plot
echo "set datafile separator ','" >> tmp/graph_11.plot
echo "set notitle" >> tmp/graph_11.plot
echo "set key inside right top" >> tmp/graph_11.plot
echo "set hidden3d" >> tmp/graph_11.plot
echo "set xlabel 'triples'" >> tmp/graph_11.plot
echo "set ylabel 'selectivity' " >> tmp/graph_11.plot
echo "set zlabel 'triples/second'" >> tmp/graph_11.plot
echo "set logscale x" >>tmp/graph_11.plot
echo "set view 45,300" >>tmp/graph_11.plot
echo "set isosamples 100,100" >>tmp/graph_11.plot
echo "log2(x) = log(x) / log(2)" >>tmp/graph_11.plot
echo "ga(x,z,w) = -557.28759765625 + 259.3994140625 * w + 2.42919921875 * w * x + 14.453125 * x + -554.443359375 * w * z + 24985.1806640625 * z + 24.3896484375 * w * x * z + -565.1123046875 * x * z" >>tmp/graph_11.plot
echo "mapX(x) = log2(x)" >>tmp/graph_11.plot
echo "mapZ(z) = 1 / (1 + z)" >>tmp/graph_11.plot
echo "fa(x,z,w) = ga(mapX(x),mapZ(z),w)" >>tmp/graph_11.plot
echo "splot [1 : 100000000000] [0:120] [0:5000]\\" >> tmp/graph_11.plot
for d in $(seq 1 6)
do
	v=$(( $d - 1))
        r=$(( ( ( $v % 6 ) * 256 ) / 6))
        g=$(( ( ( $v / 6 ) * 256 ) / 6))
        b="0"
        c=$(( ( ( $r * 256 ) + $g ) * 256 + $b ))
	c2=$(( 256*256*256 * $v / 24))

	echo "fa(x,y,$d) with lines lc ${d},\\" >> tmp/graph_11.plot
done
for f in $(find tmp -name "graph_11*.plot")
do
	gnuplot $f
done
mv graph_11* tmp
