#!/bin/bash
(
	cd /opt/sp2b/bin
	# t parameter specifies amount of tupels
	./sp2b_gen -t 100
)
cat /opt/sp2b/bin/sp2b.n3 | sed "s/\.$/ ./g" > ./common/src/main/resources/sp2b/sp2b.n3

pkill java

/opt/apache-jena-fuseki-3.14.0/fuseki-server &

sleep 3

curl -X POST --data-urlencode "dbName=sp2b" --data-urlencode "dbType=mem" -H  "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/$/datasets

curl -X POST -d "@./common/src/main/resources/sp2b/sp2b.n3" -H "Content-Type: text/turtle" localhost:3030/sp2b/data

(
	cd common/src/main/resources/sp2b
	for f in *.sparql
	do
	        curl -X POST --data-urlencode "query=$(cat $f)" -H "Content-Type: application/x-www-form-urlencoded; charset=UTF-8" localhost:3030/sp2b/query > "${f%.sparql}.srj"
	done
)
pkill java
