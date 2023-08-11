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

function generateConfig() {
ctr=$1
sparql=$2
input=$3
output=$4

echo '{"query":' > x
cat $sparql |jq -Rsa . >> x
echo ',"rdf":' >> x
cat $input |jq -Rsa . >> x
echo ',"formats":["xml","plain"],"inference":"NONE","inferenceGeneration":"GENERATEDOPT","evaluator":"MemoryIndex"}' >> x
curl 'https://www.ifis.uni-luebeck.de/sparql-endpoint/nonstandard/sparql' \
-X 'POST' \
-d @x \
| jq -rc '.XML[0]' > $output

echo $1,$2,$3,$4 >> config.csv2
}

for i in $(seq 1 7)
do
generateConfig "$(wc -l x${i}.ttl | sed 's/ .*//g')" "x${i}.sparql" "x${i}.ttl" "x${i}.srx"
done

for i in $(seq 1 64)
do
for j in $(seq 1 2)
do
generateConfig "$(wc -l optional${j}.ttl | sed "s/ .*//g")" "optional${i}.sparql" "optional${j}.ttl" "optional${j}-${i}.srx"
done
done

for i in 1 2 3 4 5 6 7 8 \
 2_1 3_1 3_2 4_1 4_2 5_1 6_1 6_2 7_1 7_2 8_1 8_2 \
 2_1_1 2_1_2 2_1_3 3_1_1 3_1_2 3_1_3 3_1_4 3_1_5 3_1_6 3_1_7 4_1_1 4_1_2 4_1_3 4_1_4 4_1_5 4_1_6 4_1_7 5_1_1 5_1_2 5_1_3 5_1_4 6_1_1 6_1_2 6_1_3 6_1_4 6_1_5 6_1_6 6_2_1 7_1_1 7_2_1 7_2_2 8_1_1 8_2_1 \
 8_1_2 8_1_3 8_1_4 8_1_5 8_1_6 8_1_7
do
generateConfig "$(wc -l simulator_parking_input.ttl)" "simulator_parking_query$i.sparql" "simulator_parking_input.ttl" "simulator_parking_result$i.srx"
generateConfig "$(wc -l simulator_parking_input_small.ttl)" "simulator_parking_query$i.sparql" "simulator_parking_input_small.ttl" "simulator_parking_result${i}_small.srx"
done

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

