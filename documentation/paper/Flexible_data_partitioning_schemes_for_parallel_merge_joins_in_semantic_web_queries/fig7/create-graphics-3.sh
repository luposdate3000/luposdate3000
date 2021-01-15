#!/bin/bash
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

gnuplot_terminal="set terminal epslatex color size 13cm,13cm"
gnuplot_terminal_ending="tex"
#gnuplot_terminal="set terminal png size 1920,1080"
#gnuplot_terminal="set terminal png size 800,600"
#gnuplot_terminal_ending="png"

rm tmp/graph_3*
for var_query in q2 q3 q4 q5 q6 q7 q8 q9 q10
do
	hastitle=0
	echo "${gnuplot_terminal}" > tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "set output 'graph_3_r_var_result_rows_${var_query}.${gnuplot_terminal_ending}'" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "set datafile separator ','" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "set notitle" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "set logscale y" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "set logscale x 2" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "unset key" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "set xlabel 'selectivity'" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "set ylabel 'ms per result row' offset 3,0,0" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "set format x \"$\\\\frac{1}{1+2^{%L}}$\"" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	echo "set multiplot layout 2,1 rowsfirst" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
echo "set rmargin screen 0.65" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
echo "set lmargin screen 0.1" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
	for var_result_rows in 128 32768
	do
		echo "set title \"$var_result_rows result rows\"" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
if [ "$hastitle" = "0" ]
then
echo "set key reverse Left at screen 1.1, 0.9" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
fi
		echo "plot \\" >>tmp/graph_3_r_var_result_rows_${var_query}.plot
		i=0
		for var_database in blazegraph jena virtuoso
		do
thetitle="notitle"
			echo "tmp/makro_filtered_${var_database}_1_${var_query}_r${var_result_rows}.csv"
			if [ -f "tmp/makro_filtered_${var_database}_1_${var_query}_r${var_result_rows}.csv" ]
			then
if [ "$hastitle" = "0" ]
then
thetitle="title '${var_database}'"
fi
				echo "  'tmp/makro_filtered_${var_database}_1_${var_query}_r${var_result_rows}.csv' using 1:2:3:4 with yerrorlines ${thetitle},\\" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
				i=1
			fi
		done
		echo "tmp/makro_filtered_luposdate_1_${var_query}_r${var_result_rows}.csv"
		if [ -f "tmp/makro_filtered_luposdate_1_${var_query}_r${var_result_rows}.csv" ]
		then
if [ "$hastitle" = "0" ]
then
thetitle="title 'luposdate-MEMORY'"
fi
			echo "  'tmp/makro_filtered_luposdate_1_${var_query}_r${var_result_rows}.csv' using 1:2:3:4 with yerrorlines ${thetitle},\\" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
			i=1
		fi
		echo "tmp/makro_filtered_luposdate-parallel_1_${var_query}_r${var_result_rows}.csv"
		if [ -f "tmp/makro_filtered_luposdate-parallel_1_${var_query}_r${var_result_rows}.csv" ]
		then
if [ "$hastitle" = "0" ]
then
thetitle="title 'luposdate-RDF3X'"
fi
			echo "  'tmp/makro_filtered_luposdate-parallel_1_${var_query}_r${var_result_rows}.csv' using 1:2:3:4 with yerrorlines ${thetitle},\\" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
			i=1
		fi
		for var_process in 1 6 12 24
		do
			echo "tmp/makro_filtered_luposdate3000_${var_process}_${var_query}_r${var_result_rows}.csv"
			if [ -f "tmp/makro_filtered_luposdate3000_${var_process}_${var_query}_r${var_result_rows}.csv" ]
			then
if [ "$hastitle" = "0" ]
then
thetitle="title '\\textbf{luposdate3000($var_process)}'"
fi
				echo "  'tmp/makro_filtered_luposdate3000_${var_process}_${var_query}_r${var_result_rows}.csv' using 1:2:3:4 with yerrorlines ${thetitle},\\" >> tmp/graph_3_r_var_result_rows_${var_query}.plot
				i=1
			fi
		done
		echo "">>tmp/graph_3_r_var_result_rows_${var_query}.plot
		if [ "$i" == "0" ]
		then
			rm tmp/graph_3_r_var_result_rows_${var_query}.plot
		fi
	hastitle=1
	done
done


for f in $(find tmp -name "graph_3_*.plot")
do
        gnuplot $f
done
mv graph_3_* tmp
