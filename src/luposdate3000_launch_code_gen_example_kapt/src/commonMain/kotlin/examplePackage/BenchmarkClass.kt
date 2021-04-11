package examplePackage

import kotlin.jvm.JvmField
import lupos.endpoint.LuposdateEndpoint
import lupos.s00misc.CodeGenerationAnnotation
import lupos.s00misc.DateHelperRelative

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
public class BenchmarkClass {

    /*
    @JvmField
    @CodeGenerationAnnotation
    public val exampleVar: String =
        "SELECT ?pages ?article ?title WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://purl.org/dc/elements/1.1/title> ?title}"
    */
    /*@JvmField
    @CodeGenerationAnnotation
    public val exampleVar: String =
        "SELECT ?pages ?article (?pages > 100 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages}"
    */


    @JvmField
    @CodeGenerationAnnotation
    public val exampleVar: String =
        "SELECT ?pages ?article (?pages > 100 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . FILTER(?pages > 50)}"


    /*
    @JvmField
    @CodeGenerationAnnotation
    public val exampleVar: String =
        "SELECT ?pages ?article WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . FILTER(?pages > 50)}"

     */

    internal fun startTimer(): Pair<Double, Int> {
        var time: Double = 0.0
        var counter: Int = 0
        exampleVar_evaluate()
        val timer = DateHelperRelative.markNow()
        while (time < 10.0) {
            exampleVar_evaluate()
            time = DateHelperRelative.elapsedSeconds(timer)
            counter++
        }
        return Pair(time / counter, counter)
    }

    internal fun startTimerEndpoint(): Pair<Double, Int> {
        var time: Double = 0.0
        var counter: Int = 0
        LuposdateEndpoint.evaluateSparqlToResultB(exampleVar)
        val timer = DateHelperRelative.markNow()
        while (time < 10.0) {
            LuposdateEndpoint.evaluateSparqlToResultB(exampleVar)
            time = DateHelperRelative.elapsedSeconds(timer)
            counter++
        }
        return Pair(time / counter, counter)
    }
}
