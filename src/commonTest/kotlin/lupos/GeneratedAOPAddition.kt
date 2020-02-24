package lupos

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


class GeneratedAOPAdditionTest {
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
                resultSet.createVariable("#0")
                resultSet.createVariable("O1")
                resultSet.createVariable("#2")
                resultSet.createVariable("O2")
                resultSet.createVariable("S")
                MicroTestA1(
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(0)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("O1")
                resultSet.createVariable("#2")
                resultSet.createVariable("O2")
                resultSet.createVariable("S")
                MicroTestA1(
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(1)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("O1")
                resultSet.createVariable("#2")
                resultSet.createVariable("O2")
                resultSet.createVariable("S")
                MicroTestA1(
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("O1")
                resultSet.createVariable("#2")
                resultSet.createVariable("O2")
                resultSet.createVariable("S")
                MicroTestA1(
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(1)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("O1")
                resultSet.createVariable("#2")
                resultSet.createVariable("O2")
                resultSet.createVariable("S")
                MicroTestA1(
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("O1")
                resultSet.createVariable("#2")
                resultSet.createVariable("O2")
                resultSet.createVariable("S")
                MicroTestA1(
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("O1")
                resultSet.createVariable("#2")
                resultSet.createVariable("O2")
                resultSet.createVariable("S")
                MicroTestA1(
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("O1")
                resultSet.createVariable("#2")
                resultSet.createVariable("O2")
                resultSet.createVariable("S")
                MicroTestA1(
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("O1")
                resultSet.createVariable("#2")
                resultSet.createVariable("O2")
                resultSet.createVariable("S")
                MicroTestA1(
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("O1")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("O2")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("S")] = resultSet.createValue("<http://www.example.org/s>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(4)
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(11)
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(12)
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(13)
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(14)
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(100), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(101)
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(100), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(102)
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(100), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(103)
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(100), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(104)
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(1), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(1), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(1), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(4)
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPAddition(AOPInteger(1), AOPVariable("o")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(5)
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("_:b")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x2>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://example/a>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x3>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x4>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x5>")
                            resultRow
                        }(),
                        resultSet,
                        AOPDecimal(3.0)
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x6>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x7>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"a\"")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x1>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x4>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x5>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x6>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x7>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("x")
                resultSet.createVariable("#2")
                resultSet.createVariable("y")
                resultSet.createVariable("s")
                MicroTestA1(
                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x8>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPAddition only works with numeric input")
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("y")
                resultSet.createVariable("#2")
                resultSet.createVariable("z")
                resultSet.createVariable("x")
                MicroTestA1(
                        AOPAddition(AOPVariable("z"), AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("y")
                resultSet.createVariable("#2")
                resultSet.createVariable("z")
                resultSet.createVariable("x")
                MicroTestA1(
                        AOPAddition(AOPVariable("z"), AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(3)
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("#1")
                resultSet.createVariable("y")
                MicroTestA1(
                        AOPAddition(AOPVariable("y"), AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(2)
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("x")
                resultSet.createVariable("#1")
                resultSet.createVariable("y")
                MicroTestA1(
                        AOPAddition(AOPVariable("y"), AOPVariable("y")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPInteger(4)
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
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
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
