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
package lupos.launch.code_gen_test
import lupos.endpoint.LuposdateEndpoint
import lupos.shared_inline.MyPrintWriter
import lupos.shared_inline.File
import lupos.shared.MemoryTable
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.tripleStoreManager
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.shared.EIndexPatternExt
import lupos.operator.base.Query
import lupos.shared.CodeGenerationAnnotation

public object TestCase212 {
    internal const val inputFileName=".//resources/sparql11-test-suite/negation/set-data.ttl"
    internal const val targetFileName=".//resources/sparql11-test-suite/negation/subset-02.srx"
    @CodeGenerationAnnotation
    internal const val query = "PREFIX :    <http://example/> " +
        "PREFIX  rdf:    <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
        "SELECT (?s1 AS ?subset) (?s2 AS ?superset) " +
        "WHERE " +
        "{ " +
        "    # All pairs of sets " +
        "    ?s2 rdf:type :Set . " +
        "    ?s1 rdf:type :Set . " +
        "    MINUS { " +
        "        ?s1 rdf:type :Set . " +
        "        ?s2 rdf:type :Set . " +
        "        # Assumes ?s1 has at least one member  " +
        "        ?s1 :member ?x . " +
        "        # If we want to exclude A as a subset of A. " +
        "        # This is not perfect as \"?s1 = ?s2\" is not a " +
        "        # contents based comparison. " +
        "        FILTER ( ?s1 = ?s2 || NOT EXISTS { ?s2 :member ?x . } ) " +
        "    } " +
        "    MINUS { " +
        "        # If we don't want the empty set being a subset of itself. " +
        "        ?s1 rdf:type :Set . " +
        "        ?s2 rdf:type :Set . " +
        "        # Choose the pair (empty set, empty set) " +
        "        FILTER ( NOT EXISTS { ?s1 :member ?y . } ) " +
        "        FILTER ( NOT EXISTS { ?s2 :member ?y . } ) " +
        "    } " +
        "} " +
        ""
    internal operator fun invoke(){
        println("Test: 'Calculate which sets are subsets of others (exclude A subsetOf A)'")
        var success = true
        try {
            LuposdateEndpoint.initialize()
            LuposdateEndpoint.importTurtleFiles(inputFileName,mutableMapOf())
            val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(query)
            val buf = MyPrintWriter(true)
            val targetString = File(targetFileName).readAsString()
            val target = MemoryTable.parseFromAny(targetString, targetFileName, op.getQuery())!!
            val result = LuposdateEndpoint.evaluateOperatorgraphToResultA(op, buf, EQueryResultToStreamExt.MEMORY_TABLE)
            if (!target.equalsVerbose(result, true, true)) {
                success = false
            }
        } catch (e:Throwable) {
            e.printStackTrace()
            success = false
        }
            LuposdateEndpoint.close()
        if (success) {
            println("Result: 'Calculate which sets are subsets of others (exclude A subsetOf A)' success")
        } else {
            println("Result: 'Calculate which sets are subsets of others (exclude A subsetOf A)' failed")
        }
        if(success){
            var success2 = true
            try {
                LuposdateEndpoint.initialize()
                LuposdateEndpoint.importTurtleFiles(inputFileName,mutableMapOf())
                val op = query_evaluate()
                val buf = MyPrintWriter(true)
                val targetString = File(targetFileName).readAsString()
                val target = MemoryTable.parseFromAny(targetString, targetFileName, op.getQuery())!!
                val result = LuposdateEndpoint.evaluateOperatorgraphToResultA(op, buf, EQueryResultToStreamExt.MEMORY_TABLE)
                if (!target.equalsVerbose(result, true, true)) {
                    success2 = false
                }
            } catch (e:Throwable) {
                e.printStackTrace()
                success2 = false
            }
                LuposdateEndpoint.close()
            if (!success2) {
                println("ResultCodegen: 'Calculate which sets are subsets of others (exclude A subsetOf A)' failed")
            }
        }
    }
}
