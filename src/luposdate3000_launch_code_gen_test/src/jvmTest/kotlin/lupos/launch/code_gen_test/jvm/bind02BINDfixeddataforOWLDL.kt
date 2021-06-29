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
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.fail

public class bind02BINDfixeddataforOWLDL {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/bind02BINDfixeddataforOWLDL.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".ttl",
    )
    internal val targetData = File("src/jvmTest/resources/bind02BINDfixeddataforOWLDL.output").readAsString()
    internal val targetType = ".srx"
    internal val query = File("src/jvmTest/resources/bind02BINDfixeddataforOWLDL.query").readAsString()

    @Ignore // Reason: >Bug<
    @Test
    fun `bind02  BIND fixed data for OWL DL}`() {
        val instance = LuposdateEndpoint.initialize()
        instance.LUPOS_BUFFER_SIZE = 128
        if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
            LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
        } else {
            TODO()
        }
        val op = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
        val buf = MyPrintWriter(true)
        val target = MemoryTable.parseFromAny(targetData, targetType, op.getQuery())!!
        val result = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, op, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
        val buf_err = MyPrintWriter()
        if (!target.equalsVerbose(result, true, true, buf_err)) {
            fail(buf_err.toString())
        }
        LuposdateEndpoint.close(instance)
    }
}
