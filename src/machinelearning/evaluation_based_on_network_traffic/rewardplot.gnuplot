#!/usr/bin/env gnuplot
set term tikz size 9cm,5cm
set datafile separator ','
set output 'rewardlog.tex'
set xrange [1000:]
set yrange [-1:11]
set grid xtics
set xlabel "training steps"
set ylabel "reward"
set log x
plot "rewardlog_3_3cleaned.csv" using 1:2 with lines title "3-3" ,\
     "rewardlog_3_4cleaned.csv" using 1:2 with lines title "3-4" ,\
     "rewardlog_4_4cleaned.csv" using 1:2 with lines title "4-4" ,\
     "rewardlog_3_5cleaned.csv" using 1:2 with lines title "3-5" ,\
     "rewardlog_4_5cleaned.csv" using 1:2 with lines title "4-5" ,\
     "rewardlog_5_5cleaned.csv" using 1:2 with lines title "5-5" ,\
     "rewardlog_3_5cleaned.csv" using 1:2 with lines title "3-6" ,\
     "rewardlog_4_5cleaned.csv" using 1:2 with lines title "4-6" ,\
     "rewardlog_4_5cleaned.csv" using 1:2 with lines title "5-6" ,\
     "rewardlog_5_5cleaned.csv" using 1:2 with lines title "6-6"

