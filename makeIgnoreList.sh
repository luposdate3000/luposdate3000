
head -n1 $(grep -rl "AOPBuildInCallExists" *log makeIgnoreList.sh) | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/exists,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "path not implemented" *log makeIgnoreList.sh) | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/path,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "service not implemented" *log makeIgnoreList.sh) | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/service,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "OperatorGraphVisitor not implemented" *log makeIgnoreList.sh) | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/operator,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "Java heap space" *log makeIgnoreList.sh) | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/memory,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "datasets not supported" *log makeIgnoreList.sh) | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/dataset,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "describe query not implemented" *log makeIgnoreList.sh) | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/describe,/g" >> resources/tests/ignorelist

sort resources/tests/ignorelist |uniq> xxx
mv xxx resources/tests/ignorelist
rm resources/tests/timeout resources/tests/passed resources/tests/blacklist resources/tests/failed
