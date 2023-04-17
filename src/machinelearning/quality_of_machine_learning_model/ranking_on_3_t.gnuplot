#!/usr/bin/env gnuplot
set term svg size 800,600 enhanced
set datafile separator ','
set output 'ranking_on_3_t.svg'

set style data histogram
set style fill solid border

set style histogram clustered
plot for [COL=2:8] 'ranking_on_3_t.csv' using COL:xticlabels(1) title columnheader
#plot 'ranking_on_3_t.csv' using 2:xticlabels(1) title "rdf3x", \
#     '' using 3:xticlabels(1) title "jena", \
#     '' using 4:xticlabels(1) title "graphdb", \
#     '' using 5:xticlabels(1) title "model 3-6", \
#     '' using 6:xticlabels(1) title "model 3-5", \
#     '' using 7:xticlabels(1) title "model 3-4", \
#     '' using 8:xticlabels(1) title "model 3"
