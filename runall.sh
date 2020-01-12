#!/bin/bash
./run.sh > x
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized"| sort | uniq -c
grep -e "Test: " -e Failed x | grep -B1 -e Failed

