#!/bin/bash
for query in $(find resources/lupos/ -type f | sed "s-.*/--g")
do
cat <<EOF > tmp/legend_3.plot
set terminal epslatex
set output 'legend_3.tex'
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
for f in tmp/*_$query
do
        x=$(echo $f | sed 's-tmp/--g' | sed "s/_${query}//g" | sed 's/_/-/g')
        s="$s, 20 title \"$x\" with linespoints"
done
echo "plot${s:1}" >> tmp/legend_3.plot
cat tmp/legend_3.plot | gnuplot

############## plot 2 content
cat <<EOF > tmp/${query}_3.plot
set terminal epslatex
set output '$(echo $query | sed "s/.sparql//g")_3.tex'
set datafile separator ","
set nokey
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
echo "plot${s:1}" >> tmp/${query}_3.plot
cat tmp/${query}_3.plot | gnuplot
done
mv *.tex tmp
mv *.eps tmp
