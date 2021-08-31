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

grep lupos.*PASSED x \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | sed "s/\[jvm\] PASSED.*//g" \
 | sed "s/.*\[jvm\] > //g" >> resources/tests/passed
if [ -s "resources/tests/passed" ]
then
  grep -F -f resources/tests/passed x > tmp/x ; mv tmp/x x
fi
grep org.junit.runners.model.TestTimedOutException x -B1 \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | grep lupos.*FAILED \
 | sed "s/\[jvm\] FAILED.*//g" \
 | sed "s/.*\[jvm\] > //g" >> resources/tests/timeout
if [ -s "resources/tests/timeout" ]
then
grep -F -f resources/tests/timeout x > tmp/x ; mv tmp/x x
fi
grep lupos.*FAILED x \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | sed "s/\[jvm\] FAILED.*//g" \
 | sed "s/.*\[jvm\] > //g" >> resources/tests/failed
if [ -s "resources/tests/failed" ]
then
grep -F -f resources/tests/failed x > tmp/x ; mv tmp/x x
fi
grep lupos.*STARTED x \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | sed "s/\[jvm\] STARTED.*//g" \
 | sed "s/.*\[jvm\] > //g" >> resources/tests/failed
cat resources/tests/passed \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | sort | uniq > tmp/x ; mv tmp/x resources/tests/passed
cat resources/tests/timeout \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | sort | uniq > tmp/x ; mv tmp/x resources/tests/timeout
cat resources/tests/failed \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | sort| uniq > tmp/x ; mv tmp/x resources/tests/failed
exit
done
