## other Databases used for benchmark-comparisons

This installs other databases, which can be used to compare benchmark results.

luposdate3000 can be used as a wrapper for apache jena

```bash
dependencieshome=/opt
#list from https://www.w3.org/wiki/SparqlImplementations
#list from wikipedia

#4store
{
    # class: distributed
    apt install 4store
    # using fixed path "/var/lib/4store" which makes it hard to simulate multiple instances
}
#Amazon Neptune
{
    # pay to use
}
#Apache ARQ/Jena/Fuseki/Joseki/Sesame ->
{
    # class: not distributed, Maven, NoEndpoint
    # luposdate3000 provides a wrapper for jena
    cd $dependencieshome
    wget https://archive.apache.org/dist/jena/binaries/jena-fuseki1-1.6.0-distribution.tar.gz
    tar -xzf jena-fuseki1-1.6.0-distribution.tar.gz 
}
#Apache Marmotta ->
{
    # class: distributed, sparql2sql
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
    # pay to use
}
#Blazegraph Bigdata(R)
{
    # class: not distributed
    cd $dependencieshome
    wget https://github.com/blazegraph/database/releases/download/BLAZEGRAPH_2_1_6_RC/blazegraph.jar
}
#Eclipse RDF4J ->
{
    # class: SHACL, GeoSPARQL, Maven, NoEndpoint
}
#Corese - Conceptual Resource Search Engine ->
{
    # class SHACL, only validation???
    cd $dependencieshome
    wget http://files.inria.fr/corese/distrib/corese-server-4.1.6d.jar
}
#Cray Urika-GD ->
{
    # no download available?
}
#cwm ->
{
    # class: commandline-parser?!?
    cd $dependencieshome
    git clone https://github.com/linkeddata/swap
}
#Hercules(distributed js)
{
    git clone http://lab.arielworks.com/hg/hercules
}
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



