
head -n1 $(grep -rl "path not implemented" *log) makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/path,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "service not implemented" *log) makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/service,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "OperatorGraphVisitor not implemented" *log) makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/operator,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "Java heap space" *log) makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/memory,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "datasets not supported" *log) makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/dataset,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "describe query not implemented" *log) makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/describe,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "not implemented: ASTFunctionCall" *log) makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/functioncall,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "unknown filetype " *log) makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/filetype,/g" >> resources/tests/ignorelist
head -n1 $(grep -rl "expected syntax failure" *log) makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/syntax,/g" >> resources/tests/ignorelist
head -n1 lupos.launch_code_gen_test_00.Syn* makeIgnoreList.sh | grep -v "^$" | grep -v "==>" | sed "s/ - .*//g" | sed "s/^/syntax,/g" >> resources/tests/ignorelist

sort resources/tests/ignorelist |uniq> xxx
mv xxx resources/tests/ignorelist
rm resources/tests/timeout resources/tests/passed resources/tests/blacklist resources/tests/failed resources/tests/whitelist
