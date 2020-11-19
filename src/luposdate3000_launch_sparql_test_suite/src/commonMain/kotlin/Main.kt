import lupos.s00misc.Parallel
import lupos.s16network.LuposdateEndpoint
import lupos.SparqlTestSuite

fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    SparqlTestSuite.filterList.addAll(args)
    SparqlTestSuite().testMain()
}
