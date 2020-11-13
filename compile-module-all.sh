#!/bin/bash
export JAVA_OPTS="-Xmx60g"
./generate-buildfile-module.main.kts --module="Luposdate3000_Shared" --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Jena_Wrapper_On" --prefix="Luposdate3000_Jena_Wrapper" --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Jena_Wrapper_Off" --prefix="Luposdate3000_Jena_Wrapper" --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Parser" --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Dictionary_Inmemory" --prefix="Luposdate3000_Dictionary" --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Operators"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Result_Format" --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Buffer_Manager_Inmemory" --prefix="Luposdate3000_Buffer_Manager"  --inline --nosuspend --debug  --fast --idea --BUFFER_MANAGER_PAGE_SIZE_IN_BYTES=8192 --BUFFER_MANAGER_USE_FREE_LIST=true
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Triple_Store_Id_Triple"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Triple_Store_All" --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi

#this is the only exception, where there is no postfix for the module-variant. This should make it easier for the idea-integration, but it breaks the similarity to other exchangeable modules
./generate-buildfile-module.main.kts --module="Luposdate3000_Optimizer" --src="src/luposdate3000_optimizer" --prefix="Luposdate3000_Optimizer" --inline --nosuspend --debug  --fast --idea --USE_PARTITIONS=true
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Optimizer_NoPartitions" --src="src/luposdate3000_optimizer" --prefix="Luposdate3000_Optimizer" --inline --nosuspend --debug  --fast --idea --USE_PARTITIONS=false
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Endpoint" --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Test" --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Endpoint_None" --prefix="Luposdate3000_Endpoint_Launcher"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Endpoint_Java_Sockets" --prefix="Luposdate3000_Endpoint_Launcher"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Launch_Benchmark_Jena" --prefix="Luposdate3000_Main"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Launch_Benchmark" --prefix="Luposdate3000_Main"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Launch_Binary_Test_Suite" --prefix="Luposdate3000_Main"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Launch_Endpoint" --prefix="Luposdate3000_Main"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Launch_Import" --prefix="Luposdate3000_Main"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.main.kts --module="Luposdate3000_Launch_Sparql_Test_Suite" --prefix="Luposdate3000_Main"  --inline --nosuspend --debug  --fast --idea
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi

diff build-cache/bin/libLuposdate3000_Optimizer-linuxX64.h build-cache/bin/libLuposdate3000_Optimizer_NoPartitions-linuxX64.h
diff build-cache/bin/libLuposdate3000_Jena_Wrapper_On-linuxX64.h build-cache/bin/libLuposdate3000_Jena_Wrapper_Off-linuxX64.h
diff build-cache/bin/libLuposdate3000_Endpoint_Java_Sockets-linuxX64.h build-cache/bin/libLuposdate3000_Endpoint_None-linuxX64.h
