## hints for performant kotlin code

Things you can change:

* "import java.xyz"<br/>
  Only use java-only code within jvmMain and never within commonMain.
  Because jvm related code will not compile on native or js.
  The current build-file layout should mark this as error (in most cases).
* "import xyz.\*"<br/>
  Dont use the \* in imports, always include exactly what you want.
  Because otherwise the compilation is very very slow.
* "inline" function modifier<br/>
  The compiler copy paste the byte-code into the calling function.
  This modifier is applied recoursively - therefore the function byte-code may explode in size.
  Use this when the code is performance critical.
  There is a limit of 64kb byte-code per function - this is very easy to reach if you put inline everywhere.
  This modifier breaks the exception-stack-traces.
  If you have functional parameters combine it with "crossinline" to inline that parameter too.
* "internal" function/class modifier <br/>
  This hides the function/class from the api.
  This decreases the binary size - because the compiler removes unnecessary code.
  This increases the compile-speed, because there are less generated/exported functions.
* "private" <br/>
  functions and classes are ok, but dont use "private" fields, because they introduce getters and setters if they are used within private functions in the context of an lambda-call.
* function pointers<br/>
  Be careful.
  This creates (multiple) additional objects.
  If these are called, multiple indirect function calls are performed, which may be slow.
  Dont do this in performance-critical sections.
  The performant-kotlin way to to this is a function with a "when(xyz)" where each case contains the code you otherwise would have put into different function pointers.
* debug-only-code<br/>
  use this:
  
  ```kotlin
    SanityCheck {
       debug-code here
    }
  ```
  
  than everything inside that section is removed completely from the release-build
* "override" function modifier<br/>
  Every function with this modifier can NOT be inlined, and is therefore limited in performance.
  Use it only, when there are different implementations at Runtime.
  Whenever possible use typealias to select the class to use at compile time.
* "nullable" datatypes<br/>
  Introduce boxing if the datatype otherwise is a primitive such as Int.
  Especially in performance-critical code try to avoid it.
  If you already use a variable of an object type, this should not introduce additional overhead - besides the non-null check.
* "constant values"<br/>
  Primitive global constants should be defined like "const val x = 42"
  Strings are "primitives" in this context.
  Everything which is not primitive may at least use "val" instead of "var"
* "class-variables" and "instance-variables"<br/>
  Annotate all of these with "@JvmField" - otherwise the kotlin compiler generates useless getter and setter code.
  An "annotate-everything"-compiler option was suggested by other people too, but is currently not supported.
* "get" and "set" kotlin-language feature <br/>
  dont use the kotlin-language features "get" and "set" on fields, because they add an extra function, which can not be inlined.
  If you need to, than write the getters/setters yourself and put the "inline" keyword in front of them.

Current limitations of the kotlin compiler:

* The jvm-target generates the "fastest" code
* At time of testing ... the native target is approximately 20 times slower - the current database does not support native due to the memory model.
* Native target calls freeze on EVERYTHING.
  That means, that any global variable needs a full replication on every modification which is very very bad.
  Currently the native version of the database is not executable because of this memory model "freeze everything immediately" - which just dont work with a database.
  This was supposed to change with upcoming kotlin-releases, but it does not seem to be in near future.
* Gradle has more kotlin related features than maven especially if non-java targets should be build.
* If the program breaks and you dont think it should break, then clear the build folder and compile again.
  The incremental build sometimes just dont work - especially with enabled inlining.
  This "bug" happend too often for me.
* My experiments show that Threads are faster than Coroutines, because of the constantly allocated and released intermediate Objects, which are completely useless.
  Maybe this changes with updates to the kotlin-compiler.
  Currently: just use Threads - because they are much faster.
