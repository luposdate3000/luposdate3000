#!/bin/kscript
import java.io.*

var folderName = args[0]
var line = readLine()
var chunk = 0
var outsize = 0
var out: PrintWriter? = null
var prefixes = mutableListOf<String>()
var currentTriple = Array<String>(3) { "" }
var lastTriple = Array<String>(3) { "" }
var nextType = 0 /* 0:S,1:P,2:O*/
var finishTriple = false
while (line != null) {
    if (line.startsWith("@prefix")) {
        prefixes.add(line)
    } else {
        out = nextChunk()
        var column = 0
        var start = column
        while (column < line.length) {
            while (column < line.length && line.charAt(column) == ' ') {
                column++
            }
            start = column
            var qouteCount = 0
            while (column < line.length) {
                when (line.charAt(column)) {
                    '"' -> qouteCount++
                    '<' -> {
                        if (qouteCount % 2 == 0) {
                            qouteCount++
                        }
                    }
                    '>' -> {
                        if (qouteCount % 2 == 1) {
                            qouteCount--
                        }
                    }
                    ' ' -> {
                        if (qouteCount % 2 == 0) {
                            break
                        }
                    }
                    '.', ',', ';' -> {
                        if (qouteCount % 2 == 0 && nextType == 2) {
                            break
                        }
                    }
                }
                column++
            }
            currentTriple[nextType] = line.substring(start, column)
            if (nextType == 2) {
                writeTriple()
                while (column < line.length && line.charAt(column) == ' ') {
                    column++
                }
                when (line.charAt(column)) {
                    "." -> {
                        nextType = 0
                    }
                    ";" -> {
                        nextType = 1
                    }
                    "," -> {
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
    line = readLine()
}
fun writeTriple() {
    finishTriple = true
    if (lastTriple[0] == "") {
        write(currentTriple[0] + " " + currentTriple[1] + " " + currentTriple[2] + " ")
    } else {
        if (currentTriple[0] == lastTriple[0]) {
            if (currentTriple[1] == lastTriple[1]) {
                write(",\n" + currentTriple[2] + " ")
            } else {
                write(";\n" + currentTriple[1] + " " + currentTriple[2] + " ")
            }
        } else {
            write(".\n" + currentTriple[0] + " " + currentTriple[1] + " " + currentTriple[2] + " ")
        }
    }
    for (i in 0 until 3) {
        lastTriple[i] = currentTriple[i]
    }
}

fun write(str: String) {
    out.print(str)
    outsize += str.length
}

fun nextChunk() {
    if (outsize > 1000000000 || out == null) {
        if (out != null) {
            if (finishTriple) {
                out.println(".")
            }
            out.close()
        }
        out = File(folderName + "/data" + chunk++ + ".n3").printWriter()
        for (p in prefixes) {
            write(p + "\n")
        }
    }
}
