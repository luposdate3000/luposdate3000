package examplePackage

import kotlin.jvm.JvmField
import lupos.endpoint.LuposdateEndpoint
import lupos.s00misc.CodeGenerationAnnotation
import lupos.s00misc.DateHelperRelative

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
public class BenchmarkClass {

    init {
        LuposdateEndpoint.importTurtleFiles(
            "resources/code-generation/example10000.n3",
            mutableMapOf()
        )
        println("Init finished")
    }

    @JvmField
    @CodeGenerationAnnotation
    public val exampleVar: String = "SELECT ?pages ?article ?title  WHERE {?article <http://swrc.ontoware.org/ontology#pages> ?pages . ?article <http://purl.org/dc/elements/1.1/title> ?title}"

    internal fun startTimer() : Pair<Double,Int> {
        var time: Double = 0.0
        var counter: Int = 0
        exampleVar_evaluate()
        val timer = DateHelperRelative.markNow()
        while (time < 10.0){
            exampleVar_evaluate()
            time = DateHelperRelative.elapsedSeconds(timer)
            counter ++
        }
        return Pair(time/counter,counter)
    }

    internal fun startTimerEndpoint() : Pair<Double,Int> {
        var time: Double = 0.0
        var counter: Int = 0
        LuposdateEndpoint.evaluateSparqlToResultB(exampleVar)
        val timer = DateHelperRelative.markNow()
        while (time < 10.0){
            LuposdateEndpoint.evaluateSparqlToResultB(exampleVar)
            time = DateHelperRelative.elapsedSeconds(timer)
            counter ++
        }
        return Pair(time/counter,counter)
    }
}
