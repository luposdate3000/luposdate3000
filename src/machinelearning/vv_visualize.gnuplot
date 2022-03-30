#!/usr/bin/env gnuplot
set terminal png
set datafile separator ","
set output 'results_abs.png'
plot "results_abs.csv" matrix rowheaders columnheaders with image notitle
set output 'results_rel.png'
plot "results_rel.csv" matrix rowheaders columnheaders with image notitle
set output 'time_abs.png'
plot "time_abs.csv" matrix rowheaders columnheaders with image notitle
set output 'time_rel.png'
plot "time_rel.csv" matrix rowheaders columnheaders with image notitle
set output 'networkTraffic_abs.png'
plot "networkTraffic_abs.csv" matrix rowheaders columnheaders with image notitle
set output 'networkTraffic_rel.png'
plot "networkTraffic_rel.csv" matrix rowheaders columnheaders with image notitle
