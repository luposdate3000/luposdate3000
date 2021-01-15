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

for i in $(seq 1 7)
do
echo $(wc -l x${i}.ttl | sed "s/ .*//g"),x${i}.sparql,x${i}.ttl,x${i}.srx >> config.csv2
done

for i in $(seq 1 64)
do
for j in $(seq 1 2)
do
echo $(wc -l optional${j}.ttl | sed "s/ .*//g"),optional${i}.sparql,optional${j}.ttl,optional${j}-${i}.srx >> config.csv2
done
done

for f in $(find . -name "ygraphic*.sparql"| sed "s-./--g")
do
echo $(wc -l ygraphic.ttl | sed "s/ .*//g"),$f,ygraphic.ttl,ygraphic_$(echo $f | sed "s/sparql/srx/g") >> config.csv2
echo $(wc -l ygraphic2.ttl | sed "s/ .*//g"),$f,ygraphic2.ttl,ygraphic2_$(echo $f | sed "s/sparql/srx/g") >> config.csv2
done

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

