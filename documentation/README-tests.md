## Tests

Most tests are currently included in the gradle-build task.

There are additional randomized Tests.
To execute them, you can take a look at the file [runRandomizedTests.sh](../runRandomizedTests.sh)




If you want to have code-generation unit tests, than you need to enable them explicitly, because there are currently way too much errors.
Navigate to the file
[SparqlTestSuiteConverterToUnitTest.kt](../src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt)
and change the value of withCodeGen to true.

Afterwards generate the module, which contains the unit-test code.

If you are using the commandline, the tests can be generated and executed using:

```bash
./launcher.main.kts --setup
# assemble is like build, but skips the tests
./gradlew assemble
./launcher.main.kts --run --mainClass=Generate_Unit_Test_Suite_Multi
./launcher.main.kts --setup
./gradlew build
```

In intellij, you need to execute the main function in the Luposdate3000_Launch_Generate_Unit_Test_Suite_Multi package.
Than the intellij files need to be recreated to add the new module.

```bash
./launcher.main.kts --setup
```

Finally you can execute the tests by compiling the project.
