#!/usr/bin/env kotlin
import java.io.File
import java.lang.ProcessBuilder.Redirect


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
var skip = true
for (t in todos2) {
//    if (t.first == 131072 && t.second == 256) {
//        skip = false
//    }
//    if (skip || (t.first == 131072 && t.second == 256)) {
//        continue
//    }
    val p = ProcessBuilder("./execute-benchmark.main.kts", "${t.second}", "${t.first}")
            .directory(File("."))
            .redirectOutput(Redirect.INHERIT)
            .redirectError(Redirect.INHERIT)
            .start()
    p.waitFor()
}


