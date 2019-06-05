import lupos.datastructures.b_plus_tree.*
import lupos.io.buffer.BufferManager
import lupos.io.buffer.OnePageBenchmark
import kotlin.system.measureNanoTime

fun main(args:Array<String>){
/*	for(i in 1..10) {
		lateinit var o: OnePageBenchmark
		val timeForSetup = measureNanoTime { o = OnePageBenchmark() }
		println("Time for setup: " + timeForSetup)
		o.run(::measureNanoTime)
	}*/
	testBPlusTree7d()
}