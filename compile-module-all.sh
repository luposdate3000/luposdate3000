#!/bin/bash
./compile-module-shared.sh
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./compile-module-jena-on.sh
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./compile-module-jena-off.sh
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./compile-module-parser.sh
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./compile-module-dictionary-inmemory.sh
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./compile-module-operators.sh
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./compile-module-buffermanager-inmemory.sh
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
./compile-module-triple_store_id_triple.sh
ret=$? ; if [ $ret -ne 0 ] ; then exit $ret ; fi
