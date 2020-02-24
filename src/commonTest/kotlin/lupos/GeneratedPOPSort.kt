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
                                "#p35310",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35310" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35310" to "<http://www.example.org/schema#p>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35310",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35310" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35310" to "<http://www.example.org/schema#p>",
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
                                "#p35340",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35340" to "<http://www.example.org/schema#p>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35340" to "<http://www.example.org/schema#p>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35340",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35340" to "<http://www.example.org/schema#p>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35340" to "<http://www.example.org/schema#p>",
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
                                "#p37067",
                                "#o37067"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37067" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37067" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37067" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37067" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37067" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37067" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37067" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37067" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37067",
                            "#o37067"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37067" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37067" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37067" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37067" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37067" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37067" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37067" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37067" to "<http://www.example.orgOrder>"
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
                                "#p37064",
                                "L",
                                "#p37065",
                                "#_36993",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36970",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36971",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36972",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36973",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36974",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37065" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36975",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#p37064",
                            "L",
                            "#p37065",
                            "#_36993",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36970",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36973",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36974",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36971",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36975",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37064" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37065" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36972",
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
                                "#p37088",
                                "#o37088"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37088" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37088" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37088" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37088" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37088" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37088" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37088" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37088" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37088",
                            "#o37088"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37088" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37088" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37088" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37088" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37088" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37088" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37088" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37088" to "<http://www.example.orgOrder>"
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
                                "#p37085",
                                "L",
                                "#p37086",
                                "#_36993",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36970",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36971",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36972",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36973",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36974",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37086" to "<http://www.example.orghasItem>",
                                    "#_36993" to "_:_36975",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#p37085",
                            "L",
                            "#p37086",
                            "#_36993",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36970",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36973",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36974",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36971",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36975",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37085" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37086" to "<http://www.example.orghasItem>",
                                "#_36993" to "_:_36972",
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
                                "#p37432",
                                "#o37432"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37432" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37432" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37432" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37432" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37432" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37432" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37432" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37432" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37432",
                            "#o37432"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37432" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37432" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37432" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37432" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37432" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37432" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37432" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37432" to "<http://www.example.orgOrder>"
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
                                "#p37429",
                                "L",
                                "#p37430",
                                "#_37358",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37335",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37336",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37337",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37338",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37339",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37430" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37340",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#p37429",
                            "L",
                            "#p37430",
                            "#_37358",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37335",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37338",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37339",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37336",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37340",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37429" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37430" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37337",
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
                                "#p37453",
                                "#o37453"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37453" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37453" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37453" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37453" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37453" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37453" to "<http://www.example.orgOrder>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37453" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                    "#o37453" to "<http://www.example.orgOrder>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37453",
                            "#o37453"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37453" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37453" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37453" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37453" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37453" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37453" to "<http://www.example.orgOrder>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37453" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "#o37453" to "<http://www.example.orgOrder>"
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
                                "#p37450",
                                "L",
                                "#p37451",
                                "#_37358",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37335",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pizza\"",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37336",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Wine\"",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37337",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Ice Cream\"",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37338",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Pasta\"",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37339",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                    "L" to "\"Soft Drink\"",
                                    "#p37451" to "<http://www.example.orghasItem>",
                                    "#_37358" to "_:_37340",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#p37450",
                            "L",
                            "#p37451",
                            "#_37358",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37335",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Ice Cream\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37338",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pasta\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37339",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Pizza\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37336",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Soft Drink\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37340",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#p37450" to "<http://www.w3.org/2000/01/rdf-schema#label>",
                                "L" to "\"Wine\"",
                                "#p37451" to "<http://www.example.orghasItem>",
                                "#_37358" to "_:_37337",
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
