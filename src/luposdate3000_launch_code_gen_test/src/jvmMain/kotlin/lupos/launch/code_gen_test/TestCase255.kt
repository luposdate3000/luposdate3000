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

public object TestCase255 {
    internal const val inputFileName=".//resources/sparql11-test-suite/update-silent/spo.ttl"
    internal const val targetFileName=".//resources/sparql11-test-suite/update-silent/spo.ttl"
    @CodeGenerationAnnotation
    internal const val query = "CLEAR SILENT GRAPH <http://www.example.org> " +
        ""
    internal operator fun invoke(){
        println("Test: 'CLEAR SILENT GRAPH iri'")
        var success = true
        try {
            LuposdateEndpoint.initialize()
            LuposdateEndpoint.importTurtleFiles(inputFileName,mutableMapOf())
            val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(query)
            val buf = MyPrintWriter(true)
            val targetString = File(targetFileName).readAsString()
            val target = MemoryTable.parseFromAny(targetString, targetFileName, op.getQuery())!!
            LuposdateEndpoint.evaluateOperatorgraphToResultA(op, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val graph = tripleStoreManager.getGraph("")
            val op2 = graph.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), "s"), AOPVariable(op.getQuery(), "p"), AOPVariable(op.getQuery(), "o")), EIndexPatternExt.SPO)
            val result = LuposdateEndpoint.evaluateOperatorgraphToResultA(op2, buf, EQueryResultToStreamExt.MEMORY_TABLE)
            if (!target.equalsVerbose(result, true, true)) {
                success = false
            }
        } catch (e:Throwable) {
            e.printStackTrace()
            success = false
        }
            LuposdateEndpoint.close()
        if (success) {
            println("Result: 'CLEAR SILENT GRAPH iri' success")
        } else {
            println("Result: 'CLEAR SILENT GRAPH iri' failed")
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
                LuposdateEndpoint.evaluateOperatorgraphToResultA(op, buf, EQueryResultToStreamExt.EMPTY_STREAM)
                val graph = tripleStoreManager.getGraph("")
                val op2 = graph.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), "s"), AOPVariable(op.getQuery(), "p"), AOPVariable(op.getQuery(), "o")), EIndexPatternExt.SPO)
                val result = LuposdateEndpoint.evaluateOperatorgraphToResultA(op2, buf, EQueryResultToStreamExt.MEMORY_TABLE)
                if (!target.equalsVerbose(result, true, true)) {
                    success2 = false
                }
            } catch (e:Throwable) {
                e.printStackTrace()
                success2 = false
            }
                LuposdateEndpoint.close()
            if (!success2) {
                println("ResultCodegen: 'CLEAR SILENT GRAPH iri' failed")
            }
        }
    }
}
