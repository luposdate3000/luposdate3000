#!/usr/bin/env gnuplot
set term svg size 800,600 enhanced
set datafile separator ','
set output 'ranking_on_6_t.svg'

set style data histogram
set style fill solid border

set style histogram clustered
plot for [COL=2:8] 'ranking_on_6_t.csv' using COL:xticlabels(1) title columnheader
