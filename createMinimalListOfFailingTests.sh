#!/bin/bash

rm -rf tmp
mkdir tmp

while true
do

pkill java -9
./gradlew assemble
./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi
./launcher.main.kts --setup
timeout -9 -k 600 ./gradlew build > x
pkill java -9

grep lupos.*PASSED x | sed 's/\x1b\[[0-9;]*m//g' | sed "s/\[jvm\] PASSED//g" | sed "s/.*\[jvm\] > //g" >> resources/tests/passed
grep -F resources/tests/passed x > y ; mv y x
grep org.junit.runners.model.TestTimedOutException x -B1 | sed 's/\x1b\[[0-9;]*m//g' | grep lupos.*FAILED | sed "s/\[jvm\] FAILED//g" | sed "s/.*\[jvm\] > //g" >> resources/tests/timeout
grep -F resources/tests/timeout x > y ; mv y x
grep lupos.*FAILED x | sed 's/\x1b\[[0-9;]*m//g' | sed "s/\[jvm\] FAILED//g" | sed "s/.*\[jvm\] > //g" >> resources/tests/failed
grep -F resources/tests/failed x > y ; mv y x
grep lupos.*STARTED x | sed 's/\x1b\[[0-9;]*m//g' | sed "s/\[jvm\] STARTED//g" | sed "s/.*\[jvm\] > //g" >> resources/tests/failed
sort resources/tests/passed | uniq > tmp/x ; mv tmp/x resources/tests/passed
sort resources/tests/timeout | uniq > tmp/x ; mv tmp/x resources/tests/timeout
sort resources/tests/failed | uniq > tmp/x ; mv tmp/x resources/tests/failed

done
