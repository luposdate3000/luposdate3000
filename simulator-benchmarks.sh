#!/bin/bash

rm -rf simulator_output
git clean -xdf
./launcher.main.kts --setup --intellijMode=Disable --releaseMode=Enable
#./launcher.main.kts --setup --intellijMode=Disable
./gradlew assemble
cmd=$(./launcher.main.kts --run --mainClass=Launch_Simulator_Config --dryMode=Enable | grep exec | sed "s/exec :: //g")
declare -A baselineValues

first=true

BASE_PATH="src/luposdate3000_simulator_iot/src/jvmMain/resources"
EVALUATION_LOCATION="${BASE_PATH}/evaluation.json"
JSON_LOCATION="${BASE_PATH}/campus.json"
LUPOS_BASE_LOCATION="${BASE_PATH}/luposdate3000.json"
for q in Q0 Q3 Q2 Q1 Q4 Q5 Q6 Q7 Q8
#for q in Q3
do
JSON_QUERY="${BASE_PATH}/$q.json"
for t in distributed distributedWithQueryHops central
#for t in distributedWithQueryHops
#for t in distributed
do
JSON_TOPOLOGY="${BASE_PATH}/$t.json"
for d in luposdate3000_by_key luposdate3000_by_id_twice_all_collations luposdate3000_by_id_0_all_collations luposdate3000_by_id_1_all_collations luposdate3000_by_id_O_all_collations luposdate3000_by_id_S_all_collations luposdate3000_by_simple
#for d in luposdate3000_by_key
do
JSON_DATABASE="${BASE_PATH}/$d.json"
for m in Disabled Enabled
do
JSON_MULTICAST="${BASE_PATH}/luposdate3000Multicast${m}.json"
for dist in luposdate3000_distribution_routing luposdate3000_distribution_centralized
do
JSON_DIST="${BASE_PATH}/$dist.json"
echo $cmd $JSON_LOCATION $JSON_TOPOLOGY $JSON_QUERY $JSON_DATABASE $EVALUATION_LOCATION $LUPOS_BASE_LOCATION $JSON_DIST $JSON_MULTICAST
eval $cmd $JSON_LOCATION $JSON_TOPOLOGY $JSON_QUERY $JSON_DATABASE $EVALUATION_LOCATION $LUPOS_BASE_LOCATION $JSON_DIST $JSON_MULTICAST
echo "simulator_output/_campus_${t}_${q}_${d}_evaluation_luposdate3000_${dist}_luposdate3000Multicast${m}/measurement.csv"
headerLine="topology,database,query,dist,multicast,readwrite"
contentLine="${t},${d},${q},${m},${dist}"
if [ "$q" = "Q0" ]
then
contentLine="$contentLine,w"
else
contentLine="$contentLine,r"
fi
for idx in 11 12 14 15 18 19 23 24 26 29 30
do
headerLine="$headerLine,$(sed '1q;d' simulator_output/_campus_${t}_${q}_${d}_evaluation/measurement.csv | cut -f${idx} -d ',')"
value="$(sed '2q;d' simulator_output/_campus_${t}_${q}_${d}_evaluation/measurement.csv | cut -f${idx} -d ',')"
if [ "$q" = "Q0" ]
then
#baseline
baselineValues["${t},${d},${idx}"]="$value"
else
value=$(echo "$value - ${baselineValues["${t},${d},${idx}"]}" | bc)
fi
contentLine="$contentLine,${value}"
done
if $first
then
echo $headerLine >> simulator_output/final.csv
fi
echo $contentLine >> simulator_output/final.csv
first=false
done
done
done
done
done
