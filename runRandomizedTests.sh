#!/bin/bash
./launcher.main.kts --run --mainClass=Test_Buffermanager --memoryMode=persistent --runArgument_Luposdate3000_Launch_Test_Buffermanager:arg=0 > afl.buffermanager.persistent.log 2>&1 &
./launcher.main.kts --run --mainClass=Test_Buffermanager --memoryMode=inmemory   --runArgument_Luposdate3000_Launch_Test_Buffermanager:arg=0 > afl.buffermanager.inmemory.log 2>&1 &

tail -f afl*log
