package lupos

import lupos.s00misc.*
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.*
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.*
import lupos.s12p2p.P2P
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.EndpointImpl
import lupos.s15tripleStoreDistributed.*
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
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("O12"),
                                true,
                                POPValues(dictionary, listOf(
                                        "O12",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map2758map,
                                        GeneratedMutableMap.map2759map,
                                        GeneratedMutableMap.map2760map,
                                        GeneratedMutableMap.map2761map,
                                        GeneratedMutableMap.map2762map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O12",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map2758map,
                                GeneratedMutableMap.map2759map,
                                GeneratedMutableMap.map2760map,
                                GeneratedMutableMap.map2761map,
                                GeneratedMutableMap.map2762map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10099map,
                            GeneratedMutableMap.map10100map,
                            GeneratedMutableMap.map10098map,
                            GeneratedMutableMap.map10097map,
                            GeneratedMutableMap.map10096map,
                            GeneratedMutableMap.map10101map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10101map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10096map,
                            GeneratedMutableMap.map10097map,
                            GeneratedMutableMap.map10098map,
                            GeneratedMutableMap.map10099map,
                            GeneratedMutableMap.map10100map,
                            GeneratedMutableMap.map10101map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10096map,
                            GeneratedMutableMap.map10097map,
                            GeneratedMutableMap.map10098map,
                            GeneratedMutableMap.map10099map,
                            GeneratedMutableMap.map10100map,
                            GeneratedMutableMap.map10101map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10098map,
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map10100map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10098map,
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map10100map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("p"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map10100map,
                                        GeneratedMutableMap.map10098map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map10100map,
                                        GeneratedMutableMap.map10098map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                LOPSort(
                                        true,
                                        AOPVariable("o"),
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("s"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10098map,
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map10100map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                }()

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10098map,
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map10100map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                LOPSort(
                                        true,
                                        AOPVariable("p"),
                                        LOPSort(
                                                true,
                                                AOPVariable("o"),
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                }()

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
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
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10280map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
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
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10280map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
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
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10280map,
                            GeneratedMutableMap.map10285map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10285map
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
                            GeneratedMutableMap.map10280map,
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
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
                            GeneratedMutableMap.map10280map,
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("o2"),
                                true,
                                POPJoinHashMap(
                                        dictionary,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        true)

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o2"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o2"),
                                LOPJoin(
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                        }()
                                        ,
                                        true)

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10280map
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                true)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p2"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10280map
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p2"),
                                LOPSort(
                                        true,
                                        AOPVariable("o2"),
                                        LOPJoin(
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                                }()
                                                ,
                                                true)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10280map
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
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        true)

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
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10280map
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
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                LOPSort(
                                        true,
                                        AOPVariable("p2"),
                                        LOPSort(
                                                true,
                                                AOPVariable("o2"),
                                                LOPJoin(
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                                        }()
                                                        ,
                                                        true)

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
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                                }()
                                                                ,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                                                }()
                                                                ,
                                                                true)

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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                LOPSort(
                                        true,
                                        AOPVariable("o"),
                                        LOPSort(
                                                true,
                                                AOPVariable("p2"),
                                                LOPSort(
                                                        true,
                                                        AOPVariable("o2"),
                                                        LOPJoin(
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                                }()
                                                                ,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                                                }()
                                                                ,
                                                                true)

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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                                        }()
                                                                        ,
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                                                        }()
                                                                        ,
                                                                        true)

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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10282map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                LOPSort(
                                        true,
                                        AOPVariable("p"),
                                        LOPSort(
                                                true,
                                                AOPVariable("o"),
                                                LOPSort(
                                                        true,
                                                        AOPVariable("p2"),
                                                        LOPSort(
                                                                true,
                                                                AOPVariable("o2"),
                                                                LOPJoin(
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                                        }()
                                                                        ,
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                                                        }()
                                                                        ,
                                                                        true)

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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10469map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10470map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10475map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10475map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10469map,
                            GeneratedMutableMap.map10470map,
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10469map,
                            GeneratedMutableMap.map10470map,
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10469map,
                                        GeneratedMutableMap.map10470map,
                                        GeneratedMutableMap.map10471map,
                                        GeneratedMutableMap.map10472map,
                                        GeneratedMutableMap.map10473map,
                                        GeneratedMutableMap.map10474map,
                                        GeneratedMutableMap.map10475map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5,5\"^^<http://example.org/myCustomDatatype>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/p7>", "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10469map,
                                        GeneratedMutableMap.map10470map,
                                        GeneratedMutableMap.map10471map,
                                        GeneratedMutableMap.map10472map,
                                        GeneratedMutableMap.map10473map,
                                        GeneratedMutableMap.map10474map,
                                        GeneratedMutableMap.map10475map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5,5\"^^<http://example.org/myCustomDatatype>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/p7>", "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("p"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10471map,
                                        GeneratedMutableMap.map10469map,
                                        GeneratedMutableMap.map10474map,
                                        GeneratedMutableMap.map10470map,
                                        GeneratedMutableMap.map10472map,
                                        GeneratedMutableMap.map10473map,
                                        GeneratedMutableMap.map10475map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5,5\"^^<http://example.org/myCustomDatatype>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                                            graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/p7>", "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10471map,
                                        GeneratedMutableMap.map10469map,
                                        GeneratedMutableMap.map10474map,
                                        GeneratedMutableMap.map10470map,
                                        GeneratedMutableMap.map10472map,
                                        GeneratedMutableMap.map10473map,
                                        GeneratedMutableMap.map10475map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                LOPSort(
                                        true,
                                        AOPVariable("o"),
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5,5\"^^<http://example.org/myCustomDatatype>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                                            graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/p7>", "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("s"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10469map,
                                        GeneratedMutableMap.map10470map,
                                        GeneratedMutableMap.map10471map,
                                        GeneratedMutableMap.map10472map,
                                        GeneratedMutableMap.map10473map,
                                        GeneratedMutableMap.map10474map,
                                        GeneratedMutableMap.map10475map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5,5\"^^<http://example.org/myCustomDatatype>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/p7>", "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                }()

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10469map,
                                        GeneratedMutableMap.map10470map,
                                        GeneratedMutableMap.map10471map,
                                        GeneratedMutableMap.map10472map,
                                        GeneratedMutableMap.map10473map,
                                        GeneratedMutableMap.map10474map,
                                        GeneratedMutableMap.map10475map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                LOPSort(
                                        true,
                                        AOPVariable("p"),
                                        LOPSort(
                                                true,
                                                AOPVariable("o"),
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p3>", "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5,5\"^^<http://example.org/myCustomDatatype>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/p7>", "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                }()

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26627map,
                                        GeneratedMutableMap.map26628map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "x", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "y", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                false)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("sum"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26627map,
                                        GeneratedMutableMap.map26628map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("sum"),
                                LOPBind(
                                        AOPVariable("sum"),
                                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                                        LOPJoin(
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("x"), graphName, false)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/q>"), AOPVariable("y"), graphName, false)
                                                }()
                                                ,
                                                false)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map,
                                        GeneratedMutableMap.map26627map,
                                        GeneratedMutableMap.map26628map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                            graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                            graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                            graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "x", false, true, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                            graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                            graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "y", false, true, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        false)

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("y"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map,
                                        GeneratedMutableMap.map26627map,
                                        GeneratedMutableMap.map26628map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("y"),
                                LOPSort(
                                        true,
                                        AOPVariable("sum"),
                                        LOPBind(
                                                AOPVariable("sum"),
                                                AOPAddition(AOPVariable("y"), AOPVariable("x")),
                                                LOPJoin(
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                            graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                            graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                            graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("x"), graphName, false)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                            graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                            graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/q>"), AOPVariable("y"), graphName, false)
                                                        }()
                                                        ,
                                                        false)

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map,
                                        GeneratedMutableMap.map26627map,
                                        GeneratedMutableMap.map26628map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26628map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26625map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "x", false, true, false, EIndexPattern.SPO)
                                                                }()
                                                                ,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "y", false, true, false, EIndexPattern.SPO)
                                                                }()
                                                                ,
                                                                false)

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
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26628map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26625map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("x"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map,
                                        GeneratedMutableMap.map26627map,
                                        GeneratedMutableMap.map26628map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26628map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26625map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("x"),
                                LOPSort(
                                        true,
                                        AOPVariable("y"),
                                        LOPSort(
                                                true,
                                                AOPVariable("sum"),
                                                LOPBind(
                                                        AOPVariable("sum"),
                                                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                                                        LOPJoin(
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("x"), graphName, false)
                                                                }()
                                                                ,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/q>"), AOPVariable("y"), graphName, false)
                                                                }()
                                                                ,
                                                                false)

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
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26628map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26625map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26888map,
                                        GeneratedMutableMap.map26889map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "x", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "y", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                false)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("sum"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26888map,
                                        GeneratedMutableMap.map26889map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("sum"),
                                LOPBind(
                                        AOPVariable("sum"),
                                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                                        LOPJoin(
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("x"), graphName, false)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/q>"), AOPVariable("y"), graphName, false)
                                                }()
                                                ,
                                                false)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26888map,
                                        GeneratedMutableMap.map26889map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26631map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                            graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                            graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                            graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "x", false, true, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                            graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                            graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "y", false, true, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        false)

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26631map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("y"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26888map,
                                        GeneratedMutableMap.map26889map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26631map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26631map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("y"),
                                LOPSort(
                                        true,
                                        AOPVariable("sum"),
                                        LOPBind(
                                                AOPVariable("sum"),
                                                AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                                                LOPJoin(
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                            graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                            graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                            graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("x"), graphName, false)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                            graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                            graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                            graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/q>"), AOPVariable("y"), graphName, false)
                                                        }()
                                                        ,
                                                        false)

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26625map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26631map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26888map,
                                        GeneratedMutableMap.map26889map,
                                        GeneratedMutableMap.map26631map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26625map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "x", false, true, false, EIndexPattern.SPO)
                                                                }()
                                                                ,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "y", false, true, false, EIndexPattern.SPO)
                                                                }()
                                                                ,
                                                                false)

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
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26625map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("x"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map26625map,
                                        GeneratedMutableMap.map26626map,
                                        GeneratedMutableMap.map26624map,
                                        GeneratedMutableMap.map26629map,
                                        GeneratedMutableMap.map26630map,
                                        GeneratedMutableMap.map26888map,
                                        GeneratedMutableMap.map26889map,
                                        GeneratedMutableMap.map26631map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26625map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("x"),
                                LOPSort(
                                        true,
                                        AOPVariable("y"),
                                        LOPSort(
                                                true,
                                                AOPVariable("sum"),
                                                LOPBind(
                                                        AOPVariable("sum"),
                                                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                                                        LOPJoin(
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/p>", "\"a\""))
                                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/p>", "_:b"))
                                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/p>", "<http://example/a>"))
                                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/p>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/p>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("x"), graphName, false)
                                                                }()
                                                                ,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example/x1>", "<http://example/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x2>", "<http://example/q>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x3>", "<http://example/q>", "\"1\""))
                                                                    graph.addData(1L, listOf("<http://example/x4>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x5>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example/x6>", "<http://example/q>", "\"2\""))
                                                                    graph.addData(1L, listOf("<http://example/x7>", "<http://example/q>", "\"2\""))
                                                                    graph.addData(1L, listOf("<http://example/x8>", "<http://example/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/q>"), AOPVariable("y"), graphName, false)
                                                                }()
                                                                ,
                                                                false)

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
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26888map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map,
                                GeneratedMutableMap.map26889map,
                                GeneratedMutableMap.map26624map,
                                GeneratedMutableMap.map26626map,
                                GeneratedMutableMap.map26625map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map32229map,
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map32230map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("o"),
                                true,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map32229map,
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map32230map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("p"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map32230map,
                                        GeneratedMutableMap.map32229map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map32230map,
                                        GeneratedMutableMap.map32229map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                LOPSort(
                                        true,
                                        AOPVariable("o"),
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("s"),
                                true,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map32229map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map32230map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                }()

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map32229map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map32230map,
                                        GeneratedMutableMap.map10101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                LOPSort(
                                        true,
                                        AOPVariable("p"),
                                        LOPSort(
                                                true,
                                                AOPVariable("o"),
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                }()

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("o2"),
                                true,
                                POPJoinHashMap(
                                        dictionary,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        true)

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o2"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o2"),
                                LOPJoin(
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                        }()
                                        ,
                                        true)

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10280map
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                true)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p2"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10280map
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
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p2"),
                                LOPSort(
                                        true,
                                        AOPVariable("o2"),
                                        LOPJoin(
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                                }()
                                                ,
                                                true)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10280map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10280map
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
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        true)

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
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10280map
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
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("o"),
                                LOPSort(
                                        true,
                                        AOPVariable("p2"),
                                        LOPSort(
                                                true,
                                                AOPVariable("o2"),
                                                LOPJoin(
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                                        }()
                                                        ,
                                                        true)

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
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                                }()
                                                                ,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                                                }()
                                                                ,
                                                                true)

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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("p"),
                                LOPSort(
                                        true,
                                        AOPVariable("o"),
                                        LOPSort(
                                                true,
                                                AOPVariable("p2"),
                                                LOPSort(
                                                        true,
                                                        AOPVariable("o2"),
                                                        LOPJoin(
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                                }()
                                                                ,
                                                                {
                                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                    DistributedTripleStore.commit(1L)
                                                                    LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                                                }()
                                                                ,
                                                                true)

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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                                        }()
                                                                        ,
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                                                        }()
                                                                        ,
                                                                        true)

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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10280map,
                                        GeneratedMutableMap.map32357map,
                                        GeneratedMutableMap.map10281map,
                                        GeneratedMutableMap.map10283map,
                                        GeneratedMutableMap.map32358map,
                                        GeneratedMutableMap.map10285map
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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("s"),
                                LOPSort(
                                        true,
                                        AOPVariable("p"),
                                        LOPSort(
                                                true,
                                                AOPVariable("o"),
                                                LOPSort(
                                                        true,
                                                        AOPVariable("p2"),
                                                        LOPSort(
                                                                true,
                                                                AOPVariable("o2"),
                                                                LOPJoin(
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                                        }()
                                                                        ,
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p2>", "\"foo\""))
                                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p2>", "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p4>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/p5>", "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/p6>", "_:o6"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                                                        }()
                                                                        ,
                                                                        true)

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
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32357map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map32358map,
                                GeneratedMutableMap.map10285map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("sum"),
                                true,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map34416map,
                                        GeneratedMutableMap.map34417map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map34416map,
                                GeneratedMutableMap.map34417map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map34416map,
                                GeneratedMutableMap.map34417map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("sum"),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y",
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map34416map,
                                        GeneratedMutableMap.map34417map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map34416map,
                                GeneratedMutableMap.map34417map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("sum"),
                                LOPBind(
                                        AOPVariable("sum"),
                                        AOPAddition(AOPVariable("y"), AOPVariable("y")),
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("y"), graphName, false)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map34416map,
                                GeneratedMutableMap.map34417map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("O"),
                                true,
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36108map,
                                        GeneratedMutableMap.map36109map,
                                        GeneratedMutableMap.map36110map,
                                        GeneratedMutableMap.map36111map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map,
                                GeneratedMutableMap.map36110map,
                                GeneratedMutableMap.map36111map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("O"),
                                true,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>", false, true, true, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map,
                                GeneratedMutableMap.map36110map,
                                GeneratedMutableMap.map36111map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("O"),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36108map,
                                        GeneratedMutableMap.map36109map,
                                        GeneratedMutableMap.map36110map,
                                        GeneratedMutableMap.map36111map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map,
                                GeneratedMutableMap.map36110map,
                                GeneratedMutableMap.map36111map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("O"),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://www.example.orgOrder>"), graphName, false)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map,
                                GeneratedMutableMap.map36110map,
                                GeneratedMutableMap.map36111map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("L"),
                                true,
                                POPValues(dictionary, listOf(
                                        "#_35992",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36132map,
                                        GeneratedMutableMap.map36133map,
                                        GeneratedMutableMap.map36134map,
                                        GeneratedMutableMap.map36135map,
                                        GeneratedMutableMap.map36136map,
                                        GeneratedMutableMap.map36137map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#_35992",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36132map,
                                GeneratedMutableMap.map36135map,
                                GeneratedMutableMap.map36136map,
                                GeneratedMutableMap.map36133map,
                                GeneratedMutableMap.map36137map,
                                GeneratedMutableMap.map36134map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("_:_35969", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_35970", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                                    graph.addData(1L, listOf("_:_35971", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                                    graph.addData(1L, listOf("_:_35972", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_35973", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                                    graph.addData(1L, listOf("_:_35974", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_35975", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                                    graph.addData(1L, listOf("_:_35976", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_35977", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                                    graph.addData(1L, listOf("_:_35978", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_35992", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35969"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35970"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35971"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35972"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35973"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35974"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_35975"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_35976"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_35977"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_35978"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_35992", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                false)
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
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>", false, true, true, EIndexPattern.SPO)
                                                                        }()

                                                                )

                                                        )

                                                )

                                        )
                                        ,
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "#_35992",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36132map,
                                GeneratedMutableMap.map36135map,
                                GeneratedMutableMap.map36136map,
                                GeneratedMutableMap.map36133map,
                                GeneratedMutableMap.map36137map,
                                GeneratedMutableMap.map36134map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("L"),
                                POPValues(dictionary, listOf(
                                        "#_35992",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36132map,
                                        GeneratedMutableMap.map36133map,
                                        GeneratedMutableMap.map36134map,
                                        GeneratedMutableMap.map36135map,
                                        GeneratedMutableMap.map36136map,
                                        GeneratedMutableMap.map36137map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#_35992",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36132map,
                                GeneratedMutableMap.map36135map,
                                GeneratedMutableMap.map36136map,
                                GeneratedMutableMap.map36133map,
                                GeneratedMutableMap.map36137map,
                                GeneratedMutableMap.map36134map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("L"),
                                LOPJoin(
                                        LOPJoin(
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("_:_35969", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_35970", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                                    graph.addData(1L, listOf("_:_35971", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                                    graph.addData(1L, listOf("_:_35972", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_35973", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                                    graph.addData(1L, listOf("_:_35974", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_35975", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                                    graph.addData(1L, listOf("_:_35976", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_35977", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                                    graph.addData(1L, listOf("_:_35978", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("#_35992"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35969"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35970"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35971"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35972"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35973"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35974"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_35975"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_35976"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_35977"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_35978"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.example.orghasItem>"), AOPVariable("#_35992"), graphName, false)
                                                }()
                                                ,
                                                false)
                                        ,
                                        LOPDistinct(
                                                LOPProjection(
                                                        mutableListOf(
                                                                AOPVariable("O")
                                                        ),
                                                        LOPLimit(
                                                                2,
                                                                LOPSort(
                                                                        true,
                                                                        AOPVariable("O"),
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://www.example.orgOrder>"), graphName, false)
                                                                        }()

                                                                )

                                                        )

                                                )

                                        )
                                        ,
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "#_35992",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36132map,
                                GeneratedMutableMap.map36135map,
                                GeneratedMutableMap.map36136map,
                                GeneratedMutableMap.map36133map,
                                GeneratedMutableMap.map36137map,
                                GeneratedMutableMap.map36134map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPSort(
                                dictionary,
                                AOPVariable("L"),
                                true,
                                POPValues(dictionary, listOf(
                                        "#_36360",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36496map,
                                        GeneratedMutableMap.map36497map,
                                        GeneratedMutableMap.map36498map,
                                        GeneratedMutableMap.map36499map,
                                        GeneratedMutableMap.map36500map,
                                        GeneratedMutableMap.map36501map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#_36360",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36496map,
                                GeneratedMutableMap.map36499map,
                                GeneratedMutableMap.map36500map,
                                GeneratedMutableMap.map36497map,
                                GeneratedMutableMap.map36501map,
                                GeneratedMutableMap.map36498map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("_:_36337", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_36338", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                                    graph.addData(1L, listOf("_:_36339", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                                    graph.addData(1L, listOf("_:_36340", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_36341", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                                    graph.addData(1L, listOf("_:_36342", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_36343", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                                    graph.addData(1L, listOf("_:_36344", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_36345", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                                    graph.addData(1L, listOf("_:_36346", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_36360", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36337"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36338"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36339"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36340"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36341"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36342"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36343"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36344"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36345"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36346"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_36360", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                false)
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
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>", false, true, true, EIndexPattern.SPO)
                                                                        }()

                                                                )

                                                        )

                                                )

                                        )
                                        ,
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "#_36360",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36496map,
                                GeneratedMutableMap.map36499map,
                                GeneratedMutableMap.map36500map,
                                GeneratedMutableMap.map36497map,
                                GeneratedMutableMap.map36501map,
                                GeneratedMutableMap.map36498map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("L"),
                                POPValues(dictionary, listOf(
                                        "#_36360",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36496map,
                                        GeneratedMutableMap.map36497map,
                                        GeneratedMutableMap.map36498map,
                                        GeneratedMutableMap.map36499map,
                                        GeneratedMutableMap.map36500map,
                                        GeneratedMutableMap.map36501map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#_36360",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36496map,
                                GeneratedMutableMap.map36499map,
                                GeneratedMutableMap.map36500map,
                                GeneratedMutableMap.map36497map,
                                GeneratedMutableMap.map36501map,
                                GeneratedMutableMap.map36498map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPSort(
                                true,
                                AOPVariable("L"),
                                LOPJoin(
                                        LOPJoin(
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("_:_36337", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_36338", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                                    graph.addData(1L, listOf("_:_36339", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                                    graph.addData(1L, listOf("_:_36340", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_36341", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                                    graph.addData(1L, listOf("_:_36342", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_36343", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                                    graph.addData(1L, listOf("_:_36344", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_36345", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                                    graph.addData(1L, listOf("_:_36346", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("#_36360"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36337"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36338"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36339"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36340"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36341"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36342"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36343"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36344"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36345"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36346"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.example.orghasItem>"), AOPVariable("#_36360"), graphName, false)
                                                }()
                                                ,
                                                false)
                                        ,
                                        LOPDistinct(
                                                LOPProjection(
                                                        mutableListOf(
                                                                AOPVariable("O")
                                                        ),
                                                        LOPLimit(
                                                                2,
                                                                LOPSort(
                                                                        true,
                                                                        AOPVariable("O"),
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                                                                            DistributedTripleStore.commit(1L)
                                                                            LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://www.example.orgOrder>"), graphName, false)
                                                                        }()

                                                                )

                                                        )

                                                )

                                        )
                                        ,
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "#_36360",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36496map,
                                GeneratedMutableMap.map36499map,
                                GeneratedMutableMap.map36500map,
                                GeneratedMutableMap.map36497map,
                                GeneratedMutableMap.map36501map,
                                GeneratedMutableMap.map36498map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
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
                } else if (data.input is POPBase && data is MicroTestPN) {
                    val input = data.input as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)) {
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    assertTrue(expected.myEquals(output))
                } else if (data.input is LOPBase && data is MicroTestPN) {
                    val lop_node = data.input as LOPBase
                    val dictionary = data.dictionary
                    val lop_node2 = LogicalOptimizer(1L, dictionary).optimizeCall(lop_node)
                    val pop_node = PhysicalOptimizer(1L, dictionary).optimizeCall(lop_node2)
                    val input = KeyDistributionOptimizer(1L, dictionary).optimizeCall(pop_node) as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)) {
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
