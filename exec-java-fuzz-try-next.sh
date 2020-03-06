#!/bin/bash
./tool-gradle-build.sh
for f in crash-*; do echo $f;cat $f | ./build/executable || break;mv $f done/ ;done > x 2>&1;cat x;head x -n1
