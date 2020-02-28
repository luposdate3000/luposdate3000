#!/bin/bash
gradle --build-file="build.gradle.jvm.test" --project-cache-dir="gradleJvmTestGenerated" build -x test
(
        cd buildJvmTest/distributions
        tar -xf luposdate3000.tar
)
cp /opt/java-afl/*.jar buildJvmTest/distributions/luposdate3000/lib/
echo core >/proc/sys/kernel/core_pattern
(
	cd /sys/devices/system/cpu
	echo performance | tee cpu*/cpufreq/scaling_governor
)
filename=$(tempfile)
/opt/java-afl/java-afl-fuzz -f $filename -i src/commonTest/kotlin/lupos/ -o afl-out -- java -classpath "buildJvmTest/distributions/luposdate3000/lib/*" javafl.run lupos.TestBinaryKt $filename
rm $filename


#https://www.floyd.ch/?p=1090
