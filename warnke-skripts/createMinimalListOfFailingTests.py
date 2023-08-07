#!/usr/bin/env -S python3 -OO -u
import os

os.makedirs("resources/tests")
srcCode=[]
with open ("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt") as f:
 for l in f:
  idx=l.index("minifyMode")
   if idx<0:
    srcCode.append(l.replace("\n",""))
   else:
    srcCode.append(l[0:idx]+": Boolean = true")
with open ("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt", "w") as f:
 for l in srcCode:
  f.write(l+"\n")
