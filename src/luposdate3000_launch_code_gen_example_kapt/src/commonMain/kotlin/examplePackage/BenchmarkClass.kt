package examplePackage

import kotlin.jvm.JvmField
import lupos.endpoint.LuposdateEndpoint
import lupos.s00misc.CodeGenerationAnnotation
import lupos.s00misc.DateHelperRelative

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
public class BenchmarkClass {

    init {
        LuposdateEndpoint.importTurtleFiles(
            "resources/code-generation/example.n3",
            mutableMapOf()
        )
    }

    @JvmField
    @CodeGenerationAnnotation
    public val exampleVar: String = "SELECT ?article ?pages (?pages <= 20 as ?x) WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . FILTER(?pages <= 50)}"

    internal fun startTimer() : Double {
        var time: Double = 0.0
        for (count in 1..1000){
            val timer = DateHelperRelative.markNow()
            exampleVar_evaluate()
            time += DateHelperRelative.elapsedSeconds(timer)
    }
        return time/1000
    }

    internal fun startTimerEndpoint() : Double {
        var time: Double = 0.0
        for (count in 1..1000){
            val timer = DateHelperRelative.markNow()
            LuposdateEndpoint.evaluateSparqlToResultB(exampleVar)
            time += DateHelperRelative.elapsedSeconds(timer)
        }
        return time/1000
    }



}
