#!/usr/bin/env gnuplot
#set term tikz size 13cm,5cm
set term svg size 1600,800
set datafile separator ','
#set output 'evaluate_on_network_traffic.tex'
set output 'evaluate_on_network_traffic.svg'

set style data histogram
set style fill solid border

set style histogram clustered

#plot for [COL=2:8] 'ranking_on_3_t.csv' using COL:xticlabels(1) title columnheader
set rmargin 35
set key inside vertical right top

set yrange [0.99:]

plot newhistogram "evaluated on 3", \
     for [COL=2:7] 'ranking_on_3_t.csv' every 2:1:1 using COL:xticlabels(1) axes x1y1 notitle columnheader linecolor (COL-1) fillstyle pattern 3, \
     'ranking_on_3_t.csv' every 2:1:1 using 8:xticlabels(1) axes x1y1 notitle columnheader linecolor 1 fillstyle pattern 4, \
     'ranking_on_3_t.csv' every 2:1:1 using 9:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 4, \
     'ranking_on_3_t.csv' every 2:1:1 using 10:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 4, \
     'ranking_on_3_t.csv' every 2:1:1 using 11:xticlabels(1) axes x1y1 notitle columnheader linecolor 4 fillstyle pattern 4, \
     newhistogram "evaluated on 4", \
     for [COL=2:7] 'ranking_on_4_t.csv' every 2:1:1 using COL:xticlabels(1) axes x1y1 notitle columnheader linecolor (COL-1) fillstyle pattern 3, \
     'ranking_on_4_t.csv' every 2:1:1 using 8:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 4, \
     'ranking_on_4_t.csv' every 2:1:1 using 9:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 4, \
     'ranking_on_4_t.csv' every 2:1:1 using 10:xticlabels(1) axes x1y1 notitle columnheader linecolor 4 fillstyle pattern 4, \
     'ranking_on_4_t.csv' every 2:1:1 using 11:xticlabels(1) axes x1y1 notitle columnheader linecolor 1 fillstyle pattern 5, \
     'ranking_on_4_t.csv' every 2:1:1 using 12:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 5, \
     'ranking_on_4_t.csv' every 2:1:1 using 13:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 5, \
     newhistogram "evaluated on 5", \
     for [COL=2:7] 'ranking_on_5_t.csv' every 2:1:1 using COL:xticlabels(1) axes x1y1 notitle columnheader linecolor (COL-1) fillstyle pattern 3, \
     'ranking_on_5_t.csv' every 2:1:1 using 8:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 4, \
     'ranking_on_5_t.csv' every 2:1:1 using 9:xticlabels(1) axes x1y1 notitle columnheader linecolor 4 fillstyle pattern 4, \
     'ranking_on_5_t.csv' every 2:1:1 using 10:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 5, \
     'ranking_on_5_t.csv' every 2:1:1 using 11:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 5, \
     'ranking_on_5_t.csv' every 2:1:1 using 12:xticlabels(1) axes x1y1 notitle columnheader linecolor 1 fillstyle pattern 1, \
     'ranking_on_5_t.csv' every 2:1:1 using 13:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 1, \
     newhistogram "evaluated on 6", \
     for [COL=2:7] 'ranking_on_6_t.csv' every 2:1:1 using COL:xticlabels(1) axes x1y1 notitle columnheader linecolor (COL-1) fillstyle pattern 3, \
     'ranking_on_6_t.csv' every 2:1:1 using 8:xticlabels(1) axes x1y1 notitle columnheader linecolor 4 fillstyle pattern 4, \
     'ranking_on_6_t.csv' every 2:1:1 using 9:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 5, \
     'ranking_on_6_t.csv' every 2:1:1 using 10:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 1, \
     'ranking_on_6_t.csv' every 2:1:1 using 11:xticlabels(1) axes x1y1 notitle columnheader linecolor 7 fillstyle pattern 3, \
     newhistogram, \
     for [COL=2:7] 'ranking_on_0_t.csv' every 2:1:1 using COL:xticlabels(1) axes x1y1 title columnheader linecolor (COL-1) fillstyle pattern 3, \
     'ranking_on_0_t.csv' every 2:1:1 using 8:xticlabels(1) axes x1y1 title columnheader linecolor 1 fillstyle pattern 4, \
     'ranking_on_0_t.csv' every 2:1:1 using 9:xticlabels(1) axes x1y1 title columnheader linecolor 2 fillstyle pattern 4, \
     'ranking_on_0_t.csv' every 2:1:1 using 10:xticlabels(1) axes x1y1 title columnheader linecolor 3 fillstyle pattern 4, \
     'ranking_on_0_t.csv' every 2:1:1 using 11:xticlabels(1) axes x1y1 title columnheader linecolor 4 fillstyle pattern 4, \
     'ranking_on_0_t.csv' every 2:1:1 using 12:xticlabels(1) axes x1y1 title columnheader linecolor 1 fillstyle pattern 5, \
     'ranking_on_0_t.csv' every 2:1:1 using 13:xticlabels(1) axes x1y1 title columnheader linecolor 2 fillstyle pattern 5, \
     'ranking_on_0_t.csv' every 2:1:1 using 14:xticlabels(1) axes x1y1 title columnheader linecolor 3 fillstyle pattern 5, \
     'ranking_on_0_t.csv' every 2:1:1 using 15:xticlabels(1) axes x1y1 title columnheader linecolor 1 fillstyle pattern 1, \
     'ranking_on_0_t.csv' every 2:1:1 using 16:xticlabels(1) axes x1y1 title columnheader linecolor 2 fillstyle pattern 1, \
     'ranking_on_0_t.csv' every 2:1:1 using 17:xticlabels(1) axes x1y1 title columnheader linecolor 7 fillstyle pattern 3
