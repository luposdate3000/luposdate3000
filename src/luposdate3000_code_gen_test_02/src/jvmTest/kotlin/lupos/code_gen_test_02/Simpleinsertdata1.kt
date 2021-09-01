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
package lupos.code_gen_test_02
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
import lupos.simulator_core.Simulation
import lupos.simulator_db.luposdate3000.DatabaseHandle
import lupos.simulator_db.luposdate3000.MySimulatorTestingCompareGraphPackage
import lupos.simulator_db.luposdate3000.MySimulatorTestingExecute
import lupos.simulator_iot.SimulationRun
import kotlin.test.Test
import kotlin.test.fail

public class Simpleinsertdata1 {
    internal val outputData = arrayOf(
        File("src/jvmTest/resources/Simpleinsertdata1.output0").readAsString(),
    )
    internal val outputGraph = arrayOf(
        "",
    )
    internal val outputType = arrayOf(
        ".ttl",
    )
    internal val query = "PREFIX : <http://example.org/ns#> \n" +
        "INSERT DATA { :s :p :o } \n" +
        ""

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByIDTwiceAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByIDTwiceAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator2 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator2, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query3 = Query(instance)
            val graph3 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator3 = graph3.getIterator(query3, arrayOf(AOPVariable(query3, "s"), AOPVariable(query3, "p"), AOPVariable(query3, "o")), EIndexPatternExt.SPO)
            val actual3 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator3, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected3 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err3 = MyPrintWriter()
            if (!expected3.equalsVerbose(actual3, true, true, buf_err3)) {
                fail(expected3.toString() + " .. " + actual3.toString() + " .. " + buf_err3.toString() + " .. " + operator3)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByID_1_AllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator4 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator4, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query5 = Query(instance)
            val graph5 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator5 = graph5.getIterator(query5, arrayOf(AOPVariable(query5, "s"), AOPVariable(query5, "p"), AOPVariable(query5, "o")), EIndexPatternExt.SPO)
            val actual5 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator5, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected5 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err5 = MyPrintWriter()
            if (!expected5.equalsVerbose(actual5, true, true, buf_err5)) {
                fail(expected5.toString() + " .. " + actual5.toString() + " .. " + buf_err5.toString() + " .. " + operator5)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByID_1_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_1_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator6 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator6, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query7 = Query(instance)
            val graph7 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator7 = graph7.getIterator(query7, arrayOf(AOPVariable(query7, "s"), AOPVariable(query7, "p"), AOPVariable(query7, "o")), EIndexPatternExt.SPO)
            val actual7 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator7, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected7 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err7 = MyPrintWriter()
            if (!expected7.equalsVerbose(actual7, true, true, buf_err7)) {
                fail(expected7.toString() + " .. " + actual7.toString() + " .. " + buf_err7.toString() + " .. " + operator7)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByID_2_AllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_2_AllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator8 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator8, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query9 = Query(instance)
            val graph9 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator9 = graph9.getIterator(query9, arrayOf(AOPVariable(query9, "s"), AOPVariable(query9, "p"), AOPVariable(query9, "o")), EIndexPatternExt.SPO)
            val actual9 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator9, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected9 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err9 = MyPrintWriter()
            if (!expected9.equalsVerbose(actual9, true, true, buf_err9)) {
                fail(expected9.toString() + " .. " + actual9.toString() + " .. " + buf_err9.toString() + " .. " + operator9)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByID_2_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_2_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator10 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator10, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query11 = Query(instance)
            val graph11 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator11 = graph11.getIterator(query11, arrayOf(AOPVariable(query11, "s"), AOPVariable(query11, "p"), AOPVariable(query11, "o")), EIndexPatternExt.SPO)
            val actual11 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator11, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected11 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err11 = MyPrintWriter()
            if (!expected11.equalsVerbose(actual11, true, true, buf_err11)) {
                fail(expected11.toString() + " .. " + actual11.toString() + " .. " + buf_err11.toString() + " .. " + operator11)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByID_O_AllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator12 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator12, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query13 = Query(instance)
            val graph13 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator13 = graph13.getIterator(query13, arrayOf(AOPVariable(query13, "s"), AOPVariable(query13, "p"), AOPVariable(query13, "o")), EIndexPatternExt.SPO)
            val actual13 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator13, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected13 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err13 = MyPrintWriter()
            if (!expected13.equalsVerbose(actual13, true, true, buf_err13)) {
                fail(expected13.toString() + " .. " + actual13.toString() + " .. " + buf_err13.toString() + " .. " + operator13)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByID_O_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_O_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator14 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator14, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query15 = Query(instance)
            val graph15 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator15 = graph15.getIterator(query15, arrayOf(AOPVariable(query15, "s"), AOPVariable(query15, "p"), AOPVariable(query15, "o")), EIndexPatternExt.SPO)
            val actual15 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator15, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected15 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err15 = MyPrintWriter()
            if (!expected15.equalsVerbose(actual15, true, true, buf_err15)) {
                fail(expected15.toString() + " .. " + actual15.toString() + " .. " + buf_err15.toString() + " .. " + operator15)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByID_S_AllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator16 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator16, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query17 = Query(instance)
            val graph17 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator17 = graph17.getIterator(query17, arrayOf(AOPVariable(query17, "s"), AOPVariable(query17, "p"), AOPVariable(query17, "o")), EIndexPatternExt.SPO)
            val actual17 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator17, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected17 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err17 = MyPrintWriter()
            if (!expected17.equalsVerbose(actual17, true, true, buf_err17)) {
                fail(expected17.toString() + " .. " + actual17.toString() + " .. " + buf_err17.toString() + " .. " + operator17)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByID_S_AllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByID_S_AllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator18 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator18, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query19 = Query(instance)
            val graph19 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator19 = graph19.getIterator(query19, arrayOf(AOPVariable(query19, "s"), AOPVariable(query19, "p"), AOPVariable(query19, "o")), EIndexPatternExt.SPO)
            val actual19 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator19, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected19 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err19 = MyPrintWriter()
            if (!expected19.equalsVerbose(actual19, true, true, buf_err19)) {
                fail(expected19.toString() + " .. " + actual19.toString() + " .. " + buf_err19.toString() + " .. " + operator19)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByKeyAllCollations - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator20 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator20, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query21 = Query(instance)
            val graph21 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator21 = graph21.getIterator(query21, arrayOf(AOPVariable(query21, "s"), AOPVariable(query21, "p"), AOPVariable(query21, "o")), EIndexPatternExt.SPO)
            val actual21 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator21, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected21 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err21 = MyPrintWriter()
            if (!expected21.equalsVerbose(actual21, true, true, buf_err21)) {
                fail(expected21.toString() + " .. " + actual21.toString() + " .. " + buf_err21.toString() + " .. " + operator21)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - PartitionByKeyAllCollations - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.PartitionByKeyAllCollations
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator22 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator22, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query23 = Query(instance)
            val graph23 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator23 = graph23.getIterator(query23, arrayOf(AOPVariable(query23, "s"), AOPVariable(query23, "p"), AOPVariable(query23, "o")), EIndexPatternExt.SPO)
            val actual23 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator23, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected23 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err23 = MyPrintWriter()
            if (!expected23.equalsVerbose(actual23, true, true, buf_err23)) {
                fail(expected23.toString() + " .. " + actual23.toString() + " .. " + buf_err23.toString() + " .. " + operator23)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator24 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator24, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query25 = Query(instance)
            val graph25 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator25 = graph25.getIterator(query25, arrayOf(AOPVariable(query25, "s"), AOPVariable(query25, "p"), AOPVariable(query25, "o")), EIndexPatternExt.SPO)
            val actual25 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator25, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected25 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err25 = MyPrintWriter()
            if (!expected25.equalsVerbose(actual25, true, true, buf_err25)) {
                fail(expected25.toString() + " .. " + actual25.toString() + " .. " + buf_err25.toString() + " .. " + operator25)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - None - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.None
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator26 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator26, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query27 = Query(instance)
            val graph27 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator27 = graph27.getIterator(query27, arrayOf(AOPVariable(query27, "s"), AOPVariable(query27, "p"), AOPVariable(query27, "o")), EIndexPatternExt.SPO)
            val actual27 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator27, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected27 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err27 = MyPrintWriter()
            if (!expected27.equalsVerbose(actual27, true, true, buf_err27)) {
                fail(expected27.toString() + " .. " + actual27.toString() + " .. " + buf_err27.toString() + " .. " + operator27)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - Thread - Simple - true`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = true
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator52 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator52, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query53 = Query(instance)
            val graph53 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator53 = graph53.getIterator(query53, arrayOf(AOPVariable(query53, "s"), AOPVariable(query53, "p"), AOPVariable(query53, "o")), EIndexPatternExt.SPO)
            val actual53 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator53, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected53 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err53 = MyPrintWriter()
            if (!expected53.equalsVerbose(actual53, true, true, buf_err53)) {
                fail(expected53.toString() + " .. " + actual53.toString() + " .. " + buf_err53.toString() + " .. " + operator53)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - Thread - Simple - false`() {
        var instance = Luposdate3000Instance()
        try {
            instance.LUPOS_BUFFER_SIZE = 128
            instance.LUPOS_PARTITION_MODE = EPartitionModeExt.Thread
            instance.predefinedPartitionScheme = EPredefinedPartitionSchemesExt.Simple
            instance.useDictionaryInlineEncoding = false
            instance = LuposdateEndpoint.initializeB(instance)
            val buf = MyPrintWriter(false)
            val operator54 = LuposdateEndpoint.evaluateSparqlToOperatorgraphA(instance, query)
            LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator54, buf, EQueryResultToStreamExt.EMPTY_STREAM)
            val query55 = Query(instance)
            val graph55 = instance.tripleStoreManager!!.getGraph(outputGraph[0])
            val operator55 = graph55.getIterator(query55, arrayOf(AOPVariable(query55, "s"), AOPVariable(query55, "p"), AOPVariable(query55, "o")), EIndexPatternExt.SPO)
            val actual55 = (LuposdateEndpoint.evaluateOperatorgraphToResultA(instance, operator55, buf, EQueryResultToStreamExt.MEMORY_TABLE) as List<MemoryTable>).first()
            val expected55 = MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!
            val buf_err55 = MyPrintWriter()
            if (!expected55.equalsVerbose(actual55, true, true, buf_err55)) {
                fail(expected55.toString() + " .. " + actual55.toString() + " .. " + buf_err55.toString() + " .. " + operator55)
            }
        } finally {
            LuposdateEndpoint.close(instance)
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }
    public fun simulatorHelper(fileName: String, cfg: MutableMap<String, Any>) {
        val simRun = SimulationRun()
        val config = simRun.parseConfig(fileName, false)
        config.jsonObjects.database.putAll(cfg)
        simRun.sim = Simulation(config.getEntities())
        simRun.sim.maxClock = if (simRun.simMaxClock == simRun.notInitializedClock) simRun.sim.maxClock else simRun.simMaxClock
        simRun.sim.steadyClock = if (simRun.simSteadyClock == simRun.notInitializedClock) simRun.sim.steadyClock else simRun.simSteadyClock
        simRun.sim.startUp()
        val instance = (config.devices.filter { it.userApplication != null }.map { it.userApplication!!.getAllChildApplications() }.flatten().filter { it is DatabaseHandle }.first()as DatabaseHandle).instance
        val pkg0 = MySimulatorTestingExecute(query)
        var verifyExecuted1 = 0
        val pkg1 = MySimulatorTestingCompareGraphPackage("SELECT ?s ?p ?o WHERE { ?s ?p ?o . }", MemoryTable.parseFromAny(outputData[0], outputType[0], Query(instance))!!, { verifyExecuted1++ })
        pkg0.onFinish = pkg1
        config.querySenders[0].queryPck = pkg0
        simRun.sim.run()
        simRun.sim.shutDown()
        if (verifyExecuted1 == 0) {
            fail("pck1 not verified")
        }
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_1_AllCollations - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_1_AllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_2_AllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_O_AllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_S_AllCollations - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_S_AllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByKeyAllCollations - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByKeyAllCollations - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - Simple - Centralized - true - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - Simple - Centralized - false - None`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "None",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByIDTwiceAllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByIDTwiceAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_1_AllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_1_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_2_AllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_2_AllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_2_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_O_AllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_O_AllCollations - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_O_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByID_S_AllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByID_S_AllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - PartitionByKeyAllCollations - Centralized - true - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "PartitionByKeyAllCollations",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to true,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }

    @Test(timeout = 2000)
    public fun `Simple insert data 1 - in simulator - Simple - Centralized - false - Thread`() {
        simulatorHelper(
            "../luposdate3000_simulator_iot/src/jvmTest/resources/autoIntegrationTest/test2.json",
            mutableMapOf(
                "predefinedPartitionScheme" to "Simple",
                "mergeLocalOperatorgraphs" to true,
                "queryDistributionMode" to "Centralized",
                "useDictionaryInlineEncoding" to false,
                "REPLACE_STORE_WITH_VALUES" to false,
                "LUPOS_PARTITION_MODE" to "Thread",
            )
        )
    }
}
