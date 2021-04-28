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
package lupos.launch.code_gen_example_ksp

import lupos.endpoint.LuposdateEndpoint
import lupos.shared.CodeGenerationAnnotationKSP
import lupos.shared.DateHelperRelative
import lupos.shared_inline.MyPrintWriter
import kotlin.jvm.JvmField

public class BenchmarkClass {

    @JvmField
    @CodeGenerationAnnotationKSP("SELECT ?pages ?article ?title WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://purl.org/dc/elements/1.1/title> ?title}")
    public val exampleVar: String = "SELECT ?pages ?article ?title WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://purl.org/dc/elements/1.1/title> ?title}"
//    public val exampleVar: String = "SELECT ?pages ?article (?pages > 100 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages}"
//    public val exampleVar: String = "SELECT ?pages ?article WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . FILTER(?pages > 50)}"

    internal fun startTimer(): Pair<Double, Int> {
        var time: Double = 0.0
        var counter: Int = 0
        var buf = MyPrintWriter(true)
        var op = exampleVar_evaluate()
        LuposdateEndpoint.evaluateOperatorgraphToResult(op, buf)
// lupos.shared.File("gen.res").withOutputStream{it.println(buf.toString())}
        val timer = DateHelperRelative.markNow()
        while (time < 10.0) {
            buf = MyPrintWriter(true)
            op = exampleVar_evaluate()
            LuposdateEndpoint.evaluateOperatorgraphToResult(op, buf)
            time = DateHelperRelative.elapsedSeconds(timer)
            counter++
        }
        return Pair(time / counter, counter)
    }

    internal fun startTimerEndpoint(): Pair<Double, Int> {
        var time: Double = 0.0
        var counter: Int = 0
        var buf = MyPrintWriter(true)
        var op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(exampleVar)
        LuposdateEndpoint.evaluateOperatorgraphToResult(op, buf)
// lupos.shared.File("org.res").withOutputStream{it.println(buf.toString())}
        val timer = DateHelperRelative.markNow()
        while (time < 10.0) {
            buf = MyPrintWriter(true)
            op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(exampleVar)
            LuposdateEndpoint.evaluateOperatorgraphToResult(op, buf)
            time = DateHelperRelative.elapsedSeconds(timer)
            counter++
        }
        return Pair(time / counter, counter)
    }
}
