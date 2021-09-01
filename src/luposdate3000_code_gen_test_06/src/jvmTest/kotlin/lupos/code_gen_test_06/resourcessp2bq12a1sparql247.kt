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
package lupos.code_gen_test_06
import lupos.endpoint.LuposdateEndpoint
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.result_format.EQueryResultToStreamExt
import lupos.shared.EIndexPatternExt
import lupos.shared.EPartitionModeExt
import lupos.shared.EPredefinedPartitionSchemesExt
import lupos.shared.Luposdate3000Instance
import lupos.shared.MemoryTable
import lupos.shared.inline.File
import lupos.shared.inline.MyPrintWriter
import kotlin.test.Test
import kotlin.test.fail

public class resourcessp2bq12a1sparql247 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcessp2bq12a1sparql247.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcessp2bq12a1sparql247.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX foaf:  <http://xmlns.com/foaf/0.1/> \n" +
        "PREFIX bench: <http://localhost/vocabulary/bench/> \n" +
        "PREFIX dc:    <http://purl.org/dc/elements/1.1/> \n" +
        "SELECT ?article ?person1 ?person2 ?name1 ?inproc { \n" +
        "  ?article rdf:type bench:Article . \n" +
        "  ?article dc:creator ?person1 . \n" +
        "  ?inproc  rdf:type bench:Inproceedings . \n" +
        "  ?inproc  dc:creator ?person2 . \n" +
        "  ?person1 foaf:name ?name1 . \n" +
        "  ?person2 foaf:name ?name2 . \n" +
        "  FILTER (?name1=?name2) \n" +
        "} \n" +
        ""

    @Test(timeout = 2000)
    public fun `resourcessp2bq12a1sparql247 - None - PartitionByID_1_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query6 = Query(instance)
            val graph6 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator6 = graph6.getIterator(query6, arrayOf(AOPVariable(query6, "s"), AOPVariable(query6, "p"), AOPVariable(query6, "o")), EIndexPatternExt.SPO)
            val actual6 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator6, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected6 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err6 = MyPrintWriter()
            if (!expected6.equalsVerbose(actual6, true, true, buf_err6)) {
                fail(expected6.toString() + " .. " + actual6.toString() + " .. " + buf_err6.toString() + " .. " + operator6)
            }
            val operator7 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            val actual7 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator7, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected7 = MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!
            val buf_err7 = MyPrintWriter()
            if (!expected7.equalsVerbose(actual7, true, true, buf_err7)) {
                fail(expected7.toString() + " .. " + actual7.toString() + " .. " + buf_err7.toString() + " .. " + operator7)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `resourcessp2bq12a1sparql247 - None - PartitionByID_O_AllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query12 = Query(instance)
            val graph12 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator12 = graph12.getIterator(query12, arrayOf(AOPVariable(query12, "s"), AOPVariable(query12, "p"), AOPVariable(query12, "o")), EIndexPatternExt.SPO)
            val actual12 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator12, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected12 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err12 = MyPrintWriter()
            if (!expected12.equalsVerbose(actual12, true, true, buf_err12)) {
                fail(expected12.toString() + " .. " + actual12.toString() + " .. " + buf_err12.toString() + " .. " + operator12)
            }
            val operator13 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            val actual13 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator13, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected13 = MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!
            val buf_err13 = MyPrintWriter()
            if (!expected13.equalsVerbose(actual13, true, true, buf_err13)) {
                fail(expected13.toString() + " .. " + actual13.toString() + " .. " + buf_err13.toString() + " .. " + operator13)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `resourcessp2bq12a1sparql247 - None - PartitionByKeyAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query20 = Query(instance)
            val graph20 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator20 = graph20.getIterator(query20, arrayOf(AOPVariable(query20, "s"), AOPVariable(query20, "p"), AOPVariable(query20, "o")), EIndexPatternExt.SPO)
            val actual20 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator20, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected20 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err20 = MyPrintWriter()
            if (!expected20.equalsVerbose(actual20, true, true, buf_err20)) {
                fail(expected20.toString() + " .. " + actual20.toString() + " .. " + buf_err20.toString() + " .. " + operator20)
            }
            val operator21 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            val actual21 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator21, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected21 = MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!
            val buf_err21 = MyPrintWriter()
            if (!expected21.equalsVerbose(actual21, true, true, buf_err21)) {
                fail(expected21.toString() + " .. " + actual21.toString() + " .. " + buf_err21.toString() + " .. " + operator21)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
}
