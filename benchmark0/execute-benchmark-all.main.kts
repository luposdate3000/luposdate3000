#!/usr/bin/env kotlin
import java.lang.ProcessBuilder.Redirect
import java.io.BufferedOutputStream
import java.io.DataOutputStream
import java.io.FileOutputStream
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter


var todos = mutableListOf<Pair<Long, Long>>()
for (result_rows in listOf<Long>(32, 128, 512, 2048, 8196, 32768, 131072, 524288, 2097152)) {
    for (trash in listOf<Long>(0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024)) {
        todos.add(Pair(result_rows, trash))
    }
}
val todos2 = todos.sortedBy {
    (it.first + 1) * (it.second + 1)
}

println(todos2)
for (t in todos2) {
    val p = ProcessBuilder("./execute-benchmark.main.kts", "${t.second}", "${t.first}")
            .directory(File("."))
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
    p.waitFor()
}
