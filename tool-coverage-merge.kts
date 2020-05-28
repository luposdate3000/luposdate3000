#!/bin/kscript
import java.io.File

enum class OutputFormat {
    LUPOSDATE,
    SONAR_QUBE,
    LCOV /*https://github.com/s-oravec/quilt/wiki/LCOV-Trace-File*/
}

val outputformat = OutputFormat.LCOV

var res = mutableMapOf<String, MutableMap<Long, Long>>()

for (arg in args) {
    File(arg).bufferedReader().readLines().forEach {
        val arr = it.split(",")
        if (arr.size == 2) {
            var filenameAndLine = arr[1]
            var count = arr[0].toLong()
            var arr2 = filenameAndLine.split(":")
            var filename = arr2[0]
            var linenumber = arr2[1].toLong()
            var tmp = res[filename]
            if (tmp == null) {
                tmp = mutableMapOf<Long, Long>()
                res[filename] = tmp
            }
            var tmp2 = tmp!!
            var tmp3 = tmp2[linenumber]
            if (tmp3 == null) {
                tmp2[linenumber] = count
            } else {
                tmp2[linenumber] = tmp3 + count
            }
        }
    }
}
fun cleanAndMapFile(originalName: String, coverageIn: Map<Long, Long>, coverageOut: MutableMap<Long, Long>, coverageFunctions: MutableMap<Long, String>): String {
    var targetName = "strippedSourceCode/$originalName"
    var idx = targetName.lastIndexOf("/")
    var targetDirectory = targetName.substring(0, idx)
    var lineNumbers = LongArray(coverageIn.size)
    var countersNumbers = LongArray(coverageIn.size)
    var i = 0
    for ((k, v) in coverageIn) {
        lineNumbers[i] = k - i
        countersNumbers[i] = v
        coverageOut[lineNumbers[i]] = countersNumbers[i]
        i++
    }
    File(targetDirectory).mkdirs()
    i = 0
    var j = 0
    var k = 0
    var lastLine = ""
    File(targetName).printWriter().use { out ->
        File(originalName).forEachLine { line ->
            if (j < lineNumbers.size && i.toLong() == lineNumbers[j]) {
                if (line.contains("Coverage.funStart")) {
                    var name = lastLine
                    if (name.contains(" init ")) {
                        name = "init"
                    } else {
                        var idx = name.indexOf("(")
                        name = name.substring(0, idx)
                        if (name.startsWith("fun ")) {
                            name = name.substring(4, name.length)
                        } else {
                            idx = name.lastIndexOf(" fun ")
                            name = name.substring(idx + 5, name.length)
                        }
                    }
                    coverageFunctions[lineNumbers[j]] = name
                }
                j++
            } else {
                out.println(line)
                k++
                i++
            }
            lastLine = line
        }
    }
    return targetName
}

when (outputformat) {
    OutputFormat.SONAR_QUBE -> {
        println("<coverage version=\"1\">")
    }
}
res.forEach { filename2, tmp2 ->
    val tmp = mutableMapOf<Long, Long>()
    val coverageFunctions = mutableMapOf<Long, String>()
    val filename = cleanAndMapFile(filename2, tmp2, tmp, coverageFunctions)
    println("DEBUG-counters $tmp")
    println("DEBUG-functions $coverageFunctions")
    var nonZeroExecutionCount = 0
    var executedFunctions = 0
    when (outputformat) {
        OutputFormat.SONAR_QUBE -> {
            println("  <file path=\"$filename\">")
        }
        OutputFormat.LCOV -> {
            println("TN:")
            val path = System.getProperty("user.dir")
            println("SF:$path/$filename")
        }
    }
    coverageFunctions.forEach { linenumber, functionname ->
        when (outputformat) {
            OutputFormat.LCOV -> {
                println("FN:$linenumber,$functionname")
            }
        }
    }
    coverageFunctions.forEach { linenumber, functionname ->
        val count = tmp[linenumber]!!
        if (count > 0) {
            executedFunctions++
        }
        when (outputformat) {
            OutputFormat.LCOV -> {
                println("FNDA:$count,$functionname")
            }
        }
    }
    when (outputformat) {
        OutputFormat.LCOV -> {
            println("FNF:${coverageFunctions.size}")
            println("FNH:$executedFunctions")
        }
    }
    tmp.forEach { linenumber, count ->
        if (count > 0) {
            nonZeroExecutionCount++
        }
        when (outputformat) {
            OutputFormat.LUPOSDATE -> {
                println("$count,$filename:$linenumber")
            }
            OutputFormat.SONAR_QUBE -> {
                val covered = count > 0
                println("    <lineToCover lineNumber=\"$linenumber\" covered=\"$covered\"/>")
            }
            OutputFormat.LCOV -> {
                println("DA:$linenumber,$count")
            }
        }
    }
    when (outputformat) {
        OutputFormat.SONAR_QUBE -> {
            println("  </file>")
        }
        OutputFormat.LCOV -> {
            println("LH:$nonZeroExecutionCount")
            println("LF:${tmp.size}")
            println("end_of_record")
        }
    }
}
when (outputformat) {
    OutputFormat.SONAR_QUBE -> {
        println("</coverage>")
    }
}

