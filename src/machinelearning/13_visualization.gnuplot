#!/usr/bin/env gnuplot
set terminal png
set datafile separator ","
set output 'plot.png'
set xlabel "rank"
set ylabel "percent (accumulated)"
set yrange [0:1]
set xrange [0:15]
set key right bottom
plot "luposTime.csv" using 1:4 with lines title "lupos time absolute", \
     "luposTime.csv" using 1:5 with lines title "lupos time relative", \
     "luposResults.csv" using 1:4 with lines title "lupos intermediate-results absolute", \
     "luposResults.csv" using 1:5 with lines title "lupos intermediate-results relative", \
     "randomTime.csv" using 1:4 with lines title "random time absolute", \
     "randomTime.csv" using 1:5 with lines title "random time relative", \
     "randomResults.csv" using 1:4 with lines title "random intermediate-results absolute", \
     "randomResults.csv" using 1:5 with lines title "random intermediate-results relative"

