#!/bin/bash
rm tmp/part_d* tmp/v*
gnuplot_terminal="set terminal epslatex"
gnuplot_terminal_ending="tex"
gnuplot_terminal="set terminal png size 1920,1080"
gnuplot_terminal_ending="png"
echo "${gnuplot_terminal}" > tmp/graph_8.plot
echo "set output 'graph_8.${gnuplot_terminal_ending}'" >> tmp/graph_8.plot
echo "set datafile separator ','" >> tmp/graph_8.plot
echo "set notitle" >> tmp/graph_8.plot
echo "set key inside right top" >> tmp/graph_8.plot
echo "set hidden3d" >> tmp/graph_8.plot
echo "set xlabel 'triples'" >> tmp/graph_8.plot
echo "set ylabel 'selectivity'" >> tmp/graph_8.plot
echo "set zlabel 'triples/second'" >> tmp/graph_8.plot
echo "set logscale x" >>tmp/graph_8.plot
echo "set view 45,300" >>tmp/graph_8.plot
echo "splot \\" >> tmp/graph_8.plot
for d in $(seq 1 6)
#for d in 1 6
do
	cat tmp/all.csv | grep "q2,[^,]*,1,$d," | sed -E "s/q2,(.),/q2,0\1,/g" | sort > tmp/part_d${d}.csv
	echo "'tmp/part_d${d}.matrix2.csv' matrix nonuniform title 'd${d}' with lines lc ${d}, \\" >> tmp/graph_8.plot
	cat tmp/part_d${d}.csv | sort -b -t ',' -k 2,2n -k 15,15n > tmp/xxx
	mv tmp/xxx tmp/part_d${d}.csv

	for x in $(cat tmp/part_d${d}.csv | cut -d ',' -f2 | sort -n | uniq)
	do
		row=""
		for z in $(cat tmp/part_d${d}.csv | cut -d ',' -f15 | sort -n | uniq)
		do
		row+=$(cat tmp/part_d${d}.csv \
				| awk "BEGIN { FS = \",\" } ; {if (\$2 == \"$x\") print \$0;}" \
			| awk "BEGIN { FS = \",\" } ; {if (\$15 == \"$z\") print \$0;}" \
			| cut -d ',' -f14)
			row+=","
		done
		echo $row | sed "s/,\./,0./g" | sed "s/^\./0./g" >> tmp/part_d${d}.matrix.csv
	done
	cat tmp/part_d${d}.csv | cut -d ',' -f2 | sort -n | uniq | tr "\n" "," > tmp/part_d${d}.matrix.rows.csv
	cat tmp/part_d${d}.csv | cut -d ',' -f15 | sort -n | uniq | tr "\n" "," > tmp/part_d${d}.matrix.columns.csv
        echo "xxx,$(cat tmp/part_d${d}.csv | cut -d ',' -f15 | sort -n | uniq| tr '\n' ',')" > tmp/part_d${d}.matrix2.csv
	for x in $(cat tmp/part_d${d}.csv | cut -d ',' -f2 | sort -n | uniq)
	do
		row=$x
#		row=$(echo "1 / ( 1 + ${x})" | bc -l)
		row+=","
		for z in $(cat tmp/part_d${d}.csv | cut -d ',' -f15 | sort -n | uniq)
		do
			tmp=$(cat tmp/part_d${d}.csv \
			| awk "BEGIN { FS = \",\" } ; {if (\$2 == \"$x\") print \$0;}" \
			| awk "BEGIN { FS = \",\" } ; {if (\$15 == \"$z\") print \$0;}" \
			| cut -d ',' -f14)
			row+=$(echo "1 / ${tmp}" | bc -l)
			row+=","
		done
		echo $row | sed "s/,\./,0./g" | sed "s/^\./0./g" >> tmp/part_d${d}.matrix2.csv
	done
done



for f in $(find tmp -name "graph_8*.plot")
do
	gnuplot $f
done
mv graph_8* tmp
#for f in $(find tmp -maxdepth 1 -type f -name '*.png' -empty | sed "s/\..*/.*/g" | sort | uniq)
#do
#	rm $f
#done


#cat tmp/part_d${d}.csv | sort -b -t ',' -k 2,2n -k 15,15n
