## REAL world benchmark data

There are several REAL world datasets available - the following are the ones, I had downloaded.
Some of these datasets are already used in scientific publications (not all of the provided queries in the publications work as intended).

These datasets are not required to run the database, but can be used to evaluate your own implementations.

| Dataset | Size  |
| :------ | ----: |
| barton  | 4.7G  |
| btc2010 | 624GB |
| btc2019 | 47GB  |
| yago1   | 1.5GB |
| yago2   | 8.4GB |
| yago2s  | 9.5GB |
| yago3   | 11GB  |
| yago4   |     ? |

Currently luposdate3000 is inmemory only, which means, you can only load benchmarks files, which are smaller than your available RAM.


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
#barton
{
    cd /mnt/luposdate-testdata
    mkdir barton
    cd barton
    wget http://dslam.cs.umd.edu/data/barton/barton.mods.rdf.tar.gz
    tar -xzf barton.mods.rdf.tar.gz
    rm barton.mods.rdf.tar.gz
}
```
