#!/bin/bash
for i in 0
do
./launcher.main.kts --run --mainClass=Test_Buffermanager --memoryMode=persistent --runArgument_Luposdate3000_Launch_Test_Buffermanager:arg=$i > afl.buffermanager.persistent.$i.log 2>&1 &
done
for i in 0
do
./launcher.main.kts --run --mainClass=Test_Buffermanager --memoryMode=inmemory   --runArgument_Luposdate3000_Launch_Test_Buffermanager:arg=$i > afl.buffermanager.inmemory.$i.log 2>&1 &
done
for i in 0
do
./launcher.main.kts --run --mainClass=Test_Int_Array --memoryMode=inmemory --runArgument_Luposdate3000_Launch_Test_Int_Array:arg=$i > afl.intarray.$i.log 2>&1 &
done
for i in 0
do
./launcher.main.kts --run --mainClass=Test_KV --memoryMode=inmemory --runArgument_Luposdate3000_Launch_Test_KV:arg=$i > afl.kv.$i.log 2>&1 &
done
for i in $(seq 3)
do
./launcher.main.kts --run --mainClass=Test_Triple_Index --memoryMode=inmemory --runArgument_Luposdate3000_Launch_Test_Triple_Index:arg=$i > afl.tripleindex.$i.log 2>&1 &
done
for i in $(seq 3)
do
./launcher.main.kts --run --mainClass=Test_Triple_Index_Insert_Sequentiel --memoryMode=inmemory --runArgument_Luposdate3000_Launch_Test_Triple_Index_Insert_Sequentiel:arg=$i > afl.tripleindexinsertsequentiel.$i.log 2>&1 &
done
wait
