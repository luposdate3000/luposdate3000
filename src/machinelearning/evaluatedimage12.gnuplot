#!/usr/bin/env gnuplot
set term svg size 850,600
set output "figureevaluatedimage12.svg"
set datafile separator comma
YTICS="`cut -d, -f1 < figureevaluated12.csv`"
XTICS="`head -1 figureevaluated12.csv | sed 's/,/ /g'`"
set isosample 250, 250
set table 'test.dat'
splot "figureevaluated12.csv" matrix rowheaders columnheaders notitle
unset table
set contour base
set cntrparam bspline levels discrete 0,0.7,1
unset surface
set table 'cont.dat'
system("cat figureevaluated12.csv | ./extendForContour.py > extended.csv")
splot "extended.csv" matrix notitle
unset table
system("./fixContour.py")

reset
unset key
set title "evaluated on 12"
set cbrange [0:1]
set palette rgbformulae 33,13,10
set for [i=1:words(XTICS)] xtics ( word(XTICS,i+1) i-1 ) rotate by 45 right
set for [i=1:words(YTICS)] ytics ( word(YTICS,i+1) i-1 )
set xlabel "trained on"
set ylabel "training steps"
set cblabel "percentage of good queries"
p 'test.dat' with image, 'cont2.dat' w l lt -1 lw 1.5
