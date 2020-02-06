./tool-gradle-build-without-tests-jvm-all.sh

for chooseS00Trace in "commonS00TraceOn" "commonS00TraceOff"
do
for chooseS03 in "commonS03DictionaryNoneMain" "commonS03DictionaryQueryLocalLongMain"
do
for chooseS05 in "commonS05HashMapMain"
do
for chooseS12 in "commonS12LocalDummyMain"
do
for chooseS14 in "jvmS14KorioMain"
do

buildName="${chooseS00Trace}-${chooseS03}-${chooseS05}-${chooseS12}-${chooseS14}"
buildFile="build.gradle-${buildName}.jvm.generated"
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
grep -e "Test: " -e Failed -e Success x-$buildName | grep -B1 -e Failed -e Success >> c-$buildName
echo "diff a c-$buildName"
diff a c-$buildName
)

done
done
done
done
done
