#!/bin/bash
for testcase in Test_KV Test_Triple_Index Test_Triple_Index_Insert_Sequentiel Test_Dictionary Test_Dictionary_Encoding Test_VK
do
	for memorymode in Inmemory Persistent
	do
		for i in 0
		do
			(
				export LUPOS_HOME=/tmp/luposdate3000.$testcase.$memorymode.$i/
				j=$RANDOM
				echo "./launcher.main.kts --run --mainClass=$testcase --memoryMode=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$j"
				./launcher.main.kts --run --mainClass=$testcase --Buffer_Manager=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$j > afl.$testcase.$memorymode.$i.log 2>&1
			)&
		done
	done
done
for testcase in Test_Buffermanager Test_Int_Array
do
	for memorymode in Inmemory Persistent Persistent_Cached
	do
		for i in 0
		do
			(
				export LUPOS_HOME=/tmp/luposdate3000.$testcase.$memorymode.$i/
				j=$RANDOM
				echo "./launcher.main.kts --run --mainClass=$testcase --memoryMode=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$j"
				./launcher.main.kts --run --mainClass=$testcase --Buffer_Manager=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$j > afl.$testcase.$memorymode.$i.log 2>&1
			)&
		done
	done
done

wait
