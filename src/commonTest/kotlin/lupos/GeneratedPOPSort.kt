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


class GeneratedPOPSortTest {
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
                    POPSort(
                        dictionary,
                        AOPVariable("O12"),
                        true,
                        POPValues(dictionary, listOf(
                                "C",
                                "O12"
                            ), listOf(
                                mutableMapOf(
                                    "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "O12"
                        ), listOf(
                            mutableMapOf(
                                "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/p7>",
                                    "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/p7>",
                                    "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/p7>",
                                    "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/p7>",
                                    "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/p7>",
                                    "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s7>",
                                    "p" to "<http://example.org/p7>",
                                    "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
                        POPValues(dictionary, listOf(
                                "sum",
                                "#p26417",
                                "x",
                                "#p26418",
                                "y",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"a\"",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "_:b",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x2>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "<http://example/a>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x5>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x8>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "#p26417",
                            "x",
                            "#p26418",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"a\"",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "_:b",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "<http://example/a>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x8>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("y"),
                        true,
                        POPValues(dictionary, listOf(
                                "sum",
                                "#p26417",
                                "x",
                                "#p26418",
                                "y",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"a\"",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "_:b",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x2>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "<http://example/a>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x8>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x5>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "#p26417",
                            "x",
                            "#p26418",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "_:b",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "<http://example/a>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"a\"",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x8>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("x"),
                        true,
                        POPValues(dictionary, listOf(
                                "sum",
                                "#p26417",
                                "x",
                                "#p26418",
                                "y",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "_:b",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x2>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "<http://example/a>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"a\"",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x8>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26417" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26418" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x5>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "#p26417",
                            "x",
                            "#p26418",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x8>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26417" to "<http://example/p>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "\"a\"",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "<http://example/a>",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26417" to "<http://example/p>",
                                "x" to "_:b",
                                "#p26418" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
                        POPValues(dictionary, listOf(
                                "sum",
                                "#p26590",
                                "x",
                                "#p26591",
                                "y",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"a\"",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "_:b",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x2>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "<http://example/a>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x5>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x8>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "#p26590",
                            "x",
                            "#p26591",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"a\"",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "_:b",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "<http://example/a>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x8>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("y"),
                        true,
                        POPValues(dictionary, listOf(
                                "sum",
                                "#p26590",
                                "x",
                                "#p26591",
                                "y",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"a\"",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "_:b",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x2>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "<http://example/a>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x5>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x8>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "#p26590",
                            "x",
                            "#p26591",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "_:b",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "<http://example/a>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"a\"",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x8>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("x"),
                        true,
                        POPValues(dictionary, listOf(
                                "sum",
                                "#p26590",
                                "x",
                                "#p26591",
                                "y",
                                "s"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "_:b",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x2>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "<http://example/a>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"",
                                    "s" to "<http://example/x3>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"a\"",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x1>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x6>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"",
                                    "s" to "<http://example/x7>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x5>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "#p26590" to "<http://example/p>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26591" to "<http://example/q>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x8>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "#p26590",
                            "x",
                            "#p26591",
                            "y",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x6>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"",
                                "s" to "<http://example/x7>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x8>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x5>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "\"a\"",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x1>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "<http://example/a>",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x3>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "#p26590" to "<http://example/p>",
                                "x" to "_:b",
                                "#p26591" to "<http://example/q>",
                                "y" to "\"1\"",
                                "s" to "<http://example/x2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "p2",
                                "o2",
                                "o"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\"",
                                    "o" to "<http://example.org/s2>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "p2" to null,
                                    "o2" to null,
                                    "o" to "_:o6"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "p2",
                            "o2",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\"",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "p2" to null,
                                "o2" to null,
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
                        POPValues(dictionary, listOf(
                                "sum",
                                "x",
                                "#p35307",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35307" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35307" to "<http://www.example.org/schema#p>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35307",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35307" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35307" to "<http://www.example.org/schema#p>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
                        POPValues(dictionary, listOf(
                                "sum",
                                "x",
                                "#p35337",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35337" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35337" to "<http://www.example.org/schema#p>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35337",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35337" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35337" to "<http://www.example.org/schema#p>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("O"),
                        true,
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37064",
                                "#o37064"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37064" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37064" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37064" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37064" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37064" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37064" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37064" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37064" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37064",
                            "#o37064"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37064" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37064" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37064" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37064" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37064" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37064" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37064" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37064" to "<http://www.example.orgOrder>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("L"),
                        true,
                        POPValues(dictionary, listOf(
                                "#p37061",
                                "L",
                                "#p37062",
                                "#_36990",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37062" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36967",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37062" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36968",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37062" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36969",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37062" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36970",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37062" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36971",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37062" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36972",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#p37061",
                            "L",
                            "#p37062",
                            "#_36990",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37062" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36967",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37062" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36970",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37062" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36971",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37062" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36968",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37062" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36972",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37061" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37062" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36969",
                                "O" to "<http://www.example.orgorder1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("O"),
                        true,
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37085",
                                "#o37085"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37085" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37085" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37085" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37085" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37085" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37085" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37085" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37085" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37085",
                            "#o37085"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37085" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37085" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37085" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37085" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37085" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37085" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37085" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37085" to "<http://www.example.orgOrder>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("L"),
                        true,
                        POPValues(dictionary, listOf(
                                "#p37082",
                                "L",
                                "#p37083",
                                "#_36990",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37083" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36967",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37083" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36968",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37083" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36969",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37083" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36970",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37083" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36971",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37083" to "<http://www.example.orghasItem>",
                                    "#_36990" to "_:_36972",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#p37082",
                            "L",
                            "#p37083",
                            "#_36990",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37083" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36967",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37083" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36970",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37083" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36971",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37083" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36968",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37083" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36972",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37082" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37083" to "<http://www.example.orghasItem>",
                                "#_36990" to "_:_36969",
                                "O" to "<http://www.example.orgorder1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("O"),
                        true,
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37429",
                                "#o37429"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37429" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37429" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37429" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37429" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37429" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37429" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37429" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37429" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37429",
                            "#o37429"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37429" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37429" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37429" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37429" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37429" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37429" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37429" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37429" to "<http://www.example.orgOrder>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("L"),
                        true,
                        POPValues(dictionary, listOf(
                                "#p37426",
                                "L",
                                "#p37427",
                                "#_37355",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37427" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37332",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37427" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37333",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37427" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37334",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37427" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37335",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37427" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37336",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37427" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37337",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#p37426",
                            "L",
                            "#p37427",
                            "#_37355",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37427" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37332",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37427" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37335",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37427" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37336",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37427" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37333",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37427" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37337",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37426" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37427" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37334",
                                "O" to "<http://www.example.orgorder1>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("O"),
                        true,
                        POPValues(dictionary, listOf(
                                "O",
                                "#p37450",
                                "#o37450"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37450" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37450" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37450" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37450" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37450" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37450" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37450" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37450" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37450",
                            "#o37450"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37450" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37450" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37450" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37450" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37450" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37450" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37450" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37450" to "<http://www.example.orgOrder>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("L"),
                        true,
                        POPValues(dictionary, listOf(
                                "#p37447",
                                "L",
                                "#p37448",
                                "#_37355",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37448" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37332",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37448" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37333",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37448" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37334",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37448" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37335",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37448" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37336",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37448" to "<http://www.example.orghasItem>",
                                    "#_37355" to "_:_37337",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#p37447",
                            "L",
                            "#p37448",
                            "#_37355",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37448" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37332",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37448" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37335",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37448" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37336",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37448" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37333",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37448" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37337",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37447" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37448" to "<http://www.example.orghasItem>",
                                "#_37355" to "_:_37334",
                                "O" to "<http://www.example.orgorder1>"
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
