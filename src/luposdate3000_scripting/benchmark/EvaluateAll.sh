#!/bin/bash
find /data/benchmark-results -name "*pretty.xml" -delete
for f in $(find /data/benchmark-results -type f -name "*xml")
do
	cat $f \
	| sed 's/<?xml version="1.0"?>//g' \
	| sed 's/<sparql [^>]*>/<sparql>/g' \
	| xmllint --format -  \
	| tr "\n" " " \
	| sed 's-       <binding-<binding-g' \
	| sed 's-       </binding-</binding-g' \
	| sed 's-         <literal-<literal-g' \
	| sed 's-     </result>-</result>-g' \
	| sed 's-     <result>-\n<result>-g' \
	| sed 's-  </results>-\n</results>-g' \
	> $f.pretty.xml
done
