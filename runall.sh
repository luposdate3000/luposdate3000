#!/bin/bash

./run.sh > x 2>&1
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" | sort | uniq -c
grep -e "Test: " -e Failed x | grep -B1 -e Failed

