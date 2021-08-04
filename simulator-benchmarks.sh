#!/bin/bash

#git clean -xdf
./launcher.main.kts --setup --intellijMode=Disable --releaseMode=Enable
./gradlew assemble

rm -rf visual*svg "benchmark-results"
mkdir -p "benchmark-results"

for distribution in "PartitionByIDTwiceAllCollations" "PartitionByKeyAllCollations"
do

####
./launcher.main.kts --run --mainClass=Launch_Simulator --runArgument_Luposdate3000_Launch_Simulator:evaluate="evalQueryProcessingDistributedCase" --runArgument_Luposdate3000_Launch_Simulator:distribution="$distribution"
mv src/luposdate3000_simulator_iot/simulator_output/campusDistributedCase "benchmark-results/distributed-$distribution"
mv visual*svg "benchmark-results/distributed-$distribution/"
exit
####
./launcher.main.kts --run --mainClass=Launch_Simulator --runArgument_Luposdate3000_Launch_Simulator:evaluate="evalQueryProcessingCentralizedCase" --runArgument_Luposdate3000_Launch_Simulator:distribution="$distribution"
mv src/luposdate3000_simulator_iot/simulator_output/campusCentralCase "benchmark-results/centralized-$distribution"
mv visual*svg "benchmark-results/distributed-$distribution/"

done
