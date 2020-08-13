#!/bin/bash
for query in $(find resources/lupos/ -type f | sed "s-.*/--g")
do
for data in $(find tmp -name "*_${query}" | sed "s/_[0-9][0-9]*P.*//g" | sed "s-.*/v_--g"| sort | uniq)
do
gg=$(find tmp -name "*_${query}" | grep $data | sort -n)
############## plot 3 legend
cat <<EOF > tmp/legend_4.plot
set terminal epslatex
set output 'legend_4-$data.tex'
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
for f in $gg
do
        x=$(echo $f | sed 's-tmp/--g' | sed "s/_${query}//g" | sed 's/_/-/g' | sed "s/v-$data-//g")
        s="$s, 20 title \"$x\" with linespoints"
done
echo "plot${s:1}" >> tmp/legend_4.plot
cat tmp/legend_4.plot | gnuplot

############## plot 3 content
cat <<EOF > tmp/${query}_4_${data}.plot
set terminal epslatex
set output '$(echo $query | sed "s/.sparql//g")_4-${data}.tex'
set datafile separator ","
set nokey
set notitle
set logscale x
set logscale y
f(x)=-0.0020977709362774988 + 6.4290845584022814E-6 * x-5.643385504700015E-14 * x * x + 1.5546876220977975E-21 * x * x * x
EOF
s=""
for f in $gg
do
        s="$s, '$f' using 2:6 with linespoints"
done
s="$s, f(x) with lines"
echo "plot[1:10000000] ${s:1}" >> tmp/${query}_4_${data}.plot
cat tmp/${query}_4_${data}.plot | gnuplot
done
done
mv *.tex tmp
mv *.eps tmp
