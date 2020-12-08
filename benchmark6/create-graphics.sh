#!/usr/bin/bash

for f in $(ls stat* | sed "s/_[^_]*$//g" | sort | uniq)
do)
gnuplot << EOF
#gnuplot_terminal="set terminal png size 1920,1080"
#set output 'graph_2.png'
set terminal epslatex color
set output '$f.tex'
set datafile separator ','
set notitle
set key right bottom
set logscale x
set xlabel 'occurences'
set ylabel 'percentage of store'
#distribution function
set yrange [0:1]
set xrange [0:1]
plot '${f}_s.csv' using 1:6 with lines title "S", \
     '${f}_p.csv' using 1:6 with lines title "P", \
     '${f}_o.csv' using 1:6 with lines title "O", \
     '${f}_sp.csv' using 1:6 with lines title "SP", \
     '${f}_so.csv' using 1:6 with lines title "SO", \
     '${f}_po.csv' using 1:6 with lines title "PO", \
EOF
done
