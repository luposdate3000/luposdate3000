#!/bin/bash
export JAVA_OPTS="-Xmx30g"

#clean up directory
rm -rf log/alltest build/compile*
mkdir -p log/alltest
./tool-coverage-enable.sh

#generate and execute compile scripts
echo "i=0" > exec-sparql-test-suite-jvm-all-tmp.sh
./generate-buildfile.kts listAll "KotlinVersion->1.4.255-SNAPSHOT" "Platform->jvm" "Launch->SparqlTestSuite" "BufferManager->Heap" "Coverage->ECoverage.Count" "CoverageGenerate->DontChange" "Jena->On" "Sanity->On" "IteratorVerbose->None" "Execution->Sequential" \
> tmpfile.txt
cat exec-sparql-test-suite-jvm-all.template \
>> tmpfile.txt
cat tmpfile.txt \
|  sed "s/generate-buildfile.kts/generate-buildfile.kts\n.\/tool-gradle-build.sh\nret=\$?\nif [ \$ret -ne 0 ]; then exit \$ret; fi\nln -s \$(readlink -f build\/executable ) log\/alltest\/\$i.exec\ni=\$((\$i + 1))/g" \
>> exec-sparql-test-suite-jvm-all-tmp.sh
rm tmpfile.txt
if [ $? -ne 0 ]
then
	cat build/compile* \
	| sort \
	| uniq \
	| grep "e: " \
	| grep -v "Expected performance impact from inlining is insignificant" \
	| grep -v "kotlin/lupos/datastructures" \
	| grep -v "kotlin/lupos/s02buildSyntaxTree" \
	| grep -v "This class can only be used with the compiler argument" \
	| grep -v "'UseExperimental' is deprecated. Please use OptI" \
	| grep -v "Parameter.*is never used" \
	| grep -v "commonConfig.*No cast needed" \
	| grep -v "commonConfig.*Unchecked cast: Any? to" \
	| grep -v "commonConfig.*Unnecessary non-null assertion" \
	| grep -v "kotlin/lupos/s01io" \
	| grep -v "This API is experimental. It could be removed or changed in future"
	exit 1
fi
chmod +x exec-sparql-test-suite-jvm-all-tmp.sh
./exec-sparql-test-suite-jvm-all-tmp.sh
rm exec-sparql-test-suite-jvm-all-tmp.sh

#execute the tests in parallel
(
	for f in $(find log/alltest -name "*.exec")
	do
		(
			export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64
			./$f > $f.x 2>&1
			cat $f.x \
			| grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line" \
			| grep -v "<h1>Success</h1>" \
			| sort \
			| uniq -c \
			| sed "s/kotlin.//g" \
			| sed "s/java.lang.//g" \
			>> $f.c
			grep -e "Test: " -e Failed -e Success $f.x \
			| grep -v "Failed requirement" \
			| grep -v "<h1>Success</h1>" \
			| grep -B1 -e Failed -e Success \
			>> $f.c
		)&
	done
	wait
)
for f in $(find log/alltest -name "*.c")
do
	diff log/a $f -y \
	| grep -v "NotImplemented" \
	| grep -v "Syntax" \
	| grep "|" -B1 \
	| grep "Success.*|.*Failed" -B1
done
