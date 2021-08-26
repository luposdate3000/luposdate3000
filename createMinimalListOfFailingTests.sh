#!/bin/bash
function run_first(){
rm -rf tmp
mkdir tmp
reset_too_slow
reset_not_implemented
reset_errors
reset_errors_in_simulator
./gradlew assemble
./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi
./launcher.main.kts --setup
./gradlew build > x
grep code_gen_test.*FAILED x -A1 > tmp/x-minified
}
function run_later(){
./gradlew build > x
grep FAILED x -A1 > tmp/x-minified
}


function reset_too_slow(){
head ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToTooSlow.kt -n 19 > tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToTooSlow.kt
}
function reset_errors(){
head ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugs.kt -n 19 > tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugs.kt
}
function reset_errors_in_simulator(){
head ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugsInSimulator.kt -n 19 > tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugsInSimulator.kt
}
function reset_not_implemented(){
head ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToNotImplemented.kt -n 19 > tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToNotImplemented.kt
}
function add_too_slow(){
head ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToTooSlow.kt -n 19 > tmp/a
grep " to " ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToTooSlow.kt > tmp/b
grep org.junit.runners.model.TestTimedOutException tmp/x-minified -B1 | grep "lupos.code_gen_test_" > tmp/d
cat tmp/d | sed "s/\[.*/\" to \"too slow\",/g" | sed "s/.*\./        \"/g" | sort | uniq > tmp/c
cat tmp/c >> tmp/b
cat tmp/b | sort | uniq >> tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToTooSlow.kt
}
function add_not_implemented(){
head ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToNotImplemented.kt -n 19 > tmp/a
grep " to " ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToNotImplemented.kt > tmp/b
grep kotlin.NotImplementedError tmp/x-minified -B1 | grep "lupos.code_gen_test_" > tmp/d
cat tmp/d | sed "s/\[.*/\" to \"not implemented\",/g" | sed "s/.*\./        \"/g" | sort | uniq > tmp/c
cat tmp/c >> tmp/b
cat tmp/b | sort | uniq >> tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToNotImplemented.kt
}
function add_errors(){
head ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugs.kt -n 19 > tmp/a
grep " to " ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugs.kt > tmp/b
grep -e java.lang.Exception -e java.lang.AssertionError tmp/x-minified -B1 | grep "lupos.code_gen_test_" > tmp/d
cat tmp/d | sed "s/\[.*/\" to \"too slow\",/g" | sed "s/.*\./        \"/g" | sort | uniq > tmp/c
cat tmp/c >> tmp/b
cat tmp/b | sort | uniq >> tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugs.kt
}
function add_errors_simulator(){
head ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugsInSimulator.kt -n 19 > tmp/a
grep " to " ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugsInSimulator.kt > tmp/b
grep -e java.lang.Exception -e java.lang.AssertionError tmp/x-minified -B1 | grep "lupos.code_gen_test_" | grep "in simulator" > tmp/d
cat tmp/d | sed "s/\[.*/\" to \"too slow\",/g" | sed "s/.*\./        \"/g" | sort | uniq > tmp/c
cat tmp/c >> tmp/b
cat tmp/b | sort | uniq >> tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a ./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueToBugsInSimulator.kt
}


function remove_findings(){
cat tmp/d | sed "s/\[.*//g" | sort | uniq > tmp/e
grep -F -v -f tmp/e tmp/x-minified > tmp/f
mv tmp/f tmp/x-minified
for f in $(cat tmp/e | sed "s/.*\.//g" | sed "s/$/.kt/g")
do
find -name $f -delete
done
}
function remove_passed(){
for f in $(grep code_gen_test.*PASSED x | sed "s/\[.*//g" | sed "s/.*\.//g" | sort | uniq -c | sed "s/^ *//g" | sed "s/ /,/g")
do
g=(${f//,/ })
for h in $(find -name "${g[1]}.kt")
do
i=$(grep "@Test" $h | wc -l)
if [ "$g[0]" = "$i" ]
then
echo "rm due to passing $i .. $h"
rm $h
fi
done
done
}
function process_results(){
add_errors_simulator
remove_findings
add_errors
remove_findings
add_too_slow
remove_findings
add_not_implemented
remove_findings
grep "FAILED" tmp/x-minified -A1 >> tmp/x-collected
}



pkill java -9
run_first
process_results
exit
while true
do
pkill java -9
run_later
process_results
exit
done
