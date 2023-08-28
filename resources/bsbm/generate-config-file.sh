#!/bin/bash
# This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
# Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, version 3.
#
# This program is distributed in the hope that it will be useful, but
# WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
# General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program. If not, see <http://www.gnu.org/licenses/>.

touch config.csv2
rm config.csv2 *.srx *.sparql

for triples2 in 1853 2210 2553
do
#	(
#		cd /opt/bsbmtools-0.2
#		./generate -s ttl -pc $triples2 > /dev/null 2>&1
#	)
#	mkdir tmpdata
#	cat /opt/bsbmtools-0.2/dataset.ttl | ./../../exec-compress-chunked-n3.main.kts tmpdata false
#	mv tmpdata/data0.n3 bsbm-$triples2.n3
#	rm -rf tmpdata
	triples=$(wc -l bsbm-$triples2.n3 | sed "s/ .*//g")
#	mv bsbm-$triples2.n3 bsbm-$triples.n3
#productPropertyNumeric1
	grep "bsbm:productPropertyNumeric1" bsbm-$triples.n3 | sed "s/.*bsbm:productPropertyNumeric1 //g" | sed "s/ .$//g" | sort -u | shuf > bsbm-helper-$triples-productPropertyNumeric1.txt
#productPropertyNumeric2
	grep "bsbm:productPropertyNumeric2" bsbm-$triples.n3 | sed "s/.*bsbm:productPropertyNumeric2 //g" | sed "s/ .$//g" | sort -u | shuf > bsbm-helper-$triples-productPropertyNumeric2.txt
#productPropertyNumeric3
	grep "bsbm:productPropertyNumeric3" bsbm-$triples.n3 | sed "s/.*bsbm:productPropertyNumeric3 //g" | sed "s/ .$//g" | sort -u | shuf > bsbm-helper-$triples-productPropertyNumeric3.txt
#country
	grep "bsbm:country" bsbm-$triples.n3 | sed "s/.*bsbm:country //g" | sed "s/ .$//g" | sort -u | shuf > bsbm-helper-$triples-country.txt
#review
	grep "rdf:type bsbm:Review" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:Review .//g" | sort -u | shuf > bsbm-helper-$triples-review.txt
#product
	grep "rdf:type bsbm:Product ." bsbm-$triples.n3 | sed "s/ rdf:type bsbm:Product .//g" | sort -u | shuf > bsbm-helper-$triples-product.txt
#producer
	grep "rdf:type bsbm:Producer" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:Producer .//g" | sort -u | shuf > bsbm-helper-$triples-producer.txt
#offer
	grep "rdf:type bsbm:Offer" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:Offer .//g" | sort -u | shuf > bsbm-helper-$triples-offer.txt
#product type
	grep "rdf:type bsbm:ProductType" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:ProductType .//g" | sort -u > bsbm-helper-$triples-producttypeA.txt
	grep "rdf:type bsbm-inst:" bsbm-$triples.n3 | sed "s/.*bsbm-inst:/bsbm-inst:/g" | sed "s/ .$//g"| sort -u > bsbm-helper-$triples-producttypeB.txt
	comm -12 bsbm-helper-$triples-producttypeA.txt bsbm-helper-$triples-producttypeB.txt | shuf > bsbm-helper-$triples-producttype.txt
#product feature
	grep "rdf:type bsbm:ProductFeature" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:ProductFeature .//g" | sort -u | shuf > bsbm-helper-$triples-productfeature.txt
#month
	grep '"' bsbm-$triples.n3 | sed 's/^[^"]*//g' | grep -v "@" | grep -v "integer" | grep -v USD | grep "^.[0-9].*" | sed -E "s/^.(....-..-).*$/\1/g" | sed "s/$/01/g" | sort -u | grep -v -- "-11-" | grep -v -- "-12-" > bsbm-helper-$triples-month.txt
	cat bsbm-helper-$triples-month.txt | head -n$(($(wc -l bsbm-helper-$triples-month.txt | sed "s/ .*//g") - 2 )) | shuf > bsbm-helper-$triples-month2.txt
	mv bsbm-helper-$triples-month2.txt bsbm-helper-$triples-month.txt
