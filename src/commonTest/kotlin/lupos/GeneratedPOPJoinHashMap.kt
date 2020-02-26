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


class GeneratedPOPJoinHashMapTest {
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
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "S",
                                        "O1"
                                ), listOf(
                                        GeneratedMutableMap.map2789map,
                                        GeneratedMutableMap.map2790map,
                                        GeneratedMutableMap.map2791map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "S",
                                        "O2"
                                ), listOf(
                                        GeneratedMutableMap.map2796map,
                                        GeneratedMutableMap.map2797map,
                                        GeneratedMutableMap.map2798map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2"
                        ), listOf(
                                GeneratedMutableMap.map2800map,
                                GeneratedMutableMap.map2801map,
                                GeneratedMutableMap.map2802map,
                                GeneratedMutableMap.map2803map,
                                GeneratedMutableMap.map2804map,
                                GeneratedMutableMap.map2805map,
                                GeneratedMutableMap.map2806map,
                                GeneratedMutableMap.map2807map,
                                GeneratedMutableMap.map2808map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "S", "<http://www.example.org/p>", "O1", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "S", "<http://www.example.org/q>", "O2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2"
                        ), listOf(
                                GeneratedMutableMap.map2800map,
                                GeneratedMutableMap.map2801map,
                                GeneratedMutableMap.map2802map,
                                GeneratedMutableMap.map2803map,
                                GeneratedMutableMap.map2804map,
                                GeneratedMutableMap.map2805map,
                                GeneratedMutableMap.map2806map,
                                GeneratedMutableMap.map2807map,
                                GeneratedMutableMap.map2808map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("S"),
                                        AOPVariable("O1")
                                ), listOf(
                                        GeneratedMutableMap.map2792map,
                                        GeneratedMutableMap.map2793map,
                                        GeneratedMutableMap.map2794map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("S"),
                                        AOPVariable("O2")
                                ), listOf(
                                        GeneratedMutableMap.map2792map,
                                        GeneratedMutableMap.map2793map,
                                        GeneratedMutableMap.map2794map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O1"),
                                AOPVariable("O2")
                        ), listOf(
                                GeneratedMutableMap.map2809map,
                                GeneratedMutableMap.map2810map,
                                GeneratedMutableMap.map2811map,
                                GeneratedMutableMap.map2812map,
                                GeneratedMutableMap.map2813map,
                                GeneratedMutableMap.map2814map,
                                GeneratedMutableMap.map2815map,
                                GeneratedMutableMap.map2816map,
                                GeneratedMutableMap.map2817map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("S"), AOPVariable.calculate("<http://www.example.org/p>"), AOPVariable("O1"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("S"), AOPVariable.calculate("<http://www.example.org/q>"), AOPVariable("O2"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O1"),
                                AOPVariable("O2")
                        ), listOf(
                                GeneratedMutableMap.map2809map,
                                GeneratedMutableMap.map2810map,
                                GeneratedMutableMap.map2811map,
                                GeneratedMutableMap.map2812map,
                                GeneratedMutableMap.map2813map,
                                GeneratedMutableMap.map2814map,
                                GeneratedMutableMap.map2815map,
                                GeneratedMutableMap.map2816map,
                                GeneratedMutableMap.map2817map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5965map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5966map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map5966map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5966map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5966map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map5966map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5966map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "count"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O",
                            "count"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5965map
                            )
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5965map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5965map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode2.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5965map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode2.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5965map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6398map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6398map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6398map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6398map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6398map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6398map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "S",
                            "P",
                            "O"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map6784map,
                                GeneratedMutableMap.map6785map,
                                GeneratedMutableMap.map6786map,
                                GeneratedMutableMap.map6787map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7260map,
                                GeneratedMutableMap.map7261map,
                                GeneratedMutableMap.map7262map,
                                GeneratedMutableMap.map7263map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7264map,
                            GeneratedMutableMap.map7265map,
                            GeneratedMutableMap.map7266map,
                            GeneratedMutableMap.map7267map,
                            GeneratedMutableMap.map7268map,
                            GeneratedMutableMap.map7269map,
                            GeneratedMutableMap.map7270map,
                            GeneratedMutableMap.map7271map,
                            GeneratedMutableMap.map7272map,
                            GeneratedMutableMap.map7273map,
                            GeneratedMutableMap.map7274map,
                            GeneratedMutableMap.map7275map,
                            GeneratedMutableMap.map7276map,
                            GeneratedMutableMap.map7277map,
                            GeneratedMutableMap.map7278map,
                            GeneratedMutableMap.map7279map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/bind/bind03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map6784map,
                                        GeneratedMutableMap.map6785map,
                                        GeneratedMutableMap.map6786map,
                                        GeneratedMutableMap.map6787map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map7792map,
                                        GeneratedMutableMap.map7792map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7359map,
                                GeneratedMutableMap.map7360map,
                                GeneratedMutableMap.map7361map,
                                GeneratedMutableMap.map7362map,
                                GeneratedMutableMap.map7359map,
                                GeneratedMutableMap.map7360map,
                                GeneratedMutableMap.map7361map,
                                GeneratedMutableMap.map7362map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map8579map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8583map,
                                        GeneratedMutableMap.map8584map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8588map,
                                GeneratedMutableMap.map8589map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://purl.org/dc/elements/1.1/title>", "\"SPARQL Tutorial\""))
                                    graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://purl.org/dc/elements/1.1/title>", "\"The Semantic Web\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "book", "<http://purl.org/dc/elements/1.1/title>", "title", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://example.org/ns#price>", "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://example.org/ns#price>", "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "book", "<http://example.org/ns#price>", "price", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8588map,
                                GeneratedMutableMap.map8589map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("book"),
                                        AOPVariable("title")
                                ), listOf(
                                        GeneratedMutableMap.map8580map,
                                        GeneratedMutableMap.map8581map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("book"),
                                        AOPVariable("price")
                                ), listOf(
                                        GeneratedMutableMap.map8585map,
                                        GeneratedMutableMap.map8586map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("title"),
                                AOPVariable("price")
                        ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map8591map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://purl.org/dc/elements/1.1/title>", "\"SPARQL Tutorial\""))
                                    graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://purl.org/dc/elements/1.1/title>", "\"The Semantic Web\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("book"), AOPVariable.calculate("<http://purl.org/dc/elements/1.1/title>"), AOPVariable("title"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://example.org/ns#price>", "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://example.org/ns#price>", "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("book"), AOPVariable.calculate("<http://example.org/ns#price>"), AOPVariable("price"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("title"),
                                AOPVariable("price")
                        ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map8591map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book"
                                ), listOf(
                                        GeneratedMutableMap.map8592map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8588map,
                                        GeneratedMutableMap.map8589map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8588map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8650map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8651map,
                                        GeneratedMutableMap.map8652map,
                                        GeneratedMutableMap.map8653map,
                                        GeneratedMutableMap.map8654map,
                                        GeneratedMutableMap.map8655map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8656map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8748map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8749map,
                                        GeneratedMutableMap.map8750map,
                                        GeneratedMutableMap.map8751map,
                                        GeneratedMutableMap.map8752map,
                                        GeneratedMutableMap.map8753map,
                                        GeneratedMutableMap.map8754map,
                                        GeneratedMutableMap.map8755map,
                                        GeneratedMutableMap.map8756map,
                                        GeneratedMutableMap.map8757map,
                                        GeneratedMutableMap.map8758map,
                                        GeneratedMutableMap.map8759map,
                                        GeneratedMutableMap.map8760map,
                                        GeneratedMutableMap.map8761map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8762map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        GeneratedMutableMap.map8764map,
                                        GeneratedMutableMap.map8765map,
                                        GeneratedMutableMap.map8766map,
                                        GeneratedMutableMap.map8767map,
                                        GeneratedMutableMap.map8768map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8770map,
                                        GeneratedMutableMap.map8771map,
                                        GeneratedMutableMap.map8772map,
                                        GeneratedMutableMap.map8773map,
                                        GeneratedMutableMap.map8774map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map8776map,
                                GeneratedMutableMap.map8777map,
                                GeneratedMutableMap.map8778map,
                                GeneratedMutableMap.map8779map,
                                GeneratedMutableMap.map8780map,
                                GeneratedMutableMap.map8781map,
                                GeneratedMutableMap.map8782map,
                                GeneratedMutableMap.map8783map,
                                GeneratedMutableMap.map8784map,
                                GeneratedMutableMap.map8785map,
                                GeneratedMutableMap.map8786map,
                                GeneratedMutableMap.map8787map,
                                GeneratedMutableMap.map8788map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p1", "o1", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p2", "o2", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map8776map,
                                GeneratedMutableMap.map8777map,
                                GeneratedMutableMap.map8778map,
                                GeneratedMutableMap.map8779map,
                                GeneratedMutableMap.map8780map,
                                GeneratedMutableMap.map8781map,
                                GeneratedMutableMap.map8782map,
                                GeneratedMutableMap.map8783map,
                                GeneratedMutableMap.map8784map,
                                GeneratedMutableMap.map8785map,
                                GeneratedMutableMap.map8786map,
                                GeneratedMutableMap.map8787map,
                                GeneratedMutableMap.map8788map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p1"),
                                        AOPVariable("o1")
                                ), listOf(
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map8665map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map8665map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p1"),
                                AOPVariable("o1"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8789map,
                                GeneratedMutableMap.map8790map,
                                GeneratedMutableMap.map8791map,
                                GeneratedMutableMap.map8792map,
                                GeneratedMutableMap.map8793map,
                                GeneratedMutableMap.map8794map,
                                GeneratedMutableMap.map8795map,
                                GeneratedMutableMap.map8796map,
                                GeneratedMutableMap.map8797map,
                                GeneratedMutableMap.map8798map,
                                GeneratedMutableMap.map8799map,
                                GeneratedMutableMap.map8800map,
                                GeneratedMutableMap.map8801map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p1"), AOPVariable("o1"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p1"),
                                AOPVariable("o1"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8789map,
                                GeneratedMutableMap.map8790map,
                                GeneratedMutableMap.map8791map,
                                GeneratedMutableMap.map8792map,
                                GeneratedMutableMap.map8793map,
                                GeneratedMutableMap.map8794map,
                                GeneratedMutableMap.map8795map,
                                GeneratedMutableMap.map8796map,
                                GeneratedMutableMap.map8797map,
                                GeneratedMutableMap.map8798map,
                                GeneratedMutableMap.map8799map,
                                GeneratedMutableMap.map8800map,
                                GeneratedMutableMap.map8801map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8890map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8749map,
                                        GeneratedMutableMap.map8750map,
                                        GeneratedMutableMap.map8751map,
                                        GeneratedMutableMap.map8752map,
                                        GeneratedMutableMap.map8753map,
                                        GeneratedMutableMap.map8754map,
                                        GeneratedMutableMap.map8755map,
                                        GeneratedMutableMap.map8756map,
                                        GeneratedMutableMap.map8757map,
                                        GeneratedMutableMap.map8758map,
                                        GeneratedMutableMap.map8759map,
                                        GeneratedMutableMap.map8760map,
                                        GeneratedMutableMap.map8761map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8891map,
                                GeneratedMutableMap.map8762map,
                                GeneratedMutableMap.map8892map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8980map,
                                        GeneratedMutableMap.map8981map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8749map,
                                        GeneratedMutableMap.map8750map,
                                        GeneratedMutableMap.map8751map,
                                        GeneratedMutableMap.map8752map,
                                        GeneratedMutableMap.map8753map,
                                        GeneratedMutableMap.map8754map,
                                        GeneratedMutableMap.map8755map,
                                        GeneratedMutableMap.map8756map,
                                        GeneratedMutableMap.map8757map,
                                        GeneratedMutableMap.map8758map,
                                        GeneratedMutableMap.map8759map,
                                        GeneratedMutableMap.map8760map,
                                        GeneratedMutableMap.map8761map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8982map,
                                GeneratedMutableMap.map8983map,
                                GeneratedMutableMap.map8984map,
                                GeneratedMutableMap.map8984map,
                                GeneratedMutableMap.map8985map,
                                GeneratedMutableMap.map8892map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "p1"
                                ), listOf(
                                        GeneratedMutableMap.map9048map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        GeneratedMutableMap.map8764map,
                                        GeneratedMutableMap.map8765map,
                                        GeneratedMutableMap.map8766map,
                                        GeneratedMutableMap.map8767map,
                                        GeneratedMutableMap.map8768map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p1",
                                "s",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map9049map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map9131map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8749map,
                                        GeneratedMutableMap.map8750map,
                                        GeneratedMutableMap.map8751map,
                                        GeneratedMutableMap.map9132map,
                                        GeneratedMutableMap.map9133map,
                                        GeneratedMutableMap.map9134map,
                                        GeneratedMutableMap.map9135map,
                                        GeneratedMutableMap.map9136map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o2",
                                "s",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map9137map,
                                GeneratedMutableMap.map9138map,
                                GeneratedMutableMap.map9139map,
                                GeneratedMutableMap.map9140map,
                                GeneratedMutableMap.map9141map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        GeneratedMutableMap.map8764map,
                                        GeneratedMutableMap.map8765map,
                                        GeneratedMutableMap.map8766map,
                                        GeneratedMutableMap.map9147map,
                                        GeneratedMutableMap.map8767map,
                                        GeneratedMutableMap.map8768map,
                                        GeneratedMutableMap.map9148map,
                                        GeneratedMutableMap.map9149map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map9143map,
                                        GeneratedMutableMap.map9144map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map9154map,
                                GeneratedMutableMap.map9155map,
                                GeneratedMutableMap.map9156map,
                                GeneratedMutableMap.map9157map,
                                GeneratedMutableMap.map9158map,
                                GeneratedMutableMap.map9159map,
                                GeneratedMutableMap.map9160map,
                                GeneratedMutableMap.map9161map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alice@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Alice\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p1", "o1", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "o2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map9154map,
                                GeneratedMutableMap.map9155map,
                                GeneratedMutableMap.map9156map,
                                GeneratedMutableMap.map9157map,
                                GeneratedMutableMap.map9158map,
                                GeneratedMutableMap.map9159map,
                                GeneratedMutableMap.map9160map,
                                GeneratedMutableMap.map9161map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p1"),
                                        AOPVariable("o1")
                                ), listOf(
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map8665map,
                                        GeneratedMutableMap.map9150map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map,
                                        GeneratedMutableMap.map9151map,
                                        GeneratedMutableMap.map9152map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map8668map,
                                        GeneratedMutableMap.map9145map
                                )
                                ),
                                true),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p1"),
                                AOPVariable("o1"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map9162map,
                                GeneratedMutableMap.map9163map,
                                GeneratedMutableMap.map9164map,
                                GeneratedMutableMap.map9165map,
                                GeneratedMutableMap.map9166map,
                                GeneratedMutableMap.map9167map,
                                GeneratedMutableMap.map9168map,
                                GeneratedMutableMap.map9169map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alice@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Alice\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p1"), AOPVariable("o1"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("o2"), graphName, false)
                                }()
                                ,
                                true),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p1"),
                                AOPVariable("o1"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map9162map,
                                GeneratedMutableMap.map9163map,
                                GeneratedMutableMap.map9164map,
                                GeneratedMutableMap.map9165map,
                                GeneratedMutableMap.map9166map,
                                GeneratedMutableMap.map9167map,
                                GeneratedMutableMap.map9168map,
                                GeneratedMutableMap.map9169map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        GeneratedMutableMap.map9277map,
                                        GeneratedMutableMap.map9278map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8588map,
                                        GeneratedMutableMap.map8589map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8588map,
                                GeneratedMutableMap.map8589map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book"
                                ), listOf(
                                        GeneratedMutableMap.map8592map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map8579map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                        ), listOf(
                                GeneratedMutableMap.map8578map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        GeneratedMutableMap.map8578map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8583map,
                                        GeneratedMutableMap.map8584map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8588map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
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
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10606map,
                                GeneratedMutableMap.map10607map,
                                GeneratedMutableMap.map10608map,
                                GeneratedMutableMap.map10609map,
                                GeneratedMutableMap.map10610map
                            )
                        ),
                        true                    ),
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
                        POPJoinHashMap(
                                dictionary,
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
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10605map,
                                        GeneratedMutableMap.map10606map,
                                        GeneratedMutableMap.map10607map,
                                        GeneratedMutableMap.map10608map,
                                        GeneratedMutableMap.map10609map,
                                        GeneratedMutableMap.map10610map
                                )
                                ),
                                true),
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
                                true),
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
                        LOPJoin(
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
                                ),
                                LOPValues(listOf(
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10502map,
                                        GeneratedMutableMap.map10503map,
                                        GeneratedMutableMap.map10504map,
                                        GeneratedMutableMap.map10505map,
                                        GeneratedMutableMap.map10506map,
                                        GeneratedMutableMap.map10507map
                                )
                                ),
                                true),
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
                                true),
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
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5965map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11513map,
                                GeneratedMutableMap.map11513map,
                                GeneratedMutableMap.map11513map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete-insert/delete-insert-01c.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11513map,
                                GeneratedMutableMap.map11513map,
                                GeneratedMutableMap.map11513map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11513map,
                                GeneratedMutableMap.map11513map,
                                GeneratedMutableMap.map11513map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map,
                            GeneratedMutableMap.map11513map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete-insert/delete-insert-01c.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a"
                                ), listOf(
                                        GeneratedMutableMap.map11674map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "b"
                                ), listOf(
                                        GeneratedMutableMap.map11400map,
                                        GeneratedMutableMap.map11401map,
                                        GeneratedMutableMap.map11402map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                        ), listOf(
                                GeneratedMutableMap.map11400map,
                                GeneratedMutableMap.map11401map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\"", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/knows>", "b", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                        ), listOf(
                                GeneratedMutableMap.map11400map,
                                GeneratedMutableMap.map11401map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("a")
                                ), listOf(
                                        GeneratedMutableMap.map11675map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("a"),
                                        AOPVariable("b")
                                ), listOf(
                                        GeneratedMutableMap.map8668map,
                                        GeneratedMutableMap.map11403map,
                                        GeneratedMutableMap.map9145map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("b")
                        ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map11403map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"), AOPVariable.calculate("\"Alan\""), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("b"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("b")
                        ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map11403map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a"
                                ), listOf(
                                        GeneratedMutableMap.map11674map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        GeneratedMutableMap.map11913map,
                                        GeneratedMutableMap.map11914map,
                                        GeneratedMutableMap.map11915map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11913map,
                                GeneratedMutableMap.map11914map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\"", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/knows>", "Var_B", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11913map,
                                GeneratedMutableMap.map11914map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("a")
                                ), listOf(
                                        GeneratedMutableMap.map11675map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("a"),
                                        AOPVariable("Var_B")
                                ), listOf(
                                        GeneratedMutableMap.map8668map,
                                        GeneratedMutableMap.map11403map,
                                        GeneratedMutableMap.map9145map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                        ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map11403map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"), AOPVariable.calculate("\"Alan\""), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("Var_B"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                        ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map11403map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a"
                                ), listOf(
                                        GeneratedMutableMap.map11674map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        GeneratedMutableMap.map11915map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\"", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/knows>", "Var_B", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("a")
                                ), listOf(
                                        GeneratedMutableMap.map11675map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("a"),
                                        AOPVariable("Var_B")
                                ), listOf(
                                        GeneratedMutableMap.map9145map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"), AOPVariable.calculate("\"Alan\""), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("Var_B"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map13081map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8658map,
                                        GeneratedMutableMap.map8659map,
                                        GeneratedMutableMap.map8660map,
                                        GeneratedMutableMap.map8661map,
                                        GeneratedMutableMap.map8662map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "s", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s")
                                ), listOf(
                                        GeneratedMutableMap.map13082map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map8665map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/a>"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("s"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8658map,
                                        GeneratedMutableMap.map8659map,
                                        GeneratedMutableMap.map8660map,
                                        GeneratedMutableMap.map8661map,
                                        GeneratedMutableMap.map8662map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s")
                                ), listOf(
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map8665map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable.calculate("<http://example.org/c>"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map13502map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8658map,
                                        GeneratedMutableMap.map11150map,
                                        GeneratedMutableMap.map8661map,
                                        GeneratedMutableMap.map8662map,
                                        GeneratedMutableMap.map11151map,
                                        GeneratedMutableMap.map11152map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11151map,
                                GeneratedMutableMap.map11152map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\"", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11151map,
                                GeneratedMutableMap.map11152map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s")
                                ), listOf(
                                        GeneratedMutableMap.map13503map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map9150map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map,
                                        GeneratedMutableMap.map11153map,
                                        GeneratedMutableMap.map11154map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map11153map,
                                GeneratedMutableMap.map11154map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"), AOPVariable.calculate("\"Chris\""), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map11153map,
                                GeneratedMutableMap.map11154map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map13081map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8658map,
                                        GeneratedMutableMap.map11150map,
                                        GeneratedMutableMap.map8661map,
                                        GeneratedMutableMap.map8662map,
                                        GeneratedMutableMap.map11151map,
                                        GeneratedMutableMap.map11152map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11150map,
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "s", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11150map,
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s")
                                ), listOf(
                                        GeneratedMutableMap.map13082map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map9150map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map,
                                        GeneratedMutableMap.map11153map,
                                        GeneratedMutableMap.map11154map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9150map,
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/a>"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("s"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9150map,
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map11156map,
                                        GeneratedMutableMap.map11151map,
                                        GeneratedMutableMap.map11152map,
                                        GeneratedMutableMap.map11157map,
                                        GeneratedMutableMap.map11158map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "s", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/d>"))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/mbox>", "\"dan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/name>", "\"Dan\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s")
                                ), listOf(
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map11159map,
                                        GeneratedMutableMap.map11153map,
                                        GeneratedMutableMap.map11154map,
                                        GeneratedMutableMap.map11160map,
                                        GeneratedMutableMap.map11161map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/a>"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("s"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/d>"))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/mbox>", "\"dan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/name>", "\"Dan\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8658map,
                                        GeneratedMutableMap.map11150map,
                                        GeneratedMutableMap.map8661map,
                                        GeneratedMutableMap.map8662map,
                                        GeneratedMutableMap.map11151map,
                                        GeneratedMutableMap.map11152map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/d>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s")
                                ), listOf(
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map9150map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map,
                                        GeneratedMutableMap.map11153map,
                                        GeneratedMutableMap.map11154map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable.calculate("<http://example.org/d>"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                                GeneratedMutableMap.map13502map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map11156map,
                                GeneratedMutableMap.map11151map,
                                GeneratedMutableMap.map11152map,
                                GeneratedMutableMap.map11157map,
                                GeneratedMutableMap.map11158map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11156map,
                            GeneratedMutableMap.map11151map,
                            GeneratedMutableMap.map11152map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map14273map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8658map,
                                        GeneratedMutableMap.map8659map,
                                        GeneratedMutableMap.map8660map,
                                        GeneratedMutableMap.map8661map,
                                        GeneratedMutableMap.map8662map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8658map,
                                GeneratedMutableMap.map8659map,
                                GeneratedMutableMap.map8660map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8658map,
                                GeneratedMutableMap.map8659map,
                                GeneratedMutableMap.map8660map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s")
                                ), listOf(
                                        GeneratedMutableMap.map11675map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map8665map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable.calculate("<http://example.org/b>"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                                    graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16119map,
                                GeneratedMutableMap.map16120map,
                                GeneratedMutableMap.map16121map,
                                GeneratedMutableMap.map16122map,
                                GeneratedMutableMap.map16123map,
                                GeneratedMutableMap.map16124map,
                                GeneratedMutableMap.map16125map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16118map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16122map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16388map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16388map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map16388map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "c"
                                ), listOf(
                                        GeneratedMutableMap.map16584map,
                                        GeneratedMutableMap.map16585map,
                                        GeneratedMutableMap.map16586map,
                                        GeneratedMutableMap.map16587map,
                                        GeneratedMutableMap.map16119map,
                                        GeneratedMutableMap.map16120map,
                                        GeneratedMutableMap.map16121map,
                                        GeneratedMutableMap.map16588map,
                                        GeneratedMutableMap.map16589map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "c"
                                ), listOf(
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/d>"))
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "_:x"))
                                    graph.addData(1L, listOf("<http://example.org/x/c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                                    graph.addData(1L, listOf("<http://example.org/x/d>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                                    graph.addData(1L, listOf("<http://example.org/x/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                    graph.addData(1L, listOf("_:ont", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Ontology>"))
                                    graph.addData(1L, listOf("_:x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Restriction>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "c", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "c", "<http://www.w3.org/2000/01/rdf-schema#subClassOf>", "<http://example.org/x/c>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("c")
                                ), listOf(
                                        GeneratedMutableMap.map16590map,
                                        GeneratedMutableMap.map16591map,
                                        GeneratedMutableMap.map16592map,
                                        GeneratedMutableMap.map16593map,
                                        GeneratedMutableMap.map16594map,
                                        GeneratedMutableMap.map16595map,
                                        GeneratedMutableMap.map16596map,
                                        GeneratedMutableMap.map16597map,
                                        GeneratedMutableMap.map16598map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("c")
                                ), listOf(
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("c")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/d>"))
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                    graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "_:x"))
                                    graph.addData(1L, listOf("<http://example.org/x/c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                                    graph.addData(1L, listOf("<http://example.org/x/d>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                                    graph.addData(1L, listOf("<http://example.org/x/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                    graph.addData(1L, listOf("_:ont", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Ontology>"))
                                    graph.addData(1L, listOf("_:x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Restriction>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable("c"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("c"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#subClassOf>"), AOPVariable.calculate("<http://example.org/x/c>"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("c")
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map16665map,
                                        GeneratedMutableMap.map16666map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map16660map,
                                        GeneratedMutableMap.map16661map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16665map,
                                GeneratedMutableMap.map16666map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "<http://example.org/x/y>"))
                                    graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "_:y"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://example.org/x/p>", "y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/y>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    graph.addData(1L, listOf("_:y", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "y", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16665map,
                                GeneratedMutableMap.map16666map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map16667map,
                                        GeneratedMutableMap.map16668map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map16662map,
                                        GeneratedMutableMap.map16663map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map16667map,
                                GeneratedMutableMap.map16668map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "<http://example.org/x/y>"))
                                    graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "_:y"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://example.org/x/p>"), AOPVariable("y"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/y>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    graph.addData(1L, listOf("_:y", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("y"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://example.org/x/c>"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map16667map,
                                GeneratedMutableMap.map16668map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#c"
                            ), listOf(
                                GeneratedMutableMap.map16879map,
                                GeneratedMutableMap.map16880map,
                                GeneratedMutableMap.map16881map,
                                GeneratedMutableMap.map16882map,
                                GeneratedMutableMap.map16883map,
                                GeneratedMutableMap.map16884map,
                                GeneratedMutableMap.map16885map,
                                GeneratedMutableMap.map16886map,
                                GeneratedMutableMap.map16887map,
                                GeneratedMutableMap.map16888map,
                                GeneratedMutableMap.map16889map,
                                GeneratedMutableMap.map16890map,
                                GeneratedMutableMap.map16891map,
                                GeneratedMutableMap.map16892map,
                                GeneratedMutableMap.map16893map,
                                GeneratedMutableMap.map16894map,
                                GeneratedMutableMap.map16895map,
                                GeneratedMutableMap.map16896map,
                                GeneratedMutableMap.map16897map,
                                GeneratedMutableMap.map16898map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16774"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16774"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#c",
                                "#_16774",
                                "#_16779"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16899map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16774",
                            "#_16779",
                            "y"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0"
                            ), listOf(
                                GeneratedMutableMap.map17056map,
                                GeneratedMutableMap.map17057map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16920"
                            ), listOf(
                                GeneratedMutableMap.map17034map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16920"
                        ), listOf(
                            GeneratedMutableMap.map17058map,
                            GeneratedMutableMap.map17059map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16920"
                            ), listOf(
                                GeneratedMutableMap.map17058map,
                                GeneratedMutableMap.map17059map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16920"
                            ), listOf(
                                GeneratedMutableMap.map17034map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16920"
                        ), listOf(
                            GeneratedMutableMap.map17058map,
                            GeneratedMutableMap.map17059map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16920"
                            ), listOf(
                                GeneratedMutableMap.map17058map,
                                GeneratedMutableMap.map17059map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16929"
                            ), listOf(
                                GeneratedMutableMap.map17028map,
                                GeneratedMutableMap.map17029map,
                                GeneratedMutableMap.map17030map,
                                GeneratedMutableMap.map17031map,
                                GeneratedMutableMap.map17032map,
                                GeneratedMutableMap.map17033map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16920",
                            "#_16929"
                        ), listOf(
                            GeneratedMutableMap.map17060map,
                            GeneratedMutableMap.map17061map,
                            GeneratedMutableMap.map17062map,
                            GeneratedMutableMap.map17063map,
                            GeneratedMutableMap.map17064map,
                            GeneratedMutableMap.map17065map,
                            GeneratedMutableMap.map17066map,
                            GeneratedMutableMap.map17067map,
                            GeneratedMutableMap.map17068map,
                            GeneratedMutableMap.map17069map,
                            GeneratedMutableMap.map17070map,
                            GeneratedMutableMap.map17071map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16920",
                                "#_16929"
                            ), listOf(
                                GeneratedMutableMap.map17060map,
                                GeneratedMutableMap.map17061map,
                                GeneratedMutableMap.map17062map,
                                GeneratedMutableMap.map17063map,
                                GeneratedMutableMap.map17064map,
                                GeneratedMutableMap.map17065map,
                                GeneratedMutableMap.map17066map,
                                GeneratedMutableMap.map17067map,
                                GeneratedMutableMap.map17068map,
                                GeneratedMutableMap.map17069map,
                                GeneratedMutableMap.map17070map,
                                GeneratedMutableMap.map17071map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16929"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16920",
                            "#_16929"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16920",
                                "#_16929"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16920",
                                "#_16929"
                            ), listOf(
                                GeneratedMutableMap.map17035map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16920",
                            "#_16929"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16920",
                                "#_16929"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#_16920"
                            ), listOf(
                                GeneratedMutableMap.map17036map,
                                GeneratedMutableMap.map17037map,
                                GeneratedMutableMap.map17038map,
                                GeneratedMutableMap.map17039map,
                                GeneratedMutableMap.map17040map,
                                GeneratedMutableMap.map17041map,
                                GeneratedMutableMap.map17042map,
                                GeneratedMutableMap.map17043map,
                                GeneratedMutableMap.map17044map,
                                GeneratedMutableMap.map17045map,
                                GeneratedMutableMap.map17046map,
                                GeneratedMutableMap.map17047map,
                                GeneratedMutableMap.map17048map,
                                GeneratedMutableMap.map17049map,
                                GeneratedMutableMap.map17050map,
                                GeneratedMutableMap.map17051map,
                                GeneratedMutableMap.map17052map,
                                GeneratedMutableMap.map17053map,
                                GeneratedMutableMap.map17054map,
                                GeneratedMutableMap.map17055map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16920",
                            "#_16929"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map17146map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map17147map,
                                GeneratedMutableMap.map17148map,
                                GeneratedMutableMap.map17149map,
                                GeneratedMutableMap.map17150map,
                                GeneratedMutableMap.map17151map,
                                GeneratedMutableMap.map17152map,
                                GeneratedMutableMap.map17153map,
                                GeneratedMutableMap.map17154map,
                                GeneratedMutableMap.map17155map,
                                GeneratedMutableMap.map17156map,
                                GeneratedMutableMap.map17157map,
                                GeneratedMutableMap.map17158map,
                                GeneratedMutableMap.map17159map,
                                GeneratedMutableMap.map17160map,
                                GeneratedMutableMap.map17161map,
                                GeneratedMutableMap.map17162map,
                                GeneratedMutableMap.map17163map,
                                GeneratedMutableMap.map17164map,
                                GeneratedMutableMap.map17165map,
                                GeneratedMutableMap.map17166map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map17147map,
                            GeneratedMutableMap.map17148map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map17147map,
                                GeneratedMutableMap.map17148map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16711map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        GeneratedMutableMap.map17231map,
                                        GeneratedMutableMap.map17232map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map17236map,
                                        GeneratedMutableMap.map17237map,
                                        GeneratedMutableMap.map17238map,
                                        GeneratedMutableMap.map17239map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map17236map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/hasPublication>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                    graph.addData(1L, listOf("<http://example.org/publishedAt>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "p", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/hasPublication>", "<http://example.org/paper1>"))
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/name>", "\"Johnnie\""))
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/GraduateAssistant>"))
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/John>", "p", "v", true, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map17236map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("p")
                                ), listOf(
                                        GeneratedMutableMap.map17233map,
                                        GeneratedMutableMap.map17234map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("p"),
                                        AOPVariable("v")
                                ), listOf(
                                        GeneratedMutableMap.map17240map,
                                        GeneratedMutableMap.map17241map,
                                        GeneratedMutableMap.map17242map,
                                        GeneratedMutableMap.map17243map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("v")
                        ), listOf(
                                GeneratedMutableMap.map17240map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/hasPublication>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                    graph.addData(1L, listOf("<http://example.org/publishedAt>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("p"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://www.w3.org/2002/07/owl#ObjectProperty>"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/hasPublication>", "<http://example.org/paper1>"))
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/name>", "\"Johnnie\""))
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/GraduateAssistant>"))
                                    graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/John>"), AOPVariable("p"), AOPVariable("v"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("v")
                        ), listOf(
                                GeneratedMutableMap.map17240map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map17395map,
                                        GeneratedMutableMap.map6784map,
                                        GeneratedMutableMap.map6785map,
                                        GeneratedMutableMap.map6786map,
                                        GeneratedMutableMap.map6787map,
                                        GeneratedMutableMap.map17396map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        GeneratedMutableMap.map17392map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map6784map,
                                GeneratedMutableMap.map6785map,
                                GeneratedMutableMap.map6786map,
                                GeneratedMutableMap.map6787map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("_:1", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Ontology>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "p", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map6784map,
                                GeneratedMutableMap.map6785map,
                                GeneratedMutableMap.map6786map,
                                GeneratedMutableMap.map6787map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map17397map,
                                        GeneratedMutableMap.map6788map,
                                        GeneratedMutableMap.map6789map,
                                        GeneratedMutableMap.map6790map,
                                        GeneratedMutableMap.map6791map,
                                        GeneratedMutableMap.map17398map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("p")
                                ), listOf(
                                        GeneratedMutableMap.map17393map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map6788map,
                                GeneratedMutableMap.map6789map,
                                GeneratedMutableMap.map6790map,
                                GeneratedMutableMap.map6791map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("_:1", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Ontology>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("p"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map6788map,
                                GeneratedMutableMap.map6789map,
                                GeneratedMutableMap.map6790map,
                                GeneratedMutableMap.map6791map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map17395map,
                                GeneratedMutableMap.map6784map,
                                GeneratedMutableMap.map6785map,
                                GeneratedMutableMap.map6786map,
                                GeneratedMutableMap.map6787map,
                                GeneratedMutableMap.map17396map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map17392map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map6784map,
                            GeneratedMutableMap.map6785map,
                            GeneratedMutableMap.map6786map,
                            GeneratedMutableMap.map6787map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map6784map,
                                GeneratedMutableMap.map6785map,
                                GeneratedMutableMap.map6786map,
                                GeneratedMutableMap.map6787map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map17889map,
                                GeneratedMutableMap.map7260map,
                                GeneratedMutableMap.map7261map,
                                GeneratedMutableMap.map7262map,
                                GeneratedMutableMap.map7263map,
                                GeneratedMutableMap.map17890map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map17892map,
                            GeneratedMutableMap.map17893map,
                            GeneratedMutableMap.map17894map,
                            GeneratedMutableMap.map17895map,
                            GeneratedMutableMap.map7264map,
                            GeneratedMutableMap.map7265map,
                            GeneratedMutableMap.map7266map,
                            GeneratedMutableMap.map7267map,
                            GeneratedMutableMap.map7268map,
                            GeneratedMutableMap.map7269map,
                            GeneratedMutableMap.map7270map,
                            GeneratedMutableMap.map7271map,
                            GeneratedMutableMap.map7272map,
                            GeneratedMutableMap.map7273map,
                            GeneratedMutableMap.map7274map,
                            GeneratedMutableMap.map7275map,
                            GeneratedMutableMap.map7276map,
                            GeneratedMutableMap.map7277map,
                            GeneratedMutableMap.map7278map,
                            GeneratedMutableMap.map7279map,
                            GeneratedMutableMap.map17896map,
                            GeneratedMutableMap.map17897map,
                            GeneratedMutableMap.map17898map,
                            GeneratedMutableMap.map17899map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map17892map,
                                GeneratedMutableMap.map17893map,
                                GeneratedMutableMap.map17894map,
                                GeneratedMutableMap.map17895map,
                                GeneratedMutableMap.map7264map,
                                GeneratedMutableMap.map7265map,
                                GeneratedMutableMap.map7266map,
                                GeneratedMutableMap.map7267map,
                                GeneratedMutableMap.map7268map,
                                GeneratedMutableMap.map7269map,
                                GeneratedMutableMap.map7270map,
                                GeneratedMutableMap.map7271map,
                                GeneratedMutableMap.map7272map,
                                GeneratedMutableMap.map7273map,
                                GeneratedMutableMap.map7274map,
                                GeneratedMutableMap.map7275map,
                                GeneratedMutableMap.map7276map,
                                GeneratedMutableMap.map7277map,
                                GeneratedMutableMap.map7278map,
                                GeneratedMutableMap.map7279map,
                                GeneratedMutableMap.map17896map,
                                GeneratedMutableMap.map17897map,
                                GeneratedMutableMap.map17898map,
                                GeneratedMutableMap.map17899map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p1"
                            ), listOf(
                                GeneratedMutableMap.map17891map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7264map,
                            GeneratedMutableMap.map7265map,
                            GeneratedMutableMap.map7266map,
                            GeneratedMutableMap.map7267map,
                            GeneratedMutableMap.map7268map,
                            GeneratedMutableMap.map7269map,
                            GeneratedMutableMap.map7270map,
                            GeneratedMutableMap.map7271map,
                            GeneratedMutableMap.map7272map,
                            GeneratedMutableMap.map7273map,
                            GeneratedMutableMap.map7274map,
                            GeneratedMutableMap.map7275map,
                            GeneratedMutableMap.map7276map,
                            GeneratedMutableMap.map7277map,
                            GeneratedMutableMap.map7278map,
                            GeneratedMutableMap.map7279map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16119map,
                                GeneratedMutableMap.map16121map,
                                GeneratedMutableMap.map16122map,
                                GeneratedMutableMap.map16588map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16584map,
                                GeneratedMutableMap.map16586map,
                                GeneratedMutableMap.map16119map,
                                GeneratedMutableMap.map16120map,
                                GeneratedMutableMap.map16121map,
                                GeneratedMutableMap.map16122map,
                                GeneratedMutableMap.map18896map,
                                GeneratedMutableMap.map16123map,
                                GeneratedMutableMap.map18897map,
                                GeneratedMutableMap.map18898map,
                                GeneratedMutableMap.map16589map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#y"
                            ), listOf(
                                GeneratedMutableMap.map18895map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c",
                            "#y"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X"
                                ), listOf(
                                        GeneratedMutableMap.map18977map,
                                        GeneratedMutableMap.map18978map,
                                        GeneratedMutableMap.map18979map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1"
                                ), listOf(
                                        GeneratedMutableMap.map18972map,
                                        GeneratedMutableMap.map18973map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                GeneratedMutableMap.map18972map,
                                GeneratedMutableMap.map18973map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                    graph.addData(1L, listOf("<http://example.org/test#c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>", false, true, true, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#name>", "\"A\""))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#name>", "\"B\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#name>", "Y1", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                GeneratedMutableMap.map18972map,
                                GeneratedMutableMap.map18973map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("X")
                                ), listOf(
                                        GeneratedMutableMap.map18980map,
                                        GeneratedMutableMap.map17287map,
                                        GeneratedMutableMap.map18981map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y1")
                                ), listOf(
                                        GeneratedMutableMap.map18974map,
                                        GeneratedMutableMap.map18975map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1")
                        ), listOf(
                                GeneratedMutableMap.map18974map,
                                GeneratedMutableMap.map18975map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                    graph.addData(1L, listOf("<http://example.org/test#c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://example.org/test#Person>"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#name>", "\"A\""))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#name>", "\"B\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#name>"), AOPVariable("Y1"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1")
                        ), listOf(
                                GeneratedMutableMap.map18974map,
                                GeneratedMutableMap.map18975map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1"
                                ), listOf(
                                        GeneratedMutableMap.map18972map,
                                        GeneratedMutableMap.map18973map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y2"
                                ), listOf(
                                        GeneratedMutableMap.map18984map,
                                        GeneratedMutableMap.map18985map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1",
                                "Y2"
                        ), listOf(
                                GeneratedMutableMap.map18989map,
                                GeneratedMutableMap.map18990map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPJoinHashMap(
                                        dictionary,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                            graph.addData(1L, listOf("<http://example.org/test#c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>", false, true, true, EIndexPattern.SPO)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#name>", "\"A\""))
                                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#name>", "\"B\""))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#name>", "Y1", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        false)
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#nick>", "\"Anick\""))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#nick>", "\"Bnick\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#nick>", "Y2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1",
                                "Y2"
                        ), listOf(
                                GeneratedMutableMap.map18989map,
                                GeneratedMutableMap.map18990map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y1")
                                ), listOf(
                                        GeneratedMutableMap.map18974map,
                                        GeneratedMutableMap.map18975map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y2")
                                ), listOf(
                                        GeneratedMutableMap.map18986map,
                                        GeneratedMutableMap.map18987map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1"),
                                AOPVariable("Y2")
                        ), listOf(
                                GeneratedMutableMap.map18991map,
                                GeneratedMutableMap.map18992map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPJoin(
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                            graph.addData(1L, listOf("<http://example.org/test#c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://example.org/test#Person>"), graphName, false)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#name>", "\"A\""))
                                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#name>", "\"B\""))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#name>"), AOPVariable("Y1"), graphName, false)
                                        }()
                                        ,
                                        false)
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#nick>", "\"Anick\""))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#nick>", "\"Bnick\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#nick>"), AOPVariable("Y2"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1"),
                                AOPVariable("Y2")
                        ), listOf(
                                GeneratedMutableMap.map18991map,
                                GeneratedMutableMap.map18992map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#aa"
                            ), listOf(
                                GeneratedMutableMap.map19125map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                GeneratedMutableMap.map19122map,
                                GeneratedMutableMap.map19123map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd"
                        ), listOf(
                            GeneratedMutableMap.map19122map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                GeneratedMutableMap.map19122map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#bb"
                            ), listOf(
                                GeneratedMutableMap.map19124map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd",
                            "#bb"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#aa"
                                ), listOf(
                                        GeneratedMutableMap.map19125map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map19218map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19223map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/test#a>", "<http://example.org/test#p>", "#aa", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#t>", "Y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19223map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("#aa")
                                ), listOf(
                                        GeneratedMutableMap.map19221map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ), listOf(
                                        GeneratedMutableMap.map19219map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19224map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/test#a>"), AOPVariable.calculate("<http://example.org/test#p>"), AOPVariable("#aa"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#t>"), AOPVariable("Y"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19224map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "X",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map19223map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "Y",
                                        "#aa"
                                ), listOf(
                                        GeneratedMutableMap.map19215map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19223map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPJoinHashMap(
                                        dictionary,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/test#a>", "<http://example.org/test#p>", "#aa", true, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#t>", "Y", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        false)
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#bb>", "<http://example.org/test#s>", "<http://example.org/test#aa>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "Y", "<http://example.org/test#s>", "#aa", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19223map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("#aa"),
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ), listOf(
                                        GeneratedMutableMap.map19224map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("Y"),
                                        AOPVariable("#aa")
                                ), listOf(
                                        GeneratedMutableMap.map19216map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19224map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPJoin(
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable.calculate("<http://example.org/test#a>"), AOPVariable.calculate("<http://example.org/test#p>"), AOPVariable("#aa"), graphName, false)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#t>"), AOPVariable("Y"), graphName, false)
                                        }()
                                        ,
                                        false)
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#bb>", "<http://example.org/test#s>", "<http://example.org/test#aa>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("Y"), AOPVariable.calculate("<http://example.org/test#s>"), AOPVariable("#aa"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19224map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "X",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map19223map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "Z"
                                ), listOf(
                                        GeneratedMutableMap.map19210map,
                                        GeneratedMutableMap.map19211map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map19227map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPJoinHashMap(
                                        dictionary,
                                        POPJoinHashMap(
                                                dictionary,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/test#a>", "<http://example.org/test#p>", "#aa", true, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#t>", "Y", false, true, false, EIndexPattern.SPO)
                                                }()
                                                ,
                                                false)
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#bb>", "<http://example.org/test#s>", "<http://example.org/test#aa>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "Y", "<http://example.org/test#s>", "#aa", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        false)
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                                    graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#aa", "<http://example.org/test#r>", "Z", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map19227map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("#aa"),
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ), listOf(
                                        GeneratedMutableMap.map19224map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("#aa"),
                                        AOPVariable("Z")
                                ), listOf(
                                        GeneratedMutableMap.map19212map,
                                        GeneratedMutableMap.map19213map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y"),
                                AOPVariable("Z")
                        ), listOf(
                                GeneratedMutableMap.map19228map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPJoin(
                                        LOPJoin(
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable.calculate("<http://example.org/test#a>"), AOPVariable.calculate("<http://example.org/test#p>"), AOPVariable("#aa"), graphName, false)
                                                }()
                                                ,
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#t>"), AOPVariable("Y"), graphName, false)
                                                }()
                                                ,
                                                false)
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/test#bb>", "<http://example.org/test#s>", "<http://example.org/test#aa>"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("Y"), AOPVariable.calculate("<http://example.org/test#s>"), AOPVariable("#aa"), graphName, false)
                                        }()
                                        ,
                                        false)
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                                    graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("#aa"), AOPVariable.calculate("<http://example.org/test#r>"), AOPVariable("Z"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y"),
                                AOPVariable("Z")
                        ), listOf(
                                GeneratedMutableMap.map19228map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a"
                                ), listOf(
                                        GeneratedMutableMap.map19286map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map19289map,
                                        GeneratedMutableMap.map19290map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19292map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#p>", "#a", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                                    graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#a", "<http://example.org/test#r>", "Y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19292map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("#a")
                                ), listOf(
                                        GeneratedMutableMap.map19287map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("#a"),
                                        AOPVariable("Y")
                                ), listOf(
                                        GeneratedMutableMap.map19212map,
                                        GeneratedMutableMap.map19213map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19293map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#p>"), AOPVariable("#a"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                                    graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("#a"), AOPVariable.calculate("<http://example.org/test#r>"), AOPVariable("Y"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19293map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a"
                                ), listOf(
                                        GeneratedMutableMap.map19354map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map19357map,
                                        GeneratedMutableMap.map19358map,
                                        GeneratedMutableMap.map19359map,
                                        GeneratedMutableMap.map19360map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19366map,
                                GeneratedMutableMap.map19367map,
                                GeneratedMutableMap.map19368map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#b>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#p>", "#a", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#c>"))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#h>"))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#i>"))
                                    graph.addData(1L, listOf("<http://example.org/test#x>", "<http://example.org/test#q>", "<http://example.org/test#x>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#a", "<http://example.org/test#q>", "Y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19366map,
                                GeneratedMutableMap.map19367map,
                                GeneratedMutableMap.map19368map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("#a")
                                ), listOf(
                                        GeneratedMutableMap.map19355map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("#a"),
                                        AOPVariable("Y")
                                ), listOf(
                                        GeneratedMutableMap.map19361map,
                                        GeneratedMutableMap.map19362map,
                                        GeneratedMutableMap.map19363map,
                                        GeneratedMutableMap.map19364map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19369map,
                                GeneratedMutableMap.map19370map,
                                GeneratedMutableMap.map19371map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#b>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#p>"), AOPVariable("#a"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#c>"))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#h>"))
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#i>"))
                                    graph.addData(1L, listOf("<http://example.org/test#x>", "<http://example.org/test#q>", "<http://example.org/test#x>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("#a"), AOPVariable.calculate("<http://example.org/test#q>"), AOPVariable("Y"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19369map,
                                GeneratedMutableMap.map19370map,
                                GeneratedMutableMap.map19371map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "a"
                            ), listOf(
                                GeneratedMutableMap.map19465map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "a",
                            "b"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "a",
                                "b",
                                "Y"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map19466map,
                                GeneratedMutableMap.map19467map,
                                GeneratedMutableMap.map19468map,
                                GeneratedMutableMap.map19469map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "a",
                            "b",
                            "Y",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map19638map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19634map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19639map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19639map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19635map,
                                GeneratedMutableMap.map19636map,
                                GeneratedMutableMap.map19637map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19639map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19758"
                            ), listOf(
                                GeneratedMutableMap.map19824map,
                                GeneratedMutableMap.map19825map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19758"
                            ), listOf(
                                GeneratedMutableMap.map19824map,
                                GeneratedMutableMap.map19825map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19758"
                        ), listOf(
                            GeneratedMutableMap.map19824map,
                            GeneratedMutableMap.map19825map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19758"
                            ), listOf(
                                GeneratedMutableMap.map19824map,
                                GeneratedMutableMap.map19825map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19758"
                            ), listOf(
                                GeneratedMutableMap.map19824map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19758"
                        ), listOf(
                            GeneratedMutableMap.map19824map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19758"
                            ), listOf(
                                GeneratedMutableMap.map19824map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19758"
                            ), listOf(
                                GeneratedMutableMap.map19826map,
                                GeneratedMutableMap.map19827map,
                                GeneratedMutableMap.map19828map,
                                GeneratedMutableMap.map19829map,
                                GeneratedMutableMap.map19830map,
                                GeneratedMutableMap.map19831map,
                                GeneratedMutableMap.map19832map,
                                GeneratedMutableMap.map19833map,
                                GeneratedMutableMap.map19834map,
                                GeneratedMutableMap.map19835map,
                                GeneratedMutableMap.map19836map,
                                GeneratedMutableMap.map19837map,
                                GeneratedMutableMap.map19838map,
                                GeneratedMutableMap.map19839map,
                                GeneratedMutableMap.map19840map,
                                GeneratedMutableMap.map19841map,
                                GeneratedMutableMap.map19842map,
                                GeneratedMutableMap.map19843map,
                                GeneratedMutableMap.map19844map,
                                GeneratedMutableMap.map19845map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19758",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19870"
                            ), listOf(
                                GeneratedMutableMap.map19941map,
                                GeneratedMutableMap.map19942map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19870"
                            ), listOf(
                                GeneratedMutableMap.map19941map,
                                GeneratedMutableMap.map19942map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19870"
                        ), listOf(
                            GeneratedMutableMap.map19941map,
                            GeneratedMutableMap.map19942map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19870"
                            ), listOf(
                                GeneratedMutableMap.map19941map,
                                GeneratedMutableMap.map19942map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19870"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19870"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19870"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19870"
                            ), listOf(
                                GeneratedMutableMap.map19943map,
                                GeneratedMutableMap.map19944map,
                                GeneratedMutableMap.map19945map,
                                GeneratedMutableMap.map19946map,
                                GeneratedMutableMap.map19947map,
                                GeneratedMutableMap.map19948map,
                                GeneratedMutableMap.map19949map,
                                GeneratedMutableMap.map19950map,
                                GeneratedMutableMap.map19951map,
                                GeneratedMutableMap.map19952map,
                                GeneratedMutableMap.map19953map,
                                GeneratedMutableMap.map19954map,
                                GeneratedMutableMap.map19955map,
                                GeneratedMutableMap.map19956map,
                                GeneratedMutableMap.map19957map,
                                GeneratedMutableMap.map19958map,
                                GeneratedMutableMap.map19959map,
                                GeneratedMutableMap.map19960map,
                                GeneratedMutableMap.map19961map,
                                GeneratedMutableMap.map19962map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19870",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19984"
                            ), listOf(
                                GeneratedMutableMap.map20050map,
                                GeneratedMutableMap.map20051map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19984"
                            ), listOf(
                                GeneratedMutableMap.map20050map,
                                GeneratedMutableMap.map20051map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19984"
                        ), listOf(
                            GeneratedMutableMap.map20050map,
                            GeneratedMutableMap.map20051map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19984"
                            ), listOf(
                                GeneratedMutableMap.map20050map,
                                GeneratedMutableMap.map20051map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19984"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19984"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19984"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19984"
                            ), listOf(
                                GeneratedMutableMap.map20052map,
                                GeneratedMutableMap.map20053map,
                                GeneratedMutableMap.map20054map,
                                GeneratedMutableMap.map20055map,
                                GeneratedMutableMap.map20056map,
                                GeneratedMutableMap.map20057map,
                                GeneratedMutableMap.map20058map,
                                GeneratedMutableMap.map20059map,
                                GeneratedMutableMap.map20060map,
                                GeneratedMutableMap.map20061map,
                                GeneratedMutableMap.map20062map,
                                GeneratedMutableMap.map20063map,
                                GeneratedMutableMap.map20064map,
                                GeneratedMutableMap.map20065map,
                                GeneratedMutableMap.map20066map,
                                GeneratedMutableMap.map20067map,
                                GeneratedMutableMap.map20068map,
                                GeneratedMutableMap.map20069map,
                                GeneratedMutableMap.map20070map,
                                GeneratedMutableMap.map20071map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19984",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20094"
                            ), listOf(
                                GeneratedMutableMap.map20176map,
                                GeneratedMutableMap.map20177map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20094"
                            ), listOf(
                                GeneratedMutableMap.map20176map,
                                GeneratedMutableMap.map20177map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20094"
                        ), listOf(
                            GeneratedMutableMap.map20176map,
                            GeneratedMutableMap.map20177map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20094"
                            ), listOf(
                                GeneratedMutableMap.map20176map,
                                GeneratedMutableMap.map20177map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20094"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20094"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20094"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20094"
                            ), listOf(
                                GeneratedMutableMap.map20178map,
                                GeneratedMutableMap.map20179map,
                                GeneratedMutableMap.map20180map,
                                GeneratedMutableMap.map20181map,
                                GeneratedMutableMap.map20182map,
                                GeneratedMutableMap.map20183map,
                                GeneratedMutableMap.map20184map,
                                GeneratedMutableMap.map20185map,
                                GeneratedMutableMap.map20186map,
                                GeneratedMutableMap.map20187map,
                                GeneratedMutableMap.map20188map,
                                GeneratedMutableMap.map20189map,
                                GeneratedMutableMap.map20190map,
                                GeneratedMutableMap.map20191map,
                                GeneratedMutableMap.map20192map,
                                GeneratedMutableMap.map20193map,
                                GeneratedMutableMap.map20194map,
                                GeneratedMutableMap.map20195map,
                                GeneratedMutableMap.map20196map,
                                GeneratedMutableMap.map20197map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20094",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20220"
                            ), listOf(
                                GeneratedMutableMap.map20302map,
                                GeneratedMutableMap.map20303map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20220"
                            ), listOf(
                                GeneratedMutableMap.map20302map,
                                GeneratedMutableMap.map20303map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20220"
                        ), listOf(
                            GeneratedMutableMap.map20302map,
                            GeneratedMutableMap.map20303map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20220"
                            ), listOf(
                                GeneratedMutableMap.map20302map,
                                GeneratedMutableMap.map20303map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20220"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20220"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20220"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20220"
                            ), listOf(
                                GeneratedMutableMap.map20304map,
                                GeneratedMutableMap.map20305map,
                                GeneratedMutableMap.map20306map,
                                GeneratedMutableMap.map20307map,
                                GeneratedMutableMap.map20308map,
                                GeneratedMutableMap.map20309map,
                                GeneratedMutableMap.map20310map,
                                GeneratedMutableMap.map20311map,
                                GeneratedMutableMap.map20312map,
                                GeneratedMutableMap.map20313map,
                                GeneratedMutableMap.map20314map,
                                GeneratedMutableMap.map20315map,
                                GeneratedMutableMap.map20316map,
                                GeneratedMutableMap.map20317map,
                                GeneratedMutableMap.map20318map,
                                GeneratedMutableMap.map20319map,
                                GeneratedMutableMap.map20320map,
                                GeneratedMutableMap.map20321map,
                                GeneratedMutableMap.map20322map,
                                GeneratedMutableMap.map20323map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20220",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20346"
                            ), listOf(
                                GeneratedMutableMap.map20428map,
                                GeneratedMutableMap.map20429map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20346"
                            ), listOf(
                                GeneratedMutableMap.map20428map,
                                GeneratedMutableMap.map20429map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20346"
                        ), listOf(
                            GeneratedMutableMap.map20428map,
                            GeneratedMutableMap.map20429map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20346"
                            ), listOf(
                                GeneratedMutableMap.map20428map,
                                GeneratedMutableMap.map20429map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20346"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20346"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20346"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20346"
                            ), listOf(
                                GeneratedMutableMap.map20430map,
                                GeneratedMutableMap.map20431map,
                                GeneratedMutableMap.map20432map,
                                GeneratedMutableMap.map20433map,
                                GeneratedMutableMap.map20434map,
                                GeneratedMutableMap.map20435map,
                                GeneratedMutableMap.map20436map,
                                GeneratedMutableMap.map20437map,
                                GeneratedMutableMap.map20438map,
                                GeneratedMutableMap.map20439map,
                                GeneratedMutableMap.map20440map,
                                GeneratedMutableMap.map20441map,
                                GeneratedMutableMap.map20442map,
                                GeneratedMutableMap.map20443map,
                                GeneratedMutableMap.map20444map,
                                GeneratedMutableMap.map20445map,
                                GeneratedMutableMap.map20446map,
                                GeneratedMutableMap.map20447map,
                                GeneratedMutableMap.map20448map,
                                GeneratedMutableMap.map20449map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20346",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20473"
                            ), listOf(
                                GeneratedMutableMap.map20541map,
                                GeneratedMutableMap.map20542map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20473"
                            ), listOf(
                                GeneratedMutableMap.map20541map,
                                GeneratedMutableMap.map20542map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20473"
                        ), listOf(
                            GeneratedMutableMap.map20541map,
                            GeneratedMutableMap.map20542map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20473"
                            ), listOf(
                                GeneratedMutableMap.map20541map,
                                GeneratedMutableMap.map20542map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20473"
                            ), listOf(
                                GeneratedMutableMap.map20541map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20473"
                        ), listOf(
                            GeneratedMutableMap.map20541map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20473"
                            ), listOf(
                                GeneratedMutableMap.map20541map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#_20473"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20473",
                            "C"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "C",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b"
                            ), listOf(
                                GeneratedMutableMap.map20647map,
                                GeneratedMutableMap.map20648map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#b"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "C",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b"
                            ), listOf(
                                GeneratedMutableMap.map20647map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#b"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20665",
                                "#_20670",
                                "#_20662"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20662"
                            ), listOf(
                                GeneratedMutableMap.map20754map,
                                GeneratedMutableMap.map20755map,
                                GeneratedMutableMap.map20756map,
                                GeneratedMutableMap.map20757map,
                                GeneratedMutableMap.map20758map,
                                GeneratedMutableMap.map20759map,
                                GeneratedMutableMap.map20760map,
                                GeneratedMutableMap.map20761map,
                                GeneratedMutableMap.map20762map,
                                GeneratedMutableMap.map20763map,
                                GeneratedMutableMap.map20764map,
                                GeneratedMutableMap.map20765map,
                                GeneratedMutableMap.map20766map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20665",
                            "#_20670",
                            "#_20662",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple1.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20783",
                                "#_20787",
                                "#_20797",
                                "#_20780"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20780"
                            ), listOf(
                                GeneratedMutableMap.map20911map,
                                GeneratedMutableMap.map20912map,
                                GeneratedMutableMap.map20913map,
                                GeneratedMutableMap.map20914map,
                                GeneratedMutableMap.map20915map,
                                GeneratedMutableMap.map20916map,
                                GeneratedMutableMap.map20917map,
                                GeneratedMutableMap.map20918map,
                                GeneratedMutableMap.map20919map,
                                GeneratedMutableMap.map20920map,
                                GeneratedMutableMap.map20921map,
                                GeneratedMutableMap.map20922map,
                                GeneratedMutableMap.map20923map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20783",
                            "#_20787",
                            "#_20797",
                            "#_20780",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple2.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20937",
                                "#_20948",
                                "#_20953",
                                "#_20945"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20937"
                            ), listOf(
                                GeneratedMutableMap.map21068map,
                                GeneratedMutableMap.map21069map,
                                GeneratedMutableMap.map21070map,
                                GeneratedMutableMap.map21071map,
                                GeneratedMutableMap.map21072map,
                                GeneratedMutableMap.map21073map,
                                GeneratedMutableMap.map21074map,
                                GeneratedMutableMap.map21075map,
                                GeneratedMutableMap.map21076map,
                                GeneratedMutableMap.map21077map,
                                GeneratedMutableMap.map21078map,
                                GeneratedMutableMap.map21079map,
                                GeneratedMutableMap.map21080map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20937",
                            "#_20948",
                            "#_20953",
                            "#_20945",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21094"
                            ), listOf(
                                GeneratedMutableMap.map21211map,
                                GeneratedMutableMap.map21212map,
                                GeneratedMutableMap.map21213map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21099"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21094",
                            "#_21099"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21094",
                                "#_21099",
                                "#_21104"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21094"
                            ), listOf(
                                GeneratedMutableMap.map21198map,
                                GeneratedMutableMap.map21199map,
                                GeneratedMutableMap.map21200map,
                                GeneratedMutableMap.map21201map,
                                GeneratedMutableMap.map21202map,
                                GeneratedMutableMap.map21203map,
                                GeneratedMutableMap.map21204map,
                                GeneratedMutableMap.map21205map,
                                GeneratedMutableMap.map21206map,
                                GeneratedMutableMap.map21207map,
                                GeneratedMutableMap.map21208map,
                                GeneratedMutableMap.map21209map,
                                GeneratedMutableMap.map21210map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21094",
                            "#_21099",
                            "#_21104",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21227"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21235"
                            ), listOf(
                                GeneratedMutableMap.map21370map,
                                GeneratedMutableMap.map21371map,
                                GeneratedMutableMap.map21372map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21227",
                            "#_21235"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21227",
                                "#_21235",
                                "#_21240",
                                "#_21245"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21227"
                            ), listOf(
                                GeneratedMutableMap.map21373map,
                                GeneratedMutableMap.map21374map,
                                GeneratedMutableMap.map21375map,
                                GeneratedMutableMap.map21376map,
                                GeneratedMutableMap.map21377map,
                                GeneratedMutableMap.map21378map,
                                GeneratedMutableMap.map21379map,
                                GeneratedMutableMap.map21380map,
                                GeneratedMutableMap.map21381map,
                                GeneratedMutableMap.map21382map,
                                GeneratedMutableMap.map21383map,
                                GeneratedMutableMap.map21384map,
                                GeneratedMutableMap.map21385map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21227",
                            "#_21235",
                            "#_21240",
                            "#_21245",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21399"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21407"
                            ), listOf(
                                GeneratedMutableMap.map21567map,
                                GeneratedMutableMap.map21568map,
                                GeneratedMutableMap.map21569map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21399",
                            "#_21407"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21399",
                                "#_21407",
                                "#_21412",
                                "#_21417",
                                "#_21422"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21399"
                            ), listOf(
                                GeneratedMutableMap.map21570map,
                                GeneratedMutableMap.map21571map,
                                GeneratedMutableMap.map21572map,
                                GeneratedMutableMap.map21573map,
                                GeneratedMutableMap.map21574map,
                                GeneratedMutableMap.map21575map,
                                GeneratedMutableMap.map21576map,
                                GeneratedMutableMap.map21577map,
                                GeneratedMutableMap.map21578map,
                                GeneratedMutableMap.map21579map,
                                GeneratedMutableMap.map21580map,
                                GeneratedMutableMap.map21581map,
                                GeneratedMutableMap.map21582map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21399",
                            "#_21407",
                            "#_21412",
                            "#_21417",
                            "#_21422",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21596"
                            ), listOf(
                                GeneratedMutableMap.map21775map,
                                GeneratedMutableMap.map21776map,
                                GeneratedMutableMap.map21777map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21601"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21596",
                            "#_21601"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21596",
                                "#_21601"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21605"
                            ), listOf(
                                GeneratedMutableMap.map21778map,
                                GeneratedMutableMap.map21779map,
                                GeneratedMutableMap.map21780map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21596",
                            "#_21601",
                            "#_21605"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21596",
                                "#_21601",
                                "#_21605",
                                "#_21610",
                                "#_21615",
                                "#_21621"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21596"
                            ), listOf(
                                GeneratedMutableMap.map21781map,
                                GeneratedMutableMap.map21782map,
                                GeneratedMutableMap.map21783map,
                                GeneratedMutableMap.map21784map,
                                GeneratedMutableMap.map21785map,
                                GeneratedMutableMap.map21786map,
                                GeneratedMutableMap.map21787map,
                                GeneratedMutableMap.map21788map,
                                GeneratedMutableMap.map21789map,
                                GeneratedMutableMap.map21790map,
                                GeneratedMutableMap.map21791map,
                                GeneratedMutableMap.map21792map,
                                GeneratedMutableMap.map21793map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21596",
                            "#_21601",
                            "#_21605",
                            "#_21610",
                            "#_21615",
                            "#_21621",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21807",
                                "#_21815"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21807"
                            ), listOf(
                                GeneratedMutableMap.map21914map,
                                GeneratedMutableMap.map21915map,
                                GeneratedMutableMap.map21916map,
                                GeneratedMutableMap.map21917map,
                                GeneratedMutableMap.map21918map,
                                GeneratedMutableMap.map21919map,
                                GeneratedMutableMap.map21920map,
                                GeneratedMutableMap.map21921map,
                                GeneratedMutableMap.map21922map,
                                GeneratedMutableMap.map21923map,
                                GeneratedMutableMap.map21924map,
                                GeneratedMutableMap.map21925map,
                                GeneratedMutableMap.map21926map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21807",
                            "#_21815",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple8.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "str1"
                                ), listOf(
                                        GeneratedMutableMap.map24947map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map24944map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24950map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s6>", "<http://example.org/str>", "str1", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s7>", "<http://example.org/str>", "str2", true, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24950map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("str1")
                                ), listOf(
                                        GeneratedMutableMap.map24948map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("str2")
                                ), listOf(
                                        GeneratedMutableMap.map24945map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("str1"),
                                AOPVariable("str2")
                        ), listOf(
                                GeneratedMutableMap.map24951map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/s6>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str1"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/s7>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str2"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("str1"),
                                AOPVariable("str2")
                        ), listOf(
                                GeneratedMutableMap.map24951map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s1",
                                        "str1"
                                ), listOf(
                                        GeneratedMutableMap.map25550map,
                                        GeneratedMutableMap.map25551map,
                                        GeneratedMutableMap.map25552map,
                                        GeneratedMutableMap.map25553map,
                                        GeneratedMutableMap.map25554map,
                                        GeneratedMutableMap.map25555map,
                                        GeneratedMutableMap.map25556map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s2",
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map25565map,
                                        GeneratedMutableMap.map25566map,
                                        GeneratedMutableMap.map25567map,
                                        GeneratedMutableMap.map25568map,
                                        GeneratedMutableMap.map25569map,
                                        GeneratedMutableMap.map25570map,
                                        GeneratedMutableMap.map25571map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map25573map,
                                GeneratedMutableMap.map25574map,
                                GeneratedMutableMap.map25575map,
                                GeneratedMutableMap.map25576map,
                                GeneratedMutableMap.map25577map,
                                GeneratedMutableMap.map25578map,
                                GeneratedMutableMap.map25579map,
                                GeneratedMutableMap.map25580map,
                                GeneratedMutableMap.map25581map,
                                GeneratedMutableMap.map25582map,
                                GeneratedMutableMap.map25583map,
                                GeneratedMutableMap.map25584map,
                                GeneratedMutableMap.map25585map,
                                GeneratedMutableMap.map25586map,
                                GeneratedMutableMap.map25587map,
                                GeneratedMutableMap.map25588map,
                                GeneratedMutableMap.map25589map,
                                GeneratedMutableMap.map25590map,
                                GeneratedMutableMap.map25591map,
                                GeneratedMutableMap.map25592map,
                                GeneratedMutableMap.map25593map,
                                GeneratedMutableMap.map25594map,
                                GeneratedMutableMap.map25595map,
                                GeneratedMutableMap.map25596map,
                                GeneratedMutableMap.map25597map,
                                GeneratedMutableMap.map25598map,
                                GeneratedMutableMap.map25599map,
                                GeneratedMutableMap.map25600map,
                                GeneratedMutableMap.map25601map,
                                GeneratedMutableMap.map25602map,
                                GeneratedMutableMap.map25603map,
                                GeneratedMutableMap.map25604map,
                                GeneratedMutableMap.map25605map,
                                GeneratedMutableMap.map25606map,
                                GeneratedMutableMap.map25607map,
                                GeneratedMutableMap.map25608map,
                                GeneratedMutableMap.map25609map,
                                GeneratedMutableMap.map25610map,
                                GeneratedMutableMap.map25611map,
                                GeneratedMutableMap.map25612map,
                                GeneratedMutableMap.map25613map,
                                GeneratedMutableMap.map25614map,
                                GeneratedMutableMap.map25615map,
                                GeneratedMutableMap.map25616map,
                                GeneratedMutableMap.map25617map,
                                GeneratedMutableMap.map25618map,
                                GeneratedMutableMap.map25619map,
                                GeneratedMutableMap.map25620map,
                                GeneratedMutableMap.map25621map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"123\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"日本語\"@ja"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"english\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"français\"@fr"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s1", "<http://example.org/str>", "str1", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"123\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"日本語\"@ja"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"english\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"français\"@fr"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s2", "<http://example.org/str>", "str2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map25573map,
                                GeneratedMutableMap.map25574map,
                                GeneratedMutableMap.map25575map,
                                GeneratedMutableMap.map25576map,
                                GeneratedMutableMap.map25577map,
                                GeneratedMutableMap.map25578map,
                                GeneratedMutableMap.map25579map,
                                GeneratedMutableMap.map25580map,
                                GeneratedMutableMap.map25581map,
                                GeneratedMutableMap.map25582map,
                                GeneratedMutableMap.map25583map,
                                GeneratedMutableMap.map25584map,
                                GeneratedMutableMap.map25585map,
                                GeneratedMutableMap.map25586map,
                                GeneratedMutableMap.map25587map,
                                GeneratedMutableMap.map25588map,
                                GeneratedMutableMap.map25589map,
                                GeneratedMutableMap.map25590map,
                                GeneratedMutableMap.map25591map,
                                GeneratedMutableMap.map25592map,
                                GeneratedMutableMap.map25593map,
                                GeneratedMutableMap.map25594map,
                                GeneratedMutableMap.map25595map,
                                GeneratedMutableMap.map25596map,
                                GeneratedMutableMap.map25597map,
                                GeneratedMutableMap.map25598map,
                                GeneratedMutableMap.map25599map,
                                GeneratedMutableMap.map25600map,
                                GeneratedMutableMap.map25601map,
                                GeneratedMutableMap.map25602map,
                                GeneratedMutableMap.map25603map,
                                GeneratedMutableMap.map25604map,
                                GeneratedMutableMap.map25605map,
                                GeneratedMutableMap.map25606map,
                                GeneratedMutableMap.map25607map,
                                GeneratedMutableMap.map25608map,
                                GeneratedMutableMap.map25609map,
                                GeneratedMutableMap.map25610map,
                                GeneratedMutableMap.map25611map,
                                GeneratedMutableMap.map25612map,
                                GeneratedMutableMap.map25613map,
                                GeneratedMutableMap.map25614map,
                                GeneratedMutableMap.map25615map,
                                GeneratedMutableMap.map25616map,
                                GeneratedMutableMap.map25617map,
                                GeneratedMutableMap.map25618map,
                                GeneratedMutableMap.map25619map,
                                GeneratedMutableMap.map25620map,
                                GeneratedMutableMap.map25621map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s1"),
                                        AOPVariable("str1")
                                ), listOf(
                                        GeneratedMutableMap.map25557map,
                                        GeneratedMutableMap.map25558map,
                                        GeneratedMutableMap.map25559map,
                                        GeneratedMutableMap.map25560map,
                                        GeneratedMutableMap.map25561map,
                                        GeneratedMutableMap.map25562map,
                                        GeneratedMutableMap.map25563map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s2"),
                                        AOPVariable("str2")
                                ), listOf(
                                        GeneratedMutableMap.map25557map,
                                        GeneratedMutableMap.map25558map,
                                        GeneratedMutableMap.map25559map,
                                        GeneratedMutableMap.map25560map,
                                        GeneratedMutableMap.map25561map,
                                        GeneratedMutableMap.map25562map,
                                        GeneratedMutableMap.map25563map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("str1"),
                                AOPVariable("s2"),
                                AOPVariable("str2")
                        ), listOf(
                                GeneratedMutableMap.map25622map,
                                GeneratedMutableMap.map25623map,
                                GeneratedMutableMap.map25624map,
                                GeneratedMutableMap.map25625map,
                                GeneratedMutableMap.map25626map,
                                GeneratedMutableMap.map25627map,
                                GeneratedMutableMap.map25628map,
                                GeneratedMutableMap.map25629map,
                                GeneratedMutableMap.map25630map,
                                GeneratedMutableMap.map25631map,
                                GeneratedMutableMap.map25632map,
                                GeneratedMutableMap.map25633map,
                                GeneratedMutableMap.map25634map,
                                GeneratedMutableMap.map25635map,
                                GeneratedMutableMap.map25636map,
                                GeneratedMutableMap.map25637map,
                                GeneratedMutableMap.map25638map,
                                GeneratedMutableMap.map25639map,
                                GeneratedMutableMap.map25640map,
                                GeneratedMutableMap.map25641map,
                                GeneratedMutableMap.map25642map,
                                GeneratedMutableMap.map25643map,
                                GeneratedMutableMap.map25644map,
                                GeneratedMutableMap.map25645map,
                                GeneratedMutableMap.map25646map,
                                GeneratedMutableMap.map25647map,
                                GeneratedMutableMap.map25648map,
                                GeneratedMutableMap.map25649map,
                                GeneratedMutableMap.map25650map,
                                GeneratedMutableMap.map25651map,
                                GeneratedMutableMap.map25652map,
                                GeneratedMutableMap.map25653map,
                                GeneratedMutableMap.map25654map,
                                GeneratedMutableMap.map25655map,
                                GeneratedMutableMap.map25656map,
                                GeneratedMutableMap.map25657map,
                                GeneratedMutableMap.map25658map,
                                GeneratedMutableMap.map25659map,
                                GeneratedMutableMap.map25660map,
                                GeneratedMutableMap.map25661map,
                                GeneratedMutableMap.map25662map,
                                GeneratedMutableMap.map25663map,
                                GeneratedMutableMap.map25664map,
                                GeneratedMutableMap.map25665map,
                                GeneratedMutableMap.map25666map,
                                GeneratedMutableMap.map25667map,
                                GeneratedMutableMap.map25668map,
                                GeneratedMutableMap.map25669map,
                                GeneratedMutableMap.map25670map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"123\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"日本語\"@ja"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"english\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"français\"@fr"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s1"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str1"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"123\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"日本語\"@ja"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"english\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"français\"@fr"))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s2"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str2"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("str1"),
                                AOPVariable("s2"),
                                AOPVariable("str2")
                        ), listOf(
                                GeneratedMutableMap.map25622map,
                                GeneratedMutableMap.map25623map,
                                GeneratedMutableMap.map25624map,
                                GeneratedMutableMap.map25625map,
                                GeneratedMutableMap.map25626map,
                                GeneratedMutableMap.map25627map,
                                GeneratedMutableMap.map25628map,
                                GeneratedMutableMap.map25629map,
                                GeneratedMutableMap.map25630map,
                                GeneratedMutableMap.map25631map,
                                GeneratedMutableMap.map25632map,
                                GeneratedMutableMap.map25633map,
                                GeneratedMutableMap.map25634map,
                                GeneratedMutableMap.map25635map,
                                GeneratedMutableMap.map25636map,
                                GeneratedMutableMap.map25637map,
                                GeneratedMutableMap.map25638map,
                                GeneratedMutableMap.map25639map,
                                GeneratedMutableMap.map25640map,
                                GeneratedMutableMap.map25641map,
                                GeneratedMutableMap.map25642map,
                                GeneratedMutableMap.map25643map,
                                GeneratedMutableMap.map25644map,
                                GeneratedMutableMap.map25645map,
                                GeneratedMutableMap.map25646map,
                                GeneratedMutableMap.map25647map,
                                GeneratedMutableMap.map25648map,
                                GeneratedMutableMap.map25649map,
                                GeneratedMutableMap.map25650map,
                                GeneratedMutableMap.map25651map,
                                GeneratedMutableMap.map25652map,
                                GeneratedMutableMap.map25653map,
                                GeneratedMutableMap.map25654map,
                                GeneratedMutableMap.map25655map,
                                GeneratedMutableMap.map25656map,
                                GeneratedMutableMap.map25657map,
                                GeneratedMutableMap.map25658map,
                                GeneratedMutableMap.map25659map,
                                GeneratedMutableMap.map25660map,
                                GeneratedMutableMap.map25661map,
                                GeneratedMutableMap.map25662map,
                                GeneratedMutableMap.map25663map,
                                GeneratedMutableMap.map25664map,
                                GeneratedMutableMap.map25665map,
                                GeneratedMutableMap.map25666map,
                                GeneratedMutableMap.map25667map,
                                GeneratedMutableMap.map25668map,
                                GeneratedMutableMap.map25669map,
                                GeneratedMutableMap.map25670map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map27472map,
                                        GeneratedMutableMap.map27473map,
                                        GeneratedMutableMap.map27474map,
                                        GeneratedMutableMap.map27475map,
                                        GeneratedMutableMap.map27476map,
                                        GeneratedMutableMap.map27477map,
                                        GeneratedMutableMap.map27478map,
                                        GeneratedMutableMap.map27479map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map27489map,
                                        GeneratedMutableMap.map27490map,
                                        GeneratedMutableMap.map27491map,
                                        GeneratedMutableMap.map27492map,
                                        GeneratedMutableMap.map27493map,
                                        GeneratedMutableMap.map27494map,
                                        GeneratedMutableMap.map27495map,
                                        GeneratedMutableMap.map27496map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map27506map,
                                GeneratedMutableMap.map27507map,
                                GeneratedMutableMap.map27508map,
                                GeneratedMutableMap.map27509map,
                                GeneratedMutableMap.map27510map,
                                GeneratedMutableMap.map27511map,
                                GeneratedMutableMap.map27512map,
                                GeneratedMutableMap.map27513map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map27506map,
                                GeneratedMutableMap.map27507map,
                                GeneratedMutableMap.map27508map,
                                GeneratedMutableMap.map27509map,
                                GeneratedMutableMap.map27510map,
                                GeneratedMutableMap.map27511map,
                                GeneratedMutableMap.map27512map,
                                GeneratedMutableMap.map27513map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ), listOf(
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map,
                                        GeneratedMutableMap.map27484map,
                                        GeneratedMutableMap.map27485map,
                                        GeneratedMutableMap.map27486map,
                                        GeneratedMutableMap.map27487map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map27497map,
                                        GeneratedMutableMap.map27498map,
                                        GeneratedMutableMap.map27499map,
                                        GeneratedMutableMap.map27500map,
                                        GeneratedMutableMap.map27501map,
                                        GeneratedMutableMap.map27502map,
                                        GeneratedMutableMap.map27503map,
                                        GeneratedMutableMap.map27504map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map27514map,
                                GeneratedMutableMap.map27515map,
                                GeneratedMutableMap.map27516map,
                                GeneratedMutableMap.map27517map,
                                GeneratedMutableMap.map27518map,
                                GeneratedMutableMap.map27519map,
                                GeneratedMutableMap.map27520map,
                                GeneratedMutableMap.map27521map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map27514map,
                                GeneratedMutableMap.map27515map,
                                GeneratedMutableMap.map27516map,
                                GeneratedMutableMap.map27517map,
                                GeneratedMutableMap.map27518map,
                                GeneratedMutableMap.map27519map,
                                GeneratedMutableMap.map27520map,
                                GeneratedMutableMap.map27521map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "a",
                                        "s1"
                                ), listOf(
                                        GeneratedMutableMap.map31301map,
                                        GeneratedMutableMap.map31302map,
                                        GeneratedMutableMap.map31303map,
                                        GeneratedMutableMap.map31304map,
                                        GeneratedMutableMap.map31305map,
                                        GeneratedMutableMap.map31306map,
                                        GeneratedMutableMap.map31307map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "b",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map31309map,
                                        GeneratedMutableMap.map31310map,
                                        GeneratedMutableMap.map31311map,
                                        GeneratedMutableMap.map31312map,
                                        GeneratedMutableMap.map31313map,
                                        GeneratedMutableMap.map31314map,
                                        GeneratedMutableMap.map31315map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map31317map,
                                GeneratedMutableMap.map31318map,
                                GeneratedMutableMap.map31319map,
                                GeneratedMutableMap.map31320map,
                                GeneratedMutableMap.map31321map,
                                GeneratedMutableMap.map31322map,
                                GeneratedMutableMap.map31323map,
                                GeneratedMutableMap.map31324map,
                                GeneratedMutableMap.map31325map,
                                GeneratedMutableMap.map31326map,
                                GeneratedMutableMap.map31327map,
                                GeneratedMutableMap.map31328map,
                                GeneratedMutableMap.map31329map,
                                GeneratedMutableMap.map31330map,
                                GeneratedMutableMap.map31331map,
                                GeneratedMutableMap.map31332map,
                                GeneratedMutableMap.map31333map,
                                GeneratedMutableMap.map31334map,
                                GeneratedMutableMap.map31335map,
                                GeneratedMutableMap.map31336map,
                                GeneratedMutableMap.map31337map,
                                GeneratedMutableMap.map31338map,
                                GeneratedMutableMap.map31339map,
                                GeneratedMutableMap.map31340map,
                                GeneratedMutableMap.map31341map,
                                GeneratedMutableMap.map31342map,
                                GeneratedMutableMap.map31343map,
                                GeneratedMutableMap.map31344map,
                                GeneratedMutableMap.map31345map,
                                GeneratedMutableMap.map31346map,
                                GeneratedMutableMap.map31347map,
                                GeneratedMutableMap.map31348map,
                                GeneratedMutableMap.map31349map,
                                GeneratedMutableMap.map31350map,
                                GeneratedMutableMap.map31351map,
                                GeneratedMutableMap.map31352map,
                                GeneratedMutableMap.map31353map,
                                GeneratedMutableMap.map31354map,
                                GeneratedMutableMap.map31355map,
                                GeneratedMutableMap.map31356map,
                                GeneratedMutableMap.map31357map,
                                GeneratedMutableMap.map31358map,
                                GeneratedMutableMap.map31359map,
                                GeneratedMutableMap.map31360map,
                                GeneratedMutableMap.map31361map,
                                GeneratedMutableMap.map31362map,
                                GeneratedMutableMap.map31363map,
                                GeneratedMutableMap.map31364map,
                                GeneratedMutableMap.map31365map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://example.org/str>", "s1", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "b", "<http://example.org/str>", "s2", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map31317map,
                                GeneratedMutableMap.map31318map,
                                GeneratedMutableMap.map31319map,
                                GeneratedMutableMap.map31320map,
                                GeneratedMutableMap.map31321map,
                                GeneratedMutableMap.map31322map,
                                GeneratedMutableMap.map31323map,
                                GeneratedMutableMap.map31324map,
                                GeneratedMutableMap.map31325map,
                                GeneratedMutableMap.map31326map,
                                GeneratedMutableMap.map31327map,
                                GeneratedMutableMap.map31328map,
                                GeneratedMutableMap.map31329map,
                                GeneratedMutableMap.map31330map,
                                GeneratedMutableMap.map31331map,
                                GeneratedMutableMap.map31332map,
                                GeneratedMutableMap.map31333map,
                                GeneratedMutableMap.map31334map,
                                GeneratedMutableMap.map31335map,
                                GeneratedMutableMap.map31336map,
                                GeneratedMutableMap.map31337map,
                                GeneratedMutableMap.map31338map,
                                GeneratedMutableMap.map31339map,
                                GeneratedMutableMap.map31340map,
                                GeneratedMutableMap.map31341map,
                                GeneratedMutableMap.map31342map,
                                GeneratedMutableMap.map31343map,
                                GeneratedMutableMap.map31344map,
                                GeneratedMutableMap.map31345map,
                                GeneratedMutableMap.map31346map,
                                GeneratedMutableMap.map31347map,
                                GeneratedMutableMap.map31348map,
                                GeneratedMutableMap.map31349map,
                                GeneratedMutableMap.map31350map,
                                GeneratedMutableMap.map31351map,
                                GeneratedMutableMap.map31352map,
                                GeneratedMutableMap.map31353map,
                                GeneratedMutableMap.map31354map,
                                GeneratedMutableMap.map31355map,
                                GeneratedMutableMap.map31356map,
                                GeneratedMutableMap.map31357map,
                                GeneratedMutableMap.map31358map,
                                GeneratedMutableMap.map31359map,
                                GeneratedMutableMap.map31360map,
                                GeneratedMutableMap.map31361map,
                                GeneratedMutableMap.map31362map,
                                GeneratedMutableMap.map31363map,
                                GeneratedMutableMap.map31364map,
                                GeneratedMutableMap.map31365map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("a"),
                                        AOPVariable("s1")
                                ), listOf(
                                        GeneratedMutableMap.map22993map,
                                        GeneratedMutableMap.map22994map,
                                        GeneratedMutableMap.map22995map,
                                        GeneratedMutableMap.map22996map,
                                        GeneratedMutableMap.map22997map,
                                        GeneratedMutableMap.map22998map,
                                        GeneratedMutableMap.map22999map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("b"),
                                        AOPVariable("s2")
                                ), listOf(
                                        GeneratedMutableMap.map22993map,
                                        GeneratedMutableMap.map22994map,
                                        GeneratedMutableMap.map22995map,
                                        GeneratedMutableMap.map22996map,
                                        GeneratedMutableMap.map22997map,
                                        GeneratedMutableMap.map22998map,
                                        GeneratedMutableMap.map22999map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1"),
                                AOPVariable("b"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map31366map,
                                GeneratedMutableMap.map31367map,
                                GeneratedMutableMap.map31368map,
                                GeneratedMutableMap.map31369map,
                                GeneratedMutableMap.map31370map,
                                GeneratedMutableMap.map31371map,
                                GeneratedMutableMap.map31372map,
                                GeneratedMutableMap.map31373map,
                                GeneratedMutableMap.map31374map,
                                GeneratedMutableMap.map31375map,
                                GeneratedMutableMap.map31376map,
                                GeneratedMutableMap.map31377map,
                                GeneratedMutableMap.map31378map,
                                GeneratedMutableMap.map31379map,
                                GeneratedMutableMap.map31380map,
                                GeneratedMutableMap.map31381map,
                                GeneratedMutableMap.map31382map,
                                GeneratedMutableMap.map31383map,
                                GeneratedMutableMap.map31384map,
                                GeneratedMutableMap.map31385map,
                                GeneratedMutableMap.map31386map,
                                GeneratedMutableMap.map31387map,
                                GeneratedMutableMap.map31388map,
                                GeneratedMutableMap.map31389map,
                                GeneratedMutableMap.map31390map,
                                GeneratedMutableMap.map31391map,
                                GeneratedMutableMap.map31392map,
                                GeneratedMutableMap.map31393map,
                                GeneratedMutableMap.map31394map,
                                GeneratedMutableMap.map31395map,
                                GeneratedMutableMap.map31396map,
                                GeneratedMutableMap.map31397map,
                                GeneratedMutableMap.map31398map,
                                GeneratedMutableMap.map31399map,
                                GeneratedMutableMap.map31400map,
                                GeneratedMutableMap.map31401map,
                                GeneratedMutableMap.map31402map,
                                GeneratedMutableMap.map31403map,
                                GeneratedMutableMap.map31404map,
                                GeneratedMutableMap.map31405map,
                                GeneratedMutableMap.map31406map,
                                GeneratedMutableMap.map31407map,
                                GeneratedMutableMap.map31408map,
                                GeneratedMutableMap.map31409map,
                                GeneratedMutableMap.map31410map,
                                GeneratedMutableMap.map31411map,
                                GeneratedMutableMap.map31412map,
                                GeneratedMutableMap.map31413map,
                                GeneratedMutableMap.map31414map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("s1"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("b"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("s2"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1"),
                                AOPVariable("b"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map31366map,
                                GeneratedMutableMap.map31367map,
                                GeneratedMutableMap.map31368map,
                                GeneratedMutableMap.map31369map,
                                GeneratedMutableMap.map31370map,
                                GeneratedMutableMap.map31371map,
                                GeneratedMutableMap.map31372map,
                                GeneratedMutableMap.map31373map,
                                GeneratedMutableMap.map31374map,
                                GeneratedMutableMap.map31375map,
                                GeneratedMutableMap.map31376map,
                                GeneratedMutableMap.map31377map,
                                GeneratedMutableMap.map31378map,
                                GeneratedMutableMap.map31379map,
                                GeneratedMutableMap.map31380map,
                                GeneratedMutableMap.map31381map,
                                GeneratedMutableMap.map31382map,
                                GeneratedMutableMap.map31383map,
                                GeneratedMutableMap.map31384map,
                                GeneratedMutableMap.map31385map,
                                GeneratedMutableMap.map31386map,
                                GeneratedMutableMap.map31387map,
                                GeneratedMutableMap.map31388map,
                                GeneratedMutableMap.map31389map,
                                GeneratedMutableMap.map31390map,
                                GeneratedMutableMap.map31391map,
                                GeneratedMutableMap.map31392map,
                                GeneratedMutableMap.map31393map,
                                GeneratedMutableMap.map31394map,
                                GeneratedMutableMap.map31395map,
                                GeneratedMutableMap.map31396map,
                                GeneratedMutableMap.map31397map,
                                GeneratedMutableMap.map31398map,
                                GeneratedMutableMap.map31399map,
                                GeneratedMutableMap.map31400map,
                                GeneratedMutableMap.map31401map,
                                GeneratedMutableMap.map31402map,
                                GeneratedMutableMap.map31403map,
                                GeneratedMutableMap.map31404map,
                                GeneratedMutableMap.map31405map,
                                GeneratedMutableMap.map31406map,
                                GeneratedMutableMap.map31407map,
                                GeneratedMutableMap.map31408map,
                                GeneratedMutableMap.map31409map,
                                GeneratedMutableMap.map31410map,
                                GeneratedMutableMap.map31411map,
                                GeneratedMutableMap.map31412map,
                                GeneratedMutableMap.map31413map,
                                GeneratedMutableMap.map31414map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map32781map,
                                        GeneratedMutableMap.map32782map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        GeneratedMutableMap.map32918map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map32922map,
                                GeneratedMutableMap.map32923map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "v", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/q>", "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "w", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map32922map,
                                GeneratedMutableMap.map32923map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("v")
                                ), listOf(
                                        GeneratedMutableMap.map32783map,
                                        GeneratedMutableMap.map32784map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("w")
                                ), listOf(
                                        GeneratedMutableMap.map32919map
                                )
                                ),
                                true),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("w")
                        ), listOf(
                                GeneratedMutableMap.map32924map,
                                GeneratedMutableMap.map32925map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("v"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/q>", "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/q>"), AOPVariable("w"), graphName, false)
                                }()
                                ,
                                true),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("w")
                        ), listOf(
                                GeneratedMutableMap.map32924map,
                                GeneratedMutableMap.map32925map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map32781map,
                                        GeneratedMutableMap.map32782map,
                                        GeneratedMutableMap.map33072map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        GeneratedMutableMap.map32918map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map32922map,
                                GeneratedMutableMap.map32923map,
                                GeneratedMutableMap.map33075map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/s3>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "v", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/q>", "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "w", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map32922map,
                                GeneratedMutableMap.map32923map,
                                GeneratedMutableMap.map33075map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("v")
                                ), listOf(
                                        GeneratedMutableMap.map32783map,
                                        GeneratedMutableMap.map32784map,
                                        GeneratedMutableMap.map33073map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("w")
                                ), listOf(
                                        GeneratedMutableMap.map32919map
                                )
                                ),
                                true),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("w")
                        ), listOf(
                                GeneratedMutableMap.map32924map,
                                GeneratedMutableMap.map32925map,
                                GeneratedMutableMap.map33076map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example/s3>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("v"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example/s1>", "<http://example/q>", "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/q>"), AOPVariable("w"), graphName, false)
                                }()
                                ,
                                true),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("w")
                        ), listOf(
                                GeneratedMutableMap.map32924map,
                                GeneratedMutableMap.map32925map,
                                GeneratedMutableMap.map33076map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
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
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10605map,
                                        GeneratedMutableMap.map10606map,
                                        GeneratedMutableMap.map33485map,
                                        GeneratedMutableMap.map10608map,
                                        GeneratedMutableMap.map33486map,
                                        GeneratedMutableMap.map10610map
                                )
                                ),
                                true),
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
                                true),
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
                        LOPJoin(
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
                                ),
                                LOPValues(listOf(
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10502map,
                                        GeneratedMutableMap.map10503map,
                                        GeneratedMutableMap.map33357map,
                                        GeneratedMutableMap.map10505map,
                                        GeneratedMutableMap.map33358map,
                                        GeneratedMutableMap.map10507map
                                )
                                ),
                                true),
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
                                true),
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
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map35235map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map35238map,
                                        GeneratedMutableMap.map35239map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map35240map,
                                GeneratedMutableMap.map35241map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "z", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map35240map,
                                GeneratedMutableMap.map35241map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map35236map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map35236map,
                                        GeneratedMutableMap.map35242map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map35243map,
                                GeneratedMutableMap.map35244map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("y"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#q>"), AOPVariable("z"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map35243map,
                                GeneratedMutableMap.map35244map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map35235map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map35238map,
                                        GeneratedMutableMap.map35369map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map35240map,
                                GeneratedMutableMap.map35372map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"foobar\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "z", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map35240map,
                                GeneratedMutableMap.map35372map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map35236map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map35236map,
                                        GeneratedMutableMap.map35370map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map35243map,
                                GeneratedMutableMap.map35373map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("y"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"foobar\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#q>"), AOPVariable("z"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map35243map,
                                GeneratedMutableMap.map35373map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map35235map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map35239map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35241map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/project-expression/projexp03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map35235map,
                                        GeneratedMutableMap.map35873map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map35876map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map35877map,
                                GeneratedMutableMap.map35878map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#b>", "<http://www.example.org/schema#p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "l", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                true),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map35877map,
                                GeneratedMutableMap.map35878map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map35236map,
                                        GeneratedMutableMap.map35874map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map35242map
                                )
                                ),
                                true),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("l")
                        ), listOf(
                                GeneratedMutableMap.map35244map,
                                GeneratedMutableMap.map35879map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#b>", "<http://www.example.org/schema#p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("y"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#q>"), AOPVariable("l"), graphName, false)
                                }()
                                ,
                                true),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("l")
                        ), listOf(
                                GeneratedMutableMap.map35244map,
                                GeneratedMutableMap.map35879map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_37191",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map37308map,
                                        GeneratedMutableMap.map37309map,
                                        GeneratedMutableMap.map37310map,
                                        GeneratedMutableMap.map37311map,
                                        GeneratedMutableMap.map37312map,
                                        GeneratedMutableMap.map37313map,
                                        GeneratedMutableMap.map37314map,
                                        GeneratedMutableMap.map37315map,
                                        GeneratedMutableMap.map37316map,
                                        GeneratedMutableMap.map37317map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_37191"
                                ), listOf(
                                        GeneratedMutableMap.map37329map,
                                        GeneratedMutableMap.map37330map,
                                        GeneratedMutableMap.map37331map,
                                        GeneratedMutableMap.map37332map,
                                        GeneratedMutableMap.map37333map,
                                        GeneratedMutableMap.map37334map,
                                        GeneratedMutableMap.map37335map,
                                        GeneratedMutableMap.map37336map,
                                        GeneratedMutableMap.map37337map,
                                        GeneratedMutableMap.map37338map
                                )
                                ),
                                false),
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
                                GeneratedMutableMap.map37344map,
                                GeneratedMutableMap.map37345map,
                                GeneratedMutableMap.map37346map,
                                GeneratedMutableMap.map37347map,
                                GeneratedMutableMap.map37348map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                                false),
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
                                GeneratedMutableMap.map37344map,
                                GeneratedMutableMap.map37345map,
                                GeneratedMutableMap.map37346map,
                                GeneratedMutableMap.map37347map,
                                GeneratedMutableMap.map37348map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("#_37191"),
                                        AOPVariable("L")
                                ), listOf(
                                        GeneratedMutableMap.map37318map,
                                        GeneratedMutableMap.map37319map,
                                        GeneratedMutableMap.map37320map,
                                        GeneratedMutableMap.map37321map,
                                        GeneratedMutableMap.map37322map,
                                        GeneratedMutableMap.map37323map,
                                        GeneratedMutableMap.map37324map,
                                        GeneratedMutableMap.map37325map,
                                        GeneratedMutableMap.map37326map,
                                        GeneratedMutableMap.map37327map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("O"),
                                        AOPVariable("#_37191")
                                ), listOf(
                                        GeneratedMutableMap.map37349map,
                                        GeneratedMutableMap.map37350map,
                                        GeneratedMutableMap.map37351map,
                                        GeneratedMutableMap.map37352map,
                                        GeneratedMutableMap.map37353map,
                                        GeneratedMutableMap.map37354map,
                                        GeneratedMutableMap.map37355map,
                                        GeneratedMutableMap.map37356map,
                                        GeneratedMutableMap.map37357map,
                                        GeneratedMutableMap.map37358map
                                )
                                ),
                                false),
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
                                GeneratedMutableMap.map37364map,
                                GeneratedMutableMap.map37365map,
                                GeneratedMutableMap.map37366map,
                                GeneratedMutableMap.map37367map,
                                GeneratedMutableMap.map37368map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                                false),
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
                                GeneratedMutableMap.map37364map,
                                GeneratedMutableMap.map37365map,
                                GeneratedMutableMap.map37366map,
                                GeneratedMutableMap.map37367map,
                                GeneratedMutableMap.map37368map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
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
                                        GeneratedMutableMap.map37344map,
                                        GeneratedMutableMap.map37345map,
                                        GeneratedMutableMap.map37346map,
                                        GeneratedMutableMap.map37347map,
                                        GeneratedMutableMap.map37348map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map37371map,
                                        GeneratedMutableMap.map37372map
                                )
                                ),
                                false),
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
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                false),
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
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
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
                                        GeneratedMutableMap.map37364map,
                                        GeneratedMutableMap.map37365map,
                                        GeneratedMutableMap.map37366map,
                                        GeneratedMutableMap.map37367map,
                                        GeneratedMutableMap.map37368map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map37375map,
                                        GeneratedMutableMap.map37376map
                                )
                                ),
                                false),
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
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                                false),
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
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "P",
                                        "F"
                                ), listOf(
                                        GeneratedMutableMap.map37579map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map37576map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37582map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/firstName>", "F", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/lastName>", "L", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37582map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("P"),
                                        AOPVariable("F")
                                ), listOf(
                                        GeneratedMutableMap.map37580map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("P"),
                                        AOPVariable("L")
                                ), listOf(
                                        GeneratedMutableMap.map37577map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("F"),
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37583map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("P"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"), AOPVariable("F"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("P"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"), AOPVariable("L"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("F"),
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37583map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "#_37620",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map37737map,
                                        GeneratedMutableMap.map37738map,
                                        GeneratedMutableMap.map37739map,
                                        GeneratedMutableMap.map37740map,
                                        GeneratedMutableMap.map37741map,
                                        GeneratedMutableMap.map37742map,
                                        GeneratedMutableMap.map37743map,
                                        GeneratedMutableMap.map37744map,
                                        GeneratedMutableMap.map37745map,
                                        GeneratedMutableMap.map37746map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_37620"
                                ), listOf(
                                        GeneratedMutableMap.map37758map,
                                        GeneratedMutableMap.map37759map,
                                        GeneratedMutableMap.map37760map,
                                        GeneratedMutableMap.map37761map,
                                        GeneratedMutableMap.map37762map,
                                        GeneratedMutableMap.map37763map,
                                        GeneratedMutableMap.map37764map,
                                        GeneratedMutableMap.map37765map,
                                        GeneratedMutableMap.map37766map,
                                        GeneratedMutableMap.map37767map
                                )
                                ),
                                false),
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
                                GeneratedMutableMap.map37773map,
                                GeneratedMutableMap.map37774map,
                                GeneratedMutableMap.map37775map,
                                GeneratedMutableMap.map37776map,
                                GeneratedMutableMap.map37777map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                                false),
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
                                GeneratedMutableMap.map37773map,
                                GeneratedMutableMap.map37774map,
                                GeneratedMutableMap.map37775map,
                                GeneratedMutableMap.map37776map,
                                GeneratedMutableMap.map37777map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                LOPValues(listOf(
                                        AOPVariable("#_37620"),
                                        AOPVariable("L")
                                ), listOf(
                                        GeneratedMutableMap.map37747map,
                                        GeneratedMutableMap.map37748map,
                                        GeneratedMutableMap.map37749map,
                                        GeneratedMutableMap.map37750map,
                                        GeneratedMutableMap.map37751map,
                                        GeneratedMutableMap.map37752map,
                                        GeneratedMutableMap.map37753map,
                                        GeneratedMutableMap.map37754map,
                                        GeneratedMutableMap.map37755map,
                                        GeneratedMutableMap.map37756map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("O"),
                                        AOPVariable("#_37620")
                                ), listOf(
                                        GeneratedMutableMap.map37778map,
                                        GeneratedMutableMap.map37779map,
                                        GeneratedMutableMap.map37780map,
                                        GeneratedMutableMap.map37781map,
                                        GeneratedMutableMap.map37782map,
                                        GeneratedMutableMap.map37783map,
                                        GeneratedMutableMap.map37784map,
                                        GeneratedMutableMap.map37785map,
                                        GeneratedMutableMap.map37786map,
                                        GeneratedMutableMap.map37787map
                                )
                                ),
                                false),
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
                                GeneratedMutableMap.map37793map,
                                GeneratedMutableMap.map37794map,
                                GeneratedMutableMap.map37795map,
                                GeneratedMutableMap.map37796map,
                                GeneratedMutableMap.map37797map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                                false),
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
                                GeneratedMutableMap.map37793map,
                                GeneratedMutableMap.map37794map,
                                GeneratedMutableMap.map37795map,
                                GeneratedMutableMap.map37796map,
                                GeneratedMutableMap.map37797map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPJoinHashMap(
                                dictionary,
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
                                        GeneratedMutableMap.map37773map,
                                        GeneratedMutableMap.map37774map,
                                        GeneratedMutableMap.map37775map,
                                        GeneratedMutableMap.map37776map,
                                        GeneratedMutableMap.map37777map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map37371map,
                                        GeneratedMutableMap.map37372map
                                )
                                ),
                                false),
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
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                false),
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
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
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
                                        GeneratedMutableMap.map37793map,
                                        GeneratedMutableMap.map37794map,
                                        GeneratedMutableMap.map37795map,
                                        GeneratedMutableMap.map37796map,
                                        GeneratedMutableMap.map37797map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map37375map,
                                        GeneratedMutableMap.map37376map
                                )
                                ),
                                false),
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
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                                false),
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
