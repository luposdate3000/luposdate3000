package examplePackage

import lupos.endpoint.LuposdateEndpoint
import lupos.modulename.MyPrintWriter
import lupos.s00misc.CodeGenerationAnnotation
import lupos.s00misc.DateHelperRelative
import kotlin.jvm.JvmField

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
public class BenchmarkClass {

    @JvmField
    @CodeGenerationAnnotation
    public val exampleVar: String = "SELECT ?pages ?article ?title WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://purl.org/dc/elements/1.1/title> ?title}"
//    public val exampleVar: String = "SELECT ?pages ?article (?pages > 100 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages}"
//    public val exampleVar: String = "SELECT ?pages ?article WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . FILTER(?pages > 50)}"

    internal fun startTimer(): Pair<Double, Int> {
        var time: Double = 0.0
        var counter: Int = 0
        var buf = MyPrintWriter(true)
        var op = exampleVar_evaluate()
        LuposdateEndpoint.evaluateOperatorgraphToResult(op, buf)
// lupos.s00misc.File("gen.res").withOutputStream{it.println(buf.toString())}
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
// lupos.s00misc.File("org.res").withOutputStream{it.println(buf.toString())}
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