#currentDate
	grep '"' bsbm-$triples.n3 | sed 's/^[^"]*//g' | grep -v "@" | grep -v "integer" | grep -v USD | grep "^.[0-9].*" | sed -E "s/^(.....-..-..).*$/\1/g" | sed 's/$/"^^xsd:date/g' | sort -u | shuf > bsbm-helper-$triples-date.txt
#word
	grep "rdfs:label" bsbm-$triples.n3 | sed "s/.*rdfs:label \"//g" | sed "s/\".*$//g" | tr " " "\n" | sort -u | shuf > bsbm-helper-$triples-word.txt
	for q in $(find -name "*.template" | sed "s/.template//g")
	do
		i=$(head -n1 bsbm-helper-$triples-month.txt | sed -E "s/.....0*(.*).../\1/g")
		p=$(head -n1 bsbm-helper-$triples-month.txt| sed -E "s/(....).*/\1/g")
		month0=$p-$(printf %02d $i)-01
		month1=$p-$(printf %02d $((i + 1)))-01
		month2=$p-$(printf %02d $((i + 2)))-01
		cat $q.template \
		| sed "s/%ProductPropertyNumeric1%/$(head -n1 bsbm-helper-$triples-productPropertyNumeric1.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%ProductPropertyNumeric2%/$(head -n1 bsbm-helper-$triples-productPropertyNumeric2.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%ProductPropertyNumeric3%/$(head -n1 bsbm-helper-$triples-productPropertyNumeric3.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%Country1%/$(head -n1 bsbm-helper-$triples-country.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%Country2%/$(head -n2 bsbm-helper-$triples-country.txt | tail -n1 | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%Country%/$(head -n3 bsbm-helper-$triples-country.txt | tail -n1 | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%Review1%/$(head -n1 bsbm-helper-$triples-review.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%Product%/$(head -n1 bsbm-helper-$triples-product.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%Product1%/$(head -n2 bsbm-helper-$triples-product.txt | tail -n1 | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%ProductType%/$(head -n1 bsbm-helper-$triples-producttype.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%ProductFeature1%/$(head -n1 bsbm-helper-$triples-productfeature.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%ProductFeature2%/$(head -n2 bsbm-helper-$triples-productfeature.txt | tail -n1 | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%ProductFeature3%/$(head -n3 bsbm-helper-$triples-productfeature.txt | tail -n1 | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%Producer%/$(head -n1 bsbm-helper-$triples-producer.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%Offer1%/$(head -n1 bsbm-helper-$triples-offer.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%currentDate%/$(head -n1 bsbm-helper-$triples-date.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%word1%/$(head -n1 bsbm-helper-$triples-word.txt | sed -e 's/[\/&]/\\&/g')/g" \
		| sed "s/%ConsecutiveMonth_0%/$month0/g" \
		| sed "s/%ConsecutiveMonth_1%/$month1/g" \
		| sed "s/%ConsecutiveMonth_2%/$month2/g" \
		> $q-$triples.sparql
		echo $triples,$q-$triples.sparql,bsbm-$triples.n3,$q-$triples.srx >> config.csv2
		q1=$(realpath $q-$triples.sparql)
		d1=$(realpath bsbm-$triples.n3)
		o1=$(realpath $q-$triples.srx)
		(
echo "evaluating " ./bin/sparql --data=$d1 --query=$q1 --results=xml " > " $o1
		cd /src/apache-jena-4.9.0
#		./bin/sparql --data=$d1 --query=$q1 --results=xml > $o1
echo "done evaluating"
		)
	done
#	mkdir tmpdata
#        cat bsbm-$triples.n3 | ./../../exec-compress-chunked-n3.main.kts tmpdata
#        mv tmpdata/data0.n3 bsbm-$triples.n3
#        rm -rf tmpdata
	rm bsbm-helper-*
