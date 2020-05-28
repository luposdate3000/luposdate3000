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
when (outputformat) {
    OutputFormat.SONAR_QUBE -> {
        println("<coverage version=\"1\">")
    }
}
res.forEach { filename, tmp ->
    var nonZeroExecutionCount = 0
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

