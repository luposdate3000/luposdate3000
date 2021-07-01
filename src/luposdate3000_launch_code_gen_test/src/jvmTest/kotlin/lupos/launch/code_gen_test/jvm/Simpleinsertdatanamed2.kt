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

public class Simpleinsertdatanamed2 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/Simpleinsertdatanamed2.input0").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "http://example.org/g1",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/Simpleinsertdatanamed2.output0").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "http://example.org/g1",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = File("src/jvmTest/resources/Simpleinsertdatanamed2.query").readAsString()
    @Ignore // Reason: >Bug<
    @Test
    fun `Simple insert data named 2}`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[0],inputGraph[0])
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
        val operator1 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op, buf, EQueryResultToStreamExt.EMPTY_STREAM)
        val expected2 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
        val query2 = Query(instance)
        val graph2 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
        val operator2 = graph2.getIterator(query2, arrayOf(AOPVariable(query2, "s"), AOPVariable(query2, "p"), AOPVariable(query2, "o")), EIndexPatternExt.SPO)
        val actual2 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator2, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err2 = MyPrintWriter()
        if (!expected2.equalsVerbose(actual2, true, true, buf_err2)) {
            fail(expected2.toString()+" .. "+actual2.toString()+" .. "+buf_err2.toString()+" .. "+operator2)
        }
        LuposdateEndpoint.close(instance)
    }
/*
val pkg0 = MySimulatorTestingImportPackage(inputData[0],inputGraph[0],inputType[0])
*/

}
