./tool-gradle-build-without-tests-jvm.sh
function execJvm
{
	./buildJvm/distributions/luposdate3000/bin/luposdate3000 > x 2>&1
}

{ { time execJvm ; } > c 2>&1 ;} &
wait
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line" | sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" > b
grep -e "Test: " -e Failed -e Success x | grep -B1 -e Failed -e Success >> c
cat b
echo "diff c c2"
diff c c2
echo "diff a c"
diff a c
