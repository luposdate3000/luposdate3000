## hints for performant kotlin code

Things you can change:

* "import java.xyz"<br/>
  Only use java-only code within jvmMain and never within commonMain.
  Because jvm related code will not compile on native.
  The current build-file layout should mark this as error (in most cases).
* "import xyz.\*"<br/>
  Dont use the \* in imports, always include exactly what you want.
  Because otherwise the compilation is very very slow.
* "inline" function modifier<br/>
  The compiler copy paste the byte-code into the calling function.
  This modifier is applied recoursively - therefore the function byte-code may explode in size.
  Use this when the code is performance critical.
  There is a limit of 64kb byte-code per function - this is very easy to reach if you put inline everywhere.
  This modifier should NEVER be used during debugging, because it breaks the exception-stack-traces.
  If you have functional parameters combine it with "crossinline" to inline that parameter too.
* "internal" function/class modifier <br/>
  This hides the function/class from the api.
  This decreases the binary size.
  This increases the compile-speed, because there are no generated/exported functions.
* function pointers<br/>
  Be careful.
  This creates (multiple) additional objects.
  If these are called, multiple indirect function calls are performed, which may be slow.
  Dont do this in performance-critical sections.
  The performant-kotlin way to to this is a function with a "when(xyz)" where each case contains the code you otherwise would have put into different function pointers.
* coroutines<br/>
  If you have blocking code for example when you need a Lock, than recursively put the "suspend" keyword in front of every required function - the whole way up until "main" if necessary.
  Do the same for every function, which calles any of the functions you modify.
  Do NOT use "runBlocking" - because it does just that: blocking.
  Use "runBlocking" only if you are really forced to do so by an external library.
* "suspend" function modifier<br/>
  The compiler performs a lot of changes to functions with this keyword.
  Try to avoid any function-local variable ... and put that as a instance-variable instead - because otherwise all these local variables are transformet into a temporary object by the kotlin compiler - which is bad.
  If a suspend function does not contain any suspend code (or only the last call is a suspendable function), then compiler changes are minimal.
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
  An "annotate-everything"-compiler option was suggested by other people too, but is currently not supported.

Current limitations of the kotlin compiler:

* The jvm-target generates the "fastest" code
* At time of testing ... the native target is approximately 20 times slower.
* Native target calls freeze on EVERYTHING.
  That means, that any global variable needs a full replication on every modification which is very very bad.
  Currently the native version of the database is not executable because of this memory model "freeze everything immediately" - which just dont work with a database.
  This should change with upcoming kotlin-releases.
* Gradle has more kotlin related features than maven especially if not-java targets should be build.
* If the program breaks and you dont think it should break, than clear the build folder and compile again.
  The incremental build sometimes just dont work - especially with enabled inlining.
  The current version of the scripts automatically wipe the build folder for every build - just to be sure.
  This "bug" happend too often for me.
* My experiments show that Threads are faster than Coroutines, because of the constantly allocated and released intermediate Objects, which are completely useless.
  Maybe this changes with updates to the kotlin-compiler.
  Currently: just use Threads.
