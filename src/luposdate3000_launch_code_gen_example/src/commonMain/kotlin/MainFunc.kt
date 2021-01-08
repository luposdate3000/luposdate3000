import lupos.s00misc.EIndexPattern
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s03resultRepresentation.ValueIri
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal
import lupos.s16network.LuposdateEndpoint
@Suppress("NOTHING_TO_INLINE") internal inline fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    LuposdateEndpoint.importTurtleFiles("/mnt/luposdate-testdata/sp2b/1024/complete.n3", mutableMapOf<String, Int>())
    val preparedStatement = LuposdateEndpoint.evaluateSparqlToResultB("INSERT Data {<A> <a> <C>}")
// importieren der daten
    println("abc")
    val partition = Partition()	// fürs erste ohne partitionierung - einfach ohne parameter überall übergeben
    val query = Query() // überall in einem query die gleiche instanz übergeben
    // SELECT ?j ?a WHERE {?j <a> ?a}
    val x = TripleStoreIteratorGlobal(
        query,
        listOf("j", "a"), // Variablen fuer den output
        "", // Leerer String = Standardgraph
        arrayOf(
            AOPVariable(query, "j"), // Triple-Pattern mit Var j,
            AOPConstant(query, ValueIri("a")), // Konstante
            AOPVariable(query, "a") // Variable Objekt
        ),
        EIndexPattern.P_SO, // Indexsortierung nach PSO, Variablen mit Namen _ kommen zum Schluss
        partition // Partition
    )
    val c: IteratorBundle = x.evaluate(Partition())
    val zeileniterator: RowIterator = c.rows
    println(zeileniterator.columns.map { it })
    val dictionary = query.getDictionary() // Dictionary für aktuelle Query
    var i = zeileniterator.next() // Rueckgabe: Index im Buffer des Zeileniterators
    while (i != -1) {
        val spalte1Wert = dictionary.getValue(zeileniterator.buf[i]) // Rückgabe: ValueDefinition (typisierter Wert)
        val spalte2Wert = dictionary.getValue(zeileniterator.buf[i + 1])
        println(zeileniterator.buf[i])
        println(zeileniterator.buf[i + 1])
        println(spalte1Wert.valueToString())
        println(spalte2Wert.valueToString())
        i = zeileniterator.next()
    }
}
