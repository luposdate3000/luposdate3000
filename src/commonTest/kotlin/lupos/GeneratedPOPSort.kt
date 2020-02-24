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


class GeneratedPOPSortTest {
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
                                "s",
                                "#p26389",
                                "x",
                                "#p26390",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x1>",
                                    "#p26389" to null,
                                    "x" to "\"a\"",
                                    "#p26390" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x2>",
                                    "#p26389" to null,
                                    "x" to "_:b",
                                    "#p26390" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x3>",
                                    "#p26389" to null,
                                    "x" to "<http://example/a>",
                                    "#p26390" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example/x5>",
                                    "#p26389" to null,
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x6>",
                                    "#p26389" to null,
                                    "x" to "\"1\"",
                                    "#p26390" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x7>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26390" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x8>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "s",
                            "#p26389",
                            "x",
                            "#p26390",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x1>",
                                "#p26389" to null,
                                "x" to "\"a\"",
                                "#p26390" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x2>",
                                "#p26389" to null,
                                "x" to "_:b",
                                "#p26390" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x3>",
                                "#p26389" to null,
                                "x" to "<http://example/a>",
                                "#p26390" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x6>",
                                "#p26389" to null,
                                "x" to "\"1\"",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x7>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x8>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example/x5>",
                                "#p26389" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "s",
                                "#p26389",
                                "x",
                                "#p26390",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x1>",
                                    "#p26389" to null,
                                    "x" to "\"a\"",
                                    "#p26390" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x2>",
                                    "#p26389" to null,
                                    "x" to "_:b",
                                    "#p26390" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x3>",
                                    "#p26389" to null,
                                    "x" to "<http://example/a>",
                                    "#p26390" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x6>",
                                    "#p26389" to null,
                                    "x" to "\"1\"",
                                    "#p26390" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x7>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26390" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x8>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example/x5>",
                                    "#p26389" to null,
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "s",
                            "#p26389",
                            "x",
                            "#p26390",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x2>",
                                "#p26389" to null,
                                "x" to "_:b",
                                "#p26390" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x3>",
                                "#p26389" to null,
                                "x" to "<http://example/a>",
                                "#p26390" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x1>",
                                "#p26389" to null,
                                "x" to "\"a\"",
                                "#p26390" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x6>",
                                "#p26389" to null,
                                "x" to "\"1\"",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x7>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x8>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example/x5>",
                                "#p26389" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "s",
                                "#p26389",
                                "x",
                                "#p26390",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x2>",
                                    "#p26389" to null,
                                    "x" to "_:b",
                                    "#p26390" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x3>",
                                    "#p26389" to null,
                                    "x" to "<http://example/a>",
                                    "#p26390" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x1>",
                                    "#p26389" to null,
                                    "x" to "\"a\"",
                                    "#p26390" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x6>",
                                    "#p26389" to null,
                                    "x" to "\"1\"",
                                    "#p26390" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x7>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26390" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x8>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "s" to "<http://example/x4>",
                                    "#p26389" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "s" to "<http://example/x5>",
                                    "#p26389" to null,
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26390" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "s",
                            "#p26389",
                            "x",
                            "#p26390",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x6>",
                                "#p26389" to null,
                                "x" to "\"1\"",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "s" to "<http://example/x4>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x7>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x8>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "s" to "<http://example/x5>",
                                "#p26389" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x1>",
                                "#p26389" to null,
                                "x" to "\"a\"",
                                "#p26390" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x3>",
                                "#p26389" to null,
                                "x" to "<http://example/a>",
                                "#p26390" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x2>",
                                "#p26389" to null,
                                "x" to "_:b",
                                "#p26390" to null,
                                "y" to "\"1\""
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
                                "s",
                                "#p26562",
                                "x",
                                "#p26563",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x1>",
                                    "#p26562" to null,
                                    "x" to "\"a\"",
                                    "#p26563" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x2>",
                                    "#p26562" to null,
                                    "x" to "_:b",
                                    "#p26563" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x3>",
                                    "#p26562" to null,
                                    "x" to "<http://example/a>",
                                    "#p26563" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x4>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x5>",
                                    "#p26562" to null,
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x6>",
                                    "#p26562" to null,
                                    "x" to "\"1\"",
                                    "#p26563" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x7>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26563" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x8>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "s",
                            "#p26562",
                            "x",
                            "#p26563",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x1>",
                                "#p26562" to null,
                                "x" to "\"a\"",
                                "#p26563" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x2>",
                                "#p26562" to null,
                                "x" to "_:b",
                                "#p26563" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x3>",
                                "#p26562" to null,
                                "x" to "<http://example/a>",
                                "#p26563" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x4>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x5>",
                                "#p26562" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x6>",
                                "#p26562" to null,
                                "x" to "\"1\"",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x7>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x8>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "s",
                                "#p26562",
                                "x",
                                "#p26563",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x1>",
                                    "#p26562" to null,
                                    "x" to "\"a\"",
                                    "#p26563" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x2>",
                                    "#p26562" to null,
                                    "x" to "_:b",
                                    "#p26563" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x3>",
                                    "#p26562" to null,
                                    "x" to "<http://example/a>",
                                    "#p26563" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x4>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x5>",
                                    "#p26562" to null,
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x6>",
                                    "#p26562" to null,
                                    "x" to "\"1\"",
                                    "#p26563" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x7>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26563" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x8>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "s",
                            "#p26562",
                            "x",
                            "#p26563",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x2>",
                                "#p26562" to null,
                                "x" to "_:b",
                                "#p26563" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x3>",
                                "#p26562" to null,
                                "x" to "<http://example/a>",
                                "#p26563" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x1>",
                                "#p26562" to null,
                                "x" to "\"a\"",
                                "#p26563" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x6>",
                                "#p26562" to null,
                                "x" to "\"1\"",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x7>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x4>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x5>",
                                "#p26562" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x8>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "s",
                                "#p26562",
                                "x",
                                "#p26563",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x2>",
                                    "#p26562" to null,
                                    "x" to "_:b",
                                    "#p26563" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x3>",
                                    "#p26562" to null,
                                    "x" to "<http://example/a>",
                                    "#p26563" to null,
                                    "y" to "\"1\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x1>",
                                    "#p26562" to null,
                                    "x" to "\"a\"",
                                    "#p26563" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x6>",
                                    "#p26562" to null,
                                    "x" to "\"1\"",
                                    "#p26563" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x7>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26563" to null,
                                    "y" to "\"2\""
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x4>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x5>",
                                    "#p26562" to null,
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to null,
                                    "s" to "<http://example/x8>",
                                    "#p26562" to null,
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "#p26563" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "s",
                            "#p26562",
                            "x",
                            "#p26563",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x6>",
                                "#p26562" to null,
                                "x" to "\"1\"",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x4>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x7>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x8>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x5>",
                                "#p26562" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x1>",
                                "#p26562" to null,
                                "x" to "\"a\"",
                                "#p26563" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x3>",
                                "#p26562" to null,
                                "x" to "<http://example/a>",
                                "#p26563" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "sum" to null,
                                "s" to "<http://example/x2>",
                                "#p26562" to null,
                                "x" to "_:b",
                                "#p26563" to null,
                                "y" to "\"1\""
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
                                "#p35280",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35280" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35280" to null,
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum",
                            "x",
                            "#p35280",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35280" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35280" to null,
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
                                "#p35310",
                                "y"
                            ), listOf(
                                mutableMapOf(
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35310" to null,
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "x" to "<http://www.example.org/instance#a>",
                                    "#p35310" to null,
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
                                "#p35310" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35310" to null,
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
                                "#p37037",
                                "#o37037"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37037" to null,
                                    "#o37037" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37037" to null,
                                    "#o37037" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37037" to null,
                                    "#o37037" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37037" to null,
                                    "#o37037" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37037",
                            "#o37037"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37037" to null,
                                "#o37037" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37037" to null,
                                "#o37037" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37037" to null,
                                "#o37037" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37037" to null,
                                "#o37037" to null
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
                                "#_36963",
                                "#p37034",
                                "L",
                                "#p37035",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#_36963" to "_:_36940",
                                    "#p37034" to null,
                                    "L" to "\"Ice Cream\"",
                                    "#p37035" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36941",
                                    "#p37034" to null,
                                    "L" to "\"Pizza\"",
                                    "#p37035" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36942",
                                    "#p37034" to null,
                                    "L" to "\"Wine\"",
                                    "#p37035" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36943",
                                    "#p37034" to null,
                                    "L" to "\"Ice Cream\"",
                                    "#p37035" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36944",
                                    "#p37034" to null,
                                    "L" to "\"Pasta\"",
                                    "#p37035" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36945",
                                    "#p37034" to null,
                                    "L" to "\"Soft Drink\"",
                                    "#p37035" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#_36963",
                            "#p37034",
                            "L",
                            "#p37035",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_36963" to "_:_36940",
                                "#p37034" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36943",
                                "#p37034" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36944",
                                "#p37034" to null,
                                "L" to "\"Pasta\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36941",
                                "#p37034" to null,
                                "L" to "\"Pizza\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36945",
                                "#p37034" to null,
                                "L" to "\"Soft Drink\"",
                                "#p37035" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36942",
                                "#p37034" to null,
                                "L" to "\"Wine\"",
                                "#p37035" to null,
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
                                "#p37058",
                                "#o37058"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37058" to null,
                                    "#o37058" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37058" to null,
                                    "#o37058" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37058" to null,
                                    "#o37058" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37058" to null,
                                    "#o37058" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37058",
                            "#o37058"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37058" to null,
                                "#o37058" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37058" to null,
                                "#o37058" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37058" to null,
                                "#o37058" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37058" to null,
                                "#o37058" to null
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
                                "#_36963",
                                "#p37055",
                                "L",
                                "#p37056",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#_36963" to "_:_36940",
                                    "#p37055" to null,
                                    "L" to "\"Ice Cream\"",
                                    "#p37056" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36941",
                                    "#p37055" to null,
                                    "L" to "\"Pizza\"",
                                    "#p37056" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36942",
                                    "#p37055" to null,
                                    "L" to "\"Wine\"",
                                    "#p37056" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36943",
                                    "#p37055" to null,
                                    "L" to "\"Ice Cream\"",
                                    "#p37056" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36944",
                                    "#p37055" to null,
                                    "L" to "\"Pasta\"",
                                    "#p37056" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_36963" to "_:_36945",
                                    "#p37055" to null,
                                    "L" to "\"Soft Drink\"",
                                    "#p37056" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#_36963",
                            "#p37055",
                            "L",
                            "#p37056",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_36963" to "_:_36940",
                                "#p37055" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36943",
                                "#p37055" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36944",
                                "#p37055" to null,
                                "L" to "\"Pasta\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36941",
                                "#p37055" to null,
                                "L" to "\"Pizza\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36945",
                                "#p37055" to null,
                                "L" to "\"Soft Drink\"",
                                "#p37056" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36942",
                                "#p37055" to null,
                                "L" to "\"Wine\"",
                                "#p37056" to null,
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
                                "#p37402",
                                "#o37402"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37402" to null,
                                    "#o37402" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37402" to null,
                                    "#o37402" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37402" to null,
                                    "#o37402" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37402" to null,
                                    "#o37402" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37402",
                            "#o37402"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37402" to null,
                                "#o37402" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37402" to null,
                                "#o37402" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37402" to null,
                                "#o37402" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37402" to null,
                                "#o37402" to null
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
                                "#_37328",
                                "#p37399",
                                "L",
                                "#p37400",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#_37328" to "_:_37305",
                                    "#p37399" to null,
                                    "L" to "\"Ice Cream\"",
                                    "#p37400" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37306",
                                    "#p37399" to null,
                                    "L" to "\"Pizza\"",
                                    "#p37400" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37307",
                                    "#p37399" to null,
                                    "L" to "\"Wine\"",
                                    "#p37400" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37308",
                                    "#p37399" to null,
                                    "L" to "\"Ice Cream\"",
                                    "#p37400" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37309",
                                    "#p37399" to null,
                                    "L" to "\"Pasta\"",
                                    "#p37400" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37310",
                                    "#p37399" to null,
                                    "L" to "\"Soft Drink\"",
                                    "#p37400" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#_37328",
                            "#p37399",
                            "L",
                            "#p37400",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_37328" to "_:_37305",
                                "#p37399" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37308",
                                "#p37399" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37309",
                                "#p37399" to null,
                                "L" to "\"Pasta\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37306",
                                "#p37399" to null,
                                "L" to "\"Pizza\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37310",
                                "#p37399" to null,
                                "L" to "\"Soft Drink\"",
                                "#p37400" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37307",
                                "#p37399" to null,
                                "L" to "\"Wine\"",
                                "#p37400" to null,
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
                                "#p37423",
                                "#o37423"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>",
                                    "#p37423" to null,
                                    "#o37423" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>",
                                    "#p37423" to null,
                                    "#o37423" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>",
                                    "#p37423" to null,
                                    "#o37423" to null
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>",
                                    "#p37423" to null,
                                    "#o37423" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37423",
                            "#o37423"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37423" to null,
                                "#o37423" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37423" to null,
                                "#o37423" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37423" to null,
                                "#o37423" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37423" to null,
                                "#o37423" to null
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
                                "#_37328",
                                "#p37420",
                                "L",
                                "#p37421",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#_37328" to "_:_37305",
                                    "#p37420" to null,
                                    "L" to "\"Ice Cream\"",
                                    "#p37421" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37306",
                                    "#p37420" to null,
                                    "L" to "\"Pizza\"",
                                    "#p37421" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37307",
                                    "#p37420" to null,
                                    "L" to "\"Wine\"",
                                    "#p37421" to null,
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37308",
                                    "#p37420" to null,
                                    "L" to "\"Ice Cream\"",
                                    "#p37421" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37309",
                                    "#p37420" to null,
                                    "L" to "\"Pasta\"",
                                    "#p37421" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_37328" to "_:_37310",
                                    "#p37420" to null,
                                    "L" to "\"Soft Drink\"",
                                    "#p37421" to null,
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#_37328",
                            "#p37420",
                            "L",
                            "#p37421",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_37328" to "_:_37305",
                                "#p37420" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37308",
                                "#p37420" to null,
                                "L" to "\"Ice Cream\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37309",
                                "#p37420" to null,
                                "L" to "\"Pasta\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37306",
                                "#p37420" to null,
                                "L" to "\"Pizza\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37310",
                                "#p37420" to null,
                                "L" to "\"Soft Drink\"",
                                "#p37421" to null,
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37307",
                                "#p37420" to null,
                                "L" to "\"Wine\"",
                                "#p37421" to null,
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
                e.printStackTrace()
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
