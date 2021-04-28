#!/usr/bin/env kotlin
/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
val showonlyremoved = true
var line = readLine()
var prefix = ""
var mapping = mutableMapOf<String, Int>()
val modulenames = listOf(
    "Luposdate3000_Triple_Store_All",
    "Luposdate3000_Result_Format",
    "Luposdate3000_Optimizer_Ast",
    "Luposdate3000_Optimizer_Logical",
    "Luposdate3000_Optimizer_Physical",
    "Luposdate3000_Triple_Store_Manager",
    "Luposdate3000_Result_Format",
    "Luposdate3000_Triple_Store_All",
    "Luposdate3000_Result_Format",
    "Luposdate3000_Triple_Store_Id_Triple",
    "Luposdate3000_Test",
    "Luposdate3000_Shared",
    "Luposdate3000_Parser",
    "Luposdate3000_Operators",
    "Luposdate3000_Launch_Endpoint",
    "Luposdate3000_Jena_Wrapper_On",
    "Luposdate3000_Endpoint_Java_Sockets",
    "Luposdate3000_Endpoint",
    "Luposdate3000_Dictionary",
    "Luposdate3000_Buffer_Manager_Inmemory",
)

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
