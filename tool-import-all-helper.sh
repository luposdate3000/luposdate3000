#!/bin/bash
echo "starting $1"
ports=(8080 8081 8082 8083 8084 8085 8086 8087)
intermediates=( btc2010/btc-2010.n4 yago3/yago3.ttl yago1/yago-1.0.0-turtle.ttl yago2/yago-2.n3 yago4/yago-all.nt yago2s/yago-2.5.3-turtle-simple.ttl barton/barton.nt btc2019/btc2019-triples.nt )
storages=( btc2010 yago3 yago1 yago2 yago4 yago2s barton btc2019 )
i=$1
port=${ports[i]}
intermediate=/mnt/luposdate-testdata/${intermediates[i]}
storage=/mnt/db/${storages[i]}/
rm -rf $storage
mkdir -p $storage
(
export LUPOS_RAM=14
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
