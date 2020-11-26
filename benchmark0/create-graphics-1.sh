#!/bin/bash
for display in 0 1 2 4
do
for join in 2 10
do
#gnuplot_terminal="set terminal epslatex color size 5.5,3.8"
gnuplot_terminal="set terminal epslatex standalone color size 5.5,3.8"
gnuplot_terminal_ending="tex"
#gnuplot_terminal="set terminal png size 1920,1080"
#gnuplot_terminal="set terminal png size 800,600"
#gnuplot_terminal_ending="png"

echo "${gnuplot_terminal}" > tmp/graph_1_${display}_${join}.plot
echo "set output 'graph_1_${display}_${join}.${gnuplot_terminal_ending}'" >> tmp/graph_1_${display}_${join}.plot
echo "set datafile separator ','" >> tmp/graph_1_${display}_${join}.plot
echo "set notitle" >> tmp/graph_1_${display}_${join}.plot
echo "unset key" >> tmp/graph_1_${display}_${join}.plot
echo "set xlabel 'output rows'" >> tmp/graph_1_${display}_${join}.plot
echo "set ylabel 'selectivity' offset 3,0,0" >> tmp/graph_1_${display}_${join}.plot
echo "zmax=24.5" >>tmp/graph_1_${display}_${join}.plot
echo "zmin=0.5" >>tmp/graph_1_${display}_${join}.plot
echo "zstep=1" >>tmp/graph_1_${display}_${join}.plot
echo "set cbrange [zmin:zmax]" >>tmp/graph_1_${display}_${join}.plot
echo "set cbtics zstep" >>tmp/graph_1_${display}_${join}.plot
#echo "set palette rgbformulae 33,13,10" >>tmp/graph_1_${display}_${join}.plot
#echo "set palette maxcolors (zmax-zmin)/zstep" >>tmp/graph_1_${display}_${join}.plot

#echo "set palette model RGB maxcolors (zmax-zmin)" >>tmp/graph_1_${display}_${join}.plot
echo "set palette model RGB" >>tmp/graph_1_${display}_${join}.plot
echo "set palette defined (0 '#ffffff',0.1 '#8000ff',1 '#8000ff',2 '#6a21ff',3 '#5542fd',4 '#4062fa',5 '#2b80f7',6 '#159bf2',7 '#00b4ec',8 '#15cae5',9 '#2bdddd',10 '#40ecd4',11 '#55f7ca',12 '#6afdc0',13 '#80ffb4',14 '#95fda8',15 '#aaf79b',16 '#d5dd80',17 '#eaca71',18 '#ffb462',19 '#ff9b52',20 '#ff8042',21 '#ff6232',22 '#ff4221',23 '#ff2111',24 '#ff0000')" >>tmp/graph_1_${display}_${join}.plot

echo "plot 'tmp/matrix_${display}_${join}.csv' matrix rowheaders columnheaders using 1:2:(\$3) with image notitle,\\" >>tmp/graph_1_${display}_${join}.plot
#echo " 'tmp/matrix_3_${join}.csv' matrix rowheaders columnheaders using 1:2:(\$3>0?sprintf('%.2f', \$3):'') with labels notitle,\\" >>tmp/graph_1_${display}_${join}.plot
echo " 'tmp/matrix_5_${join}.csv' matrix rowheaders columnheaders using 1:2:(\$3>0?sprintf('%.2f', \$3):'') with labels notitle,\\" >>tmp/graph_1_${display}_${join}.plot
done
done
for f in $(find tmp -name "graph_1_*.plot")
do
        gnuplot $f
done

for join in 2 10
do
for g in graph_1_*_${join}.tex
do
while read p; do
arrIN=(${p//=/ })
sed "s/strut{}${arrIN[0]}0*}/strut{}${arrIN[1]}}/g" -i $g
done <tmp/matrix_5_${join}.csv.map
done
done

mv graph_1_* tmp
