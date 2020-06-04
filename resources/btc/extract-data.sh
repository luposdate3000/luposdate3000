cat *.sparql \
| grep -v "^#" \
| sed 's/"[^"]*"//g' \
| tr " " "\n" \
| grep "...*" \
| grep -v "^?" \
| grep -vi "distinct" \
| grep -vi "where" \
| grep -vi "select" \
| grep -v "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>" \
| sort \
| uniq > x
cat *.sparql \
| grep -v "^#" \
| sed 's/^[^"]*//' \
| sed 's/[^"]*$//g' \
| grep "...*" \
| sort \
| uniq >> x
IFS=$'\n'
for i in $(cat x)
do
	f="data$(echo $i | sed 's/[^a-zA-Z0-9]/_/g').n3"
	if [ -f "$f" ]; then
		echo "$f exist"
	else
		(
			LC_ALL=C
			echo "start with $i"
			parallel -a /src/luposdate3000/btc-2019-triples.nt --pipepart grep -F "'$i'"  > $f
			echo "done with $i"
		)
	fi
done
wait

for f in $(find . -name "*.sparql" | sort)
do
	rm $f.n2
	for x in $(cat $f | tr " " "\n" | sort | uniq | grep -v "^?" | sort)
	do
		i="data$(echo $x | sed 's/[^a-zA-Z0-9]/_/g').n3"
		echo $f $i
		cat $i >> $f.n2
	done
	sort -u $f.n2 > $f.n3
	rm $f.n2
done

rm x
