gradle --build-file="build.gradle.jvm.test" --project-cache-dir="gradleJvmTestGenerated" build -x test
(
        cd buildJvmTest/distributions
        tar -xf luposdate3000.tar
)
function execJvm
{
	./buildJvmTest/distributions/luposdate3000/bin/luposdate3000 > log/x 2>&1
}

{ { time execJvm ; } > log/c 2>&1 ;} &
wait
