#!/usr/bin/env kotlin
@file:Import("src/luposdate3000_shared/commonMain/kotlin/lupos/s00misc/OperatorGraphToLatex.kt")
val inputString = StringBuilder()
while (true) {
    val line = readLine()
    if (line == null) {
        break
    }
    inputString.append(line.trim())
}
println(OperatorGraphToLatex(inputString.toString()))
