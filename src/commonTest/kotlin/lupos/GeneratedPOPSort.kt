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
                                        GeneratedMutableMap.map2836map,
                                        GeneratedMutableMap.map2837map,
                                        GeneratedMutableMap.map2838map,
                                        GeneratedMutableMap.map2839map,
                                        GeneratedMutableMap.map2840map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O12",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map2836map,
                                GeneratedMutableMap.map2837map,
                                GeneratedMutableMap.map2838map,
                                GeneratedMutableMap.map2839map,
                                GeneratedMutableMap.map2840map
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
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10424map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10422map,
                            GeneratedMutableMap.map10423map,
                            GeneratedMutableMap.map10421map,
                            GeneratedMutableMap.map10420map,
                            GeneratedMutableMap.map10419map,
                            GeneratedMutableMap.map10424map
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
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10424map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10419map,
                            GeneratedMutableMap.map10420map,
                            GeneratedMutableMap.map10421map,
                            GeneratedMutableMap.map10422map,
                            GeneratedMutableMap.map10423map,
                            GeneratedMutableMap.map10424map
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
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10424map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10419map,
                            GeneratedMutableMap.map10420map,
                            GeneratedMutableMap.map10421map,
                            GeneratedMutableMap.map10422map,
                            GeneratedMutableMap.map10423map,
                            GeneratedMutableMap.map10424map
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
                                        GeneratedMutableMap.map10419map,
                                        GeneratedMutableMap.map10420map,
                                        GeneratedMutableMap.map10421map,
                                        GeneratedMutableMap.map10422map,
                                        GeneratedMutableMap.map10423map,
                                        GeneratedMutableMap.map10424map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10424map
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
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10424map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map10502map,
                                        GeneratedMutableMap.map10503map,
                                        GeneratedMutableMap.map10504map,
                                        GeneratedMutableMap.map10505map,
                                        GeneratedMutableMap.map10506map,
                                        GeneratedMutableMap.map10507map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map10506map,
                                GeneratedMutableMap.map10504map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10507map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map10506map,
                                GeneratedMutableMap.map10504map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10507map
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
                                        GeneratedMutableMap.map10422map,
                                        GeneratedMutableMap.map10423map,
                                        GeneratedMutableMap.map10421map,
                                        GeneratedMutableMap.map10420map,
                                        GeneratedMutableMap.map10419map,
                                        GeneratedMutableMap.map10424map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10424map
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
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10424map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map10505map,
                                        GeneratedMutableMap.map10506map,
                                        GeneratedMutableMap.map10504map,
                                        GeneratedMutableMap.map10503map,
                                        GeneratedMutableMap.map10502map,
                                        GeneratedMutableMap.map10507map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10504map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map10506map,
                                GeneratedMutableMap.map10507map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10504map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map10506map,
                                GeneratedMutableMap.map10507map
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
                                        GeneratedMutableMap.map10419map,
                                        GeneratedMutableMap.map10420map,
                                        GeneratedMutableMap.map10421map,
                                        GeneratedMutableMap.map10422map,
                                        GeneratedMutableMap.map10423map,
                                        GeneratedMutableMap.map10424map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10424map
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
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10424map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map10502map,
                                        GeneratedMutableMap.map10503map,
                                        GeneratedMutableMap.map10504map,
                                        GeneratedMutableMap.map10505map,
                                        GeneratedMutableMap.map10506map,
                                        GeneratedMutableMap.map10507map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10504map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map10506map,
                                GeneratedMutableMap.map10507map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10504map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map10506map,
                                GeneratedMutableMap.map10507map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map
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
                            GeneratedMutableMap.map10612map,
                            GeneratedMutableMap.map10613map,
                            GeneratedMutableMap.map10614map,
                            GeneratedMutableMap.map10615map,
                            GeneratedMutableMap.map10616map,
                            GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                            GeneratedMutableMap.map10612map,
                            GeneratedMutableMap.map10613map,
                            GeneratedMutableMap.map10614map,
                            GeneratedMutableMap.map10615map,
                            GeneratedMutableMap.map10616map,
                            GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                            GeneratedMutableMap.map10614map,
                            GeneratedMutableMap.map10615map,
                            GeneratedMutableMap.map10613map,
                            GeneratedMutableMap.map10612map,
                            GeneratedMutableMap.map10611map,
                            GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10616map
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
                            GeneratedMutableMap.map10611map,
                            GeneratedMutableMap.map10612map,
                            GeneratedMutableMap.map10613map,
                            GeneratedMutableMap.map10614map,
                            GeneratedMutableMap.map10615map,
                            GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map
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
                            GeneratedMutableMap.map10611map,
                            GeneratedMutableMap.map10612map,
                            GeneratedMutableMap.map10613map,
                            GeneratedMutableMap.map10614map,
                            GeneratedMutableMap.map10615map,
                            GeneratedMutableMap.map10616map
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
                                        GeneratedMutableMap.map10611map,
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map10613map,
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map10615map,
                                        GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10744map,
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map10746map,
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map10748map,
                                        GeneratedMutableMap.map10749map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10749map,
                                GeneratedMutableMap.map10744map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10749map,
                                GeneratedMutableMap.map10744map
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
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map10613map,
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map10615map,
                                        GeneratedMutableMap.map10616map,
                                        GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map10746map,
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map10748map,
                                        GeneratedMutableMap.map10749map,
                                        GeneratedMutableMap.map10744map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10749map,
                                GeneratedMutableMap.map10744map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10749map,
                                GeneratedMutableMap.map10744map
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
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map10613map,
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map10615map,
                                        GeneratedMutableMap.map10616map,
                                        GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10616map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map10746map,
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map10748map,
                                        GeneratedMutableMap.map10749map,
                                        GeneratedMutableMap.map10744map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10749map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10749map
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
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map10615map,
                                        GeneratedMutableMap.map10613map,
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map10611map,
                                        GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map10748map,
                                        GeneratedMutableMap.map10746map,
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map10744map,
                                        GeneratedMutableMap.map10749map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10749map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10749map
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
                                        GeneratedMutableMap.map10611map,
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map10613map,
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map10615map,
                                        GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10613map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map10615map,
                                GeneratedMutableMap.map10616map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10744map,
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map10746map,
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map10748map,
                                        GeneratedMutableMap.map10749map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10749map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10746map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map10748map,
                                GeneratedMutableMap.map10749map
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
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10818map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10814map,
                            GeneratedMutableMap.map10812map,
                            GeneratedMutableMap.map10817map,
                            GeneratedMutableMap.map10813map,
                            GeneratedMutableMap.map10815map,
                            GeneratedMutableMap.map10816map,
                            GeneratedMutableMap.map10818map
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
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10818map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10812map,
                            GeneratedMutableMap.map10813map,
                            GeneratedMutableMap.map10814map,
                            GeneratedMutableMap.map10815map,
                            GeneratedMutableMap.map10816map,
                            GeneratedMutableMap.map10817map,
                            GeneratedMutableMap.map10818map
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
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10818map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10812map,
                            GeneratedMutableMap.map10813map,
                            GeneratedMutableMap.map10814map,
                            GeneratedMutableMap.map10815map,
                            GeneratedMutableMap.map10816map,
                            GeneratedMutableMap.map10817map,
                            GeneratedMutableMap.map10818map
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
                                        GeneratedMutableMap.map10812map,
                                        GeneratedMutableMap.map10813map,
                                        GeneratedMutableMap.map10814map,
                                        GeneratedMutableMap.map10815map,
                                        GeneratedMutableMap.map10816map,
                                        GeneratedMutableMap.map10817map,
                                        GeneratedMutableMap.map10818map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10818map
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
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10818map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map10879map,
                                        GeneratedMutableMap.map10880map,
                                        GeneratedMutableMap.map10881map,
                                        GeneratedMutableMap.map10882map,
                                        GeneratedMutableMap.map10883map,
                                        GeneratedMutableMap.map10884map,
                                        GeneratedMutableMap.map10885map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10881map,
                                GeneratedMutableMap.map10879map,
                                GeneratedMutableMap.map10884map,
                                GeneratedMutableMap.map10880map,
                                GeneratedMutableMap.map10882map,
                                GeneratedMutableMap.map10883map,
                                GeneratedMutableMap.map10885map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10881map,
                                GeneratedMutableMap.map10879map,
                                GeneratedMutableMap.map10884map,
                                GeneratedMutableMap.map10880map,
                                GeneratedMutableMap.map10882map,
                                GeneratedMutableMap.map10883map,
                                GeneratedMutableMap.map10885map
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
                                        GeneratedMutableMap.map10814map,
                                        GeneratedMutableMap.map10812map,
                                        GeneratedMutableMap.map10817map,
                                        GeneratedMutableMap.map10813map,
                                        GeneratedMutableMap.map10815map,
                                        GeneratedMutableMap.map10816map,
                                        GeneratedMutableMap.map10818map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10818map
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
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10818map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map10881map,
                                        GeneratedMutableMap.map10879map,
                                        GeneratedMutableMap.map10884map,
                                        GeneratedMutableMap.map10880map,
                                        GeneratedMutableMap.map10882map,
                                        GeneratedMutableMap.map10883map,
                                        GeneratedMutableMap.map10885map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10879map,
                                GeneratedMutableMap.map10880map,
                                GeneratedMutableMap.map10881map,
                                GeneratedMutableMap.map10882map,
                                GeneratedMutableMap.map10883map,
                                GeneratedMutableMap.map10884map,
                                GeneratedMutableMap.map10885map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10879map,
                                GeneratedMutableMap.map10880map,
                                GeneratedMutableMap.map10881map,
                                GeneratedMutableMap.map10882map,
                                GeneratedMutableMap.map10883map,
                                GeneratedMutableMap.map10884map,
                                GeneratedMutableMap.map10885map
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
                                        GeneratedMutableMap.map10812map,
                                        GeneratedMutableMap.map10813map,
                                        GeneratedMutableMap.map10814map,
                                        GeneratedMutableMap.map10815map,
                                        GeneratedMutableMap.map10816map,
                                        GeneratedMutableMap.map10817map,
                                        GeneratedMutableMap.map10818map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10818map
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
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10818map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map10879map,
                                        GeneratedMutableMap.map10880map,
                                        GeneratedMutableMap.map10881map,
                                        GeneratedMutableMap.map10882map,
                                        GeneratedMutableMap.map10883map,
                                        GeneratedMutableMap.map10884map,
                                        GeneratedMutableMap.map10885map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10879map,
                                GeneratedMutableMap.map10880map,
                                GeneratedMutableMap.map10881map,
                                GeneratedMutableMap.map10882map,
                                GeneratedMutableMap.map10883map,
                                GeneratedMutableMap.map10884map,
                                GeneratedMutableMap.map10885map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10879map,
                                GeneratedMutableMap.map10880map,
                                GeneratedMutableMap.map10881map,
                                GeneratedMutableMap.map10882map,
                                GeneratedMutableMap.map10883map,
                                GeneratedMutableMap.map10884map,
                                GeneratedMutableMap.map10885map
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
                                        GeneratedMutableMap.map27522map,
                                        GeneratedMutableMap.map27523map,
                                        GeneratedMutableMap.map27524map,
                                        GeneratedMutableMap.map27525map,
                                        GeneratedMutableMap.map27526map,
                                        GeneratedMutableMap.map27527map,
                                        GeneratedMutableMap.map27528map,
                                        GeneratedMutableMap.map27529map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27523map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map,
                                GeneratedMutableMap.map27525map,
                                GeneratedMutableMap.map27526map
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
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27523map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map,
                                GeneratedMutableMap.map27525map,
                                GeneratedMutableMap.map27526map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ), listOf(
                                        GeneratedMutableMap.map27530map,
                                        GeneratedMutableMap.map27531map,
                                        GeneratedMutableMap.map27532map,
                                        GeneratedMutableMap.map27533map,
                                        GeneratedMutableMap.map27534map,
                                        GeneratedMutableMap.map27535map,
                                        GeneratedMutableMap.map27536map,
                                        GeneratedMutableMap.map27537map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27531map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map,
                                GeneratedMutableMap.map27533map,
                                GeneratedMutableMap.map27534map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27531map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map,
                                GeneratedMutableMap.map27533map,
                                GeneratedMutableMap.map27534map
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
                                        GeneratedMutableMap.map27522map,
                                        GeneratedMutableMap.map27523map,
                                        GeneratedMutableMap.map27524map,
                                        GeneratedMutableMap.map27527map,
                                        GeneratedMutableMap.map27528map,
                                        GeneratedMutableMap.map27529map,
                                        GeneratedMutableMap.map27525map,
                                        GeneratedMutableMap.map27526map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27523map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map,
                                GeneratedMutableMap.map27525map,
                                GeneratedMutableMap.map27526map
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
                                GeneratedMutableMap.map27523map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map,
                                GeneratedMutableMap.map27525map,
                                GeneratedMutableMap.map27526map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ), listOf(
                                        GeneratedMutableMap.map27530map,
                                        GeneratedMutableMap.map27531map,
                                        GeneratedMutableMap.map27532map,
                                        GeneratedMutableMap.map27535map,
                                        GeneratedMutableMap.map27536map,
                                        GeneratedMutableMap.map27537map,
                                        GeneratedMutableMap.map27533map,
                                        GeneratedMutableMap.map27534map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27531map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map,
                                GeneratedMutableMap.map27533map,
                                GeneratedMutableMap.map27534map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27531map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map,
                                GeneratedMutableMap.map27533map,
                                GeneratedMutableMap.map27534map
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
                                        GeneratedMutableMap.map27523map,
                                        GeneratedMutableMap.map27524map,
                                        GeneratedMutableMap.map27522map,
                                        GeneratedMutableMap.map27527map,
                                        GeneratedMutableMap.map27528map,
                                        GeneratedMutableMap.map27529map,
                                        GeneratedMutableMap.map27525map,
                                        GeneratedMutableMap.map27526map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27525map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map,
                                GeneratedMutableMap.map27526map,
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27523map
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
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27525map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map,
                                GeneratedMutableMap.map27526map,
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27523map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ), listOf(
                                        GeneratedMutableMap.map27531map,
                                        GeneratedMutableMap.map27532map,
                                        GeneratedMutableMap.map27530map,
                                        GeneratedMutableMap.map27535map,
                                        GeneratedMutableMap.map27536map,
                                        GeneratedMutableMap.map27537map,
                                        GeneratedMutableMap.map27533map,
                                        GeneratedMutableMap.map27534map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27533map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map,
                                GeneratedMutableMap.map27534map,
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27531map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27533map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map,
                                GeneratedMutableMap.map27534map,
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27531map
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
                                        GeneratedMutableMap.map27522map,
                                        GeneratedMutableMap.map27523map,
                                        GeneratedMutableMap.map27524map,
                                        GeneratedMutableMap.map27805map,
                                        GeneratedMutableMap.map27806map,
                                        GeneratedMutableMap.map27527map,
                                        GeneratedMutableMap.map27528map,
                                        GeneratedMutableMap.map27529map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27523map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map
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
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27523map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ), listOf(
                                        GeneratedMutableMap.map27530map,
                                        GeneratedMutableMap.map27531map,
                                        GeneratedMutableMap.map27532map,
                                        GeneratedMutableMap.map27807map,
                                        GeneratedMutableMap.map27808map,
                                        GeneratedMutableMap.map27535map,
                                        GeneratedMutableMap.map27536map,
                                        GeneratedMutableMap.map27537map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27531map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27531map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map
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
                                        GeneratedMutableMap.map27522map,
                                        GeneratedMutableMap.map27523map,
                                        GeneratedMutableMap.map27524map,
                                        GeneratedMutableMap.map27805map,
                                        GeneratedMutableMap.map27806map,
                                        GeneratedMutableMap.map27527map,
                                        GeneratedMutableMap.map27528map,
                                        GeneratedMutableMap.map27529map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27523map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27529map
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
                                GeneratedMutableMap.map27523map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27529map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ), listOf(
                                        GeneratedMutableMap.map27530map,
                                        GeneratedMutableMap.map27531map,
                                        GeneratedMutableMap.map27532map,
                                        GeneratedMutableMap.map27807map,
                                        GeneratedMutableMap.map27808map,
                                        GeneratedMutableMap.map27535map,
                                        GeneratedMutableMap.map27536map,
                                        GeneratedMutableMap.map27537map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27531map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27537map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27531map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27537map
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
                                        GeneratedMutableMap.map27523map,
                                        GeneratedMutableMap.map27524map,
                                        GeneratedMutableMap.map27522map,
                                        GeneratedMutableMap.map27527map,
                                        GeneratedMutableMap.map27528map,
                                        GeneratedMutableMap.map27805map,
                                        GeneratedMutableMap.map27806map,
                                        GeneratedMutableMap.map27529map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27523map
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
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27522map,
                                GeneratedMutableMap.map27524map,
                                GeneratedMutableMap.map27523map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ), listOf(
                                        GeneratedMutableMap.map27531map,
                                        GeneratedMutableMap.map27532map,
                                        GeneratedMutableMap.map27530map,
                                        GeneratedMutableMap.map27535map,
                                        GeneratedMutableMap.map27536map,
                                        GeneratedMutableMap.map27807map,
                                        GeneratedMutableMap.map27808map,
                                        GeneratedMutableMap.map27537map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27531map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27530map,
                                GeneratedMutableMap.map27532map,
                                GeneratedMutableMap.map27531map
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
                                        GeneratedMutableMap.map10419map,
                                        GeneratedMutableMap.map10420map,
                                        GeneratedMutableMap.map33355map,
                                        GeneratedMutableMap.map10422map,
                                        GeneratedMutableMap.map33356map,
                                        GeneratedMutableMap.map10424map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map33356map,
                                GeneratedMutableMap.map33355map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10424map
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
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map33356map,
                                GeneratedMutableMap.map33355map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10424map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map10502map,
                                        GeneratedMutableMap.map10503map,
                                        GeneratedMutableMap.map33357map,
                                        GeneratedMutableMap.map10505map,
                                        GeneratedMutableMap.map33358map,
                                        GeneratedMutableMap.map10507map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map33358map,
                                GeneratedMutableMap.map33357map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10507map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map33358map,
                                GeneratedMutableMap.map33357map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10507map
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
                                        GeneratedMutableMap.map10422map,
                                        GeneratedMutableMap.map33356map,
                                        GeneratedMutableMap.map33355map,
                                        GeneratedMutableMap.map10420map,
                                        GeneratedMutableMap.map10419map,
                                        GeneratedMutableMap.map10424map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map33355map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map33356map,
                                GeneratedMutableMap.map10424map
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
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map33355map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map33356map,
                                GeneratedMutableMap.map10424map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map10505map,
                                        GeneratedMutableMap.map33358map,
                                        GeneratedMutableMap.map33357map,
                                        GeneratedMutableMap.map10503map,
                                        GeneratedMutableMap.map10502map,
                                        GeneratedMutableMap.map10507map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map33357map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map33358map,
                                GeneratedMutableMap.map10507map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map33357map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map33358map,
                                GeneratedMutableMap.map10507map
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
                                        GeneratedMutableMap.map10419map,
                                        GeneratedMutableMap.map33355map,
                                        GeneratedMutableMap.map10420map,
                                        GeneratedMutableMap.map10422map,
                                        GeneratedMutableMap.map33356map,
                                        GeneratedMutableMap.map10424map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map33355map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map33356map,
                                GeneratedMutableMap.map10424map
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
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map33355map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map33356map,
                                GeneratedMutableMap.map10424map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map10502map,
                                        GeneratedMutableMap.map33357map,
                                        GeneratedMutableMap.map10503map,
                                        GeneratedMutableMap.map10505map,
                                        GeneratedMutableMap.map33358map,
                                        GeneratedMutableMap.map10507map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map33357map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map33358map,
                                GeneratedMutableMap.map10507map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map33357map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map33358map,
                                GeneratedMutableMap.map10507map
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
                                        GeneratedMutableMap.map10611map,
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map33489map,
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map33490map,
                                        GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10744map,
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map33491map,
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map33492map,
                                        GeneratedMutableMap.map10749map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map10749map,
                                GeneratedMutableMap.map10744map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map10749map,
                                GeneratedMutableMap.map10744map
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
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map33489map,
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map33490map,
                                        GeneratedMutableMap.map10616map,
                                        GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map10616map,
                                GeneratedMutableMap.map10611map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map33491map,
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map33492map,
                                        GeneratedMutableMap.map10749map,
                                        GeneratedMutableMap.map10744map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map10749map,
                                GeneratedMutableMap.map10744map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map10749map,
                                GeneratedMutableMap.map10744map
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
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map33489map,
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map33490map,
                                        GeneratedMutableMap.map10616map,
                                        GeneratedMutableMap.map10611map
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
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10616map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map33491map,
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map33492map,
                                        GeneratedMutableMap.map10749map,
                                        GeneratedMutableMap.map10744map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10749map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10749map
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
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map33490map,
                                        GeneratedMutableMap.map33489map,
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map10611map,
                                        GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map10616map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map33492map,
                                        GeneratedMutableMap.map33491map,
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map10744map,
                                        GeneratedMutableMap.map10749map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map10749map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map10749map
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
                                        GeneratedMutableMap.map10611map,
                                        GeneratedMutableMap.map33489map,
                                        GeneratedMutableMap.map10612map,
                                        GeneratedMutableMap.map10614map,
                                        GeneratedMutableMap.map33490map,
                                        GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map10616map
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
                                GeneratedMutableMap.map10611map,
                                GeneratedMutableMap.map10612map,
                                GeneratedMutableMap.map33489map,
                                GeneratedMutableMap.map10614map,
                                GeneratedMutableMap.map33490map,
                                GeneratedMutableMap.map10616map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10744map,
                                        GeneratedMutableMap.map33491map,
                                        GeneratedMutableMap.map10745map,
                                        GeneratedMutableMap.map10747map,
                                        GeneratedMutableMap.map33492map,
                                        GeneratedMutableMap.map10749map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map10749map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10744map,
                                GeneratedMutableMap.map10745map,
                                GeneratedMutableMap.map33491map,
                                GeneratedMutableMap.map10747map,
                                GeneratedMutableMap.map33492map,
                                GeneratedMutableMap.map10749map
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
                                        GeneratedMutableMap.map35596map,
                                        GeneratedMutableMap.map35597map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map35596map,
                                GeneratedMutableMap.map35597map
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
                                GeneratedMutableMap.map35596map,
                                GeneratedMutableMap.map35597map
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
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ), listOf(
                                        GeneratedMutableMap.map35244map,
                                        GeneratedMutableMap.map35598map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map35244map,
                                GeneratedMutableMap.map35598map
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
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map35244map,
                                GeneratedMutableMap.map35598map
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
                                        GeneratedMutableMap.map37371map,
                                        GeneratedMutableMap.map37372map,
                                        GeneratedMutableMap.map37373map,
                                        GeneratedMutableMap.map37374map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37371map,
                                GeneratedMutableMap.map37372map,
                                GeneratedMutableMap.map37373map,
                                GeneratedMutableMap.map37374map
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
                                GeneratedMutableMap.map37371map,
                                GeneratedMutableMap.map37372map,
                                GeneratedMutableMap.map37373map,
                                GeneratedMutableMap.map37374map
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
                                LOPValues(listOf(
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map37375map,
                                        GeneratedMutableMap.map37376map,
                                        GeneratedMutableMap.map37377map,
                                        GeneratedMutableMap.map37378map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37375map,
                                GeneratedMutableMap.map37376map,
                                GeneratedMutableMap.map37377map,
                                GeneratedMutableMap.map37378map
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
                        LOPValues(listOf(
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37375map,
                                GeneratedMutableMap.map37376map,
                                GeneratedMutableMap.map37377map,
                                GeneratedMutableMap.map37378map
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
                                        "#_37191",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map37339map,
                                        GeneratedMutableMap.map37340map,
                                        GeneratedMutableMap.map37341map,
                                        GeneratedMutableMap.map37342map,
                                        GeneratedMutableMap.map37343map,
                                        GeneratedMutableMap.map37344map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#_37191",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37339map,
                                GeneratedMutableMap.map37342map,
                                GeneratedMutableMap.map37343map,
                                GeneratedMutableMap.map37340map,
                                GeneratedMutableMap.map37344map,
                                GeneratedMutableMap.map37341map
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
                                                    graph.addData(1L, listOf("_:_37168", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_37169", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                                    graph.addData(1L, listOf("_:_37170", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                                    graph.addData(1L, listOf("_:_37171", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_37172", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                                    graph.addData(1L, listOf("_:_37173", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_37174", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                                    graph.addData(1L, listOf("_:_37175", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_37176", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                                    graph.addData(1L, listOf("_:_37177", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_37191", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37168"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37169"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37170"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37171"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37172"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37173"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37174"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37175"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37176"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37177"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_37191", false, true, false, EIndexPattern.SPO)
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
                                "#_37191",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37339map,
                                GeneratedMutableMap.map37342map,
                                GeneratedMutableMap.map37343map,
                                GeneratedMutableMap.map37340map,
                                GeneratedMutableMap.map37344map,
                                GeneratedMutableMap.map37341map
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
                                LOPValues(listOf(
                                        AOPVariable("#_37191"),
                                        AOPVariable("L"),
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map37359map,
                                        GeneratedMutableMap.map37360map,
                                        GeneratedMutableMap.map37361map,
                                        GeneratedMutableMap.map37362map,
                                        GeneratedMutableMap.map37363map,
                                        GeneratedMutableMap.map37364map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#_37191"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37359map,
                                GeneratedMutableMap.map37362map,
                                GeneratedMutableMap.map37363map,
                                GeneratedMutableMap.map37360map,
                                GeneratedMutableMap.map37364map,
                                GeneratedMutableMap.map37361map
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
                                                    graph.addData(1L, listOf("_:_37168", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_37169", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                                    graph.addData(1L, listOf("_:_37170", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                                    graph.addData(1L, listOf("_:_37171", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_37172", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                                    graph.addData(1L, listOf("_:_37173", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_37174", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                                    graph.addData(1L, listOf("_:_37175", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_37176", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                                    graph.addData(1L, listOf("_:_37177", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("#_37191"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37168"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37169"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37170"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37171"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37172"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37173"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37174"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37175"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37176"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37177"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.example.orghasItem>"), AOPVariable("#_37191"), graphName, false)
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
                        LOPValues(listOf(
                                AOPVariable("#_37191"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37359map,
                                GeneratedMutableMap.map37362map,
                                GeneratedMutableMap.map37363map,
                                GeneratedMutableMap.map37360map,
                                GeneratedMutableMap.map37364map,
                                GeneratedMutableMap.map37361map
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
                                        "#_37620",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map37768map,
                                        GeneratedMutableMap.map37769map,
                                        GeneratedMutableMap.map37770map,
                                        GeneratedMutableMap.map37771map,
                                        GeneratedMutableMap.map37772map,
                                        GeneratedMutableMap.map37773map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#_37620",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37768map,
                                GeneratedMutableMap.map37771map,
                                GeneratedMutableMap.map37772map,
                                GeneratedMutableMap.map37769map,
                                GeneratedMutableMap.map37773map,
                                GeneratedMutableMap.map37770map
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
                                                    graph.addData(1L, listOf("_:_37597", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_37598", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                                    graph.addData(1L, listOf("_:_37599", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                                    graph.addData(1L, listOf("_:_37600", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_37601", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                                    graph.addData(1L, listOf("_:_37602", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_37603", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                                    graph.addData(1L, listOf("_:_37604", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_37605", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                                    graph.addData(1L, listOf("_:_37606", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_37620", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37597"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37598"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37599"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37600"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37601"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37602"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37603"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37604"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37605"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37606"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_37620", false, true, false, EIndexPattern.SPO)
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
                                "#_37620",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37768map,
                                GeneratedMutableMap.map37771map,
                                GeneratedMutableMap.map37772map,
                                GeneratedMutableMap.map37769map,
                                GeneratedMutableMap.map37773map,
                                GeneratedMutableMap.map37770map
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
                                LOPValues(listOf(
                                        AOPVariable("#_37620"),
                                        AOPVariable("L"),
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map37788map,
                                        GeneratedMutableMap.map37789map,
                                        GeneratedMutableMap.map37790map,
                                        GeneratedMutableMap.map37791map,
                                        GeneratedMutableMap.map37792map,
                                        GeneratedMutableMap.map37793map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#_37620"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37788map,
                                GeneratedMutableMap.map37791map,
                                GeneratedMutableMap.map37792map,
                                GeneratedMutableMap.map37789map,
                                GeneratedMutableMap.map37793map,
                                GeneratedMutableMap.map37790map
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
                                                    graph.addData(1L, listOf("_:_37597", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_37598", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                                    graph.addData(1L, listOf("_:_37599", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                                    graph.addData(1L, listOf("_:_37600", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                                    graph.addData(1L, listOf("_:_37601", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                                    graph.addData(1L, listOf("_:_37602", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_37603", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                                    graph.addData(1L, listOf("_:_37604", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    graph.addData(1L, listOf("_:_37605", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                                    graph.addData(1L, listOf("_:_37606", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("#_37620"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37597"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37598"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37599"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37600"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37601"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37602"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37603"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37604"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37605"))
                                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37606"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.example.orghasItem>"), AOPVariable("#_37620"), graphName, false)
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
                        LOPValues(listOf(
                                AOPVariable("#_37620"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37788map,
                                GeneratedMutableMap.map37791map,
                                GeneratedMutableMap.map37792map,
                                GeneratedMutableMap.map37789map,
                                GeneratedMutableMap.map37793map,
                                GeneratedMutableMap.map37790map
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
                } else if (data.input is LOPBase && data is MicroTestLN) {
                    val lop_node = data.input as LOPBase
                    val dictionary = data.dictionary
                    ExecuteOptimizer.enabledOptimizers.clear()
                    val lOptimizer = LogicalOptimizer(1L, dictionary)
                    val pOptimizer = PhysicalOptimizer(1L, dictionary)
                    val dOptimizer = KeyDistributionOptimizer(1L, dictionary)
                    val lop_node2 = lOptimizer.optimizeCall(lop_node)
                    val pop_node = pOptimizer.optimizeCall(lop_node2)
                    val input = dOptimizer.optimizeCall(pop_node) as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)) {
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    assertTrue(expected.myEquals(output))
                    for (k in ExecuteOptimizer.enabledOptimizers.keys) {
                        ExecuteOptimizer.enabledOptimizers[k] = true
                        val lop_node2 = lOptimizer.optimizeCall(lop_node)
                        val pop_node = pOptimizer.optimizeCall(lop_node2)
                        val input = dOptimizer.optimizeCall(pop_node) as POPBase
                        assertTrue(data.expected is POPValues)
                        val output = QueryResultToXML.toXML(input).first()
                        val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                        if (!expected.myEquals(output)) {
                            println(ExecuteOptimizer.enabledOptimizers.keys.map { it to ExecuteOptimizer.enabledOptimizers[it] })
                            println(output.toPrettyString())
                            println(expected.toPrettyString())
                        }
                        assertTrue(expected.myEquals(output))
                    }
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                assertTrue(data.expected is Throwable)
            }
        }
    }
}
