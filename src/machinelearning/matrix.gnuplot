#!/usr/bin/env gnuplot
set term svg size 850,600
set output "matrix131072.svg"
set datafile separator comma
set yrange reverse

set contour
set style data lines

set key center bottom vertical maxrows 7 outside
splot "figuresteps131072.csv" matrix rowheaders columnheaders notitle
