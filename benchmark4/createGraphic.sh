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

#set terminal png size 1920,1080
#set output 'graph_4_${outputcount}.png'
set terminal epslatex size 20cm,10cm
set output 'graph_4_${outputcount}.tex'

set xlabel "mergejoins"
set ylabel "selectivity" offset 4,0,0
set cblabel "optimal partitions"

set style textbox opaque noborder
set datafile separator ','

set xrange [-0.5:4.5]
set yrange [-0.5:17.5]

unset xtics
set xtics format " "
set xtics ($(cat plot.XLabels))

unset ytics
set ytics format " "
set ytics ($(cat plot.YLabels))
set palette model RGB maxcolors 5
set palette defined ( 0 0.5 0.5 0.5, 1 1 1 1 )
set logscale cb 2
set cbrange [0.75:24]
plot	'plot.map' matrix with image notitle, \
	'plot.csv' u 1:2:3 w labels notitle

EOF

rm -rf contour.csv contour.tmp plot.map plot.csv plot.XLabels plot.YLabels log
done
#	'contour.csv' u 1:2 w l lw 1.5 notitle,
