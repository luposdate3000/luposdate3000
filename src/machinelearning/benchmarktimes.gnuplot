#!/usr/bin/env gnuplot
set term tikz size 8.5cm,6cm
#set term svg size 850,600
set output 'benchmarktimes.tex'
set datafile separator ","
set key top left

set xlabel 'triple patterns'
set ylabel 'time (seconds)'
set logscale y
plot 'benchmarktimes.csv' u 1:2 title columnhead w lp, \
     ''      u 1:3 title columnhead w lp, \
     ''      u 1:4 title columnhead w lp
