#!/usr/bin/gnuplot
#gnuplot_terminal="set terminal png size 1920,1080"
#set output 'graph_2.png'
set terminal epslatex color
set datafile separator ','
set notitle
set key right bottom
set logscale x
set xlabel 'occurences'
set ylabel 'relative sum'
set yrange [0:1]
set xrange [1:*]
set output 'graph_2-sp2b33554432.tex'
plot 'ygraphicS.sparql-sp2b-33554432.srx.csv' using 1:6 with lines title "S", 'ygraphicP.sparql-sp2b-33554432.srx.csv' using 1:6 with lines title "P", 'ygraphicO.sparql-sp2b-33554432.srx.csv' using 1:6 with lines title "O", 'ygraphicSP.sparql-sp2b-33554432.srx.csv' using 1:6 with lines title "SP", 'ygraphicSO.sparql-sp2b-33554432.srx.csv' using 1:6 with lines title "SO", 'ygraphicPO.sparql-sp2b-33554432.srx.csv' using 1:6 with lines title "PO"
set xrange [1:*]
set output 'graph_2-btc2019.tex'
plot 'ygraphicS.sparql-btc-2019.srx.csv' using 1:6 with lines title "S", 'ygraphicP.sparql-btc-2019.srx.csv' using 1:6 with lines title "P", 'ygraphicO.sparql-btc-2019.srx.csv' using 1:6 with lines title "O", 'ygraphicSP.sparql-btc-2019.srx.csv' using 1:6 with lines title "SP", 'ygraphicSO.sparql-btc-2019.srx.csv' using 1:6 with lines title "SO", 'ygraphicPO.sparql-btc-2019.srx.csv' using 1:6 with lines title "PO"
set xrange [1:*]
set output 'graph_2-yago2.tex'
plot '/mnt/luposdate-testdata/yago2/ygraphicS.sparql.srx.csv' using 1:6 with lines title "S", '/mnt/luposdate-testdata/yago2/ygraphicP.sparql.srx.csv' using 1:6 with lines title "P", '/mnt/luposdate-testdata/yago2/ygraphicO.sparql.srx.csv' using 1:6 with lines title "O", '/mnt/luposdate-testdata/yago2/ygraphicSP.sparql.srx.csv' using 1:6 with lines title "SP", '/mnt/luposdate-testdata/yago2/ygraphicSO.sparql.srx.csv' using 1:6 with lines title "SO", '/mnt/luposdate-testdata/yago2/ygraphicPO.sparql.srx.csv' using 1:6 with lines title "PO"
