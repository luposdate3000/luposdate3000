package lupos

import lupos.s00misc.*
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.*
import lupos.s11outputResult.*
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl
import lupos.s15tripleStoreDistributed.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class GeneratedAOPBuildInCallSTRLENTest {
    constructor() {
        P2P.knownClients.clear()
        P2P.knownClients.add(EndpointImpl.fullname)
    }

    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {
        for (n in node.children)
            setAggregationMode(n, mode, count)
        if (node is AOPAggregation) {
            node.count = count
            node.collectMode = mode
            if (node.collectMode)
                node.a = null
        }
    }

    @TestFactory
    fun test() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                MicroTestA1(
                        AOPBuildInCallSTRLEN(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                MicroTestA1(
                        AOPBuildInCallSTRLEN(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                MicroTestA1(
                        AOPBuildInCallSTRLEN(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                MicroTestA1(
                        AOPBuildInCallSTRLEN(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                MicroTestA1(
                        AOPBuildInCallSTRLEN(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(4)
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                MicroTestA1(
                        AOPBuildInCallSTRLEN(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                MicroTestA1(
                        AOPBuildInCallSTRLEN(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                MicroTest0(AOPUndef(), AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("$index") {
            try {
                if (data.input is AOPBase) {
                    val input = data.input as AOPBase
                    val output: AOPConstant
                    if (data is MicroTestA1) {
                        output = input.calculate(data.resultSet, data.resultRow)
                    } else if (data is MicroTestAN) {
                        setAggregationMode(input, true, data.resultRows.count())
                        for (resultRow in data.resultRows)
                            input.calculate(data.resultSet, resultRow)
                        setAggregationMode(input, false, data.resultRows.count())
                        output = input.calculate(data.resultSet, data.resultSet.createResultRow())
                    } else {
                        val resultSet = ResultSet(ResultSetDictionary())
                        output = input.calculate(resultSet, resultSet.createResultRow())
                    }
                    assertTrue(data.expected is AOPConstant)
                    if (!data.expected.equals(output)) {
                        if (data is MicroTestA1)
                            println(data.resultRow)
                        println(output.valueToString())
                        println((data.expected as AOPConstant).valueToString())
                    }
                    assertTrue(data.expected.equals(output))
                } else if (data.input is POPBase) {
                    val input = data.input as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)) {
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    assertTrue(expected.myEquals(output))
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
