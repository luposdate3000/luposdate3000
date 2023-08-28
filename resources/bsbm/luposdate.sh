echo usage $0 data-file query-file
echo '{"query":' > x
cat $2 |jq -Rsa . >> x
echo ',"rdf":' >> x
cat $1 |jq -Rsa . >> x
echo ',"formats":["xml","plain"],"inference":"NONE","inferenceGeneration":"GENERATEDOPT","evaluator":"MemoryIndex"}' >> x
curl 'https://www.ifis.uni-luebeck.de/sparql-endpoint/nonstandard/sparql' \
-X 'POST' \
-d @x \
| jq -rc '.XML[0]' > x
cat x
