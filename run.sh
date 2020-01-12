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
java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/TestKt
#java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/s2buildOperatorGraph/OperatorGraphVisitorTestKt
#java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/s7physicalOptimisation/TestKt
#java -cp ./heap_jvm/build/libs/heap_jvm.jar lupos/s8outputResult/TestKt
