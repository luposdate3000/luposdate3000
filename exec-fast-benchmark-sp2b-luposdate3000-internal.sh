#!/bin/bash
export JAVA_OPTS="-Xmx30g"
#in milliseconds
timemin=0
#in seconds
timeout=120
triples=524288
#triples=16384

./generate-buildfile.kts 1.4.255-SNAPSHOT jvm Endpoint Off Fast Sequential Heap MultiMap SingleList Dummy Korio None Local Off BTree BTree None Empty
./tool-gradle-build.sh




pkill java
sleep 3
(export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64 ; ./build/executable 127.0.0.1>x)&
sleep 3

curl -X POST --data-binary "/mnt/sp2b-testdata/$triples/data0.n3" http://localhost:80/import/turtle --header "Content-Type:text/plain"
#jmap -dump:live,file=dump 9997
#visualvm

wait
exit





p=$(pwd)/benchmark_results/sp2b
mkdir -p $p
rm log/queries2

#find resources/sp2b/ -name "*.sparql" > log/queries
echo "resources/sp2b/q1.sparql" > log/queries
echo "resources/sp2b/q3a.sparql" >> log/queries
echo "resources/sp2b/q3b.sparql" >> log/queries
echo "resources/sp2b/q3c.sparql" >> log/queries
echo "resources/sp2b/q6.sparql" >> log/queries
echo "resources/sp2b/q10.sparql" >> log/queries
echo "resources/sp2b/q12a.sparql" >> log/queries
echo "resources/sp2b/q12b.sparql" >> log/queries
echo "resources/sp2b/q12c.sparql" >> log/queries
csvfile=$p/luposdate-$(git rev-parse HEAD)-internal.csv
triplesfolder=/mnt/sp2b-testdata/${triples}
size=$(du -sb $triplesfolder | sed 's/\t.*//g')
pkill java
sleep 3
(export JAVA_HOME=/usr/lib/jvm/java-14-openjdk-amd64 ; ./build/executable 127.0.0.1 > log/server 2>&1)&
sleep 3
a=$(($(date +%s%N)/1000000))
for f in $(find $triplesfolder/*.n3)
do
	curl -X POST --data-binary "@$f" http://localhost:80/import/turtle --header "Content-Type:text/plain" > /dev/null 2>&1
done
code=$?
b=$(($(date +%s%N)/1000000))
c=$((b - a))
qps=$(bc <<< "scale=2; 1000 / $c")
echo "resources/sp2b/load.sparql,$triples,$code,1,$c,$qps,$size" >> $csvfile
while read query; do
	echo "----------"
	echo $query
#	curl -X POST --data-binary "@$query" http://localhost:80/sparql/query > /dev/null 2>&1
done < log/queries

echo "q1"
#curl -X POST --data-binary "SELECT ?yr WHERE { ?journal <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Journal> . ?journal <http://purl.org/dc/elements/1.1/title> \"Journal 1 (1940)\"^^<http://www.w3.org/2001/XMLSchema#string> . ?journal <http://purl.org/dc/terms/issued> ?yr . }" http://localhost:80/sparql/query
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?journal <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Journal> . ?journal <http://purl.org/dc/elements/1.1/title> \"Journal 1 (1940)\"^^<http://www.w3.org/2001/XMLSchema#string> . ?journal <http://purl.org/dc/terms/issued> ?yr . }" http://localhost:80/sparql/query
#echo "-> 1"
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?journal <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Journal> . ?journal <http://purl.org/dc/elements/1.1/title> \"Journal 1 (1940)\"^^<http://www.w3.org/2001/XMLSchema#string> . }" http://localhost:80/sparql/query
#echo "-> 1"

echo "q3a"
curl -X POST --data-binary "@resources/sp2b/q3a.sparql" http://localhost:80/sparql/query > x
#curl -X POST --data-binary "SELECT ?article WHERE { ?article <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Article> . ?article <http://swrc.ontoware.org/ontology#pages> ?value .}" http://localhost:80/sparql/query
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?article <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Article> . ?article <http://swrc.ontoware.org/ontology#pages> ?value .}" http://localhost:80/sparql/query
#echo "-> 1"

echo "q3b"
#curl -X POST --data-binary "SELECT ?article WHERE { ?article <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Article> . ?article <http://swrc.ontoware.org/ontology#month> ?value .}" http://localhost:80/sparql/query
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?article <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Article> . ?article <http://swrc.ontoware.org/ontology#month> ?value .}" http://localhost:80/sparql/query
#echo "-> 3"
echo "q3c"
#curl -X POST --data-binary "SELECT ?article WHERE { ?article <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Article> . ?article <http://swrc.ontoware.org/ontology#isbn> ?value .}" http://localhost:80/sparql/query
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?article <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Article> . ?article <http://swrc.ontoware.org/ontology#isbn> ?value .}" http://localhost:80/sparql/query
#echo "-> 0"

echo "q6"
#curl -X POST --data-binary "SELECT ?yr ?name ?document WHERE { ?class <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://xmlns.com/foaf/0.1/Document> . ?document <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class . ?document <http://purl.org/dc/terms/issued> ?yr . ?document <http://purl.org/dc/elements/1.1/creator> ?author . ?author <http://xmlns.com/foaf/0.1/name> ?name . OPTIONAL { ?class2 <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://xmlns.com/foaf/0.1/Document> . ?document2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class2 . ?document2 <http://purl.org/dc/terms/issued> ?yr2 . ?document2 <http://purl.org/dc/elements/1.1/creator> ?author2 . FILTER ( ?author = ?author2 && ?yr2 < ?yr ) } . FILTER ( !bound( ?author2 ) )}" http://localhost:80/sparql/query
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?class <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://xmlns.com/foaf/0.1/Document> . ?document <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class . ?document <http://purl.org/dc/terms/issued> ?yr . ?document <http://purl.org/dc/elements/1.1/creator> ?author . ?author <http://xmlns.com/foaf/0.1/name> ?name . OPTIONAL { ?class2 <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://xmlns.com/foaf/0.1/Document> . ?document2 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?class2 . ?document2 <http://purl.org/dc/terms/issued> ?yr2 . ?document2 <http://purl.org/dc/elements/1.1/creator> ?author2 . FILTER ( ?author = ?author2 && ?yr2 < ?yr ) } . FILTER ( !bound( ?author2 ) )}" http://localhost:80/sparql/query
#echo "-> 0"

echo "q10"
#curl -X POST --data-binary "SELECT ?subject ?predicate WHERE { ?subject ?predicate <http://localhost/persons/Paul_Erdoes>}" http://localhost:80/sparql/query
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?subject ?predicate <http://localhost/persons/Paul_Erdoes>}" http://localhost:80/sparql/query
#echo "-> 211"

echo "q12a"
#curl -X POST --data-binary "ASK { ?article <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Article> . ?article <http://purl.org/dc/elements/1.1/creator> ?person1 . ?inproc <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Inproceedings> . ?inproc <http://purl.org/dc/elements/1.1/creator> ?person2 . ?person1 <http://xmlns.com/foaf/0.1/name> ?name1 . ?person2 <http://xmlns.com/foaf/0.1/name> ?name2 . FILTER (?name1 = ?name2)}" http://localhost:80/sparql/query
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?article <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Article> . ?article <http://purl.org/dc/elements/1.1/creator> ?person1 . ?inproc <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Inproceedings> . ?inproc <http://purl.org/dc/elements/1.1/creator> ?person2 . ?person1 <http://xmlns.com/foaf/0.1/name> ?name1 . ?person2 <http://xmlns.com/foaf/0.1/name> ?name2 . FILTER (?name1 = ?name2)}" http://localhost:80/sparql/query
#echo "-> 10000"

echo "q12b"
#curl -X POST --data-binary "ASK { ?erdoes <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://xmlns.com/foaf/0.1/Person> . ?erdoes <http://xmlns.com/foaf/0.1/name> \"Paul Erdoes\"^^<http://www.w3.org/2001/XMLSchema#string> . { ?document <http://purl.org/dc/elements/1.1/creator> ?erdoes . ?document <http://purl.org/dc/elements/1.1/creator> ?author . ?document2 <http://purl.org/dc/elements/1.1/creator> ?author . ?document2 <http://purl.org/dc/elements/1.1/creator> ?author2 . ?author2 <http://xmlns.com/foaf/0.1/name> ?name . FILTER (?author!=?erdoes && ?document2!=?document && ?author2!=?erdoes && ?author2!=?author) } UNION { ?document <http://purl.org/dc/elements/1.1/creator> ?erdoes . ?document <http://purl.org/dc/elements/1.1/creator> ?author . ?author <http://xmlns.com/foaf/0.1/name> ?name . FILTER (?author!=?erdoes) }}" http://localhost:80/sparql/query
#curl -X POST --data-binary "SELECT * WHERE { ?erdoes <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://xmlns.com/foaf/0.1/Person> . ?erdoes <http://xmlns.com/foaf/0.1/name> \"Paul Erdoes\"^^<http://www.w3.org/2001/XMLSchema#string> . ?document <http://purl.org/dc/elements/1.1/creator> ?erdoes . ?document <http://purl.org/dc/elements/1.1/creator> ?author . ?author <http://xmlns.com/foaf/0.1/name> ?name . FILTER (?author!=?erdoes) }" http://localhost:80/sparql/query
#echo "-> 1"
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?erdoes <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://xmlns.com/foaf/0.1/Person> . ?erdoes <http://xmlns.com/foaf/0.1/name> \"Paul Erdoes\"^^<http://www.w3.org/2001/XMLSchema#string> . ?document <http://purl.org/dc/elements/1.1/creator> ?erdoes . ?document <http://purl.org/dc/elements/1.1/creator> ?author . ?document2 <http://purl.org/dc/elements/1.1/creator> ?author . ?document2 <http://purl.org/dc/elements/1.1/creator> ?author2 . ?author2 <http://xmlns.com/foaf/0.1/name> ?name . FILTER (?author!=?erdoes && ?document2!=?document && ?author2!=?erdoes && ?author2!=?author) }" http://localhost:80/sparql/query
#echo "-> 0"
echo "q12c"
#curl -X POST --data-binary "ASK { <http://localhost/persons/John_Q_Public> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://xmlns.com/foaf/0.1/Person> . }" http://localhost:80/sparql/query
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { <http://localhost/persons/John_Q_Public> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://xmlns.com/foaf/0.1/Person> . }" http://localhost:80/sparql/query
#echo "-> 0"


#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 ?2 <http://localhost/persons/Paul_Erdoes> . }" http://localhost:80/sparql/query
#a 211
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://purl.org/dc/elements/1.1/creator> ?v2 . }" http://localhost:80/sparql/query
#b 2327
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://purl.org/dc/elements/1.1/title> \"Journal 1 (1940)\"^^<http://www.w3.org/2001/XMLSchema#string> . }" http://localhost:80/sparql/query
#c 1
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://purl.org/dc/terms/issued> ?v2 . }" http://localhost:80/sparql/query
#d 435
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://swrc.ontoware.org/ontology#isbn> ?v2 . }" http://localhost:80/sparql/query
#e 10
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://swrc.ontoware.org/ontology#month> ?v2 . }" http://localhost:80/sparql/query
#f 11
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://swrc.ontoware.org/ontology#pages> ?v2 . }" http://localhost:80/sparql/query
#g 1674
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Article> . }" http://localhost:80/sparql/query
#h 1426
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Inproceedings> . }" http://localhost:80/sparql/query
#i 361
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://localhost/vocabulary/bench/Journal> . }" http://localhost:80/sparql/query
#j 39
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://xmlns.com/foaf/0.1/Person>. }" http://localhost:80/sparql/query
#k 1453
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?v2 . }" http://localhost:80/sparql/query
#l 3322
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://xmlns.com/foaf/0.1/Document> . }" http://localhost:80/sparql/query
#m 9
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://xmlns.com/foaf/0.1/name> \"Paul Erdoes\"^^<http://www.w3.org/2001/XMLSchema#string> . }" http://localhost:80/sparql/query
#o 1
#curl -X POST --data-binary "SELECT (COUNT(*) AS ?c) WHERE { ?v1 <http://xmlns.com/foaf/0.1/name> ?v2 . }" http://localhost:80/sparql/query
#p 1453

pkill java