done

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-1853.n3 /src/luposdate3000/resources/bsbm/bi_query1-1-1853.sparql /src/luposdate3000/resources/bsbm/bi_query1-1-1853.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-1853.n3 /src/luposdate3000/resources/bsbm/bi_query4-1853.sparql /src/luposdate3000/resources/bsbm/bi_query4-1853.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-1853.n3 /src/luposdate3000/resources/bsbm/bi_query1-1853.sparql /src/luposdate3000/resources/bsbm/bi_query1-1853.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-1853.n3 /src/luposdate3000/resources/bsbm/bi_query1-3-1853.sparql /src/luposdate3000/resources/bsbm/bi_query1-3-1853.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-1853.n3 /src/luposdate3000/resources/bsbm/bi_query4-1-1853.sparql /src/luposdate3000/resources/bsbm/bi_query4-1-1853.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-1853.n3 /src/luposdate3000/resources/bsbm/bi_query1-2-1853.sparql /src/luposdate3000/resources/bsbm/bi_query1-2-1853.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2210.n3 /src/luposdate3000/resources/bsbm/bi_query1-1-2210.sparql /src/luposdate3000/resources/bsbm/bi_query1-1-2210.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2210.n3 /src/luposdate3000/resources/bsbm/bi_query4-2210.sparql /src/luposdate3000/resources/bsbm/bi_query4-2210.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2210.n3 /src/luposdate3000/resources/bsbm/bi_query1-2210.sparql /src/luposdate3000/resources/bsbm/bi_query1-2210.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2210.n3 /src/luposdate3000/resources/bsbm/bi_query1-3-2210.sparql /src/luposdate3000/resources/bsbm/bi_query1-3-2210.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2210.n3 /src/luposdate3000/resources/bsbm/bi_query4-1-2210.sparql /src/luposdate3000/resources/bsbm/bi_query4-1-2210.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2210.n3 /src/luposdate3000/resources/bsbm/bi_query1-2-2210.sparql /src/luposdate3000/resources/bsbm/bi_query1-2-2210.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2553.n3 /src/luposdate3000/resources/bsbm/bi_query1-1-2553.sparql /src/luposdate3000/resources/bsbm/bi_query1-1-2553.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2553.n3 /src/luposdate3000/resources/bsbm/bi_query4-2553.sparql /src/luposdate3000/resources/bsbm/bi_query4-2553.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2553.n3 /src/luposdate3000/resources/bsbm/bi_query1-2553.sparql /src/luposdate3000/resources/bsbm/bi_query1-2553.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2553.n3 /src/luposdate3000/resources/bsbm/bi_query1-3-2553.sparql /src/luposdate3000/resources/bsbm/bi_query1-3-2553.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2553.n3 /src/luposdate3000/resources/bsbm/bi_query4-1-2553.sparql /src/luposdate3000/resources/bsbm/bi_query4-1-2553.srx
./luposdate3000-2.sh /src/luposdate3000/resources/bsbm/bsbm-2553.n3 /src/luposdate3000/resources/bsbm/bi_query1-2-2553.sparql /src/luposdate3000/resources/bsbm/bi_query1-2-2553.srx

./jena.sh /src/luposdate3000/resources/bsbm/bsbm-1853.n3 /src/luposdate3000/resources/bsbm/explore_query9-1853.sparql  >  /src/luposdate3000/resources/bsbm/explore_query9-1853.n3
./jena.sh /src/luposdate3000/resources/bsbm/bsbm-1853.n3 /src/luposdate3000/resources/bsbm/explore_query12-1853.sparql  >  /src/luposdate3000/resources/bsbm/explore_query12-1853.n3
./jena.sh /src/luposdate3000/resources/bsbm/bsbm-2210.n3 /src/luposdate3000/resources/bsbm/explore_query9-2210.sparql  >  /src/luposdate3000/resources/bsbm/explore_query9-2210.n3
./jena.sh /src/luposdate3000/resources/bsbm/bsbm-2210.n3 /src/luposdate3000/resources/bsbm/explore_query12-2210.sparql  >  /src/luposdate3000/resources/bsbm/explore_query12-2210.n3
./jena.sh /src/luposdate3000/resources/bsbm/bsbm-2553.n3 /src/luposdate3000/resources/bsbm/explore_query9-2553.sparql  >  /src/luposdate3000/resources/bsbm/explore_query9-2553.n3
./jena.sh /src/luposdate3000/resources/bsbm/bsbm-2553.n3 /src/luposdate3000/resources/bsbm/explore_query12-2553.sparql  >  /src/luposdate3000/resources/bsbm/explore_query12-2553.n3
