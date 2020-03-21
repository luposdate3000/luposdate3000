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
var counter = 0

enum class CoverageMode {
    Enable, Disable
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
                    CoverageMode.Enable -> addCoverage(fileSource.bufferedReader().readLines()).forEach {
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

fun addCoverage(lines: List<String>): List<String> {
    val res = mutableListOf<String>()
    var appendClosingBracket = 0
    var hadPackage = false
    var hadImport = false
    var hadCoverageImport = false
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
                res.add(line)
                res.add("Coverage.funStart(${counter++})")
            }
            regexWhileLoopBracket.matches(line) -> {
                res.add(line)
                res.add("Coverage.whileLoopStart(${counter++})")
            }
            regexForEachLoopBracket.matches(line) -> {
                res.add(line)
                res.add("Coverage.forEachLoopStart(${counter++})")
            }
            regexForLoopBracket.matches(line) -> {
                res.add(line)
                res.add("Coverage.forLoopStart(${counter++})")
            }
            regexIfBracket.matches(line) -> {
                res.add(line)
                res.add("Coverage.ifStart(${counter++})")
            }
            regexWhileLoop.matches(line) -> {
                res.add(line + "{")
                res.add("Coverage.whileLoopStart(${counter++})")
                appendClosingBracket = 2
            }
            regexForLoop.matches(line) -> {
                res.add(line + "{")
                res.add("Coverage.forLoopStart(${counter++})")
                appendClosingBracket = 2
            }
            regexIf.matches(line) -> {
                res.add(line + "{")
                res.add("Coverage.ifStart(${counter++})")
                appendClosingBracket = 2
            }
            regexSpace.matches(line) -> {
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
        res.add(line)
    }
    return res
}
