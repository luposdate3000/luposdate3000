#!/bin/bash
./tool-get-called-functions.sh
./tool-get-provided-functions.sh
diff log/called-functions log/provided-functions | grep "^>" | grep -vw "s02buildSyntaxTree" | grep -vw "s01io.buffer" | grep -vw "datastructures"
