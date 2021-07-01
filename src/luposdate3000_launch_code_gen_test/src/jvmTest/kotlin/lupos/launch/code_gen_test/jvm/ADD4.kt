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
    @Test
    fun `ADD 4}`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[0],inputGraph[0])
        } else {
            TODO()
        }
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[1])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[1],inputGraph[1])
        } else {
            TODO()
        }
        val expected0 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
        val query0 = Query(instance)
        val graph0 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
        val operator0 = graph0.getIterator(query0, arrayOf(AOPVariable(query0, "s"), AOPVariable(query0, "p"), AOPVariable(query0, "o")), EIndexPatternExt.SPO)
        val actual0 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator0, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err0 = MyPrintWriter()
        if (!expected0.equalsVerbose(actual0, true, true, buf_err0)) {
            fail(expected0.toString()+" .. "+actual0.toString()+" .. "+buf_err0.toString()+" .. "+operator0)
        }
        val expected1 = MemoryTable.parseFromAny(inputData[1], inputType[1], Query(instance))!!
        val query1 = Query(instance)
        val graph1 = instance.tripleStoreManager!!.getGraph(inputGraph[1])
        val operator1 = graph1.getIterator(query1, arrayOf(AOPVariable(query1, "s"), AOPVariable(query1, "p"), AOPVariable(query1, "o")), EIndexPatternExt.SPO)
        val actual1 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator1, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err1 = MyPrintWriter()
        if (!expected1.equalsVerbose(actual1, true, true, buf_err1)) {
            fail(expected1.toString()+" .. "+actual1.toString()+" .. "+buf_err1.toString()+" .. "+operator1)
        }
        val operator2 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val expected3 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val query3 = Query(instance)
        val graph3 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator3 = graph3.getIterator(query3, arrayOf(AOPVariable(query3, "s"), AOPVariable(query3, "p"), AOPVariable(query3, "o")), EIndexPatternExt.SPO)
        val actual3 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator3, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err3 = MyPrintWriter()
        if (!expected3.equalsVerbose(actual3, true, true, buf_err3)) {
            fail(expected3.toString()+" .. "+actual3.toString()+" .. "+buf_err3.toString()+" .. "+operator3)
        }
        val expected4 = MemoryTable.parseFromAny(outputData[1], outputType[1], Query(instance))!!
        val query4 = Query(instance)
        val graph4 = instance.tripleStoreManager!!.getGraph(outputGraph[1])
        val operator4 = graph4.getIterator(query4, arrayOf(AOPVariable(query4, "s"), AOPVariable(query4, "p"), AOPVariable(query4, "o")), EIndexPatternExt.SPO)
        val actual4 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator4, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err4 = MyPrintWriter()
        if (!expected4.equalsVerbose(actual4, true, true, buf_err4)) {
            fail(expected4.toString()+" .. "+actual4.toString()+" .. "+buf_err4.toString()+" .. "+operator4)
        }
        val expected5 = MemoryTable.parseFromAny(outputData[2], outputType[2], Query(instance))!!
        val query5 = Query(instance)
        val graph5 = instance.tripleStoreManager!!.getGraph(outputGraph[2])
        val operator5 = graph5.getIterator(query5, arrayOf(AOPVariable(query5, "s"), AOPVariable(query5, "p"), AOPVariable(query5, "o")), EIndexPatternExt.SPO)
        val actual5 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator5, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err5 = MyPrintWriter()
        if (!expected5.equalsVerbose(actual5, true, true, buf_err5)) {
            fail(expected5.toString()+" .. "+actual5.toString()+" .. "+buf_err5.toString()+" .. "+operator5)
        }
        LuposdateEndpoint.close(instance)
    }
/*
val pkg0 = MySimulatorTestingImportPackage(inputData[0],inputGraph[0],inputType[0])
val pkg1 = MySimulatorTestingImportPackage(inputData[1],inputGraph[1],inputType[1])
pkg1.onFinish = pkg2
*/

}
