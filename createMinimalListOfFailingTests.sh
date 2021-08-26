#!/bin/bash
rm -rf tmp
mkdir tmp
function run_first(){
./gradlew assemble
./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi
./launcher.main.kts --setup
./gradlew build > x
grep FAILED x -A1 > tmp/x-minified
reset_too_slow
reset_not_implemented
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
function remove_findings(){
cat tmp/d | sed "s/\[.*//g" | sort | uniq > tmp/e
grep -F -v -f tmp/e tmp/x-minified > tmp/f
mv tmp/f tmp/x-minified
for f in $(cat tmp/e | sed "s/.*\.//g" | sed "s/$/.kt/g")
do
find -name $f -delete
done
}




#run_first
run_later

add_too_slow
remove_findings
add_not_implemented
remove_findings

