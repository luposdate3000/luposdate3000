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

public class Simpleinsertdatanamed1 {
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/Simpleinsertdatanamed1.output0").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "http://example.org/g1",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = File("src/jvmTest/resources/Simpleinsertdatanamed1.query").readAsString()

    @Ignore // Reason: >Bug<
    @Test
    fun `Simple insert data named 1}`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        val buf = MyPrintWriter(false)
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
        LuposdateEndpoint.close(instance)
    }
}
