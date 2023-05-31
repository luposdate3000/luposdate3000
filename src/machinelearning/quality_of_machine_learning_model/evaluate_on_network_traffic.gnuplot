#!/usr/bin/env gnuplot
set term tikz size 13cm,8cm
#set term svg size 1600,800
set datafile separator ','
set output 'evaluate_on_network_traffic.tex'
#set output 'evaluate_on_network_traffic.svg'

set style data histogram
set style fill solid border

set style histogram clustered

set key above vertical maxrows 9

set yrange [0.99:]

plot newhistogram "evaluated on 3" at 0.5, \
     for [COL=2:7] 'ranking_on_3_t.csv' every 2:1:1 using COL:xticlabels(1) axes x1y1 notitle columnheader linecolor (COL-1) , \
     'ranking_on_3_t.csv' every 2:1:1 using 8:xticlabels(1) axes x1y1 notitle columnheader linecolor 1 fillstyle pattern 1, \
     'ranking_on_3_t.csv' every 2:1:1 using 9:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 1, \
     'ranking_on_3_t.csv' every 2:1:1 using 10:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 1, \
     'ranking_on_3_t.csv' every 2:1:1 using 11:xticlabels(1) axes x1y1 notitle columnheader linecolor 4 fillstyle pattern 1, \
     newhistogram "evaluated on 4" at 3, \
     for [COL=2:7] 'ranking_on_4_t.csv' every 2:1:1 using COL:xticlabels(1) axes x1y1 notitle columnheader linecolor (COL-1) , \
     'ranking_on_4_t.csv' every 2:1:1 using 8:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 1, \
     'ranking_on_4_t.csv' every 2:1:1 using 9:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 1, \
     'ranking_on_4_t.csv' every 2:1:1 using 10:xticlabels(1) axes x1y1 notitle columnheader linecolor 4 fillstyle pattern 1, \
     'ranking_on_4_t.csv' every 2:1:1 using 11:xticlabels(1) axes x1y1 notitle columnheader linecolor 1 fillstyle pattern 2, \
     'ranking_on_4_t.csv' every 2:1:1 using 12:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 2, \
     'ranking_on_4_t.csv' every 2:1:1 using 13:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 2, \
     newhistogram "evaluated on 5" at 5.5, \
     for [COL=2:7] 'ranking_on_5_t.csv' every 2:1:1 using COL:xticlabels(1) axes x1y1 notitle columnheader linecolor (COL-1) , \
     'ranking_on_5_t.csv' every 2:1:1 using 8:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 1, \
     'ranking_on_5_t.csv' every 2:1:1 using 9:xticlabels(1) axes x1y1 notitle columnheader linecolor 4 fillstyle pattern 1, \
     'ranking_on_5_t.csv' every 2:1:1 using 10:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 2, \
     'ranking_on_5_t.csv' every 2:1:1 using 11:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 2, \
     'ranking_on_5_t.csv' every 2:1:1 using 12:xticlabels(1) axes x1y1 notitle columnheader linecolor 1 fillstyle pattern 3, \
     'ranking_on_5_t.csv' every 2:1:1 using 13:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 3, \
     newhistogram "evaluated on 6" at 8, \
     for [COL=2:7] 'ranking_on_6_t.csv' every 2:1:1 using COL:xticlabels(1) axes x1y1 notitle columnheader linecolor (COL-1) , \
     'ranking_on_6_t.csv' every 2:1:1 using 8:xticlabels(1) axes x1y1 notitle columnheader linecolor 4 fillstyle pattern 1, \
     'ranking_on_6_t.csv' every 2:1:1 using 9:xticlabels(1) axes x1y1 notitle columnheader linecolor 3 fillstyle pattern 2, \
     'ranking_on_6_t.csv' every 2:1:1 using 10:xticlabels(1) axes x1y1 notitle columnheader linecolor 2 fillstyle pattern 3, \
     'ranking_on_6_t.csv' every 2:1:1 using 11:xticlabels(1) axes x1y1 notitle columnheader linecolor 7 , \
     newhistogram at 4.75, \
     for [COL=2:7] 'ranking_on_0_t.csv' every 4:1:1 using COL:xticlabels(1) axes x1y1 title columnheader linecolor (COL-1) , \
     'ranking_on_0_t.csv' every 4:1:1 using 8:xticlabels(1) axes x1y1 title columnheader linecolor 1 fillstyle pattern 1, \
     'ranking_on_0_t.csv' every 4:1:1 using 9:xticlabels(1) axes x1y1 title columnheader linecolor 2 fillstyle pattern 1, \
     'ranking_on_0_t.csv' every 4:1:1 using 10:xticlabels(1) axes x1y1 title columnheader linecolor 3 fillstyle pattern 1, \
     'ranking_on_0_t.csv' every 4:1:1 using 11:xticlabels(1) axes x1y1 title columnheader linecolor 4 fillstyle pattern 1, \
     'ranking_on_0_t.csv' every 4:1:1 using 12:xticlabels(1) axes x1y1 title columnheader linecolor 1 fillstyle pattern 2, \
     'ranking_on_0_t.csv' every 4:1:1 using 13:xticlabels(1) axes x1y1 title columnheader linecolor 2 fillstyle pattern 2, \
     'ranking_on_0_t.csv' every 4:1:1 using 14:xticlabels(1) axes x1y1 title columnheader linecolor 3 fillstyle pattern 2, \
     'ranking_on_0_t.csv' every 4:1:1 using 15:xticlabels(1) axes x1y1 title columnheader linecolor 1 fillstyle pattern 3, \
     'ranking_on_0_t.csv' every 4:1:1 using 16:xticlabels(1) axes x1y1 title columnheader linecolor 2 fillstyle pattern 3, \
     'ranking_on_0_t.csv' every 4:1:1 using 17:xticlabels(1) axes x1y1 title columnheader linecolor 7 
