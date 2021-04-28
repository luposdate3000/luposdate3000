#!/bin/bash
echo "starting $1"
intermediates=( /mnt/luposdate-testdata/yago1/yago-1.0.0-turtle.ttl /mnt/luposdate-testdata/yago2/yago-2.n3 /mnt/luposdate-testdata/yago3/yago3.ttl /mnt/luposdate-testdata/barton/barton.nt /mnt/luposdate-testdata/yago2s/yago-2.5.3-turtle-simple.ttl /mnt/luposdate-testdata/btc2019/btc2019-triples.nt /mnt/luposdate-testdata/yago4/yago-all.nt /mnt/luposdate-testdata/btc2010/btc-2010.n4 )
i=$1
port=80
intermediate=${intermediates[i]}
storage=$(dirname $(echo ${intermediates[i]} | sed "s#/mnt/luposdate-testdata/#/mnt/db/#g"))/
rm -rf $storage
mkdir -p $storage
(
export LUPOS_HOME=$storage
./launcher.main.kts \
 --run \
 --inlineMode=Enable \
 --releaseMode=Enable \
 --processUrls=localhost:$port \
 --Endpoint_Launcher=Java_Sockets \
 --Buffer_Manager=Persistent_Cached \
 --garbageCollector=Shenandoah \
 --partitionMode=None \
 &
pid=$!
while ! nc -z localhost $port
do
sleep 0.1
done
time curl localhost:$port/import/intermediate?file=$intermediate
curl localhost:$port/shutdown
) > $storage/importing-logs.log 2>&1
