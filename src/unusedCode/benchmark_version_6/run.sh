#!/bin/bash
(
cd ..

#./exec-launch-single-instance.sh &

#curl -H "Content-Type: application/x-www-form-urlencoded" localhost:2324/import/intermediate?query=/mnt/luposdate-testdata/sp2b/33554432/intermediate
#for f in $(find $(pwd)/benchmark_version_6 -name "*.sparql")
#do
#	echo $f
#	curl -H "Content-Type: application/x-www-form-urlencoded" -d "@$f" localhost:2324/sparql/query > $f-sp2b-33554432.srx
#done

#curl -H "Content-Type: application/x-www-form-urlencoded" localhost:2324/import/intermediate?query=/mnt/luposdate-testdata/btc2019/data/intermediate
#for f in $(find $(pwd)/benchmark_version_6 -name "*.sparql")
#do
#	echo $f
#	curl -H "Content-Type: application/x-www-form-urlencoded" -d "@$f" localhost:2324/sparql/query > $f-btc-2019.srx
#done
)

for f in *.srx
do
	cat $f | sed "s-<literal datatype=\"http://www.w3.org/2001/XMLSchema#integer>-,-g" | sed "s-</literal>-,-g" | sed -E "s/<binding name="occurences">/\n/g" | sed -E "s-(</binding>|</results?>)--g" | sed "s-<binding name=.*>--g" | tr '\n' ' ' | sed "s-<result>-#-g" | tr '#' '\n' | sed "s/ //g" | sed "s/^,//g" | sed "s/,,/,/g" | sed "s/,$//g" | sed "s/[^0-9,]//g" | grep , | awk -F',' '{ sum+=$3; print $1 "," $2 "," $3 "," sum }' > $f.csv
	total=$(tail -n1 $f.csv | sed "s/.*,//g")
	sed -e "s/$/,$total/" -i $f.csv
	awk -F',' '{ rel=$4/$5; print $0 "," rel }' $f.csv > $f.csv2
	mv $f.csv2 $f.csv
	cat $f.csv
done
