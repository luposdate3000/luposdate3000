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

public class ADD4 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/ADD4.input").readAsString(),
        File("src/jvmTest/resources/ADD4.input1").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
        "http://example.org/g1",
    )
    internal val inputType = arrayOf(
        ".ttl",
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/ADD4.output0").readAsString(),
        File("src/jvmTest/resources/ADD4.output1").readAsString(),
        File("src/jvmTest/resources/ADD4.output2").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
        "http://example.org/g1",
        "http://example.org/g2",
    )
    internal val outputType = arrayOf(
        ".ttl",
        ".ttl",
        ".ttl",
    )
    internal val query = File("src/jvmTest/resources/ADD4.query").readAsString()

    @Ignore // Reason: >Bug<
    @Test
    fun `ADD 4}`() {
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
        val output2 = MemoryTable.parseFromAny(outputData[2], outputType[2], op.getQuery())!!
        val graph2 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val op22 = graph2.getIterator(op.getQuery(), arrayOf(AOPVariable(op.getQuery(), "s"), AOPVariable(op.getQuery(), "p"), AOPVariable(op.getQuery(), "o")), EIndexPatternExt.SPO)
        val result2 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op22, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err2 = MyPrintWriter()
        if (!output2.equalsVerbose(result2, true, true, buf_err2)) {
            fail(buf_err2.toString())
        }
        LuposdateEndpoint.close(instance)
    }
}
