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

#datasource=/mnt/luposdate-testdata/btc2019/data/intermediate
datasource=/mnt/luposdate-testdata/bsbm/131072/intermediate
dataname=bsbm

curl -H "Content-Type: application/x-www-form-urlencoded" localhost:80/import/intermediate?query=$datasource
for f in exe*.sparql
do
	echo $f
	echo
	curl -H "Content-Type: application/x-www-form-urlencoded" -d "@$f" localhost:80/sparql/query > $f.srx
	cat $f.srx \
		| tr "\n" " " \
		| sed "s/ //g" \
		| sed "s/<\/result>/<\/result>\n/g" \
		| sed "s/.*<results>//g" \
		| sed "s/<\/results>.*//g" \
		| sed "s-www.w3.org/2001--g" \
		| sed "s/[^0-9]/;/g" \
		| sed "s/;[;]*/,/g" \
		| sed "s/^,//g" \
		| sed "s/,[0-9]*,$//g" \
		| sed "s/,/ /g" \
		| sort -n \
		| awk 'BEGIN {FS = " "} ; {sum+=$1*$2; print $1,$2,sum}' \
		| sed "s/ /,/g" \
		> $f.csv2
	m=$(cat $f.csv2 | tail -n 1 | sed "s/.*,//g")
	echo $m
	cat $f.csv2 | awk -v a="$m" 'BEGIN {FS = ","} ; {print $1,$2,$3,($3/a)}' | sed "s/ /,/g" > $f.csv
	rm $f.csv2
	tail $f.csv
done
gnuplot<<EOF
set terminal png size 800,600
set output 'exec.png'
set datafile separator ","
set key inside right bottom
set xlabel "occurences"
set ylabel "sum of (cnt * occurences)"
set logscale x
set title "plot occurences"
plot \
 'execS.sparql.csv' using 1:4 title "S" with lines, \
 'execP.sparql.csv' using 1:4 title "P" with lines, \
 'execO.sparql.csv' using 1:4 title "O" with lines, \
 'execSP.sparql.csv' using 1:4 title "SP" with lines, \
 'execSO.sparql.csv' using 1:4 title "SO" with lines, \
 'execPO.sparql.csv' using 1:4 title "PO" with lines
EOF

for f in exe*.srx; do mv $f $(echo $f | sed "s/exec/${dataname}_/g"); done
for f in exe*.csv; do mv $f $(echo $f | sed "s/exec/${dataname}_/g"); done
mv exec.png $dataname.png
