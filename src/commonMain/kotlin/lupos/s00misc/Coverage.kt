package lupos.s00misc

object Coverage {
var verbose=false
val counters=Array(CoverageMapGenerated.keys.size){0}
    fun funStart(counter: Int) {
counters[counter]++
if(verbose)
        println("funStart $counter")
    }

    fun forLoopStart(counter: Int) {
counters[counter]++
if(verbose)
        println("forLoopStart $counter")
    }

    fun forEachLoopStart(counter: Int) {
counters[counter]++
if(verbose)
        println("forEachLoopStart $counter")
    }

    fun whileLoopStart(counter: Int) {
counters[counter]++
if(verbose)
        println("whileLoopStart $counter")
    }

    fun ifStart(counter: Int) {
counters[counter]++
if(verbose)
        println("ifStart $counter")
    }

fun print(){
for(k in 0 until counters.size)
println("${counters[k]},${CoverageMapGenerated[k]}")
}
}
