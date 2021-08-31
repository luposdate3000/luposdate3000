#!/bin/bash

rm -rf tmp
mkdir tmp

function run_first(){
	echo "run_first"
	pkill java -9
	./gradlew assemble
	./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi
	./launcher.main.kts --setup
	./gradlew build > x
	grep code_gen_test.*FAILED x -A1 > tmp/x-minified
}






run_first
