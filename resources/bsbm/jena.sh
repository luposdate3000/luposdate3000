echo usage $0 data-file query-file
(
cd /src/apache-jena-4.9.0
./bin/sparql --data=$1 --query=$2 --results=xml
)
