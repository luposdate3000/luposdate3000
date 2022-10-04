#!/usr/bin/env gnuplot
#set term tikz size 8.5cm,6cm
#set output "compare_time_and_intermediates.tex"
set term svg size 850,600
set output "compare_time_and_intermediates.svg"

set datafile separator comma
plot 'compare_time_and_intermediates.csv' using 1:2 with steps title columnhead, \
     'compare_time_and_intermediates.csv' using 1:3 with steps title columnhead, \
     'compare_time_and_intermediates.csv' using 1:4 with steps title columnhead, \
     'compare_time_and_intermediates.csv' using 1:5 with steps title columnhead, \
     'compare_time_and_intermediates.csv' using 1:6 with steps title columnhead, \
     'compare_time_and_intermediates.csv' using 1:7 with steps title columnhead, \
     'compare_time_and_intermediates.csv' using 1:8 with steps title columnhead, \
     'compare_time_and_intermediates.csv' using 1:9 with steps title columnhead, \
