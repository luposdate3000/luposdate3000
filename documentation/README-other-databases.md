## other Databases used for benchmark-comparisons

This installs other databases, which can be used to compare benchmark results.

luposdate3000 can be used as a wrapper for apache jena

```bash
dependencieshome=/opt
#list from https://www.w3.org/wiki/SparqlImplementations ->


#4store(distributed)
{
    apt install docker-compose docker
    git clone https://github.com/big-data-europe/docker-4store.git --depth=1
    cd docker-4store
    docker-compose -f docker-compose-cluster.yml up -d
}
#Amazon Neptune
{
    #pay to use
}
#Apache ARQ/Jena/Fuseki/Joseki/Sesame ->
#Apache Marmotta ->
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



