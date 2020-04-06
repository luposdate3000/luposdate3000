#!/bin/bash

find src/commonMain/kotlin/lupos/s04arithmetikOperators/ -name "*.kt" | sort> tmpA
find src/commonTest/kotlin/lupos/s04arithmetikOperators/ -name "*.kt" | sort | sed "s/Test/Main/g" > tmpB
diff tmpA tmpB | grep "^< " | sed "s/< //g"
rm tmpA tmpB
