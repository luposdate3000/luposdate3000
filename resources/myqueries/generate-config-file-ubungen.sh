touch config.csv2
rm config.csv2

wget -O "ubungen_d1.n3" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/Luebeck.n3
wget -O "ubungen_d2.n3" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/University_of_Luebeck.n3
wget -O "ubungen_d3.n3" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/BL.n3
wget -O "ubungen_q01.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1a.sparql
wget -O "ubungen_q02.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1b.sparql
wget -O "ubungen_q03.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1c.sparql
wget -O "ubungen_q04.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1d.sparql
wget -O "ubungen_q05.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1e.sparql
wget -O "ubungen_q06.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1f.sparql
ttl="ubungen_d1.n3"
sparql="ubungen_q01.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d1.n3"
sparql="ubungen_q02.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d1.n3"
sparql="ubungen_q03.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d1.n3"
sparql="ubungen_q04.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d2.n3"
sparql="ubungen_q05.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d3.n3"
sparql="ubungen_q06.sparql"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2

wget -O "ubungen_d4.n3" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/lubm_demo.n3
wget -O "ubungen_q07.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query1.rq
wget -O "ubungen_q08.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query2.rq
wget -O "ubungen_q09.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query3.rq
wget -O "ubungen_q10.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query4.rq
wget -O "ubungen_q11.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query5.rq
wget -O "ubungen_q12.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query6.rq
wget -O "ubungen_q13.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query7.rq
wget -O "ubungen_q14.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query8.rq
wget -O "ubungen_q15.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query9.rq
wget -O "ubungen_q16.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query10.rq

ttl="ubungen_d4.n3"
sparql="ubungen_q07.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_q08.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_q09.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_q10.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_q11.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_q12.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_q13.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_q14.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_q15.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d4.n3"
sparql="ubungen_q16.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2

wget -O "ubungen_d5.n3" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/sp2b.n3
wget -O "ubungen_q17.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query1.rq
wget -O "ubungen_q18.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query2.rq
wget -O "ubungen_q19.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query3.rq
wget -O "ubungen_q20.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query4.rq
wget -O "ubungen_q21.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query5.rq
wget -O "ubungen_q22.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query6.rq

ttl="ubungen_d5.n3"
sparql="ubungen_q17.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_q18.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_q19.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_q20.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_q21.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
ttl="ubungen_d5.n3"
sparql="ubungen_q22.rq"
srx=""
echo $(wc -l $ttl | sed "s/ .*//g"),$sparql,$ttl,$srx >> config.csv2
