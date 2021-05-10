## other Databases used for benchmark-comparisons

This installs other databases, which can be used to compare benchmark results.

luposdate3000 can be used as a wrapper for apache jena

```bash
dependencieshome=/opt
#list from https://www.w3.org/wiki/SparqlImplementations ->


#4store
{
    # distributed
    apt install 4store


    # config by parameters
}
#Amazon Neptune
{
    #pay to use
}
#Apache ARQ/Jena/Fuseki/Joseki/Sesame ->
{
    # not-distributed
    #luposdate3000 provides a wrapper for jena
}
#Apache Marmotta ->
{
    # distributed
    # sparql2sql interface
    cd $dependencieshome
    wget http://archive.apache.org/dist/marmotta/3.4.0/apache-marmotta-3.4.0-installer.tar.gz
    tar -xf apache-marmotta-3.4.0-installer.tar.gz
    cd apache-marmotta-3.4.0
    # the following launches an gui
    # choose $dependencieshome/marmotta as installation dir
    java -jar marmotta-installer-3.4.0.jar
    cd ../marmotta
    # on first run, the following launches an gui to select the network-interface
    ./startup.sh


    # config in "$dependencieshome/marmotta/marmotta-home/system-config.properties"
}
#AllegroGraph
{
    #pay to use
}
#Bigdata(R)(distributed)
#Blazegraphopen source, precompiled
{
    cd $dependencieshome
    wget https://github.com/blazegraph/database/releases/download/BLAZEGRAPH_2_1_6_RC/blazegraph.jar
}
#Eclipse RDF4J ->
#Corese - Conceptual Resource Search Engine ->
#Cray Urika-GD ->
#cwm ->
#Hercules(distributed js)
#Joost's Lib B ->
#KAON2 ->
#MarkLogic ->
#Mulgara ->
#NitrosBase ->
#Ontotext GraphDB ->
#Open Anzo ->
#Oracle DB Enterprise Spatial & Graph ->
#Pellet ->
#RDFLib Python library ->
#Redland / Redstore ->
#SPARQL4J ->
#Stardog ->
#The Semantic Discovery System ->
#Virtuosoopen source, source code
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
```



