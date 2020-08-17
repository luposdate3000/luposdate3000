#!/bin/bash

for query in $(find resources/lupos/ -type f | sed "s-.*/--g")
do
for data in $(find tmp -name "*_${query}" | sed "s/_[0-9][0-9]*P.*//g" | sed "s-.*/v_--g"| sort | uniq)
do

                var_trash=$(echo $data | sed "s-.*T--g")
                var_join=$(echo $data | sed -E "s-.*T(.*)T.*-\1-g")
                var_predicates=$(echo $data | sed "s-T.*--g")
		var_q=$(echo $query | sed "s/.sparql//g")


gg=$(find tmp -name "*_${query}" | grep $data | sort -n)
############## plot legend
cat <<EOF > tmp/legend_5.plot
set terminal epslatex
set output 'legend_5-$data.tex'
set key inside right top
set noborder
set noxtics
set noytics
set notitle
set noxlabel
set noylabel
set xrange [-10:10]
set yrange [-10:10]
EOF
s=""
for i in $(seq 1 12)
do
	s="$s, 20 title \"$i\" with linespoints"
done
for f in $gg
do
        x=$(echo $f | sed 's-tmp/--g' | sed "s/_${query}//g" | sed 's/_/-/g' | sed "s/v-$data-//g")
        s="$s, 20 title \"$x\" with linespoints"
done
echo "plot${s:1}" >> tmp/legend_5.plot
cat tmp/legend_5.plot | gnuplot

############## plot content
cat <<EOF > tmp/${query}_5_${data}.plot
set terminal epslatex
set output '$(echo $query | sed "s/.sparql//g")_5-${data}.tex'
set datafile separator ","
set nokey
set notitle
set logscale x
set logscale y
set yrange [0:*]
set xrange [0:*]
FIT_LIMIT = 1e-14
EOF
s=""
#for i in $(seq 1 12)
for i in 1 12
do
	s="$s, f$i(x) with lines"
done
#for i in $(seq 1 12)
for i in 1 12
do
	var_filter="$var_q,$var_trash,$var_join,$i,$var_predicates"
	var_params=$(cat tmp/all.csv | grep "$var_filter" | sed "s/$var_filter,//g" | sed "s/,[^,]*,[^,]*,[^,]*$//g" | sed "s/,.*,/,/g" | tr '\n' ';')
	var_func=$(java -jar /src/curveFitting/target/curveFitting-1.0-jar-with-dependencies.jar ${var_params})
	cat tmp/all.csv | grep "$var_filter" > tmp/${query}_5_${data}_$i.csv
	if [ -z "$var_func" ]; then
		var_func=-1
	fi
	echo "f$i(x)=$var_func" >>tmp/${query}_5_${data}.plot

#	echo "a$i=0.1;b$i=0.1;c$i=0.1">>tmp/${query}_5_${data}.plot
#	echo "f$i(x)=a$i*exp(x)+b$i*x+c$i" >>tmp/${query}_5_${data}.plot
##	echo "f$i(x)=a$i+b$i*x+c$i*x*x" >>tmp/${query}_5_${data}.plot
#	echo "fit [1:100000000] f$i(x) 'tmp/${query}_5_${data}_$i.csv' using 6:10 via a$i, b$i, c$i" >>tmp/${query}_5_${data}.plot

	s="$s, 'tmp/${query}_5_${data}_$i.csv' using 6:10 with linespoints"
done
echo "plot [1024:1000000000] ${s:1}" >> tmp/${query}_5_${data}.plot


cat tmp/${query}_5_${data}.plot | gnuplot
done
done
mv *.tex tmp
mv *.eps tmp
