# luposdate3000

luposdate3000 is a Database which can process sparql-queries.

## Installation

This describes how to install the prerequisites for the database.

If you chose Windows, the compiler is about 25% slower compared to the linux version - tested with the same database-code and the same compiler-version.

[readme linux](documentation/installation/README-linux.md)
[readme windows](documentation/installation/README-windows.md)

## Usage and Compilation

You can choose either the simple mode - and use intellij-IDE (and use only the standard (jvm-only) modules).
The compilation in intellij is faster than the compilation on the commandline - due to the usage of caches.
On the other hand exactly this caches can introduce Bugs in the database, which are not present in the code, due to false-negatives in the cache-detection.
In the case of code-generation, the caches are used very badly - such that you have to clean them manually.

[readme intelliJ-IDE](documentation/README-usage-compile-intellij.md)

Or you may choose the advanced mode without IDE, which allows you to use all configurations.

This describes how to compile the database itself (during development).

[readme-advanced](documentation/README-usage-compile-advanced.md)


The compilation of the SPA-Client is described here:

[readme-spaclient](documentation/README-SPAClient.md)

## Tests

[readme](documentation/README-tests.md)

## Benchmarks

[readme - benchmark-data](documentation/README-real-world-benchmark-data.md)
[readme - other databases](documentation/README-other-databases.md)
[readme - will be updated soon](documentation/README-benchmarks.md)

## Configuration

[readme](documentation/README-configuration.md)

## Interface

[readme](documentation/README-interface.md)

## Database internal structure

[readme](documentation/README-database-internals.md)

## Hints for performant kotlin code

[readme](documentation/README-performant-kotlin.md)

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0)


##FAQ

[faq](documentation/README-faq)
