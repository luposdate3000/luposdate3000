#/bin/bash

#https://stackoverflow.com/questions/57061753/labeling-2d-contour-plot-from-table-in-gnuplot

for outputcount in 512 2048 8192 32768
do


./extract-data.main.kts results $outputcount

gnuplot << EOF
set contour base
#set cntrparam levels disc 16,8,4,2,1
set cntrparam levels disc 11,6,3,1.5,0.5

unset surface
set table 'contour.tmp'
set datafile separator ','
splot 'plot.map' matrix
unset table

EOF

cat contour.tmp | grep -v "#" | sed "s/\s\s*/,/g" | sed "s/^,//g" | sed "s/,$//g" | tr "\n" "#" | sed "s/###*/##/g" | tr "#" "\n" > contour.csv

gnuplot << EOF

set terminal png size 1920,1080
set output 'plot${outputcount}.png'

set xlabel "mergejoins"
set ylabel "selectivity"

set style textbox opaque noborder
set datafile separator ','

set xrange [-0.5:4.5]
set yrange [-0.5:16.5]

unset xtics
set xtics format " "
set xtics ($(cat plot.XLabels))

unset ytics
set ytics format " "
set ytics ($(cat plot.YLabels))

plot 'contour.csv' u 1:2 w l lw 1.5 notitle, 'plot.csv' u 1:2:3 w labels boxed notitle

EOF

rm -rf contour.csv contour.tmp plot.map plot.csv plot.XLabels plot.YLabels log
done
