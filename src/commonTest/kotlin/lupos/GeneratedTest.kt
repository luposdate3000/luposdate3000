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
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class AOPVariableTest {
    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(5))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg02_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(2))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg03_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),AOPInteger(2)),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O"))),AOPInteger(2)),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPBoolean(false))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg03.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg04_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(5))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg04.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg05_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(2))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg05.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg06_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(0)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(0)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(0)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(0)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(0)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(0)),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPBoolean(true))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg06.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg07_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p1>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o3>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(2)),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o1>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(2)),{resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://www.example.org/p2>");resultRow[resultSet.createVariable("O")]=resultSet.createValue("<http://www.example.org/o2>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf()),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("S");resultSet.createVariable("P");resultSet.createVariable("O");val resultRow = resultSet.createResultRow();MicroTest(AOPGT(AOPAggregation(Aggregation.COUNT,false,arrayOf()),AOPInteger(2)),{resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultSet.setUndefValue(resultRow,resultSet.createVariable("P"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O"));resultRow}(),resultSet,AOPBoolean(false))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg07.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg08b_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("O2"),AOPVariable("O1")),{resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("O2"),AOPVariable("O1")),{resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("O2"),AOPVariable("O1")),{resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("O2"),AOPVariable("O1")),{resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("O2"),AOPVariable("O1")),{resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("O2"),AOPVariable("O1")),{resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("O2"),AOPVariable("O1")),{resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("O2"),AOPVariable("O1")),{resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("O2"),AOPVariable("O1")),{resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultRow[resultSet.createVariable("O12")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("O12"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p2083"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O1"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p2084"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O2"));resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultRow[resultSet.createVariable("O12")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultRow[resultSet.createVariable("O12")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("O12"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p2083"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O1"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p2084"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O2"));resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultRow[resultSet.createVariable("O12")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultRow[resultSet.createVariable("O12")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultRow[resultSet.createVariable("O12")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("O12"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p2083"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O1"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p2084"));resultSet.setUndefValue(resultRow,resultSet.createVariable("O2"));resultSet.setUndefValue(resultRow,resultSet.createVariable("S"));resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultRow[resultSet.createVariable("O12")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultRow[resultSet.createVariable("O12")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("O12");resultSet.createVariable("#p2083");resultSet.createVariable("O1");resultSet.createVariable("#p2084");resultSet.createVariable("O2");resultSet.createVariable("S");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.COUNT,false,arrayOf(AOPVariable("O1"))),{resultRow[resultSet.createVariable("O12")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2083")]=resultSet.createValue("<http://www.example.org/p>");resultRow[resultSet.createVariable("O1")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p2084")]=resultSet.createValue("<http://www.example.org/q>");resultRow[resultSet.createVariable("O2")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("S")]=resultSet.createValue("<http://www.example.org/s>");resultRow}(),resultSet,AOPInteger(1))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg08b.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_sum_01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p2889");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p2889")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p2889");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p2889")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(3.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p2889");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p2889")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(6.7))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p2889");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("#p2889")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(8.9))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p2889");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("#p2889")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(11.100000000000001))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p2889");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p2889"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(11.100000000000001))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-sum-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_sum_02_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(3.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(6.7))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(6.7))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(2100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(32100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDouble(32100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(6))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPInteger(6))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(3.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(3.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(0.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(0.4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SUM,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDouble(0.4))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-sum-02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_avg_01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3183");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p3183")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(0.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3183");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p3183")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(0.6400000000000001))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3183");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p3183")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.34))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3183");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("#p3183")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.7800000000000002))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3183");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("#p3183")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.22))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3183");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p3183"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(2.22))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-avg-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_avg_02_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(0.3333333333333333))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0666666666666667))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2333333333333334))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(2.2333333333333334))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(33.333333333333336))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(700.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(10700.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDouble(10700.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(0.3333333333333333))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(2.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(2.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(1.6))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(1.6))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(0.1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(0.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPLEQ(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),AOPDecimal(2.0)),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDouble(0.2))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-avg-02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_min_01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3781");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p3781")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3781");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p3781")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3781");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p3781")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3781");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("#p3781")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3781");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("#p3781")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p3781");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p3781"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-min-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_min_02_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDouble(100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(0.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDouble(0.2))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-min-02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_max_01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(3.5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(2000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-max-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_max_02_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(3.5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(3.5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"100.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(100.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(2000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/doubles>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"30000.0\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDouble(30000.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/ints>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/int>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://www.example.org/double>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"0.2\"^^<http://www.w3.org/2001/XMLSchema#double>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-max-02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_sample_01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p4446");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p4446")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p4446");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p4446")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p4446");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/decimals>");resultRow[resultSet.createVariable("#p4446")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(3.5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p4446");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed1>");resultRow[resultSet.createVariable("#p4446")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p4446");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("o"))),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://www.example.org/mixed2>");resultRow[resultSet.createVariable("#p4446")]=resultSet.createValue("<http://www.example.org/dec>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p4446");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("o"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p4446"));resultSet.setUndefValue(resultRow,resultSet.createVariable("o"));resultRow}(),resultSet,AOPDecimal(2.2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("sample");val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("sample"),AOPDecimal(3.5)),{resultRow[resultSet.createVariable("sample")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("sample");val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("sample"),AOPDecimal(2.2)),{resultRow[resultSet.createVariable("sample")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("sample");val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("sample"),AOPDecimal(1.0)),{resultRow[resultSet.createVariable("sample")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("sample");val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("sample"),AOPDecimal(2.2)),AOPEQ(AOPVariable("sample"),AOPDecimal(1.0))),{resultRow[resultSet.createVariable("sample")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("sample");val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("sample"),AOPDecimal(3.5)),AOPOr(AOPEQ(AOPVariable("sample"),AOPDecimal(2.2)),AOPEQ(AOPVariable("sample"),AOPDecimal(1.0)))),{resultRow[resultSet.createVariable("sample")]=resultSet.createValue("\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPBoolean(true))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-sample-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_aggregates_agg_err_01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("g"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p4598"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultRow}(),resultSet,AOPInteger(4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("g"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p4598"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("g"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p4598"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("g"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p4598"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultRow}(),resultSet,AOPInteger(0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(0.25))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(0.75))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(1.5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#x>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(2.5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("g"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p4598"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultRow}(),resultSet,AOPDecimal(2.5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(3))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(0.25))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#y>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(2.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(2.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(3.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(0.6666666666666666))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(3.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(4.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(0.5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPInteger(4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(5.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(0.4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("g"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p4598"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultRow}(),resultSet,AOPDecimal(1.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p")))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("g"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p4598"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultRow}(),resultSet,AOPDecimal(5.0))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(2),AOPAddition(AOPAggregation(Aggregation.MAX,false,arrayOf(AOPVariable("p"))),AOPAggregation(Aggregation.MIN,false,arrayOf(AOPVariable("p"))))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("g"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p4598"));resultSet.setUndefValue(resultRow,resultSet.createVariable("p"));resultRow}(),resultSet,AOPDecimal(0.4))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(0.25))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(0.75))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,AOPDecimal(1.5))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("g");resultSet.createVariable("#p4598");resultSet.createVariable("p");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.AVG,false,arrayOf(AOPVariable("p"))),{resultRow[resultSet.createVariable("g")]=resultSet.createValue("<http://example.com/data/#z>");resultRow[resultSet.createVariable("#p4598")]=resultSet.createValue("<http://example.com/data/#p>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,AOPDecimal(2.5))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/aggregates/agg-err-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_bind_bind01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(11))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(12))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(13))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(14))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/bind/bind01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_bind_bind02_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(11))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(100), AOPVariable("o")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(101))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(12))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(13))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(14))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(100), AOPVariable("o")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(102))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(100), AOPVariable("o")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(103))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(100), AOPVariable("o")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(104))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/bind/bind02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_bind_bind03_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(4))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(5))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/bind/bind03.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_bind_bind05_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(4))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(5))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/bind/bind05.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_bind_bind06_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(11))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(12))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(13))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(14))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/bind/bind06.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_bind_bind08_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(4))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(5))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/bind/bind08.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_bind_bind10_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7660");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("z"));resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p7660")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7660");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("z"));resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p7660")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7660");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("z"));resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p7660")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7660");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("z"));resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p7660")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7729");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("z"));resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p7729")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7729");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("z"));resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p7729")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7729");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("z"));resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p7729")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7729");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("z"));resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p7729")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/bind/bind10.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_bind_bind11_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7853");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p7853")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7853");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p7853")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7853");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p7853")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7853");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p7853")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7917");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p7917")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7917");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p7917")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7917");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p7917")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("#p7917");resultSet.createVariable("v");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("v"), AOPVariable("z")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p7917")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("v")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/bind/bind11.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_entailment_bind01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(11))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(12))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(13))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(14))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/entailment/bind01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_entailment_bind02_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(11))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(12))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(13))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(100), AOPVariable("o")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"11\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(101))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(100), AOPVariable("o")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"12\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(102))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(14))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(100), AOPVariable("o")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"13\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(103))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(100), AOPVariable("o")), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"14\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(104))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/entailment/bind02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_entailment_bind03_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(4))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(5))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/entailment/bind03.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_entailment_bind05_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(4))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(5))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p16582");resultSet.createVariable("#o16582");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p16582")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o16582")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p16582");resultSet.createVariable("#o16582");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p16582")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o16582")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p16582");resultSet.createVariable("#o16582");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p16582")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o16582")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p16582");resultSet.createVariable("#o16582");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p16582")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o16582")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p16699");resultSet.createVariable("#o16699");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p16699")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o16699")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p16699");resultSet.createVariable("#o16699");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p16699")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o16699")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p16699");resultSet.createVariable("#o16699");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p16699")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o16699")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p16699");resultSet.createVariable("#o16699");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p16699")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o16699")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/entailment/bind05.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_entailment_bind06_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(11))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(12))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(13))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(10), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(14))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/entailment/bind06.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_entailment_bind08_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(4))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPInteger(1), AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(5))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p17130");resultSet.createVariable("#o17130");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p17130")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o17130")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p17130");resultSet.createVariable("#o17130");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p17130")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o17130")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p17130");resultSet.createVariable("#o17130");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p17130")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o17130")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p17130");resultSet.createVariable("#o17130");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p17130")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o17130")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p17247");resultSet.createVariable("#o17247");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p17247")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o17247")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p17247");resultSet.createVariable("#o17247");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p17247")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o17247")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p17247");resultSet.createVariable("#o17247");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p17247")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o17247")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("z");resultSet.createVariable("s");resultSet.createVariable("o");resultSet.createVariable("#p17247");resultSet.createVariable("#o17247");resultSet.createVariable("p");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("z"), AOPInteger(3)), { resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"5\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p17247")] = resultSet.createValue("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>");resultRow[resultSet.createVariable("#o17247")] = resultSet.createValue("<http://www.w3.org/2002/07/owl#DatatypeProperty>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/p>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/entailment/bind08.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_strdt01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"bar\"@en");resultRow}(),resultSet,AOPSimpleLiteral("\"","en"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"bar\"@en");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"食べ物\"");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"食べ物\"");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"100%\"");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"100%\"");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("str"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"bar\"@en");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21018");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21018")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/strdt01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_strdt02_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"bar\"@en");resultRow}(),resultSet,AOPSimpleLiteral("\"","en"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"bar\"@en");resultRow}(),resultSet,AOPBoolean(true))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"食べ物\"");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"食べ物\"");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"100%\"");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"100%\"");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"bar\"@en");resultRow}(),resultSet,AOPSimpleLiteral("\"","bar"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"bar\"@en");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21219");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")),AOPSimpleLiteral("\"","en")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21219")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,AOPBoolean(false))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/strdt02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_strdt03_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n3>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n4>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n5>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"bar\"@en");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"食べ物\"");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"100%\"");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRDT(AOPVariable("o"),AOPIri("http://www.w3.org/2001/XMLSchema#string")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRDT only works with simple string input"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/strdt03.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_strlang01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPSimpleLiteral("\"", "en"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, Exception("AOPBuiltInCall STRLANG only works with simple string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21579");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21579")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPSimpleLiteral("\"", "en"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, Exception("AOPBuiltInCall STRLANG only works with simple string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21712");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21712")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/strlang01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_strlang02_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPSimpleLiteral("\"", "en"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPSimpleLiteral("\"", "bar"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPLanguageTaggedLiteral("\"", "bar", "en-US"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p21901");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p21901")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPSimpleLiteral("\"", "en"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPSimpleLiteral("\"", "bar"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPLanguageTaggedLiteral("\"", "bar", "en-US"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22042");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p22042")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/strlang02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_strlang03_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n3>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n4>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/n5>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","foo","en-US"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"bar\"@en");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","BAZ","en-US"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"食べ物\"");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","食べ物","en-US"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"100%\"");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","100%","en-US"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLANG(AOPVariable("o"),AOPSimpleLiteral("\"","en-US")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")]=resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRLANG only works with simple string input"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/strlang03.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_isnumeric01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n5>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIsNUMERIC(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/isnumeric01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_abs01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallABS(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n1>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(1))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n1>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallABS(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n2>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(1.6))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n2>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallABS(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n3>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(1.1))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n3>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallABS(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n4>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n4>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallABS(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n5>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(2.5))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22576");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n5>");resultRow[resultSet.createVariable("#p22576")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/abs01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_ceil01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22708");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCEIL(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n1>");resultRow[resultSet.createVariable("#p22708")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(-1))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22708");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCEIL(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n2>");resultRow[resultSet.createVariable("#p22708")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(-1.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22708");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCEIL(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n3>");resultRow[resultSet.createVariable("#p22708")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(2.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22708");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCEIL(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n4>");resultRow[resultSet.createVariable("#p22708")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(-2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22708");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCEIL(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n5>");resultRow[resultSet.createVariable("#p22708")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(3.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/ceil01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_floor01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22803");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallFLOOR(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n2>");resultRow[resultSet.createVariable("#p22803")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(-2.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22803");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallFLOOR(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n3>");resultRow[resultSet.createVariable("#p22803")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(1.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22803");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallFLOOR(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n5>");resultRow[resultSet.createVariable("#p22803")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(2.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/floor01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_round01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22886");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallROUND(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n1>");resultRow[resultSet.createVariable("#p22886")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(-1))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22886");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallROUND(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n2>");resultRow[resultSet.createVariable("#p22886")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(-2.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22886");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallROUND(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n3>");resultRow[resultSet.createVariable("#p22886")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(1.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22886");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallROUND(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n4>");resultRow[resultSet.createVariable("#p22886")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(-2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p22886");resultSet.createVariable("num");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallROUND(AOPVariable("num")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/n5>");resultRow[resultSet.createVariable("#p22886")] = resultSet.createValue("<http://example.org/num>");resultRow[resultSet.createVariable("num")] = resultSet.createValue("\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow }(), resultSet, AOPDecimal(3.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/round01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_concat01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s22987");resultSet.createVariable("#p22987");resultSet.createVariable("str1");resultSet.createVariable("#s22988");resultSet.createVariable("#p22988");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("#s22987")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p22987")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("#s22988")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p22988")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/concat01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_concat02_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"123\"");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"123\"");resultRow}(),resultSet,AOPSimpleLiteral("\"","123123"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"日本語\"@ja");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"123\"");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","日本語123","ja"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"english\"@en");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"123\"");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","english123","en"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"français\"@fr");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"123\"");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","français123","fr"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"123\"");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"123\"");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"123\"");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"123\"");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"日本語\"@ja");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","日本語123","ja"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"日本語\"@ja");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"日本語\"@ja");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","日本語日本語","ja"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"english\"@en");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"日本語\"@ja");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible languages input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"français\"@fr");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"日本語\"@ja");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible languages input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"日本語\"@ja");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"日本語\"@ja");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"日本語\"@ja");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"123\"");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"english\"@en");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","english123","en"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"日本語\"@ja");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"english\"@en");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible languages input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"english\"@en");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"english\"@en");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","englishenglish","en"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"français\"@fr");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"english\"@en");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible languages input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"english\"@en");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"english\"@en");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"english\"@en");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"123\"");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"français\"@fr");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","français123","fr"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"日本語\"@ja");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"français\"@fr");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible languages input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"english\"@en");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"français\"@fr");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible languages input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"français\"@fr");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"français\"@fr");resultRow}(),resultSet,AOPLanguageTaggedLiteral("\"","françaisfrançais","fr"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"français\"@fr");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"français\"@fr");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"français\"@fr");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"123\"");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"日本語\"@ja");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"english\"@en");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"français\"@fr");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"123\"");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"日本語\"@ja");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"english\"@en");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"français\"@fr");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"123\"");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"日本語\"@ja");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"english\"@en");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"français\"@fr");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s1");resultSet.createVariable("#p23067");resultSet.createVariable("str1");resultSet.createVariable("s2");resultSet.createVariable("#p23068");resultSet.createVariable("str2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("str1"),AOPVariable("str2")),{resultRow[resultSet.createVariable("s1")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23067")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str1")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23068")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str2")]=resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall CONCAT only works with compatible string input"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/concat02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_length01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23874");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLEN(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23874")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23874");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLEN(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23874")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23874");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLEN(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23874")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23874");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLEN(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23874")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23874");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLEN(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23874")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPInteger(4))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23874");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLEN(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23874")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23874");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRLEN(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23874")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/length01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_ucase01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23982");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallUCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p23982")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "FOO"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23982");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallUCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p23982")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPLanguageTaggedLiteral("\"", "BAR", "en"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23982");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallUCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p23982")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "BAZ"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23982");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallUCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p23982")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "食べ物"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23982");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallUCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p23982")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "100%"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23982");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallUCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p23982")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPTypedLiteral("\"", "ABC", "http://www.w3.org/2001/XMLSchema#string"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p23982");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallUCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p23982")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPTypedLiteral("\"", "DEF", "http://www.w3.org/2001/XMLSchema#string"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/ucase01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_lcase01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24083");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p24083")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "foo"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24083");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p24083")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPLanguageTaggedLiteral("\"", "bar", "en"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24083");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p24083")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "baz"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24083");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p24083")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "食べ物"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24083");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p24083")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "100%"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24083");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p24083")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPTypedLiteral("\"", "abc", "http://www.w3.org/2001/XMLSchema#string"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24083");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLCASE(AOPVariable("str")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p24083")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPTypedLiteral("\"", "def", "http://www.w3.org/2001/XMLSchema#string"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/lcase01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_contains01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24222");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p24222")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24222");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p24222")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24222");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p24222")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24222");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p24222")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24222");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p24222")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24222");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p24222")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24222");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p24222")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24293");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p24293")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24293");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p24293")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24293");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p24293")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24293");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p24293")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24293");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p24293")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24293");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p24293")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p24293");resultSet.createVariable("str");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p24293")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("str")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/contains01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_starts01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("str")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STR only works with string input"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/starts01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_ends01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("str");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTRENDS(AOPVariable("str"),AOPSimpleLiteral("\"","bc")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("p")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("str")]=resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall STRENDS only works with string input"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/ends01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_plus_1_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24560");resultSet.createVariable("x");resultSet.createVariable("#p24561");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("x")), { resultRow[resultSet.createVariable("#p24560")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("_:b");resultRow[resultSet.createVariable("#p24561")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x2>");resultRow }(), resultSet, Exception("AOPAddition only works with numeric input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24560");resultSet.createVariable("x");resultSet.createVariable("#p24561");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("x")), { resultRow[resultSet.createVariable("#p24560")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://example/a>");resultRow[resultSet.createVariable("#p24561")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x3>");resultRow }(), resultSet, Exception("AOPAddition only works with numeric input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24560");resultSet.createVariable("x");resultSet.createVariable("#p24561");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("x")), { resultRow[resultSet.createVariable("#p24560")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p24561")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x4>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24560");resultSet.createVariable("x");resultSet.createVariable("#p24561");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("x")), { resultRow[resultSet.createVariable("#p24560")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow[resultSet.createVariable("#p24561")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x5>");resultRow }(), resultSet, AOPDecimal(3.0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24560");resultSet.createVariable("x");resultSet.createVariable("#p24561");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("x")), { resultRow[resultSet.createVariable("#p24560")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("#p24561")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x6>");resultRow }(), resultSet, Exception("AOPAddition only works with numeric input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24560");resultSet.createVariable("x");resultSet.createVariable("#p24561");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("x")), { resultRow[resultSet.createVariable("#p24560")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("#p24561")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x7>");resultRow }(), resultSet, Exception("AOPAddition only works with numeric input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/plus-1.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_plus_2_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("y")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"a\"");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x1>");resultRow }(), resultSet, Exception("AOPBuiltInCall STR only works with string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("y")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("_:b");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x2>");resultRow }(), resultSet, AOPSimpleLiteral("\"", "1"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("x")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("_:b");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x2>");resultRow }(), resultSet, Exception("AOPBuiltInCall STR only works with string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("y")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://example/a>");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x3>");resultRow }(), resultSet, AOPSimpleLiteral("\"", "1"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("x")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://example/a>");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x3>");resultRow }(), resultSet, Exception("AOPBuiltInCall STR only works with string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("y")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x4>");resultRow }(), resultSet, Exception("AOPBuiltInCall STR only works with string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("y")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x5>");resultRow }(), resultSet, Exception("AOPBuiltInCall STR only works with string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("y")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x6>");resultRow }(), resultSet, AOPSimpleLiteral("\"", "2"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("x")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x6>");resultRow }(), resultSet, AOPSimpleLiteral("\"", "1"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x6>");resultRow }(), resultSet, Exception("AOPAddition only works with numeric input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("y")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x7>");resultRow }(), resultSet, AOPSimpleLiteral("\"", "2"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("x")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x7>");resultRow }(), resultSet, AOPSimpleLiteral("\"", "1"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x7>");resultRow }(), resultSet, Exception("AOPAddition only works with numeric input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p24732");resultSet.createVariable("x");resultSet.createVariable("#p24733");resultSet.createVariable("y");resultSet.createVariable("s");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSTR(AOPVariable("y")), { resultRow[resultSet.createVariable("#p24732")] = resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("#p24733")] = resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example/x8>");resultRow }(), resultSet, Exception("AOPBuiltInCall STR only works with string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/plus-2.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_md5_01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s24949");resultSet.createVariable("#p24949");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMD5(AOPVariable("l")), { resultRow[resultSet.createVariable("#s24949")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p24949")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "acbd18db4cc2f85cedef654fccc4a4d8"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s24967");resultSet.createVariable("#p24967");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMD5(AOPVariable("l")), { resultRow[resultSet.createVariable("#s24967")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p24967")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "acbd18db4cc2f85cedef654fccc4a4d8"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/md5-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_md5_02_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25029");resultSet.createVariable("#p25029");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMD5(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25029")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p25029")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "e7ada485d13b1decf628c9211bc3a97b"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25047");resultSet.createVariable("#p25047");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMD5(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25047")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p25047")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "e7ada485d13b1decf628c9211bc3a97b"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/md5-02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_sha1_01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25109");resultSet.createVariable("#p25109");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSHA1(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25109")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p25109")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25127");resultSet.createVariable("#p25127");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSHA1(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25127")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p25127")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "0beec7b5ea3f0fdbc95d0dd47f3c5bc275da8a33"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/sha1-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_sha1_02_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25189");resultSet.createVariable("#p25189");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSHA1(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25189")] = resultSet.createValue("<http://example.org/s8>");resultRow[resultSet.createVariable("#p25189")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"食\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "d46696735b6a09ff407bfc1a9407e008840db9c9"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25207");resultSet.createVariable("#p25207");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSHA1(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25207")] = resultSet.createValue("<http://example.org/s8>");resultRow[resultSet.createVariable("#p25207")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"食\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "d46696735b6a09ff407bfc1a9407e008840db9c9"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/sha1-02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_sha256_01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25269");resultSet.createVariable("#p25269");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSHA256(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25269")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p25269")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25287");resultSet.createVariable("#p25287");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSHA256(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25287")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p25287")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "2c26b46b68ffc68ff99b453c1d30413413422d706483bfa0f98a5e886266e7ae"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/sha256-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_sha256_02_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25349");resultSet.createVariable("#p25349");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSHA256(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25349")] = resultSet.createValue("<http://example.org/s8>");resultRow[resultSet.createVariable("#p25349")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"食\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "0fbe868d1df356ca9df7ebff346da3a56280e059a7ea81186ef885b140d254ee"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#s25367");resultSet.createVariable("#p25367");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallSHA256(AOPVariable("l")), { resultRow[resultSet.createVariable("#s25367")] = resultSet.createValue("<http://example.org/s8>");resultRow[resultSet.createVariable("#p25367")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"食\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", "0fbe868d1df356ca9df7ebff346da3a56280e059a7ea81186ef885b140d254ee"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/sha256-02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_minutes_01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25483");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMINUTES(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p25483")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(28))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25483");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMINUTES(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p25483")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(38))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25483");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMINUTES(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p25483")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(59))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25483");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMINUTES(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p25483")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25525");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMINUTES(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p25525")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(28))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25525");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMINUTES(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p25525")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(38))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25525");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMINUTES(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p25525")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(59))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25525");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMINUTES(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p25525")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/minutes-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    //marker XXX here
    @TestFactory
    fun testresources_sparql11_test_suite_functions_seconds_01_rq() = listOf(

            {
                val resultSet = ResultSet(ResultSetDictionary());
                resultSet.createVariable("s");
                resultSet.createVariable("#p25614");
                resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();
                MicroTest(
                        AOPBuildInCallSECONDS(
                                AOPVariable("date")
                        ), {
                    resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");
                    resultRow[resultSet.createVariable("#p25614")] = resultSet.createValue("<http://example.org/date>");
                    resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");
                    resultRow
                }(),
                        resultSet,
                        AOPDecimal(1.0)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                resultSet.createVariable("s");
                resultSet.createVariable("#p25614");
                resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();
                MicroTest(
                        AOPBuildInCallSECONDS(
                                AOPVariable("date")
                        ), {
                    resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");
                    resultRow[resultSet.createVariable("#p25614")] = resultSet.createValue("<http://example.org/date>");
                    resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");
                    resultRow
                }(),
                        resultSet,
                        AOPDecimal(2.0)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                resultSet.createVariable("s");
                resultSet.createVariable("#p25614");
                resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();
                MicroTest(
                        AOPBuildInCallSECONDS(
                                AOPVariable("date")
                        ), {
                    resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");
                    resultRow[resultSet.createVariable("#p25614")] = resultSet.createValue("<http://example.org/date>");
                    resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");
                    resultRow
                }(),
                        resultSet,
                        AOPDecimal(0.0)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                resultSet.createVariable("s");
                resultSet.createVariable("#p25614");
                resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();
                MicroTest(
                        AOPBuildInCallSECONDS(
                                AOPVariable("date")
                        ), {
                    resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");
                    resultRow[resultSet.createVariable("#p25614")] = resultSet.createValue("<http://example.org/date>");
                    resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");
                    resultRow
                }(),
                        resultSet,
                        AOPDecimal(3.0)
                )
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/seconds-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_hours_01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25698");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallHOURS(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p25698")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(11))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25698");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallHOURS(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p25698")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(15))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25698");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallHOURS(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p25698")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(23))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25698");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallHOURS(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p25698")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(1))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25740");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallHOURS(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p25740")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(11))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25740");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallHOURS(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p25740")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(15))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25740");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallHOURS(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p25740")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(23))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25740");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallHOURS(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p25740")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(1))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/hours-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_month_01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25829");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMONTH(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p25829")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(6))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25829");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMONTH(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p25829")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(12))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25829");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMONTH(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p25829")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(6))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25829");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMONTH(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p25829")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25871");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMONTH(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p25871")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(6))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25871");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMONTH(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p25871")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(12))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25871");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMONTH(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p25871")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(6))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25871");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallMONTH(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p25871")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/month-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_year_01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25960");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallYEAR(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p25960")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2010))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25960");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallYEAR(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p25960")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2010))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25960");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallYEAR(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p25960")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2008))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p25960");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallYEAR(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p25960")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2011))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26002");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallYEAR(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p26002")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2010))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26002");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallYEAR(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p26002")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2010))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26002");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallYEAR(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p26002")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2008))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26002");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallYEAR(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p26002")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(2011))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/year-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_day_01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26091");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDAY(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p26091")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(21))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26091");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDAY(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p26091")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(21))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26091");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDAY(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p26091")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(20))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26091");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDAY(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p26091")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(1))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26133");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDAY(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p26133")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(21))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26133");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDAY(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p26133")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(21))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26133");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDAY(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p26133")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(20))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26133");resultSet.createVariable("date");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDAY(AOPVariable("date")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p26133")] = resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")] = resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow }(), resultSet, AOPInteger(1))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/day-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_timezone_01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26222");resultSet.createVariable("date");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallTIMEZONE(AOPVariable("date")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p26222")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")]=resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,AOPSimpleLiteral("\"","\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26222");resultSet.createVariable("date");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallTIMEZONE(AOPVariable("date")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p26222")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")]=resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,AOPSimpleLiteral("\"","\"-PT8H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26222");resultSet.createVariable("date");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallTIMEZONE(AOPVariable("date")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p26222")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")]=resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,AOPSimpleLiteral("\"","\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26222");resultSet.createVariable("date");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallTIMEZONE(AOPVariable("date")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p26222")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")]=resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,AOPSimpleLiteral("\"",""))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/timezone-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_tz_01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26306");resultSet.createVariable("date");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallTZ(AOPVariable("date")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d1>");resultRow[resultSet.createVariable("#p26306")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")]=resultSet.createValue("\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,AOPSimpleLiteral("\"","\"Z\""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26306");resultSet.createVariable("date");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallTZ(AOPVariable("date")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d2>");resultRow[resultSet.createVariable("#p26306")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")]=resultSet.createValue("\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,AOPSimpleLiteral("\"","\"-08:00\""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26306");resultSet.createVariable("date");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallTZ(AOPVariable("date")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d3>");resultRow[resultSet.createVariable("#p26306")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")]=resultSet.createValue("\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,AOPSimpleLiteral("\"","\"Z\""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("#p26306");resultSet.createVariable("date");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallTZ(AOPVariable("date")),{resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example.org/d4>");resultRow[resultSet.createVariable("#p26306")]=resultSet.createValue("<http://example.org/date>");resultRow[resultSet.createVariable("date")]=resultSet.createValue("\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,AOPSimpleLiteral("\"","\"\""))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/tz-01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_bnode01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE1(AOPVariable("s2")),{resultRow[resultSet.createVariable("a")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")]=resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,AOPBnode("26410\"foo\""))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("b2");resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE1(AOPVariable("s1")),{resultRow[resultSet.createVariable("b2")]=resultSet.createValue("_:26410\"foo\"");resultRow[resultSet.createVariable("a")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")]=resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,AOPBnode("26404\"foo\""))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE1(AOPVariable("s2")),{resultRow[resultSet.createVariable("a")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")]=resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,AOPBnode("26410\"foo\""))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("b2");resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE1(AOPVariable("s1")),{resultRow[resultSet.createVariable("b2")]=resultSet.createValue("_:26410\"foo\"");resultRow[resultSet.createVariable("a")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")]=resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("\"foo\"");resultRow}(),resultSet,AOPBnode("26404\"BAZ\""))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"foo\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"bar\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE1(AOPVariable("s2")),{resultRow[resultSet.createVariable("a")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")]=resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,AOPBnode("26410\"BAZ\""))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("b2");resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE1(AOPVariable("s1")),{resultRow[resultSet.createVariable("b2")]=resultSet.createValue("_:26410\"BAZ\"");resultRow[resultSet.createVariable("a")]=resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")]=resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,AOPBnode("26404\"foo\""))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE1(AOPVariable("s2")),{resultRow[resultSet.createVariable("a")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")]=resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,AOPBnode("26410\"BAZ\""))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("b2");resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE1(AOPVariable("s1")),{resultRow[resultSet.createVariable("b2")]=resultSet.createValue("_:26410\"BAZ\"");resultRow[resultSet.createVariable("a")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")]=resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")]=resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")]=resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")]=resultSet.createValue("\"BAZ\"");resultRow}(),resultSet,AOPBnode("26404\"BAZ\""))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"BAZ\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"食べ物\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"100%\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"foo\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"bar\"@en");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"BAZ\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"食べ物\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"100%\"");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1")), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("a");resultSet.createVariable("#p26455");resultSet.createVariable("s1");resultSet.createVariable("b");resultSet.createVariable("#p26456");resultSet.createVariable("s2");
                val resultRow = resultSet.createResultRow();MicroTest(AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))), { resultRow[resultSet.createVariable("a")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26455")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s1")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow[resultSet.createVariable("b")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("#p26456")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("s2")] = resultSet.createValue("\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/bnode01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_bnode02_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE0(),{resultRow}(),resultSet,AOPBnode("2921429239"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("b2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE0(),{resultRow[resultSet.createVariable("b2")]=resultSet.createValue("_:2921429239");resultRow}(),resultSet,AOPBnode("2920929241"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE0(),{resultRow}(),resultSet,AOPBnode("2924729265"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("b2");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallBNODE0(),{resultRow[resultSet.createVariable("b2")]=resultSet.createValue("_:2924729265");resultRow}(),resultSet,AOPBnode("2925429267"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/bnode02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_now01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallNOW(),{resultRow}(),resultSet,AOPDateTime(""2020-02-22T08:57:37Z"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("n");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDATATYPE(AOPVariable("n")),{resultRow[resultSet.createVariable("n")]=resultSet.createValue("\"2020-02-22T08:57:37Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>");resultRow}(),resultSet,Exception("AOPBuiltInCall DATATYPE only works with typed string input"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/now01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_iri01_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIRI(AOPSimpleLiteral("\"","iri")),{resultRow}(),resultSet,AOPIri("iri"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("iri");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallURI(AOPSimpleLiteral("\"","uri")),{resultRow[resultSet.createVariable("iri")]=resultSet.createValue("<iri>");resultRow}(),resultSet,AOPIri("uri"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/iri01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_if01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"123\"");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"123\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s1>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"123\"");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"日本語\"@ja");resultRow }(), resultSet, AOPSimpleLiteral("\"", "ja"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"日本語\"@ja");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s2>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"日本語\"@ja");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"english\"@en");resultRow }(), resultSet, AOPSimpleLiteral("\"", "en"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"english\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s3>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"english\"@en");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"français\"@fr");resultRow }(), resultSet, AOPSimpleLiteral("\"", "fr"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"français\"@fr");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s4>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"français\"@fr");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s5>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s6>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"def\"^^<http://www.w3.org/2001/XMLSchema#string>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallLANG(AOPVariable("o")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPSimpleLiteral("\"", ""))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("s");resultSet.createVariable("p");resultSet.createVariable("o");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)), { resultRow[resultSet.createVariable("s")] = resultSet.createValue("<http://example.org/s7>");resultRow[resultSet.createVariable("p")] = resultSet.createValue("<http://example.org/str>");resultRow[resultSet.createVariable("o")] = resultSet.createValue("\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/if01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_functions_if02_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPDivision(AOPInteger(0), AOPInteger(1)), { resultRow }(), resultSet, AOPInteger(0))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallIF(AOPDivision(AOPInteger(0), AOPInteger(1)), AOPBoolean(false), AOPBoolean(true)), { resultRow }(), resultSet, Exception("AOPBuiltInCall IF only works with boolean condition"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/functions/if02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_grouping_group03_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p30533");resultSet.createVariable("v");resultSet.createVariable("#p30534");resultSet.createVariable("w");resultSet.createVariable("s");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("v"))),{resultRow[resultSet.createVariable("#p30533")]=resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("v")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p30534")]=resultSet.createValue("<http://example/q>");resultRow[resultSet.createVariable("w")]=resultSet.createValue("\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example/s1>");resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p30533");resultSet.createVariable("v");resultSet.createVariable("#p30534");resultSet.createVariable("w");resultSet.createVariable("s");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("v"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("#p30533"));resultSet.setUndefValue(resultRow,resultSet.createVariable("v"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p30534"));resultSet.setUndefValue(resultRow,resultSet.createVariable("w"));resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultRow}(),resultSet,AOPInteger(1))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p30533");resultSet.createVariable("v");resultSet.createVariable("#p30534");resultSet.createVariable("w");resultSet.createVariable("s");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("v"))),{resultRow[resultSet.createVariable("#p30533")]=resultSet.createValue("<http://example/p>");resultRow[resultSet.createVariable("v")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultSet.setUndefValue(resultRow,resultSet.createVariable("#p30534"));resultSet.setUndefValue(resultRow,resultSet.createVariable("w"));resultRow[resultSet.createVariable("s")]=resultSet.createValue("<http://example/s2>");resultRow}(),resultSet,AOPInteger(2))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p30533");resultSet.createVariable("v");resultSet.createVariable("#p30534");resultSet.createVariable("w");resultSet.createVariable("s");val resultRow = resultSet.createResultRow();MicroTest(AOPAggregation(Aggregation.SAMPLE,false,arrayOf(AOPVariable("v"))),{resultSet.setUndefValue(resultRow,resultSet.createVariable("#p30533"));resultSet.setUndefValue(resultRow,resultSet.createVariable("v"));resultSet.setUndefValue(resultRow,resultSet.createVariable("#p30534"));resultSet.setUndefValue(resultRow,resultSet.createVariable("w"));resultSet.setUndefValue(resultRow,resultSet.createVariable("s"));resultRow}(),resultSet,AOPInteger(2))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/grouping/group03.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_project_expression_projexp01_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p32765");resultSet.createVariable("y");resultSet.createVariable("#p32766");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("y"), AOPVariable("z")), { resultRow[resultSet.createVariable("#p32765")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p32766")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p32765");resultSet.createVariable("y");resultSet.createVariable("#p32766");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("y"), AOPVariable("z")), { resultRow[resultSet.createVariable("#p32765")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p32766")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p32804");resultSet.createVariable("y");resultSet.createVariable("#p32805");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("y"), AOPVariable("z")), { resultRow[resultSet.createVariable("#p32804")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p32805")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPBoolean(true))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p32804");resultSet.createVariable("y");resultSet.createVariable("#p32805");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPEQ(AOPVariable("y"), AOPVariable("z")), { resultRow[resultSet.createVariable("#p32804")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p32805")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPBoolean(false))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/project-expression/projexp01.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_project_expression_projexp02_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p32913");resultSet.createVariable("y");resultSet.createVariable("#p32914");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("z"), AOPVariable("y")), { resultRow[resultSet.createVariable("#p32913")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p32914")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p32941");resultSet.createVariable("y");resultSet.createVariable("#p32942");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("z"), AOPVariable("y")), { resultRow[resultSet.createVariable("#p32941")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p32942")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/project-expression/projexp02.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_project_expression_projexp03_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p33052");resultSet.createVariable("y");resultSet.createVariable("#p33053");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("z"), AOPVariable("y")), { resultRow[resultSet.createVariable("#p33052")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p33053")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("sum");resultSet.createVariable("#p33052");resultSet.createVariable("y");resultSet.createVariable("#p33053");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPMultiplication(AOPVariable("sum"), AOPInteger(2)), { resultRow[resultSet.createVariable("sum")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p33052")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p33053")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPInteger(6))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p33097");resultSet.createVariable("y");resultSet.createVariable("#p33098");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("z"), AOPVariable("y")), { resultRow[resultSet.createVariable("#p33097")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p33098")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPInteger(3))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("sum");resultSet.createVariable("#p33097");resultSet.createVariable("y");resultSet.createVariable("#p33098");resultSet.createVariable("z");resultSet.createVariable("x");
                val resultRow = resultSet.createResultRow();MicroTest(AOPMultiplication(AOPVariable("sum"), AOPInteger(2)), { resultRow[resultSet.createVariable("sum")] = resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p33097")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p33098")] = resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("z")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow }(), resultSet, AOPInteger(6))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/project-expression/projexp03.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_project_expression_projexp04_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("x");resultSet.createVariable("#p33209");resultSet.createVariable("y");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("y")), { resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow[resultSet.createVariable("#p33209")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("x");resultSet.createVariable("#p33209");resultSet.createVariable("y");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("y")), { resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow[resultSet.createVariable("#p33209")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(4))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("x");resultSet.createVariable("#p33239");resultSet.createVariable("y");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("y")), { resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow[resultSet.createVariable("#p33239")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(2))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("x");resultSet.createVariable("#p33239");resultSet.createVariable("y");
                val resultRow = resultSet.createResultRow();MicroTest(AOPAddition(AOPVariable("y"), AOPVariable("y")), { resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow[resultSet.createVariable("#p33239")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")] = resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, AOPInteger(4))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/project-expression/projexp04.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_project_expression_projexp05_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("x");resultSet.createVariable("#p33324");resultSet.createVariable("l");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDATATYPE(AOPVariable("l")),{resultRow[resultSet.createVariable("x")]=resultSet.createValue("<http://www.example.org/instance#a>");resultRow[resultSet.createVariable("#p33324")]=resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("l")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow}(),resultSet,Exception("AOPBuiltInCall DATATYPE only works with typed string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("x");resultSet.createVariable("#p33324");resultSet.createVariable("l");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDATATYPE(AOPVariable("l")),{resultRow[resultSet.createVariable("x")]=resultSet.createValue("<http://www.example.org/instance#a>");resultRow[resultSet.createVariable("#p33324")]=resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("l")]=resultSet.createValue("<http://www.example.org/schema#a>");resultRow}(),resultSet,Exception("AOPBuiltInCall DATATYPE only works with typed string input"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/project-expression/projexp05.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_project_expression_projexp06_rq() = listOf(
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("m");resultSet.createVariable("x");resultSet.createVariable("#p33401");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDATATYPE(AOPVariable("m")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("m"));resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow[resultSet.createVariable("#p33401")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, Exception("AOPBuiltInCall DATATYPE only works with typed string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("m");resultSet.createVariable("x");resultSet.createVariable("#p33425");resultSet.createVariable("l");
                val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDATATYPE(AOPVariable("m")), { resultSet.setUndefValue(resultRow, resultSet.createVariable("m"));resultRow[resultSet.createVariable("x")] = resultSet.createValue("<http://www.example.org/instance#a>");resultRow[resultSet.createVariable("#p33425")] = resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("l")] = resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow }(), resultSet, Exception("AOPBuiltInCall DATATYPE only works with typed string input"))
            }(),
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/project-expression/projexp06.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_project_expression_projexp07_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p33520");resultSet.createVariable("y");resultSet.createVariable("#p33521");resultSet.createVariable("l");resultSet.createVariable("x");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDATATYPE(AOPVariable("l")),{resultRow[resultSet.createVariable("#p33520")]=resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")]=resultSet.createValue("\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("#p33521")]=resultSet.createValue("<http://www.example.org/schema#q>");resultRow[resultSet.createVariable("l")]=resultSet.createValue("\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultRow[resultSet.createVariable("x")]=resultSet.createValue("<http://www.example.org/instance#a>");resultRow}(),resultSet,Exception("AOPBuiltInCall DATATYPE only works with typed string input"))}()*/
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p33520");resultSet.createVariable("y");resultSet.createVariable("#p33521");resultSet.createVariable("l");resultSet.createVariable("x");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallDATATYPE(AOPVariable("l")),{resultRow[resultSet.createVariable("#p33520")]=resultSet.createValue("<http://www.example.org/schema#p>");resultRow[resultSet.createVariable("y")]=resultSet.createValue("\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>");resultSet.setUndefValue(resultRow,resultSet.createVariable("#p33521"));resultSet.setUndefValue(resultRow,resultSet.createVariable("l"));resultRow[resultSet.createVariable("x")]=resultSet.createValue("<http://www.example.org/instance#b>");resultRow}(),resultSet,Exception("AOPBuiltInCall DATATYPE only works with typed string input"))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/project-expression/projexp07.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }

    @TestFactory
    fun testresources_sparql11_test_suite_subquery_sq12_rq() = listOf(
            /*{val resultSet = ResultSet(ResultSetDictionary());resultSet.createVariable("#p35052");resultSet.createVariable("F");resultSet.createVariable("#p35053");resultSet.createVariable("L");resultSet.createVariable("P");val resultRow = resultSet.createResultRow();MicroTest(AOPBuildInCallCONCAT(AOPVariable("F"),AOPSimpleLiteral("\""," ")),{resultRow[resultSet.createVariable("#p35052")]=resultSet.createValue("<http://xmlns.com/foaf/0.1/firstName>");resultRow[resultSet.createVariable("F")]=resultSet.createValue("\"John\"");resultRow[resultSet.createVariable("#p35053")]=resultSet.createValue("<http://xmlns.com/foaf/0.1/lastName>");resultRow[resultSet.createVariable("L")]=resultSet.createValue("\"Doe\"");resultRow[resultSet.createVariable("P")]=resultSet.createValue("<http://p1>");resultRow}(),resultSet,AOPSimpleLiteral("\"","John "))}()*/
            {
                val resultSet = ResultSet(ResultSetDictionary());
                val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())
            }()
    ).mapIndexed { index, data ->
        DynamicTest.dynamicTest("test->resources/sparql11-test-suite/subquery/sq12.rq<-$index") {
            try {
                val output = data.input.calculate(data.resultSet, data.resultRow)
                assertTrue(data.expected is AOPConstant)
                if (!data.expected.equals(output)) {
                    println(data.resultRow)
                    println((output as AOPConstant).valueToString())
                    println((data.expected as AOPConstant).valueToString())
                }
                assertTrue(data.expected.equals(output))
            } catch (e: Throwable) {
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
