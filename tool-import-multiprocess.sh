#!/bin/bash
port=8080
#intermediate=/mnt/luposdate-testdata/btc2019/btc2019-triples.nt
storage_base=/mnt/db/btc2019
intermediate=/mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.ttl
storage_base=/mnt/db/yago1

for i in 1 2 4 8 16
#for i in 2
do
storage=${storage_base}/$i-instances/
(
rm -rf $storage
mkdir -p $storage
export LUPOS_HOME=$storage
urls=""
for j in $(seq 0 $((i-1)))
do
urls="${urls},localhost:$((port+j+i-1))"
done
urls="${urls:1}"
myport=$((port+i-1))
echo "opening $i $urls to access $myport"
./launcher.main.kts --run --inlineMode=Enable --releaseMode=Enable --garbageCollector=Shenandoah --compilerVersion=1.5.255-SNAPSHOT --Buffer_Manager=Persistent_Cached --Endpoint_Launcher=Java_Sockets --processUrls=$urls > $storage/server.log 2>&1 &
while ! nc -z localhost $myport
do
sleep 0.1
done
echo "$myport is open"
curl localhost:$myport/import/intermediate?file=$intermediate > $storage/import.log 2>&1
for j in $(seq 0 $((i-1)))
do
curl "localhost:$((port+j+i-1))/shutdown" $storage/shutdown.log 2>&1
done
)&
done

wait
