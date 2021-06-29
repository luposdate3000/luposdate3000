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
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class DROPGRAPH {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/DROPGRAPH.input").readAsString(),
        File("src/jvmTest/resources/DROPGRAPH.input1").readAsString(),
        File("src/jvmTest/resources/DROPGRAPH.input2").readAsString(),
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
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/DROPGRAPH.output0").readAsString(),
        File("src/jvmTest/resources/DROPGRAPH.output1").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
        "http://example.org/g2",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val query = File("src/jvmTest/resources/DROPGRAPH.query").readAsString()

    @Ignore // Reason: >Bug<
    @Test
    fun `DROP GRAPH}`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
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
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        val buf = MyPrintWriter(true)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val output0 = MemoryTable.parseFromAny(outputData[0], outputType[0], op.getQuery())!!
        val graph0 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val op20 = graph0.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), "s"), AOPVariable(op.getQuery(), "p"), AOPVariable(op.getQuery(), "o")), EIndexPatternExt.SPO)
        val result0 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op20, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err0 = MyPrintWriter()
        if (!output0.equalsVerbose(result0, true, true, buf_err0)) {
            fail(buf_err0.toString())
        }
        val output1 = MemoryTable.parseFromAny(outputData[1], outputType[1], op.getQuery())!!
        val graph1 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val op21 = graph1.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), "s"), AOPVariable(op.getQuery(), "p"), AOPVariable(op.getQuery(), "o")), EIndexPatternExt.SPO)
        val result1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op21, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err1 = MyPrintWriter()
        if (!output1.equalsVerbose(result1, true, true, buf_err1)) {
            fail(buf_err1.toString())
        }
        LuposdateEndpoint.close(instance)
    }
}
