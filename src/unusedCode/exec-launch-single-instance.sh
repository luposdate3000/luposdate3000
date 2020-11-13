./generate-buildfile.main.kts --file=src/generateBuildfile/template-exec-launch-single-instance
./tool-gradle-build.sh
ret=$?
if [ $ret -ne 0 ]
then
        exit $ret
fi
./build/executable $(hostname)
#wget localhost:80/import/turtle?query=%2Fmnt%2Fluposdate-testdata%2Fbtc2019%2Fbtc2019-triples.nt
#wget localhost:80/import/turtle?query=$(find /mnt/luposdate-testdata/btc2019/data/*.n3 | sort | paste -s -d ';' | sed "s-/-%2F-g")
#$(find $triplesfolder/*.n3 | paste -s -d ';')

exit

find /mnt/luposdate-testdata/btc2019/data/*.n3 | sort | paste -s -d ';' > tmpparams
curl -H "Content-Type: application/x-www-form-urlencoded" -d @tmpparams localhost:80/import/turtle
rm tmpparams

exit

curl -H "Content-Type: application/x-www-form-urlencoded" localhost:80/import/intermediate?query=/mnt/luposdate-testdata/btc2019/data/intermediate
curl -H "Content-Type: application/x-www-form-urlencoded" localhost:80/persistence/store
curl -H "Content-Type: application/x-www-form-urlencoded" -d "@resources/myqueries/execP.sparql" localhost:80/sparql/query
