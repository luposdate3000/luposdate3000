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
import kotlin.test.Test
import kotlin.test.fail

public class DROPALL {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/DROPALL.input").readAsString(),
        File("src/jvmTest/resources/DROPALL.input1").readAsString(),
        File("src/jvmTest/resources/DROPALL.input2").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
        "http://example.org/g1",
        "http://example.org/g2",
    )
    internal val inputType = arrayOf(
        ".ttl",
        ".ttl",
        ".ttl",
    )
    internal val query = File("src/jvmTest/resources/DROPALL.query").readAsString()

    @Test
    fun `DROP ALL}`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
        } else {
            TODO()
        }
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[1])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[1], inputGraph[1])
        } else {
            TODO()
        }
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[2])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[2], inputGraph[2])
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

        val query_a_1 = Query(instance)
        val input_a_1 = MemoryTable.parseFromAny(inputData[1], inputType[1], query_a_1)!!
        val graph_a_1 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val op2_a_1 = graph_a_1.getIterator(query_a_1, arrayOf(AOPVariable(query_a_1, "s"), AOPVariable(query_a_1, "p"), AOPVariable(query_a_1, "o")), EIndexPatternExt.SPO)
        val result_a_1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op2_a_1, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err_a_1 = MyPrintWriter()
        if (!input_a_1.equalsVerbose(result_a_1, true, true, buf_err_a_1)) {
            fail(input_a_1.toString() + " .. " + result_a_1.toString() + " .. " + buf_err_a_1.toString() + " .. " + op2_a_1)
        }

        val query_a_2 = Query(instance)
        val input_a_2 = MemoryTable.parseFromAny(inputData[2], inputType[2], query_a_2)!!
        val graph_a_2 = instance.tripleStoreManager!!.getGraph(inputGraph[2])
        val op2_a_2 = graph_a_2.getIterator(query_a_2, arrayOf(AOPVariable(query_a_2, "s"), AOPVariable(query_a_2, "p"), AOPVariable(query_a_2, "o")), EIndexPatternExt.SPO)
        val result_a_2 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op2_a_2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err_a_2 = MyPrintWriter()
        if (!input_a_2.equalsVerbose(result_a_2, true, true, buf_err_a_2)) {
            fail(input_a_2.toString() + " .. " + result_a_2.toString() + " .. " + buf_err_a_2.toString() + " .. " + op2_a_2)
        }
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.close(instance)
    }
}
