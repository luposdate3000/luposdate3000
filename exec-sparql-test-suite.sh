cd /src/luposdate3000
(
	cd heap_jvm
	gradle build -x test
)
java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/TestKt
