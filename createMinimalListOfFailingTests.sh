#!/bin/bash

rm -rf tmp
mkdir tmp

withCodeGen="false"
withSimulator="false"
filePrefix="./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTestIgnoreListDueTo"
filePostfix=".kt"
function setInSimulator(){
echo "setInSimulator"
withSimulator="true"
filePostfix="InSimulator"
sed -i "s/private val withCodeGen.*/private val withCodeGen = $withCodeGen/g" /src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt
sed -i "s/private val withSimulator.*/private val withSimulator = $withSimulator/g" /src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt
}
function setNotInSimulator(){
echo "setInSimulator"
withSimulator="false"
filePostfix=""
sed -i "s/private val withCodeGen.*/private val withCodeGen = $withCodeGen/g" /src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt
sed -i "s/private val withSimulator.*/private val withSimulator = $withSimulator/g" /src/luposdate3000/src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt
}

function resetAll(){
reset_file "TooSlow"
reset_file "NotImplemented"
reset_file "Bugs"
}
function run_first(){
echo "run_first"
pkill java -9
./gradlew assemble
./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi
./launcher.main.kts --setup
./gradlew build > x
grep code_gen_test.*FAILED x -A1 > tmp/x-minified
}
function run_later(){
echo "run_later"
pkill java -9
./gradlew build > x
grep FAILED x -A1 > tmp/x-minified
}

function reset_file(){
cat > ${filePrefix}$1${filePostfix}.kt <<- EOF
/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.test
internal object SparqlTestSuiteConverterToUnitTestIgnoreListDueTo$1${filePostfix} {
    internal val ignoreList = mapOf<String, String>( //
EOF
head ${filePrefix}$1${filePostfix}.kt -n 19 > tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a ${filePrefix}$1${filePostfix}.kt
}

function add_filter_target_reason(){
filter=$1
target=$filePrefix$2${filePostfix}.kt
reason=$3
head $target -n 19 > tmp/a
grep " to " $target > tmp/b
grep $1 tmp/x-minified -B1 | grep "lupos.code_gen_test_" > tmp/d
cat tmp/d | sed "s/\[.*/\" to \"$reason\",/g" | sed "s/.*\./        \"/g" | sort | uniq > tmp/c
cat tmp/c >> tmp/b
cat tmp/b | sort | uniq >> tmp/a
echo "    )" >> tmp/a
echo "}" >> tmp/a
mv tmp/a $target
}

function remove_findings(){
cat tmp/d | sed "s/\[.*//g" | sort | uniq > tmp/e
grep -F -v -f tmp/e tmp/x-minified > tmp/f
mv tmp/f tmp/x-minified
for f in $(cat tmp/e | sed "s/.*\.//g" | sed "s/$/.kt/g")
do
find -name $f -delete
flag=true
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

function process(){
flag=false
add_filter_target_reason "org.junit.runners.model.TestTimedOutException" "TooSlow" "too slow"
remove_findings
add_filter_target_reason "kotlin.NotImplementedError" "NotImplemented" "not implemented"
remove_findings
add_filter_target_reason "FAILED" "Bugs" "bugs"
remove_findings
}


setInSimulator
resetAll
setNotInSimulator
resetAll

setNotInSimulator
resetAll
run_first
process
while $flag
do
run_later
process
done

setInSimulator
resetAll
run_first
process
while $flag
do
run_later
process
done
