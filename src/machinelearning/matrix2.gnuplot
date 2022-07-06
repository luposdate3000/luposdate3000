#!/usr/bin/env gnuplot
#set term svg size 850,600
#set output "matrix2.svg"
set term png size 800,600
set output "matrix2.png"
set datafile separator comma

YTICS="`cut -d, -f1 < figuresteps131072.csv`"
XTICS="`head -1 figuresteps131072.csv | sed 's/,/ /g'`"


set xrange [-2:20]
set yrange [-2:20]
set isosample 150, 150
set table 'test.dat'
splot "figuresteps131072.csv" matrix rowheaders columnheaders notitle
unset table
set cont base
set cntrparam level incremental 0, 0.1, 1
unset surf
set table 'cont.dat'
splot "figuresteps131072.csv" matrix rowheaders columnheaders notitle
unset table
reset
set xrange [-2:20]
set yrange [-2:20]
unset key
set palette rgbformulae 33,13,10
l '<./cont.sh cont.dat 0 15 0'

set for [i=1:words(XTICS)] xtics ( word(XTICS,i+1) i-1 )
set for [i=1:words(YTICS)] ytics ( word(YTICS,i+1) i-1 )

p 'test.dat' w ima, '<./cont.sh cont.dat 1 15 0' w l lt -1 lw 1.5




#   http://www.phyast.pitt.edu/~zov1/gnuplot/html/contour.html
