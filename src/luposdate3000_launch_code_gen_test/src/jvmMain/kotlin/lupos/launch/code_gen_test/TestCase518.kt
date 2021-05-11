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

public object TestCase518 {
    internal const val inputFileName=".//resources/sp2b/sp2b-247.n3"
    internal const val targetFileName=".//resources/sp2b/q6-3-247.srx"
    @CodeGenerationAnnotation
    internal const val query = "PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
        "PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#> " +
        "PREFIX foaf:    <http://xmlns.com/foaf/0.1/> " +
        "PREFIX dc:      <http://purl.org/dc/elements/1.1/> " +
        "PREFIX dcterms: <http://purl.org/dc/terms/> " +
        "SELECT * " +
        "WHERE { " +
        "  ?class rdfs:subClassOf foaf:Document . " +
        "  ?document rdf:type ?class . " +
        "  ?document dcterms:issued ?yr . " +
        "  ?document dc:creator ?author . " +
        "  ?author foaf:name ?name . " +
        "    ?class2 rdfs:subClassOf foaf:Document . " +
        "    ?document2 rdf:type ?class2 . " +
        "    ?document2 dcterms:issued ?yr2 . " +
        "    ?document2 dc:creator ?author2 . " +
        "    FILTER ( ?author = ?author2 ) " +
        "} " +
        ""
    internal operator fun invoke(){
        println("Test: './/resources/sp2b/q6-3.sparql-247'")
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
            println("Result: './/resources/sp2b/q6-3.sparql-247' success")
        } else {
            println("Result: './/resources/sp2b/q6-3.sparql-247' failed")
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
                println("ResultCodegen: './/resources/sp2b/q6-3.sparql-247' failed")
            }
        }
    }
}
