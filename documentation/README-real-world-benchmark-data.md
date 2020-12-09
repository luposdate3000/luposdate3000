## REAL world benchmark data

There are several REAL world datasets available - the following are the ones, I had downloaded.
Some of these datasets are already used in scientific publications (not all of the provided queries in the publications work as intended).

These datasets are not required to run the database, but can be used to evaluate your own implementations.

| Dataset | Size (turtle) | Size (intermediate) | triples   |
| :------ | ------------: | ------------------: | -------:  |
| barton  |       9.5 GiB |             1.5 GiB |  78497317 |
| btc2010 |     624.0 GiB | ???                 |           |
| btc2019 |      38.0 GiB |             8.8 GiB | 256059356 |
| yago1   |       0.9 GiB |             0.5 GiB |  19012849 |
| yago2   |       5.8 GiB |             2.7 GiB | 112824705 |
| yago2s  |       9.5 GiB |             3.0 GiB | 171684850 |
| yago3   |       8.5 GiB |             3.6 GiB | 138264317 |
| yago4   |     474.0 GiB | ???                 |           |

Currently luposdate3000 is inmemory only, which means, you can only load benchmarks files, which are smaller than your available RAM.
The size in the "intermediate" column should give you an idea how large the dataset is, when using a dictionary.
These intermediate files are generated using "./exec-import.main.kts turtle-file-name.nt"
The numbers are much smaller than the original size.
Of course you need some additional memory to evaluate queries on these datasets.
The intermediate size "???" means, that I have not completely loaded that dataset currently.

Some datasets contain invalid data as for example btc2019 contains the "OgtbbJctXNk"^^<http://www.w3.org/2001/XMLSchema#integer> - which is obviously not a number.
In these cases I 'fix' those errors by removing the type specifier - or if possible change it to something useful as for example "decimal".
Additionally there are lot of "dateTime" entries, which are not formatted according to the specification - I fixed these to match the intended "date" as close as possible.

You can copy paste execute the following script.
However, there are some commands (with comments), which will run until your disk-space is consumed.
Make sure you read the following comments.

```bash
#bsbm
{
    cd $dependencieshome
    wget https://ayera.dl.sourceforge.net/project/bsbmtools/bsbmtools/bsbmtools-0.2/bsbmtools-v0.2.zip
    unzip bsbmtools-v0.2.zip
    rm bsbmtools-v0.2.zip
    cd bsbmtools-0.2
    git init .
    git add .
    git commit -m a
    # The following line runs until your disk space is completely consumed.
    # Make sure to abort it if you have enough benchmark data.
    mkdir -p /mnt/luposdate-testdata/bsbm
    touch /mnt/luposdate-testdata/bsbm/stat.csv
    ${luposdate3000home}/exec-benchmark-generate-bsbm.main.kts
}
#sp2b
{
    cd $dependencieshome
    wget http://dbis.informatik.uni-freiburg.de/content/projects/SP2B/docs/sp2b-v1_00-full.tar.gz
    tar -xzf sp2b-v1_00-full.tar.gz
    rm sp2b-v1_00-full.tar.gz
    cd sp2b
    git init .
    git add .
    git commit -m a
    git apply ${luposdate3000home}/documentation/installation/sp2b.patch
    cd src
    make
    mv sp2b_gen ../bin/
    # The following line runs until your disk space is completely consumed.
    # Make sure to abort it if you have enough benchmark data.
    mkdir -p /mnt/luposdate-testdata/sp2b
    touch /mnt/luposdate-testdata/sp2b/stat.csv
    ${luposdate3000home}/exec-benchmark-generate-sp2b.main.kts
}
#btc2019
{
    cd /mnt/luposdate-testdata
    mkdir btc2019
    cd btc2019
    wget https://zenodo.org/record/2634588/files/btc2019-triples.nt.gz?download=1 btc2019-triples.nt.gz
    gunzip btc2019-triples.nt.gz
}
#btc2010
{
    cd /mnt/luposdate-testdata
    mkdir btc2010
    cd btc2010
    wget https://km.aifb.kit.edu/projects/btc-2010/000-CONTENTS
    # This one is about 600 gigabytes.
    # Make sure you have enough space available and a good enough internet connection.
    wget -i 000-CONTENTS
}
#yago1
{
    cd /mnt/luposdate-testdata
    mkdir yago1
    cd yago1
    wget https://yago-knowledge.org/data/yago1/yago-1.0.0-turtle.7z
    7z x yago-1.0.0-turtle.7z
    rm yago-1.0.0-turtle.7z statistics.txt
}
#yago2
{
    cd /mnt/luposdate-testdata
    mkdir yago2
    cd yago2
    wget https://yago-knowledge.org/data/yago2/yago-2.3.0-turtle.7z
    7z x yago-2.3.0-turtle.7z
    rm yago-2.3.0-turtle.7z statistics.txt
}
#yago2s
{
    cd /mnt/luposdate-testdata
    mkdir yago2s
    cd yago2s
    wget https://yago-knowledge.org/data/yago2s/yago-2.5.3-turtle-simple.7z
    7z x yago-2.5.3-turtle-simple.7z
    rm yago-2.5.3-turtle-simple.7z statistics.txt
}
#yago3
{
    cd /mnt/luposdate-testdata
    mkdir yago3
    cd yago3
    wget https://yago-knowledge.org/data/yago3/yago-3.0.2-turtle-simple.7z
    7z x yago-3.0.2-turtle-simple.7z
    rm yago-3.0.2-turtle-simple.7z statistics.txt
}
#yago4
{
    cd /mnt/luposdate-testdata
    mkdir yago4
    cd yago4
    wget -r https://yago-knowledge.org/data/yago4/full/2020-02-24/
    gzip -d yago-knowledge.org/data/yago4/full/2020-02-24/*.gz
    mv yago-knowledge.org/data/yago4/full/2020-02-24/*.nt .
    rm -rf yago-knowledge.org
}
#barton
{
    cd /mnt/luposdate-testdata
    mkdir barton
    cd barton
    wget http://dslam.cs.umd.edu/data/barton/barton.mods.rdf.tar.gz
    tar -xzf barton.mods.rdf.tar.gz
    rm barton.mods.rdf.tar.gz
    for f in $(find mods_rdf_xml -type f)
    do
        rapper -o ntriples $f >> barton.nt
    done
    rm -rf mods_rdf_xml
}
```
