#!/bin/bash

sed "s/minifyMode: Boolean = .*/minifyMode: Boolean = true/g" -i ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt


rm -rf tmp
mkdir tmp
sleep 1
./launcher.main.kts --setup
./gradlew --offline assemble
sleep 1
i=0
while true
do
i=$((i+1))

#blacklisting some random tests -->>
cat resources/tests/all \
 | grep -w -v -F -f resources/tests/timeout \
 | grep -w -v -F -f resources/tests/passed \
 | grep -w -v -F -f resources/tests/failed  \
 > tmp/blacklist1
truncate -s0 resources/tests/blacklist
grep -w -v -F -f tmp/blacklist1 resources/tests/all > resources/tests/blacklist
grep -v " - in simulator - " tmp/blacklist1 > tmp/blacklist2
if [[ $(wc -l < tmp/blacklist2) -ge 500 ]]
then
 grep " - in simulator - " tmp/blacklist1 >> resources/tests/blacklist
 mv tmp/blacklist2 tmp/blacklist1
fi
grep -v " - Thread - " tmp/blacklist1 > tmp/blacklist2
if [[ $(wc -l < tmp/blacklist2) -ge 500 ]]
then
 grep " - Thread - " tmp/blacklist1 >> resources/tests/blacklist
 mv tmp/blacklist2 tmp/blacklist1
fi
cat tmp/blacklist1 \
 | shuf \
 | head -n -5000  >> resources/tests/blacklist

#blacklisting some random tests <<--

./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi
./launcher.main.kts --setup
timeout 600s ./gradlew --offline build > x
cp x backupX$i
pkill java -9
sleep 5
find /tmp/ -mindepth 1 -delete

grep org.junit.runners.model.TestTimedOutException x -B1 \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | grep lupos.*FAILED \
 | sed "s/\[jvm\] FAILED.*//g" \
 | sed "s/.*\[jvm\] > //g" >> resources/tests/timeout
if [ -s "resources/tests/timeout" ]
then
grep -w -v -F -f resources/tests/timeout x > tmp/x ; mv tmp/x x
fi

grep lupos.*FAILED x \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | sed "s/\[jvm\] FAILED.*//g" \
 | sed "s/.*\[jvm\] > //g" >> resources/tests/failed
if [ -s "resources/tests/failed" ]
then
grep -w -v -F -f resources/tests/failed x > tmp/x ; mv tmp/x x
fi

grep lupos.*PASSED x \
 | tr -cd '\11\12\15\40-\176' | sed "s/\[[0-9]*m//g" | sed "s/\[0K//g" \
 | sed "s/\[jvm\] PASSED.*//g" \
 | sed "s/.*\[jvm\] > //g" >> resources/tests/passed
if [ -s "resources/tests/passed" ]
then
  grep -w -v -F -f resources/tests/passed x > tmp/x ; mv tmp/x x
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

grep -w -v -F -f resources/tests/failed resources/tests/passed > tmp/x
mv tmp/x resources/tests/passed
grep -w -v -F -f resources/tests/timeout resources/tests/passed > tmp/x
mv tmp/x resources/tests/passed

done
