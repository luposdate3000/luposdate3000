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


class GeneratedAOPBuildInCallDATATYPETest {
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
                resultSet.createVariable("n")
                MicroTestA1(
                        AOPBuildInCallDATATYPE(AOPVariable("n")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("n")] = resultSet.createValue("\"2020-02-25T08:04:25Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>")
                            resultRow
                        }(),
                        resultSet,
                        AOPIri("http://www.w3.org/2001/XMLSchema#dateTime")
                )
            }() /* resources/sparql11-test-suite/functions/now01.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("l")
                MicroTestA1(
                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>")
                            resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPIri("http://www.w3.org/2001/XMLSchema#integer")
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("l")
                MicroTestA1(
                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>")
                            resultRow[resultSet.createVariable("l")] = resultSet.createValue("<http://www.example.org/schema#a>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall DATATYPE only works with typed string input")
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("m")
                resultSet.createVariable("x")
                resultSet.createVariable("l")
                MicroTestA1(
                        AOPBuildInCallDATATYPE(AOPVariable("m")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("m"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>")
                            resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall DATATYPE only works with typed string input")
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("y")
                resultSet.createVariable("l")
                resultSet.createVariable("x")
                MicroTestA1(
                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>")
                            resultRow
                        }(),
                        resultSet,
                        AOPIri("http://www.w3.org/2001/XMLSchema#integer")
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("y")
                resultSet.createVariable("l")
                resultSet.createVariable("x")
                MicroTestA1(
                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("l"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#b>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall DATATYPE only works with typed string input")
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
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
