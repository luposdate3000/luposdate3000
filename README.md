# luposdate3000

luposdate3000 is a database which can process sparql-queries.

## Publications

Please cite our papers about luposdate3000:

**Benjamin Warnke, Muhammad Waqas Rehan, Stefan Fischer, Sven Groppe: Flexible data partitioning schemes for parallel merge joins in semantic web queries in: Datenbanksysteme für Business, Technologie und Web (BTW), 19. Fachtagung des GI-Fachbereichs Datenbanken und Informationssysteme, Dresden, Germany, 2021, Gesellschaft für Informatik, Bonn, LNI, Vol.P-311, this publication received the label Results Reproduced, p.237-256**

@inproceedings{Warnke21Flexible,
  author    = {Benjamin Warnke and Muhammad Waqas Rehan and Stefan Fischer and Sven Groppe},
  title     = {Flexible data partitioning schemes for parallel merge joins in semantic web queries},
  booktitle = {Datenbanksysteme für Business, Technologie und Web ({BTW}), 19. Fachtagung des GI-Fachbereichs "Datenbanken und Informationssysteme", Dresden, Germany},
  series    = {{LNI}},
  volume    = {{P-311}},
  pages     = {237--256},
  publisher = {Gesellschaft für Informatik, Bonn},
  year      = {2021},
  url       = {https://doi.org/10.18420/btw2021-12},
  note = {this publication received the label 'Results Reproduced'}
}

**Sven Groppe, Rico Klinckenberg, and Benjamin Warnke. Sound of Databases: Sonification of a Semantic Web Database Engine. PVLDB, 14(12), 2021., https://doi.org/10.14778/3476311.3476322**

BibTex Code of our VLDB'21 Demo paper:
```bibtex
    @article{Groppe2020ReViz,
      title = {Sound of Databases: Sonification of a Semantic Web Database Engine},
      author = {Sven Groppe and Rico Klinckenberg and Benjamin Warnke},
      journal = {PVLDB},
      year = {2021},
      volume = {12},
      number={14},
      url = {https://doi.org/10.1007/978-3-030-64452-9_10}
    }
```

## Installation

This describes how to install the prerequisites for the database.

If you chose Windows, the compiler is about 25% slower compared to the linux version - tested with the same database-code and the same compiler-version.

[readme linux](documentation/installation/README-linux.md)
[readme windows](documentation/installation/README-windows.md)

Installation instructions for VLDB'21 Demo:

Follow the instructions above depending on your operating system.
Make sure, that you include the additional dependencies for the SPA-Client.

## Usage and Compilation

You can compile the database in multiple different ways.

You can choose either the simple mode - and use intellij-IDE (and use only the standard (jvm-only) modules).

[readme intelliJ-IDE](documentation/README-usage-compile-intellij.md)

Or you may choose the commandline mode without IDE support, which allows you to use all configurations.

[readme-advanced](documentation/README-usage-compile-advanced.md)

Or if you want to use the SPA-Client, then the usage is described here:

[readme-spaclient](documentation/README-SPAClient.md)

Running the VLDB'21 Demo:

The webserver can be started using the instructions for the SPA-Client [readme-spaclient](documentation/README-SPAClient.md).

## Web Demos

[LUPOSDATE3000 SPA Client](https://www.ifis.uni-luebeck.de/~groppe/luposdate3000-js-client/)
[VLDB'21 Demo Webpage](https://www.ifis.uni-luebeck.de/~groppe/soundofdatabases/)

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

[faq](documentation/README-faq.md)
