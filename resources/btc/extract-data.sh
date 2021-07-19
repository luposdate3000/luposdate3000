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
cat *.sparql \
| grep -v "^#" \
| sed 's/"[^"]*"//g' \
| tr " " "\n" \
| grep "...*" \
| grep -v "^?" \
| grep -vi "distinct" \
| grep -vi "where" \
| grep -vi "select" \
| grep -v "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>" \
| sort \
| uniq > x
cat *.sparql \
| grep -v "^#" \
| sed 's/^[^"]*//' \
| sed 's/[^"]*$//g' \
| grep "...*" \
| sort \
| uniq >> x
IFS=$'\n'
for i in $(cat x)
do
	f="data$(echo $i | sed 's/[^a-zA-Z0-9]/_/g').n3"
	if [ -f "$f" ]; then
		echo "$f exist"
	else
		(
			LC_ALL=C
			echo "start with $i"
			parallel -a /src/luposdate3000/btc-2019-triples.nt --pipepart grep -f "'$i'"  > $f
			echo "done with $i"
		)
	fi
done
wait

rm config.csv2
for f in $(find . -name "*.sparql" | sort)
do
	if [ -f "$f.n3" ]
	then
		echo "$f.n3 exist"
	else
		rm $f.n2
		for x in $(cat $f | tr " " "\n" | sort | uniq | grep -v "^?" | sort)
		do
			i="data$(echo $x | sed 's/[^a-zA-Z0-9]/_/g').n3"
			echo $f $i
			cat $i >> $f.n2
		done
		sort -u $f.n2 > $f.n3
		rm $f.n2
	fi
	echo $(wc -l $f.n3 | sed "s/ .*//g"),$f,$f.n3,$f.srx >> config.csv2
done

rm x
cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2
