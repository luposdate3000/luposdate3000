import lupos.SparqlTestSuite
import lupos.s00misc.Parallel
import lupos.s16network.LuposdateEndpoint
@Suppress("NOTHING_TO_INLINE") internal inline fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    SparqlTestSuite.filterList.addAll(args)
    SparqlTestSuite().testMain()
}
