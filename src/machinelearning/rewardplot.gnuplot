#!/usr/bin/env gnuplot
set term svg
set datafile separator ','
set output 'rewardlog.svg'
set xrange [1000:]
set yrange [-1:11]
set grid xtics
set log x
plot "rewardlog_3_3cleaned.csv" using 1:2 with lines title "3_3" ,\
     "rewardlog_3_4cleaned.csv" using 1:2 with lines title "3_4" ,\
     "rewardlog_3_5cleaned.csv" using 1:2 with lines title "3_5" ,\
     "rewardlog_4_4cleaned.csv" using 1:2 with lines title "4_4" ,\
     "rewardlog_4_5cleaned.csv" using 1:2 with lines title "4_5" ,\
     "rewardlog_5_5cleaned.csv" using 1:2 with lines title "5_5"

