#!/bin/bash

afl-cmin -m 2000000 -i resources/afl -o afl-cmin-out -Q ./buildNative/bin/linuxX64/debugExecutable/luposdate3000.kexe

exit
rm -rf afltmpin/Generated* afl-cmin-*
for i in $(seq 0 11)
do
	mkdir afl-cmin-input-$i
done
i=0
for f in $(find /opt/tmpfs | shuf)
do
	g=afl-cmin-input-$i/$(echo $f | sed "s-.*/--g")
	cp $f $g
	i=$(( (i + 1) % 12 ))
done
for i in $(seq 0 11)
do
	afl-cmin -m 2000000 -i afl-cmin-input-$i -o afl-cmin-out-$i -Q ./buildNative/bin/linuxX64/debugExecutable/luposdate3000.kexe &
done

wait
exit


./tool-gradle-build-without-tests-native.sh
echo core >/proc/sys/kernel/core_pattern
(
	cd /sys/devices/system/cpu
	echo performance | tee cpu*/cpufreq/scaling_governor
)
afl-cmin -m 2000000 -i afl-in-cmin -o afl-out-cmin -Q ./buildNative/bin/linuxX64/debugExecutable/luposdate3000.kexe
afl-fuzz -i afl-out-cmin -o afl-out -Q ./buildNative/bin/linuxX64/debugExecutable/luposdate3000.kexe
