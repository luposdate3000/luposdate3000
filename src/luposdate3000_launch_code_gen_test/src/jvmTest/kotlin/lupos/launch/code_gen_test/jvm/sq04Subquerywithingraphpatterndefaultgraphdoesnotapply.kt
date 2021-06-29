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

public class sq04Subquerywithingraphpatterndefaultgraphdoesnotapply {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/sq04Subquerywithingraphpatterndefaultgraphdoesnotapply.input").readAsString(),
        File("src/jvmTest/resources/sq04Subquerywithingraphpatterndefaultgraphdoesnotapply.input1").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
        "sq01.rdf",
    )
    internal val inputType = arrayOf(
        ".rdf",
        ".rdf",
    )
    internal val targetData = File("src/jvmTest/resources/sq04Subquerywithingraphpatterndefaultgraphdoesnotapply.output").readAsString()
    internal val targetType = ".srx"
    internal val query = File("src/jvmTest/resources/sq04Subquerywithingraphpatterndefaultgraphdoesnotapply.query").readAsString()

    @Ignore // Reason: >using not implemented feature<
    @Test
    fun `sq04  Subquery within graph pattern default graph does not apply}`() {
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
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        val query_target = Query(instance)
        val target = MemoryTable.parseFromAny(targetData, targetType, query_target)!!
        val result = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err = MyPrintWriter()
        if (!target.equalsVerbose(result, true, true, buf_err)) {
            fail(buf_err.toString())
        }
        LuposdateEndpoint.close(instance)
    }
}
