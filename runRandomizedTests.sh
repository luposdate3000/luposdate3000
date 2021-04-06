#!/bin/bash
for memorymode in inmemory persistent
do
	for testcase in Test_Buffermanager Test_Int_Array Test_KV Test_Triple_Index Test_Triple_Index_Insert_Sequentiel Test_Dictionary Test_Dictionary_Encoding Test_VK
	do
		for i in 0
		do
			(
				export LUPOS_HOME=/tmp/luposdate3000.$testcase.$memorymode.$i/
				echo "./launcher.main.kts --run --mainClass=$testcase --memoryMode=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$i"
				./launcher.main.kts --run --mainClass=$testcase --memoryMode=$memorymode --runArgument_Luposdate3000_Launch_$testcase:arg=$i > afl.$testcase.$memorymode.$i.log 2>&1
			)&
		done
	done
done
wait
