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
                                        GeneratedMutableMap.map2749map,
                                        GeneratedMutableMap.map2750map,
                                        GeneratedMutableMap.map2751map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "S",
                                        "O2"
                                ), listOf(
                                        GeneratedMutableMap.map2755map,
                                        GeneratedMutableMap.map2756map,
                                        GeneratedMutableMap.map2757map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2"
                        ), listOf(
                                GeneratedMutableMap.map2758map,
                                GeneratedMutableMap.map2759map,
                                GeneratedMutableMap.map2760map,
                                GeneratedMutableMap.map2761map,
                                GeneratedMutableMap.map2762map,
                                GeneratedMutableMap.map2763map,
                                GeneratedMutableMap.map2764map,
                                GeneratedMutableMap.map2765map,
                                GeneratedMutableMap.map2766map
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
                                GeneratedMutableMap.map2758map,
                                GeneratedMutableMap.map2759map,
                                GeneratedMutableMap.map2760map,
                                GeneratedMutableMap.map2761map,
                                GeneratedMutableMap.map2762map,
                                GeneratedMutableMap.map2763map,
                                GeneratedMutableMap.map2764map,
                                GeneratedMutableMap.map2765map,
                                GeneratedMutableMap.map2766map
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
                                        GeneratedMutableMap.map2752map,
                                        GeneratedMutableMap.map2753map,
                                        GeneratedMutableMap.map2754map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("S"),
                                        AOPVariable("O2")
                                ), listOf(
                                        GeneratedMutableMap.map2752map,
                                        GeneratedMutableMap.map2753map,
                                        GeneratedMutableMap.map2754map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O1"),
                                AOPVariable("O2")
                        ), listOf(
                                GeneratedMutableMap.map2767map,
                                GeneratedMutableMap.map2768map,
                                GeneratedMutableMap.map2769map,
                                GeneratedMutableMap.map2770map,
                                GeneratedMutableMap.map2771map,
                                GeneratedMutableMap.map2772map,
                                GeneratedMutableMap.map2773map,
                                GeneratedMutableMap.map2774map,
                                GeneratedMutableMap.map2775map
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
                                GeneratedMutableMap.map2767map,
                                GeneratedMutableMap.map2768map,
                                GeneratedMutableMap.map2769map,
                                GeneratedMutableMap.map2770map,
                                GeneratedMutableMap.map2771map,
                                GeneratedMutableMap.map2772map,
                                GeneratedMutableMap.map2773map,
                                GeneratedMutableMap.map2774map,
                                GeneratedMutableMap.map2775map
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
                                GeneratedMutableMap.map5895map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5896map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map5896map
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
                                GeneratedMutableMap.map5896map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5896map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map5896map
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
                                GeneratedMutableMap.map5896map
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
                                GeneratedMutableMap.map5895map
                            )
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5895map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5895map
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
                                GeneratedMutableMap.map5895map
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
                                GeneratedMutableMap.map5895map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6328map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6328map
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
                                GeneratedMutableMap.map6328map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6328map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6328map
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
                                GeneratedMutableMap.map6328map
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
                                GeneratedMutableMap.map6716map,
                                GeneratedMutableMap.map6717map,
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7191map,
                                GeneratedMutableMap.map7192map,
                                GeneratedMutableMap.map7193map,
                                GeneratedMutableMap.map7194map
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
                            GeneratedMutableMap.map7195map,
                            GeneratedMutableMap.map7196map,
                            GeneratedMutableMap.map7197map,
                            GeneratedMutableMap.map7198map,
                            GeneratedMutableMap.map7199map,
                            GeneratedMutableMap.map7200map,
                            GeneratedMutableMap.map7201map,
                            GeneratedMutableMap.map7202map,
                            GeneratedMutableMap.map7203map,
                            GeneratedMutableMap.map7204map,
                            GeneratedMutableMap.map7205map,
                            GeneratedMutableMap.map7206map,
                            GeneratedMutableMap.map7207map,
                            GeneratedMutableMap.map7208map,
                            GeneratedMutableMap.map7209map,
                            GeneratedMutableMap.map7210map
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
                                        GeneratedMutableMap.map6716map,
                                        GeneratedMutableMap.map6717map,
                                        GeneratedMutableMap.map6718map,
                                        GeneratedMutableMap.map6719map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map7719map,
                                        GeneratedMutableMap.map7719map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7289map,
                                GeneratedMutableMap.map7290map,
                                GeneratedMutableMap.map7291map,
                                GeneratedMutableMap.map7292map,
                                GeneratedMutableMap.map7289map,
                                GeneratedMutableMap.map7290map,
                                GeneratedMutableMap.map7291map,
                                GeneratedMutableMap.map7292map
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
                                        GeneratedMutableMap.map8501map,
                                        GeneratedMutableMap.map8502map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8497map,
                                        GeneratedMutableMap.map8498map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8505map,
                                GeneratedMutableMap.map8506map
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
                                GeneratedMutableMap.map8505map,
                                GeneratedMutableMap.map8506map
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
                                        GeneratedMutableMap.map8503map,
                                        GeneratedMutableMap.map8504map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("book"),
                                        AOPVariable("price")
                                ), listOf(
                                        GeneratedMutableMap.map8499map,
                                        GeneratedMutableMap.map8500map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("title"),
                                AOPVariable("price")
                        ), listOf(
                                GeneratedMutableMap.map8507map,
                                GeneratedMutableMap.map8508map
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
                                GeneratedMutableMap.map8507map,
                                GeneratedMutableMap.map8508map
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
                                        GeneratedMutableMap.map8509map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8505map,
                                        GeneratedMutableMap.map8506map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8505map
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
                                        GeneratedMutableMap.map8564map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8565map,
                                        GeneratedMutableMap.map8566map,
                                        GeneratedMutableMap.map8567map,
                                        GeneratedMutableMap.map8568map,
                                        GeneratedMutableMap.map8569map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8570map
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
                                        GeneratedMutableMap.map8661map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8662map,
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map8665map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map,
                                        GeneratedMutableMap.map8668map,
                                        GeneratedMutableMap.map8669map,
                                        GeneratedMutableMap.map8670map,
                                        GeneratedMutableMap.map8671map,
                                        GeneratedMutableMap.map8672map,
                                        GeneratedMutableMap.map8673map,
                                        GeneratedMutableMap.map8674map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8675map
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
                                        GeneratedMutableMap.map8676map,
                                        GeneratedMutableMap.map8677map,
                                        GeneratedMutableMap.map8678map,
                                        GeneratedMutableMap.map8679map,
                                        GeneratedMutableMap.map8680map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8681map,
                                        GeneratedMutableMap.map8682map,
                                        GeneratedMutableMap.map8683map,
                                        GeneratedMutableMap.map8684map,
                                        GeneratedMutableMap.map8685map
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
                                GeneratedMutableMap.map8686map,
                                GeneratedMutableMap.map8687map,
                                GeneratedMutableMap.map8688map,
                                GeneratedMutableMap.map8689map,
                                GeneratedMutableMap.map8690map,
                                GeneratedMutableMap.map8691map,
                                GeneratedMutableMap.map8692map,
                                GeneratedMutableMap.map8693map,
                                GeneratedMutableMap.map8694map,
                                GeneratedMutableMap.map8695map,
                                GeneratedMutableMap.map8696map,
                                GeneratedMutableMap.map8697map,
                                GeneratedMutableMap.map8698map
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
                                GeneratedMutableMap.map8686map,
                                GeneratedMutableMap.map8687map,
                                GeneratedMutableMap.map8688map,
                                GeneratedMutableMap.map8689map,
                                GeneratedMutableMap.map8690map,
                                GeneratedMutableMap.map8691map,
                                GeneratedMutableMap.map8692map,
                                GeneratedMutableMap.map8693map,
                                GeneratedMutableMap.map8694map,
                                GeneratedMutableMap.map8695map,
                                GeneratedMutableMap.map8696map,
                                GeneratedMutableMap.map8697map,
                                GeneratedMutableMap.map8698map
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
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map8577map,
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map8577map,
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map
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
                                GeneratedMutableMap.map8699map,
                                GeneratedMutableMap.map8700map,
                                GeneratedMutableMap.map8701map,
                                GeneratedMutableMap.map8702map,
                                GeneratedMutableMap.map8703map,
                                GeneratedMutableMap.map8704map,
                                GeneratedMutableMap.map8705map,
                                GeneratedMutableMap.map8706map,
                                GeneratedMutableMap.map8707map,
                                GeneratedMutableMap.map8708map,
                                GeneratedMutableMap.map8709map,
                                GeneratedMutableMap.map8710map,
                                GeneratedMutableMap.map8711map
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
                                GeneratedMutableMap.map8699map,
                                GeneratedMutableMap.map8700map,
                                GeneratedMutableMap.map8701map,
                                GeneratedMutableMap.map8702map,
                                GeneratedMutableMap.map8703map,
                                GeneratedMutableMap.map8704map,
                                GeneratedMutableMap.map8705map,
                                GeneratedMutableMap.map8706map,
                                GeneratedMutableMap.map8707map,
                                GeneratedMutableMap.map8708map,
                                GeneratedMutableMap.map8709map,
                                GeneratedMutableMap.map8710map,
                                GeneratedMutableMap.map8711map
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
                                        GeneratedMutableMap.map8800map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8662map,
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map8665map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map,
                                        GeneratedMutableMap.map8668map,
                                        GeneratedMutableMap.map8669map,
                                        GeneratedMutableMap.map8670map,
                                        GeneratedMutableMap.map8671map,
                                        GeneratedMutableMap.map8672map,
                                        GeneratedMutableMap.map8673map,
                                        GeneratedMutableMap.map8674map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8801map,
                                GeneratedMutableMap.map8675map,
                                GeneratedMutableMap.map8802map
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
                                        GeneratedMutableMap.map8887map,
                                        GeneratedMutableMap.map8888map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8662map,
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map8665map,
                                        GeneratedMutableMap.map8666map,
                                        GeneratedMutableMap.map8667map,
                                        GeneratedMutableMap.map8668map,
                                        GeneratedMutableMap.map8669map,
                                        GeneratedMutableMap.map8670map,
                                        GeneratedMutableMap.map8671map,
                                        GeneratedMutableMap.map8672map,
                                        GeneratedMutableMap.map8673map,
                                        GeneratedMutableMap.map8674map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map8889map,
                                GeneratedMutableMap.map8890map,
                                GeneratedMutableMap.map8891map,
                                GeneratedMutableMap.map8891map,
                                GeneratedMutableMap.map8892map,
                                GeneratedMutableMap.map8802map
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
                                        GeneratedMutableMap.map8952map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p1",
                                        "o1"
                                ), listOf(
                                        GeneratedMutableMap.map8676map,
                                        GeneratedMutableMap.map8677map,
                                        GeneratedMutableMap.map8678map,
                                        GeneratedMutableMap.map8679map,
                                        GeneratedMutableMap.map8680map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p1",
                                "s",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map8953map
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
                                        GeneratedMutableMap.map9034map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o1",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map8662map,
                                        GeneratedMutableMap.map8663map,
                                        GeneratedMutableMap.map8664map,
                                        GeneratedMutableMap.map9035map,
                                        GeneratedMutableMap.map9036map,
                                        GeneratedMutableMap.map9037map,
                                        GeneratedMutableMap.map9038map,
                                        GeneratedMutableMap.map9039map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "o2",
                                "s",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map9040map,
                                GeneratedMutableMap.map9041map,
                                GeneratedMutableMap.map9042map,
                                GeneratedMutableMap.map9043map,
                                GeneratedMutableMap.map9044map
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
                                        GeneratedMutableMap.map8676map,
                                        GeneratedMutableMap.map8677map,
                                        GeneratedMutableMap.map8678map,
                                        GeneratedMutableMap.map9048map,
                                        GeneratedMutableMap.map8679map,
                                        GeneratedMutableMap.map8680map,
                                        GeneratedMutableMap.map9049map,
                                        GeneratedMutableMap.map9050map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map9045map,
                                        GeneratedMutableMap.map9046map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map9054map,
                                GeneratedMutableMap.map9055map,
                                GeneratedMutableMap.map9056map,
                                GeneratedMutableMap.map9057map,
                                GeneratedMutableMap.map9058map,
                                GeneratedMutableMap.map9059map,
                                GeneratedMutableMap.map9060map,
                                GeneratedMutableMap.map9061map
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
                                GeneratedMutableMap.map9054map,
                                GeneratedMutableMap.map9055map,
                                GeneratedMutableMap.map9056map,
                                GeneratedMutableMap.map9057map,
                                GeneratedMutableMap.map9058map,
                                GeneratedMutableMap.map9059map,
                                GeneratedMutableMap.map9060map,
                                GeneratedMutableMap.map9061map
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
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map8577map,
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map9051map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map,
                                        GeneratedMutableMap.map9052map,
                                        GeneratedMutableMap.map9053map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map8581map,
                                        GeneratedMutableMap.map9047map
                                )
                                ),
                                true),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p1"),
                                AOPVariable("o1"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map9062map,
                                GeneratedMutableMap.map9063map,
                                GeneratedMutableMap.map9064map,
                                GeneratedMutableMap.map9065map,
                                GeneratedMutableMap.map9066map,
                                GeneratedMutableMap.map9067map,
                                GeneratedMutableMap.map9068map,
                                GeneratedMutableMap.map9069map
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
                                GeneratedMutableMap.map9062map,
                                GeneratedMutableMap.map9063map,
                                GeneratedMutableMap.map9064map,
                                GeneratedMutableMap.map9065map,
                                GeneratedMutableMap.map9066map,
                                GeneratedMutableMap.map9067map,
                                GeneratedMutableMap.map9068map,
                                GeneratedMutableMap.map9069map
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
                                        GeneratedMutableMap.map9177map,
                                        GeneratedMutableMap.map9178map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8505map,
                                        GeneratedMutableMap.map8506map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8505map,
                                GeneratedMutableMap.map8506map
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
                                        GeneratedMutableMap.map8509map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title"
                                ), listOf(
                                        GeneratedMutableMap.map8501map,
                                        GeneratedMutableMap.map8502map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                        ), listOf(
                                GeneratedMutableMap.map8501map
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
                                        GeneratedMutableMap.map8501map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8497map,
                                        GeneratedMutableMap.map8498map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8505map
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
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10286map,
                                GeneratedMutableMap.map10287map,
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10468map,
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map
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
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10477map,
                            GeneratedMutableMap.map10478map,
                            GeneratedMutableMap.map10479map
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
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10286map,
                                        GeneratedMutableMap.map10287map,
                                        GeneratedMutableMap.map10288map,
                                        GeneratedMutableMap.map10289map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10468map,
                                        GeneratedMutableMap.map10469map,
                                        GeneratedMutableMap.map10470map,
                                        GeneratedMutableMap.map10471map,
                                        GeneratedMutableMap.map10472map,
                                        GeneratedMutableMap.map10473map
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
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map,
                                GeneratedMutableMap.map10477map,
                                GeneratedMutableMap.map10478map,
                                GeneratedMutableMap.map10479map
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
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map,
                                GeneratedMutableMap.map10477map,
                                GeneratedMutableMap.map10478map,
                                GeneratedMutableMap.map10479map
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
                                        GeneratedMutableMap.map10366map,
                                        GeneratedMutableMap.map10367map,
                                        GeneratedMutableMap.map10368map,
                                        GeneratedMutableMap.map10369map,
                                        GeneratedMutableMap.map10370map,
                                        GeneratedMutableMap.map10371map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10366map,
                                        GeneratedMutableMap.map10367map,
                                        GeneratedMutableMap.map10368map,
                                        GeneratedMutableMap.map10369map,
                                        GeneratedMutableMap.map10370map,
                                        GeneratedMutableMap.map10371map
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
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10606map,
                                GeneratedMutableMap.map10607map,
                                GeneratedMutableMap.map10608map,
                                GeneratedMutableMap.map10609map
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
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10606map,
                                GeneratedMutableMap.map10607map,
                                GeneratedMutableMap.map10608map,
                                GeneratedMutableMap.map10609map
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
                                GeneratedMutableMap.map5895map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11360map,
                                GeneratedMutableMap.map11360map,
                                GeneratedMutableMap.map11360map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map
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
                                GeneratedMutableMap.map11360map,
                                GeneratedMutableMap.map11360map,
                                GeneratedMutableMap.map11360map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11360map,
                                GeneratedMutableMap.map11360map,
                                GeneratedMutableMap.map11360map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map,
                            GeneratedMutableMap.map11360map
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
                                        GeneratedMutableMap.map11520map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "b"
                                ), listOf(
                                        GeneratedMutableMap.map11248map,
                                        GeneratedMutableMap.map11249map,
                                        GeneratedMutableMap.map11250map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                        ), listOf(
                                GeneratedMutableMap.map11248map,
                                GeneratedMutableMap.map11249map
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
                                GeneratedMutableMap.map11248map,
                                GeneratedMutableMap.map11249map
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
                                        GeneratedMutableMap.map11521map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("a"),
                                        AOPVariable("b")
                                ), listOf(
                                        GeneratedMutableMap.map8581map,
                                        GeneratedMutableMap.map11251map,
                                        GeneratedMutableMap.map9047map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("b")
                        ), listOf(
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map11251map
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
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map11251map
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
                                        GeneratedMutableMap.map11520map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        GeneratedMutableMap.map11754map,
                                        GeneratedMutableMap.map11755map,
                                        GeneratedMutableMap.map11756map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11754map,
                                GeneratedMutableMap.map11755map
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
                                GeneratedMutableMap.map11754map,
                                GeneratedMutableMap.map11755map
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
                                        GeneratedMutableMap.map11521map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("a"),
                                        AOPVariable("Var_B")
                                ), listOf(
                                        GeneratedMutableMap.map8581map,
                                        GeneratedMutableMap.map11251map,
                                        GeneratedMutableMap.map9047map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                        ), listOf(
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map11251map
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
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map11251map
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
                                        GeneratedMutableMap.map11520map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "Var_B"
                                ), listOf(
                                        GeneratedMutableMap.map11756map
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
                                        GeneratedMutableMap.map11521map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("a"),
                                        AOPVariable("Var_B")
                                ), listOf(
                                        GeneratedMutableMap.map9047map
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
                                        GeneratedMutableMap.map12904map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8571map,
                                        GeneratedMutableMap.map8572map,
                                        GeneratedMutableMap.map8573map,
                                        GeneratedMutableMap.map8574map,
                                        GeneratedMutableMap.map8575map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8574map,
                                GeneratedMutableMap.map8575map
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
                                GeneratedMutableMap.map8574map,
                                GeneratedMutableMap.map8575map
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
                                        GeneratedMutableMap.map12905map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map8577map,
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8579map,
                                GeneratedMutableMap.map8580map
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
                                GeneratedMutableMap.map8579map,
                                GeneratedMutableMap.map8580map
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
                                        GeneratedMutableMap.map8571map,
                                        GeneratedMutableMap.map8572map,
                                        GeneratedMutableMap.map8573map,
                                        GeneratedMutableMap.map8574map,
                                        GeneratedMutableMap.map8575map
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
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map8577map,
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map
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
                                        GeneratedMutableMap.map13305map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8571map,
                                        GeneratedMutableMap.map11000map,
                                        GeneratedMutableMap.map8574map,
                                        GeneratedMutableMap.map8575map,
                                        GeneratedMutableMap.map11001map,
                                        GeneratedMutableMap.map11002map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11001map,
                                GeneratedMutableMap.map11002map
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
                                GeneratedMutableMap.map11001map,
                                GeneratedMutableMap.map11002map
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
                                        GeneratedMutableMap.map13306map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map9051map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map,
                                        GeneratedMutableMap.map11003map,
                                        GeneratedMutableMap.map11004map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map11003map,
                                GeneratedMutableMap.map11004map
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
                                GeneratedMutableMap.map11003map,
                                GeneratedMutableMap.map11004map
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
                                        GeneratedMutableMap.map12904map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8571map,
                                        GeneratedMutableMap.map11000map,
                                        GeneratedMutableMap.map8574map,
                                        GeneratedMutableMap.map8575map,
                                        GeneratedMutableMap.map11001map,
                                        GeneratedMutableMap.map11002map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11000map,
                                GeneratedMutableMap.map8574map,
                                GeneratedMutableMap.map8575map
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
                                GeneratedMutableMap.map11000map,
                                GeneratedMutableMap.map8574map,
                                GeneratedMutableMap.map8575map
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
                                        GeneratedMutableMap.map12905map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map9051map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map,
                                        GeneratedMutableMap.map11003map,
                                        GeneratedMutableMap.map11004map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9051map,
                                GeneratedMutableMap.map8579map,
                                GeneratedMutableMap.map8580map
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
                                GeneratedMutableMap.map9051map,
                                GeneratedMutableMap.map8579map,
                                GeneratedMutableMap.map8580map
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
                                        GeneratedMutableMap.map11005map,
                                        GeneratedMutableMap.map11001map,
                                        GeneratedMutableMap.map11002map,
                                        GeneratedMutableMap.map11006map,
                                        GeneratedMutableMap.map11007map
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
                                        GeneratedMutableMap.map11008map,
                                        GeneratedMutableMap.map11003map,
                                        GeneratedMutableMap.map11004map,
                                        GeneratedMutableMap.map11009map,
                                        GeneratedMutableMap.map11010map
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
                                        GeneratedMutableMap.map8571map,
                                        GeneratedMutableMap.map11000map,
                                        GeneratedMutableMap.map8574map,
                                        GeneratedMutableMap.map8575map,
                                        GeneratedMutableMap.map11001map,
                                        GeneratedMutableMap.map11002map
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
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map9051map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map,
                                        GeneratedMutableMap.map11003map,
                                        GeneratedMutableMap.map11004map
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
                                GeneratedMutableMap.map13305map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map11005map,
                                GeneratedMutableMap.map11001map,
                                GeneratedMutableMap.map11002map,
                                GeneratedMutableMap.map11006map,
                                GeneratedMutableMap.map11007map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11005map,
                            GeneratedMutableMap.map11001map,
                            GeneratedMutableMap.map11002map
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
                                        GeneratedMutableMap.map14056map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map8571map,
                                        GeneratedMutableMap.map8572map,
                                        GeneratedMutableMap.map8573map,
                                        GeneratedMutableMap.map8574map,
                                        GeneratedMutableMap.map8575map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map8573map
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
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map8573map
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
                                        GeneratedMutableMap.map11521map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map8577map,
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8576map,
                                GeneratedMutableMap.map8577map,
                                GeneratedMutableMap.map8578map
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
                                GeneratedMutableMap.map8576map,
                                GeneratedMutableMap.map8577map,
                                GeneratedMutableMap.map8578map
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
                                GeneratedMutableMap.map15889map,
                                GeneratedMutableMap.map15890map,
                                GeneratedMutableMap.map15891map,
                                GeneratedMutableMap.map15892map,
                                GeneratedMutableMap.map15893map,
                                GeneratedMutableMap.map15894map,
                                GeneratedMutableMap.map15895map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map15888map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map15892map
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
                                GeneratedMutableMap.map16157map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16157map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map16157map
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
                                        GeneratedMutableMap.map16351map,
                                        GeneratedMutableMap.map16352map,
                                        GeneratedMutableMap.map16353map,
                                        GeneratedMutableMap.map16354map,
                                        GeneratedMutableMap.map15889map,
                                        GeneratedMutableMap.map15890map,
                                        GeneratedMutableMap.map15891map,
                                        GeneratedMutableMap.map16355map,
                                        GeneratedMutableMap.map16356map
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
                                        GeneratedMutableMap.map16357map,
                                        GeneratedMutableMap.map16358map,
                                        GeneratedMutableMap.map16359map,
                                        GeneratedMutableMap.map16360map,
                                        GeneratedMutableMap.map16361map,
                                        GeneratedMutableMap.map16362map,
                                        GeneratedMutableMap.map16363map,
                                        GeneratedMutableMap.map16364map,
                                        GeneratedMutableMap.map16365map
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
                                        GeneratedMutableMap.map16429map,
                                        GeneratedMutableMap.map16430map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map16425map,
                                        GeneratedMutableMap.map16426map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16429map,
                                GeneratedMutableMap.map16430map
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
                                GeneratedMutableMap.map16429map,
                                GeneratedMutableMap.map16430map
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
                                        GeneratedMutableMap.map16431map,
                                        GeneratedMutableMap.map16432map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map16427map,
                                        GeneratedMutableMap.map16428map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map16431map,
                                GeneratedMutableMap.map16432map
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
                                GeneratedMutableMap.map16431map,
                                GeneratedMutableMap.map16432map
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
                                GeneratedMutableMap.map16643map,
                                GeneratedMutableMap.map16644map,
                                GeneratedMutableMap.map16645map,
                                GeneratedMutableMap.map16646map,
                                GeneratedMutableMap.map16647map,
                                GeneratedMutableMap.map16648map,
                                GeneratedMutableMap.map16649map,
                                GeneratedMutableMap.map16650map,
                                GeneratedMutableMap.map16651map,
                                GeneratedMutableMap.map16652map,
                                GeneratedMutableMap.map16653map,
                                GeneratedMutableMap.map16654map,
                                GeneratedMutableMap.map16655map,
                                GeneratedMutableMap.map16656map,
                                GeneratedMutableMap.map16657map,
                                GeneratedMutableMap.map16658map,
                                GeneratedMutableMap.map16659map,
                                GeneratedMutableMap.map16660map,
                                GeneratedMutableMap.map16661map,
                                GeneratedMutableMap.map16662map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16537"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16537"
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
                                "#_16537",
                                "#_16542"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16642map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16537",
                            "#_16542",
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
                                GeneratedMutableMap.map16793map,
                                GeneratedMutableMap.map16794map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16683"
                            ), listOf(
                                GeneratedMutableMap.map16791map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16683"
                        ), listOf(
                            GeneratedMutableMap.map16795map,
                            GeneratedMutableMap.map16796map
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
                                "#_16683"
                            ), listOf(
                                GeneratedMutableMap.map16795map,
                                GeneratedMutableMap.map16796map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16683"
                            ), listOf(
                                GeneratedMutableMap.map16791map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16683"
                        ), listOf(
                            GeneratedMutableMap.map16795map,
                            GeneratedMutableMap.map16796map
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
                                "#_16683"
                            ), listOf(
                                GeneratedMutableMap.map16795map,
                                GeneratedMutableMap.map16796map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16692"
                            ), listOf(
                                GeneratedMutableMap.map16817map,
                                GeneratedMutableMap.map16818map,
                                GeneratedMutableMap.map16819map,
                                GeneratedMutableMap.map16820map,
                                GeneratedMutableMap.map16821map,
                                GeneratedMutableMap.map16822map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16683",
                            "#_16692"
                        ), listOf(
                            GeneratedMutableMap.map16823map,
                            GeneratedMutableMap.map16824map,
                            GeneratedMutableMap.map16825map,
                            GeneratedMutableMap.map16826map,
                            GeneratedMutableMap.map16827map,
                            GeneratedMutableMap.map16828map,
                            GeneratedMutableMap.map16829map,
                            GeneratedMutableMap.map16830map,
                            GeneratedMutableMap.map16831map,
                            GeneratedMutableMap.map16832map,
                            GeneratedMutableMap.map16833map,
                            GeneratedMutableMap.map16834map
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
                                "#_16683",
                                "#_16692"
                            ), listOf(
                                GeneratedMutableMap.map16823map,
                                GeneratedMutableMap.map16824map,
                                GeneratedMutableMap.map16825map,
                                GeneratedMutableMap.map16826map,
                                GeneratedMutableMap.map16827map,
                                GeneratedMutableMap.map16828map,
                                GeneratedMutableMap.map16829map,
                                GeneratedMutableMap.map16830map,
                                GeneratedMutableMap.map16831map,
                                GeneratedMutableMap.map16832map,
                                GeneratedMutableMap.map16833map,
                                GeneratedMutableMap.map16834map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16692"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16683",
                            "#_16692"
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
                                "#_16683",
                                "#_16692"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16683",
                                "#_16692"
                            ), listOf(
                                GeneratedMutableMap.map16792map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16683",
                            "#_16692"
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
                                "#_16683",
                                "#_16692"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#_16683"
                            ), listOf(
                                GeneratedMutableMap.map16797map,
                                GeneratedMutableMap.map16798map,
                                GeneratedMutableMap.map16799map,
                                GeneratedMutableMap.map16800map,
                                GeneratedMutableMap.map16801map,
                                GeneratedMutableMap.map16802map,
                                GeneratedMutableMap.map16803map,
                                GeneratedMutableMap.map16804map,
                                GeneratedMutableMap.map16805map,
                                GeneratedMutableMap.map16806map,
                                GeneratedMutableMap.map16807map,
                                GeneratedMutableMap.map16808map,
                                GeneratedMutableMap.map16809map,
                                GeneratedMutableMap.map16810map,
                                GeneratedMutableMap.map16811map,
                                GeneratedMutableMap.map16812map,
                                GeneratedMutableMap.map16813map,
                                GeneratedMutableMap.map16814map,
                                GeneratedMutableMap.map16815map,
                                GeneratedMutableMap.map16816map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16683",
                            "#_16692"
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
                                GeneratedMutableMap.map16909map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16910map,
                                GeneratedMutableMap.map16911map,
                                GeneratedMutableMap.map16912map,
                                GeneratedMutableMap.map16913map,
                                GeneratedMutableMap.map16914map,
                                GeneratedMutableMap.map16915map,
                                GeneratedMutableMap.map16916map,
                                GeneratedMutableMap.map16917map,
                                GeneratedMutableMap.map16918map,
                                GeneratedMutableMap.map16919map,
                                GeneratedMutableMap.map16920map,
                                GeneratedMutableMap.map16921map,
                                GeneratedMutableMap.map16922map,
                                GeneratedMutableMap.map16923map,
                                GeneratedMutableMap.map16924map,
                                GeneratedMutableMap.map16925map,
                                GeneratedMutableMap.map16926map,
                                GeneratedMutableMap.map16927map,
                                GeneratedMutableMap.map16928map,
                                GeneratedMutableMap.map16929map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16910map,
                            GeneratedMutableMap.map16911map
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
                                GeneratedMutableMap.map16910map,
                                GeneratedMutableMap.map16911map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16474map
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
                                        GeneratedMutableMap.map16993map,
                                        GeneratedMutableMap.map16994map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map16997map,
                                        GeneratedMutableMap.map16998map,
                                        GeneratedMutableMap.map16999map,
                                        GeneratedMutableMap.map17000map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map16997map
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
                                GeneratedMutableMap.map16997map
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
                                        GeneratedMutableMap.map16995map,
                                        GeneratedMutableMap.map16996map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("p"),
                                        AOPVariable("v")
                                ), listOf(
                                        GeneratedMutableMap.map17001map,
                                        GeneratedMutableMap.map17002map,
                                        GeneratedMutableMap.map17003map,
                                        GeneratedMutableMap.map17004map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("v")
                        ), listOf(
                                GeneratedMutableMap.map17001map
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
                                GeneratedMutableMap.map17001map
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
                                        GeneratedMutableMap.map17152map,
                                        GeneratedMutableMap.map6716map,
                                        GeneratedMutableMap.map6717map,
                                        GeneratedMutableMap.map6718map,
                                        GeneratedMutableMap.map6719map,
                                        GeneratedMutableMap.map17153map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "p"
                                ), listOf(
                                        GeneratedMutableMap.map17150map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map6716map,
                                GeneratedMutableMap.map6717map,
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map
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
                                GeneratedMutableMap.map6716map,
                                GeneratedMutableMap.map6717map,
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map
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
                                        GeneratedMutableMap.map17154map,
                                        GeneratedMutableMap.map6720map,
                                        GeneratedMutableMap.map6721map,
                                        GeneratedMutableMap.map6722map,
                                        GeneratedMutableMap.map6723map,
                                        GeneratedMutableMap.map17155map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("p")
                                ), listOf(
                                        GeneratedMutableMap.map17151map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map6720map,
                                GeneratedMutableMap.map6721map,
                                GeneratedMutableMap.map6722map,
                                GeneratedMutableMap.map6723map
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
                                GeneratedMutableMap.map6720map,
                                GeneratedMutableMap.map6721map,
                                GeneratedMutableMap.map6722map,
                                GeneratedMutableMap.map6723map
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
                                GeneratedMutableMap.map17152map,
                                GeneratedMutableMap.map6716map,
                                GeneratedMutableMap.map6717map,
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map,
                                GeneratedMutableMap.map17153map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map17150map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map6716map,
                            GeneratedMutableMap.map6717map,
                            GeneratedMutableMap.map6718map,
                            GeneratedMutableMap.map6719map
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
                                GeneratedMutableMap.map6716map,
                                GeneratedMutableMap.map6717map,
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map17643map,
                                GeneratedMutableMap.map7191map,
                                GeneratedMutableMap.map7192map,
                                GeneratedMutableMap.map7193map,
                                GeneratedMutableMap.map7194map,
                                GeneratedMutableMap.map17644map
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
                            GeneratedMutableMap.map17645map,
                            GeneratedMutableMap.map17646map,
                            GeneratedMutableMap.map17647map,
                            GeneratedMutableMap.map17648map,
                            GeneratedMutableMap.map7195map,
                            GeneratedMutableMap.map7196map,
                            GeneratedMutableMap.map7197map,
                            GeneratedMutableMap.map7198map,
                            GeneratedMutableMap.map7199map,
                            GeneratedMutableMap.map7200map,
                            GeneratedMutableMap.map7201map,
                            GeneratedMutableMap.map7202map,
                            GeneratedMutableMap.map7203map,
                            GeneratedMutableMap.map7204map,
                            GeneratedMutableMap.map7205map,
                            GeneratedMutableMap.map7206map,
                            GeneratedMutableMap.map7207map,
                            GeneratedMutableMap.map7208map,
                            GeneratedMutableMap.map7209map,
                            GeneratedMutableMap.map7210map,
                            GeneratedMutableMap.map17649map,
                            GeneratedMutableMap.map17650map,
                            GeneratedMutableMap.map17651map,
                            GeneratedMutableMap.map17652map
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
                                GeneratedMutableMap.map17645map,
                                GeneratedMutableMap.map17646map,
                                GeneratedMutableMap.map17647map,
                                GeneratedMutableMap.map17648map,
                                GeneratedMutableMap.map7195map,
                                GeneratedMutableMap.map7196map,
                                GeneratedMutableMap.map7197map,
                                GeneratedMutableMap.map7198map,
                                GeneratedMutableMap.map7199map,
                                GeneratedMutableMap.map7200map,
                                GeneratedMutableMap.map7201map,
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map,
                                GeneratedMutableMap.map7206map,
                                GeneratedMutableMap.map7207map,
                                GeneratedMutableMap.map7208map,
                                GeneratedMutableMap.map7209map,
                                GeneratedMutableMap.map7210map,
                                GeneratedMutableMap.map17649map,
                                GeneratedMutableMap.map17650map,
                                GeneratedMutableMap.map17651map,
                                GeneratedMutableMap.map17652map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p1"
                            ), listOf(
                                GeneratedMutableMap.map17642map
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
                            GeneratedMutableMap.map7195map,
                            GeneratedMutableMap.map7196map,
                            GeneratedMutableMap.map7197map,
                            GeneratedMutableMap.map7198map,
                            GeneratedMutableMap.map7199map,
                            GeneratedMutableMap.map7200map,
                            GeneratedMutableMap.map7201map,
                            GeneratedMutableMap.map7202map,
                            GeneratedMutableMap.map7203map,
                            GeneratedMutableMap.map7204map,
                            GeneratedMutableMap.map7205map,
                            GeneratedMutableMap.map7206map,
                            GeneratedMutableMap.map7207map,
                            GeneratedMutableMap.map7208map,
                            GeneratedMutableMap.map7209map,
                            GeneratedMutableMap.map7210map
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
                                GeneratedMutableMap.map15889map,
                                GeneratedMutableMap.map15891map,
                                GeneratedMutableMap.map15892map,
                                GeneratedMutableMap.map16355map
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
                                GeneratedMutableMap.map16351map,
                                GeneratedMutableMap.map16353map,
                                GeneratedMutableMap.map15889map,
                                GeneratedMutableMap.map15890map,
                                GeneratedMutableMap.map15891map,
                                GeneratedMutableMap.map15892map,
                                GeneratedMutableMap.map18627map,
                                GeneratedMutableMap.map15893map,
                                GeneratedMutableMap.map18628map,
                                GeneratedMutableMap.map18629map,
                                GeneratedMutableMap.map16356map
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
                                GeneratedMutableMap.map18626map
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
                                        GeneratedMutableMap.map18702map,
                                        GeneratedMutableMap.map18703map,
                                        GeneratedMutableMap.map18704map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1"
                                ), listOf(
                                        GeneratedMutableMap.map18707map,
                                        GeneratedMutableMap.map18708map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                GeneratedMutableMap.map18707map,
                                GeneratedMutableMap.map18708map
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
                                GeneratedMutableMap.map18707map,
                                GeneratedMutableMap.map18708map
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
                                        GeneratedMutableMap.map18705map,
                                        GeneratedMutableMap.map17046map,
                                        GeneratedMutableMap.map18706map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y1")
                                ), listOf(
                                        GeneratedMutableMap.map18709map,
                                        GeneratedMutableMap.map18710map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1")
                        ), listOf(
                                GeneratedMutableMap.map18709map,
                                GeneratedMutableMap.map18710map
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
                                GeneratedMutableMap.map18709map,
                                GeneratedMutableMap.map18710map
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
                                        GeneratedMutableMap.map18707map,
                                        GeneratedMutableMap.map18708map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y2"
                                ), listOf(
                                        GeneratedMutableMap.map18711map,
                                        GeneratedMutableMap.map18712map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1",
                                "Y2"
                        ), listOf(
                                GeneratedMutableMap.map18715map,
                                GeneratedMutableMap.map18716map
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
                                GeneratedMutableMap.map18715map,
                                GeneratedMutableMap.map18716map
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
                                        GeneratedMutableMap.map18709map,
                                        GeneratedMutableMap.map18710map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y2")
                                ), listOf(
                                        GeneratedMutableMap.map18713map,
                                        GeneratedMutableMap.map18714map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1"),
                                AOPVariable("Y2")
                        ), listOf(
                                GeneratedMutableMap.map18717map,
                                GeneratedMutableMap.map18718map
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
                                GeneratedMutableMap.map18717map,
                                GeneratedMutableMap.map18718map
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
                                GeneratedMutableMap.map18848map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                GeneratedMutableMap.map18849map,
                                GeneratedMutableMap.map18850map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd"
                        ), listOf(
                            GeneratedMutableMap.map18849map
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
                                GeneratedMutableMap.map18849map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#bb"
                            ), listOf(
                                GeneratedMutableMap.map18851map
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
                                        GeneratedMutableMap.map18848map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18936map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18937map
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
                                GeneratedMutableMap.map18937map
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
                                        GeneratedMutableMap.map18935map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ), listOf(
                                        GeneratedMutableMap.map18938map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map18939map
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
                                GeneratedMutableMap.map18939map
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
                                        GeneratedMutableMap.map18937map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "Y",
                                        "#aa"
                                ), listOf(
                                        GeneratedMutableMap.map18940map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18937map
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
                                GeneratedMutableMap.map18937map
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
                                        GeneratedMutableMap.map18939map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("Y"),
                                        AOPVariable("#aa")
                                ), listOf(
                                        GeneratedMutableMap.map18941map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map18939map
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
                                GeneratedMutableMap.map18939map
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
                                        GeneratedMutableMap.map18937map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "Z"
                                ), listOf(
                                        GeneratedMutableMap.map18942map,
                                        GeneratedMutableMap.map18943map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18946map
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
                                GeneratedMutableMap.map18946map
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
                                        GeneratedMutableMap.map18939map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("#aa"),
                                        AOPVariable("Z")
                                ), listOf(
                                        GeneratedMutableMap.map18944map,
                                        GeneratedMutableMap.map18945map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y"),
                                AOPVariable("Z")
                        ), listOf(
                                GeneratedMutableMap.map18947map
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
                                GeneratedMutableMap.map18947map
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
                                        GeneratedMutableMap.map19006map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map19004map,
                                        GeneratedMutableMap.map19005map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19008map
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
                                GeneratedMutableMap.map19008map
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
                                        GeneratedMutableMap.map19007map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("#a"),
                                        AOPVariable("Y")
                                ), listOf(
                                        GeneratedMutableMap.map18944map,
                                        GeneratedMutableMap.map18945map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19009map
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
                                GeneratedMutableMap.map19009map
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
                                        GeneratedMutableMap.map19069map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map19071map,
                                        GeneratedMutableMap.map19072map,
                                        GeneratedMutableMap.map19073map,
                                        GeneratedMutableMap.map19074map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19075map,
                                GeneratedMutableMap.map19076map,
                                GeneratedMutableMap.map19077map
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
                                GeneratedMutableMap.map19075map,
                                GeneratedMutableMap.map19076map,
                                GeneratedMutableMap.map19077map
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
                                        GeneratedMutableMap.map19070map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("#a"),
                                        AOPVariable("Y")
                                ), listOf(
                                        GeneratedMutableMap.map19078map,
                                        GeneratedMutableMap.map19079map,
                                        GeneratedMutableMap.map19080map,
                                        GeneratedMutableMap.map19081map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19082map,
                                GeneratedMutableMap.map19083map,
                                GeneratedMutableMap.map19084map
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
                                GeneratedMutableMap.map19082map,
                                GeneratedMutableMap.map19083map,
                                GeneratedMutableMap.map19084map
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
                                GeneratedMutableMap.map19178map
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
                                GeneratedMutableMap.map19179map,
                                GeneratedMutableMap.map19180map,
                                GeneratedMutableMap.map19181map,
                                GeneratedMutableMap.map19182map
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
                                GeneratedMutableMap.map19351map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19350map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19352map
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
                                GeneratedMutableMap.map19352map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19347map,
                                GeneratedMutableMap.map19348map,
                                GeneratedMutableMap.map19349map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19352map
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
                                "#_19469"
                            ), listOf(
                                GeneratedMutableMap.map19535map,
                                GeneratedMutableMap.map19536map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19469"
                            ), listOf(
                                GeneratedMutableMap.map19535map,
                                GeneratedMutableMap.map19536map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19469"
                        ), listOf(
                            GeneratedMutableMap.map19535map,
                            GeneratedMutableMap.map19536map
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
                                "#_19469"
                            ), listOf(
                                GeneratedMutableMap.map19535map,
                                GeneratedMutableMap.map19536map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19469"
                            ), listOf(
                                GeneratedMutableMap.map19535map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19469"
                        ), listOf(
                            GeneratedMutableMap.map19535map
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
                                "#_19469"
                            ), listOf(
                                GeneratedMutableMap.map19535map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19469"
                            ), listOf(
                                GeneratedMutableMap.map19537map,
                                GeneratedMutableMap.map19538map,
                                GeneratedMutableMap.map19539map,
                                GeneratedMutableMap.map19540map,
                                GeneratedMutableMap.map19541map,
                                GeneratedMutableMap.map19542map,
                                GeneratedMutableMap.map19543map,
                                GeneratedMutableMap.map19544map,
                                GeneratedMutableMap.map19545map,
                                GeneratedMutableMap.map19546map,
                                GeneratedMutableMap.map19547map,
                                GeneratedMutableMap.map19548map,
                                GeneratedMutableMap.map19549map,
                                GeneratedMutableMap.map19550map,
                                GeneratedMutableMap.map19551map,
                                GeneratedMutableMap.map19552map,
                                GeneratedMutableMap.map19553map,
                                GeneratedMutableMap.map19554map,
                                GeneratedMutableMap.map19555map,
                                GeneratedMutableMap.map19556map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19469",
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
                                "#_19581"
                            ), listOf(
                                GeneratedMutableMap.map19652map,
                                GeneratedMutableMap.map19653map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19581"
                            ), listOf(
                                GeneratedMutableMap.map19652map,
                                GeneratedMutableMap.map19653map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19581"
                        ), listOf(
                            GeneratedMutableMap.map19652map,
                            GeneratedMutableMap.map19653map
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
                                "#_19581"
                            ), listOf(
                                GeneratedMutableMap.map19652map,
                                GeneratedMutableMap.map19653map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19581"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19581"
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
                                "#_19581"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19581"
                            ), listOf(
                                GeneratedMutableMap.map19654map,
                                GeneratedMutableMap.map19655map,
                                GeneratedMutableMap.map19656map,
                                GeneratedMutableMap.map19657map,
                                GeneratedMutableMap.map19658map,
                                GeneratedMutableMap.map19659map,
                                GeneratedMutableMap.map19660map,
                                GeneratedMutableMap.map19661map,
                                GeneratedMutableMap.map19662map,
                                GeneratedMutableMap.map19663map,
                                GeneratedMutableMap.map19664map,
                                GeneratedMutableMap.map19665map,
                                GeneratedMutableMap.map19666map,
                                GeneratedMutableMap.map19667map,
                                GeneratedMutableMap.map19668map,
                                GeneratedMutableMap.map19669map,
                                GeneratedMutableMap.map19670map,
                                GeneratedMutableMap.map19671map,
                                GeneratedMutableMap.map19672map,
                                GeneratedMutableMap.map19673map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19581",
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
                                "#_19695"
                            ), listOf(
                                GeneratedMutableMap.map19781map,
                                GeneratedMutableMap.map19782map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19695"
                            ), listOf(
                                GeneratedMutableMap.map19781map,
                                GeneratedMutableMap.map19782map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19695"
                        ), listOf(
                            GeneratedMutableMap.map19781map,
                            GeneratedMutableMap.map19782map
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
                                "#_19695"
                            ), listOf(
                                GeneratedMutableMap.map19781map,
                                GeneratedMutableMap.map19782map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19695"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19695"
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
                                "#_19695"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19695"
                            ), listOf(
                                GeneratedMutableMap.map19761map,
                                GeneratedMutableMap.map19762map,
                                GeneratedMutableMap.map19763map,
                                GeneratedMutableMap.map19764map,
                                GeneratedMutableMap.map19765map,
                                GeneratedMutableMap.map19766map,
                                GeneratedMutableMap.map19767map,
                                GeneratedMutableMap.map19768map,
                                GeneratedMutableMap.map19769map,
                                GeneratedMutableMap.map19770map,
                                GeneratedMutableMap.map19771map,
                                GeneratedMutableMap.map19772map,
                                GeneratedMutableMap.map19773map,
                                GeneratedMutableMap.map19774map,
                                GeneratedMutableMap.map19775map,
                                GeneratedMutableMap.map19776map,
                                GeneratedMutableMap.map19777map,
                                GeneratedMutableMap.map19778map,
                                GeneratedMutableMap.map19779map,
                                GeneratedMutableMap.map19780map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19695",
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
                                "#_19805"
                            ), listOf(
                                GeneratedMutableMap.map19907map,
                                GeneratedMutableMap.map19908map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19805"
                            ), listOf(
                                GeneratedMutableMap.map19907map,
                                GeneratedMutableMap.map19908map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19805"
                        ), listOf(
                            GeneratedMutableMap.map19907map,
                            GeneratedMutableMap.map19908map
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
                                "#_19805"
                            ), listOf(
                                GeneratedMutableMap.map19907map,
                                GeneratedMutableMap.map19908map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19805"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19805"
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
                                "#_19805"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19805"
                            ), listOf(
                                GeneratedMutableMap.map19887map,
                                GeneratedMutableMap.map19888map,
                                GeneratedMutableMap.map19889map,
                                GeneratedMutableMap.map19890map,
                                GeneratedMutableMap.map19891map,
                                GeneratedMutableMap.map19892map,
                                GeneratedMutableMap.map19893map,
                                GeneratedMutableMap.map19894map,
                                GeneratedMutableMap.map19895map,
                                GeneratedMutableMap.map19896map,
                                GeneratedMutableMap.map19897map,
                                GeneratedMutableMap.map19898map,
                                GeneratedMutableMap.map19899map,
                                GeneratedMutableMap.map19900map,
                                GeneratedMutableMap.map19901map,
                                GeneratedMutableMap.map19902map,
                                GeneratedMutableMap.map19903map,
                                GeneratedMutableMap.map19904map,
                                GeneratedMutableMap.map19905map,
                                GeneratedMutableMap.map19906map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19805",
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
                                "#_19931"
                            ), listOf(
                                GeneratedMutableMap.map20013map,
                                GeneratedMutableMap.map20014map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19931"
                            ), listOf(
                                GeneratedMutableMap.map20013map,
                                GeneratedMutableMap.map20014map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19931"
                        ), listOf(
                            GeneratedMutableMap.map20013map,
                            GeneratedMutableMap.map20014map
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
                                "#_19931"
                            ), listOf(
                                GeneratedMutableMap.map20013map,
                                GeneratedMutableMap.map20014map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19931"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19931"
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
                                "#_19931"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19931"
                            ), listOf(
                                GeneratedMutableMap.map20015map,
                                GeneratedMutableMap.map20016map,
                                GeneratedMutableMap.map20017map,
                                GeneratedMutableMap.map20018map,
                                GeneratedMutableMap.map20019map,
                                GeneratedMutableMap.map20020map,
                                GeneratedMutableMap.map20021map,
                                GeneratedMutableMap.map20022map,
                                GeneratedMutableMap.map20023map,
                                GeneratedMutableMap.map20024map,
                                GeneratedMutableMap.map20025map,
                                GeneratedMutableMap.map20026map,
                                GeneratedMutableMap.map20027map,
                                GeneratedMutableMap.map20028map,
                                GeneratedMutableMap.map20029map,
                                GeneratedMutableMap.map20030map,
                                GeneratedMutableMap.map20031map,
                                GeneratedMutableMap.map20032map,
                                GeneratedMutableMap.map20033map,
                                GeneratedMutableMap.map20034map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19931",
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
                                "#_20057"
                            ), listOf(
                                GeneratedMutableMap.map20159map,
                                GeneratedMutableMap.map20160map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20057"
                            ), listOf(
                                GeneratedMutableMap.map20159map,
                                GeneratedMutableMap.map20160map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20057"
                        ), listOf(
                            GeneratedMutableMap.map20159map,
                            GeneratedMutableMap.map20160map
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
                                "#_20057"
                            ), listOf(
                                GeneratedMutableMap.map20159map,
                                GeneratedMutableMap.map20160map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20057"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20057"
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
                                "#_20057"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20057"
                            ), listOf(
                                GeneratedMutableMap.map20139map,
                                GeneratedMutableMap.map20140map,
                                GeneratedMutableMap.map20141map,
                                GeneratedMutableMap.map20142map,
                                GeneratedMutableMap.map20143map,
                                GeneratedMutableMap.map20144map,
                                GeneratedMutableMap.map20145map,
                                GeneratedMutableMap.map20146map,
                                GeneratedMutableMap.map20147map,
                                GeneratedMutableMap.map20148map,
                                GeneratedMutableMap.map20149map,
                                GeneratedMutableMap.map20150map,
                                GeneratedMutableMap.map20151map,
                                GeneratedMutableMap.map20152map,
                                GeneratedMutableMap.map20153map,
                                GeneratedMutableMap.map20154map,
                                GeneratedMutableMap.map20155map,
                                GeneratedMutableMap.map20156map,
                                GeneratedMutableMap.map20157map,
                                GeneratedMutableMap.map20158map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20057",
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
                                "#_20184"
                            ), listOf(
                                GeneratedMutableMap.map20252map,
                                GeneratedMutableMap.map20253map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20184"
                            ), listOf(
                                GeneratedMutableMap.map20252map,
                                GeneratedMutableMap.map20253map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20184"
                        ), listOf(
                            GeneratedMutableMap.map20252map,
                            GeneratedMutableMap.map20253map
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
                                "#_20184"
                            ), listOf(
                                GeneratedMutableMap.map20252map,
                                GeneratedMutableMap.map20253map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20184"
                            ), listOf(
                                GeneratedMutableMap.map20252map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20184"
                        ), listOf(
                            GeneratedMutableMap.map20252map
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
                                "#_20184"
                            ), listOf(
                                GeneratedMutableMap.map20252map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#_20184"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20184",
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
                                GeneratedMutableMap.map20358map,
                                GeneratedMutableMap.map20359map
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
                                GeneratedMutableMap.map20358map
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
                                "#_20376",
                                "#_20381",
                                "#_20373"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20373"
                            ), listOf(
                                GeneratedMutableMap.map20465map,
                                GeneratedMutableMap.map20466map,
                                GeneratedMutableMap.map20467map,
                                GeneratedMutableMap.map20468map,
                                GeneratedMutableMap.map20469map,
                                GeneratedMutableMap.map20470map,
                                GeneratedMutableMap.map20471map,
                                GeneratedMutableMap.map20472map,
                                GeneratedMutableMap.map20473map,
                                GeneratedMutableMap.map20474map,
                                GeneratedMutableMap.map20475map,
                                GeneratedMutableMap.map20476map,
                                GeneratedMutableMap.map20477map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20376",
                            "#_20381",
                            "#_20373",
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
                                "#_20494",
                                "#_20498",
                                "#_20508",
                                "#_20491"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20491"
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
                            "#_20494",
                            "#_20498",
                            "#_20508",
                            "#_20491",
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
                                "#_20648",
                                "#_20659",
                                "#_20664",
                                "#_20656"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20648"
                            ), listOf(
                                GeneratedMutableMap.map20779map,
                                GeneratedMutableMap.map20780map,
                                GeneratedMutableMap.map20781map,
                                GeneratedMutableMap.map20782map,
                                GeneratedMutableMap.map20783map,
                                GeneratedMutableMap.map20784map,
                                GeneratedMutableMap.map20785map,
                                GeneratedMutableMap.map20786map,
                                GeneratedMutableMap.map20787map,
                                GeneratedMutableMap.map20788map,
                                GeneratedMutableMap.map20789map,
                                GeneratedMutableMap.map20790map,
                                GeneratedMutableMap.map20791map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20648",
                            "#_20659",
                            "#_20664",
                            "#_20656",
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
                                "#_20805"
                            ), listOf(
                                GeneratedMutableMap.map20922map,
                                GeneratedMutableMap.map20923map,
                                GeneratedMutableMap.map20924map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20810"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20805",
                            "#_20810"
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
                                "#_20805",
                                "#_20810",
                                "#_20815"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20805"
                            ), listOf(
                                GeneratedMutableMap.map20909map,
                                GeneratedMutableMap.map20910map,
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
                                GeneratedMutableMap.map20921map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20805",
                            "#_20810",
                            "#_20815",
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
                                "#_20938"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20946"
                            ), listOf(
                                GeneratedMutableMap.map21081map,
                                GeneratedMutableMap.map21082map,
                                GeneratedMutableMap.map21083map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20938",
                            "#_20946"
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
                                "#_20938",
                                "#_20946",
                                "#_20951",
                                "#_20956"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20938"
                            ), listOf(
                                GeneratedMutableMap.map21084map,
                                GeneratedMutableMap.map21085map,
                                GeneratedMutableMap.map21086map,
                                GeneratedMutableMap.map21087map,
                                GeneratedMutableMap.map21088map,
                                GeneratedMutableMap.map21089map,
                                GeneratedMutableMap.map21090map,
                                GeneratedMutableMap.map21091map,
                                GeneratedMutableMap.map21092map,
                                GeneratedMutableMap.map21093map,
                                GeneratedMutableMap.map21094map,
                                GeneratedMutableMap.map21095map,
                                GeneratedMutableMap.map21096map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20938",
                            "#_20946",
                            "#_20951",
                            "#_20956",
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
                                "#_21110"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21118"
                            ), listOf(
                                GeneratedMutableMap.map21278map,
                                GeneratedMutableMap.map21279map,
                                GeneratedMutableMap.map21280map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21110",
                            "#_21118"
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
                                "#_21110",
                                "#_21118",
                                "#_21123",
                                "#_21128",
                                "#_21133"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21110"
                            ), listOf(
                                GeneratedMutableMap.map21281map,
                                GeneratedMutableMap.map21282map,
                                GeneratedMutableMap.map21283map,
                                GeneratedMutableMap.map21284map,
                                GeneratedMutableMap.map21285map,
                                GeneratedMutableMap.map21286map,
                                GeneratedMutableMap.map21287map,
                                GeneratedMutableMap.map21288map,
                                GeneratedMutableMap.map21289map,
                                GeneratedMutableMap.map21290map,
                                GeneratedMutableMap.map21291map,
                                GeneratedMutableMap.map21292map,
                                GeneratedMutableMap.map21293map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21110",
                            "#_21118",
                            "#_21123",
                            "#_21128",
                            "#_21133",
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
                                "#_21307"
                            ), listOf(
                                GeneratedMutableMap.map21502map,
                                GeneratedMutableMap.map21503map,
                                GeneratedMutableMap.map21504map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21312"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21307",
                            "#_21312"
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
                                "#_21307",
                                "#_21312"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21316"
                            ), listOf(
                                GeneratedMutableMap.map21486map,
                                GeneratedMutableMap.map21487map,
                                GeneratedMutableMap.map21488map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21307",
                            "#_21312",
                            "#_21316"
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
                                "#_21307",
                                "#_21312",
                                "#_21316",
                                "#_21321",
                                "#_21326",
                                "#_21332"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21307"
                            ), listOf(
                                GeneratedMutableMap.map21489map,
                                GeneratedMutableMap.map21490map,
                                GeneratedMutableMap.map21491map,
                                GeneratedMutableMap.map21492map,
                                GeneratedMutableMap.map21493map,
                                GeneratedMutableMap.map21494map,
                                GeneratedMutableMap.map21495map,
                                GeneratedMutableMap.map21496map,
                                GeneratedMutableMap.map21497map,
                                GeneratedMutableMap.map21498map,
                                GeneratedMutableMap.map21499map,
                                GeneratedMutableMap.map21500map,
                                GeneratedMutableMap.map21501map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21307",
                            "#_21312",
                            "#_21316",
                            "#_21321",
                            "#_21326",
                            "#_21332",
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
                                "#_21518",
                                "#_21526"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21518"
                            ), listOf(
                                GeneratedMutableMap.map21625map,
                                GeneratedMutableMap.map21626map,
                                GeneratedMutableMap.map21627map,
                                GeneratedMutableMap.map21628map,
                                GeneratedMutableMap.map21629map,
                                GeneratedMutableMap.map21630map,
                                GeneratedMutableMap.map21631map,
                                GeneratedMutableMap.map21632map,
                                GeneratedMutableMap.map21633map,
                                GeneratedMutableMap.map21634map,
                                GeneratedMutableMap.map21635map,
                                GeneratedMutableMap.map21636map,
                                GeneratedMutableMap.map21637map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21518",
                            "#_21526",
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
                                        GeneratedMutableMap.map24641map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map24639map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24643map
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
                                GeneratedMutableMap.map24643map
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
                                        GeneratedMutableMap.map24642map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("str2")
                                ), listOf(
                                        GeneratedMutableMap.map24640map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("str1"),
                                AOPVariable("str2")
                        ), listOf(
                                GeneratedMutableMap.map24644map
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
                                GeneratedMutableMap.map24644map
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
                                        GeneratedMutableMap.map25239map,
                                        GeneratedMutableMap.map25240map,
                                        GeneratedMutableMap.map25241map,
                                        GeneratedMutableMap.map25242map,
                                        GeneratedMutableMap.map25243map,
                                        GeneratedMutableMap.map25244map,
                                        GeneratedMutableMap.map25245map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s2",
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map25253map,
                                        GeneratedMutableMap.map25254map,
                                        GeneratedMutableMap.map25255map,
                                        GeneratedMutableMap.map25256map,
                                        GeneratedMutableMap.map25257map,
                                        GeneratedMutableMap.map25258map,
                                        GeneratedMutableMap.map25259map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map25260map,
                                GeneratedMutableMap.map25261map,
                                GeneratedMutableMap.map25262map,
                                GeneratedMutableMap.map25263map,
                                GeneratedMutableMap.map25264map,
                                GeneratedMutableMap.map25265map,
                                GeneratedMutableMap.map25266map,
                                GeneratedMutableMap.map25267map,
                                GeneratedMutableMap.map25268map,
                                GeneratedMutableMap.map25269map,
                                GeneratedMutableMap.map25270map,
                                GeneratedMutableMap.map25271map,
                                GeneratedMutableMap.map25272map,
                                GeneratedMutableMap.map25273map,
                                GeneratedMutableMap.map25274map,
                                GeneratedMutableMap.map25275map,
                                GeneratedMutableMap.map25276map,
                                GeneratedMutableMap.map25277map,
                                GeneratedMutableMap.map25278map,
                                GeneratedMutableMap.map25279map,
                                GeneratedMutableMap.map25280map,
                                GeneratedMutableMap.map25281map,
                                GeneratedMutableMap.map25282map,
                                GeneratedMutableMap.map25283map,
                                GeneratedMutableMap.map25284map,
                                GeneratedMutableMap.map25285map,
                                GeneratedMutableMap.map25286map,
                                GeneratedMutableMap.map25287map,
                                GeneratedMutableMap.map25288map,
                                GeneratedMutableMap.map25289map,
                                GeneratedMutableMap.map25290map,
                                GeneratedMutableMap.map25291map,
                                GeneratedMutableMap.map25292map,
                                GeneratedMutableMap.map25293map,
                                GeneratedMutableMap.map25294map,
                                GeneratedMutableMap.map25295map,
                                GeneratedMutableMap.map25296map,
                                GeneratedMutableMap.map25297map,
                                GeneratedMutableMap.map25298map,
                                GeneratedMutableMap.map25299map,
                                GeneratedMutableMap.map25300map,
                                GeneratedMutableMap.map25301map,
                                GeneratedMutableMap.map25302map,
                                GeneratedMutableMap.map25303map,
                                GeneratedMutableMap.map25304map,
                                GeneratedMutableMap.map25305map,
                                GeneratedMutableMap.map25306map,
                                GeneratedMutableMap.map25307map,
                                GeneratedMutableMap.map25308map
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
                                GeneratedMutableMap.map25260map,
                                GeneratedMutableMap.map25261map,
                                GeneratedMutableMap.map25262map,
                                GeneratedMutableMap.map25263map,
                                GeneratedMutableMap.map25264map,
                                GeneratedMutableMap.map25265map,
                                GeneratedMutableMap.map25266map,
                                GeneratedMutableMap.map25267map,
                                GeneratedMutableMap.map25268map,
                                GeneratedMutableMap.map25269map,
                                GeneratedMutableMap.map25270map,
                                GeneratedMutableMap.map25271map,
                                GeneratedMutableMap.map25272map,
                                GeneratedMutableMap.map25273map,
                                GeneratedMutableMap.map25274map,
                                GeneratedMutableMap.map25275map,
                                GeneratedMutableMap.map25276map,
                                GeneratedMutableMap.map25277map,
                                GeneratedMutableMap.map25278map,
                                GeneratedMutableMap.map25279map,
                                GeneratedMutableMap.map25280map,
                                GeneratedMutableMap.map25281map,
                                GeneratedMutableMap.map25282map,
                                GeneratedMutableMap.map25283map,
                                GeneratedMutableMap.map25284map,
                                GeneratedMutableMap.map25285map,
                                GeneratedMutableMap.map25286map,
                                GeneratedMutableMap.map25287map,
                                GeneratedMutableMap.map25288map,
                                GeneratedMutableMap.map25289map,
                                GeneratedMutableMap.map25290map,
                                GeneratedMutableMap.map25291map,
                                GeneratedMutableMap.map25292map,
                                GeneratedMutableMap.map25293map,
                                GeneratedMutableMap.map25294map,
                                GeneratedMutableMap.map25295map,
                                GeneratedMutableMap.map25296map,
                                GeneratedMutableMap.map25297map,
                                GeneratedMutableMap.map25298map,
                                GeneratedMutableMap.map25299map,
                                GeneratedMutableMap.map25300map,
                                GeneratedMutableMap.map25301map,
                                GeneratedMutableMap.map25302map,
                                GeneratedMutableMap.map25303map,
                                GeneratedMutableMap.map25304map,
                                GeneratedMutableMap.map25305map,
                                GeneratedMutableMap.map25306map,
                                GeneratedMutableMap.map25307map,
                                GeneratedMutableMap.map25308map
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
                                        GeneratedMutableMap.map25246map,
                                        GeneratedMutableMap.map25247map,
                                        GeneratedMutableMap.map25248map,
                                        GeneratedMutableMap.map25249map,
                                        GeneratedMutableMap.map25250map,
                                        GeneratedMutableMap.map25251map,
                                        GeneratedMutableMap.map25252map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s2"),
                                        AOPVariable("str2")
                                ), listOf(
                                        GeneratedMutableMap.map25246map,
                                        GeneratedMutableMap.map25247map,
                                        GeneratedMutableMap.map25248map,
                                        GeneratedMutableMap.map25249map,
                                        GeneratedMutableMap.map25250map,
                                        GeneratedMutableMap.map25251map,
                                        GeneratedMutableMap.map25252map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("str1"),
                                AOPVariable("s2"),
                                AOPVariable("str2")
                        ), listOf(
                                GeneratedMutableMap.map25309map,
                                GeneratedMutableMap.map25310map,
                                GeneratedMutableMap.map25311map,
                                GeneratedMutableMap.map25312map,
                                GeneratedMutableMap.map25313map,
                                GeneratedMutableMap.map25314map,
                                GeneratedMutableMap.map25315map,
                                GeneratedMutableMap.map25316map,
                                GeneratedMutableMap.map25317map,
                                GeneratedMutableMap.map25318map,
                                GeneratedMutableMap.map25319map,
                                GeneratedMutableMap.map25320map,
                                GeneratedMutableMap.map25321map,
                                GeneratedMutableMap.map25322map,
                                GeneratedMutableMap.map25323map,
                                GeneratedMutableMap.map25324map,
                                GeneratedMutableMap.map25325map,
                                GeneratedMutableMap.map25326map,
                                GeneratedMutableMap.map25327map,
                                GeneratedMutableMap.map25328map,
                                GeneratedMutableMap.map25329map,
                                GeneratedMutableMap.map25330map,
                                GeneratedMutableMap.map25331map,
                                GeneratedMutableMap.map25332map,
                                GeneratedMutableMap.map25333map,
                                GeneratedMutableMap.map25334map,
                                GeneratedMutableMap.map25335map,
                                GeneratedMutableMap.map25336map,
                                GeneratedMutableMap.map25337map,
                                GeneratedMutableMap.map25338map,
                                GeneratedMutableMap.map25339map,
                                GeneratedMutableMap.map25340map,
                                GeneratedMutableMap.map25341map,
                                GeneratedMutableMap.map25342map,
                                GeneratedMutableMap.map25343map,
                                GeneratedMutableMap.map25344map,
                                GeneratedMutableMap.map25345map,
                                GeneratedMutableMap.map25346map,
                                GeneratedMutableMap.map25347map,
                                GeneratedMutableMap.map25348map,
                                GeneratedMutableMap.map25349map,
                                GeneratedMutableMap.map25350map,
                                GeneratedMutableMap.map25351map,
                                GeneratedMutableMap.map25352map,
                                GeneratedMutableMap.map25353map,
                                GeneratedMutableMap.map25354map,
                                GeneratedMutableMap.map25355map,
                                GeneratedMutableMap.map25356map,
                                GeneratedMutableMap.map25357map
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
                                GeneratedMutableMap.map25309map,
                                GeneratedMutableMap.map25310map,
                                GeneratedMutableMap.map25311map,
                                GeneratedMutableMap.map25312map,
                                GeneratedMutableMap.map25313map,
                                GeneratedMutableMap.map25314map,
                                GeneratedMutableMap.map25315map,
                                GeneratedMutableMap.map25316map,
                                GeneratedMutableMap.map25317map,
                                GeneratedMutableMap.map25318map,
                                GeneratedMutableMap.map25319map,
                                GeneratedMutableMap.map25320map,
                                GeneratedMutableMap.map25321map,
                                GeneratedMutableMap.map25322map,
                                GeneratedMutableMap.map25323map,
                                GeneratedMutableMap.map25324map,
                                GeneratedMutableMap.map25325map,
                                GeneratedMutableMap.map25326map,
                                GeneratedMutableMap.map25327map,
                                GeneratedMutableMap.map25328map,
                                GeneratedMutableMap.map25329map,
                                GeneratedMutableMap.map25330map,
                                GeneratedMutableMap.map25331map,
                                GeneratedMutableMap.map25332map,
                                GeneratedMutableMap.map25333map,
                                GeneratedMutableMap.map25334map,
                                GeneratedMutableMap.map25335map,
                                GeneratedMutableMap.map25336map,
                                GeneratedMutableMap.map25337map,
                                GeneratedMutableMap.map25338map,
                                GeneratedMutableMap.map25339map,
                                GeneratedMutableMap.map25340map,
                                GeneratedMutableMap.map25341map,
                                GeneratedMutableMap.map25342map,
                                GeneratedMutableMap.map25343map,
                                GeneratedMutableMap.map25344map,
                                GeneratedMutableMap.map25345map,
                                GeneratedMutableMap.map25346map,
                                GeneratedMutableMap.map25347map,
                                GeneratedMutableMap.map25348map,
                                GeneratedMutableMap.map25349map,
                                GeneratedMutableMap.map25350map,
                                GeneratedMutableMap.map25351map,
                                GeneratedMutableMap.map25352map,
                                GeneratedMutableMap.map25353map,
                                GeneratedMutableMap.map25354map,
                                GeneratedMutableMap.map25355map,
                                GeneratedMutableMap.map25356map,
                                GeneratedMutableMap.map25357map
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
                                        GeneratedMutableMap.map27165map,
                                        GeneratedMutableMap.map27166map,
                                        GeneratedMutableMap.map27167map,
                                        GeneratedMutableMap.map27168map,
                                        GeneratedMutableMap.map27169map,
                                        GeneratedMutableMap.map27170map,
                                        GeneratedMutableMap.map27171map,
                                        GeneratedMutableMap.map27172map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map27149map,
                                        GeneratedMutableMap.map27150map,
                                        GeneratedMutableMap.map27151map,
                                        GeneratedMutableMap.map27152map,
                                        GeneratedMutableMap.map27153map,
                                        GeneratedMutableMap.map27154map,
                                        GeneratedMutableMap.map27155map,
                                        GeneratedMutableMap.map27156map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map27181map,
                                GeneratedMutableMap.map27182map,
                                GeneratedMutableMap.map27183map,
                                GeneratedMutableMap.map27184map,
                                GeneratedMutableMap.map27185map,
                                GeneratedMutableMap.map27186map,
                                GeneratedMutableMap.map27187map,
                                GeneratedMutableMap.map27188map
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
                                GeneratedMutableMap.map27181map,
                                GeneratedMutableMap.map27182map,
                                GeneratedMutableMap.map27183map,
                                GeneratedMutableMap.map27184map,
                                GeneratedMutableMap.map27185map,
                                GeneratedMutableMap.map27186map,
                                GeneratedMutableMap.map27187map,
                                GeneratedMutableMap.map27188map
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
                                        GeneratedMutableMap.map27173map,
                                        GeneratedMutableMap.map27174map,
                                        GeneratedMutableMap.map27175map,
                                        GeneratedMutableMap.map27176map,
                                        GeneratedMutableMap.map27177map,
                                        GeneratedMutableMap.map27178map,
                                        GeneratedMutableMap.map27179map,
                                        GeneratedMutableMap.map27180map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map27157map,
                                        GeneratedMutableMap.map27158map,
                                        GeneratedMutableMap.map27159map,
                                        GeneratedMutableMap.map27160map,
                                        GeneratedMutableMap.map27161map,
                                        GeneratedMutableMap.map27162map,
                                        GeneratedMutableMap.map27163map,
                                        GeneratedMutableMap.map27164map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map27189map,
                                GeneratedMutableMap.map27190map,
                                GeneratedMutableMap.map27191map,
                                GeneratedMutableMap.map27192map,
                                GeneratedMutableMap.map27193map,
                                GeneratedMutableMap.map27194map,
                                GeneratedMutableMap.map27195map,
                                GeneratedMutableMap.map27196map
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
                                GeneratedMutableMap.map27189map,
                                GeneratedMutableMap.map27190map,
                                GeneratedMutableMap.map27191map,
                                GeneratedMutableMap.map27192map,
                                GeneratedMutableMap.map27193map,
                                GeneratedMutableMap.map27194map,
                                GeneratedMutableMap.map27195map,
                                GeneratedMutableMap.map27196map
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
                                        GeneratedMutableMap.map30955map,
                                        GeneratedMutableMap.map30956map,
                                        GeneratedMutableMap.map30957map,
                                        GeneratedMutableMap.map30958map,
                                        GeneratedMutableMap.map30959map,
                                        GeneratedMutableMap.map30960map,
                                        GeneratedMutableMap.map30961map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "b",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map30948map,
                                        GeneratedMutableMap.map30949map,
                                        GeneratedMutableMap.map30950map,
                                        GeneratedMutableMap.map30951map,
                                        GeneratedMutableMap.map30952map,
                                        GeneratedMutableMap.map30953map,
                                        GeneratedMutableMap.map30954map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map30962map,
                                GeneratedMutableMap.map30963map,
                                GeneratedMutableMap.map30964map,
                                GeneratedMutableMap.map30965map,
                                GeneratedMutableMap.map30966map,
                                GeneratedMutableMap.map30967map,
                                GeneratedMutableMap.map30968map,
                                GeneratedMutableMap.map30969map,
                                GeneratedMutableMap.map30970map,
                                GeneratedMutableMap.map30971map,
                                GeneratedMutableMap.map30972map,
                                GeneratedMutableMap.map30973map,
                                GeneratedMutableMap.map30974map,
                                GeneratedMutableMap.map30975map,
                                GeneratedMutableMap.map30976map,
                                GeneratedMutableMap.map30977map,
                                GeneratedMutableMap.map30978map,
                                GeneratedMutableMap.map30979map,
                                GeneratedMutableMap.map30980map,
                                GeneratedMutableMap.map30981map,
                                GeneratedMutableMap.map30982map,
                                GeneratedMutableMap.map30983map,
                                GeneratedMutableMap.map30984map,
                                GeneratedMutableMap.map30985map,
                                GeneratedMutableMap.map30986map,
                                GeneratedMutableMap.map30987map,
                                GeneratedMutableMap.map30988map,
                                GeneratedMutableMap.map30989map,
                                GeneratedMutableMap.map30990map,
                                GeneratedMutableMap.map30991map,
                                GeneratedMutableMap.map30992map,
                                GeneratedMutableMap.map30993map,
                                GeneratedMutableMap.map30994map,
                                GeneratedMutableMap.map30995map,
                                GeneratedMutableMap.map30996map,
                                GeneratedMutableMap.map30997map,
                                GeneratedMutableMap.map30998map,
                                GeneratedMutableMap.map30999map,
                                GeneratedMutableMap.map31000map,
                                GeneratedMutableMap.map31001map,
                                GeneratedMutableMap.map31002map,
                                GeneratedMutableMap.map31003map,
                                GeneratedMutableMap.map31004map,
                                GeneratedMutableMap.map31005map,
                                GeneratedMutableMap.map31006map,
                                GeneratedMutableMap.map31007map,
                                GeneratedMutableMap.map31008map,
                                GeneratedMutableMap.map31009map,
                                GeneratedMutableMap.map31010map
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
                                GeneratedMutableMap.map30962map,
                                GeneratedMutableMap.map30963map,
                                GeneratedMutableMap.map30964map,
                                GeneratedMutableMap.map30965map,
                                GeneratedMutableMap.map30966map,
                                GeneratedMutableMap.map30967map,
                                GeneratedMutableMap.map30968map,
                                GeneratedMutableMap.map30969map,
                                GeneratedMutableMap.map30970map,
                                GeneratedMutableMap.map30971map,
                                GeneratedMutableMap.map30972map,
                                GeneratedMutableMap.map30973map,
                                GeneratedMutableMap.map30974map,
                                GeneratedMutableMap.map30975map,
                                GeneratedMutableMap.map30976map,
                                GeneratedMutableMap.map30977map,
                                GeneratedMutableMap.map30978map,
                                GeneratedMutableMap.map30979map,
                                GeneratedMutableMap.map30980map,
                                GeneratedMutableMap.map30981map,
                                GeneratedMutableMap.map30982map,
                                GeneratedMutableMap.map30983map,
                                GeneratedMutableMap.map30984map,
                                GeneratedMutableMap.map30985map,
                                GeneratedMutableMap.map30986map,
                                GeneratedMutableMap.map30987map,
                                GeneratedMutableMap.map30988map,
                                GeneratedMutableMap.map30989map,
                                GeneratedMutableMap.map30990map,
                                GeneratedMutableMap.map30991map,
                                GeneratedMutableMap.map30992map,
                                GeneratedMutableMap.map30993map,
                                GeneratedMutableMap.map30994map,
                                GeneratedMutableMap.map30995map,
                                GeneratedMutableMap.map30996map,
                                GeneratedMutableMap.map30997map,
                                GeneratedMutableMap.map30998map,
                                GeneratedMutableMap.map30999map,
                                GeneratedMutableMap.map31000map,
                                GeneratedMutableMap.map31001map,
                                GeneratedMutableMap.map31002map,
                                GeneratedMutableMap.map31003map,
                                GeneratedMutableMap.map31004map,
                                GeneratedMutableMap.map31005map,
                                GeneratedMutableMap.map31006map,
                                GeneratedMutableMap.map31007map,
                                GeneratedMutableMap.map31008map,
                                GeneratedMutableMap.map31009map,
                                GeneratedMutableMap.map31010map
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
                                        GeneratedMutableMap.map22703map,
                                        GeneratedMutableMap.map22704map,
                                        GeneratedMutableMap.map22705map,
                                        GeneratedMutableMap.map22706map,
                                        GeneratedMutableMap.map22707map,
                                        GeneratedMutableMap.map22708map,
                                        GeneratedMutableMap.map22709map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("b"),
                                        AOPVariable("s2")
                                ), listOf(
                                        GeneratedMutableMap.map22703map,
                                        GeneratedMutableMap.map22704map,
                                        GeneratedMutableMap.map22705map,
                                        GeneratedMutableMap.map22706map,
                                        GeneratedMutableMap.map22707map,
                                        GeneratedMutableMap.map22708map,
                                        GeneratedMutableMap.map22709map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1"),
                                AOPVariable("b"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map31011map,
                                GeneratedMutableMap.map31012map,
                                GeneratedMutableMap.map31013map,
                                GeneratedMutableMap.map31014map,
                                GeneratedMutableMap.map31015map,
                                GeneratedMutableMap.map31016map,
                                GeneratedMutableMap.map31017map,
                                GeneratedMutableMap.map31018map,
                                GeneratedMutableMap.map31019map,
                                GeneratedMutableMap.map31020map,
                                GeneratedMutableMap.map31021map,
                                GeneratedMutableMap.map31022map,
                                GeneratedMutableMap.map31023map,
                                GeneratedMutableMap.map31024map,
                                GeneratedMutableMap.map31025map,
                                GeneratedMutableMap.map31026map,
                                GeneratedMutableMap.map31027map,
                                GeneratedMutableMap.map31028map,
                                GeneratedMutableMap.map31029map,
                                GeneratedMutableMap.map31030map,
                                GeneratedMutableMap.map31031map,
                                GeneratedMutableMap.map31032map,
                                GeneratedMutableMap.map31033map,
                                GeneratedMutableMap.map31034map,
                                GeneratedMutableMap.map31035map,
                                GeneratedMutableMap.map31036map,
                                GeneratedMutableMap.map31037map,
                                GeneratedMutableMap.map31038map,
                                GeneratedMutableMap.map31039map,
                                GeneratedMutableMap.map31040map,
                                GeneratedMutableMap.map31041map,
                                GeneratedMutableMap.map31042map,
                                GeneratedMutableMap.map31043map,
                                GeneratedMutableMap.map31044map,
                                GeneratedMutableMap.map31045map,
                                GeneratedMutableMap.map31046map,
                                GeneratedMutableMap.map31047map,
                                GeneratedMutableMap.map31048map,
                                GeneratedMutableMap.map31049map,
                                GeneratedMutableMap.map31050map,
                                GeneratedMutableMap.map31051map,
                                GeneratedMutableMap.map31052map,
                                GeneratedMutableMap.map31053map,
                                GeneratedMutableMap.map31054map,
                                GeneratedMutableMap.map31055map,
                                GeneratedMutableMap.map31056map,
                                GeneratedMutableMap.map31057map,
                                GeneratedMutableMap.map31058map,
                                GeneratedMutableMap.map31059map
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
                                GeneratedMutableMap.map31011map,
                                GeneratedMutableMap.map31012map,
                                GeneratedMutableMap.map31013map,
                                GeneratedMutableMap.map31014map,
                                GeneratedMutableMap.map31015map,
                                GeneratedMutableMap.map31016map,
                                GeneratedMutableMap.map31017map,
                                GeneratedMutableMap.map31018map,
                                GeneratedMutableMap.map31019map,
                                GeneratedMutableMap.map31020map,
                                GeneratedMutableMap.map31021map,
                                GeneratedMutableMap.map31022map,
                                GeneratedMutableMap.map31023map,
                                GeneratedMutableMap.map31024map,
                                GeneratedMutableMap.map31025map,
                                GeneratedMutableMap.map31026map,
                                GeneratedMutableMap.map31027map,
                                GeneratedMutableMap.map31028map,
                                GeneratedMutableMap.map31029map,
                                GeneratedMutableMap.map31030map,
                                GeneratedMutableMap.map31031map,
                                GeneratedMutableMap.map31032map,
                                GeneratedMutableMap.map31033map,
                                GeneratedMutableMap.map31034map,
                                GeneratedMutableMap.map31035map,
                                GeneratedMutableMap.map31036map,
                                GeneratedMutableMap.map31037map,
                                GeneratedMutableMap.map31038map,
                                GeneratedMutableMap.map31039map,
                                GeneratedMutableMap.map31040map,
                                GeneratedMutableMap.map31041map,
                                GeneratedMutableMap.map31042map,
                                GeneratedMutableMap.map31043map,
                                GeneratedMutableMap.map31044map,
                                GeneratedMutableMap.map31045map,
                                GeneratedMutableMap.map31046map,
                                GeneratedMutableMap.map31047map,
                                GeneratedMutableMap.map31048map,
                                GeneratedMutableMap.map31049map,
                                GeneratedMutableMap.map31050map,
                                GeneratedMutableMap.map31051map,
                                GeneratedMutableMap.map31052map,
                                GeneratedMutableMap.map31053map,
                                GeneratedMutableMap.map31054map,
                                GeneratedMutableMap.map31055map,
                                GeneratedMutableMap.map31056map,
                                GeneratedMutableMap.map31057map,
                                GeneratedMutableMap.map31058map,
                                GeneratedMutableMap.map31059map
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
                                        GeneratedMutableMap.map32424map,
                                        GeneratedMutableMap.map32425map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        GeneratedMutableMap.map32559map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map32560map,
                                GeneratedMutableMap.map32561map
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
                                GeneratedMutableMap.map32560map,
                                GeneratedMutableMap.map32561map
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
                                        GeneratedMutableMap.map32426map,
                                        GeneratedMutableMap.map32427map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("w")
                                ), listOf(
                                        GeneratedMutableMap.map32562map
                                )
                                ),
                                true),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("w")
                        ), listOf(
                                GeneratedMutableMap.map32563map,
                                GeneratedMutableMap.map32564map
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
                                GeneratedMutableMap.map32563map,
                                GeneratedMutableMap.map32564map
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
                                        GeneratedMutableMap.map32424map,
                                        GeneratedMutableMap.map32425map,
                                        GeneratedMutableMap.map32706map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        GeneratedMutableMap.map32559map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map32560map,
                                GeneratedMutableMap.map32561map,
                                GeneratedMutableMap.map32708map
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
                                GeneratedMutableMap.map32560map,
                                GeneratedMutableMap.map32561map,
                                GeneratedMutableMap.map32708map
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
                                        GeneratedMutableMap.map32426map,
                                        GeneratedMutableMap.map32427map,
                                        GeneratedMutableMap.map32707map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("w")
                                ), listOf(
                                        GeneratedMutableMap.map32562map
                                )
                                ),
                                true),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("w")
                        ), listOf(
                                GeneratedMutableMap.map32563map,
                                GeneratedMutableMap.map32564map,
                                GeneratedMutableMap.map32709map
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
                                GeneratedMutableMap.map32563map,
                                GeneratedMutableMap.map32564map,
                                GeneratedMutableMap.map32709map
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
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map32984map,
                                        GeneratedMutableMap.map10287map,
                                        GeneratedMutableMap.map32985map,
                                        GeneratedMutableMap.map10289map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "p2",
                                        "o2"
                                ), listOf(
                                        GeneratedMutableMap.map10468map,
                                        GeneratedMutableMap.map10469map,
                                        GeneratedMutableMap.map33112map,
                                        GeneratedMutableMap.map10471map,
                                        GeneratedMutableMap.map33113map,
                                        GeneratedMutableMap.map10473map
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
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map33114map,
                                GeneratedMutableMap.map10477map,
                                GeneratedMutableMap.map33115map,
                                GeneratedMutableMap.map10479map
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
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map33114map,
                                GeneratedMutableMap.map10477map,
                                GeneratedMutableMap.map33115map,
                                GeneratedMutableMap.map10479map
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
                                        GeneratedMutableMap.map10366map,
                                        GeneratedMutableMap.map10367map,
                                        GeneratedMutableMap.map32986map,
                                        GeneratedMutableMap.map10369map,
                                        GeneratedMutableMap.map32987map,
                                        GeneratedMutableMap.map10371map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ), listOf(
                                        GeneratedMutableMap.map10366map,
                                        GeneratedMutableMap.map10367map,
                                        GeneratedMutableMap.map32986map,
                                        GeneratedMutableMap.map10369map,
                                        GeneratedMutableMap.map32987map,
                                        GeneratedMutableMap.map10371map
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
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map33116map,
                                GeneratedMutableMap.map10607map,
                                GeneratedMutableMap.map33117map,
                                GeneratedMutableMap.map10609map
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
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map33116map,
                                GeneratedMutableMap.map10607map,
                                GeneratedMutableMap.map33117map,
                                GeneratedMutableMap.map10609map
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
                                        GeneratedMutableMap.map34844map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34840map,
                                        GeneratedMutableMap.map34841map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34845map,
                                GeneratedMutableMap.map34846map
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
                                GeneratedMutableMap.map34845map,
                                GeneratedMutableMap.map34846map
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
                                        GeneratedMutableMap.map34842map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map34842map,
                                        GeneratedMutableMap.map34843map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map34847map,
                                GeneratedMutableMap.map34848map
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
                                GeneratedMutableMap.map34847map,
                                GeneratedMutableMap.map34848map
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
                                        GeneratedMutableMap.map34844map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34840map,
                                        GeneratedMutableMap.map34967map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34845map,
                                GeneratedMutableMap.map34968map
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
                                GeneratedMutableMap.map34845map,
                                GeneratedMutableMap.map34968map
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
                                        GeneratedMutableMap.map34842map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map34842map,
                                        GeneratedMutableMap.map34969map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map34847map,
                                GeneratedMutableMap.map34970map
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
                                GeneratedMutableMap.map34847map,
                                GeneratedMutableMap.map34970map
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
                                GeneratedMutableMap.map34844map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map34841map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map34846map
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
                                        GeneratedMutableMap.map34844map,
                                        GeneratedMutableMap.map35460map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map35462map
                                )
                                ),
                                true),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map35463map,
                                GeneratedMutableMap.map35464map
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
                                GeneratedMutableMap.map35463map,
                                GeneratedMutableMap.map35464map
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
                                        GeneratedMutableMap.map34842map,
                                        GeneratedMutableMap.map35461map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map34843map
                                )
                                ),
                                true),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("l")
                        ), listOf(
                                GeneratedMutableMap.map34848map,
                                GeneratedMutableMap.map35465map
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
                                GeneratedMutableMap.map34848map,
                                GeneratedMutableMap.map35465map
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
                                        "#_36776",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map36892map,
                                        GeneratedMutableMap.map36893map,
                                        GeneratedMutableMap.map36894map,
                                        GeneratedMutableMap.map36895map,
                                        GeneratedMutableMap.map36896map,
                                        GeneratedMutableMap.map36897map,
                                        GeneratedMutableMap.map36898map,
                                        GeneratedMutableMap.map36899map,
                                        GeneratedMutableMap.map36900map,
                                        GeneratedMutableMap.map36901map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_36776"
                                ), listOf(
                                        GeneratedMutableMap.map36912map,
                                        GeneratedMutableMap.map36913map,
                                        GeneratedMutableMap.map36914map,
                                        GeneratedMutableMap.map36915map,
                                        GeneratedMutableMap.map36916map,
                                        GeneratedMutableMap.map36917map,
                                        GeneratedMutableMap.map36918map,
                                        GeneratedMutableMap.map36919map,
                                        GeneratedMutableMap.map36920map,
                                        GeneratedMutableMap.map36921map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_36776",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36922map,
                                GeneratedMutableMap.map36923map,
                                GeneratedMutableMap.map36924map,
                                GeneratedMutableMap.map36925map,
                                GeneratedMutableMap.map36926map,
                                GeneratedMutableMap.map36927map,
                                GeneratedMutableMap.map36928map,
                                GeneratedMutableMap.map36929map,
                                GeneratedMutableMap.map36930map,
                                GeneratedMutableMap.map36931map
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
                                    graph.addData(1L, listOf("_:_36753", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                    graph.addData(1L, listOf("_:_36754", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                    graph.addData(1L, listOf("_:_36755", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                    graph.addData(1L, listOf("_:_36756", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                    graph.addData(1L, listOf("_:_36757", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                    graph.addData(1L, listOf("_:_36758", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    graph.addData(1L, listOf("_:_36759", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                    graph.addData(1L, listOf("_:_36760", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    graph.addData(1L, listOf("_:_36761", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                    graph.addData(1L, listOf("_:_36762", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_36776", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36753"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36754"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36755"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36756"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36757"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36758"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36759"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36760"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36761"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36762"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_36776", false, true, false, EIndexPattern.SPO)
                                }()
                                ,
                                false),
                        POPValues(dictionary, listOf(
                                "#_36776",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36922map,
                                GeneratedMutableMap.map36923map,
                                GeneratedMutableMap.map36924map,
                                GeneratedMutableMap.map36925map,
                                GeneratedMutableMap.map36926map,
                                GeneratedMutableMap.map36927map,
                                GeneratedMutableMap.map36928map,
                                GeneratedMutableMap.map36929map,
                                GeneratedMutableMap.map36930map,
                                GeneratedMutableMap.map36931map
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
                                        AOPVariable("#_36776"),
                                        AOPVariable("L")
                                ), listOf(
                                        GeneratedMutableMap.map36902map,
                                        GeneratedMutableMap.map36903map,
                                        GeneratedMutableMap.map36904map,
                                        GeneratedMutableMap.map36905map,
                                        GeneratedMutableMap.map36906map,
                                        GeneratedMutableMap.map36907map,
                                        GeneratedMutableMap.map36908map,
                                        GeneratedMutableMap.map36909map,
                                        GeneratedMutableMap.map36910map,
                                        GeneratedMutableMap.map36911map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("O"),
                                        AOPVariable("#_36776")
                                ), listOf(
                                        GeneratedMutableMap.map36932map,
                                        GeneratedMutableMap.map36933map,
                                        GeneratedMutableMap.map36934map,
                                        GeneratedMutableMap.map36935map,
                                        GeneratedMutableMap.map36936map,
                                        GeneratedMutableMap.map36937map,
                                        GeneratedMutableMap.map36938map,
                                        GeneratedMutableMap.map36939map,
                                        GeneratedMutableMap.map36940map,
                                        GeneratedMutableMap.map36941map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#_36776"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map36942map,
                                GeneratedMutableMap.map36943map,
                                GeneratedMutableMap.map36944map,
                                GeneratedMutableMap.map36945map,
                                GeneratedMutableMap.map36946map,
                                GeneratedMutableMap.map36947map,
                                GeneratedMutableMap.map36948map,
                                GeneratedMutableMap.map36949map,
                                GeneratedMutableMap.map36950map,
                                GeneratedMutableMap.map36951map
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
                                    graph.addData(1L, listOf("_:_36753", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                    graph.addData(1L, listOf("_:_36754", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                    graph.addData(1L, listOf("_:_36755", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                    graph.addData(1L, listOf("_:_36756", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                    graph.addData(1L, listOf("_:_36757", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                    graph.addData(1L, listOf("_:_36758", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    graph.addData(1L, listOf("_:_36759", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                    graph.addData(1L, listOf("_:_36760", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    graph.addData(1L, listOf("_:_36761", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                    graph.addData(1L, listOf("_:_36762", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("#_36776"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                                }()
                                ,
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36753"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36754"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36755"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36756"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36757"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36758"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36759"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36760"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36761"))
                                    graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36762"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.example.orghasItem>"), AOPVariable("#_36776"), graphName, false)
                                }()
                                ,
                                false),
                        LOPValues(listOf(
                                AOPVariable("#_36776"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map36942map,
                                GeneratedMutableMap.map36943map,
                                GeneratedMutableMap.map36944map,
                                GeneratedMutableMap.map36945map,
                                GeneratedMutableMap.map36946map,
                                GeneratedMutableMap.map36947map,
                                GeneratedMutableMap.map36948map,
                                GeneratedMutableMap.map36949map,
                                GeneratedMutableMap.map36950map,
                                GeneratedMutableMap.map36951map
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
                                        "#_36776",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36922map,
                                        GeneratedMutableMap.map36923map,
                                        GeneratedMutableMap.map36924map,
                                        GeneratedMutableMap.map36925map,
                                        GeneratedMutableMap.map36926map,
                                        GeneratedMutableMap.map36927map,
                                        GeneratedMutableMap.map36928map,
                                        GeneratedMutableMap.map36929map,
                                        GeneratedMutableMap.map36930map,
                                        GeneratedMutableMap.map36931map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36952map,
                                        GeneratedMutableMap.map36953map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_36776",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36922map,
                                GeneratedMutableMap.map36923map,
                                GeneratedMutableMap.map36924map,
                                GeneratedMutableMap.map36925map,
                                GeneratedMutableMap.map36926map,
                                GeneratedMutableMap.map36927map
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
                                            graph.addData(1L, listOf("_:_36753", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                            graph.addData(1L, listOf("_:_36754", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                            graph.addData(1L, listOf("_:_36755", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                            graph.addData(1L, listOf("_:_36756", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                            graph.addData(1L, listOf("_:_36757", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                            graph.addData(1L, listOf("_:_36758", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            graph.addData(1L, listOf("_:_36759", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                            graph.addData(1L, listOf("_:_36760", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            graph.addData(1L, listOf("_:_36761", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                            graph.addData(1L, listOf("_:_36762", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_36776", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36753"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36754"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36755"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36756"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36757"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36758"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36759"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36760"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36761"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36762"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_36776", false, true, false, EIndexPattern.SPO)
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
                                "#_36776",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36922map,
                                GeneratedMutableMap.map36923map,
                                GeneratedMutableMap.map36924map,
                                GeneratedMutableMap.map36925map,
                                GeneratedMutableMap.map36926map,
                                GeneratedMutableMap.map36927map
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
                                        AOPVariable("#_36776"),
                                        AOPVariable("L"),
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map36942map,
                                        GeneratedMutableMap.map36943map,
                                        GeneratedMutableMap.map36944map,
                                        GeneratedMutableMap.map36945map,
                                        GeneratedMutableMap.map36946map,
                                        GeneratedMutableMap.map36947map,
                                        GeneratedMutableMap.map36948map,
                                        GeneratedMutableMap.map36949map,
                                        GeneratedMutableMap.map36950map,
                                        GeneratedMutableMap.map36951map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map36956map,
                                        GeneratedMutableMap.map36957map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#_36776"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map36942map,
                                GeneratedMutableMap.map36943map,
                                GeneratedMutableMap.map36944map,
                                GeneratedMutableMap.map36945map,
                                GeneratedMutableMap.map36946map,
                                GeneratedMutableMap.map36947map
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
                                            graph.addData(1L, listOf("_:_36753", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                            graph.addData(1L, listOf("_:_36754", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                                            graph.addData(1L, listOf("_:_36755", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                                            graph.addData(1L, listOf("_:_36756", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                                            graph.addData(1L, listOf("_:_36757", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                                            graph.addData(1L, listOf("_:_36758", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            graph.addData(1L, listOf("_:_36759", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                                            graph.addData(1L, listOf("_:_36760", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            graph.addData(1L, listOf("_:_36761", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                                            graph.addData(1L, listOf("_:_36762", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("#_36776"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                                        }()
                                        ,
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36753"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36754"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36755"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36756"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36757"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36758"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36759"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36760"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36761"))
                                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36762"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.example.orghasItem>"), AOPVariable("#_36776"), graphName, false)
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
                                AOPVariable("#_36776"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map36942map,
                                GeneratedMutableMap.map36943map,
                                GeneratedMutableMap.map36944map,
                                GeneratedMutableMap.map36945map,
                                GeneratedMutableMap.map36946map,
                                GeneratedMutableMap.map36947map
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
                                        GeneratedMutableMap.map37154map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map37152map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37156map
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
                                GeneratedMutableMap.map37156map
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
                                        GeneratedMutableMap.map37155map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("P"),
                                        AOPVariable("L")
                                ), listOf(
                                        GeneratedMutableMap.map37153map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("F"),
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37157map
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
                                GeneratedMutableMap.map37157map
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
                                        "#_37191",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map37327map,
                                        GeneratedMutableMap.map37328map,
                                        GeneratedMutableMap.map37329map,
                                        GeneratedMutableMap.map37330map,
                                        GeneratedMutableMap.map37331map,
                                        GeneratedMutableMap.map37332map,
                                        GeneratedMutableMap.map37333map,
                                        GeneratedMutableMap.map37334map,
                                        GeneratedMutableMap.map37335map,
                                        GeneratedMutableMap.map37336map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O",
                                        "#_37191"
                                ), listOf(
                                        GeneratedMutableMap.map37307map,
                                        GeneratedMutableMap.map37308map,
                                        GeneratedMutableMap.map37309map,
                                        GeneratedMutableMap.map37310map,
                                        GeneratedMutableMap.map37311map,
                                        GeneratedMutableMap.map37312map,
                                        GeneratedMutableMap.map37313map,
                                        GeneratedMutableMap.map37314map,
                                        GeneratedMutableMap.map37315map,
                                        GeneratedMutableMap.map37316map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_37191",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37347map,
                                GeneratedMutableMap.map37348map,
                                GeneratedMutableMap.map37349map,
                                GeneratedMutableMap.map37350map,
                                GeneratedMutableMap.map37351map,
                                GeneratedMutableMap.map37352map,
                                GeneratedMutableMap.map37353map,
                                GeneratedMutableMap.map37354map,
                                GeneratedMutableMap.map37355map,
                                GeneratedMutableMap.map37356map
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
                                GeneratedMutableMap.map37347map,
                                GeneratedMutableMap.map37348map,
                                GeneratedMutableMap.map37349map,
                                GeneratedMutableMap.map37350map,
                                GeneratedMutableMap.map37351map,
                                GeneratedMutableMap.map37352map,
                                GeneratedMutableMap.map37353map,
                                GeneratedMutableMap.map37354map,
                                GeneratedMutableMap.map37355map,
                                GeneratedMutableMap.map37356map
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
                                        GeneratedMutableMap.map37337map,
                                        GeneratedMutableMap.map37338map,
                                        GeneratedMutableMap.map37339map,
                                        GeneratedMutableMap.map37340map,
                                        GeneratedMutableMap.map37341map,
                                        GeneratedMutableMap.map37342map,
                                        GeneratedMutableMap.map37343map,
                                        GeneratedMutableMap.map37344map,
                                        GeneratedMutableMap.map37345map,
                                        GeneratedMutableMap.map37346map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("O"),
                                        AOPVariable("#_37191")
                                ), listOf(
                                        GeneratedMutableMap.map37317map,
                                        GeneratedMutableMap.map37318map,
                                        GeneratedMutableMap.map37319map,
                                        GeneratedMutableMap.map37320map,
                                        GeneratedMutableMap.map37321map,
                                        GeneratedMutableMap.map37322map,
                                        GeneratedMutableMap.map37323map,
                                        GeneratedMutableMap.map37324map,
                                        GeneratedMutableMap.map37325map,
                                        GeneratedMutableMap.map37326map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#_37191"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37357map,
                                GeneratedMutableMap.map37358map,
                                GeneratedMutableMap.map37359map,
                                GeneratedMutableMap.map37360map,
                                GeneratedMutableMap.map37361map,
                                GeneratedMutableMap.map37362map,
                                GeneratedMutableMap.map37363map,
                                GeneratedMutableMap.map37364map,
                                GeneratedMutableMap.map37365map,
                                GeneratedMutableMap.map37366map
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
                                GeneratedMutableMap.map37357map,
                                GeneratedMutableMap.map37358map,
                                GeneratedMutableMap.map37359map,
                                GeneratedMutableMap.map37360map,
                                GeneratedMutableMap.map37361map,
                                GeneratedMutableMap.map37362map,
                                GeneratedMutableMap.map37363map,
                                GeneratedMutableMap.map37364map,
                                GeneratedMutableMap.map37365map,
                                GeneratedMutableMap.map37366map
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
                                        GeneratedMutableMap.map37347map,
                                        GeneratedMutableMap.map37348map,
                                        GeneratedMutableMap.map37349map,
                                        GeneratedMutableMap.map37350map,
                                        GeneratedMutableMap.map37351map,
                                        GeneratedMutableMap.map37352map,
                                        GeneratedMutableMap.map37353map,
                                        GeneratedMutableMap.map37354map,
                                        GeneratedMutableMap.map37355map,
                                        GeneratedMutableMap.map37356map
                                )
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36952map,
                                        GeneratedMutableMap.map36953map
                                )
                                ),
                                false),
                        POPValues(dictionary, listOf(
                                "#_37191",
                                "L",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37347map,
                                GeneratedMutableMap.map37348map,
                                GeneratedMutableMap.map37349map,
                                GeneratedMutableMap.map37350map,
                                GeneratedMutableMap.map37351map,
                                GeneratedMutableMap.map37352map
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
                                GeneratedMutableMap.map37347map,
                                GeneratedMutableMap.map37348map,
                                GeneratedMutableMap.map37349map,
                                GeneratedMutableMap.map37350map,
                                GeneratedMutableMap.map37351map,
                                GeneratedMutableMap.map37352map
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
                                        GeneratedMutableMap.map37357map,
                                        GeneratedMutableMap.map37358map,
                                        GeneratedMutableMap.map37359map,
                                        GeneratedMutableMap.map37360map,
                                        GeneratedMutableMap.map37361map,
                                        GeneratedMutableMap.map37362map,
                                        GeneratedMutableMap.map37363map,
                                        GeneratedMutableMap.map37364map,
                                        GeneratedMutableMap.map37365map,
                                        GeneratedMutableMap.map37366map
                                )
                                ),
                                LOPValues(listOf(
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map36956map,
                                        GeneratedMutableMap.map36957map
                                )
                                ),
                                false),
                        LOPValues(listOf(
                                AOPVariable("#_37191"),
                                AOPVariable("L"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37357map,
                                GeneratedMutableMap.map37358map,
                                GeneratedMutableMap.map37359map,
                                GeneratedMutableMap.map37360map,
                                GeneratedMutableMap.map37361map,
                                GeneratedMutableMap.map37362map
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
                                GeneratedMutableMap.map37357map,
                                GeneratedMutableMap.map37358map,
                                GeneratedMutableMap.map37359map,
                                GeneratedMutableMap.map37360map,
                                GeneratedMutableMap.map37361map,
                                GeneratedMutableMap.map37362map
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
