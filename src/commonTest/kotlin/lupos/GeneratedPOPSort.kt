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
    constructor() {
        P2P.knownClients.clear()
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
                                "O12",
                                "C"
                            ), listOf(
                                mutableMapOf(
                                    "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O12",
                            "C"
                        ), listOf(
                            mutableMapOf(
                                "O12" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "C" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "C" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "O12" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "C" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            /* {
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
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
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
                        AOPVariable("o"),
                        true,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()

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
                        AOPVariable("p"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                    }()

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
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                    }()

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
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            )
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
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
                        AOPVariable("o2"),
                        true,
                                    POPJoinHashMap(
                                        dictionary,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                    }()
,
                                        true                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o2"),
                                        true,
                                                    POPJoinHashMap(
                                                        dictionary,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        true                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p2"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o2"),
                                                        true,
                                                                    POPJoinHashMap(
                                                                        dictionary,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                                    }()
,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                                                    }()
,
                                                                        true                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("p2"),
                                                        true,
                                                                    POPSort(
                                                                        dictionary,
                                                                        AOPVariable("o2"),
                                                                        true,
                                                                                    POPJoinHashMap(
                                                                                        dictionary,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                        true                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p3>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
                                                                    POPSort(
                                                                        dictionary,
                                                                        AOPVariable("p2"),
                                                                        true,
                                                                                    POPSort(
                                                                                        dictionary,
                                                                                        AOPVariable("o2"),
                                                                                        true,
                                                                                                    POPJoinHashMap(
                                                                                                        dictionary,
                                                                                                                    {
                                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                                                                    }()
,
                                                                                                                    {
                                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                                                                                    }()
,
                                                                                                        true                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            /* {
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
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
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
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
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
                        AOPVariable("o"),
                        true,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5,5\"^^<http://example.org/myCustomDatatype>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/p7>","\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()

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
                        AOPVariable("p"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5,5\"^^<http://example.org/myCustomDatatype>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/p7>","\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                    }()

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
                        AOPVariable("s"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5,5\"^^<http://example.org/myCustomDatatype>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/p7>","\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                    }()

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
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "x" to "\"a\"",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "x" to "_:b",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "x" to "<http://example/a>",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "x" to "\"1\"",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                                    POPBind(
                                        dictionary,
                                        AOPVariable("sum"),
                                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                                                    POPJoinHashMap(
                                                        dictionary,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","x",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","y",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "x" to "\"a\"",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "x" to "_:b",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "x" to "<http://example/a>",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "x" to "\"1\"",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("sum"),
                                        true,
                                                    POPBind(
                                                        dictionary,
                                                        AOPVariable("sum"),
                                                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                                                                    POPJoinHashMap(
                                                                        dictionary,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","x",false,true,false,EIndexPattern.SPO)
                                                                                    }()
,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","y",false,true,false,EIndexPattern.SPO)
                                                                                    }()
,
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "x" to "_:b",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "x" to "<http://example/a>",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "x" to "\"a\"",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "x" to "\"1\"",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("y"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("sum"),
                                                        true,
                                                                    POPBind(
                                                                        dictionary,
                                                                        AOPVariable("sum"),
                                                                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                                                                                    POPJoinHashMap(
                                                                                        dictionary,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","x",false,true,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","y",false,true,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                        false                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
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
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "x" to "\"a\"",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "x" to "_:b",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "x" to "<http://example/a>",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "x" to "\"1\"",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
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
                        AOPVariable("sum"),
                        true,
                                    POPBind(
                                        dictionary,
                                        AOPVariable("sum"),
                                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                                                    POPJoinHashMap(
                                                        dictionary,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","x",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","y",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
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
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "x" to "\"a\"",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "x" to "_:b",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "x" to "<http://example/a>",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "x" to "\"1\"",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("sum"),
                                        true,
                                                    POPBind(
                                                        dictionary,
                                                        AOPVariable("sum"),
                                                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                                                                    POPJoinHashMap(
                                                                        dictionary,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","x",false,true,false,EIndexPattern.SPO)
                                                                                    }()
,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","y",false,true,false,EIndexPattern.SPO)
                                                                                    }()
,
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
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
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example/x2>",
                                    "x" to "_:b",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x3>",
                                    "x" to "<http://example/a>",
                                    "y" to "\"1\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x1>",
                                    "x" to "\"a\"",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x6>",
                                    "x" to "\"1\"",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x7>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x4>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x5>",
                                    "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example/x8>",
                                    "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("y"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("sum"),
                                                        true,
                                                                    POPBind(
                                                                        dictionary,
                                                                        AOPVariable("sum"),
                                                                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                                                                                    POPJoinHashMap(
                                                                                        dictionary,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","x",false,true,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","y",false,true,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                        false                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "x" to "\"1\"",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "x" to "\"a\"",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "x" to "<http://example/a>",
                                "y" to "\"1\"",
                                "sum" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "x" to "_:b",
                                "y" to "\"1\"",
                                "sum" to null
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
                        AOPVariable("o"),
                        true,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()

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
                        AOPVariable("p"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                    }()

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
                        AOPVariable("s"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                    }()

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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
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
                        AOPVariable("o2"),
                        true,
                                    POPJoinHashMap(
                                        dictionary,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                    }()
,
                                        true                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o2"),
                                        true,
                                                    POPJoinHashMap(
                                                        dictionary,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        true                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p2"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o2"),
                                                        true,
                                                                    POPJoinHashMap(
                                                                        dictionary,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                                    }()
,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                                                    }()
,
                                                                        true                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("p2"),
                                                        true,
                                                                    POPSort(
                                                                        dictionary,
                                                                        AOPVariable("o2"),
                                                                        true,
                                                                                    POPJoinHashMap(
                                                                                        dictionary,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                        true                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                mutableMapOf(
                                    "s" to "<http://example.org/s1>",
                                    "p" to "<http://example.org/p1>",
                                    "o" to "<http://example.org/s2>",
                                    "p2" to "<http://example.org/p2>",
                                    "o2" to "\"foo\""
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s3>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s2>",
                                    "p" to "<http://example.org/p2>",
                                    "o" to "\"foo\"",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s4>",
                                    "p" to "<http://example.org/p4>",
                                    "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s5>",
                                    "p" to "<http://example.org/p5>",
                                    "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                    "p2" to null,
                                    "o2" to null
                                ),
                                mutableMapOf(
                                    "s" to "<http://example.org/s6>",
                                    "p" to "<http://example.org/p6>",
                                    "o" to "_:o6",
                                    "p2" to null,
                                    "o2" to null
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
                                                                    POPSort(
                                                                        dictionary,
                                                                        AOPVariable("p2"),
                                                                        true,
                                                                                    POPSort(
                                                                                        dictionary,
                                                                                        AOPVariable("o2"),
                                                                                        true,
                                                                                                    POPJoinHashMap(
                                                                                                        dictionary,
                                                                                                                    {
                                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                                                                    }()
,
                                                                                                                    {
                                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                                                                                                    }()
,
                                                                                                        true                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\"",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>",
                                "p2" to null,
                                "o2" to null
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6",
                                "p2" to null,
                                "o2" to null
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
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                ),
                                mutableMapOf(
                                    "x" to "<http://www.example.org/instance#a>",
                                    "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                    "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                    POPBind(
                                        dictionary,
                                        AOPVariable("sum"),
                                        AOPAddition(AOPVariable("y"), AOPVariable("y")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>",
                                "sum" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
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
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder3>"
                                ),
                                mutableMapOf(
                                    "O" to "<http://www.example.orgorder4>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>"
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
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>"
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
                                "#_34549",
                                "L",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#_34549" to "_:_34526",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_34549" to "_:_34527",
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_34549" to "_:_34528",
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_34549" to "_:_34529",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_34549" to "_:_34530",
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_34549" to "_:_34531",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#_34549",
                            "L",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_34549" to "_:_34526",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34529",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34530",
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34527",
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34531",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34528",
                                "L" to "\"Wine\"",
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
                        AOPVariable("L"),
                        true,
                                    POPJoinHashMap(
                                        dictionary,
                                                    POPJoinHashMap(
                                                        dictionary,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("_:_34526","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_34527","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                                        graph.addData(1L,listOf("_:_34528","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                                        graph.addData(1L,listOf("_:_34529","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_34530","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                                        graph.addData(1L,listOf("_:_34531","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_34532","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                                        graph.addData(1L,listOf("_:_34533","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_34534","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                                        graph.addData(1L,listOf("_:_34535","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_34549","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_34526"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_34527"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_34528"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_34529"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_34530"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_34531"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_34532"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_34533"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_34534"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_34535"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_34549",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )
,
                                                    POPDistinct(
                                                        dictionary,
                                                                    POPProjection(
                                                                        dictionary,
                                                                        mutableListOf(
                                                                            AOPVariable("O")
                                                                        ),
                                                                                    POPLimit(
                                                                                        dictionary,
                                                                                        2,
                                                                                                    POPSort(
                                                                                                        dictionary,
                                                                                                        AOPVariable("O"),
                                                                                                        true,
                                                                                                                    {
                                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                                                                                                                    }()

                                                                                                    )

                                                                                    )

                                                                    )

                                                    )
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "#_34549",
                            "L",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_34549" to "_:_34526",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34529",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34530",
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34527",
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34531",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34549" to "_:_34528",
                                "L" to "\"Wine\"",
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
                        AOPVariable("L"),
                        true,
                        POPValues(dictionary, listOf(
                                "#_34870",
                                "L",
                                "O"
                            ), listOf(
                                mutableMapOf(
                                    "#_34870" to "_:_34847",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_34870" to "_:_34848",
                                    "L" to "\"Pizza\"",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_34870" to "_:_34849",
                                    "L" to "\"Wine\"",
                                    "O" to "<http://www.example.orgorder1>"
                                ),
                                mutableMapOf(
                                    "#_34870" to "_:_34850",
                                    "L" to "\"Ice Cream\"",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_34870" to "_:_34851",
                                    "L" to "\"Pasta\"",
                                    "O" to "<http://www.example.orgorder2>"
                                ),
                                mutableMapOf(
                                    "#_34870" to "_:_34852",
                                    "L" to "\"Soft Drink\"",
                                    "O" to "<http://www.example.orgorder2>"
                                )
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#_34870",
                            "L",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_34870" to "_:_34847",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34850",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34851",
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34848",
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34852",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34849",
                                "L" to "\"Wine\"",
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
                        AOPVariable("L"),
                        true,
                                    POPJoinHashMap(
                                        dictionary,
                                                    POPJoinHashMap(
                                                        dictionary,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("_:_34847","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_34848","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                                        graph.addData(1L,listOf("_:_34849","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                                        graph.addData(1L,listOf("_:_34850","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_34851","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                                        graph.addData(1L,listOf("_:_34852","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_34853","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                                        graph.addData(1L,listOf("_:_34854","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_34855","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                                        graph.addData(1L,listOf("_:_34856","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_34870","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_34847"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_34848"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_34849"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_34850"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_34851"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_34852"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_34853"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_34854"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_34855"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_34856"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_34870",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )
,
                                                    POPDistinct(
                                                        dictionary,
                                                                    POPProjection(
                                                                        dictionary,
                                                                        mutableListOf(
                                                                            AOPVariable("O")
                                                                        ),
                                                                                    POPLimit(
                                                                                        dictionary,
                                                                                        2,
                                                                                                    POPSort(
                                                                                                        dictionary,
                                                                                                        AOPVariable("O"),
                                                                                                        true,
                                                                                                                    {
                                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                                                                                                                    }()

                                                                                                    )

                                                                                    )

                                                                    )

                                                    )
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "#_34870",
                            "L",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "#_34870" to "_:_34847",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34850",
                                "L" to "\"Ice Cream\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34851",
                                "L" to "\"Pasta\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34848",
                                "L" to "\"Pizza\"",
                                "O" to "<http://www.example.orgorder1>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34852",
                                "L" to "\"Soft Drink\"",
                                "O" to "<http://www.example.orgorder2>"
                            ),
                            mutableMapOf(
                                "#_34870" to "_:_34849",
                                "L" to "\"Wine\"",
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
