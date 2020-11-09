# luposdate3000

luposdate3000 is a Database which can process sparql-queries.

## Installation

[installation readme](documentation/installation/README.md)

## Usage

To compile everything the first time you must perform a complete rebuild, which is done by using:

```bash
./compile-module-korio.sh
./compile-module-all.sh
```

This takes a lot of time (on my computer 10 Minutes using a 4GHz CPU with NVME-SSD and lots of available RAM).
The final compiled jars/js/native-libs can be found in the folder build-cache/bin

Take a look into the file ./compile-module-all.sh.
If you change something, compile only the necessary module (and the ones depending on it) instead of everything.
If you only need the jars you can append "--fast" to the calls to "./generate-buildfile-module.kts".

Compiling just a single Module for JVM only takes around 10 Seconds - which is much faster.

### Tests

To run all tests use the following:

```bash
./exec-binary-test-suite-jvm.sh
```

These tests are executed repeatedly and therefore these should be compileable and executable.
Currently luposdate3000 is not finished - thats why there are lots of failing tests.
The test script writes a summary to the file resources/binary/config2.
The comparison to the file resources/binary/config should show if there are new tests working - or if there are some tests broken right now.
You can modify resources/binary/config to enable/disable specific tests.
The found errors are summarized in the file log/error.

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
./exec-binary-test-add.sh "$inputdata" "$sparql" "$targetdata" "$outputfoldername" "$testname" "$mode"
```

### Benchmarks

The benchmarks are updated soon ...

The script

```bash
./exec-benchmark-luposdate3000-internal.sh
```

runs the sp2b and bsbm benchmarks and puts the results into the folder "benchmark_results".
Alternatively the script

```bash
./exec-benchmark-luposdate3000-internal-paper.sh
```

may also be used, which executed different benchmarks.
You may want to change the targetfolder to something else to get a usefull history.
It is unlikely that your hardware matches the one used to create the current benchmark values.
The current git revision is part of the benchmark-result-filename, to be able to exactly compare which code-changes changes the performance.

### Configuration

You can configure the database as you want.

Take a look into "compile-module-all.sh"
There you can change the parameters for the indiviual modules.

The options "--nosuspend" or "--suspend" must be the same for EVERY module, because this changes method signatures in the binarys.

All configurable options can be found in the files src/*/configOptions.
These csv files columns are: "optionName,optionType(const val/val/typedef),variableType(Int,...),defaultValue"

Additionally you can exchange some modules.
Currently these exchangeable Modules are:
* luposdate3000_endpoint_none or luposdate3000_endpoint_java_sockets
* luposdate3000_jena_wrapper_on or luposdate3000_jena_wrapper_off
* one of luposdate3000_launch*

### Interface

The interface is defined in the File src/luposdate3000_endpoint/commonMain/kotlin/lupos/s16network/LuposdateEndpoint.kt
The function "initialize" in that file must be called first before anything else.
If you just use the other functions from that file, the function is called automatically.
If you want to call any other function from the database, make sure that the "initialize" function is called first.
The function should be called only once, but it is protected such that it is not called twice.

If you extend this interface:
* Do not overload any function there
* Do not use default parameters - which is basically function overloading too.
* Annotate all public functions with '@JsName("")'

All of the above should prevent name-mangling which makes it much easier to use this interface from native and javascript.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

### hints for performant kotlin code

Things you can change:

* "import java.xyz"<br/>
  Only use java-only code within src/jvm* and never within src/common.
  Because jvm related code will not compile on native.
* "import xyz.\*"<br/>
  Dont use the \* in imports, always include exactly what you want.
  Because otherwise the compilation is very very slow.
* "inline" function modifier<br/>
  The compiler copy paste the byte-code into the calling function.
  This modifier is applied recoursively - therefore the function byte-code may explode in size.
  Use this when the code is performance critical.
  There is a limit of 64kb byte-code per function - this is very easy to reach if you put inline everywhere.
  This modifier should NEVER be used during debugging, because it breaks the exception-stack-traces.
  if you have functional parameters combine it with "crossinline" to inline that parameter too.
* "internal" function/class modifier <br/>
  This hides the function/class from the api.
  This decreases the binary size.
  This increases the compile-speed, because there are no generated/exported functions
* function pointers<br/>
  Be careful.
  This creates (multiple) additional objects.
  If these are called, multiple indirect function calls are performed, which may be slow.
  Dont do this in performance-critical sections.
  The kotlin way to to this is a function with a "when(xyz)" where each case contains the code you otherwise would have put into different function pointers.
* coroutines<br/>
  If you have blocking code for example when you need a Lock, than recursively put the "suspend" keyword in front of every required function - the whole way up until "main" if necessary.
  Do the same for every function, which calles any of the functions you modify.
  Do NOT use "runBlocking" - because it does just that blocking.
  Use "runBlocking" only if you are really forced to do so by an external library.
* "suspend" function modifier<br/>
  The compiler performs a lot of changes to functions with this keyword.
  Try to avoid any function-local variable ... and put that as a instance-variable instead - because otherwise all these local variables are transformet into a temporary object by the kotlin compiler - which is bad.
  If a suspend function does not contain any suspend code, the compiler changes are minimal.
  Try to call other suspending functions as late as possible within a function, to reduce the number of variabes which must be stored by the compiler.
* debug-only-code<br/>
  use this:
  
  ```kotlin
    SanityCheck{
       debug-code here
    }
  ```
  
  than everything inside that section is removed completely from the release-build
* "override" function modifier<br/>
  Every function with this modifier can NOT be inlined, and is therefore limited in performance.
  Use it only, when there are different implementations at Runtime.
  If during runtime only one version is used, than use typealias, to select the effective class.
* "nullable" datatypes<br/>
  Introduce boxing if the datatype otherwise is a primitive such as Int.
  Especially in performance-critical code try to avoid it.
  If you already use a variable of an object type, this should not introduce additional overhead.
* "constant values"<br/>
  Primitive global constants should be defined like "const val x = 42"
  Strings are "primitives" in this context.
  Everything which is not primitive may at least use "val" instead of "var"
* "class-variables" and "instance-variables"<br/>
  Annotate all of these with "@JvmField" - otherwise the kotlin compiler generates useless getter and setter code.

Current limitations of the kotlin compiler:

* The jvm-target generates the "fastest" code
* At time of testing ... the native target is approximately 20 times slower.
* Native target calls freeze on EVERYTHING.
  That means, that any global variable needs a full replication on every modification which is very very bad.
  Currently the native version is not executable because of this memory model "freeze everything immediately" - which just dont work with a database.
  This should change with upcoming kotlin-releases.
* Gradle has more kotlin related features than maven especially if not-java targets should be build
* If the program breaks and you dont think it should break, than clear the build folder and compile again.
  The incremental build sometimes just dont work - especially with enabled inlining.
  The current version of the scripts automatically wipe the build folder for every build - just to be sure.
  This "bug" happend too often for me.
* My experiments show that Threads are faster than Coroutines, because of the constantly allocated and released intermediate Objects, which are completely useless.
  Maybe this changes with updates to the kotlin-compiler.
  Currently: just use Threads.

## License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0)
