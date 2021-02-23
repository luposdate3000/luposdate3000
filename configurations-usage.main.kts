#!/usr/bin/env kotlin
val showonlyremoved = true
var line = readLine()
var prefix = ""
var mapping = mutableMapOf<String, Int>()
val modulenames = listOf(
    "Luposdate3000_Triple_Store_All_NoPartitions",
    "Luposdate3000_Result_Format_NoPartitions",
    "Luposdate3000_Optimizer_NoPartitions",
    "Luposdate3000_Triple_Store_Manager",
    "Luposdate3000_Result_Format",
    "Luposdate3000_Optimizer",
    "Luposdate3000_Triple_Store_All_WithProcessPartitions",
    "Luposdate3000_Result_Format_WithProcessPartitions",
    "Luposdate3000_Optimizer_WithProcessPartitions",
    "Luposdate3000_Triple_Store_Id_Triple",
    "Luposdate3000_Test",
    "Luposdate3000_Shared",
    "Luposdate3000_Parser",
    "Luposdate3000_Operators",
    "Luposdate3000_Launch_Endpoint",
    "Luposdate3000_Jena_Wrapper_On",
    "Luposdate3000_Endpoint_Java_Sockets",
    "Luposdate3000_Endpoint",
    "Luposdate3000_Dictionary_Inmemory",
    "Luposdate3000_Buffer_Manager_Inmemory",
)

// $Luposdate3000_Dictionary_Inmemory
fun process(str: String) {
    if (str.endsWith("annotations()") || str.contains('$')) {
        return
    }
    var ismodulename = false
    var s = str
    for (n in modulenames) {
        if (s.startsWith("lupos.$n")) {
            s = s.replace("lupos.$n", "lupos.modulename").replace("$" + n, "modulename")
            ismodulename = true
            break
        }
    }
    val v = mapping[s]
    mapping[s] = if (v == null) {
        if (ismodulename) {
            1
        } else {
            -1
        }
    } else {
        if (ismodulename) {
            v + 1
        } else {
            v - 1
        }
    }
}
while (line != null) {
    if (line!!.startsWith("    ")) {
        process(prefix + line!!)
    } else if (line!!.endsWith(":")) {
        prefix = line!!
    } else {
        process(line!!)
    }
    line = readLine()
}
var allclazz = mutableSetOf<String>()
for (k in mapping.keys.sorted()) {
    val v = mapping[k]!!
    if (showonlyremoved) {
        if (v < 0) {
            if (v + 1 == 0) {
                allclazz.add(k.split(":")[0])
                println(k)
            }
        } else {
            if (v - modulenames.size == 0) {
                allclazz.add(k.split(":")[0])
                println(k)
            }
        }
    } else {
        if (v < 0) {
            println("${v + 1} :: $k")
        } else {
            println("${v - modulenames.size} :: $k")
        }
    }
}
for (k in allclazz) {
    println("xx clazz $k")
}
