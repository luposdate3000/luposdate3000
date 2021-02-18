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
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.Parallel
import lupos.s00misc.Partition
import lupos.s03resultRepresentation.ValueIri
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s05tripleStore.POPTripleStoreIterator
import lupos.s16network.LuposdateEndpoint

internal fun mainFunc(args: Array<String>): Unit = Parallel.runBlocking {
    LuposdateEndpoint.initialize()
    LuposdateEndpoint.importTurtleFiles("/mnt/luposdate-testdata/sp2b/1024/complete.n3", mutableMapOf<String, Int>())
    val preparedStatement = LuposdateEndpoint.evaluateSparqlToResultB("INSERT Data {<A> <a> <C>}")
// importieren der daten
    println("abc")
    val partition = Partition() // fürs erste ohne partitionierung - einfach ohne parameter überall übergeben
    val query = Query() // überall in einem query die gleiche instanz übergeben
    // SELECT ?j ?a WHERE {?j <a> ?a}
    val x = POPTripleStoreIterator(
        query,
        listOf("j", "a"), // Variablen fuer den output
        "", // Leerer String = Standardgraph
        arrayOf(
            AOPVariable(query, "j"), // Triple-Pattern mit Var j,
            AOPConstant(query, ValueIri("a")), // Konstante
            AOPVariable(query, "a") // Variable Objekt
        ),
        EIndexPatternExt.P_SO, // Indexsortierung nach PSO, Variablen mit Namen _ kommen zum Schluss
        partition // Partition
    )
    val c: IteratorBundle = x.evaluateRoot()
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
