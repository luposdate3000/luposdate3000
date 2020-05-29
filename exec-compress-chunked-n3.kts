#!/bin/kscript
import java.io.*

var compress = true
var folderName = args[0]
var line2 = readLine()
var chunk = 0
var outsize = 0
var outbnodes: PrintWriter? = null
var out: PrintWriter? = null
var prefixes = mutableListOf<String>()
var currentTriple = Array<String>(3) { "" }
var lastTriple = Array<String>(3) { "" }
var nextType = 0 /* 0:S,1:P,2:O*/
var finishTriple = false
var countTriples = 0L

while (line2 != null) {
    val line = line2!!
    if (line.startsWith("@prefix")) {
        prefixes.add(line)
    } else {
        nextChunk()
        var column = 0
        var start = column
        while (column < line.length) {
            loop@ while (column < line.length) {
                when (line[column]) {
                    ' ', '\n', '\t', '\r' -> {
                        column++
                    }
                    '#' -> {
                        column = line.length
                    }
                    else -> {
                        break@loop
                    }
                }
            }
            start = column
            var withinQuote = false
            var withinIRIQuote = false
            loop@ while (column < line.length) {
                when (line[column]) {
                    '"' -> {
require(!withinIRIQuote)
                        if (column == 0 || line[column - 1] != '\\') {
                            withinQuote = !withinQuote
                        }
                    }
                    '<' -> {
                        if (!withinQuote) {
withinIRIQuote=true
                            withinQuote = true
                        }
                    }
                    '>' -> {
                        if (withinIRIQuote) {
require(withinQuote)
                            withinQuote = false
withinIRIQuote=false
                        }
                    }
                    ' ' -> {
                        if (!withinQuote) {
                            break@loop
                        }
                    }
                    '.', ',', ';' -> {
                        if (!withinQuote && nextType == 2) {
                            break@loop
                        }
                    }
                }
                column++
            }
            currentTriple[nextType] = line.substring(start, column)
            if (nextType == 2) {
                writeTriple()
                while (column < line.length && line[column] == ' ') {
                    column++
                }
                when (line[column]) {
                    '.' -> {
                        nextType = 0
                    }
                    ';' -> {
                        nextType = 1
                    }
                    ',' -> {
                        nextType = 2
                    }
                    else -> {
                        require(false)
                    }
                }
                column++
            } else {
                nextType++
            }
        }
    }
    line2 = readLine()
}
finishChunk()
println(countTriples)
fun writeTriple() {
    countTriples++
    if (compress) {
        finishTriple = true
        if (lastTriple[0] == "") {
            writeMaybeBnode(currentTriple[0])
            writeMaybeBnode(currentTriple[1])
            writeMaybeBnode(currentTriple[2])
            write(currentTriple[0] + " " + currentTriple[1] + " " + currentTriple[2] + " ")
        } else {
            if (currentTriple[0] == lastTriple[0]) {
                if (currentTriple[1] == lastTriple[1]) {
                    writeMaybeBnode(currentTriple[2])
                    write(",\n" + currentTriple[2] + " ")
                } else {
                    writeMaybeBnode(currentTriple[1])
                    writeMaybeBnode(currentTriple[2])
                    write(";\n" + currentTriple[1] + " " + currentTriple[2] + " ")
                }
            } else {
                writeMaybeBnode(currentTriple[0])
                writeMaybeBnode(currentTriple[1])
                writeMaybeBnode(currentTriple[2])
                write(".\n" + currentTriple[0] + " " + currentTriple[1] + " " + currentTriple[2] + " ")
            }
        }
        for (i in 0 until 3) {
            lastTriple[i] = currentTriple[i]
        }
    } else {
        writeMaybeBnode(currentTriple[0])
        writeMaybeBnode(currentTriple[1])
        writeMaybeBnode(currentTriple[2])
        write(currentTriple[0] + " " + currentTriple[1] + " " + currentTriple[2] + " .\n")
    }
}

fun writeMaybeBnode(s: String) {
    if (s[0] == '_' && s[1] == ':') {
        outbnodes!!.println(s)
    }
}

fun write(str: String) {
    out!!.print(str)
    outsize += str.length
}

fun finishChunk() {
    if (out != null) {
        if (finishTriple) {
            out!!.println(".")
        }
        out!!.close()
        outbnodes!!.close()
    }
    for (i in 0 until 3) {
        lastTriple[i] = ""
    }
    outsize = 0
}

fun nextChunk() {
    if (outsize > 100000000 || out == null) {
        finishChunk()
        out = File(folderName + "/data" + chunk + ".n3").printWriter()
        outbnodes = File(folderName + "/data" + chunk + ".bnodes").printWriter()
        chunk++
        for (p in prefixes) {
            write(p + "\n")
        }
    }
}
