./tool-gradle-build-without-tests-jvm.sh

for chooseS03 in "commonS03DictionaryNoneMain" "commonS03DictionaryQueryLocalLongMain"
do
for chooseS05 in "commonS05HashMapMain"
do
for chooseS12 in "commonS12LocalDummyMain"
do
for chooseS14 in "jvmS14KorioMain"
do

buildName=$chooseS03-$chooseS05-$chooseS12-$chooseS14
buildFile=build.gradle-$buildname.jvm.generated
buildDir=buildJvm$buildname

function execJvm
{
	./$buildDir/distributions/luposdate3000/bin/luposdate3000 > x-$buildName 2>&1
}

{ { time execJvm ; } > c-$buildName 2>&1 ;} &
wait
cat x-$buildName | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line" | sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >>c-$buildName
grep -e "Test: " -e Failed -e Success x-$buildName | grep -B1 -e Failed -e Success >> c-$buildName
echo "diff a c-$buildName"
diff a c-$buildName

done
