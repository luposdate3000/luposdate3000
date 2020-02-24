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


class GeneratedPOPLimitTest {
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
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPLimit(
                        dictionary,
                        2,
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37066",
                                "#o37066"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37066" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37066" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37066" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37066" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37066" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37066" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37066",
                            "#o37066"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37066" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37066" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37066" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37066" to "<http://www.example.orgOrder>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPLimit(
                        dictionary,
                        2,
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37087",
                                "#o37087"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37087" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37087" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37087" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37087" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37087" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37087" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37087",
                            "#o37087"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37087" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37087" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37087" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37087" to "<http://www.example.orgOrder>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPLimit(
                        dictionary,
                        2,
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37431",
                                "#o37431"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37431" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37431" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37431" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37431" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37431" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37431" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37431",
                            "#o37431"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37431" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37431" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37431" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37431" to "<http://www.example.orgOrder>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPLimit(
                        dictionary,
                        2,
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37452",
                                "#o37452"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37452" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37452" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37452" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37452" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37452" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37452" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37452",
                            "#o37452"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37452" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37452" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37452" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37452" to "<http://www.example.orgOrder>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
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
