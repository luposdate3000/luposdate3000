#/bin/bash
# This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
# Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, version 3.
#
# This program is distributed in the hope that it will be useful, but
# WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program. If not, see <http://www.gnu.org/licenses/>.

#https://stackoverflow.com/questions/57061753/labeling-2d-contour-plot-from-table-in-gnuplot

for outputcount in 512 2048 8192 32768
do


./extract-data.main.kts results $outputcount

gnuplot << EOF

#set terminal png size 1920,1080
#set output 'graph_4_${outputcount}.png'
set terminal epslatex size 20cm,8cm
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

rm -rf plot.map plot.csv plot.XLabels plot.YLabels log
done
