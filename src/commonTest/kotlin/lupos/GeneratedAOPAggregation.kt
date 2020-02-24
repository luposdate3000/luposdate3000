package lupos

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


class GeneratedAOPAggregationTest {
    constructor(){
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
                resultSet.createVariable("S")
                resultSet.createVariable("P")
                resultSet.createVariable("O")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf(AOPVariable("O"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o1>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o2>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o3>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p2>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o1>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p2>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o2>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(5)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("S")
                resultSet.createVariable("P")
                resultSet.createVariable("O")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf(AOPVariable("O"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o1>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o2>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o3>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("S")
                resultSet.createVariable("P")
                resultSet.createVariable("O")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf(AOPVariable("O"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p2>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o1>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p2>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o2>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("S")
                resultSet.createVariable("P")
                resultSet.createVariable("O")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf()),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o1>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o2>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o3>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p2>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o1>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p2>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o2>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(5)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg06.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("S")
                resultSet.createVariable("P")
                resultSet.createVariable("O")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf()),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o1>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o2>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p1>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o3>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("S")
                resultSet.createVariable("P")
                resultSet.createVariable("O")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf()),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p2>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o1>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://www.example.org/p2>")
                                resultRow[resultSet.createVariable("O")] = resultSet.createValue("<http://www.example.org/o2>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("O12")
                resultSet.createVariable("S")
                resultSet.createVariable("#2")
                resultSet.createVariable("O1")
                resultSet.createVariable("#4")
                resultSet.createVariable("O2")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf(AOPVariable("O1"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("O12")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                                resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(1)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("O12")
                resultSet.createVariable("S")
                resultSet.createVariable("#2")
                resultSet.createVariable("O1")
                resultSet.createVariable("#4")
                resultSet.createVariable("O2")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf(AOPVariable("O1"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("O12")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                                resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("O12")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                                resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("O12")
                resultSet.createVariable("S")
                resultSet.createVariable("#2")
                resultSet.createVariable("O1")
                resultSet.createVariable("#4")
                resultSet.createVariable("O2")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf(AOPVariable("O1"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("O12")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                                resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("O12")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                                resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("O12")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                                resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("O12")
                resultSet.createVariable("S")
                resultSet.createVariable("#2")
                resultSet.createVariable("O1")
                resultSet.createVariable("#4")
                resultSet.createVariable("O2")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf(AOPVariable("O1"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("O12")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                                resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("O12")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                                resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("O12")
                resultSet.createVariable("S")
                resultSet.createVariable("#2")
                resultSet.createVariable("O1")
                resultSet.createVariable("#4")
                resultSet.createVariable("O2")
                MicroTestAN(
                        AOPAggregation(Aggregation.COUNT, false, arrayOf(AOPVariable("O1"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("O12")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                                resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(1)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("#1")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.SUM, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(11.100000000000001)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.SUM, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(6.7)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.SUM, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDouble(32100.0)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.SUM, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(6)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.SUM, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(3.2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.SUM, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDouble(0.4)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("#1")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.AVG, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(2.22)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.AVG, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(2.2333333333333334)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.AVG, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDouble(10700.0)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.AVG, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(2.0)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.AVG, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(1.6)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.AVG, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDouble(0.2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("#1")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MIN, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(1.0)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MIN, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(1.0)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MIN, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDouble(100.0)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MIN, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(1)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MIN, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(1)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MIN, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDouble(0.2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MAX, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDouble(30000.0)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MAX, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(3.5)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MAX, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/doubles>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDouble(30000.0)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MAX, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/ints>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MAX, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/int>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(2.2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.MAX, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/dec>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://www.example.org/double>")
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(2.2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("#1")
                resultSet.createVariable("o")
                MicroTestAN(
                        AOPAggregation(Aggregation.SAMPLE, false, arrayOf(AOPVariable("o"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/decimals>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed1>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://www.example.org/mixed2>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(2.2)
                )
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-sample-01.rq */
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("g")
                resultSet.createVariable("#1")
                resultSet.createVariable("p")
                MicroTestAN(
                        AOPAggregation(Aggregation.MAX, false, arrayOf(AOPVariable("p"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(4)
                )
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("g")
                resultSet.createVariable("#1")
                resultSet.createVariable("p")
                MicroTestAN(
                        AOPAggregation(Aggregation.MIN, false, arrayOf(AOPVariable("p"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(1)
                )
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("g")
                resultSet.createVariable("#1")
                resultSet.createVariable("p")
                MicroTestAN(
                        AOPAggregation(Aggregation.AVG, false, arrayOf(AOPVariable("p"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#x>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(2.5)
                )
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("g")
                resultSet.createVariable("#1")
                resultSet.createVariable("p")
                MicroTestAN(
                        AOPAggregation(Aggregation.MAX, false, arrayOf(AOPVariable("p"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#y>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#y>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#y>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(4)
                )
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("g")
                resultSet.createVariable("#1")
                resultSet.createVariable("p")
                MicroTestAN(
                        AOPAggregation(Aggregation.MIN, false, arrayOf(AOPVariable("p"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#y>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#y>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#y>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(1.0)
                )
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("g")
                resultSet.createVariable("#1")
                resultSet.createVariable("p")
                MicroTestAN(
                        AOPAggregation(Aggregation.AVG, false, arrayOf(AOPVariable("p"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#y>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#y>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#y>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                                resultRow
                            }(),
                            {
                                val resultRow = resultSet.createResultRow()
                                resultRow[resultSet.createVariable("g")] = resultSet.createValue("<http://example.com/data/#z>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                                resultRow[resultSet.createVariable("p")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPDecimal(2.5)
                )
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("v")
                resultSet.createVariable("#2")
                resultSet.createVariable("w")
                resultSet.createVariable("s")
                MicroTestAN(
                        AOPAggregation(Aggregation.SAMPLE, false, arrayOf(AOPVariable("v"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                                resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultRow[resultSet.createVariable("w")] = resultSet.createValue("\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/s1>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(1)
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("v")
                resultSet.createVariable("#2")
                resultSet.createVariable("w")
                resultSet.createVariable("s")
                MicroTestAN(
                        AOPAggregation(Aggregation.SAMPLE, false, arrayOf(AOPVariable("v"))),
                        listOf(
                            {
                                val resultRow = resultSet.createResultRow()
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                                resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                                resultSet.setUndefValue(resultRow, resultSet.createVariable("w"))
                                resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/s2>")
                                resultRow
                            }()
                        ),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
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
                    if (!expected.myEquals(output)){
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
