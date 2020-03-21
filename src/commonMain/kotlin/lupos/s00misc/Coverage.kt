package lupos.s00misc

object Coverage {
val counters=Array(CoverageMapGenerated.keys.size){0}
    fun funStart(counter: Int) {
counters[counter]++
        println("funStart $counter")
    }

    fun forLoopStart(counter: Int) {
counters[counter]++
        println("forLoopStart $counter")
    }

    fun forEachLoopStart(counter: Int) {
counters[counter]++
        println("forEachLoopStart $counter")
    }

    fun whileLoopStart(counter: Int) {
counters[counter]++
        println("whileLoopStart $counter")
    }

    fun ifStart(counter: Int) {
counters[counter]++
        println("ifStart $counter")
    }

fun print(){
for(k in 0 until counters.size)
println("${counters[k]},${CoverageMapGenerated[k]}")
}
}
