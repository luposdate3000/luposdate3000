#!/bin/bash

./generate-buildfile-module.kts "Luposdate3000_Shared" "src/luposdate3000_shared" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Jena_Wrapper_On" "src/luposdate3000_jena_wrapper_on" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Jena_Wrapper_Off" "src/luposdate3000_jena_wrapper_off" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Parser" "src/luposdate3000_parser" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Dictionary_Inmemory" "src/luposdate3000_dictionary_inmemory" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Operators" "src/luposdate3000_operators" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Result_Format" "src/luposdate3000_result_format" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Buffer_Manager_Inmemory" "src/luposdate3000_buffer_manager_inmemory" "linuxX64" --inline --nosuspend --debug --BUFFER_MANAGER_PAGE_SIZE_IN_BYTES=8192 --BUFFER_MANAGER_USE_FREE_LIST=true
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Triple_Store_Id_Triple" "src/luposdate3000_triple_store_id_triple" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Triple_Store_All" "src/luposdate3000_triple_store_all" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Optimizer" "src/luposdate3000_optimizer" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Endpoint" "src/luposdate3000_endpoint" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Test" "src/luposdate3000_test" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Endpoint_None" "src/luposdate3000_endpoint_none" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Endpoint_Java_Sockets" "src/luposdate3000_endpoint_java_sockets" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Launch_Benchmark_Jena" "src/luposdate3000_launch_benchmark_jena" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Launch_Benchmark" "src/luposdate3000_launch_benchmark" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Launch_Binary_Test_Suite" "src/luposdate3000_launch_binary_test_suite" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Launch_Endpoint" "src/luposdate3000_launch_endpoint" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Launch_Import" "src/luposdate3000_launch_import" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./generate-buildfile-module.kts "Luposdate3000_Launch_Sparql_Test_Suite" "src/luposdate3000_launch_sparql_test_suite" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
