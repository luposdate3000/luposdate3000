./gradlew assemble --offline  -q > /dev/null 2>&1

rm -rf *.svg *.dot simulator_output
cmd=$(./launcher.main.kts --dryMode=Enable --run --mainClass=Launch_Simulator_Config | grep ^exec | sed "s/exec :: //g")

for topology in src/luposdate3000_simulator_db/src/jvmMain/resources/topology/*16DB.json
do
#for dataDistribution in src/luposdate3000_simulator_db/src/jvmMain/resources/dataDistribution/luposdate3000_by_key.json src/luposdate3000_simulator_db/src/jvmMain/resources/dataDistribution/luposdate3000_by_id_S_all_collations.json
for dataDistribution in src/luposdate3000_simulator_db/src/jvmMain/resources/dataDistribution/luposdate3000_by_id_S_all_collations.json
do
for programDistribution in src/luposdate3000_simulator_db/src/jvmMain/resources/programDistribution/distributed*
do
for routing in src/luposdate3000_simulator_db/src/jvmMain/resources/routing/routing_AllShortestPath.json src/luposdate3000_simulator_db/src/jvmMain/resources/routing/routing_RPL_Fast.json
do
for optimizer in operator-distribution-test-optimizer-topology-assisted.json operator-distribution-test-optimizer-default.json
do
echo $cmd \
src/luposdate3000_simulator_db/src/jvmMain/resources/ontology/campusSOSAInternalID.json \
$topology \
$programDistribution \
src/luposdate3000_simulator_db/src/jvmMain/resources/queries/SOSA_Queries.json \
$dataDistribution \
src/luposdate3000_simulator_db/src/jvmMain/resources/luposdate3000.json \
src/luposdate3000_simulator_db/src/jvmMain/resources/luposdate3000_distribution_routing.json \
src/luposdate3000_simulator_db/src/jvmMain/resources/multicast/luposdate3000MulticastEnabled.json \
$routing \
src/luposdate3000_simulator_db/src/jvmMain/resources/luposdate3000_local_execution_disabled.json \
$optimizer \
operator-distribution-test.json

$cmd \
src/luposdate3000_simulator_db/src/jvmMain/resources/ontology/campusSOSAInternalID.json \
$topology \
$programDistribution \
src/luposdate3000_simulator_db/src/jvmMain/resources/queries/SOSA_Queries.json \
$dataDistribution \
src/luposdate3000_simulator_db/src/jvmMain/resources/luposdate3000.json \
src/luposdate3000_simulator_db/src/jvmMain/resources/luposdate3000_distribution_routing.json \
src/luposdate3000_simulator_db/src/jvmMain/resources/multicast/luposdate3000MulticastEnabled.json \
$routing \
src/luposdate3000_simulator_db/src/jvmMain/resources/luposdate3000_local_execution_disabled.json \
$optimizer \
operator-distribution-test.json

done
done
done
done
done

exit
for f in *.dot
do
   echo fdp $f -Tsvg -O${f%.dot}.svg
   fdp $f -Tsvg -O${f%.dot}.svg
done

