#!/bin/kscript
import java.io.File

val mergeDirectoryAndFileNames = true

enum class OutputFormat {
    LUPOSDATE,
    SONAR_QUBE,
    LCOV /*https://github.com/s-oravec/quilt/wiki/LCOV-Trace-File*/
}

val outputformat = OutputFormat.LCOV

var res = mutableMapOf<String, MutableMap<Long, Long>>()
var whiteListFolderNames = mutableSetOf("src/commonTemplate/kotlin")
for (argI in 0 until args.size) {
    val arg = args[argI]
    if (argI == 0) {
        whiteListFolderNames.addAll(arg.split("|"))
        whiteListFolderNames.remove("src/commonConfig/kotlin")
        whiteListFolderNames.remove("")
    } else {
        File(arg).bufferedReader().readLines().forEach {
            val arr = it.split(",")
            if (arr.size == 2) {
                var filenameAndLine = arr[1]
                var count = arr[0].toLong()
                var arr2 = filenameAndLine.split(":")
                var filename = arr2[0]
                var flag = false
                if (whiteListFolderNames.size == 1) {
                    flag = true
                } else {
                    for (prefix in whiteListFolderNames) {
                        if (filename.startsWith(prefix)) {
                            flag = true
                            break
                        }
                    }
                }
                if (flag) {
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
    }
}

fun cleanAndMapFile(originalName: String, coverageIn: Map<Long, Long>, coverageOut: MutableMap<Long, Long>, coverageFunctions: MutableMap<Long, String>): String {
    var targetName = replaceFileName("strippedSourceCode/$originalName")
    var idx = targetName.lastIndexOf("/")
    var targetDirectory = targetName.substring(0, idx)
    var lineNumbersIn = LongArray(coverageIn.size)
    var lineNumbersOut = LongArray(coverageIn.size)
    var countersNumbers = LongArray(coverageIn.size)
    var i = 0
    for ((k, v) in coverageIn) {
        lineNumbersIn[i] = k - i
        lineNumbersOut[i] = k - i
        countersNumbers[i] = v
        i++
    }
    File(targetDirectory).mkdirs()
    i = 0
    var j = 0
    var lastLine = ""
    File(targetName).printWriter().use { out ->
        File(originalName).forEachLine { line ->
            if (j < lineNumbersIn.size && i.toLong() == lineNumbersIn[j]) {
                if (line.contains("Coverage.funStart")) {
                    var name = lastLine
                    if (name.contains(" init ")) {
                        name = "init"
                    } else if (name.contains(" constructor")) {
                        name = "constructor"
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
                    coverageFunctions[lineNumbersOut[j]] = name
                } else if (!line.contains("Coverage.")) {
                    out.println(line)
                    for (k in j until lineNumbersOut.size) {
                        lineNumbersOut[k]++
                    }
                }
                j++
            } else {
                out.println(line)
                i++
            }
            lastLine = line
        }
    }
    for (k in 0 until lineNumbersOut.size) {
        coverageOut[lineNumbersOut[k]] = countersNumbers[k]
    }
    return targetName
}

when (outputformat) {
    OutputFormat.SONAR_QUBE -> {
        println("<coverage version=\"1\">")
    }
}
fun replaceFileName(filename: String): String {
    val path = System.getProperty("user.dir")
    if (mergeDirectoryAndFileNames) {
        return (path + "/" + filename).replace("/", "_").replace("_src_luposdate3000_strippedSourceCode_src_", "/src/luposdate3000/strippedSourceCode/src/")
    } else {
        return "$path/$filename"
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
            println("SF:$filename")
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

