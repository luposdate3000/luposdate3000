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

public class resourcessp2bq67sparql1294 {
    internal val inputData = arrayOf(
        File("src/jvmTest/resources/resourcessp2bq67sparql1294.input").readAsString(),
    )
    internal val inputGraph = arrayOf(
        "",
    )
    internal val inputType = arrayOf(
        ".n3",
    )
    internal val targetData = File("src/jvmTest/resources/resourcessp2bq67sparql1294.output").readAsString()
    internal val targetType = ".srx"
    internal val query = "PREFIX rdf:     <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
        "PREFIX rdfs:    <http://www.w3.org/2000/01/rdf-schema#> \n" +
        "PREFIX foaf:    <http://xmlns.com/foaf/0.1/> \n" +
        "PREFIX dc:      <http://purl.org/dc/elements/1.1/> \n" +
        "PREFIX dcterms: <http://purl.org/dc/terms/> \n" +
        "SELECT * \n" +
        "WHERE { \n" +
        "  ?class rdfs:subClassOf foaf:Document . \n" +
        "  ?document rdf:type ?class . \n" +
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
    public fun `resourcessp2bq67sparql1294 - None - Simple - false`() {
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
