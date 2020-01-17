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
java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/p2p/P2PKt 8080 127.0.0.1 &
p1=$!
sleep 0.5
java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/p2p/P2PKt 8081 127.0.0.1 127.0.0.1:8080 &
p2=$!
#java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/TestKt

#wget 127.0.0.1:8080

#sleep(100)
#kill $p1
#kill $p2
