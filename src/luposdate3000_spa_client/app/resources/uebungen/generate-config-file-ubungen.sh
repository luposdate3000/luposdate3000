touch config.csv2
rm config.csv2

# files with ubungen_d contain initial data
# files with ubungen_s contain solution data
# files with ubungen_q contain initial queries
# files with ubungen_t contain target queries

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
ttl="ubungen_d1.n3"
sparql="ubungen_t01.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d1.n3"
sparql="ubungen_t02.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d1.n3"
sparql="ubungen_t03.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d1.n3"
sparql="ubungen_t04.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d2.n3"
sparql="ubungen_t05.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d3.n3"
sparql="ubungen_t06.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2

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

ttl="ubungen_d4.n3"
sparql="ubungen_t07.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_t08.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_t09.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_t10.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_t11.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_t12.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_t13.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_t14.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_t15.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_t16.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2

#https://www.ifis.uni-luebeck.de/~groppe/sw/sparql11
wget -O "ubungen_d5.n3" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/sp2b.n3
wget -O "ubungen_t17.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query1.rq
wget -O "ubungen_t18.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query2.rq
wget -O "ubungen_t19.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query3.rq
wget -O "ubungen_t20.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query4.rq
wget -O "ubungen_t21.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query5.rq
wget -O "ubungen_t22.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query6.rq

ttl="ubungen_d5.n3"
sparql="ubungen_t17.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_t18.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_t19.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_t20.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_t21.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_t22.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2


cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2
