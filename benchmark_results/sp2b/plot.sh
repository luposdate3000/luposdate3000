#!/bin/bash
cat all.csv | grep "title" > all-fuseki-server.csv
cat all.csv | grep "fuseki-server" >> all-fuseki-server.csv
cat all.csv | grep "title" > all-luposdate3000.csv
cat all.csv | grep "luposdate3000" >> all-luposdate3000.csv
./gnuplot.plot
