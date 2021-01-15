## Tests

To run all tests use the following:

```bash
./launcher.main.kts --allTest
```

or if you are using intellij, launch the main-function in the File [src/luposdate3000_launch_binary_test_suite/src/jvmMain/kotlin/Main.kt](src/luposdate3000_launch_binary_test_suite/src/jvmMain/kotlin/Main.kt) .


These tests are executed repeatedly and therefore these should be compileable and executable.
Currently luposdate3000 is not finished - thats why there are lots of failing tests.
The test script writes a summary to the file resources/binary/config2.
The comparison to the file resources/binary/config should show if there are new tests working - or if there are some tests broken right now.
You can modify resources/binary/config to enable/disable specific tests.
The found errors are summarized in the file log/error.

The errors are NOT displayed in the console.
In the console there are only debugging-logs, which may help to find the origin of the error.


luposdate3000 allows for many configuration options where completely independent code is used.

Currently the only tests are integration tests using complete sparql-queries at once.
To gain usefull insight, what breakes when, the object "lupos.s00misc.SanityCheck" provides assertion functions, which are included in debug-build, but not in release-build.

To add a new testcase::

```bash
inputdata=xyz/file.n3
sparql=xyz/file.sparql
targetdata=xyz/file.n3
outputfoldername=xyz
testname=xyz
mode=SELECT_QUERY_RESULT  # or mode=MODIFY_RESULT
./launcher --mainClass=Binary_Test_Suite --generate "$inputdata" "$sparql" "$targetdata" "$outputfoldername" "$testname" "$mode"
```
