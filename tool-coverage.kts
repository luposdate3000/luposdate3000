#!/bin/kscript
import java.io.File

val separator = "[^a-zA-Z]"

val regexCoverage = "Coverage\\.[a-zA-Z]+\\s*\\(\\s*[0-9]+\\s*\\)".toRegex()
val regexSpace = "\\s*".toRegex()
val regexFunBracket = ".*\\s*fun\\s+.*\\{\\s*".toRegex()
val regexWhileLoopBracket = ".*\\s*${separator}while${separator}.*\\{\\s*".toRegex()
val regexForEachLoopBracket = ".*\\s*${separator}forEach${separator}.*\\{\\s*".toRegex()
val regexForLoopBracket = ".*\\s*${separator}for${separator}.*\\{\\s*".toRegex()
val regexIfBracket = ".*\\s*${separator}(if|else)${separator}.*\\{\\s*".toRegex()
val regexWhenBracket = ".*\\s*${separator}when${separator}.*\\{\\s*".toRegex()
val regexWhileLoop = ".*\\s*${separator}while${separator}.*".toRegex()
val regexForLoop = ".*\\s*${separator}for${separator}.*".toRegex()
val regexIf = ".*\\s*${separator}(if|else)${separator}.*".toRegex()
val regexWhenCaseBracket = ".*\\s*->\\s*\\{.*".toRegex()
val regexWhenCase = ".*\\s*->.*".toRegex()
val regexReturn = "\\s*${separator}(return|break|continue|throw)${separator}.*".toRegex()
val regexUnreachable = ".*Coverage Unreachable.*".toRegex()
val coverageImport = "import lupos.s00misc.Coverage"

//output->
val coverageMap = mutableMapOf<Int, String>()
val whenCaseMap = mutableMapOf<Int, Int>()
//output<-

var counter = 0
val whenBrackets = mutableMapOf<Int, Int>()

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

fun appendCoverageWhenCase(filename: String, counter: Int, linenumber: Int) {
    coverageMap[counter] = "$filename:$linenumber"
}

fun appendCoverageWhen(filename: String, counter: Int, linenumber: Int) {
    coverageMap[counter] = "$filename:$linenumber"
}

fun appendCoverageStatement(filename: String, counter: Int, linenumber: Int) {
    coverageMap[counter] = "$filename:$linenumber"
}

