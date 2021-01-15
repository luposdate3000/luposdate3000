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

touch config.csv2
rm config.csv2

for triples2 in 1 200 500 900 1000 1400 32768
do
	(
		cd /opt/sp2b/bin
		./sp2b_gen -t $triples2 > /dev/null 2>&1
	)
	mkdir tmpdata
	cat /opt/sp2b/bin/sp2b.n3 | ./../../exec-compress-chunked-n3.main.kts tmpdata
	mv tmpdata/data0.n3 sp2b-$triples2.n3
	rm -rf tmpdata
	triples=$(wc -l sp2b-$triples2.n3 | sed "s/ .*//g")
	mv sp2b-$triples2.n3 sp2b-$triples.n3
	for q in $(find -name "q*.sparql" | sed "s/.sparql//g" | sed "s/.*q/q/g")
	do
		echo $triples,$q.sparql,sp2b-$triples.n3,$q-$triples.srx >> config.csv2
	done
done

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

