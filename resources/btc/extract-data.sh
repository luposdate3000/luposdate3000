cat *.sparql \
| sed "s/#.*//g" \
| sed 's/"[^"]*"//g' \
| tr " " "\n" \
| grep "...*" \
| grep -v "^?" \
| grep -v "distinct" \
| grep -v "where" \
| grep -v "select" \
| sort \
| uniq > x
cat *.sparql \
| sed "s/#.*//g" \
| sed 's/^[^"]*//' \
| sed 's/[^"]*$//g' \
| grep "...*" \
| sort \
| uniq >> x
IFS=$'\n'
for i in $(cat x)
do
	(
		LC_ALL=C
		echo "start with $i"
		parallel -a /src/luposdate3000/btc-2019-triples.nt --pipepart grep -F "'$i'"  > "data$(echo $i | sed 's/[^a-zA-Z0-9]/_/g').n3"
		echo "done with $i"
	)
done
wait

for f in $(find resources/btc -name "*.sparql" | sort)
do
	rm $f.n3
	for x in $(cat $f | tr " " "\n" | sort | uniq | grep -v "^?" | sort)
	do
		i="data$(echo $x | sed 's/[^a-zA-Z0-9]/_/g').n3"
		cat $i >> $f.n3
	done
done

exit
