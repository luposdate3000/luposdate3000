# Multiplatform Database

This repository contains some basic classes for a multiplatform database using the [multiplatform technology of Kotlin](https://kotlinlang.org/docs/reference/multiplatform.html).

## Module Structure

There are the following modules in the multiplatform project:

- `common`: This module is the very basic one offering common code shared between all other modules.
- `common_diskbased`: This module shares code for traditional databases storing data on disk.
- `common_mainmemory`: This module shares code for main memory databases.
- Modules with target JVM:
    - Helper modules:
        - `unsafehelper_jvm`: This module offers the class `lupos.io.buffer.UnsafePage` for using off-heap memory (see [Guide to sun.misc.Unsafe](https://www.baeldung.com/java-unsafe)) and is used by the modules `unsafe_jvm` and `memorymapped_unsafe_jvm`.
    - Modules targeting main memory databases:
        - `heap_jvm`: This class uses pages in the heap of the jvm.
        - `unsafe_jvm`: This class uses off-heap memory (see module `unsafehelper_jvm`).
    - Modules targeting traditional databases storing data on disk:
        - `randomaccess_jvm`: This module uses random access files for storing data on disk.
        - `memorymapped_jvm`: This module uses memory mapped files for storing data on disk.
        - `memorymapped_unsafe_jvm`: This module uses memory mapped files and off-heap memory in combination. The code is currently not working correctly.
- Modules with target JavaScript (JS): 
    - `heap_js`: This module contains the code for a main memory database using JavaScript in the browser.

## Page

All the data is organized in pages. The pages have a standard size of 8 KBytes (corresponding to standard block sizes of traditional hard disks and SSDs and may be aligned to bigger sizes of newer external storage medias). The page size may be adapted for the main memory databases, too. Using pages have the advantage to avoid garbage collection if consequently used without creation and (de-)serialization of objects, but by working directly in the pages.

There are mainly two types of pages used in this project:
- Pages using a byte array in the heap (see `lupos.io.buffer.ByteArrayPage` in module `common`).
- Pages using the Unsafe API of Java/JVM for the support of off-heap memory (not consuming cycles in the garbage collection), see `lupos.io.buffer.UnsafePage` in module `unsafehelper_jvm`.

The used type of page is set in the platform modules in the files `lupos.io.buffer.MemoryAccess` by e.g. `actual typealias Page = ByteArrayPage`.

## Buffer Manager

For caching data and holding the data in pages avoiding garbage collection cycles, there is a buffer manager defined in `lupos.io.buffer.BufferManager` (module `common`), which is implemented in the files `lupos.io.buffer.BufferManagerHelper` of the modules `common_diskbased` and `common_mainmemory`.

## Performance Issues

The whole project uses special techniques to achieve highest performance. The project especially avoids the call of virtual methods for those methods, which are very often called during data processing. Note that one method call saves only minimal execution time, but if for Big data processing millions or even billions of virtual method calls are saved, then this may be one key to achieve superior performance in comparison to other engines.

The first way to avoid virtual method calls is to use expect/actual classes of kotlin (the original purpose is to support multiplatform programming). For our project this is the case for the `lupos.io.buffer.Page` and `lupos.io.buffer.BufferManager` classes. 

The second way is to use extensively and consequently inline functions and methods (see e.g. `lupos.io.buffer.Page`). 

### (De-)Serialization with High Performance in Generic Database Indices

Avoiding virtual method calls and at the same time offering generic database indices only works when consequently using lambdas and inline methods and functions.

Please consider `lupos.datastructures.b_plus_tree.B_Plus_Tree_Uncompressed_Int_to_Int` (File `lupos.datastructures.b_plus_tree.B_Plus_Tree_Int_to_Int`) in module `common`. Here, the `get` operator is defined as inline, such that calls to this operator are inlined. Furthermore, the method calls the generic inline `search`-method of the `lupos.datastructures.b_plus_tree.B_Plus_Tree<Int, Int>`-class with some inline classes to (de-)serialize (key and value) objects of the datatype `Int` (i.e., `::deserializeInt` for deserializing integers (from a page) and `::serializedSizeOfInt` for retrieving the size of the deserialized data object (both inline functions are located in the file `lupos.io.buffer.PageHelper`)).

*Please follow these techniques for developing your database indices and other cool stuff for high performance!!!* 

## Testing

Tests are defined in the common module in the file `common/src/test/kotlin/commonTest/BPlusTreeTests.kt`.

The tests cannot be be evaluated in the common module, but must be run in each platform module (using `gradlew` at the command line in the project directory):

```
gradlew heap_jvm:test
gradlew memorymapped_jvm:test
gradlew memorymapped_unsafe_jvm:test
gradlew randomaccess_jvm:test
gradlew unsafe_jvm:test
```

Testing the js module via `gradlew heap_js:test` needs more configuration, see [Testing common modules](https://blog.kotlin-academy.com/testing-common-modules-66b39d641617).