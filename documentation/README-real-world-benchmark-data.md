## REAL world benchmark data

There are several REAL world datasets available - the following are the ones, I had downloaded.
Some of these datasets are already used in scientific publications (not all of the provided queries in the publications work as intended).

These datasets are not required to run the database, but can be used to evaluate your own implementations.

some import benchmarks (measured in seconds):

1. total (t2+t3)
2. text->intermediate (t6+?)
3. intermediate->database (t4+t5)
4. intermediate->database.dict
5. intermediate->database.triples
6. text->intermediate.parse

| Dataset | Size (turtle) | Size (intermediate) | original triples | inferred triples | distinct triples | dictionary entries |        t1 |        t2 |        t3 |        t4 |        t5 |        t6 |
| :------ | ------------: | ------------------: | ---------------: | ---------------: | ---------------: | -----------------: | --------: | --------: | --------: | --------: | --------: | --------: |
| yago1   |       0.9 GiB |           0.837 GiB |         19012849 |          4703635 |         21383706 |           12752436 |   328.648 |   195.406 |   133.242 |   121.260 |    11.982 |   150.957 |
| barton  |       9.5 GiB |           1.350 GiB |         78497317 |                0 |         35184003 |           10830905 |   723.561 |   596.459 |   127.102 |   104.411 |    22.691 |   505.545 |
| yago2   |       5.8 GiB |           4.201 GiB |        112824705 |         16972765 |        123689922 |           54351098 |  1469.642 |   833.633 |   636.009 |   521.786 |   114.222 |   654.937 |
| yago3   |       8.5 GiB |           5.415 GiB |        138264317 |         71721473 |        142608259 |           72644117 |  1944.292 |  1144.821 |   799.471 |   676.511 |   122.960 |   942.936 |
| yago2s  |       9.5 GiB |           4.447 GiB |        171684850 |                  |        151474901 |           42599960 |  1601.202 |  1058.769 |   542.433 |   406.789 |   135.643 |   827.324 |
| btc2019 |      38.0 GiB |          12.936 GiB |        256059356 |                  |        256059356 |           82631100 |  2564.312 |  1532.119 |  1032.193 |   743.061 |   289.132 |  1190.925 |
| yago4   |     474.0 GiB |          82.797 GiB |       2539591846 |                  |       2489858800 |          571715647 | 49340.051 | 18979.163 | 30360.888 |  5587.976 | 24772.911 | 13362.678 |
| btc2010 |     624.0 GiB |          53.233 GiB |       3171793030 |                  |       1426828906 |          279151232 | 46576.546 | 38684.171 |  7892.375 |  1781.721 |  6110.654 | 34402.117 |

The size in the "intermediate" column should give you an idea how large the dataset is, when using a dictionary.
These intermediate files are generated using `./launcher.main.kts --run --mainClass=Import --runArgument_Luposdate3000_Launch_Import:inputFileName=turtle-file-name.nt`
The intermediate representation is much smaller than the original text size.
Of course you need some additional memory to evaluate queries on these datasets.

Some datasets contain invalid data as for example btc2019 contains the "OgtbbJctXNk"^^<http://www.w3.org/2001/XMLSchema#integer> - which is obviously not a number.
In these cases I 'fix' those errors by removing the type specifier - or if possible change it to something useful as for example "decimal".
Additionally there are lot of "dateTime" entries, which are not formatted according to the specification - I fixed these to match the intended "date" as close as possible.

You can copy paste execute the following script.
However, there are some commands (with comments), which will run until your disk-space is consumed.
Make sure you read the following comments.

Lets define the root path for all real-world data as 
```bash
export LUPOS_REAL_WORLD_DATA_ROOT=/mnt/luposdate-testdata/
```

