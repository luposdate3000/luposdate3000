#!/usr/bin/env -S python3 -OO -u
import os
import random
import sys
from subprocess import STDOUT, check_output, PIPE

testCount = 10000
try:
    os.makedirs("resources/tests")
except:
    pass


def setup(minify):
    srcCode = []
    with open("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt") as f:
        for l in f:
            if "minifyMode: Boolean" in l:
                if minify:
                    srcCode.append(l[0:l.index("minifyMode")] + "minifyMode: Boolean = true")
                else:
                    srcCode.append(l[0:l.index("minifyMode")] + "minifyMode: Boolean = false")
            else:
                srcCode.append(l.replace("\n", ""))
    with open("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt", "w") as f:
        for l in srcCode:
            f.write(l + "\n")
    srcCode = []
    with open("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToKotlinProgram.kt") as f:
        for l in f:
            if "minifyMode: Boolean" in l:
                if minify:
                    srcCode.append(l[0:l.index("minifyMode")] + "minifyMode: Boolean = true")
                else:
                    srcCode.append(l[0:l.index("minifyMode")] + "minifyMode: Boolean = false")
            else:
                srcCode.append(l.replace("\n", ""))
    with open("./src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToKotlinProgram.kt", "w") as f:
        for l in srcCode:
            f.write(l + "\n")
    os.system("rm -rf src/luposdate3000_launch_code_gen_test_00 build.config")
    if os.system("./launcher.main.kts --setup --target=JVM --releaseMode=Disable") != 0:
        print("exit")
        sys.exit(-1)
    if os.system("./gradlew --offline assemble") != 0:
        print("exit")
        sys.exit(-1)
    if os.system("./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi") != 0:
        print("exit")
        sys.exit(-1)


def loop(filter):
    files = [filename for filename in os.listdir(".") if filename.startswith('lupos.launch_code_gen_test_00') and filename.endswith('stat')]
    with open("resources/tests/passed", "a") as fp:
        with open("resources/tests/failed", "a") as ff:
            with open("resources/tests/timeout", "a") as ft:
                for f in files:
                    with open(f) as file:
                        s = file.read()
                    if "passed" in s:
                        fp.write(f.replace("lupos.launch_code_gen_test_00.", "").replace(".stat", "") + "\n")
                    elif "failed" in s:
                        ff.write(f.replace("lupos.launch_code_gen_test_00.", "").replace(".stat", "") + "\n")
                    elif "started0" in s:  # otherwise the timeout would be "unfair"
                        ft.write(f.replace("lupos.launch_code_gen_test_00.", "").replace(".stat", "") + "\n")
    blacklist=[]
    allTests = []
    with open("resources/tests/all") as f:
        for l in f:
            allTests.append(l.strip())
    if os.path.exists("resources/tests/passed"):
        with open("resources/tests/passed") as f:
            for l in f:
                ll = l.strip()
                if ll in allTests:
                    allTests.remove(ll)
                blacklist.append(ll)
    if os.path.exists("resources/tests/failed"):
        with open("resources/tests/failed") as f:
            for l in f:
                ll = l.strip()
                if ll in allTests:
                    allTests.remove(ll)
                blacklist.append(ll)
    if os.path.exists("resources/tests/timeout"):
        with open("resources/tests/timeout") as f:
            for l in f:
                ll = l.strip()
                if ll in allTests:
                    allTests.remove(ll)
                blacklist.append(ll)
    random.shuffle(allTests)
    if len(filter) > 0:
        with open("resources/tests/blacklist", "w") as f:
            for x in allTests:
                if not (x in filter):
                    f.write(x + "\n")
            for x in blacklist:
             f.write(x + "\n")
    else:
        with open("resources/tests/blacklist", "w") as f:
            if len(allTests) == 0:
                sys.exit(0)
            start = max(min(testCount, len(allTests) - testCount), 0)
            for i in range(start, len(allTests)):
                f.write(allTests[i] + "\n")
            for x in blacklist:
             f.write(x + "\n")
    if os.system("./launcher.main.kts --run --mainClass=Launch_Generate_Unit_Test_Suite_Multi") != 0:
        print("exit")
        sys.exit(-1)
    if os.system("./launcher.main.kts --setup --target=JVM --releaseMode=Disable") != 0:
        print("exit")
        sys.exit(-1)
    if os.system("./gradlew --offline assemble") != 0:
        print("exit")
        sys.exit(-1)
    os.system("rm build.config lupos.launch_code_gen_test_00.*stat")
    os.system("./launcher.main.kts --run --mainClass=Launch_Code_Gen_Test_00 --processCount=1 --threadCount=1")


if len(sys.argv) > 1:
    if sys.argv[1] == "production":
        setup(False)
    elif sys.argv[1] == "minify":
        setup(True)
        while True:
            loop()
    else:
        setup(True)
        loop(sys.argv[1:])
else:
    setup(True)
    while True:
        loop([])
