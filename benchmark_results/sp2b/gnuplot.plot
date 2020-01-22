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
#plot for [n=3:17] 'all-fuseki-server.csv' using 2: with lines title columnhead(n) dt 1, \
#     for [n=3:17] 'all-luposdate3000.csv' using 2: with lines title columnhead(n) dt 2
plot 'all-fuseki-server.csv' using 2:3  with lines title columnhead(3)  dt 1 lt rgb "red", \
     'all-fuseki-server.csv' using 2:4  with lines title columnhead(4)  dt 1 lt rgb "orange", \
     'all-fuseki-server.csv' using 2:5  with lines title columnhead(5)  dt 1 lt rgb "yellow", \
     'all-fuseki-server.csv' using 2:6  with lines title columnhead(6)  dt 1 lt rgb "green", \
     'all-fuseki-server.csv' using 2:7  with lines title columnhead(7)  dt 1 lt rgb "cyan", \
     'all-fuseki-server.csv' using 2:8  with lines title columnhead(8)  dt 1 lt rgb "blue", \
     'all-fuseki-server.csv' using 2:9  with lines title columnhead(9)  dt 1 lt rgb "violet", \
     'all-fuseki-server.csv' using 2:10 with lines title columnhead(10) dt 1 lt rgb "black", \
     'all-fuseki-server.csv' using 2:11 with lines title columnhead(11) dt 2 lt rgb "red", \
     'all-fuseki-server.csv' using 2:12 with lines title columnhead(12) dt 2 lt rgb "orange", \
     'all-fuseki-server.csv' using 2:13 with lines title columnhead(13) dt 2 lt rgb "yellow", \
     'all-fuseki-server.csv' using 2:14 with lines title columnhead(14) dt 2 lt rgb "green", \
     'all-fuseki-server.csv' using 2:15 with lines title columnhead(15) dt 2 lt rgb "cyan", \
     'all-fuseki-server.csv' using 2:16 with lines title columnhead(16) dt 2 lt rgb "blue", \
     'all-fuseki-server.csv' using 2:17 with lines title columnhead(17) dt 2 lt rgb "violet", \
     'all-luposdate3000.csv' using 2:3  with lines title columnhead(3)  dt 3 lt rgb "red", \
     'all-luposdate3000.csv' using 2:4  with lines title columnhead(4)  dt 3 lt rgb "orange", \
     'all-luposdate3000.csv' using 2:5  with lines title columnhead(5)  dt 3 lt rgb "yellow", \
     'all-luposdate3000.csv' using 2:6  with lines title columnhead(6)  dt 3 lt rgb "green", \
     'all-luposdate3000.csv' using 2:7  with lines title columnhead(7)  dt 3 lt rgb "cyan", \
     'all-luposdate3000.csv' using 2:8  with lines title columnhead(8)  dt 3 lt rgb "blue", \
     'all-luposdate3000.csv' using 2:9  with lines title columnhead(9)  dt 3 lt rgb "violet", \
     'all-luposdate3000.csv' using 2:10 with lines title columnhead(10) dt 3 lt rgb "black", \
     'all-luposdate3000.csv' using 2:11 with lines title columnhead(17) dt 4 lt rgb "red", \
     'all-luposdate3000.csv' using 2:12 with lines title columnhead(11) dt 4 lt rgb "orange", \
     'all-luposdate3000.csv' using 2:13 with lines title columnhead(12) dt 4 lt rgb "yellow", \
     'all-luposdate3000.csv' using 2:14 with lines title columnhead(13) dt 4 lt rgb "green", \
     'all-luposdate3000.csv' using 2:15 with lines title columnhead(14) dt 4 lt rgb "cyan", \
     'all-luposdate3000.csv' using 2:16 with lines title columnhead(15) dt 4 lt rgb "blue", \
     'all-luposdate3000.csv' using 2:17 with lines title columnhead(16) dt 4 lt rgb "violet"