```bash
#bsbm
{
#syntetic data
    cd $dependencieshome
    wget https://phoenixnap.dl.sourceforge.net/project/bsbmtools/bsbmtools/bsbmtools-0.2/bsbmtools-v0.2.zip
    unzip bsbmtools-v0.2.zip
    rm bsbmtools-v0.2.zip
    cd bsbmtools-0.2
    git init .
    git add .
    git commit -m a
    # The following line runs until your disk space is completely consumed.
    # Make sure to abort it if you have enough benchmark data.
    mkdir -p ${LUPOS_REAL_WORLD_DATA_ROOT}/bsbm
    touch ${LUPOS_REAL_WORLD_DATA_ROOT}/bsbm/stat.csv
    ${luposdate3000home}/exec-benchmark-generate-bsbm.main.kts
}
#sp2b
{
#syntetic data
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
    mkdir -p ${LUPOS_REAL_WORLD_DATA_ROOT}/sp2b
    touch ${LUPOS_REAL_WORLD_DATA_ROOT}/sp2b/stat.csv
    ${luposdate3000home}/exec-benchmark-generate-sp2b.main.kts
}
#wordnet 3.1
{
    cd ${LUPOS_REAL_WORLD_DATA_ROOT}
    mkdir wordnet
    cd wordnet
    wget http://wordnet-rdf.princeton.edu/static/wordnet.nt.gz
    gunzip wordnet.nt.gz
}
#btc2019
{
    cd ${LUPOS_REAL_WORLD_DATA_ROOT}
    mkdir btc2019
    cd btc2019
    wget https://zenodo.org/record/2634588/files/btc2019-triples.nt.gz?download=1 btc2019-triples.nt.gz
    gunzip btc2019-triples.nt.gz
}
#btc2010
{
    cd ${LUPOS_REAL_WORLD_DATA_ROOT}
    mkdir btc2010
    cd btc2010
    wget https://km.aifb.kit.edu/projects/btc-2010/000-CONTENTS
    # This one is about 620 gigabytes.
    # Make sure you have enough space available and a good enough internet connection.
    wget -i 000-CONTENTS
    # concat everything into a *.n4 file
}
#yago1
{
    cd ${LUPOS_REAL_WORLD_DATA_ROOT}
    mkdir yago1
    cd yago1
    wget https://yago-knowledge.org/data/yago1/yago-1.0.0-turtle.7z
    7z x yago-1.0.0-turtle.7z
    rm yago-1.0.0-turtle.7z statistics.txt
}
#yago2
{
    cd ${LUPOS_REAL_WORLD_DATA_ROOT}
    mkdir yago2
    cd yago2
    wget https://yago-knowledge.org/data/yago2/yago-2.3.0-turtle.7z
    7z x yago-2.3.0-turtle.7z
    rm yago-2.3.0-turtle.7z statistics.txt
}
#yago2s
{
    cd ${LUPOS_REAL_WORLD_DATA_ROOT}
    mkdir yago2s
    cd yago2s
    wget https://yago-knowledge.org/data/yago2s/yago-2.5.3-turtle-simple.7z
    7z x yago-2.5.3-turtle-simple.7z
    rm yago-2.5.3-turtle-simple.7z statistics.txt
}
#yago3
{
    cd ${LUPOS_REAL_WORLD_DATA_ROOT}
    mkdir yago3
    cd yago3
    wget https://yago-knowledge.org/data/yago3/yago-3.0.2-turtle-simple.7z
    7z x yago-3.0.2-turtle-simple.7z
    rm yago-3.0.2-turtle-simple.7z statistics.txt
}
#yago4
{
    cd ${LUPOS_REAL_WORLD_DATA_ROOT}
    mkdir yago4
    cd yago4
    # This one is about 470 gigabytes.
    # Make sure you have enough space available and a good enough internet connection.
    wget -r https://yago-knowledge.org/data/yago4/full/2020-02-24/
    gzip -d yago-knowledge.org/data/yago4/full/2020-02-24/*.gz
    mv yago-knowledge.org/data/yago4/full/2020-02-24/*.nt .
    rm -rf yago-knowledge.org
    cat yago-wd-* >> yago-all.nt
    rm yago-wd-*
}
#barton
{
    cd ${LUPOS_REAL_WORLD_DATA_ROOT}
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
