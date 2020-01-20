cd /src/luposdate3000
for f in $(ls | grep common)
do
(
	cd $f
	gradle build -x test
)
done
(
	cd heap_jvm
	gradle build -x test
)
pkill java
clientCount=10
for i in $(seq $clientCount)
do
	sleep 1
	java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/p2p/MainKt $((8080 + $i)) 127.0.0.1 127.0.0.1:8081 &
done
sleep 1
for i in $(seq $clientCount)
do
	curl 127.0.0.1:$((8080 + $i))/peers/list
done
