#!/bin/bash
usedFolders=$(cat build/script* | grep "src/" | sort -u | sed "s-.*src/-src/-g" | sed 's/".*//g' | tr '\n' '|')
./tool-coverage-merge.main.kts $usedFolders coverage*.cov > coverage.info
genhtml coverage.info --output-directory out --prefix "/src/luposdate3000/strippedSourceCode/src"
rm -rf strippedSourceCode
