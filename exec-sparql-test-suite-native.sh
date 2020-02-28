./tool-gradle-build-without-tests-native.sh
function execNative
{
	./buildNative/bin/linuxX64/debugExecutable/luposdate3000.kexe > log/x 2>&1
}

{ { time execNative ; } > log/c 2>&1 ;} &
wait
cd log
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line"| sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >>c
grep -e "Test: " -e Failed -e Success x | grep -v "Failed requirement" | grep -B1 -e Failed -e Success >> c
echo "diff c c2"
diff c c2
echo "diff a c"
diff a c
cat x
