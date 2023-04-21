#!/usr/bin/env gnuplot
set term tikz size 13cm,5cm
set datafile separator ','
set output 'ranking_on_3_t.tex'

set style data histogram
set style fill solid border

set style histogram clustered

#plot for [COL=2:8] 'ranking_on_3_t.csv' using COL:xticlabels(1) title columnheader
set rmargin 35
set key at screen 1, graph 1 reverse

set logscale y

set ytics nomirror
set y2tics nomirror
set yrange [0.1:]
set y2range [0.99:]

plot for [COL=2:11] 'ranking_on_3_t.csv' every 2:1:0 using COL:xticlabels(1) axes x1y1 title columnheader lc (COL-1), \
     newhistogram, \
     for [COL=2:11] 'ranking_on_3_t.csv' every 2:1:2 using COL:xticlabels(1) axes x1y2 notitle lc (COL-1)
