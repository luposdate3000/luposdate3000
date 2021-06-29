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
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class INSERT02 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/INSERT02.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/INSERT02.output0").readAsString(),
        File("src/jvmTest/resources/INSERT02.output1").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
        "http://example.org/g1",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val query = File("src/jvmTest/resources/INSERT02.query").readAsString()

    @Ignore // Reason: >Bug<
    @Test
    fun `INSERT 02}`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
        } else {
            TODO()
        }

        val query_a_0 = Query(instance)
        val input_a_0 = MemoryTable.parseFromAny(inputData[0], inputType[0], query_a_0)!!
        val graph_a_0 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val op2_a_0 = graph_a_0.getIterator(query_a_0, arrayOf(AOPVariable(query_a_0, "s"), AOPVariable(query_a_0, "p"), AOPVariable(query_a_0, "o")), EIndexPatternExt.SPO)
        val result_a_0 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op2_a_0, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err_a_0 = MyPrintWriter()
        if (!input_a_0.equalsVerbose(result_a_0, true, true, buf_err_a_0)) {
            fail(input_a_0.toString() + " .. " + result_a_0.toString() + " .. " + buf_err_a_0.toString() + " .. " + op2_a_0)
        }
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op, buf, EQueryResultToStreamExt.EMPTY_STREAM)

        val query_0 = Query(instance)
        val output0 = MemoryTable.parseFromAny(outputData[0], outputType[0], query_0)!!
        val graph0 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val op20 = graph0.getIterator(query_0, arrayOf(AOPVariable(query_0, "s"), AOPVariable(query_0, "p"), AOPVariable(query_0, "o")), EIndexPatternExt.SPO)
        val result0 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op20, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err0 = MyPrintWriter()
        if (!output0.equalsVerbose(result0, true, true, buf_err0)) {
            fail(output0.toString() + " .. " + result0.toString() + " .. " + buf_err0.toString() + " .. " + op20 + " .. " + op)
        }

        val query_1 = Query(instance)
        val output1 = MemoryTable.parseFromAny(outputData[1], outputType[1], query_1)!!
        val graph1 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val op21 = graph1.getIterator(query_1, arrayOf(AOPVariable(query_1, "s"), AOPVariable(query_1, "p"), AOPVariable(query_1, "o")), EIndexPatternExt.SPO)
        val result1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op21, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err1 = MyPrintWriter()
        if (!output1.equalsVerbose(result1, true, true, buf_err1)) {
            fail(output1.toString() + " .. " + result1.toString() + " .. " + buf_err1.toString() + " .. " + op21 + " .. " + op)
        }
        LuposdateEndpoint.close(instance)
    }
}
