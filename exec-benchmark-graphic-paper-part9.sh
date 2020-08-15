#!/bin/bash
column_x=2
column_z=7
column_y=11

rm tmp/part_d* tmp/v*
gnuplot_terminal="set terminal epslatex"
gnuplot_terminal_ending="tex"
gnuplot_terminal="set terminal png size 1920,1080"
gnuplot_terminal_ending="png"
echo "${gnuplot_terminal}" > tmp/graph_9.plot
echo "set output 'graph_9.${gnuplot_terminal_ending}'" >> tmp/graph_9.plot
echo "set datafile separator ','" >> tmp/graph_9.plot
echo "set notitle" >> tmp/graph_9.plot
echo "set key inside right top" >> tmp/graph_9.plot
echo "set hidden3d" >> tmp/graph_9.plot
echo "set xlabel 'triples'" >> tmp/graph_9.plot
echo "set ylabel 'selectivity' " >> tmp/graph_9.plot
echo "set zlabel 'triples/second'" >> tmp/graph_9.plot
echo "set logscale x" >>tmp/graph_9.plot
echo "set view 45,300" >>tmp/graph_9.plot
echo "splot \\" >> tmp/graph_9.plot
#for d in $(seq 1 6)
for d in 1
do
	v=$(( $d - 1))
        r=$(( ( ( $v % 6 ) * 256 ) / 6))
        g=$(( ( ( $v / 6 ) * 256 ) / 6))
        b="0"
        c=$(( ( ( $r * 256 ) + $g ) * 256 + $b ))
	c2=$(( 256*256*256 * $v / 24))

	cat tmp/all.csv | grep "q2,[^,]*,1,$d," | sed -E "s/q2,(.),/q2,0\1,/g" | sort > tmp/part_d${d}.csv
	echo "'tmp/part_d${d}.matrix2.csv' matrix nonuniform title '${d}' with linespoints lc ${d} pt 1, \\" >> tmp/graph_9.plot
	cat tmp/part_d${d}.csv | sort -b -t ',' -k ${column_x},${column_x}n -k ${column_z},${column_z}n > tmp/xxx
	mv tmp/xxx tmp/part_d${d}.csv

	for x in $(cat tmp/part_d${d}.csv | cut -d ',' -f${column_x} | sort -n | uniq)
	do
		row=""
		for z in $(cat tmp/part_d${d}.csv | cut -d ',' -f${column_z} | sort -n | uniq)
		do
		row+=$(cat tmp/part_d${d}.csv \
				| awk "BEGIN { FS = \",\" } ; {if (\$${column_x} == \"$x\") print \$0;}" \
			| awk "BEGIN { FS = \",\" } ; {if (\$${column_z} == \"$z\") print \$0;}" \
			| cut -d ',' -f${column_y})
			row+=","
		done
		echo $row | sed "s/,\./,0./g" | sed "s/^\./0./g" >> tmp/part_d${d}.matrix.csv
	done
	cat tmp/part_d${d}.csv | cut -d ',' -f${column_x} | sort -n | uniq | tr "\n" "," > tmp/part_d${d}.matrix.rows.csv
	cat tmp/part_d${d}.csv | cut -d ',' -f${column_z} | sort -n | uniq | tr "\n" "," > tmp/part_d${d}.matrix.columns.csv
        echo "xxx,$(cat tmp/part_d${d}.csv | cut -d ',' -f${column_z} | sort -n | uniq| tr '\n' ',')" > tmp/part_d${d}.matrix2.csv
	for x in $(cat tmp/part_d${d}.csv | cut -d ',' -f${column_x} | sort -n | uniq)
	do
		row=$x
#		row=$(echo "1 / ( 1 + ${x})" | bc -l)
		row+=","
		for z in $(cat tmp/part_d${d}.csv | cut -d ',' -f${column_z} | sort -n | uniq)
		do
			tmp=$(cat tmp/part_d${d}.csv \
			| awk "BEGIN { FS = \",\" } ; {if (\$${column_x} == \"$x\") print \$0;}" \
			| awk "BEGIN { FS = \",\" } ; {if (\$${column_z} == \"$z\") print \$0;}" \
			| cut -d ',' -f${column_y})
			row+=$(echo "1 / ${tmp}" | bc -l)
			row+=","
		done
		echo $row | sed "s/,\./,0./g" | sed "s/^\./0./g" >> tmp/part_d${d}.matrix2.csv
	done
done



for f in $(find tmp -name "graph_9*.plot")
do
	gnuplot $f
done
mv graph_9* tmp