var coveragemode = CoverageMode.Enable
for (arg in args) {
    if (!arg.endsWith("Coverage.kt") &&
            !arg.endsWith("SPARQLParser.kt") &&
            !arg.endsWith("SPARQLScanner.kt") &&
            !arg.endsWith("Benchmark.kt") &&
            !arg.endsWith("Runtime.kt") &&
            !arg.endsWith("TurtleParser.kt") &&
            !arg.endsWith("TurtleParserWithDictionary.kt") &&
            !arg.endsWith("TurtleParserWithStringTriples.kt") &&
            !arg.endsWith("TurtleScanner.kt") &&
            !arg.endsWith("BinaryTestHelper.kt") &&
            !arg.endsWith("BufferManager.kt") &&
            !arg.endsWith("BPlusTreeTests.kt") &&
            !arg.endsWith("B_Plus_Tree.kt") &&
            !arg.endsWith("B_Plus_Tree_Int_to_Int.kt") &&
            !arg.endsWith("LSM_Tree.kt") &&
            !arg.endsWith("Radix_Tree.kt") &&
            !arg.endsWith("MappedByteBufferPage.kt") &&
            !arg.endsWith("CachedFile.kt") &&
            !arg.endsWith("UnsafePage.kt") &&
            !arg.endsWith("PageHelper.kt") &&
            !arg.endsWith("Model.kt") &&
            !arg.endsWith("Cache.kt") &&
            !arg.endsWith("CacheOfFiles.kt")
    ) {
        try {
            coveragemode = CoverageMode.valueOf(arg)
        } catch (e: Throwable) {
            val fileSource = File(arg)
            val fileTarget = File(arg + ".tmp")
            fileTarget.printWriter().use { out ->
                when (coveragemode) {
                    CoverageMode.Enable -> addCoverage(arg, removeCoverage(fileSource.bufferedReader().readLines())).forEach {
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
    out.println("val CoverageMapGenerated=mapOf<Int,String>(")
    var tmp = StringBuilder()
    coverageMap.forEach { k, v ->
        tmp.append("$k to \"$v\",\n")
    }
    var tmps = tmp.toString()
    if (tmps.length > 0)
        out.println(tmps.substring(0, tmps.length - 2))
    out.println(")")
    out.println("val CoverageMapWhenCaseGenerated=mapOf<Int,Int>(")
    tmp = StringBuilder()
    whenCaseMap.forEach { k, v ->
        tmp.append("$k to $v,\n")
    }
    tmps = tmp.toString()
    if (tmps.length > 0)
        out.println(tmps.substring(0, tmps.length - 2))
    out.println(")")

}
fun addCoverage(filename: String, lines: List<String>): List<String> {
    val res = mutableListOf<String>()
    var appendClosingBracket = 0
    var hadPackage = false
    var hadImport = false
    var hadCoverageImport = false
    var openBracketsFunction = Int.MAX_VALUE
    var openBrackets = 0
    var openBracketsUnreachable = Int.MAX_VALUE
    lines.forEach {
        val hadUnreachable = openBracketsUnreachable == openBrackets
        val line = it
        if (!hadUnreachable && res.size > 0 && (!res[res.size - 1].startsWith("Coverage")) && openBrackets >= openBracketsFunction && (!regexReturn.matches(res[res.size - 1])) && (whenBrackets[openBrackets - 1] == null)) {
            appendCoverageStatement(filename, counter, res.size)
            res.add("Coverage.statementStart(${counter++})")
        }
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
        for (i in 0 until line.length) {
            if (line[i] == '{')
                openBrackets++
            if (line[i] == '}') {
                openBrackets--
                if (openBrackets == openBracketsFunction - 1)
                    openBracketsFunction = Int.MAX_VALUE
                whenBrackets.remove(openBrackets)
                if (openBrackets == openBracketsUnreachable - 1) {
                    openBracketsUnreachable = Int.MAX_VALUE
                }
            }
        }
        when {
            hadUnreachable -> {
                res.add(line)
            }
            regexUnreachable.matches(line) -> {
                if (regexCoverage.matches(res[res.size - 1])) {
                    coverageMap.remove(--counter)
                    res.removeAt(res.size - 1)
                }
                openBracketsUnreachable = openBrackets
                res.add(line)
            }
            regexWhenBracket.matches(line) -> {
                res.add(line)
                appendCoverageWhen(filename, counter, res.size)
                whenBrackets[openBrackets - 1] = counter++
            }
            regexFunBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                res.add(line)
                appendCoverageFun(filename, counter, res.size)
                res.add("Coverage.funStart(${counter++})")
                openBracketsFunction = openBrackets
            }
            regexWhileLoopBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                res.add(line)
                appendCoverageWhileLoop(filename, counter, res.size)
                res.add("Coverage.whileLoopStart(${counter++})")
            }
            regexForEachLoopBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                res.add(line)
                appendCoverageForEachLoop(filename, counter, res.size)
                res.add("Coverage.forEachLoopStart(${counter++})")
            }
            regexWhenCaseBracket.matches(line) -> {
                if (whenBrackets[openBrackets - 2] != null) {
                    whenCaseMap[counter] = whenBrackets[openBrackets - 2]!!
                    require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                    res.add(line)
                    appendCoverageWhenCase(filename, counter, res.size)
                    res.add("Coverage.whenCaseStart(${counter++})")
                }
            }
            regexForLoopBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                res.add(line)
                appendCoverageForLoop(filename, counter, res.size)
                res.add("Coverage.forLoopStart(${counter++})")
            }
            regexIfBracket.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                res.add(line)
                appendCoverageIf(filename, counter, res.size)
                res.add("Coverage.ifStart(${counter++})")
            }
            regexWhileLoop.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                res.add(line + "{")
                appendCoverageWhileLoop(filename, counter, res.size)
                res.add("Coverage.whileLoopStart(${counter++})")
                appendClosingBracket = 2
            }
            regexForLoop.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                res.add(line + "{")
                appendCoverageForLoop(filename, counter, res.size)
                res.add("Coverage.forLoopStart(${counter++})")
                appendClosingBracket = 2
            }
            regexIf.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                res.add(line + "{")
                appendCoverageIf(filename, counter, res.size)
                res.add("Coverage.ifStart(${counter++})")
                appendClosingBracket = 2
            }
            regexWhenCase.matches(line) -> {
                if (whenBrackets[openBrackets - 1] != null) {
                    whenCaseMap[counter] = whenBrackets[openBrackets - 1]!!
                    require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
                    res.add(line + "{")
                    appendCoverageWhenCase(filename, counter, res.size)
                    res.add("Coverage.whenCaseStart(${counter++})")
                    appendClosingBracket = 2
                }
            }
            regexSpace.matches(line) -> {
                require(appendClosingBracket == 0, { "$filename ${res.size} >$line<" })
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
    }
    return res
}

fun removeCoverage(lines: List<String>): List<String> {
    val res = mutableListOf<String>()
    lines.forEach {
        val line = it.replace(regexCoverage, "")
        if (!regexSpace.matches(line))
            res.add(line)
    }
    return res
}
