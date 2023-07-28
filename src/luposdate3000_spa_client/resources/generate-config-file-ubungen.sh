# files with ubungen_d contain initial data
# files with ubungen_s contain solution data
# files with ubungen_q contain initial queries
# files with ubungen_t contain target queries


function generateConfig() {
moduleName=$1
queryNumber=$2
queryIn=$3
dataIn=$4
queryTarget=$5
dataOut=$6

mkdir $moduleName
cp $queryTarget $moduleName
cp $queryIn $moduleName
cp $dataIn $moduleName
cat <<EOF >> "tutorials.unused..json"
{
"label":"${moduleName}",
"data":"resources/${moduleName}/tutorial.json"
}
EOF
cat <<EOF | js-beautify > "${moduleName}/data${queryNumber}.json"
{
    "sparql": "resources/$moduleName/$queryTarget",
    "rdf": "resources/$moduleName/$dataIn"
}
EOF
cat <<EOF | js-beautify >> "${moduleName}/tutorial.unused.json"
{
    "label":"Task ${queryNumber}",
    "empty": "resources/$moduleName/$queryIn",
    "target": "resources/$moduleName/$queryTarget",
    "rdf": "resources/$moduleName/$dataIn"
}
EOF
cat <<EOF | js-beautify > "${moduleName}/config.unused.json"
{
    "endpoints": [{
        "name": "Browser Luposdate3000",
    }],
    "useRDF": true
}
EOF
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

generateConfig "cloud-and-web-technologies" "1" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t01.sparql" ""
generateConfig "cloud-and-web-technologies" "2" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t02.sparql" ""
generateConfig "cloud-and-web-technologies" "3" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t03.sparql" ""
generateConfig "cloud-and-web-technologies" "4" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t04.sparql" ""
generateConfig "cloud-and-web-technologies" "5" "ubungen_q01.sparql" "ubungen_d2.n3" "ubungen_t05.sparql" ""
generateConfig "cloud-and-web-technologies" "6" "ubungen_q01.sparql" "ubungen_d3.n3" "ubungen_t06.sparql" ""

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

generateConfig "sw-sparql1.0" "1" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t07.sparql" ""
generateConfig "sw-sparql1.0" "2" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t08.sparql" ""
generateConfig "sw-sparql1.0" "3" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t09.sparql" ""
generateConfig "sw-sparql1.0" "4" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t10.sparql" ""
generateConfig "sw-sparql1.0" "5" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t11.sparql" ""
generateConfig "sw-sparql1.0" "6" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t12.sparql" ""
generateConfig "sw-sparql1.0" "7" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t13.sparql" ""
generateConfig "sw-sparql1.0" "8" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t14.sparql" ""
generateConfig "sw-sparql1.0" "9" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t15.sparql" ""
generateConfig "sw-sparql1.0" "10" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t16.sparql" ""

#https://www.ifis.uni-luebeck.de/~groppe/sw/sparql11
wget -O "ubungen_d5.n3" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/sp2b.n3
wget -O "ubungen_t17.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query1.rq
wget -O "ubungen_t18.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query2.rq
wget -O "ubungen_t19.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query3.rq
wget -O "ubungen_t20.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query4.rq
wget -O "ubungen_t21.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query5.rq
wget -O "ubungen_t22.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query6.rq
wget -O "ubungen_q03.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/startquery.rq

generateConfig "sw-sparql1.1" "1" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t17.sparql" ""
generateConfig "sw-sparql1.1" "2" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t18.sparql" ""
generateConfig "sw-sparql1.1" "3" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t19.sparql" ""
generateConfig "sw-sparql1.1" "4" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t20.sparql" ""
generateConfig "sw-sparql1.1" "5" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t21.sparql" ""
generateConfig "sw-sparql1.1" "6" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t22.sparql" ""

rm ubungen*
