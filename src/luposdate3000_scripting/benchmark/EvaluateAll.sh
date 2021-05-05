#!/bin/bash
find /data/benchmark-results -name "*pretty.xml" -delete
for f in $(find /data/benchmark-results -type f -name "*xml")
do
	cat $f \
	| sed 's/<?xml version="1.0"?>//g' \
	| sed 's/<sparql [^>]*>/<sparql>/g' \
	| xmllint --format -  \
	| tr "\n" " " \
	| sed 's-         <uri-<uri-g' \
	| sed 's-       <binding-<binding-g' \
	| sed 's-       </binding-</binding-g' \
	| sed 's-         <literal-<literal-g' \
	| sed 's-     </result>-</result>-g' \
	| sed 's-     <result>-\n<result>-g' \
	| sed 's-   </results>-\n</results>-g' \
	| sed 's-</results> </sparql>--g' \
	| sed 's-<head>.*</head>--g' \
	| sed 's-<?xml version="1.0"?> <sparql>      <results[^>]*>--g' \
	> $f.org.pretty.xml
	sort $f.org.pretty.xml > $f.sorted.pretty.xml
done


baseline=Virtuoso
for f in $(find /data/benchmark-results -type f -name ${baseline}.xml.sorted.pretty.xml)
do
	echo "compare without ordering $f"
	for g in $(ls $(echo $f | sed "s-${baseline}-*-g") | grep -v ${baseline})
	do
		diff -q $f $g
	done
done
for f in $(find /data/benchmark-results -type f -name ${baseline}.xml.org.pretty.xml)
do
	echo "compare with ordering $f"
	for g in $(ls $(echo $f | sed "s-${baseline}-*-g") | grep -v ${baseline})
	do
		diff -q $f $g
	done
done
