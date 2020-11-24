#!/bin/bash
curl -H "Content-Type: application/x-www-form-urlencoded" -d "@resources/myqueries/ygraphicO.sparql" benjamin0:2324/sparql/query > ygraphicO.sparql.srx
curl -H "Content-Type: application/x-www-form-urlencoded" -d "@resources/myqueries/ygraphicP.sparql" benjamin0:2324/sparql/query > ygraphicP.sparql.srx
curl -H "Content-Type: application/x-www-form-urlencoded" -d "@resources/myqueries/ygraphicS.sparql" benjamin0:2324/sparql/query > ygraphicS.sparql.srx
curl -H "Content-Type: application/x-www-form-urlencoded" -d "@resources/myqueries/ygraphicSP.sparql" benjamin0:2324/sparql/query > ygraphicSP.sparql.srx
curl -H "Content-Type: application/x-www-form-urlencoded" -d "@resources/myqueries/ygraphicSO.sparql" benjamin0:2324/sparql/query > ygraphicSO.sparql.srx
curl -H "Content-Type: application/x-www-form-urlencoded" -d "@resources/myqueries/ygraphicPO.sparql" benjamin0:2324/sparql/query > ygraphicPO.sparql.srx
for f in ygraphic*.sparql.srx
do
	cat $f \
	| sed "s-</literal>-,-g" \
	| sed "s-<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer\">--g" \
	| sed "s-</binding>--g" \
	| sed "s-<binding name=.*>--g" \
	| sed "s-</result>--g" \
	| sed "s-<result>-#-g" \
	| tr '\n' ' ' \
	| tr '#' '\n' \
	| sed "s- --g" \
	| grep -v "sparqlxmlns" \
	| sed "s-</results></sparql>--g" \
	| grep -v "head" \
	> $f.csv
done
