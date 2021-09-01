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
package lupos.code_gen_test_19
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

public class resourcessp2bq68sparql247 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcessp2bq68sparql247.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcessp2bq68sparql247.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#> \n" +
        "PREFIX foaf:    <http://xmlns.com/foaf/0.1/> \n" +
        "PREFIX dc:      <http://purl.org/dc/elements/1.1/> \n" +
        "PREFIX dcterms: <http://purl.org/dc/terms/> \n" +
        "SELECT * \n" +
        "WHERE { \n" +
        "  ?document rdf:type <http://localhost/vocabulary/bench/Incollection> . \n" +
        "  ?document dcterms:issued ?yr . \n" +
        "  ?document dc:creator ?author . \n" +
        "  ?author foaf:name ?name . \n" +
        "  OPTIONAL { \n" +
        "    ?class2 rdfs:subClassOf foaf:Document . \n" +
        "    ?document2 rdf:type ?class2 . \n" +
        "    ?document2 dcterms:issued ?yr2 . \n" +
        "    ?document2 dc:creator ?author2 . \n" +
        "    FILTER ( ?author = ?author2 && ?yr2 < ?yr ) \n" +
        "  } . \n" +
        "} \n" +
        ""

    @Test(timeout = 2000)
    public fun `resourcessp2bq68sparql247 - None - PartitionByID_S_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query18 = Query(instance)
            val graph18 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator18 = graph18.getIterator(query18, arrayOf(AOPVariable(query18, "s"), AOPVariable(query18, "p"), AOPVariable(query18, "o")), EIndexPatternExt.SPO)
            val actual18 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator18, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected18 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err18 = MyPrintWriter()
            if (!expected18.equalsVerbose(actual18, true, true, buf_err18)) {
                fail(expected18.toString() + " .. " + actual18.toString() + " .. " + buf_err18.toString() + " .. " + operator18)
            }
            val operator19 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            val actual19 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator19, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected19 = MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!
            val buf_err19 = MyPrintWriter()
            if (!expected19.equalsVerbose(actual19, true, true, buf_err19)) {
                fail(expected19.toString() + " .. " + actual19.toString() + " .. " + buf_err19.toString() + " .. " + operator19)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `resourcessp2bq68sparql247 - None - PartitionByKeyAllCollations - true`() {
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

    @Test(timeout = 2000)
    public fun `resourcessp2bq68sparql247 - None - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            if (listOf(".n3", ".ttl", ".nt").contains(inputType[0])) {
                LuposdateEndpoint.importTurtleString(instance, inputData[0], inputGraph[0])
            } else {
                TODO()
            }
            val query26 = Query(instance)
            val graph26 = instance.tripleStoreManager!!.getGraph(inputGraph[0])
            val operator26 = graph26.getIterator(query26, arrayOf(AOPVariable(query26, "s"), AOPVariable(query26, "p"), AOPVariable(query26, "o")), EIndexPatternExt.SPO)
            val actual26 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator26, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected26 = MemoryTable.parseFromAny(inputData[0], inputType[0], Query(instance))!!
            val buf_err26 = MyPrintWriter()
            if (!expected26.equalsVerbose(actual26, true, true, buf_err26)) {
                fail(expected26.toString() + " .. " + actual26.toString() + " .. " + buf_err26.toString() + " .. " + operator26)
            }
            val operator27 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            val actual27 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator27, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected27 = MemoryTable.parseFromAny(targetData, targetType, Query(instance))!!
            val buf_err27 = MyPrintWriter()
            if (!expected27.equalsVerbose(actual27, true, true, buf_err27)) {
                fail(expected27.toString() + " .. " + actual27.toString() + " .. " + buf_err27.toString() + " .. " + operator27)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }
}
