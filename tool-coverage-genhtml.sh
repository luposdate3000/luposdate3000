#!/bin/bash
./tool-coverage-merge.kts coverage*.cov > coverage.info
genhtml coverage.info --output-directory out --prefix "/src/luposdate3000/src"
