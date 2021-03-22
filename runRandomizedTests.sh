#!/bin/bash
./launcher.main.kts --run --mainClass=Test_Buffermanager --memoryMode=persistent --runArgument_Luposdate3000_Launch_Test_Buffermanager:arg=0 > afl.buffermanager.persistent.log 2>&1 &
./launcher.main.kts --run --mainClass=Test_Buffermanager --memoryMode=inmemory   --runArgument_Luposdate3000_Launch_Test_Buffermanager:arg=0 > afl.buffermanager.inmemory.log 2>&1 &
./launcher.main.kts --run --mainClass=Test_Int_Array --memoryMode=inmemory --runArgument_Luposdate3000_Launch_Test_Int_Array:arg=0 > afl.intarray.log 2>&1 &
./launcher.main.kts --run --mainClass=Test_KV --memoryMode=inmemory --runArgument_Luposdate3000_Launch_Test_KV:arg=0 > afl.kv.log 2>&1 &
./launcher.main.kts --run --mainClass=Test_Triple_Index --memoryMode=inmemory --runArgument_Luposdate3000_Launch_Test_Triple_Index:arg=0 > afl.tripleindex.log 2>&1 &
wait
