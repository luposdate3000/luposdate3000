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

import java.io.File
var permutations = mutableListOf<IntArray>()
for (i in 0 until 3) {
    for (j in 1 until 3) {
        val s = i
        val p = (s + j) % 3
        var o = p
        if (j == 1) {
            o = (s + 2) % 3
        } else {
            o = (s + 1) % 3
        }
        permutations.add(intArrayOf(s, p, o))
    }
}
var labelsY = arrayOf("?s", "?p", "<iri0>")
var labelsZ = arrayOf("?s", "?o", "<iri0>")
var idx = 0
for (i in 0 until permutations.size) {
    for (j in i until permutations.size) {
        val out = File("join$idx.sparql").printWriter()
        out.println("SELECT ?s ?p ?o {")
        out.println("  ${labelsY[permutations[i][0]]} ${labelsY[permutations[i][1]]} ${labelsY[permutations[i][2]]} .")
        out.println("  ${labelsZ[permutations[j][0]]} ${labelsZ[permutations[j][1]]} ${labelsZ[permutations[j][2]]} .")
        out.println("}")
        out.close()
        idx++
    }
}
val inputWriter = File("joinData.n3").printWriter()
for (i in 0 until 3) {
    for (j in 0 until 3) {
        for (k in 0 until 3) {
            inputWriter.println("<iri$i> <iri$j> <iri$k> .")
        }
    }
}
inputWriter.close()
val generateFile = File("generate.sh").printWriter()
for (i in 0 until idx) {
    println("java -Xmx60g -cp $(printf %s: $(pwd)/build-cache/bin-effective/*.jar) MainKt --generate resources/myqueries/join/joinData.n3 resources/myqueries/join/join$i.sparql resources/myqueries/join/joinData.n3 resources/binary/join$i join$i SELECT_QUERY_RESULT")
    println("echo join$i=enabled >> resources/binary/config")
}
generateFile.close()
