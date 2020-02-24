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


class GeneratedAOPBuildInCallCONCATTest {
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
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("#3")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#3"))
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPTypedLiteral("\"", "abcDEF", "http://www.w3.org/2001/XMLSchema#string")
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"123\"")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"123\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "123123")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"123\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "日本語123")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"english\"@en")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"123\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "english123")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"français\"@fr")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"123\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "français123")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"123\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "abc123")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"123\"")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "def123")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"123\"")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"123\"")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "123日本語")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow
                        }(),
                        resultSet,
                        AOPLanguageTaggedLiteral("\"", "日本語日本語", "ja")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"english\"@en")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "english日本語")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"français\"@fr")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "français日本語")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "abc日本語")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "def日本語")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"123\"")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"english\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "123english")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"english\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "日本語english")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"english\"@en")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"english\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPLanguageTaggedLiteral("\"", "englishenglish", "en")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"français\"@fr")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"english\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "françaisenglish")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"english\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "abcenglish")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"english\"@en")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "defenglish")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"english\"@en")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"123\"")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"français\"@fr")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "123français")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"français\"@fr")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "日本語français")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"english\"@en")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"français\"@fr")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "englishfrançais")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"français\"@fr")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"français\"@fr")
                            resultRow
                        }(),
                        resultSet,
                        AOPLanguageTaggedLiteral("\"", "françaisfrançais", "fr")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"français\"@fr")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "abcfrançais")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"français\"@fr")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "deffrançais")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"français\"@fr")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"123\"")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "123abc")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "日本語abc")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"english\"@en")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "englishabc")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"français\"@fr")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "françaisabc")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPTypedLiteral("\"", "abcabc", "http://www.w3.org/2001/XMLSchema#string")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPTypedLiteral("\"", "defabc", "http://www.w3.org/2001/XMLSchema#string")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"123\"")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "123def")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "日本語def")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"english\"@en")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "englishdef")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"français\"@fr")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "françaisdef")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPTypedLiteral("\"", "abcdef", "http://www.w3.org/2001/XMLSchema#string")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        AOPTypedLiteral("\"", "defdef", "http://www.w3.org/2001/XMLSchema#string")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s1>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"123\"")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s2>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"日本語\"@ja")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s3>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"english\"@en")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s4>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"français\"@fr")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s5>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s6>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("s1")
                resultSet.createVariable("#1")
                resultSet.createVariable("str1")
                resultSet.createVariable("s2")
                resultSet.createVariable("#4")
                resultSet.createVariable("str2")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultRow[resultSet.createVariable("s1")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#1"))
                            resultRow[resultSet.createVariable("str1")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow[resultSet.createVariable("s2")] = resultSet.createValue("<http://example.org/s7>")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#4"))
                            resultRow[resultSet.createVariable("str2")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>")
                            resultRow
                        }(),
                        resultSet,
                        Exception("AOPBuiltInCall CONCAT only works with compatible string input")
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("F")
                resultSet.createVariable("#2")
                resultSet.createVariable("L")
                resultSet.createVariable("P")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("F")] = resultSet.createValue("\"John\"")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("L")] = resultSet.createValue("\"Doe\"")
                            resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://p1>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "John ")
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val resultSet = ResultSet(ResultSetDictionary())
                resultSet.createVariable("#0")
                resultSet.createVariable("F")
                resultSet.createVariable("#2")
                resultSet.createVariable("L")
                resultSet.createVariable("P")
                MicroTestA1(
                        AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L")),
                        {
                            val resultRow = resultSet.createResultRow()
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#0"))
                            resultRow[resultSet.createVariable("F")] = resultSet.createValue("\"John\"")
                            resultSet.setUndefValue(resultRow, resultSet.createVariable("#2"))
                            resultRow[resultSet.createVariable("L")] = resultSet.createValue("\"Doe\"")
                            resultRow[resultSet.createVariable("P")] = resultSet.createValue("<http://p1>")
                            resultRow
                        }(),
                        resultSet,
                        AOPSimpleLiteral("\"", "John Doe")
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
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
