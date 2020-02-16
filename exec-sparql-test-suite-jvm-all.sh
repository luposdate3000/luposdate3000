./tool-gradle-build-without-tests-jvm-all.sh

for chooseS00Trace in "commonS00TraceOn" "commonS00TraceOff"
do
for chooseS03 in "commonS03DictionaryNoneMain" "commonS03DictionaryIntArrayMain"
do
for chooseS05 in "commonS05HashMapMain"
do
for chooseS12 in "jvmS12DummyMain"
do
for chooseS14 in "jvmS14KorioMain"
do
for chooseS15 in "commonS15LocalMain" "commonS15DistributedMain"
do
if [ "$chooseS03" == "commonS03DictionaryNoneMain" ] && [ "$chooseS12" == "jvmS12DummyMain" ]
then
	continue
fi

buildName="${chooseS00Trace}-${chooseS03}-${chooseS05}-${chooseS12}-${chooseS14}-${chooseS15}.generated"
buildFile="build.gradle-${buildName}.jvm.generated"
buildDir="buildJvm${buildName}.generated"

function execJvm
{
	./$buildDir/distributions/luposdate3000/bin/luposdate3000 > log/x-$buildName 2>&1
}

{ { time execJvm ; } > log/c-$buildName 2>&1 ;} &
wait
(
cd log
cat x-$buildName | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line" | sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >>c-$buildName
grep -e "Test: " -e Failed -e Success x-$buildName | grep -v "Failed requirement" | grep -B1 -e Failed -e Success >> c-$buildName
echo "diff a c-$buildName"
diff a c-$buildName
)

done
done
done
done
done
done
