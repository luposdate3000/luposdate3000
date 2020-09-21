#!/bin/bash
export JAVA_OPTS="-Xmx60g"
port="3030"
rm -rf build src.generated
./generate-buildfile.kts --file=src-generate-buildfile/template-exec-sparql-test-suite-jvm
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]
then
	exit $ret
fi
IFS=$'\n'
function execJvm
{
	export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64
	export LUPOS_HOME=/tmp/luposdate3000-test/
	rm -rf $LUPOS_HOME
	./build/executable "$@" > log/x 2>&1
}

{ { time execJvm "$@"; } > log/c 2>&1 ;}
cd log
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line"|grep -v "<h1>Success</h1>"| sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >>c
grep -e "Test: " -e Failed -e Success x | grep -v "Failed requirement" |grep -v "<h1>Success</h1>"| grep -B1 -e Failed -e Success >> c
if [ ! -f a ]; then
  cp c a
fi
echo "diff c c2"
diff c c2
echo "diff a c"
diff a c
diff a c -y |grep "|" -B1|grep "Failed.*|.*Success" -B1
diff a c -y |grep "|" -B1|grep "Success.*|.*Failed" -B1
cd ..
