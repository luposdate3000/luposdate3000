./tool-gradle-build-without-tests.sh
function execJvm
{
	./buildJvm/distributions/luposdate3000/bin/luposdate3000 > log/x 2>&1
}
function execNative
{
	./buildNative/bin/linuxX64/releaseExecutable/luposdate3000.kexe > log/x2 2>&1
}

{ { time execJvm ; } > log/c 2>&1 ;}
{ { time execNative ; } > log/c2 2>&1 ;}
cd log
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line" | sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >> c
grep -e "Test: " -e Failed -e Success x | grep -B1 -e Failed -e Success >> c
cat x2 | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line" | sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >> c2
grep -e "Test: " -e Failed -e Success x2 | grep -B1 -e Failed -e Success >> c2
echo "diff c c2"
diff c c2
echo "diff a c"
diff a c
