#!/bin/bash
./tool-gradle-build-without-tests-native.sh
echo core >/proc/sys/kernel/core_pattern
(
	cd /sys/devices/system/cpu
	echo performance | tee cpu*/cpufreq/scaling_governor
)
afl-fuzz -i /opt/tmpfs -o afl-out -Q ./buildNative/bin/linuxX64/debugExecutable/luposdate3000.kexe
