#!/usr/bin/env gnuplot
set term svg enhanced mouse size 600,400
set output 'figure_64.svg'
set datafile separator comma
set yrange [0:1];
set logscale x 2
set format x '2^{%L}'
plot for [col=2:6] 'figure_64.csv' using 1:col with lines title columnhead
