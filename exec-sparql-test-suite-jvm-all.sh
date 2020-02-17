./tool-gradle-build-without-tests-jvm-all.sh

for chooseS00Execution in "commonS00ExecutionSequentialMain" "commonS00ExecutionParallelMain"
do
for chooseS00Trace in "commonS00TraceOnMain" "commonS00TraceOffMain"
do
for chooseS03 in "commonS03DictionaryNoneMain" "commonS03DictionaryIntArrayMain"
do
for chooseS05 in "commonS05HashMapMain"
do
for chooseS12 in "jvmS12DummyMain" "commonS12LocalMain"
do
for chooseS14 in "jvmS14KorioMain" "commonS14NoneMain"
do
for chooseS15 in "commonS15LocalMain" "commonS15DistributedMain"
do
if [ "$chooseS00Execution" == "commonS00ExecutionParallelMain" ] && [ "$chooseS00Trace" == "commonS00TraceOnMain" ]
then
        continue
fi
if [ "$chooseS12" == "commonS12LocalMain" ]
then
if [ "$chooseS15" == "commonS15DistributedMain" ]
then
# if there is just one node, there is no distribution
	continue
fi
else
if [ "$chooseS03" == "commonS03DictionaryNoneMain" ]
then
# if multiple nodes exist, dictionaries are required
	continue
fi
fi

buildName="${chooseS00Execution}-${chooseS00Trace}-${chooseS03}-${chooseS05}-${chooseS12}-${chooseS14}-${chooseS15}.generated"
buildFile="build.gradle-${buildName}"
buildDir="buildJvm${buildName}"

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
