(
	cd common
	gradle build -x test
)
(
	cd heap_jvm
	gradle build -x test
)
java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/sparql1_1/test/TestKt
