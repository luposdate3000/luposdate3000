#!/bin/gnuplot

set terminal svg
set output "all.svg"
set key fixed right top vertical Right noreverse enhanced autotitle columnhead nobox

set title "thetitle"
set style data linespoints
set ylabel "query/second"
set xlabel "triples"
set xrange [*:*]
set yrange [*:*]

set logscale x

set datafile separator ","

set key autotitle columnhead
plot for [n=3:17] 'all-fuseki-server.csv' using 2:n with lines title columnhead(n), \
     for [n=3:17] 'all-luposdate3000.csv' using 2:n with lines title columnhead(n)
