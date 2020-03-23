#!/bin/kscript
import java.io.File

var res = mutableMapOf<String, Long>()

for (arg in args) {
    File(arg).bufferedReader().readLines().forEach {
        val arr = it.split(",")
        if (arr.size == 2) {
            if (res[arr[1]] == null) {
                res[arr[1]] = arr[0].toLong()
            } else {
                res[arr[1]] = res[arr[1]]!! + arr[0].toLong()
            }
        }
    }
}
var lastfile = ""
val sonarqubeoutput = true
println("<coverage version=\"1\">")
res.forEach { k, v ->
    val arr = k.split(":")
    require(arr.size == 2)
    val filename = arr[0]
    val linenumber = arr[1]
    val count = v
    val covered = count > 0
    if (sonarqubeoutput) {
        if (lastfile == "")
            println("  <file path=\"$filename\">")
        else if (lastfile != filename) {
            println("  </file>")
            println("  <file path=\"$filename\">")
        }
        println("    <lineToCover lineNumber=\"$linenumber\" covered=\"$covered\"/>")
        lastfile = filename
    } else {
        println("$v,$k")
    }
}
if (sonarqubeoutput) {
    if (lastfile != "")
        println("  </file>")
    println("</coverage>")
}


/*
<coverage version="1">
  <file path="src/main/java/com/example/NonExisting.java"/>
  <file path="src/main/java/com/example/EmptyClass.java"/>
  <file path="src/main/java/com/example/ClassWithoutBranch.java">
    <lineToCover lineNumber="2" covered="false"/>
    <lineToCover lineNumber="3" covered="true"/>
    <lineToCover lineNumber="5" covered="true"/>
    <lineToCover lineNumber="6" covered="false"/>
  </file>
  <file path="src/main/java/com/example/ClassWithBranches.java">
    <lineToCover lineNumber="3" covered="true" branchesToCover="8" coveredBranches="5"/>
    <lineToCover lineNumber="4" covered="true" branchesToCover="2"/>
  </file>
</coverage>
*/
