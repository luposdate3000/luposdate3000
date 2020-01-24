cd /src/luposdate3000
(
	gradle build -x test
	cd build/distributions
	tar -xf luposdate3000.tar
)
./build/distributions/luposdate3000/bin/luposdate3000 > x 2>&1
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line" | sort | uniq -c > b
grep -e "Test: " -e Failed x | grep -B1 -e Failed >> b
cat b
diff a b
