./tool-gradle-build-without-tests-jvm.sh
docker-compose down
docker-compose build --no-cache
docker-compose up --detach
sleep 2
function execJvm
{
	curl localhost:8000/peers/self_test > log/x 2>&1
	docker-compose logs | sed 's/\x1b\[[0-9;]*m//g' | sed "s/^[^|]*| //g" >> log/x 2>&1
}

{ { time execJvm ; } > log/c 2>&1 ;} &
wait
(
cd log
cat x | grep -e Exception -e Success -e Failed -e "Token unrecognized" -e "java.lang" -e "lupos.s1buildSyntaxTree.UnexpectedToken" -e "Error in the following line"| sort | uniq -c | sed "s/kotlin.//g" | sed "s/java.lang.//g" >>c
grep -e "Test: " -e Failed -e Success x | grep -v "Failed requirement" | grep -B1 -e Failed -e Success >> c
echo "diff a c"
diff a c
)
docker-compose down
