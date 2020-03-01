#!/bin/bash
./tool-gradle-build-without-tests-native.sh
echo core >/proc/sys/kernel/core_pattern
(
	cd /sys/devices/system/cpu
	echo performance | tee cpu*/cpufreq/scaling_governor
)
rm /opt/tmpfs/Generated*
afl-cmin -m 200 -i /opt/tmpfs -o afl-out-cmin -Q ./buildNative/bin/linuxX64/debugExecutable/luposdate3000.kexe
afl-fuzz -i afl-out-cmin -o afl-out -Q ./buildNative/bin/linuxX64/debugExecutable/luposdate3000.kexe
