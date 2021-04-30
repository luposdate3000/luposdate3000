## other Databases used for benchmark-comparisons

This installs other databases, which can be used to compare benchmark results.

luposdate3000 can be used as a wrapper for apache jena

```bash
#virtuoso
{
    apt install autoconf automake libtool flex bison gperf gawk m4 make libssl-dev
    cd $dependencieshome
    git clone git://github.com/openlink/virtuoso-opensource.git --depth=1 virtuoso
    cd virtuoso
    ./autogen.sh
    CFLAGS="-O2 -m64" ./configure --prefix=$dependencieshome/virtuoso-dist
    make -j20
    make install
}
#blazegraph
{
    wget https://github.com/blazegraph/database/releases/download/BLAZEGRAPH_2_1_6_RC/blazegraph.jar
}
```
