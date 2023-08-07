#!/usr/bin/env -S python3 -OO -u
import os
try:
    os.makedirs("resources/tests")
except:
    pass
srcCode = []
with open("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt") as f:
    for l in f:
        if "minifyMode: Boolean" in l:
            srcCode.append(l[0:l.index("minifyMode")] + "minifyMode: Boolean = true")
        else:
            srcCode.append(l.replace("\n", ""))
with open("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt", "w") as f:
    for l in srcCode:
        f.write(l + "\n")
if os.system("./launcher.main.kts --setup --target=JVM --releaseMode=Disable") != 0:
    sys.exit(-1)
if os.system("./gradlew --offline assemble") != 0:
    sys.exit(-1)
if os.system("./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi") != 0:
    sys.exit(-1)
allTests = []
with open("resources/tests/all") as f:
    for l in f:
        allTests.append(l.strip())

if os.exists("resources/tests/passed"):
    with open("resources/tests/passed") as f:
        for l in f:
            ll = l.strip()
            if ll in allTests:
                allTests.remove(ll)
if os.exists("resources/tests/failed"):
    with open("resources/tests/failed") as f:
        for l in f:
            ll = l.strip()
            if ll in allTests:
                allTests.remove(ll)
#if os.system("./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi")!=0:
# sys.exit(-1)
#if os.system("./gradlew build --offline")!=0:
# sys.exit(-1)
