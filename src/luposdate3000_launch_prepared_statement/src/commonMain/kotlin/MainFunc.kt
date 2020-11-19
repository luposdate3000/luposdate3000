import lupos.s00misc.MyPrintWriter
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s16network.HttpEndpointLauncher
import lupos.s16network.LuposdateEndpoint

fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    val preparedStatement = LuposdateEndpoint.evaluateSparqlToOperatorgraphA("SELECT ?s ?p ?o {?s ?p ?o.}")
    val buf = MyPrintWriter() //You can use any implementation of lupos.s00misc.IMyPrintWriter
    LuposdateEndpoint.evaluateOperatorgraphToResult(preparedStatement, buf)
    println(buf.toString())
}
