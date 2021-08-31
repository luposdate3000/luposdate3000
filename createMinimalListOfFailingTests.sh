#!/bin/bash

rm -rf tmp
mkdir tmp
./gradlew assemble

while true
do

pkill java -9
./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi
./launcher.main.kts --setup
timeout --signal=9 60 ./gradlew build > x
pkill java -9

grep lupos.*PASSED x | tr -cd '\11\12\15\40-\176' | sed "s/\[jvm\] PASSED//g" | sed "s/.*\[jvm\] > //g" >> resources/tests/passed
grep -F resources/tests/passed x > tmp/x ; mv tmp/x x
grep org.junit.runners.model.TestTimedOutException x -B1 | tr -cd '\11\12\15\40-\176' | grep lupos.*FAILED | sed "s/\[jvm\] FAILED//g" | sed "s/.*\[jvm\] > //g" >> resources/tests/timeout
grep -F resources/tests/timeout x > tmp/x ; mv tmp/x x
grep lupos.*FAILED x | tr -cd '\11\12\15\40-\176' | sed "s/\[jvm\] FAILED//g" | sed "s/.*\[jvm\] > //g" >> resources/tests/failed
grep -F resources/tests/failed x > tmp/x ; mv tmp/x x
grep lupos.*STARTED x | tr -cd '\11\12\15\40-\176' | sed "s/\[jvm\] STARTED//g" | sed "s/.*\[jvm\] > //g" >> resources/tests/failed
sort resources/tests/passed | uniq > tmp/x ; mv tmp/x resources/tests/passed
sort resources/tests/timeout | uniq > tmp/x ; mv tmp/x resources/tests/timeout
sort resources/tests/failed | uniq > tmp/x ; mv tmp/x resources/tests/failed
exit
done
