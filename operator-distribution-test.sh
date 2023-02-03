./gradlew assemble --offline  -q > /dev/null 2>&1

rm -rf *.svg *.dot
cmd=$(./launcher.main.kts --dryMode=Enable --run --mainClass=Launch_Simulator_Config | grep ^exec | sed "s/exec :: //g")

tasksfile="tasks.sh"
truncate -s0 $tasksfile
ctr=0
for optimizer in operator-distribution-test-optimizer-topology-assisted.json operator-distribution-test-optimizer-default.json operator-distribution-test-optimizer-topology-only.json
do
for sosa in src/luposdate3000_simulator_db/src/jvmMain/resources/ontology/campusSOSAInternalID.json src/luposdate3000_simulator_db/src/jvmMain/resources/ontology/campusSOSAInternalID10.json src/luposdate3000_simulator_db/src/jvmMain/resources/ontology/campusSOSAInternalID20.json
do
#for topology in src/luposdate3000_simulator_db/src/jvmMain/resources/topology/*16DB.json
for topology in src/luposdate3000_simulator_db/src/jvmMain/resources/topology/Random16DB.json
do
for dataDistribution in src/luposdate3000_simulator_db/src/jvmMain/resources/dataDistribution/luposdate3000_by_key.json src/luposdate3000_simulator_db/src/jvmMain/resources/dataDistribution/luposdate3000_by_id_*.json
do
for programDistribution in src/luposdate3000_simulator_db/src/jvmMain/resources/programDistribution/distributed*
do
for routing in src/luposdate3000_simulator_db/src/jvmMain/resources/routing/routing_AllShortestPath.json src/luposdate3000_simulator_db/src/jvmMain/resources/routing/routing_RPL_Fast.json
do
for joindistributionlocation in src/luposdate3000_simulator_db/src/jvmMain/resources/luposdate3000_distribution*
do
for relocateOperatorsIfTooMuchDataIsSent in src/luposdate3000_simulator_db/src/jvmMain/resources/relocateOperatorsIfTooMuchDataIsSent/*
do
c="$cmd \
$sosa \
$topology \
$programDistribution \
src/luposdate3000_simulator_db/src/jvmMain/resources/queries/SOSA_QueriesExtended.json \
$dataDistribution \
src/luposdate3000_simulator_db/src/jvmMain/resources/luposdate3000.json \
$joindistributionlocation \
src/luposdate3000_simulator_db/src/jvmMain/resources/multicast/luposdate3000MulticastEnabled.json \
$routing \
src/luposdate3000_simulator_db/src/jvmMain/resources/luposdate3000_local_execution_enabled.json \
$optimizer \
$relocateOperatorsIfTooMuchDataIsSent \
operator-distribution-test.json"
echo "echo $c into logfile_$ctr ; $c &> logfile_$ctr" >> $tasksfile
ctr=$((ctr+1))

done
done
done
done
done
done
done
done

cat $tasksfile | sed "s/java -Xmx100g/java -Xmx10g/g" | shuf > ${tasksfile}.tmp
mv ${tasksfile}.tmp $tasksfile
cat ${tasksfile} | parallel -j 20
