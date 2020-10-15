import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

val separator = "([^a-zA-Z_\\{]|$|^)"
val separatorB = "([^a-zA-Z_]|$|^)"
val regexCoverage = "Coverage\\.[a-zA-Z]+\\s*\\(\\s*[0-9]+\\s*\\)".toRegex()
val regexSpace = "\\s*".toRegex()
val regexFunBracket = "(.*\\s*fun\\s+.*\\{\\s*|.*\\s*init\\s+\\{\\s*|.*\\s*constructor.*\\{\\s*)".toRegex()
val regexWhileLoopBracket = ".*\\s*${separator}while${separator}.*\\{\\s*".toRegex()
val regexForEachLoopBracket = ".*\\s*${separator}forEach${separator}.*\\{\\s*".toRegex()
val regexForLoopBracket = ".*\\s*${separator}for${separator}.*\\{\\s*".toRegex()
val regexIfBracket = ".*\\s*${separator}(if|else)${separator}.*\\{\\s*".toRegex()
val regexWhenBracket = ".*\\s*${separator}when${separator}.*\\{\\s*".toRegex()
val regexWhileLoop = ".*\\s*${separatorB}while${separatorB}.*".toRegex()
val regexForLoop = ".*\\s*${separatorB}for${separatorB}.*".toRegex()
val regexIf = ".*\\s*${separatorB}(if|else)${separatorB}.*".toRegex()
val regexWhenCaseBracket = ".*\\s*->\\s*\\{.*".toRegex()
val regexWhenCase = ".*\\s*->.*".toRegex()
val regexReturn = "(\\s*${separatorB}(return|break|continue|throw)${separatorB}.*|.*/\\*return\\*/.*)".toRegex()
val regexUnreachable = ".*(Coverage Unreachable|SanityCheck.checkUnreachable).*".toRegex()
val coverageImport = "import lupos.s00misc.Coverage"
val regexCommentOnly = "^\\s*//.*$".toRegex()

//output->
val coverageMap = mutableMapOf<Int, String>()
val whenCaseMap = mutableMapOf<Int, Int>()

//output<-
var counter = 0
val whenBrackets = mutableMapOf<Int, Int>()

enum class CoverageMode {
    Enable, Disable
}

fun applyCoverageEnable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applyCoverage(tmp, CoverageMode.Enable)
        }
    }
}

fun applyCoverageDisable() {
    Files.walk(Paths.get("src.generated")).forEach { it ->
        val tmp = it.toString()
        if (tmp.endsWith(".kt")) {
            applyCoverage(tmp, CoverageMode.Disable)
        }
    }
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

fun applyCoverage(f: String, coverageMode: CoverageMode) {
    if (!f.endsWith("Coverage.kt") &&
            !f.endsWith("SPARQLParser.kt") &&
            !f.endsWith("SPARQLScanner.kt") &&
            !f.endsWith("Benchmark.kt") &&
            !f.endsWith("Runtime.kt") &&
            !f.endsWith("TurtleParser.kt") &&
            !f.endsWith("TurtleParserWithDictionary.kt") &&
            !f.endsWith("TurtleParserWithStringTriples.kt") &&
            !f.endsWith("TurtleScanner.kt") &&
            !f.endsWith("BinaryTestHelper.kt") &&
            !f.endsWith("BufferManager.kt") &&
            !f.endsWith("BPlusTreeTests.kt") &&
            !f.endsWith("B_Plus_Tree.kt") &&
            !f.endsWith("EndpointServer.kt") &&
            !f.endsWith("B_Plus_Tree_Int_to_Int.kt") &&
            !f.endsWith("LSM_Tree.kt") &&
            !f.endsWith("Radix_Tree.kt") &&
            !f.endsWith("MappedByteBufferPage.kt") &&
            !f.endsWith("CachedFile.kt") &&
            !f.endsWith("UnsafePage.kt") &&
            !f.endsWith("PageHelper.kt") &&
            !f.endsWith("Model.kt") &&
            !f.endsWith("Cache.kt") &&
            !f.endsWith("BenchmarkUtils.kt") &&
            !f.endsWith("CacheOfFiles.kt")
    ) {
        val fileSource = File(f)
        val fileTarget = File(f + ".tmp")
        fileTarget.printWriter().use { out ->
            when (coverageMode) {
                CoverageMode.Enable -> addCoverage(f, removeCoverage(fileSource.bufferedReader().readLines())).forEach {
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
File("resources/CoverageMapGenerated.txt").printWriter().use {
    out ->
    coverageMap.forEach { k, v ->
        out.println("$k:$v")
    }
}
File("resources/CoverageMapWhenCaseGenerated.txt").printWriter().use {
    out ->
    whenCaseMap.forEach { k, v ->
        out.println("$k:$v")
    }
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
    var isInComment = false
    lines.forEach {
        val line = it
        var idxOpen = 0
        var idxClose = 0
        while (idxOpen >= 0 && idxOpen < line.length) {
            var tmp = line.indexOf("/*", idxOpen)
            if (tmp > idxOpen) {
                idxOpen = tmp + 2
            } else {
                break
            }
        }
        while (idxClose >= 0 && idxClose < line.length) {
            var tmp = line.indexOf("*/", idxClose)
            if (tmp > idxClose) {
                idxClose = tmp + 2
            } else {
                break
            }
        }
        var localInsideComment = isInComment
        if (idxOpen > idxClose) {
            isInComment = true
            localInsideComment = true
        } else if (idxClose > idxOpen) {
            isInComment = false
        }
        if (localInsideComment) {
            res.add(line)
        } else if (regexCommentOnly.matches(line)) {
            res.add(line)
        } else {
            val hadUnreachable = openBracketsUnreachable == openBrackets
            if (!hadUnreachable && res.size > 0 && (!res[res.size - 1].startsWith("Coverage")) && openBrackets >= openBracketsFunction && (whenBrackets[openBrackets - 1] == null) && (!res[res.size - 1].endsWith("*/"))) {
                appendCoverageStatement(filename, counter, res.size)
                res.add("Coverage.statementStart(${counter++})")
            }
            if (regexReturn.matches(line)) {
                openBracketsUnreachable = openBrackets
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
            var withinQuotes = false
            for (i in 0 until line.length) {
                if ((line[i] == '"' || line[i] == '\'') && (i == 0 || line[i - 1] != '\\'))
                    withinQuotes = !withinQuotes
                if (line[i] == '{' && !withinQuotes)
                    openBrackets++
                if (line[i] == '}' && !withinQuotes) {
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
                    } else {
                        res.add(line)
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
                    } else {
                        res.add(line)
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
