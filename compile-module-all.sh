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
./generate-buildfile-module.kts "luposdate3000_Triple_Store_All" "src/luposdate3000_triple_store_all" "linuxX64" --inline --nosuspend --debug
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
