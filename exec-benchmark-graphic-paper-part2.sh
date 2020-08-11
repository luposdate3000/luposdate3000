#!/bin/bash
for query in $(find resources/lupos/ -type f | sed "s-.*/--g")
do
cat <<EOF > tmp/${query}_2.plot
set terminal png size 1920,1080
set output 'tmp/$(echo $query | sed "s/.sparql//g")_2.png'
set datafile separator ","
set key inside right top
set notitle
set logscale x
set logscale y
EOF
s=""
for f in tmp/*_$query
do
        x=$(echo $f | sed 's-tmp/--g' | sed "s/_${query}//g" | sed 's/_/-/g')
        s="$s, '$f' using 2:6 title \"$x\" with linespoints"
done
echo "plot${s:1}" >> tmp/${query}_2.plot
cat tmp/${query}_2.plot | gnuplot
done
