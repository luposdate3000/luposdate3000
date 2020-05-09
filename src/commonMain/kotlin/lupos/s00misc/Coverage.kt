package lupos.s00misc
import lupos.s00misc.File



object Coverage {
    var verbose = true
    var veryverbose = true
    val CoverageMapGenerated = mutableMapOf<Int, String>()
    val CoverageMapWhenCaseGenerated = mutableMapOf<Int, Int>()

    init {
        var s = File("resources/CoverageMapGenerated.txt").readAsString()
        var lines = s.split("\n")
        for (line in lines) {
            val row = line.split(":")
            if (row.size == 3) {
                CoverageMapGenerated[row[0].toInt()] = row[1] + ":" + row[2]
            }
        }
        s = File("resources/CoverageMapWhenCaseGenerated.txt").readAsString()
        lines = s.split("\n")
        for (line in lines) {
            val row = line.split(":")
            if (row.size == 2)
                CoverageMapWhenCaseGenerated[row[0].toInt()] = row[1].toInt()
        }
        Runtime.getRuntime().addShutdownHook(object : Thread() {
            override fun run() {
                printToFile()
            }
        })
        CoverageMapGenerated[CoverageMapGenerated.keys.size] = ""
    }

    var lastcounter = CoverageMapGenerated.keys.size - 1
    val counters = Array(CoverageMapGenerated.keys.size) { 0L }
    fun funStart(counter: Int) {
        lastcounter = counter
        counters[counter]++
        if (verbose) {
            if (veryverbose)
                println("${CoverageMapGenerated[counter]} funStart")
            else
                println("funStart $counter")
        }
    }

    fun forLoopStart(counter: Int) {
        lastcounter = counter
        counters[counter]++
        if (verbose) {
            if (veryverbose)
                println("${CoverageMapGenerated[counter]} forLoopStart")
            else
                println("forLoopStart $counter")
        }
    }

    fun forEachLoopStart(counter: Int) {
        lastcounter = counter
        counters[counter]++
        if (verbose) {
            if (veryverbose)
                println("${CoverageMapGenerated[counter]} forEachLoopStart")
            else
                println("forEachLoopStart $counter")
        }
    }

    fun whileLoopStart(counter: Int) {
        lastcounter = counter
        counters[counter]++
        if (verbose) {
            if (veryverbose)
                println("${CoverageMapGenerated[counter]} whileLoopStart")
            else
                println("whileLoopStart $counter")
        }
    }

    fun whenCaseStart(counter: Int) {
        lastcounter = counter
        counters[counter]++
        counters[CoverageMapWhenCaseGenerated[counter]!!]++
        if (verbose) {
            if (veryverbose)
                println("${CoverageMapGenerated[counter]} whenCaseStart")
            else
                println("whenCaseStart $counter")
        }
    }

    fun ifStart(counter: Int) {
        lastcounter = counter
        counters[counter]++
        if (verbose) {
            if (veryverbose)
                println("${CoverageMapGenerated[counter]} ifStart")
            else
                println("ifStart $counter")
        }
    }

    fun statementStart(counter: Int) {
        lastcounter = counter
        counters[counter]++
        if (verbose) {
            if (veryverbose)
                println("${CoverageMapGenerated[counter]} statementStart")
            else
                println("statementStart $counter")
        }
    }

    override fun toString(): String {
        val res = StringBuilder()
        for (k in 0 until counters.size) {
            val v = CoverageMapGenerated[k]
            if (v != "")
                res.append("${counters[k]},$v\n")
        }
        return res.toString()
    }

    fun printToFile() {
        val s = toString()
        var h = s.hashCode()
        if (h < 0)
            h = -h
        File("coverage${h}.cov").printWriter { out ->
            out.println(s)
        }
    }
}
