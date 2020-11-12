import lupos.SparqlTestSuite
import lupos.s00misc.Parallel
import lupos.s16network.LuposdateEndpoint

fun main(args: Array<String>) = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    SparqlTestSuite.filterList.addAll(args)
    SparqlTestSuite().testMain()
}
