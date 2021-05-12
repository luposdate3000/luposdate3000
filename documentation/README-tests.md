## Tests

If you want to have code-generation unit tests, than navigate to the file
[SparqlTestSuiteConverterToUnitTest.kt](../src/luposdate3000_test/src/commonMain/kotlin/lupos/test/SparqlTestSuiteConverterToUnitTest.kt)
and change the value useCodeGen to true.

Afterwards generate the module, which contains the unit-test code.
This module needs to be explicitly generated, because it has an significant impact on compile time.

If you are using the commandline, the tests can be generated and executed using:

```bash
./launcher.main.kts --setupIntellijIdea
./gradlew build
./launcher.main.kts --run --mainClass=Generate_Unit_Test_Suite_Multi
./launcher.main.kts --generateLauncher
./launcher.main.kts --setupIntellijIdea
./gradlew build
./launcher.main.kts --run --mainClass=Code_Gen_Test
```

In intellij, you need to execute the main function in the Luposdate3000_Launch_Generate_Unit_Test_Suite_Multi package.
Than the intellij files need to be recreated to add the new module.

```bash
./launcher.main.kts --generateLauncher
./launcher.main.kts --setupIntellijIdea
```

Finally you can execute the tests with the main function in Luposdate3000_Launch_Code_Gen_Test.
