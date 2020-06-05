#!/bin/bash
touch config.csv2
rm config.csv2

for triples2 in 1 2
do
	(
		cd /opt/bsbmtools-0.2
		./generate -s ttl -pc $triples2 > /dev/null 2>&1
	)
	mkdir tmpdata
	cat /opt/bsbmtools-0.2/dataset.ttl | ./../../exec-compress-chunked-n3.kts tmpdata false
	mv tmpdata/data0.n3 bsbm-$triples2.n3
	rm -rf tmpdata
	triples=$(wc -l bsbm-$triples2.n3 | sed "s/ .*//g")
	mv bsbm-$triples2.n3 bsbm-$triples.n3
#country
	grep "bsbm:country" bsbm-$triples.n3 | sed "s/.*bsbm:country //g" | sed "s/ .$//g" | sort -u > bsbm-helper-$triples-country.txt
#review
	grep "rdf:type bsbm:Review" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:Review .//g" | sort -u > bsbm-helper-$triples-review.txt
#product
	grep "rdf:type bsbm:Product" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:Product .//g" | sort -u > bsbm-helper-$triples-product.txt
#producer
	grep "rdf:type bsbm:Producer" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:Producer .//g" | sort -u > bsbm-helper-$triples-producer.txt
#offer
	grep "rdf:type bsbm:Offer" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:Offer .//g" | sort -u > bsbm-helper-$triples-offer.txt
#product type
	grep "rdf:type bsbm:ProductType" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:ProductType .//g" | sort -u > bsbm-helper-$triples-producttype.txt
#product feature
	grep "rdf:type bsbm:ProductFeature" bsbm-$triples.n3 | sed "s/ rdf:type bsbm:ProductFeature .//g" | sort -u > bsbm-helper-$triples-productfeature.txt


	for q in $(find -name "q*.sparql" | sed "s/.sparql//g" | sed "s/.*q/q/g")
	do
		echo $triples,$q.sparql,bsbm-$triples.n3,$q-$triples.srx >> config.csv2
	done
done

cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2

