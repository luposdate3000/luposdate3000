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


class GeneratedAOPEQTest {
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
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("sample")
                MicroTestA1(
                        AOPEQ(AOPVariable("sample"), AOPDecimal(3.5)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("sample")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }()*/
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("sample")
                MicroTestA1(
                        AOPEQ(AOPVariable("sample"), AOPDecimal(2.2)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("sample")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }()*/
            /*{
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("sample")
                MicroTestA1(
                        AOPEQ(AOPVariable("sample"), AOPDecimal(1.0)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("sample")] = resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }()*/
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("#2")
                resultSet.createVariable("v")
                MicroTestA1(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("z"))
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("#2")
                resultSet.createVariable("v")
                MicroTestA1(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("z"))
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("#2")
                resultSet.createVariable("v")
                MicroTestA1(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("z"))
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("#2")
                resultSet.createVariable("v")
                MicroTestA1(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("z"))
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("#2")
                resultSet.createVariable("v")
                MicroTestA1(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("#2")
                resultSet.createVariable("v")
                MicroTestA1(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("#2")
                resultSet.createVariable("v")
                MicroTestA1(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("#2")
                resultSet.createVariable("v")
                MicroTestA1(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("o")
                resultSet.createVariable("#3")
                resultSet.createVariable("#4")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#3"))
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("o")
                resultSet.createVariable("#3")
                resultSet.createVariable("#4")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#3"))
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("o")
                resultSet.createVariable("#3")
                resultSet.createVariable("#4")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#3"))
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("z")
                resultSet.createVariable("s")
                resultSet.createVariable("o")
                resultSet.createVariable("#3")
                resultSet.createVariable("#4")
                resultSet.createVariable("p")
                MicroTestA1(
                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#3"))
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("a")
                resultSet.createVariable("#1")
                resultSet.createVariable("s1")
                resultSet.createVariable("b")
                resultSet.createVariable("#4")
                resultSet.createVariable("s2")
                MicroTestA1(
                        AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("n")
                MicroTestA1(
                        AOPEQ(AOPBuildInCallDATATYPE(AOPVariable("n")), AOPIri("http://www.w3.org/2001/XMLSchema#dateTime")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("n")] = resultSet.createValue("\"2020-02-24T12:42:53Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"123\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"english\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"français\"@fr")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s")
                resultSet.createVariable("p")
                resultSet.createVariable("o")
                MicroTestA1(
                        AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>")
                            resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>")
                            resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        AOPBoolean(false)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("y")
                resultSet.createVariable("#2")
                resultSet.createVariable("z")
                resultSet.createVariable("x")
                MicroTestA1(
                        AOPEQ(AOPVariable("y"), AOPVariable("z")),
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
                        AOPBoolean(true)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("y")
                resultSet.createVariable("#2")
                resultSet.createVariable("z")
                resultSet.createVariable("x")
                MicroTestA1(
                        AOPEQ(AOPVariable("y"), AOPVariable("z")),
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
                        AOPBoolean(false)
                )
            }(),
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
