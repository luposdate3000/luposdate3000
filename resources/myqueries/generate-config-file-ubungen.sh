# files with ubungen_d contain initial data
# files with ubungen_s contain solution data
# files with ubungen_q contain initial queries
# files with ubungen_t contain target queries

touch config.csv2
rm config.csv2
function generateConfig() {
moduleName=$1
queryNumber=$2
queryIn=$3
dataIn=$4
queryTarget=$5
dataOut=$6

echo '{"query":' > x
cat $queryTarget |jq -Rsa . >> x
echo ',"rdf":' >> x
cat $dataIn |jq -Rsa . >> x
echo ',"formats":["xml","plain"],"inference":"NONE","inferenceGeneration":"GENERATEDOPT","evaluator":"MemoryIndex"}' >> x
curl 'https://www.ifis.uni-luebeck.de/sparql-endpoint/nonstandard/sparql' \
-X 'POST' \
-d @x \
| jq -rc '.XML[0]' > $dataOut
echo $(wc -l $dataIn | sed "s/ .*//g"),$queryTarget,$dataIn,$dataOut >> config.csv2
}




#https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/sw/sparql
wget -O "ubungen_d1.n3" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/Luebeck.n3
wget -O "ubungen_d2.n3" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/University_of_Luebeck.n3
wget -O "ubungen_d3.n3" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/BL.n3
wget -O "ubungen_t01.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1a.sparql
wget -O "ubungen_t02.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1b.sparql
wget -O "ubungen_t03.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1c.sparql
wget -O "ubungen_t04.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1d.sparql
wget -O "ubungen_t05.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1e.sparql
wget -O "ubungen_t06.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1f.sparql
wget -O "ubungen_q01.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/empty.sparql

generateConfig "cloud-and-web-technologies" "1" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t01.sparql" "ubungen_r01.srx"
generateConfig "cloud-and-web-technologies" "2" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t02.sparql" "ubungen_r02.srx"
generateConfig "cloud-and-web-technologies" "3" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t03.sparql" "ubungen_r03.srx"
generateConfig "cloud-and-web-technologies" "4" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t04.sparql" "ubungen_r04.srx"
generateConfig "cloud-and-web-technologies" "5" "ubungen_q01.sparql" "ubungen_d2.n3" "ubungen_t05.sparql" "ubungen_r05.srx"
generateConfig "cloud-and-web-technologies" "6" "ubungen_q01.sparql" "ubungen_d3.n3" "ubungen_t06.sparql" "ubungen_r06.srx"

#https://www.ifis.uni-luebeck.de/~groppe/sw/sparql10
wget -O "ubungen_d4.n3" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/lubm_demo.n3
wget -O "ubungen_t07.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query1.rq
wget -O "ubungen_t08.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query2.rq
wget -O "ubungen_t09.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query3.rq
wget -O "ubungen_t10.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query4.rq
wget -O "ubungen_t11.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query5.rq
wget -O "ubungen_t12.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query6.rq
wget -O "ubungen_t13.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query7.rq
wget -O "ubungen_t14.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query8.rq
wget -O "ubungen_t15.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query9.rq
wget -O "ubungen_t16.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query10.rq
wget -O "ubungen_q02.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/lubm_test.sparql

generateConfig "sw-sparql1.0" "1" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t07.sparql" "ubungen_r07.srx"
generateConfig "sw-sparql1.0" "2" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t08.sparql" "ubungen_r08.srx"
generateConfig "sw-sparql1.0" "3" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t09.sparql" "ubungen_r09.srx"
generateConfig "sw-sparql1.0" "4" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t10.sparql" "ubungen_r10.srx"
generateConfig "sw-sparql1.0" "5" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t11.sparql" "ubungen_r11.srx"
generateConfig "sw-sparql1.0" "6" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t12.sparql" "ubungen_r12.srx"
generateConfig "sw-sparql1.0" "7" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t13.sparql" "ubungen_r13.srx"
generateConfig "sw-sparql1.0" "8" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t14.sparql" "ubungen_r14.srx"
generateConfig "sw-sparql1.0" "9" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t15.sparql" "ubungen_r15.srx"
generateConfig "sw-sparql1.0" "10" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t16.sparql" "ubungen_r16.srx"

#https://www.ifis.uni-luebeck.de/~groppe/sw/sparql11
wget -O "ubungen_d5.n3" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/sp2b.n3
wget -O "ubungen_t17.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query1.rq
wget -O "ubungen_t18.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query2.rq
wget -O "ubungen_t19.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query3.rq
wget -O "ubungen_t20.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query4.rq
wget -O "ubungen_t21.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query5.rq
wget -O "ubungen_t22.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query6.rq
wget -O "ubungen_q03.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/startquery.rq

generateConfig "sw-sparql1.1" "1" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t17.sparql" "ubungen_r17.srx"
generateConfig "sw-sparql1.1" "2" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t18.sparql" "ubungen_r18.srx"
generateConfig "sw-sparql1.1" "3" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t19.sparql" "ubungen_r19.srx"
generateConfig "sw-sparql1.1" "4" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t20.sparql" "ubungen_r20.srx"
generateConfig "sw-sparql1.1" "5" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t21.sparql" "ubungen_r21.srx"
generateConfig "sw-sparql1.1" "6" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t22.sparql" "ubungen_r22.srx"

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2
