cd /src/luposdate3000
(
	cd heap_jvm
	gradle build -x test
)
java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/TestKt > x 2>&1
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line" | sort | uniq -c > b
grep -e "Test: " -e Failed x | grep -B1 -e Failed >> b
cat b
diff a b
