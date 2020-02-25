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
                                        GeneratedMutableMap.map2737map,
                                        GeneratedMutableMap.map2738map,
                                        GeneratedMutableMap.map2739map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "S",
                                        "O2"
                                ), listOf(
                                        GeneratedMutableMap.map2734map,
                                        GeneratedMutableMap.map2735map,
                                        GeneratedMutableMap.map2736map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2"
                        ), listOf(
                                GeneratedMutableMap.map2740map,
                                GeneratedMutableMap.map2741map,
                                GeneratedMutableMap.map2742map,
                                GeneratedMutableMap.map2743map,
                                GeneratedMutableMap.map2744map,
                                GeneratedMutableMap.map2745map,
                                GeneratedMutableMap.map2746map,
                                GeneratedMutableMap.map2747map,
                                GeneratedMutableMap.map2748map
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
                                GeneratedMutableMap.map2740map,
                                GeneratedMutableMap.map2741map,
                                GeneratedMutableMap.map2742map,
                                GeneratedMutableMap.map2743map,
                                GeneratedMutableMap.map2744map,
                                GeneratedMutableMap.map2745map,
                                GeneratedMutableMap.map2746map,
                                GeneratedMutableMap.map2747map,
                                GeneratedMutableMap.map2748map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "S",
                                        "O1"
                                ), listOf(
                                        GeneratedMutableMap.map2737map,
                                        GeneratedMutableMap.map2738map,
                                        GeneratedMutableMap.map2739map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "S",
                                        "O2"
                                ), listOf(
                                        GeneratedMutableMap.map2734map,
                                        GeneratedMutableMap.map2735map,
                                        GeneratedMutableMap.map2736map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2"
                        ), listOf(
                                GeneratedMutableMap.map2740map,
                                GeneratedMutableMap.map2741map,
                                GeneratedMutableMap.map2742map,
                                GeneratedMutableMap.map2743map,
                                GeneratedMutableMap.map2744map,
                                GeneratedMutableMap.map2745map,
                                GeneratedMutableMap.map2746map,
                                GeneratedMutableMap.map2747map,
                                GeneratedMutableMap.map2748map
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
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2"
                        ), listOf(
                                GeneratedMutableMap.map2740map,
                                GeneratedMutableMap.map2741map,
                                GeneratedMutableMap.map2742map,
                                GeneratedMutableMap.map2743map,
                                GeneratedMutableMap.map2744map,
                                GeneratedMutableMap.map2745map,
                                GeneratedMutableMap.map2746map,
                                GeneratedMutableMap.map2747map,
                                GeneratedMutableMap.map2748map
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
                                GeneratedMutableMap.map5828map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5829map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map5829map
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
                                GeneratedMutableMap.map5829map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5829map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map5829map
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
                                GeneratedMutableMap.map5829map
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
                                GeneratedMutableMap.map5828map
                            )
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-data-same-bnode.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5828map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6261map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6261map
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
                                GeneratedMutableMap.map6261map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6261map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6261map
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
                                GeneratedMutableMap.map6261map
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
                            ), listOf(
                                GeneratedMutableMap.map5828map
                            )
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5828map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5828map
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
                                GeneratedMutableMap.map5828map
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
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7104map,
                                GeneratedMutableMap.map7105map,
                                GeneratedMutableMap.map7106map,
                                GeneratedMutableMap.map7107map
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
                            GeneratedMutableMap.map7108map,
                            GeneratedMutableMap.map7109map,
                            GeneratedMutableMap.map7110map,
                            GeneratedMutableMap.map7111map,
                            GeneratedMutableMap.map7112map,
                            GeneratedMutableMap.map7113map,
                            GeneratedMutableMap.map7114map,
                            GeneratedMutableMap.map7115map,
                            GeneratedMutableMap.map7116map,
                            GeneratedMutableMap.map7117map,
                            GeneratedMutableMap.map7118map,
                            GeneratedMutableMap.map7119map,
                            GeneratedMutableMap.map7120map,
                            GeneratedMutableMap.map7121map,
                            GeneratedMutableMap.map7122map,
                            GeneratedMutableMap.map7123map
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
                                        GeneratedMutableMap.map6649map,
                                        GeneratedMutableMap.map6650map,
                                        GeneratedMutableMap.map6651map,
                                        GeneratedMutableMap.map6652map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map7624map,
                                        GeneratedMutableMap.map7624map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map,
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map
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
                                        GeneratedMutableMap.map8392map,
                                        GeneratedMutableMap.map8393map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8390map,
                                        GeneratedMutableMap.map8391map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8394map,
                                GeneratedMutableMap.map8395map
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
                                GeneratedMutableMap.map8394map,
                                GeneratedMutableMap.map8395map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        GeneratedMutableMap.map8392map,
                                        GeneratedMutableMap.map8393map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8390map,
                                        GeneratedMutableMap.map8391map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8394map,
                                GeneratedMutableMap.map8395map
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
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8394map,
                                GeneratedMutableMap.map8395map
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
                                        GeneratedMutableMap.map8396map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8394map,
                                        GeneratedMutableMap.map8395map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8394map
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
                                        GeneratedMutableMap.map8451map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8452map,
                                        GeneratedMutableMap.map8453map,
                                        GeneratedMutableMap.map8454map,
                                        GeneratedMutableMap.map8455map,
                                        GeneratedMutableMap.map8456map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8457map
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
                                        GeneratedMutableMap.map8538map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8539map,
                                        GeneratedMutableMap.map8540map,
                                        GeneratedMutableMap.map8541map,
                                        GeneratedMutableMap.map8542map,
                                        GeneratedMutableMap.map8543map,
                                        GeneratedMutableMap.map8544map,
                                        GeneratedMutableMap.map8545map,
                                        GeneratedMutableMap.map8546map,
                                        GeneratedMutableMap.map8547map,
                                        GeneratedMutableMap.map8548map,
                                        GeneratedMutableMap.map8549map,
                                        GeneratedMutableMap.map8550map,
                                        GeneratedMutableMap.map8551map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8552map
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
                                        GeneratedMutableMap.map8553map,
                                        GeneratedMutableMap.map8554map,
                                        GeneratedMutableMap.map8555map,
                                        GeneratedMutableMap.map8556map,
                                        GeneratedMutableMap.map8557map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8558map,
                                        GeneratedMutableMap.map8559map,
                                        GeneratedMutableMap.map8560map,
                                        GeneratedMutableMap.map8561map,
                                        GeneratedMutableMap.map8562map
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
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map8564map,
                                GeneratedMutableMap.map8565map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map,
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map8573map,
                                GeneratedMutableMap.map8574map,
                                GeneratedMutableMap.map8575map
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
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map8564map,
                                GeneratedMutableMap.map8565map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map,
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map8573map,
                                GeneratedMutableMap.map8574map,
                                GeneratedMutableMap.map8575map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        GeneratedMutableMap.map8553map,
                                        GeneratedMutableMap.map8554map,
                                        GeneratedMutableMap.map8555map,
                                        GeneratedMutableMap.map8556map,
                                        GeneratedMutableMap.map8557map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8558map,
                                        GeneratedMutableMap.map8559map,
                                        GeneratedMutableMap.map8560map,
                                        GeneratedMutableMap.map8561map,
                                        GeneratedMutableMap.map8562map
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
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map8564map,
                                GeneratedMutableMap.map8565map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map,
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map8573map,
                                GeneratedMutableMap.map8574map,
                                GeneratedMutableMap.map8575map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map8564map,
                                GeneratedMutableMap.map8565map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map,
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map8573map,
                                GeneratedMutableMap.map8574map,
                                GeneratedMutableMap.map8575map
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
                                        GeneratedMutableMap.map8651map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8539map,
                                        GeneratedMutableMap.map8540map,
                                        GeneratedMutableMap.map8541map,
                                        GeneratedMutableMap.map8542map,
                                        GeneratedMutableMap.map8543map,
                                        GeneratedMutableMap.map8544map,
                                        GeneratedMutableMap.map8545map,
                                        GeneratedMutableMap.map8546map,
                                        GeneratedMutableMap.map8547map,
                                        GeneratedMutableMap.map8548map,
                                        GeneratedMutableMap.map8549map,
                                        GeneratedMutableMap.map8550map,
                                        GeneratedMutableMap.map8551map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8652map,
                                GeneratedMutableMap.map8552map,
                                GeneratedMutableMap.map8653map
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
                                        GeneratedMutableMap.map8738map,
                                        GeneratedMutableMap.map8739map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8539map,
                                        GeneratedMutableMap.map8540map,
                                        GeneratedMutableMap.map8541map,
                                        GeneratedMutableMap.map8542map,
                                        GeneratedMutableMap.map8543map,
                                        GeneratedMutableMap.map8544map,
                                        GeneratedMutableMap.map8545map,
                                        GeneratedMutableMap.map8546map,
                                        GeneratedMutableMap.map8547map,
                                        GeneratedMutableMap.map8548map,
                                        GeneratedMutableMap.map8549map,
                                        GeneratedMutableMap.map8550map,
                                        GeneratedMutableMap.map8551map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8740map,
                                GeneratedMutableMap.map8741map,
                                GeneratedMutableMap.map8742map,
                                GeneratedMutableMap.map8742map,
                                GeneratedMutableMap.map8743map,
                                GeneratedMutableMap.map8653map
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
                                        GeneratedMutableMap.map8803map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        GeneratedMutableMap.map8553map,
                                        GeneratedMutableMap.map8554map,
                                        GeneratedMutableMap.map8555map,
                                        GeneratedMutableMap.map8556map,
                                        GeneratedMutableMap.map8557map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p1",
                                "s",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map8804map
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
                                        GeneratedMutableMap.map8885map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8539map,
                                        GeneratedMutableMap.map8540map,
                                        GeneratedMutableMap.map8541map,
                                        GeneratedMutableMap.map8886map,
                                        GeneratedMutableMap.map8887map,
                                        GeneratedMutableMap.map8888map,
                                        GeneratedMutableMap.map8889map,
                                        GeneratedMutableMap.map8890map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o2",
                                "s",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map8891map,
                                GeneratedMutableMap.map8892map,
                                GeneratedMutableMap.map8893map,
                                GeneratedMutableMap.map8894map,
                                GeneratedMutableMap.map8895map
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
                                        GeneratedMutableMap.map8553map,
                                        GeneratedMutableMap.map8554map,
                                        GeneratedMutableMap.map8555map,
                                        GeneratedMutableMap.map8898map,
                                        GeneratedMutableMap.map8556map,
                                        GeneratedMutableMap.map8557map,
                                        GeneratedMutableMap.map8899map,
                                        GeneratedMutableMap.map8900map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8896map,
                                        GeneratedMutableMap.map8897map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map8901map,
                                GeneratedMutableMap.map8902map,
                                GeneratedMutableMap.map8903map,
                                GeneratedMutableMap.map8904map,
                                GeneratedMutableMap.map8905map,
                                GeneratedMutableMap.map8906map,
                                GeneratedMutableMap.map8907map,
                                GeneratedMutableMap.map8908map
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
                                GeneratedMutableMap.map8901map,
                                GeneratedMutableMap.map8902map,
                                GeneratedMutableMap.map8903map,
                                GeneratedMutableMap.map8904map,
                                GeneratedMutableMap.map8905map,
                                GeneratedMutableMap.map8906map,
                                GeneratedMutableMap.map8907map,
                                GeneratedMutableMap.map8908map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        GeneratedMutableMap.map8553map,
                                        GeneratedMutableMap.map8554map,
                                        GeneratedMutableMap.map8555map,
                                        GeneratedMutableMap.map8898map,
                                        GeneratedMutableMap.map8556map,
                                        GeneratedMutableMap.map8557map,
                                        GeneratedMutableMap.map8899map,
                                        GeneratedMutableMap.map8900map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8896map,
                                        GeneratedMutableMap.map8897map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map8901map,
                                GeneratedMutableMap.map8902map,
                                GeneratedMutableMap.map8903map,
                                GeneratedMutableMap.map8904map,
                                GeneratedMutableMap.map8905map,
                                GeneratedMutableMap.map8906map,
                                GeneratedMutableMap.map8907map,
                                GeneratedMutableMap.map8908map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map8901map,
                                GeneratedMutableMap.map8902map,
                                GeneratedMutableMap.map8903map,
                                GeneratedMutableMap.map8904map,
                                GeneratedMutableMap.map8905map,
                                GeneratedMutableMap.map8906map,
                                GeneratedMutableMap.map8907map,
                                GeneratedMutableMap.map8908map
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
                                        GeneratedMutableMap.map9011map,
                                        GeneratedMutableMap.map9012map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8394map,
                                        GeneratedMutableMap.map8395map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8394map,
                                GeneratedMutableMap.map8395map
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
                                        GeneratedMutableMap.map8396map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        GeneratedMutableMap.map8392map,
                                        GeneratedMutableMap.map8393map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                        ), listOf(
                                GeneratedMutableMap.map8392map
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
                                        GeneratedMutableMap.map8392map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8390map,
                                        GeneratedMutableMap.map8391map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8394map
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
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10274map,
                                GeneratedMutableMap.map10275map,
                                GeneratedMutableMap.map10276map,
                                GeneratedMutableMap.map10277map,
                                GeneratedMutableMap.map10278map,
                                GeneratedMutableMap.map10279map
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
                        POPJoinHashMap(
                                dictionary,
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
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10274map,
                                        GeneratedMutableMap.map10275map,
                                        GeneratedMutableMap.map10276map,
                                        GeneratedMutableMap.map10277map,
                                        GeneratedMutableMap.map10278map,
                                        GeneratedMutableMap.map10279map
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
                        LOPJoin(
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
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10274map,
                                        GeneratedMutableMap.map10275map,
                                        GeneratedMutableMap.map10276map,
                                        GeneratedMutableMap.map10277map,
                                        GeneratedMutableMap.map10278map,
                                        GeneratedMutableMap.map10279map
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
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5828map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11140map,
                                GeneratedMutableMap.map11140map,
                                GeneratedMutableMap.map11140map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map
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
                                GeneratedMutableMap.map11140map,
                                GeneratedMutableMap.map11140map,
                                GeneratedMutableMap.map11140map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11140map,
                                GeneratedMutableMap.map11140map,
                                GeneratedMutableMap.map11140map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11140map
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
                                        GeneratedMutableMap.map11299map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "b"
                                ), listOf(
                                        GeneratedMutableMap.map11036map,
                                        GeneratedMutableMap.map11037map,
                                        GeneratedMutableMap.map11038map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                        ), listOf(
                                GeneratedMutableMap.map11036map,
                                GeneratedMutableMap.map11037map
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
                                GeneratedMutableMap.map11036map,
                                GeneratedMutableMap.map11037map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "a"
                                ), listOf(
                                        GeneratedMutableMap.map11299map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "b"
                                ), listOf(
                                        GeneratedMutableMap.map11036map,
                                        GeneratedMutableMap.map11037map,
                                        GeneratedMutableMap.map11038map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                        ), listOf(
                                GeneratedMutableMap.map11036map,
                                GeneratedMutableMap.map11037map
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
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                        ), listOf(
                                GeneratedMutableMap.map11036map,
                                GeneratedMutableMap.map11037map
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
                                        GeneratedMutableMap.map11299map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        GeneratedMutableMap.map11532map,
                                        GeneratedMutableMap.map11533map,
                                        GeneratedMutableMap.map11534map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11532map,
                                GeneratedMutableMap.map11533map
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
                                GeneratedMutableMap.map11532map,
                                GeneratedMutableMap.map11533map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "a"
                                ), listOf(
                                        GeneratedMutableMap.map11299map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        GeneratedMutableMap.map11532map,
                                        GeneratedMutableMap.map11533map,
                                        GeneratedMutableMap.map11534map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11532map,
                                GeneratedMutableMap.map11533map
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
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11532map,
                                GeneratedMutableMap.map11533map
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
                                        GeneratedMutableMap.map11299map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        GeneratedMutableMap.map11534map
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
                                POPValues(dictionary, listOf(
                                        "a"
                                ), listOf(
                                        GeneratedMutableMap.map11299map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        GeneratedMutableMap.map11534map
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
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map12681map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map8459map,
                                        GeneratedMutableMap.map8460map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map12681map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map8459map,
                                        GeneratedMutableMap.map8460map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map8459map,
                                        GeneratedMutableMap.map8460map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map
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
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map8459map,
                                        GeneratedMutableMap.map8460map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map
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
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map10793map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map,
                                        GeneratedMutableMap.map10794map,
                                        GeneratedMutableMap.map10795map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map
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
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
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
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map10793map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map,
                                        GeneratedMutableMap.map10794map,
                                        GeneratedMutableMap.map10795map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map
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
                                        GeneratedMutableMap.map12681map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map10793map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map,
                                        GeneratedMutableMap.map10794map,
                                        GeneratedMutableMap.map10795map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map12681map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map10793map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map,
                                        GeneratedMutableMap.map10794map,
                                        GeneratedMutableMap.map10795map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                                        GeneratedMutableMap.map10796map,
                                        GeneratedMutableMap.map10794map,
                                        GeneratedMutableMap.map10795map,
                                        GeneratedMutableMap.map10797map,
                                        GeneratedMutableMap.map10798map
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
                                        GeneratedMutableMap.map10796map,
                                        GeneratedMutableMap.map10794map,
                                        GeneratedMutableMap.map10795map,
                                        GeneratedMutableMap.map10797map,
                                        GeneratedMutableMap.map10798map
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
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map10793map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map,
                                        GeneratedMutableMap.map10794map,
                                        GeneratedMutableMap.map10795map
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
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map10793map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map,
                                        GeneratedMutableMap.map10794map,
                                        GeneratedMutableMap.map10795map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
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
                                GeneratedMutableMap.map13081map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10796map,
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map,
                                GeneratedMutableMap.map10797map,
                                GeneratedMutableMap.map10798map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10796map,
                            GeneratedMutableMap.map10794map,
                            GeneratedMutableMap.map10795map
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
                                        GeneratedMutableMap.map13831map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map8459map,
                                        GeneratedMutableMap.map8460map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map
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
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map13831map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map8459map,
                                        GeneratedMutableMap.map8460map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map
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
                                GeneratedMutableMap.map15659map,
                                GeneratedMutableMap.map15660map,
                                GeneratedMutableMap.map15661map,
                                GeneratedMutableMap.map15662map,
                                GeneratedMutableMap.map15663map,
                                GeneratedMutableMap.map15664map,
                                GeneratedMutableMap.map15665map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map15658map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map15662map
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
                                GeneratedMutableMap.map15926map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map15926map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15926map
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
                                        GeneratedMutableMap.map16119map,
                                        GeneratedMutableMap.map16120map,
                                        GeneratedMutableMap.map16121map,
                                        GeneratedMutableMap.map16122map,
                                        GeneratedMutableMap.map15659map,
                                        GeneratedMutableMap.map15660map,
                                        GeneratedMutableMap.map15661map,
                                        GeneratedMutableMap.map16123map,
                                        GeneratedMutableMap.map16124map
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
                                POPValues(dictionary, listOf(
                                        "x",
                                        "c"
                                ), listOf(
                                        GeneratedMutableMap.map16119map,
                                        GeneratedMutableMap.map16120map,
                                        GeneratedMutableMap.map16121map,
                                        GeneratedMutableMap.map16122map,
                                        GeneratedMutableMap.map15659map,
                                        GeneratedMutableMap.map15660map,
                                        GeneratedMutableMap.map15661map,
                                        GeneratedMutableMap.map16123map,
                                        GeneratedMutableMap.map16124map
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
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map16186map,
                                        GeneratedMutableMap.map16187map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map16184map,
                                        GeneratedMutableMap.map16185map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16186map,
                                GeneratedMutableMap.map16187map
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
                                GeneratedMutableMap.map16186map,
                                GeneratedMutableMap.map16187map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map16186map,
                                        GeneratedMutableMap.map16187map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map16184map,
                                        GeneratedMutableMap.map16185map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16186map,
                                GeneratedMutableMap.map16187map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16186map,
                                GeneratedMutableMap.map16187map
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
                                GeneratedMutableMap.map16397map,
                                GeneratedMutableMap.map16398map,
                                GeneratedMutableMap.map16399map,
                                GeneratedMutableMap.map16400map,
                                GeneratedMutableMap.map16401map,
                                GeneratedMutableMap.map16402map,
                                GeneratedMutableMap.map16403map,
                                GeneratedMutableMap.map16404map,
                                GeneratedMutableMap.map16405map,
                                GeneratedMutableMap.map16406map,
                                GeneratedMutableMap.map16407map,
                                GeneratedMutableMap.map16408map,
                                GeneratedMutableMap.map16409map,
                                GeneratedMutableMap.map16410map,
                                GeneratedMutableMap.map16411map,
                                GeneratedMutableMap.map16412map,
                                GeneratedMutableMap.map16413map,
                                GeneratedMutableMap.map16414map,
                                GeneratedMutableMap.map16415map,
                                GeneratedMutableMap.map16416map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16292"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16292"
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
                                "#_16292",
                                "#_16297"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16417map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16292",
                            "#_16297",
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
                                GeneratedMutableMap.map16574map,
                                GeneratedMutableMap.map16575map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16438"
                            ), listOf(
                                GeneratedMutableMap.map16546map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16438"
                        ), listOf(
                            GeneratedMutableMap.map16576map,
                            GeneratedMutableMap.map16577map
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
                                "#_16438"
                            ), listOf(
                                GeneratedMutableMap.map16576map,
                                GeneratedMutableMap.map16577map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16438"
                            ), listOf(
                                GeneratedMutableMap.map16546map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16438"
                        ), listOf(
                            GeneratedMutableMap.map16576map,
                            GeneratedMutableMap.map16577map
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
                                "#_16438"
                            ), listOf(
                                GeneratedMutableMap.map16576map,
                                GeneratedMutableMap.map16577map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16447"
                            ), listOf(
                                GeneratedMutableMap.map16548map,
                                GeneratedMutableMap.map16549map,
                                GeneratedMutableMap.map16550map,
                                GeneratedMutableMap.map16551map,
                                GeneratedMutableMap.map16552map,
                                GeneratedMutableMap.map16553map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16438",
                            "#_16447"
                        ), listOf(
                            GeneratedMutableMap.map16578map,
                            GeneratedMutableMap.map16579map,
                            GeneratedMutableMap.map16580map,
                            GeneratedMutableMap.map16581map,
                            GeneratedMutableMap.map16582map,
                            GeneratedMutableMap.map16583map,
                            GeneratedMutableMap.map16584map,
                            GeneratedMutableMap.map16585map,
                            GeneratedMutableMap.map16586map,
                            GeneratedMutableMap.map16587map,
                            GeneratedMutableMap.map16588map,
                            GeneratedMutableMap.map16589map
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
                                "#_16438",
                                "#_16447"
                            ), listOf(
                                GeneratedMutableMap.map16578map,
                                GeneratedMutableMap.map16579map,
                                GeneratedMutableMap.map16580map,
                                GeneratedMutableMap.map16581map,
                                GeneratedMutableMap.map16582map,
                                GeneratedMutableMap.map16583map,
                                GeneratedMutableMap.map16584map,
                                GeneratedMutableMap.map16585map,
                                GeneratedMutableMap.map16586map,
                                GeneratedMutableMap.map16587map,
                                GeneratedMutableMap.map16588map,
                                GeneratedMutableMap.map16589map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16447"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16438",
                            "#_16447"
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
                                "#_16438",
                                "#_16447"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16438",
                                "#_16447"
                            ), listOf(
                                GeneratedMutableMap.map16547map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16438",
                            "#_16447"
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
                                "#_16438",
                                "#_16447"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#_16438"
                            ), listOf(
                                GeneratedMutableMap.map16554map,
                                GeneratedMutableMap.map16555map,
                                GeneratedMutableMap.map16556map,
                                GeneratedMutableMap.map16557map,
                                GeneratedMutableMap.map16558map,
                                GeneratedMutableMap.map16559map,
                                GeneratedMutableMap.map16560map,
                                GeneratedMutableMap.map16561map,
                                GeneratedMutableMap.map16562map,
                                GeneratedMutableMap.map16563map,
                                GeneratedMutableMap.map16564map,
                                GeneratedMutableMap.map16565map,
                                GeneratedMutableMap.map16566map,
                                GeneratedMutableMap.map16567map,
                                GeneratedMutableMap.map16568map,
                                GeneratedMutableMap.map16569map,
                                GeneratedMutableMap.map16570map,
                                GeneratedMutableMap.map16571map,
                                GeneratedMutableMap.map16572map,
                                GeneratedMutableMap.map16573map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16438",
                            "#_16447"
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
                                GeneratedMutableMap.map16664map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16665map,
                                GeneratedMutableMap.map16666map,
                                GeneratedMutableMap.map16667map,
                                GeneratedMutableMap.map16668map,
                                GeneratedMutableMap.map16669map,
                                GeneratedMutableMap.map16670map,
                                GeneratedMutableMap.map16671map,
                                GeneratedMutableMap.map16672map,
                                GeneratedMutableMap.map16673map,
                                GeneratedMutableMap.map16674map,
                                GeneratedMutableMap.map16675map,
                                GeneratedMutableMap.map16676map,
                                GeneratedMutableMap.map16677map,
                                GeneratedMutableMap.map16678map,
                                GeneratedMutableMap.map16679map,
                                GeneratedMutableMap.map16680map,
                                GeneratedMutableMap.map16681map,
                                GeneratedMutableMap.map16682map,
                                GeneratedMutableMap.map16683map,
                                GeneratedMutableMap.map16684map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16665map,
                            GeneratedMutableMap.map16666map
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
                                GeneratedMutableMap.map16665map,
                                GeneratedMutableMap.map16666map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16229map
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
                                        GeneratedMutableMap.map16748map,
                                        GeneratedMutableMap.map16749map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map16750map,
                                        GeneratedMutableMap.map16751map,
                                        GeneratedMutableMap.map16752map,
                                        GeneratedMutableMap.map16753map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map16750map
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
                                GeneratedMutableMap.map16750map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        GeneratedMutableMap.map16748map,
                                        GeneratedMutableMap.map16749map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map16750map,
                                        GeneratedMutableMap.map16751map,
                                        GeneratedMutableMap.map16752map,
                                        GeneratedMutableMap.map16753map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map16750map
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
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map16750map
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
                                        GeneratedMutableMap.map16899map,
                                        GeneratedMutableMap.map6649map,
                                        GeneratedMutableMap.map6650map,
                                        GeneratedMutableMap.map6651map,
                                        GeneratedMutableMap.map6652map,
                                        GeneratedMutableMap.map16900map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        GeneratedMutableMap.map16898map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map
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
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map16899map,
                                        GeneratedMutableMap.map6649map,
                                        GeneratedMutableMap.map6650map,
                                        GeneratedMutableMap.map6651map,
                                        GeneratedMutableMap.map6652map,
                                        GeneratedMutableMap.map16900map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        GeneratedMutableMap.map16898map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map
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
                                GeneratedMutableMap.map16899map,
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map,
                                GeneratedMutableMap.map16900map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map16898map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map6649map,
                            GeneratedMutableMap.map6650map,
                            GeneratedMutableMap.map6651map,
                            GeneratedMutableMap.map6652map
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
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map17388map,
                                GeneratedMutableMap.map7104map,
                                GeneratedMutableMap.map7105map,
                                GeneratedMutableMap.map7106map,
                                GeneratedMutableMap.map7107map,
                                GeneratedMutableMap.map17389map
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
                            GeneratedMutableMap.map17390map,
                            GeneratedMutableMap.map17391map,
                            GeneratedMutableMap.map17392map,
                            GeneratedMutableMap.map17393map,
                            GeneratedMutableMap.map7108map,
                            GeneratedMutableMap.map7109map,
                            GeneratedMutableMap.map7110map,
                            GeneratedMutableMap.map7111map,
                            GeneratedMutableMap.map7112map,
                            GeneratedMutableMap.map7113map,
                            GeneratedMutableMap.map7114map,
                            GeneratedMutableMap.map7115map,
                            GeneratedMutableMap.map7116map,
                            GeneratedMutableMap.map7117map,
                            GeneratedMutableMap.map7118map,
                            GeneratedMutableMap.map7119map,
                            GeneratedMutableMap.map7120map,
                            GeneratedMutableMap.map7121map,
                            GeneratedMutableMap.map7122map,
                            GeneratedMutableMap.map7123map,
                            GeneratedMutableMap.map17394map,
                            GeneratedMutableMap.map17395map,
                            GeneratedMutableMap.map17396map,
                            GeneratedMutableMap.map17397map
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
                                GeneratedMutableMap.map17390map,
                                GeneratedMutableMap.map17391map,
                                GeneratedMutableMap.map17392map,
                                GeneratedMutableMap.map17393map,
                                GeneratedMutableMap.map7108map,
                                GeneratedMutableMap.map7109map,
                                GeneratedMutableMap.map7110map,
                                GeneratedMutableMap.map7111map,
                                GeneratedMutableMap.map7112map,
                                GeneratedMutableMap.map7113map,
                                GeneratedMutableMap.map7114map,
                                GeneratedMutableMap.map7115map,
                                GeneratedMutableMap.map7116map,
                                GeneratedMutableMap.map7117map,
                                GeneratedMutableMap.map7118map,
                                GeneratedMutableMap.map7119map,
                                GeneratedMutableMap.map7120map,
                                GeneratedMutableMap.map7121map,
                                GeneratedMutableMap.map7122map,
                                GeneratedMutableMap.map7123map,
                                GeneratedMutableMap.map17394map,
                                GeneratedMutableMap.map17395map,
                                GeneratedMutableMap.map17396map,
                                GeneratedMutableMap.map17397map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p1"
                            ), listOf(
                                GeneratedMutableMap.map17387map
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
                            GeneratedMutableMap.map7108map,
                            GeneratedMutableMap.map7109map,
                            GeneratedMutableMap.map7110map,
                            GeneratedMutableMap.map7111map,
                            GeneratedMutableMap.map7112map,
                            GeneratedMutableMap.map7113map,
                            GeneratedMutableMap.map7114map,
                            GeneratedMutableMap.map7115map,
                            GeneratedMutableMap.map7116map,
                            GeneratedMutableMap.map7117map,
                            GeneratedMutableMap.map7118map,
                            GeneratedMutableMap.map7119map,
                            GeneratedMutableMap.map7120map,
                            GeneratedMutableMap.map7121map,
                            GeneratedMutableMap.map7122map,
                            GeneratedMutableMap.map7123map
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
                                GeneratedMutableMap.map15659map,
                                GeneratedMutableMap.map15661map,
                                GeneratedMutableMap.map15662map,
                                GeneratedMutableMap.map16123map
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
                                GeneratedMutableMap.map16119map,
                                GeneratedMutableMap.map16121map,
                                GeneratedMutableMap.map15659map,
                                GeneratedMutableMap.map15660map,
                                GeneratedMutableMap.map15661map,
                                GeneratedMutableMap.map15662map,
                                GeneratedMutableMap.map18371map,
                                GeneratedMutableMap.map15663map,
                                GeneratedMutableMap.map18372map,
                                GeneratedMutableMap.map18373map,
                                GeneratedMutableMap.map16124map
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
                                GeneratedMutableMap.map18370map
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
                                        GeneratedMutableMap.map18446map,
                                        GeneratedMutableMap.map18447map,
                                        GeneratedMutableMap.map18448map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1"
                                ), listOf(
                                        GeneratedMutableMap.map18449map,
                                        GeneratedMutableMap.map18450map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                GeneratedMutableMap.map18449map,
                                GeneratedMutableMap.map18450map
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
                                GeneratedMutableMap.map18449map,
                                GeneratedMutableMap.map18450map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "X"
                                ), listOf(
                                        GeneratedMutableMap.map18446map,
                                        GeneratedMutableMap.map18447map,
                                        GeneratedMutableMap.map18448map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1"
                                ), listOf(
                                        GeneratedMutableMap.map18449map,
                                        GeneratedMutableMap.map18450map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                GeneratedMutableMap.map18449map,
                                GeneratedMutableMap.map18450map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                GeneratedMutableMap.map18449map,
                                GeneratedMutableMap.map18450map
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
                                        GeneratedMutableMap.map18449map,
                                        GeneratedMutableMap.map18450map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y2"
                                ), listOf(
                                        GeneratedMutableMap.map18451map,
                                        GeneratedMutableMap.map18452map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1",
                                "Y2"
                        ), listOf(
                                GeneratedMutableMap.map18453map,
                                GeneratedMutableMap.map18454map
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
                                GeneratedMutableMap.map18453map,
                                GeneratedMutableMap.map18454map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1"
                                ), listOf(
                                        GeneratedMutableMap.map18449map,
                                        GeneratedMutableMap.map18450map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y2"
                                ), listOf(
                                        GeneratedMutableMap.map18451map,
                                        GeneratedMutableMap.map18452map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1",
                                "Y2"
                        ), listOf(
                                GeneratedMutableMap.map18453map,
                                GeneratedMutableMap.map18454map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1",
                                "Y2"
                        ), listOf(
                                GeneratedMutableMap.map18453map,
                                GeneratedMutableMap.map18454map
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
                                GeneratedMutableMap.map18584map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                GeneratedMutableMap.map18585map,
                                GeneratedMutableMap.map18586map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd"
                        ), listOf(
                            GeneratedMutableMap.map18585map
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
                                GeneratedMutableMap.map18585map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#bb"
                            ), listOf(
                                GeneratedMutableMap.map18587map
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
                                        GeneratedMutableMap.map18584map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18674map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18675map
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
                                GeneratedMutableMap.map18675map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "#aa"
                                ), listOf(
                                        GeneratedMutableMap.map18584map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18674map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18675map
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
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18675map
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
                                        GeneratedMutableMap.map18675map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "Y",
                                        "#aa"
                                ), listOf(
                                        GeneratedMutableMap.map18671map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18675map
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
                                GeneratedMutableMap.map18675map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "X",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18675map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "Y",
                                        "#aa"
                                ), listOf(
                                        GeneratedMutableMap.map18671map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18675map
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
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18675map
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
                                        GeneratedMutableMap.map18675map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "Z"
                                ), listOf(
                                        GeneratedMutableMap.map18672map,
                                        GeneratedMutableMap.map18673map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18676map
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
                                GeneratedMutableMap.map18676map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "X",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18675map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "Z"
                                ), listOf(
                                        GeneratedMutableMap.map18672map,
                                        GeneratedMutableMap.map18673map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18676map
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
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18676map
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
                                        GeneratedMutableMap.map18732map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18733map,
                                        GeneratedMutableMap.map18734map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18735map
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
                                GeneratedMutableMap.map18735map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a"
                                ), listOf(
                                        GeneratedMutableMap.map18732map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18733map,
                                        GeneratedMutableMap.map18734map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18735map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18735map
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
                                        GeneratedMutableMap.map18794map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18795map,
                                        GeneratedMutableMap.map18796map,
                                        GeneratedMutableMap.map18797map,
                                        GeneratedMutableMap.map18798map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18799map,
                                GeneratedMutableMap.map18800map,
                                GeneratedMutableMap.map18801map
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
                                GeneratedMutableMap.map18799map,
                                GeneratedMutableMap.map18800map,
                                GeneratedMutableMap.map18801map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a"
                                ), listOf(
                                        GeneratedMutableMap.map18794map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18795map,
                                        GeneratedMutableMap.map18796map,
                                        GeneratedMutableMap.map18797map,
                                        GeneratedMutableMap.map18798map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18799map,
                                GeneratedMutableMap.map18800map,
                                GeneratedMutableMap.map18801map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18799map,
                                GeneratedMutableMap.map18800map,
                                GeneratedMutableMap.map18801map
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
                                GeneratedMutableMap.map18892map
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
                                GeneratedMutableMap.map18893map,
                                GeneratedMutableMap.map18894map,
                                GeneratedMutableMap.map18895map,
                                GeneratedMutableMap.map18896map
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
                                GeneratedMutableMap.map19062map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19061map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19066map
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
                                GeneratedMutableMap.map19066map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19063map,
                                GeneratedMutableMap.map19064map,
                                GeneratedMutableMap.map19065map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19066map
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
                                "#_19179"
                            ), listOf(
                                GeneratedMutableMap.map19245map,
                                GeneratedMutableMap.map19246map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19179"
                            ), listOf(
                                GeneratedMutableMap.map19245map,
                                GeneratedMutableMap.map19246map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19179"
                        ), listOf(
                            GeneratedMutableMap.map19245map,
                            GeneratedMutableMap.map19246map
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
                                "#_19179"
                            ), listOf(
                                GeneratedMutableMap.map19245map,
                                GeneratedMutableMap.map19246map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19179"
                            ), listOf(
                                GeneratedMutableMap.map19245map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19179"
                        ), listOf(
                            GeneratedMutableMap.map19245map
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
                                "#_19179"
                            ), listOf(
                                GeneratedMutableMap.map19245map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19179"
                            ), listOf(
                                GeneratedMutableMap.map19247map,
                                GeneratedMutableMap.map19248map,
                                GeneratedMutableMap.map19249map,
                                GeneratedMutableMap.map19250map,
                                GeneratedMutableMap.map19251map,
                                GeneratedMutableMap.map19252map,
                                GeneratedMutableMap.map19253map,
                                GeneratedMutableMap.map19254map,
                                GeneratedMutableMap.map19255map,
                                GeneratedMutableMap.map19256map,
                                GeneratedMutableMap.map19257map,
                                GeneratedMutableMap.map19258map,
                                GeneratedMutableMap.map19259map,
                                GeneratedMutableMap.map19260map,
                                GeneratedMutableMap.map19261map,
                                GeneratedMutableMap.map19262map,
                                GeneratedMutableMap.map19263map,
                                GeneratedMutableMap.map19264map,
                                GeneratedMutableMap.map19265map,
                                GeneratedMutableMap.map19266map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19179",
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
                                "#_19291"
                            ), listOf(
                                GeneratedMutableMap.map19382map,
                                GeneratedMutableMap.map19383map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19291"
                            ), listOf(
                                GeneratedMutableMap.map19382map,
                                GeneratedMutableMap.map19383map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19291"
                        ), listOf(
                            GeneratedMutableMap.map19382map,
                            GeneratedMutableMap.map19383map
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
                                "#_19291"
                            ), listOf(
                                GeneratedMutableMap.map19382map,
                                GeneratedMutableMap.map19383map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19291"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19291"
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
                                "#_19291"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19291"
                            ), listOf(
                                GeneratedMutableMap.map19362map,
                                GeneratedMutableMap.map19363map,
                                GeneratedMutableMap.map19364map,
                                GeneratedMutableMap.map19365map,
                                GeneratedMutableMap.map19366map,
                                GeneratedMutableMap.map19367map,
                                GeneratedMutableMap.map19368map,
                                GeneratedMutableMap.map19369map,
                                GeneratedMutableMap.map19370map,
                                GeneratedMutableMap.map19371map,
                                GeneratedMutableMap.map19372map,
                                GeneratedMutableMap.map19373map,
                                GeneratedMutableMap.map19374map,
                                GeneratedMutableMap.map19375map,
                                GeneratedMutableMap.map19376map,
                                GeneratedMutableMap.map19377map,
                                GeneratedMutableMap.map19378map,
                                GeneratedMutableMap.map19379map,
                                GeneratedMutableMap.map19380map,
                                GeneratedMutableMap.map19381map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19291",
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
                                "#_19405"
                            ), listOf(
                                GeneratedMutableMap.map19491map,
                                GeneratedMutableMap.map19492map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19405"
                            ), listOf(
                                GeneratedMutableMap.map19491map,
                                GeneratedMutableMap.map19492map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19405"
                        ), listOf(
                            GeneratedMutableMap.map19491map,
                            GeneratedMutableMap.map19492map
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
                                "#_19405"
                            ), listOf(
                                GeneratedMutableMap.map19491map,
                                GeneratedMutableMap.map19492map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19405"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19405"
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
                                "#_19405"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19405"
                            ), listOf(
                                GeneratedMutableMap.map19471map,
                                GeneratedMutableMap.map19472map,
                                GeneratedMutableMap.map19473map,
                                GeneratedMutableMap.map19474map,
                                GeneratedMutableMap.map19475map,
                                GeneratedMutableMap.map19476map,
                                GeneratedMutableMap.map19477map,
                                GeneratedMutableMap.map19478map,
                                GeneratedMutableMap.map19479map,
                                GeneratedMutableMap.map19480map,
                                GeneratedMutableMap.map19481map,
                                GeneratedMutableMap.map19482map,
                                GeneratedMutableMap.map19483map,
                                GeneratedMutableMap.map19484map,
                                GeneratedMutableMap.map19485map,
                                GeneratedMutableMap.map19486map,
                                GeneratedMutableMap.map19487map,
                                GeneratedMutableMap.map19488map,
                                GeneratedMutableMap.map19489map,
                                GeneratedMutableMap.map19490map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19405",
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
                                "#_19515"
                            ), listOf(
                                GeneratedMutableMap.map19617map,
                                GeneratedMutableMap.map19618map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19515"
                            ), listOf(
                                GeneratedMutableMap.map19617map,
                                GeneratedMutableMap.map19618map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19515"
                        ), listOf(
                            GeneratedMutableMap.map19617map,
                            GeneratedMutableMap.map19618map
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
                                "#_19515"
                            ), listOf(
                                GeneratedMutableMap.map19617map,
                                GeneratedMutableMap.map19618map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19515"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19515"
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
                                "#_19515"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19515"
                            ), listOf(
                                GeneratedMutableMap.map19597map,
                                GeneratedMutableMap.map19598map,
                                GeneratedMutableMap.map19599map,
                                GeneratedMutableMap.map19600map,
                                GeneratedMutableMap.map19601map,
                                GeneratedMutableMap.map19602map,
                                GeneratedMutableMap.map19603map,
                                GeneratedMutableMap.map19604map,
                                GeneratedMutableMap.map19605map,
                                GeneratedMutableMap.map19606map,
                                GeneratedMutableMap.map19607map,
                                GeneratedMutableMap.map19608map,
                                GeneratedMutableMap.map19609map,
                                GeneratedMutableMap.map19610map,
                                GeneratedMutableMap.map19611map,
                                GeneratedMutableMap.map19612map,
                                GeneratedMutableMap.map19613map,
                                GeneratedMutableMap.map19614map,
                                GeneratedMutableMap.map19615map,
                                GeneratedMutableMap.map19616map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19515",
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
                                "#_19641"
                            ), listOf(
                                GeneratedMutableMap.map19723map,
                                GeneratedMutableMap.map19724map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19641"
                            ), listOf(
                                GeneratedMutableMap.map19723map,
                                GeneratedMutableMap.map19724map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19641"
                        ), listOf(
                            GeneratedMutableMap.map19723map,
                            GeneratedMutableMap.map19724map
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
                                "#_19641"
                            ), listOf(
                                GeneratedMutableMap.map19723map,
                                GeneratedMutableMap.map19724map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19641"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19641"
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
                                "#_19641"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19641"
                            ), listOf(
                                GeneratedMutableMap.map19725map,
                                GeneratedMutableMap.map19726map,
                                GeneratedMutableMap.map19727map,
                                GeneratedMutableMap.map19728map,
                                GeneratedMutableMap.map19729map,
                                GeneratedMutableMap.map19730map,
                                GeneratedMutableMap.map19731map,
                                GeneratedMutableMap.map19732map,
                                GeneratedMutableMap.map19733map,
                                GeneratedMutableMap.map19734map,
                                GeneratedMutableMap.map19735map,
                                GeneratedMutableMap.map19736map,
                                GeneratedMutableMap.map19737map,
                                GeneratedMutableMap.map19738map,
                                GeneratedMutableMap.map19739map,
                                GeneratedMutableMap.map19740map,
                                GeneratedMutableMap.map19741map,
                                GeneratedMutableMap.map19742map,
                                GeneratedMutableMap.map19743map,
                                GeneratedMutableMap.map19744map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19641",
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
                                "#_19767"
                            ), listOf(
                                GeneratedMutableMap.map19849map,
                                GeneratedMutableMap.map19850map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19767"
                            ), listOf(
                                GeneratedMutableMap.map19849map,
                                GeneratedMutableMap.map19850map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19767"
                        ), listOf(
                            GeneratedMutableMap.map19849map,
                            GeneratedMutableMap.map19850map
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
                                "#_19767"
                            ), listOf(
                                GeneratedMutableMap.map19849map,
                                GeneratedMutableMap.map19850map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19767"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19767"
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
                                "#_19767"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19767"
                            ), listOf(
                                GeneratedMutableMap.map19851map,
                                GeneratedMutableMap.map19852map,
                                GeneratedMutableMap.map19853map,
                                GeneratedMutableMap.map19854map,
                                GeneratedMutableMap.map19855map,
                                GeneratedMutableMap.map19856map,
                                GeneratedMutableMap.map19857map,
                                GeneratedMutableMap.map19858map,
                                GeneratedMutableMap.map19859map,
                                GeneratedMutableMap.map19860map,
                                GeneratedMutableMap.map19861map,
                                GeneratedMutableMap.map19862map,
                                GeneratedMutableMap.map19863map,
                                GeneratedMutableMap.map19864map,
                                GeneratedMutableMap.map19865map,
                                GeneratedMutableMap.map19866map,
                                GeneratedMutableMap.map19867map,
                                GeneratedMutableMap.map19868map,
                                GeneratedMutableMap.map19869map,
                                GeneratedMutableMap.map19870map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19767",
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
                                "#_19894"
                            ), listOf(
                                GeneratedMutableMap.map19962map,
                                GeneratedMutableMap.map19963map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19894"
                            ), listOf(
                                GeneratedMutableMap.map19962map,
                                GeneratedMutableMap.map19963map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19894"
                        ), listOf(
                            GeneratedMutableMap.map19962map,
                            GeneratedMutableMap.map19963map
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
                                "#_19894"
                            ), listOf(
                                GeneratedMutableMap.map19962map,
                                GeneratedMutableMap.map19963map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19894"
                            ), listOf(
                                GeneratedMutableMap.map19962map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19894"
                        ), listOf(
                            GeneratedMutableMap.map19962map
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
                                "#_19894"
                            ), listOf(
                                GeneratedMutableMap.map19962map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#_19894"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19894",
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
                                GeneratedMutableMap.map20068map,
                                GeneratedMutableMap.map20069map
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
                                GeneratedMutableMap.map20068map
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
                                "#_20086",
                                "#_20091",
                                "#_20083"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20083"
                            ), listOf(
                                GeneratedMutableMap.map20175map,
                                GeneratedMutableMap.map20176map,
                                GeneratedMutableMap.map20177map,
                                GeneratedMutableMap.map20178map,
                                GeneratedMutableMap.map20179map,
                                GeneratedMutableMap.map20180map,
                                GeneratedMutableMap.map20181map,
                                GeneratedMutableMap.map20182map,
                                GeneratedMutableMap.map20183map,
                                GeneratedMutableMap.map20184map,
                                GeneratedMutableMap.map20185map,
                                GeneratedMutableMap.map20186map,
                                GeneratedMutableMap.map20187map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20086",
                            "#_20091",
                            "#_20083",
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
                                "#_20204",
                                "#_20208",
                                "#_20218",
                                "#_20201"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20201"
                            ), listOf(
                                GeneratedMutableMap.map20332map,
                                GeneratedMutableMap.map20333map,
                                GeneratedMutableMap.map20334map,
                                GeneratedMutableMap.map20335map,
                                GeneratedMutableMap.map20336map,
                                GeneratedMutableMap.map20337map,
                                GeneratedMutableMap.map20338map,
                                GeneratedMutableMap.map20339map,
                                GeneratedMutableMap.map20340map,
                                GeneratedMutableMap.map20341map,
                                GeneratedMutableMap.map20342map,
                                GeneratedMutableMap.map20343map,
                                GeneratedMutableMap.map20344map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20204",
                            "#_20208",
                            "#_20218",
                            "#_20201",
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
                                "#_20358",
                                "#_20369",
                                "#_20374",
                                "#_20366"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20358"
                            ), listOf(
                                GeneratedMutableMap.map20489map,
                                GeneratedMutableMap.map20490map,
                                GeneratedMutableMap.map20491map,
                                GeneratedMutableMap.map20492map,
                                GeneratedMutableMap.map20493map,
                                GeneratedMutableMap.map20494map,
                                GeneratedMutableMap.map20495map,
                                GeneratedMutableMap.map20496map,
                                GeneratedMutableMap.map20497map,
                                GeneratedMutableMap.map20498map,
                                GeneratedMutableMap.map20499map,
                                GeneratedMutableMap.map20500map,
                                GeneratedMutableMap.map20501map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20358",
                            "#_20369",
                            "#_20374",
                            "#_20366",
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
                                "#_20515"
                            ), listOf(
                                GeneratedMutableMap.map20619map,
                                GeneratedMutableMap.map20620map,
                                GeneratedMutableMap.map20621map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20520"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20515",
                            "#_20520"
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
                                "#_20515",
                                "#_20520",
                                "#_20525"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20515"
                            ), listOf(
                                GeneratedMutableMap.map20622map,
                                GeneratedMutableMap.map20623map,
                                GeneratedMutableMap.map20624map,
                                GeneratedMutableMap.map20625map,
                                GeneratedMutableMap.map20626map,
                                GeneratedMutableMap.map20627map,
                                GeneratedMutableMap.map20628map,
                                GeneratedMutableMap.map20629map,
                                GeneratedMutableMap.map20630map,
                                GeneratedMutableMap.map20631map,
                                GeneratedMutableMap.map20632map,
                                GeneratedMutableMap.map20633map,
                                GeneratedMutableMap.map20634map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20515",
                            "#_20520",
                            "#_20525",
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
                                "#_20648"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20656"
                            ), listOf(
                                GeneratedMutableMap.map20791map,
                                GeneratedMutableMap.map20792map,
                                GeneratedMutableMap.map20793map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20648",
                            "#_20656"
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
                                "#_20648",
                                "#_20656",
                                "#_20661",
                                "#_20666"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20648"
                            ), listOf(
                                GeneratedMutableMap.map20794map,
                                GeneratedMutableMap.map20795map,
                                GeneratedMutableMap.map20796map,
                                GeneratedMutableMap.map20797map,
                                GeneratedMutableMap.map20798map,
                                GeneratedMutableMap.map20799map,
                                GeneratedMutableMap.map20800map,
                                GeneratedMutableMap.map20801map,
                                GeneratedMutableMap.map20802map,
                                GeneratedMutableMap.map20803map,
                                GeneratedMutableMap.map20804map,
                                GeneratedMutableMap.map20805map,
                                GeneratedMutableMap.map20806map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20648",
                            "#_20656",
                            "#_20661",
                            "#_20666",
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
                                "#_20820"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20828"
                            ), listOf(
                                GeneratedMutableMap.map20988map,
                                GeneratedMutableMap.map20989map,
                                GeneratedMutableMap.map20990map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20820",
                            "#_20828"
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
                                "#_20820",
                                "#_20828",
                                "#_20833",
                                "#_20838",
                                "#_20843"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20820"
                            ), listOf(
                                GeneratedMutableMap.map20991map,
                                GeneratedMutableMap.map20992map,
                                GeneratedMutableMap.map20993map,
                                GeneratedMutableMap.map20994map,
                                GeneratedMutableMap.map20995map,
                                GeneratedMutableMap.map20996map,
                                GeneratedMutableMap.map20997map,
                                GeneratedMutableMap.map20998map,
                                GeneratedMutableMap.map20999map,
                                GeneratedMutableMap.map21000map,
                                GeneratedMutableMap.map21001map,
                                GeneratedMutableMap.map21002map,
                                GeneratedMutableMap.map21003map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20820",
                            "#_20828",
                            "#_20833",
                            "#_20838",
                            "#_20843",
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
                                "#_21017"
                            ), listOf(
                                GeneratedMutableMap.map21212map,
                                GeneratedMutableMap.map21213map,
                                GeneratedMutableMap.map21214map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21022"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21017",
                            "#_21022"
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
                                "#_21017",
                                "#_21022"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21026"
                            ), listOf(
                                GeneratedMutableMap.map21196map,
                                GeneratedMutableMap.map21197map,
                                GeneratedMutableMap.map21198map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21017",
                            "#_21022",
                            "#_21026"
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
                                "#_21017",
                                "#_21022",
                                "#_21026",
                                "#_21031",
                                "#_21036",
                                "#_21042"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21017"
                            ), listOf(
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
                                GeneratedMutableMap.map21210map,
                                GeneratedMutableMap.map21211map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21017",
                            "#_21022",
                            "#_21026",
                            "#_21031",
                            "#_21036",
                            "#_21042",
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
                                "#_21228",
                                "#_21236"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21228"
                            ), listOf(
                                GeneratedMutableMap.map21335map,
                                GeneratedMutableMap.map21336map,
                                GeneratedMutableMap.map21337map,
                                GeneratedMutableMap.map21338map,
                                GeneratedMutableMap.map21339map,
                                GeneratedMutableMap.map21340map,
                                GeneratedMutableMap.map21341map,
                                GeneratedMutableMap.map21342map,
                                GeneratedMutableMap.map21343map,
                                GeneratedMutableMap.map21344map,
                                GeneratedMutableMap.map21345map,
                                GeneratedMutableMap.map21346map,
                                GeneratedMutableMap.map21347map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21228",
                            "#_21236",
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
                                        GeneratedMutableMap.map24269map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map24268map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24270map
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
                                GeneratedMutableMap.map24270map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "str1"
                                ), listOf(
                                        GeneratedMutableMap.map24269map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map24268map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24270map
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
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24270map
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
                                        GeneratedMutableMap.map24863map,
                                        GeneratedMutableMap.map24864map,
                                        GeneratedMutableMap.map24865map,
                                        GeneratedMutableMap.map24866map,
                                        GeneratedMutableMap.map24867map,
                                        GeneratedMutableMap.map24868map,
                                        GeneratedMutableMap.map24869map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s2",
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map24870map,
                                        GeneratedMutableMap.map24871map,
                                        GeneratedMutableMap.map24872map,
                                        GeneratedMutableMap.map24873map,
                                        GeneratedMutableMap.map24874map,
                                        GeneratedMutableMap.map24875map,
                                        GeneratedMutableMap.map24876map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24877map,
                                GeneratedMutableMap.map24878map,
                                GeneratedMutableMap.map24879map,
                                GeneratedMutableMap.map24880map,
                                GeneratedMutableMap.map24881map,
                                GeneratedMutableMap.map24882map,
                                GeneratedMutableMap.map24883map,
                                GeneratedMutableMap.map24884map,
                                GeneratedMutableMap.map24885map,
                                GeneratedMutableMap.map24886map,
                                GeneratedMutableMap.map24887map,
                                GeneratedMutableMap.map24888map,
                                GeneratedMutableMap.map24889map,
                                GeneratedMutableMap.map24890map,
                                GeneratedMutableMap.map24891map,
                                GeneratedMutableMap.map24892map,
                                GeneratedMutableMap.map24893map,
                                GeneratedMutableMap.map24894map,
                                GeneratedMutableMap.map24895map,
                                GeneratedMutableMap.map24896map,
                                GeneratedMutableMap.map24897map,
                                GeneratedMutableMap.map24898map,
                                GeneratedMutableMap.map24899map,
                                GeneratedMutableMap.map24900map,
                                GeneratedMutableMap.map24901map,
                                GeneratedMutableMap.map24902map,
                                GeneratedMutableMap.map24903map,
                                GeneratedMutableMap.map24904map,
                                GeneratedMutableMap.map24905map,
                                GeneratedMutableMap.map24906map,
                                GeneratedMutableMap.map24907map,
                                GeneratedMutableMap.map24908map,
                                GeneratedMutableMap.map24909map,
                                GeneratedMutableMap.map24910map,
                                GeneratedMutableMap.map24911map,
                                GeneratedMutableMap.map24912map,
                                GeneratedMutableMap.map24913map,
                                GeneratedMutableMap.map24914map,
                                GeneratedMutableMap.map24915map,
                                GeneratedMutableMap.map24916map,
                                GeneratedMutableMap.map24917map,
                                GeneratedMutableMap.map24918map,
                                GeneratedMutableMap.map24919map,
                                GeneratedMutableMap.map24920map,
                                GeneratedMutableMap.map24921map,
                                GeneratedMutableMap.map24922map,
                                GeneratedMutableMap.map24923map,
                                GeneratedMutableMap.map24924map,
                                GeneratedMutableMap.map24925map
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
                                GeneratedMutableMap.map24877map,
                                GeneratedMutableMap.map24878map,
                                GeneratedMutableMap.map24879map,
                                GeneratedMutableMap.map24880map,
                                GeneratedMutableMap.map24881map,
                                GeneratedMutableMap.map24882map,
                                GeneratedMutableMap.map24883map,
                                GeneratedMutableMap.map24884map,
                                GeneratedMutableMap.map24885map,
                                GeneratedMutableMap.map24886map,
                                GeneratedMutableMap.map24887map,
                                GeneratedMutableMap.map24888map,
                                GeneratedMutableMap.map24889map,
                                GeneratedMutableMap.map24890map,
                                GeneratedMutableMap.map24891map,
                                GeneratedMutableMap.map24892map,
                                GeneratedMutableMap.map24893map,
                                GeneratedMutableMap.map24894map,
                                GeneratedMutableMap.map24895map,
                                GeneratedMutableMap.map24896map,
                                GeneratedMutableMap.map24897map,
                                GeneratedMutableMap.map24898map,
                                GeneratedMutableMap.map24899map,
                                GeneratedMutableMap.map24900map,
                                GeneratedMutableMap.map24901map,
                                GeneratedMutableMap.map24902map,
                                GeneratedMutableMap.map24903map,
                                GeneratedMutableMap.map24904map,
                                GeneratedMutableMap.map24905map,
                                GeneratedMutableMap.map24906map,
                                GeneratedMutableMap.map24907map,
                                GeneratedMutableMap.map24908map,
                                GeneratedMutableMap.map24909map,
                                GeneratedMutableMap.map24910map,
                                GeneratedMutableMap.map24911map,
                                GeneratedMutableMap.map24912map,
                                GeneratedMutableMap.map24913map,
                                GeneratedMutableMap.map24914map,
                                GeneratedMutableMap.map24915map,
                                GeneratedMutableMap.map24916map,
                                GeneratedMutableMap.map24917map,
                                GeneratedMutableMap.map24918map,
                                GeneratedMutableMap.map24919map,
                                GeneratedMutableMap.map24920map,
                                GeneratedMutableMap.map24921map,
                                GeneratedMutableMap.map24922map,
                                GeneratedMutableMap.map24923map,
                                GeneratedMutableMap.map24924map,
                                GeneratedMutableMap.map24925map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s1",
                                        "str1"
                                ), listOf(
                                        GeneratedMutableMap.map24863map,
                                        GeneratedMutableMap.map24864map,
                                        GeneratedMutableMap.map24865map,
                                        GeneratedMutableMap.map24866map,
                                        GeneratedMutableMap.map24867map,
                                        GeneratedMutableMap.map24868map,
                                        GeneratedMutableMap.map24869map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s2",
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map24870map,
                                        GeneratedMutableMap.map24871map,
                                        GeneratedMutableMap.map24872map,
                                        GeneratedMutableMap.map24873map,
                                        GeneratedMutableMap.map24874map,
                                        GeneratedMutableMap.map24875map,
                                        GeneratedMutableMap.map24876map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24877map,
                                GeneratedMutableMap.map24878map,
                                GeneratedMutableMap.map24879map,
                                GeneratedMutableMap.map24880map,
                                GeneratedMutableMap.map24881map,
                                GeneratedMutableMap.map24882map,
                                GeneratedMutableMap.map24883map,
                                GeneratedMutableMap.map24884map,
                                GeneratedMutableMap.map24885map,
                                GeneratedMutableMap.map24886map,
                                GeneratedMutableMap.map24887map,
                                GeneratedMutableMap.map24888map,
                                GeneratedMutableMap.map24889map,
                                GeneratedMutableMap.map24890map,
                                GeneratedMutableMap.map24891map,
                                GeneratedMutableMap.map24892map,
                                GeneratedMutableMap.map24893map,
                                GeneratedMutableMap.map24894map,
                                GeneratedMutableMap.map24895map,
                                GeneratedMutableMap.map24896map,
                                GeneratedMutableMap.map24897map,
                                GeneratedMutableMap.map24898map,
                                GeneratedMutableMap.map24899map,
                                GeneratedMutableMap.map24900map,
                                GeneratedMutableMap.map24901map,
                                GeneratedMutableMap.map24902map,
                                GeneratedMutableMap.map24903map,
                                GeneratedMutableMap.map24904map,
                                GeneratedMutableMap.map24905map,
                                GeneratedMutableMap.map24906map,
                                GeneratedMutableMap.map24907map,
                                GeneratedMutableMap.map24908map,
                                GeneratedMutableMap.map24909map,
                                GeneratedMutableMap.map24910map,
                                GeneratedMutableMap.map24911map,
                                GeneratedMutableMap.map24912map,
                                GeneratedMutableMap.map24913map,
                                GeneratedMutableMap.map24914map,
                                GeneratedMutableMap.map24915map,
                                GeneratedMutableMap.map24916map,
                                GeneratedMutableMap.map24917map,
                                GeneratedMutableMap.map24918map,
                                GeneratedMutableMap.map24919map,
                                GeneratedMutableMap.map24920map,
                                GeneratedMutableMap.map24921map,
                                GeneratedMutableMap.map24922map,
                                GeneratedMutableMap.map24923map,
                                GeneratedMutableMap.map24924map,
                                GeneratedMutableMap.map24925map
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
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24877map,
                                GeneratedMutableMap.map24878map,
                                GeneratedMutableMap.map24879map,
                                GeneratedMutableMap.map24880map,
                                GeneratedMutableMap.map24881map,
                                GeneratedMutableMap.map24882map,
                                GeneratedMutableMap.map24883map,
                                GeneratedMutableMap.map24884map,
                                GeneratedMutableMap.map24885map,
                                GeneratedMutableMap.map24886map,
                                GeneratedMutableMap.map24887map,
                                GeneratedMutableMap.map24888map,
                                GeneratedMutableMap.map24889map,
                                GeneratedMutableMap.map24890map,
                                GeneratedMutableMap.map24891map,
                                GeneratedMutableMap.map24892map,
                                GeneratedMutableMap.map24893map,
                                GeneratedMutableMap.map24894map,
                                GeneratedMutableMap.map24895map,
                                GeneratedMutableMap.map24896map,
                                GeneratedMutableMap.map24897map,
                                GeneratedMutableMap.map24898map,
                                GeneratedMutableMap.map24899map,
                                GeneratedMutableMap.map24900map,
                                GeneratedMutableMap.map24901map,
                                GeneratedMutableMap.map24902map,
                                GeneratedMutableMap.map24903map,
                                GeneratedMutableMap.map24904map,
                                GeneratedMutableMap.map24905map,
                                GeneratedMutableMap.map24906map,
                                GeneratedMutableMap.map24907map,
                                GeneratedMutableMap.map24908map,
                                GeneratedMutableMap.map24909map,
                                GeneratedMutableMap.map24910map,
                                GeneratedMutableMap.map24911map,
                                GeneratedMutableMap.map24912map,
                                GeneratedMutableMap.map24913map,
                                GeneratedMutableMap.map24914map,
                                GeneratedMutableMap.map24915map,
                                GeneratedMutableMap.map24916map,
                                GeneratedMutableMap.map24917map,
                                GeneratedMutableMap.map24918map,
                                GeneratedMutableMap.map24919map,
                                GeneratedMutableMap.map24920map,
                                GeneratedMutableMap.map24921map,
                                GeneratedMutableMap.map24922map,
                                GeneratedMutableMap.map24923map,
                                GeneratedMutableMap.map24924map,
                                GeneratedMutableMap.map24925map
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
                                        GeneratedMutableMap.map26600map,
                                        GeneratedMutableMap.map26601map,
                                        GeneratedMutableMap.map26602map,
                                        GeneratedMutableMap.map26603map,
                                        GeneratedMutableMap.map26604map,
                                        GeneratedMutableMap.map26605map,
                                        GeneratedMutableMap.map26606map,
                                        GeneratedMutableMap.map26607map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map26608map,
                                        GeneratedMutableMap.map26609map,
                                        GeneratedMutableMap.map26610map,
                                        GeneratedMutableMap.map26611map,
                                        GeneratedMutableMap.map26612map,
                                        GeneratedMutableMap.map26613map,
                                        GeneratedMutableMap.map26614map,
                                        GeneratedMutableMap.map26615map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map26616map,
                                GeneratedMutableMap.map26617map,
                                GeneratedMutableMap.map26618map,
                                GeneratedMutableMap.map26619map,
                                GeneratedMutableMap.map26620map,
                                GeneratedMutableMap.map26621map,
                                GeneratedMutableMap.map26622map,
                                GeneratedMutableMap.map26623map
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
                                GeneratedMutableMap.map26616map,
                                GeneratedMutableMap.map26617map,
                                GeneratedMutableMap.map26618map,
                                GeneratedMutableMap.map26619map,
                                GeneratedMutableMap.map26620map,
                                GeneratedMutableMap.map26621map,
                                GeneratedMutableMap.map26622map,
                                GeneratedMutableMap.map26623map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s",
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map26600map,
                                        GeneratedMutableMap.map26601map,
                                        GeneratedMutableMap.map26602map,
                                        GeneratedMutableMap.map26603map,
                                        GeneratedMutableMap.map26604map,
                                        GeneratedMutableMap.map26605map,
                                        GeneratedMutableMap.map26606map,
                                        GeneratedMutableMap.map26607map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map26608map,
                                        GeneratedMutableMap.map26609map,
                                        GeneratedMutableMap.map26610map,
                                        GeneratedMutableMap.map26611map,
                                        GeneratedMutableMap.map26612map,
                                        GeneratedMutableMap.map26613map,
                                        GeneratedMutableMap.map26614map,
                                        GeneratedMutableMap.map26615map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map26616map,
                                GeneratedMutableMap.map26617map,
                                GeneratedMutableMap.map26618map,
                                GeneratedMutableMap.map26619map,
                                GeneratedMutableMap.map26620map,
                                GeneratedMutableMap.map26621map,
                                GeneratedMutableMap.map26622map,
                                GeneratedMutableMap.map26623map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map26616map,
                                GeneratedMutableMap.map26617map,
                                GeneratedMutableMap.map26618map,
                                GeneratedMutableMap.map26619map,
                                GeneratedMutableMap.map26620map,
                                GeneratedMutableMap.map26621map,
                                GeneratedMutableMap.map26622map,
                                GeneratedMutableMap.map26623map
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
                                        GeneratedMutableMap.map30282map,
                                        GeneratedMutableMap.map30283map,
                                        GeneratedMutableMap.map30284map,
                                        GeneratedMutableMap.map30285map,
                                        GeneratedMutableMap.map30286map,
                                        GeneratedMutableMap.map30287map,
                                        GeneratedMutableMap.map30288map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "b",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map30289map,
                                        GeneratedMutableMap.map30290map,
                                        GeneratedMutableMap.map30291map,
                                        GeneratedMutableMap.map30292map,
                                        GeneratedMutableMap.map30293map,
                                        GeneratedMutableMap.map30294map,
                                        GeneratedMutableMap.map30295map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map30296map,
                                GeneratedMutableMap.map30297map,
                                GeneratedMutableMap.map30298map,
                                GeneratedMutableMap.map30299map,
                                GeneratedMutableMap.map30300map,
                                GeneratedMutableMap.map30301map,
                                GeneratedMutableMap.map30302map,
                                GeneratedMutableMap.map30303map,
                                GeneratedMutableMap.map30304map,
                                GeneratedMutableMap.map30305map,
                                GeneratedMutableMap.map30306map,
                                GeneratedMutableMap.map30307map,
                                GeneratedMutableMap.map30308map,
                                GeneratedMutableMap.map30309map,
                                GeneratedMutableMap.map30310map,
                                GeneratedMutableMap.map30311map,
                                GeneratedMutableMap.map30312map,
                                GeneratedMutableMap.map30313map,
                                GeneratedMutableMap.map30314map,
                                GeneratedMutableMap.map30315map,
                                GeneratedMutableMap.map30316map,
                                GeneratedMutableMap.map30317map,
                                GeneratedMutableMap.map30318map,
                                GeneratedMutableMap.map30319map,
                                GeneratedMutableMap.map30320map,
                                GeneratedMutableMap.map30321map,
                                GeneratedMutableMap.map30322map,
                                GeneratedMutableMap.map30323map,
                                GeneratedMutableMap.map30324map,
                                GeneratedMutableMap.map30325map,
                                GeneratedMutableMap.map30326map,
                                GeneratedMutableMap.map30327map,
                                GeneratedMutableMap.map30328map,
                                GeneratedMutableMap.map30329map,
                                GeneratedMutableMap.map30330map,
                                GeneratedMutableMap.map30331map,
                                GeneratedMutableMap.map30332map,
                                GeneratedMutableMap.map30333map,
                                GeneratedMutableMap.map30334map,
                                GeneratedMutableMap.map30335map,
                                GeneratedMutableMap.map30336map,
                                GeneratedMutableMap.map30337map,
                                GeneratedMutableMap.map30338map,
                                GeneratedMutableMap.map30339map,
                                GeneratedMutableMap.map30340map,
                                GeneratedMutableMap.map30341map,
                                GeneratedMutableMap.map30342map,
                                GeneratedMutableMap.map30343map,
                                GeneratedMutableMap.map30344map
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
                                GeneratedMutableMap.map30296map,
                                GeneratedMutableMap.map30297map,
                                GeneratedMutableMap.map30298map,
                                GeneratedMutableMap.map30299map,
                                GeneratedMutableMap.map30300map,
                                GeneratedMutableMap.map30301map,
                                GeneratedMutableMap.map30302map,
                                GeneratedMutableMap.map30303map,
                                GeneratedMutableMap.map30304map,
                                GeneratedMutableMap.map30305map,
                                GeneratedMutableMap.map30306map,
                                GeneratedMutableMap.map30307map,
                                GeneratedMutableMap.map30308map,
                                GeneratedMutableMap.map30309map,
                                GeneratedMutableMap.map30310map,
                                GeneratedMutableMap.map30311map,
                                GeneratedMutableMap.map30312map,
                                GeneratedMutableMap.map30313map,
                                GeneratedMutableMap.map30314map,
                                GeneratedMutableMap.map30315map,
                                GeneratedMutableMap.map30316map,
                                GeneratedMutableMap.map30317map,
                                GeneratedMutableMap.map30318map,
                                GeneratedMutableMap.map30319map,
                                GeneratedMutableMap.map30320map,
                                GeneratedMutableMap.map30321map,
                                GeneratedMutableMap.map30322map,
                                GeneratedMutableMap.map30323map,
                                GeneratedMutableMap.map30324map,
                                GeneratedMutableMap.map30325map,
                                GeneratedMutableMap.map30326map,
                                GeneratedMutableMap.map30327map,
                                GeneratedMutableMap.map30328map,
                                GeneratedMutableMap.map30329map,
                                GeneratedMutableMap.map30330map,
                                GeneratedMutableMap.map30331map,
                                GeneratedMutableMap.map30332map,
                                GeneratedMutableMap.map30333map,
                                GeneratedMutableMap.map30334map,
                                GeneratedMutableMap.map30335map,
                                GeneratedMutableMap.map30336map,
                                GeneratedMutableMap.map30337map,
                                GeneratedMutableMap.map30338map,
                                GeneratedMutableMap.map30339map,
                                GeneratedMutableMap.map30340map,
                                GeneratedMutableMap.map30341map,
                                GeneratedMutableMap.map30342map,
                                GeneratedMutableMap.map30343map,
                                GeneratedMutableMap.map30344map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "a",
                                        "s1"
                                ), listOf(
                                        GeneratedMutableMap.map30282map,
                                        GeneratedMutableMap.map30283map,
                                        GeneratedMutableMap.map30284map,
                                        GeneratedMutableMap.map30285map,
                                        GeneratedMutableMap.map30286map,
                                        GeneratedMutableMap.map30287map,
                                        GeneratedMutableMap.map30288map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "b",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map30289map,
                                        GeneratedMutableMap.map30290map,
                                        GeneratedMutableMap.map30291map,
                                        GeneratedMutableMap.map30292map,
                                        GeneratedMutableMap.map30293map,
                                        GeneratedMutableMap.map30294map,
                                        GeneratedMutableMap.map30295map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map30296map,
                                GeneratedMutableMap.map30297map,
                                GeneratedMutableMap.map30298map,
                                GeneratedMutableMap.map30299map,
                                GeneratedMutableMap.map30300map,
                                GeneratedMutableMap.map30301map,
                                GeneratedMutableMap.map30302map,
                                GeneratedMutableMap.map30303map,
                                GeneratedMutableMap.map30304map,
                                GeneratedMutableMap.map30305map,
                                GeneratedMutableMap.map30306map,
                                GeneratedMutableMap.map30307map,
                                GeneratedMutableMap.map30308map,
                                GeneratedMutableMap.map30309map,
                                GeneratedMutableMap.map30310map,
                                GeneratedMutableMap.map30311map,
                                GeneratedMutableMap.map30312map,
                                GeneratedMutableMap.map30313map,
                                GeneratedMutableMap.map30314map,
                                GeneratedMutableMap.map30315map,
                                GeneratedMutableMap.map30316map,
                                GeneratedMutableMap.map30317map,
                                GeneratedMutableMap.map30318map,
                                GeneratedMutableMap.map30319map,
                                GeneratedMutableMap.map30320map,
                                GeneratedMutableMap.map30321map,
                                GeneratedMutableMap.map30322map,
                                GeneratedMutableMap.map30323map,
                                GeneratedMutableMap.map30324map,
                                GeneratedMutableMap.map30325map,
                                GeneratedMutableMap.map30326map,
                                GeneratedMutableMap.map30327map,
                                GeneratedMutableMap.map30328map,
                                GeneratedMutableMap.map30329map,
                                GeneratedMutableMap.map30330map,
                                GeneratedMutableMap.map30331map,
                                GeneratedMutableMap.map30332map,
                                GeneratedMutableMap.map30333map,
                                GeneratedMutableMap.map30334map,
                                GeneratedMutableMap.map30335map,
                                GeneratedMutableMap.map30336map,
                                GeneratedMutableMap.map30337map,
                                GeneratedMutableMap.map30338map,
                                GeneratedMutableMap.map30339map,
                                GeneratedMutableMap.map30340map,
                                GeneratedMutableMap.map30341map,
                                GeneratedMutableMap.map30342map,
                                GeneratedMutableMap.map30343map,
                                GeneratedMutableMap.map30344map
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
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map30296map,
                                GeneratedMutableMap.map30297map,
                                GeneratedMutableMap.map30298map,
                                GeneratedMutableMap.map30299map,
                                GeneratedMutableMap.map30300map,
                                GeneratedMutableMap.map30301map,
                                GeneratedMutableMap.map30302map,
                                GeneratedMutableMap.map30303map,
                                GeneratedMutableMap.map30304map,
                                GeneratedMutableMap.map30305map,
                                GeneratedMutableMap.map30306map,
                                GeneratedMutableMap.map30307map,
                                GeneratedMutableMap.map30308map,
                                GeneratedMutableMap.map30309map,
                                GeneratedMutableMap.map30310map,
                                GeneratedMutableMap.map30311map,
                                GeneratedMutableMap.map30312map,
                                GeneratedMutableMap.map30313map,
                                GeneratedMutableMap.map30314map,
                                GeneratedMutableMap.map30315map,
                                GeneratedMutableMap.map30316map,
                                GeneratedMutableMap.map30317map,
                                GeneratedMutableMap.map30318map,
                                GeneratedMutableMap.map30319map,
                                GeneratedMutableMap.map30320map,
                                GeneratedMutableMap.map30321map,
                                GeneratedMutableMap.map30322map,
                                GeneratedMutableMap.map30323map,
                                GeneratedMutableMap.map30324map,
                                GeneratedMutableMap.map30325map,
                                GeneratedMutableMap.map30326map,
                                GeneratedMutableMap.map30327map,
                                GeneratedMutableMap.map30328map,
                                GeneratedMutableMap.map30329map,
                                GeneratedMutableMap.map30330map,
                                GeneratedMutableMap.map30331map,
                                GeneratedMutableMap.map30332map,
                                GeneratedMutableMap.map30333map,
                                GeneratedMutableMap.map30334map,
                                GeneratedMutableMap.map30335map,
                                GeneratedMutableMap.map30336map,
                                GeneratedMutableMap.map30337map,
                                GeneratedMutableMap.map30338map,
                                GeneratedMutableMap.map30339map,
                                GeneratedMutableMap.map30340map,
                                GeneratedMutableMap.map30341map,
                                GeneratedMutableMap.map30342map,
                                GeneratedMutableMap.map30343map,
                                GeneratedMutableMap.map30344map
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
                                        GeneratedMutableMap.map31676map,
                                        GeneratedMutableMap.map31677map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        GeneratedMutableMap.map31809map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map31810map,
                                GeneratedMutableMap.map31811map
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
                                GeneratedMutableMap.map31810map,
                                GeneratedMutableMap.map31811map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map31676map,
                                        GeneratedMutableMap.map31677map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        GeneratedMutableMap.map31809map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map31810map,
                                GeneratedMutableMap.map31811map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map31810map,
                                GeneratedMutableMap.map31811map
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
                                        GeneratedMutableMap.map31676map,
                                        GeneratedMutableMap.map31677map,
                                        GeneratedMutableMap.map31953map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        GeneratedMutableMap.map31809map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map31810map,
                                GeneratedMutableMap.map31811map,
                                GeneratedMutableMap.map31954map
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
                                GeneratedMutableMap.map31810map,
                                GeneratedMutableMap.map31811map,
                                GeneratedMutableMap.map31954map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map31676map,
                                        GeneratedMutableMap.map31677map,
                                        GeneratedMutableMap.map31953map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        GeneratedMutableMap.map31809map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map31810map,
                                GeneratedMutableMap.map31811map,
                                GeneratedMutableMap.map31954map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map31810map,
                                GeneratedMutableMap.map31811map,
                                GeneratedMutableMap.map31954map
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
                                        GeneratedMutableMap.map10096map,
                                        GeneratedMutableMap.map10097map,
                                        GeneratedMutableMap.map32229map,
                                        GeneratedMutableMap.map10099map,
                                        GeneratedMutableMap.map32230map,
                                        GeneratedMutableMap.map10101map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10274map,
                                        GeneratedMutableMap.map10275map,
                                        GeneratedMutableMap.map32355map,
                                        GeneratedMutableMap.map10277map,
                                        GeneratedMutableMap.map32356map,
                                        GeneratedMutableMap.map10279map
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
                        LOPJoin(
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
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10274map,
                                        GeneratedMutableMap.map10275map,
                                        GeneratedMutableMap.map32355map,
                                        GeneratedMutableMap.map10277map,
                                        GeneratedMutableMap.map32356map,
                                        GeneratedMutableMap.map10279map
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
                        POPJoinHashMap(
                                dictionary,
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map34077map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34078map,
                                        GeneratedMutableMap.map34079map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34080map,
                                GeneratedMutableMap.map34081map
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
                                GeneratedMutableMap.map34080map,
                                GeneratedMutableMap.map34081map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map34077map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34078map,
                                        GeneratedMutableMap.map34079map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34080map,
                                GeneratedMutableMap.map34081map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34080map,
                                GeneratedMutableMap.map34081map
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
                                        GeneratedMutableMap.map34077map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34078map,
                                        GeneratedMutableMap.map34198map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34080map,
                                GeneratedMutableMap.map34199map
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
                                GeneratedMutableMap.map34080map,
                                GeneratedMutableMap.map34199map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map34077map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34078map,
                                        GeneratedMutableMap.map34198map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34080map,
                                GeneratedMutableMap.map34199map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34080map,
                                GeneratedMutableMap.map34199map
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
                                GeneratedMutableMap.map34077map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map34079map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map34081map
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
                                        GeneratedMutableMap.map34077map,
                                        GeneratedMutableMap.map34683map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map34682map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map34684map,
                                GeneratedMutableMap.map34685map
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
                                GeneratedMutableMap.map34684map,
                                GeneratedMutableMap.map34685map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map34077map,
                                        GeneratedMutableMap.map34683map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map34682map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map34684map,
                                GeneratedMutableMap.map34685map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map34684map,
                                GeneratedMutableMap.map34685map
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
                                        "#_35992",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map36122map,
                                        GeneratedMutableMap.map36123map,
                                        GeneratedMutableMap.map36124map,
                                        GeneratedMutableMap.map36125map,
                                        GeneratedMutableMap.map36126map,
                                        GeneratedMutableMap.map36127map,
                                        GeneratedMutableMap.map36128map,
                                        GeneratedMutableMap.map36129map,
                                        GeneratedMutableMap.map36130map,
                                        GeneratedMutableMap.map36131map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_35992"
                                ), listOf(
                                        GeneratedMutableMap.map36112map,
                                        GeneratedMutableMap.map36113map,
                                        GeneratedMutableMap.map36114map,
                                        GeneratedMutableMap.map36115map,
                                        GeneratedMutableMap.map36116map,
                                        GeneratedMutableMap.map36117map,
                                        GeneratedMutableMap.map36118map,
                                        GeneratedMutableMap.map36119map,
                                        GeneratedMutableMap.map36120map,
                                        GeneratedMutableMap.map36121map
                                )
                                ),
                                false),
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
                                GeneratedMutableMap.map36137map,
                                GeneratedMutableMap.map36138map,
                                GeneratedMutableMap.map36139map,
                                GeneratedMutableMap.map36140map,
                                GeneratedMutableMap.map36141map
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
                                false),
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
                                GeneratedMutableMap.map36137map,
                                GeneratedMutableMap.map36138map,
                                GeneratedMutableMap.map36139map,
                                GeneratedMutableMap.map36140map,
                                GeneratedMutableMap.map36141map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "#_35992",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map36122map,
                                        GeneratedMutableMap.map36123map,
                                        GeneratedMutableMap.map36124map,
                                        GeneratedMutableMap.map36125map,
                                        GeneratedMutableMap.map36126map,
                                        GeneratedMutableMap.map36127map,
                                        GeneratedMutableMap.map36128map,
                                        GeneratedMutableMap.map36129map,
                                        GeneratedMutableMap.map36130map,
                                        GeneratedMutableMap.map36131map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_35992"
                                ), listOf(
                                        GeneratedMutableMap.map36112map,
                                        GeneratedMutableMap.map36113map,
                                        GeneratedMutableMap.map36114map,
                                        GeneratedMutableMap.map36115map,
                                        GeneratedMutableMap.map36116map,
                                        GeneratedMutableMap.map36117map,
                                        GeneratedMutableMap.map36118map,
                                        GeneratedMutableMap.map36119map,
                                        GeneratedMutableMap.map36120map,
                                        GeneratedMutableMap.map36121map
                                )
                                ),
                                false),
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
                                GeneratedMutableMap.map36137map,
                                GeneratedMutableMap.map36138map,
                                GeneratedMutableMap.map36139map,
                                GeneratedMutableMap.map36140map,
                                GeneratedMutableMap.map36141map
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
                                false),
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
                                GeneratedMutableMap.map36137map,
                                GeneratedMutableMap.map36138map,
                                GeneratedMutableMap.map36139map,
                                GeneratedMutableMap.map36140map,
                                GeneratedMutableMap.map36141map
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
                                        "#_35992",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36132map,
                                        GeneratedMutableMap.map36133map,
                                        GeneratedMutableMap.map36134map,
                                        GeneratedMutableMap.map36135map,
                                        GeneratedMutableMap.map36136map,
                                        GeneratedMutableMap.map36137map,
                                        GeneratedMutableMap.map36138map,
                                        GeneratedMutableMap.map36139map,
                                        GeneratedMutableMap.map36140map,
                                        GeneratedMutableMap.map36141map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36108map,
                                        GeneratedMutableMap.map36109map
                                )
                                ),
                                false),
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
                                false),
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
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
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
                                        GeneratedMutableMap.map36137map,
                                        GeneratedMutableMap.map36138map,
                                        GeneratedMutableMap.map36139map,
                                        GeneratedMutableMap.map36140map,
                                        GeneratedMutableMap.map36141map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36108map,
                                        GeneratedMutableMap.map36109map
                                )
                                ),
                                false),
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
                                false),
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
                                        GeneratedMutableMap.map36329map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map36330map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36331map
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
                                GeneratedMutableMap.map36331map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "P",
                                        "F"
                                ), listOf(
                                        GeneratedMutableMap.map36329map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map36330map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36331map
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
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36331map
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
                                        "#_36360",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map36486map,
                                        GeneratedMutableMap.map36487map,
                                        GeneratedMutableMap.map36488map,
                                        GeneratedMutableMap.map36489map,
                                        GeneratedMutableMap.map36490map,
                                        GeneratedMutableMap.map36491map,
                                        GeneratedMutableMap.map36492map,
                                        GeneratedMutableMap.map36493map,
                                        GeneratedMutableMap.map36494map,
                                        GeneratedMutableMap.map36495map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_36360"
                                ), listOf(
                                        GeneratedMutableMap.map36476map,
                                        GeneratedMutableMap.map36477map,
                                        GeneratedMutableMap.map36478map,
                                        GeneratedMutableMap.map36479map,
                                        GeneratedMutableMap.map36480map,
                                        GeneratedMutableMap.map36481map,
                                        GeneratedMutableMap.map36482map,
                                        GeneratedMutableMap.map36483map,
                                        GeneratedMutableMap.map36484map,
                                        GeneratedMutableMap.map36485map
                                )
                                ),
                                false),
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
                                GeneratedMutableMap.map36501map,
                                GeneratedMutableMap.map36502map,
                                GeneratedMutableMap.map36503map,
                                GeneratedMutableMap.map36504map,
                                GeneratedMutableMap.map36505map
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
                                false),
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
                                GeneratedMutableMap.map36501map,
                                GeneratedMutableMap.map36502map,
                                GeneratedMutableMap.map36503map,
                                GeneratedMutableMap.map36504map,
                                GeneratedMutableMap.map36505map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
                                POPValues(dictionary, listOf(
                                        "#_36360",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map36486map,
                                        GeneratedMutableMap.map36487map,
                                        GeneratedMutableMap.map36488map,
                                        GeneratedMutableMap.map36489map,
                                        GeneratedMutableMap.map36490map,
                                        GeneratedMutableMap.map36491map,
                                        GeneratedMutableMap.map36492map,
                                        GeneratedMutableMap.map36493map,
                                        GeneratedMutableMap.map36494map,
                                        GeneratedMutableMap.map36495map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_36360"
                                ), listOf(
                                        GeneratedMutableMap.map36476map,
                                        GeneratedMutableMap.map36477map,
                                        GeneratedMutableMap.map36478map,
                                        GeneratedMutableMap.map36479map,
                                        GeneratedMutableMap.map36480map,
                                        GeneratedMutableMap.map36481map,
                                        GeneratedMutableMap.map36482map,
                                        GeneratedMutableMap.map36483map,
                                        GeneratedMutableMap.map36484map,
                                        GeneratedMutableMap.map36485map
                                )
                                ),
                                false),
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
                                GeneratedMutableMap.map36501map,
                                GeneratedMutableMap.map36502map,
                                GeneratedMutableMap.map36503map,
                                GeneratedMutableMap.map36504map,
                                GeneratedMutableMap.map36505map
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
                                false),
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
                                GeneratedMutableMap.map36501map,
                                GeneratedMutableMap.map36502map,
                                GeneratedMutableMap.map36503map,
                                GeneratedMutableMap.map36504map,
                                GeneratedMutableMap.map36505map
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
                                        "#_36360",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36496map,
                                        GeneratedMutableMap.map36497map,
                                        GeneratedMutableMap.map36498map,
                                        GeneratedMutableMap.map36499map,
                                        GeneratedMutableMap.map36500map,
                                        GeneratedMutableMap.map36501map,
                                        GeneratedMutableMap.map36502map,
                                        GeneratedMutableMap.map36503map,
                                        GeneratedMutableMap.map36504map,
                                        GeneratedMutableMap.map36505map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36108map,
                                        GeneratedMutableMap.map36109map
                                )
                                ),
                                false),
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
                                false),
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
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPJoin(
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
                                        GeneratedMutableMap.map36501map,
                                        GeneratedMutableMap.map36502map,
                                        GeneratedMutableMap.map36503map,
                                        GeneratedMutableMap.map36504map,
                                        GeneratedMutableMap.map36505map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36108map,
                                        GeneratedMutableMap.map36109map
                                )
                                ),
                                false),
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
                                false),
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
