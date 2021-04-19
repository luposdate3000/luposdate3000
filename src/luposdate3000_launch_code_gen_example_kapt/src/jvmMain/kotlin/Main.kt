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
import examplePackage.BenchmarkClass
import lupos.endpoint.LuposdateEndpoint

public fun main(args: Array<String>) {
    /*//var flag = false
    mainFunc()
    val example = ExampleAnnotation()
    println(example.exampleVar_evaluate())*/

// LuposdateEndpoint.importTurtleFiles("resources/code-generation/example.n3", mutableMapOf())
    LuposdateEndpoint.importIntermediateFiles("/mnt/luposdate-testdata/sp2b/1048576/complete.n3")
    println("Init finished")
    // For counting of results set true
    if (false) {
        val exampleVar: String = "SELECT (count(?pages) as ?count) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://purl.org/dc/elements/1.1/title> ?title}"
        println(LuposdateEndpoint.evaluateSparqlToResultB(exampleVar))
    } else {
        for (i in 1..3) {
            val benchmark = BenchmarkClass()
            var (time, counter) = benchmark.startTimer()
            println("Elapsed time generated $time ms for $counter iterations")
            var (time2, counter2) = benchmark.startTimerEndpoint()
            println("Elapsed time non-generated $time2 ms for $counter2 iterations")
        }
    }
}
