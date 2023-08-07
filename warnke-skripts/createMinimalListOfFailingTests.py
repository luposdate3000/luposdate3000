#!/usr/bin/env -S python3 -OO -u
import os
try:
 os.makedirs("resources/tests")
except:
 pass
srcCode=[]
with open ("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt") as f:
 for l in f:
   if "minifyMode: Boolean" in l:
    srcCode.append(l[0:l.index("minifyMode")]+"minifyMode: Boolean = true")
   else:
    srcCode.append(l.replace("\n",""))
with open ("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt", "w") as f:
 for l in srcCode:
  f.write(l+"\n")
