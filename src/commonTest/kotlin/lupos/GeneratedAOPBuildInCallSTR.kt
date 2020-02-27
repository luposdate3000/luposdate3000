package lupos

import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl
import lupos.s00misc.*
import lupos.s15tripleStoreDistributed.*
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.*
import lupos.s11outputResult.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class GeneratedAOPBuildInCallSTRTest {
    constructor() {
        P2P.knownClients.clear()
        P2P.knownClients.add(EndpointImpl.fullname)
    }
    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {
        for (n in  node.children)
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
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "bar")
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/date>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2010-06-21T11:28:01Z")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/date>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2010-12-21T15:38:02-08:00")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/date>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2008-06-20T23:59:00Z")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/date>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2011-02-01T01:02:03")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n1>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "-1")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n2>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "-1.6")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n3>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "1.1")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n4>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "-2")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n5>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2.5")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "foo")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "bar")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "BAZ")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "食べ物")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "100%")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "abc")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("str")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("str")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>")
                            resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "DEF")
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"a\"")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x1>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "1")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"a\"")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x1>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "a")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("_:b")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x2>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "1")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("_:b")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x2>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall STR only works with string input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://example/a>")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x3>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "1")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x4>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x4>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "1")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x5>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x5>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "1.0")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x6>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x6>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "1")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x7>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x7>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "1")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x8>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "2")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPBuildInCallSTR(AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x8>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "1")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
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
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
