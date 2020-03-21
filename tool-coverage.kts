import java.io.File

val regexCoverage = "Coverage.[a-zA-Z]+\\s*\\(\\s*[0-9]+\\s*\\)".toRegex()
val regexSpace = "\\s+".toRegex()
val regexFunBracket = ".*s*fun\\s+.*\\{\\s*".toRegex()
val regexWhileLoopBracket = ".*s*while.*\\{\\s*".toRegex()
val regexForEachLoopBracket = ".*s*forEach.*\\{\\s*".toRegex()
val regexForLoopBracket = ".*s*for.*\\{\\s*".toRegex()
val regexIfBracket = ".*s*(if|else).*\\{\\s*".toRegex()
val regexWhileLoop = ".*s*while.*".toRegex()
val regexForLoop = ".*s*for.*".toRegex()
val regexIf = ".*s*(if|else).*".toRegex()
val coverageImport = "import lupos.s00misc.Coverage"
val coverageMap = mutableMapOf<Int, String>()
var counter = 0

enum class CoverageMode {
    Enable, Disable
}

fun appendCoverageForLoop(filename: String, counter: Int, linenumber: Int) {
    coverageMap[counter] = "$filename:$linenumber"
}

fun appendCoverageForEachLoop(filename: String, counter: Int, linenumber: Int) {
    coverageMap[counter] = "$filename:$linenumber"
}

fun appendCoverageWhileLoop(filename: String, counter: Int, linenumber: Int) {
    coverageMap[counter] = "$filename:$linenumber"
}

fun appendCoverageIf(filename: String, counter: Int, linenumber: Int) {
    coverageMap[counter] = "$filename:$linenumber"
}

fun appendCoverageFun(filename: String, counter: Int, linenumber: Int) {
    coverageMap[counter] = "$filename:$linenumber"
}

var coveragemode = CoverageMode.Enable
for (arg in args) {
    if (!arg.endsWith("Coverage.kt")) {
        try {
            coveragemode = CoverageMode.valueOf(arg)
        } catch (e: Throwable) {
            val fileSource = File(arg)
            val fileTarget = File(arg + ".tmp")
            fileTarget.printWriter().use { out ->
                when (coveragemode) {
                    CoverageMode.Enable -> addCoverage(arg, fileSource.bufferedReader().readLines()).forEach {
                        out.println(it)
                    }
                    CoverageMode.Disable -> removeCoverage(fileSource.bufferedReader().readLines()).forEach {
                        out.println(it)
                    }
                }
            }
            fileSource.delete()
            fileTarget.copyTo(fileSource)
            fileTarget.delete()
        }
    }
}
File("src/commonMain/kotlin/lupos/s00misc/CoverageMapGenerated.kt").printWriter().use { out ->
    out.println("package lupos.s00misc")
    out.println("val CoverageMapGenerated=mapOf(")
    coverageMap.forEach { k, v ->
        out.println("$k to \"$v\",")
    }
    out.println("$counter to \"\"")
    out.println(")")
}

fun addCoverage(filename: String, lines: List<String>): List<String> {
    val res = mutableListOf<String>()
    var appendClosingBracket = 0
    var hadPackage = false
    var hadImport = false
    var hadCoverageImport = false
    var linenumber = 0
    lines.forEach {
        val line = it.replace(regexCoverage, "")
        if (line.startsWith("package "))
            hadPackage = true
        else if (line.startsWith("import ")) {
            hadImport = true
            if (line == coverageImport)
                hadCoverageImport = true
        } else if (!regexSpace.matches(line) && line.length > 0) {
            if (!hadCoverageImport)
                res.add(coverageImport)
            hadCoverageImport = true
        }
        when {
            regexFunBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename $linenumber" })
                res.add(line)
                appendCoverageFun(filename, counter, linenumber)
                res.add("Coverage.funStart(${counter++})")
            }
            regexWhileLoopBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename $linenumber" })
                res.add(line)
                appendCoverageWhileLoop(filename, counter, linenumber)
                res.add("Coverage.whileLoopStart(${counter++})")
            }
            regexForEachLoopBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename $linenumber" })
                res.add(line)
                appendCoverageForEachLoop(filename, counter, linenumber)
                res.add("Coverage.forEachLoopStart(${counter++})")
            }
            regexForLoopBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename $linenumber" })
                res.add(line)
                appendCoverageForLoop(filename, counter, linenumber)
                res.add("Coverage.forLoopStart(${counter++})")
            }
            regexIfBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename $linenumber" })
                res.add(line)
                appendCoverageIf(filename, counter, linenumber)
                res.add("Coverage.ifStart(${counter++})")
            }
            regexWhileLoop.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename $linenumber" })
                res.add(line + "{")
                appendCoverageWhileLoop(filename, counter, linenumber)
                res.add("Coverage.whileLoopStart(${counter++})")
                appendClosingBracket = 2
            }
            regexForLoop.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename $linenumber" })
                res.add(line + "{")
                appendCoverageForLoop(filename, counter, linenumber)
                res.add("Coverage.forLoopStart(${counter++})")
                appendClosingBracket = 2
            }
            regexIf.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename $linenumber" })
                res.add(line + "{")
                appendCoverageIf(filename, counter, linenumber)
                res.add("Coverage.ifStart(${counter++})")
                appendClosingBracket = 2
            }
            regexSpace.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename $linenumber" })
            }
            else -> {
                res.add(line)
            }
        }
        if (appendClosingBracket == 2)
            appendClosingBracket = 1
        else if (appendClosingBracket == 1) {
            res.add("}")
            appendClosingBracket = 0
        }
        linenumber++
    }
    return res
}

fun removeCoverage(lines: List<String>): List<String> {
    val res = mutableListOf<String>()
    lines.forEach {
        val line = it.replace(regexCoverage, "")
        res.add(line)
    }
    return res
}
