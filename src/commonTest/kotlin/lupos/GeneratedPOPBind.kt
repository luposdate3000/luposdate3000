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


class GeneratedPOPBindTest {
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
                        POPBind(
                                dictionary,
                                AOPVariable("O12"),
                                AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
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
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2",
                                "O12"
                        ), listOf(
                                GeneratedMutableMap.map2776map,
                                GeneratedMutableMap.map2777map,
                                GeneratedMutableMap.map2778map,
                                GeneratedMutableMap.map2779map,
                                GeneratedMutableMap.map2780map,
                                GeneratedMutableMap.map2781map,
                                GeneratedMutableMap.map2782map,
                                GeneratedMutableMap.map2783map,
                                GeneratedMutableMap.map2784map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("O12"),
                                AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2",
                                "O12"
                        ), listOf(
                                GeneratedMutableMap.map2776map,
                                GeneratedMutableMap.map2777map,
                                GeneratedMutableMap.map2778map,
                                GeneratedMutableMap.map2779map,
                                GeneratedMutableMap.map2780map,
                                GeneratedMutableMap.map2781map,
                                GeneratedMutableMap.map2782map,
                                GeneratedMutableMap.map2783map,
                                GeneratedMutableMap.map2784map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("O12"),
                                AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O1"),
                                AOPVariable("O2"),
                                AOPVariable("O12")
                        ), listOf(
                                GeneratedMutableMap.map2785map,
                                GeneratedMutableMap.map2786map,
                                GeneratedMutableMap.map2787map,
                                GeneratedMutableMap.map2788map,
                                GeneratedMutableMap.map2789map,
                                GeneratedMutableMap.map2790map,
                                GeneratedMutableMap.map2791map,
                                GeneratedMutableMap.map2792map,
                                GeneratedMutableMap.map2793map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("O12"),
                                AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O1"),
                                AOPVariable("O2"),
                                AOPVariable("O12")
                        ), listOf(
                                GeneratedMutableMap.map2785map,
                                GeneratedMutableMap.map2786map,
                                GeneratedMutableMap.map2787map,
                                GeneratedMutableMap.map2788map,
                                GeneratedMutableMap.map2789map,
                                GeneratedMutableMap.map2790map,
                                GeneratedMutableMap.map2791map,
                                GeneratedMutableMap.map2792map,
                                GeneratedMutableMap.map2793map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map,
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(10), AOPVariable("o")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map,
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map6728map,
                                GeneratedMutableMap.map6729map,
                                GeneratedMutableMap.map6730map,
                                GeneratedMutableMap.map6731map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(10), AOPVariable("o")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map6728map,
                                GeneratedMutableMap.map6729map,
                                GeneratedMutableMap.map6730map,
                                GeneratedMutableMap.map6731map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z2"),
                                AOPAddition(AOPInteger(100), AOPVariable("o")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map6724map,
                                        GeneratedMutableMap.map6725map,
                                        GeneratedMutableMap.map6726map,
                                        GeneratedMutableMap.map6727map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6883map,
                                GeneratedMutableMap.map6884map,
                                GeneratedMutableMap.map6885map,
                                GeneratedMutableMap.map6886map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z2"),
                                AOPAddition(AOPInteger(100), AOPVariable("o")),
                                POPBind(
                                        dictionary,
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6883map,
                                GeneratedMutableMap.map6884map,
                                GeneratedMutableMap.map6885map,
                                GeneratedMutableMap.map6886map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z2"),
                                AOPAddition(AOPInteger(100), AOPVariable("o")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map6728map,
                                        GeneratedMutableMap.map6729map,
                                        GeneratedMutableMap.map6730map,
                                        GeneratedMutableMap.map6731map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z"),
                                AOPVariable("z2")
                        ), listOf(
                                GeneratedMutableMap.map6887map,
                                GeneratedMutableMap.map6888map,
                                GeneratedMutableMap.map6889map,
                                GeneratedMutableMap.map6890map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z2"),
                                AOPAddition(AOPInteger(100), AOPVariable("o")),
                                LOPBind(
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                        }()

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z"),
                                AOPVariable("z2")
                        ), listOf(
                                GeneratedMutableMap.map6887map,
                                GeneratedMutableMap.map6888map,
                                GeneratedMutableMap.map6889map,
                                GeneratedMutableMap.map6890map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPBind(
                        dictionary,
                        AOPVariable("z"),
                        AOPAddition(AOPInteger(1), AOPVariable("o")),
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
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7211map,
                            GeneratedMutableMap.map7212map,
                            GeneratedMutableMap.map7213map,
                            GeneratedMutableMap.map7214map,
                            GeneratedMutableMap.map7199map,
                            GeneratedMutableMap.map7215map,
                            GeneratedMutableMap.map7216map,
                            GeneratedMutableMap.map7217map,
                            GeneratedMutableMap.map7218map,
                            GeneratedMutableMap.map7204map,
                            GeneratedMutableMap.map7219map,
                            GeneratedMutableMap.map7220map,
                            GeneratedMutableMap.map7221map,
                            GeneratedMutableMap.map7222map,
                            GeneratedMutableMap.map7209map,
                            GeneratedMutableMap.map7223map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(1), AOPVariable("o")),
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7537map,
                                GeneratedMutableMap.map7538map,
                                GeneratedMutableMap.map7539map,
                                GeneratedMutableMap.map7540map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(1), AOPVariable("o")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7537map,
                                GeneratedMutableMap.map7538map,
                                GeneratedMutableMap.map7539map,
                                GeneratedMutableMap.map7540map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(1), AOPVariable("o")),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7541map,
                                GeneratedMutableMap.map7542map,
                                GeneratedMutableMap.map7543map,
                                GeneratedMutableMap.map7544map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(1), AOPVariable("o")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7541map,
                                GeneratedMutableMap.map7542map,
                                GeneratedMutableMap.map7543map,
                                GeneratedMutableMap.map7544map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(1), AOPVariable("o")),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5895map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7719map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(2), AOPVariable("o")),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5895map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7719map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPUndef(),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map8139map,
                                        GeneratedMutableMap.map8140map,
                                        GeneratedMutableMap.map8141map,
                                        GeneratedMutableMap.map8142map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8147map,
                                GeneratedMutableMap.map8148map,
                                GeneratedMutableMap.map8149map,
                                GeneratedMutableMap.map8150map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPUndef(),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/p>", "v", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8147map,
                                GeneratedMutableMap.map8148map,
                                GeneratedMutableMap.map8149map,
                                GeneratedMutableMap.map8150map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPUndef(),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("v")
                                ), listOf(
                                        GeneratedMutableMap.map8143map,
                                        GeneratedMutableMap.map8144map,
                                        GeneratedMutableMap.map8145map,
                                        GeneratedMutableMap.map8146map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map8151map,
                                GeneratedMutableMap.map8152map,
                                GeneratedMutableMap.map8153map,
                                GeneratedMutableMap.map8154map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPUndef(),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/p>"), AOPVariable("v"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map8151map,
                                GeneratedMutableMap.map8152map,
                                GeneratedMutableMap.map8153map,
                                GeneratedMutableMap.map8154map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPInteger(4),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map8139map,
                                        GeneratedMutableMap.map8140map,
                                        GeneratedMutableMap.map8141map,
                                        GeneratedMutableMap.map8142map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8311map,
                                GeneratedMutableMap.map8312map,
                                GeneratedMutableMap.map8313map,
                                GeneratedMutableMap.map8314map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPInteger(4),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/p>", "v", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8311map,
                                GeneratedMutableMap.map8312map,
                                GeneratedMutableMap.map8313map,
                                GeneratedMutableMap.map8314map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPInteger(4),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("v")
                                ), listOf(
                                        GeneratedMutableMap.map8143map,
                                        GeneratedMutableMap.map8144map,
                                        GeneratedMutableMap.map8145map,
                                        GeneratedMutableMap.map8146map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map8315map,
                                GeneratedMutableMap.map8316map,
                                GeneratedMutableMap.map8317map,
                                GeneratedMutableMap.map8318map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPInteger(4),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/p>"), AOPVariable("v"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map8315map,
                                GeneratedMutableMap.map8316map,
                                GeneratedMutableMap.map8317map,
                                GeneratedMutableMap.map8318map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map,
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map6728map,
                                GeneratedMutableMap.map6729map,
                                GeneratedMutableMap.map6730map,
                                GeneratedMutableMap.map6731map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z2"),
                                AOPAddition(AOPInteger(100), AOPVariable("o")),
                                POPBind(
                                        dictionary,
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                                false)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6883map,
                                GeneratedMutableMap.map6884map,
                                GeneratedMutableMap.map6885map,
                                GeneratedMutableMap.map6886map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z2"),
                                AOPAddition(AOPInteger(100), AOPVariable("o")),
                                LOPBind(
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                                false)

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z"),
                                AOPVariable("z2")
                        ), listOf(
                                GeneratedMutableMap.map6887map,
                                GeneratedMutableMap.map6888map,
                                GeneratedMutableMap.map6889map,
                                GeneratedMutableMap.map6890map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(1), AOPVariable("o")),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7537map,
                                GeneratedMutableMap.map7538map,
                                GeneratedMutableMap.map7539map,
                                GeneratedMutableMap.map7540map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("z"),
                                AOPAddition(AOPInteger(1), AOPVariable("o")),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7541map,
                                GeneratedMutableMap.map7542map,
                                GeneratedMutableMap.map7543map,
                                GeneratedMutableMap.map7544map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22697map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22710map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                POPFilter(
                                        dictionary,
                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/str>", "str", false, true, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22710map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map22704map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map22711map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                LOPFilter(
                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str"), graphName, false)
                                        }()

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map22711map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22697map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22984map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                POPFilter(
                                        dictionary,
                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/str>", "str", false, true, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22984map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map22704map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map22985map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                LOPFilter(
                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str"), graphName, false)
                                        }()

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map22985map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPVariable("o"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map23159map,
                                        GeneratedMutableMap.map23160map,
                                        GeneratedMutableMap.map23161map,
                                        GeneratedMutableMap.map23162map,
                                        GeneratedMutableMap.map23163map,
                                        GeneratedMutableMap.map23164map,
                                        GeneratedMutableMap.map23165map,
                                        GeneratedMutableMap.map23166map,
                                        GeneratedMutableMap.map23167map,
                                        GeneratedMutableMap.map23168map,
                                        GeneratedMutableMap.map23169map,
                                        GeneratedMutableMap.map23170map,
                                        GeneratedMutableMap.map23171map,
                                        GeneratedMutableMap.map23172map,
                                        GeneratedMutableMap.map23173map,
                                        GeneratedMutableMap.map23174map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23191map,
                                GeneratedMutableMap.map23192map,
                                GeneratedMutableMap.map23193map,
                                GeneratedMutableMap.map23194map,
                                GeneratedMutableMap.map23195map,
                                GeneratedMutableMap.map23196map,
                                GeneratedMutableMap.map23197map,
                                GeneratedMutableMap.map23198map,
                                GeneratedMutableMap.map23199map,
                                GeneratedMutableMap.map23200map,
                                GeneratedMutableMap.map23201map,
                                GeneratedMutableMap.map23202map,
                                GeneratedMutableMap.map23203map,
                                GeneratedMutableMap.map23204map,
                                GeneratedMutableMap.map23205map,
                                GeneratedMutableMap.map23206map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPVariable("o"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23191map,
                                GeneratedMutableMap.map23192map,
                                GeneratedMutableMap.map23193map,
                                GeneratedMutableMap.map23194map,
                                GeneratedMutableMap.map23195map,
                                GeneratedMutableMap.map23196map,
                                GeneratedMutableMap.map23197map,
                                GeneratedMutableMap.map23198map,
                                GeneratedMutableMap.map23199map,
                                GeneratedMutableMap.map23200map,
                                GeneratedMutableMap.map23201map,
                                GeneratedMutableMap.map23202map,
                                GeneratedMutableMap.map23203map,
                                GeneratedMutableMap.map23204map,
                                GeneratedMutableMap.map23205map,
                                GeneratedMutableMap.map23206map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPVariable("o"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map23175map,
                                        GeneratedMutableMap.map23176map,
                                        GeneratedMutableMap.map23177map,
                                        GeneratedMutableMap.map23178map,
                                        GeneratedMutableMap.map23179map,
                                        GeneratedMutableMap.map23180map,
                                        GeneratedMutableMap.map23181map,
                                        GeneratedMutableMap.map23182map,
                                        GeneratedMutableMap.map23183map,
                                        GeneratedMutableMap.map23184map,
                                        GeneratedMutableMap.map23185map,
                                        GeneratedMutableMap.map23186map,
                                        GeneratedMutableMap.map23187map,
                                        GeneratedMutableMap.map23188map,
                                        GeneratedMutableMap.map23189map,
                                        GeneratedMutableMap.map23190map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23207map,
                                GeneratedMutableMap.map23208map,
                                GeneratedMutableMap.map23209map,
                                GeneratedMutableMap.map23210map,
                                GeneratedMutableMap.map23211map,
                                GeneratedMutableMap.map23212map,
                                GeneratedMutableMap.map23213map,
                                GeneratedMutableMap.map23214map,
                                GeneratedMutableMap.map23215map,
                                GeneratedMutableMap.map23216map,
                                GeneratedMutableMap.map23217map,
                                GeneratedMutableMap.map23218map,
                                GeneratedMutableMap.map23219map,
                                GeneratedMutableMap.map23220map,
                                GeneratedMutableMap.map23221map,
                                GeneratedMutableMap.map23222map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str1"),
                                AOPBuildInCallSTRDT(AOPVariable("o"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23207map,
                                GeneratedMutableMap.map23208map,
                                GeneratedMutableMap.map23209map,
                                GeneratedMutableMap.map23210map,
                                GeneratedMutableMap.map23211map,
                                GeneratedMutableMap.map23212map,
                                GeneratedMutableMap.map23213map,
                                GeneratedMutableMap.map23214map,
                                GeneratedMutableMap.map23215map,
                                GeneratedMutableMap.map23216map,
                                GeneratedMutableMap.map23217map,
                                GeneratedMutableMap.map23218map,
                                GeneratedMutableMap.map23219map,
                                GeneratedMutableMap.map23220map,
                                GeneratedMutableMap.map23221map,
                                GeneratedMutableMap.map23222map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("s2"),
                                AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22697map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23505map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("s2"),
                                AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")),
                                POPFilter(
                                        dictionary,
                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/str>", "str", false, true, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23505map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("s2"),
                                AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map22704map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map22711map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("s2"),
                                AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")),
                                LOPFilter(
                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str"), graphName, false)
                                        }()

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map22711map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("s2"),
                                AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22697map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23774map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("s2"),
                                AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")),
                                POPFilter(
                                        dictionary,
                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/str>", "str", false, true, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23774map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("s2"),
                                AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map22704map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map23775map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("s2"),
                                AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")),
                                LOPFilter(
                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str"), graphName, false)
                                        }()

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map23775map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str1"),
                                AOPBuildInCallSTRLANG(AOPVariable("o"), AOPSimpleLiteral("\"", "en-US")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map23159map,
                                        GeneratedMutableMap.map23160map,
                                        GeneratedMutableMap.map23161map,
                                        GeneratedMutableMap.map23162map,
                                        GeneratedMutableMap.map23163map,
                                        GeneratedMutableMap.map23164map,
                                        GeneratedMutableMap.map23165map,
                                        GeneratedMutableMap.map23166map,
                                        GeneratedMutableMap.map23167map,
                                        GeneratedMutableMap.map23168map,
                                        GeneratedMutableMap.map23169map,
                                        GeneratedMutableMap.map23170map,
                                        GeneratedMutableMap.map23171map,
                                        GeneratedMutableMap.map23172map,
                                        GeneratedMutableMap.map23173map,
                                        GeneratedMutableMap.map23174map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23191map,
                                GeneratedMutableMap.map23192map,
                                GeneratedMutableMap.map23193map,
                                GeneratedMutableMap.map23194map,
                                GeneratedMutableMap.map23195map,
                                GeneratedMutableMap.map23196map,
                                GeneratedMutableMap.map23197map,
                                GeneratedMutableMap.map23198map,
                                GeneratedMutableMap.map23199map,
                                GeneratedMutableMap.map23946map,
                                GeneratedMutableMap.map23201map,
                                GeneratedMutableMap.map23947map,
                                GeneratedMutableMap.map23948map,
                                GeneratedMutableMap.map23949map,
                                GeneratedMutableMap.map23205map,
                                GeneratedMutableMap.map23206map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str1"),
                                AOPBuildInCallSTRLANG(AOPVariable("o"), AOPSimpleLiteral("\"", "en-US")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23191map,
                                GeneratedMutableMap.map23192map,
                                GeneratedMutableMap.map23193map,
                                GeneratedMutableMap.map23194map,
                                GeneratedMutableMap.map23195map,
                                GeneratedMutableMap.map23196map,
                                GeneratedMutableMap.map23197map,
                                GeneratedMutableMap.map23198map,
                                GeneratedMutableMap.map23199map,
                                GeneratedMutableMap.map23946map,
                                GeneratedMutableMap.map23201map,
                                GeneratedMutableMap.map23947map,
                                GeneratedMutableMap.map23948map,
                                GeneratedMutableMap.map23949map,
                                GeneratedMutableMap.map23205map,
                                GeneratedMutableMap.map23206map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str1"),
                                AOPBuildInCallSTRLANG(AOPVariable("o"), AOPSimpleLiteral("\"", "en-US")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map23175map,
                                        GeneratedMutableMap.map23176map,
                                        GeneratedMutableMap.map23177map,
                                        GeneratedMutableMap.map23178map,
                                        GeneratedMutableMap.map23179map,
                                        GeneratedMutableMap.map23180map,
                                        GeneratedMutableMap.map23181map,
                                        GeneratedMutableMap.map23182map,
                                        GeneratedMutableMap.map23183map,
                                        GeneratedMutableMap.map23184map,
                                        GeneratedMutableMap.map23185map,
                                        GeneratedMutableMap.map23186map,
                                        GeneratedMutableMap.map23187map,
                                        GeneratedMutableMap.map23188map,
                                        GeneratedMutableMap.map23189map,
                                        GeneratedMutableMap.map23190map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23207map,
                                GeneratedMutableMap.map23208map,
                                GeneratedMutableMap.map23209map,
                                GeneratedMutableMap.map23210map,
                                GeneratedMutableMap.map23211map,
                                GeneratedMutableMap.map23212map,
                                GeneratedMutableMap.map23213map,
                                GeneratedMutableMap.map23214map,
                                GeneratedMutableMap.map23215map,
                                GeneratedMutableMap.map23950map,
                                GeneratedMutableMap.map23217map,
                                GeneratedMutableMap.map23951map,
                                GeneratedMutableMap.map23952map,
                                GeneratedMutableMap.map23953map,
                                GeneratedMutableMap.map23221map,
                                GeneratedMutableMap.map23222map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str1"),
                                AOPBuildInCallSTRLANG(AOPVariable("o"), AOPSimpleLiteral("\"", "en-US")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/str>", "\"bar\"@en"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/str>", "\"BAZ\""))
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    graph.addData(1L, listOf("<http://example.org/s5>", "<http://example.org/str>", "\"100%\""))
                                    graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23207map,
                                GeneratedMutableMap.map23208map,
                                GeneratedMutableMap.map23209map,
                                GeneratedMutableMap.map23210map,
                                GeneratedMutableMap.map23211map,
                                GeneratedMutableMap.map23212map,
                                GeneratedMutableMap.map23213map,
                                GeneratedMutableMap.map23214map,
                                GeneratedMutableMap.map23215map,
                                GeneratedMutableMap.map23950map,
                                GeneratedMutableMap.map23217map,
                                GeneratedMutableMap.map23951map,
                                GeneratedMutableMap.map23952map,
                                GeneratedMutableMap.map23953map,
                                GeneratedMutableMap.map23221map,
                                GeneratedMutableMap.map23222map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("ceil"),
                                AOPBuildInCallCEIL(AOPVariable("num")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map24156map,
                                        GeneratedMutableMap.map24157map,
                                        GeneratedMutableMap.map24158map,
                                        GeneratedMutableMap.map24159map,
                                        GeneratedMutableMap.map24160map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "ceil"
                        ), listOf(
                                GeneratedMutableMap.map24356map,
                                GeneratedMutableMap.map24357map,
                                GeneratedMutableMap.map24358map,
                                GeneratedMutableMap.map24359map,
                                GeneratedMutableMap.map24360map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("ceil"),
                                AOPBuildInCallCEIL(AOPVariable("num")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/num>", "num", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "ceil"
                        ), listOf(
                                GeneratedMutableMap.map24356map,
                                GeneratedMutableMap.map24357map,
                                GeneratedMutableMap.map24358map,
                                GeneratedMutableMap.map24359map,
                                GeneratedMutableMap.map24360map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("ceil"),
                                AOPBuildInCallCEIL(AOPVariable("num")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ), listOf(
                                        GeneratedMutableMap.map24161map,
                                        GeneratedMutableMap.map24162map,
                                        GeneratedMutableMap.map24163map,
                                        GeneratedMutableMap.map24164map,
                                        GeneratedMutableMap.map24165map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("ceil")
                        ), listOf(
                                GeneratedMutableMap.map24361map,
                                GeneratedMutableMap.map24362map,
                                GeneratedMutableMap.map24363map,
                                GeneratedMutableMap.map24364map,
                                GeneratedMutableMap.map24365map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("ceil"),
                                AOPBuildInCallCEIL(AOPVariable("num")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/num>"), AOPVariable("num"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("ceil")
                        ), listOf(
                                GeneratedMutableMap.map24361map,
                                GeneratedMutableMap.map24362map,
                                GeneratedMutableMap.map24363map,
                                GeneratedMutableMap.map24364map,
                                GeneratedMutableMap.map24365map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("floor"),
                                AOPBuildInCallFLOOR(AOPVariable("num")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map24156map,
                                        GeneratedMutableMap.map24157map,
                                        GeneratedMutableMap.map24158map,
                                        GeneratedMutableMap.map24159map,
                                        GeneratedMutableMap.map24160map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "floor"
                        ), listOf(
                                GeneratedMutableMap.map24449map,
                                GeneratedMutableMap.map24450map,
                                GeneratedMutableMap.map24451map,
                                GeneratedMutableMap.map24452map,
                                GeneratedMutableMap.map24453map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("floor"),
                                AOPBuildInCallFLOOR(AOPVariable("num")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/num>", "num", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "floor"
                        ), listOf(
                                GeneratedMutableMap.map24449map,
                                GeneratedMutableMap.map24450map,
                                GeneratedMutableMap.map24451map,
                                GeneratedMutableMap.map24452map,
                                GeneratedMutableMap.map24453map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("floor"),
                                AOPBuildInCallFLOOR(AOPVariable("num")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ), listOf(
                                        GeneratedMutableMap.map24161map,
                                        GeneratedMutableMap.map24162map,
                                        GeneratedMutableMap.map24163map,
                                        GeneratedMutableMap.map24164map,
                                        GeneratedMutableMap.map24165map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("floor")
                        ), listOf(
                                GeneratedMutableMap.map24361map,
                                GeneratedMutableMap.map24454map,
                                GeneratedMutableMap.map24455map,
                                GeneratedMutableMap.map24364map,
                                GeneratedMutableMap.map24456map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("floor"),
                                AOPBuildInCallFLOOR(AOPVariable("num")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/num>"), AOPVariable("num"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("floor")
                        ), listOf(
                                GeneratedMutableMap.map24361map,
                                GeneratedMutableMap.map24454map,
                                GeneratedMutableMap.map24455map,
                                GeneratedMutableMap.map24364map,
                                GeneratedMutableMap.map24456map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("round"),
                                AOPBuildInCallROUND(AOPVariable("num")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map24156map,
                                        GeneratedMutableMap.map24157map,
                                        GeneratedMutableMap.map24158map,
                                        GeneratedMutableMap.map24159map,
                                        GeneratedMutableMap.map24160map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "round"
                        ), listOf(
                                GeneratedMutableMap.map24540map,
                                GeneratedMutableMap.map24541map,
                                GeneratedMutableMap.map24542map,
                                GeneratedMutableMap.map24543map,
                                GeneratedMutableMap.map24544map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("round"),
                                AOPBuildInCallROUND(AOPVariable("num")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/num>", "num", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "round"
                        ), listOf(
                                GeneratedMutableMap.map24540map,
                                GeneratedMutableMap.map24541map,
                                GeneratedMutableMap.map24542map,
                                GeneratedMutableMap.map24543map,
                                GeneratedMutableMap.map24544map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("round"),
                                AOPBuildInCallROUND(AOPVariable("num")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ), listOf(
                                        GeneratedMutableMap.map24161map,
                                        GeneratedMutableMap.map24162map,
                                        GeneratedMutableMap.map24163map,
                                        GeneratedMutableMap.map24164map,
                                        GeneratedMutableMap.map24165map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("round")
                        ), listOf(
                                GeneratedMutableMap.map24361map,
                                GeneratedMutableMap.map24454map,
                                GeneratedMutableMap.map24455map,
                                GeneratedMutableMap.map24364map,
                                GeneratedMutableMap.map24365map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("round"),
                                AOPBuildInCallROUND(AOPVariable("num")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/n1>", "<http://example.org/num>", "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n2>", "<http://example.org/num>", "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n3>", "<http://example.org/num>", "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    graph.addData(1L, listOf("<http://example.org/n4>", "<http://example.org/num>", "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://example.org/n5>", "<http://example.org/num>", "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/num>"), AOPVariable("num"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("round")
                        ), listOf(
                                GeneratedMutableMap.map24361map,
                                GeneratedMutableMap.map24454map,
                                GeneratedMutableMap.map24455map,
                                GeneratedMutableMap.map24364map,
                                GeneratedMutableMap.map24365map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str"),
                                AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                                POPValues(dictionary, listOf(
                                        "str1",
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map24643map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24645map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str"),
                                AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24645map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str"),
                                AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                                LOPValues(listOf(
                                        AOPVariable("str1"),
                                        AOPVariable("str2")
                                ), listOf(
                                        GeneratedMutableMap.map24644map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("str1"),
                                AOPVariable("str2"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map24646map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str"),
                                AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("str1"),
                                AOPVariable("str2"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map24646map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str"),
                                AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
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
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map25358map,
                                GeneratedMutableMap.map25359map,
                                GeneratedMutableMap.map25360map,
                                GeneratedMutableMap.map25361map,
                                GeneratedMutableMap.map25362map,
                                GeneratedMutableMap.map25363map,
                                GeneratedMutableMap.map25364map,
                                GeneratedMutableMap.map25365map,
                                GeneratedMutableMap.map25366map,
                                GeneratedMutableMap.map25367map,
                                GeneratedMutableMap.map25368map,
                                GeneratedMutableMap.map25369map,
                                GeneratedMutableMap.map25370map,
                                GeneratedMutableMap.map25371map,
                                GeneratedMutableMap.map25372map,
                                GeneratedMutableMap.map25373map,
                                GeneratedMutableMap.map25374map,
                                GeneratedMutableMap.map25375map,
                                GeneratedMutableMap.map25376map,
                                GeneratedMutableMap.map25377map,
                                GeneratedMutableMap.map25378map,
                                GeneratedMutableMap.map25379map,
                                GeneratedMutableMap.map25380map,
                                GeneratedMutableMap.map25381map,
                                GeneratedMutableMap.map25382map,
                                GeneratedMutableMap.map25383map,
                                GeneratedMutableMap.map25384map,
                                GeneratedMutableMap.map25385map,
                                GeneratedMutableMap.map25386map,
                                GeneratedMutableMap.map25387map,
                                GeneratedMutableMap.map25388map,
                                GeneratedMutableMap.map25389map,
                                GeneratedMutableMap.map25390map,
                                GeneratedMutableMap.map25391map,
                                GeneratedMutableMap.map25392map,
                                GeneratedMutableMap.map25393map,
                                GeneratedMutableMap.map25394map,
                                GeneratedMutableMap.map25395map,
                                GeneratedMutableMap.map25396map,
                                GeneratedMutableMap.map25397map,
                                GeneratedMutableMap.map25398map,
                                GeneratedMutableMap.map25399map,
                                GeneratedMutableMap.map25400map,
                                GeneratedMutableMap.map25401map,
                                GeneratedMutableMap.map25402map,
                                GeneratedMutableMap.map25403map,
                                GeneratedMutableMap.map25404map,
                                GeneratedMutableMap.map25405map,
                                GeneratedMutableMap.map25406map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("str"),
                                AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map25358map,
                                GeneratedMutableMap.map25359map,
                                GeneratedMutableMap.map25360map,
                                GeneratedMutableMap.map25361map,
                                GeneratedMutableMap.map25362map,
                                GeneratedMutableMap.map25363map,
                                GeneratedMutableMap.map25364map,
                                GeneratedMutableMap.map25365map,
                                GeneratedMutableMap.map25366map,
                                GeneratedMutableMap.map25367map,
                                GeneratedMutableMap.map25368map,
                                GeneratedMutableMap.map25369map,
                                GeneratedMutableMap.map25370map,
                                GeneratedMutableMap.map25371map,
                                GeneratedMutableMap.map25372map,
                                GeneratedMutableMap.map25373map,
                                GeneratedMutableMap.map25374map,
                                GeneratedMutableMap.map25375map,
                                GeneratedMutableMap.map25376map,
                                GeneratedMutableMap.map25377map,
                                GeneratedMutableMap.map25378map,
                                GeneratedMutableMap.map25379map,
                                GeneratedMutableMap.map25380map,
                                GeneratedMutableMap.map25381map,
                                GeneratedMutableMap.map25382map,
                                GeneratedMutableMap.map25383map,
                                GeneratedMutableMap.map25384map,
                                GeneratedMutableMap.map25385map,
                                GeneratedMutableMap.map25386map,
                                GeneratedMutableMap.map25387map,
                                GeneratedMutableMap.map25388map,
                                GeneratedMutableMap.map25389map,
                                GeneratedMutableMap.map25390map,
                                GeneratedMutableMap.map25391map,
                                GeneratedMutableMap.map25392map,
                                GeneratedMutableMap.map25393map,
                                GeneratedMutableMap.map25394map,
                                GeneratedMutableMap.map25395map,
                                GeneratedMutableMap.map25396map,
                                GeneratedMutableMap.map25397map,
                                GeneratedMutableMap.map25398map,
                                GeneratedMutableMap.map25399map,
                                GeneratedMutableMap.map25400map,
                                GeneratedMutableMap.map25401map,
                                GeneratedMutableMap.map25402map,
                                GeneratedMutableMap.map25403map,
                                GeneratedMutableMap.map25404map,
                                GeneratedMutableMap.map25405map,
                                GeneratedMutableMap.map25406map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str"),
                                AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("str1"),
                                AOPVariable("s2"),
                                AOPVariable("str2"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map25407map,
                                GeneratedMutableMap.map25408map,
                                GeneratedMutableMap.map25409map,
                                GeneratedMutableMap.map25410map,
                                GeneratedMutableMap.map25411map,
                                GeneratedMutableMap.map25412map,
                                GeneratedMutableMap.map25413map,
                                GeneratedMutableMap.map25414map,
                                GeneratedMutableMap.map25415map,
                                GeneratedMutableMap.map25416map,
                                GeneratedMutableMap.map25417map,
                                GeneratedMutableMap.map25418map,
                                GeneratedMutableMap.map25419map,
                                GeneratedMutableMap.map25420map,
                                GeneratedMutableMap.map25421map,
                                GeneratedMutableMap.map25422map,
                                GeneratedMutableMap.map25423map,
                                GeneratedMutableMap.map25424map,
                                GeneratedMutableMap.map25425map,
                                GeneratedMutableMap.map25426map,
                                GeneratedMutableMap.map25427map,
                                GeneratedMutableMap.map25428map,
                                GeneratedMutableMap.map25429map,
                                GeneratedMutableMap.map25430map,
                                GeneratedMutableMap.map25431map,
                                GeneratedMutableMap.map25432map,
                                GeneratedMutableMap.map25433map,
                                GeneratedMutableMap.map25434map,
                                GeneratedMutableMap.map25435map,
                                GeneratedMutableMap.map25436map,
                                GeneratedMutableMap.map25437map,
                                GeneratedMutableMap.map25438map,
                                GeneratedMutableMap.map25439map,
                                GeneratedMutableMap.map25440map,
                                GeneratedMutableMap.map25441map,
                                GeneratedMutableMap.map25442map,
                                GeneratedMutableMap.map25443map,
                                GeneratedMutableMap.map25444map,
                                GeneratedMutableMap.map25445map,
                                GeneratedMutableMap.map25446map,
                                GeneratedMutableMap.map25447map,
                                GeneratedMutableMap.map25448map,
                                GeneratedMutableMap.map25449map,
                                GeneratedMutableMap.map25450map,
                                GeneratedMutableMap.map25451map,
                                GeneratedMutableMap.map25452map,
                                GeneratedMutableMap.map25453map,
                                GeneratedMutableMap.map25454map,
                                GeneratedMutableMap.map25455map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("str"),
                                AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("str1"),
                                AOPVariable("s2"),
                                AOPVariable("str2"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map25407map,
                                GeneratedMutableMap.map25408map,
                                GeneratedMutableMap.map25409map,
                                GeneratedMutableMap.map25410map,
                                GeneratedMutableMap.map25411map,
                                GeneratedMutableMap.map25412map,
                                GeneratedMutableMap.map25413map,
                                GeneratedMutableMap.map25414map,
                                GeneratedMutableMap.map25415map,
                                GeneratedMutableMap.map25416map,
                                GeneratedMutableMap.map25417map,
                                GeneratedMutableMap.map25418map,
                                GeneratedMutableMap.map25419map,
                                GeneratedMutableMap.map25420map,
                                GeneratedMutableMap.map25421map,
                                GeneratedMutableMap.map25422map,
                                GeneratedMutableMap.map25423map,
                                GeneratedMutableMap.map25424map,
                                GeneratedMutableMap.map25425map,
                                GeneratedMutableMap.map25426map,
                                GeneratedMutableMap.map25427map,
                                GeneratedMutableMap.map25428map,
                                GeneratedMutableMap.map25429map,
                                GeneratedMutableMap.map25430map,
                                GeneratedMutableMap.map25431map,
                                GeneratedMutableMap.map25432map,
                                GeneratedMutableMap.map25433map,
                                GeneratedMutableMap.map25434map,
                                GeneratedMutableMap.map25435map,
                                GeneratedMutableMap.map25436map,
                                GeneratedMutableMap.map25437map,
                                GeneratedMutableMap.map25438map,
                                GeneratedMutableMap.map25439map,
                                GeneratedMutableMap.map25440map,
                                GeneratedMutableMap.map25441map,
                                GeneratedMutableMap.map25442map,
                                GeneratedMutableMap.map25443map,
                                GeneratedMutableMap.map25444map,
                                GeneratedMutableMap.map25445map,
                                GeneratedMutableMap.map25446map,
                                GeneratedMutableMap.map25447map,
                                GeneratedMutableMap.map25448map,
                                GeneratedMutableMap.map25449map,
                                GeneratedMutableMap.map25450map,
                                GeneratedMutableMap.map25451map,
                                GeneratedMutableMap.map25452map,
                                GeneratedMutableMap.map25453map,
                                GeneratedMutableMap.map25454map,
                                GeneratedMutableMap.map25455map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("len"),
                                AOPBuildInCallSTRLEN(AOPVariable("str")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22696map,
                                        GeneratedMutableMap.map22697map,
                                        GeneratedMutableMap.map22698map,
                                        GeneratedMutableMap.map22699map,
                                        GeneratedMutableMap.map22700map,
                                        GeneratedMutableMap.map22701map,
                                        GeneratedMutableMap.map22702map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map25690map,
                                GeneratedMutableMap.map25691map,
                                GeneratedMutableMap.map25692map,
                                GeneratedMutableMap.map25693map,
                                GeneratedMutableMap.map25694map,
                                GeneratedMutableMap.map25695map,
                                GeneratedMutableMap.map25696map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("len"),
                                AOPBuildInCallSTRLEN(AOPVariable("str")),
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
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/str>", "str", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map25690map,
                                GeneratedMutableMap.map25691map,
                                GeneratedMutableMap.map25692map,
                                GeneratedMutableMap.map25693map,
                                GeneratedMutableMap.map25694map,
                                GeneratedMutableMap.map25695map,
                                GeneratedMutableMap.map25696map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("len"),
                                AOPBuildInCallSTRLEN(AOPVariable("str")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map22703map,
                                        GeneratedMutableMap.map22704map,
                                        GeneratedMutableMap.map22705map,
                                        GeneratedMutableMap.map22706map,
                                        GeneratedMutableMap.map22707map,
                                        GeneratedMutableMap.map22708map,
                                        GeneratedMutableMap.map22709map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("len")
                        ), listOf(
                                GeneratedMutableMap.map25697map,
                                GeneratedMutableMap.map25698map,
                                GeneratedMutableMap.map25699map,
                                GeneratedMutableMap.map25700map,
                                GeneratedMutableMap.map25701map,
                                GeneratedMutableMap.map25702map,
                                GeneratedMutableMap.map25703map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("len"),
                                AOPBuildInCallSTRLEN(AOPVariable("str")),
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
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("len")
                        ), listOf(
                                GeneratedMutableMap.map25697map,
                                GeneratedMutableMap.map25698map,
                                GeneratedMutableMap.map25699map,
                                GeneratedMutableMap.map25700map,
                                GeneratedMutableMap.map25701map,
                                GeneratedMutableMap.map25702map,
                                GeneratedMutableMap.map25703map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("ustr"),
                                AOPBuildInCallUCASE(AOPVariable("str")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22696map,
                                        GeneratedMutableMap.map22697map,
                                        GeneratedMutableMap.map22698map,
                                        GeneratedMutableMap.map22699map,
                                        GeneratedMutableMap.map22700map,
                                        GeneratedMutableMap.map22701map,
                                        GeneratedMutableMap.map22702map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map25810map,
                                GeneratedMutableMap.map25811map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25813map,
                                GeneratedMutableMap.map25814map,
                                GeneratedMutableMap.map25815map,
                                GeneratedMutableMap.map25816map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("ustr"),
                                AOPBuildInCallUCASE(AOPVariable("str")),
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
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/str>", "str", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map25810map,
                                GeneratedMutableMap.map25811map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25813map,
                                GeneratedMutableMap.map25814map,
                                GeneratedMutableMap.map25815map,
                                GeneratedMutableMap.map25816map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("ustr"),
                                AOPBuildInCallUCASE(AOPVariable("str")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map22703map,
                                        GeneratedMutableMap.map22704map,
                                        GeneratedMutableMap.map22705map,
                                        GeneratedMutableMap.map22706map,
                                        GeneratedMutableMap.map22707map,
                                        GeneratedMutableMap.map22708map,
                                        GeneratedMutableMap.map22709map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("ustr")
                        ), listOf(
                                GeneratedMutableMap.map25817map,
                                GeneratedMutableMap.map25818map,
                                GeneratedMutableMap.map25819map,
                                GeneratedMutableMap.map25820map,
                                GeneratedMutableMap.map25821map,
                                GeneratedMutableMap.map25822map,
                                GeneratedMutableMap.map25823map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("ustr"),
                                AOPBuildInCallUCASE(AOPVariable("str")),
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
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("ustr")
                        ), listOf(
                                GeneratedMutableMap.map25817map,
                                GeneratedMutableMap.map25818map,
                                GeneratedMutableMap.map25819map,
                                GeneratedMutableMap.map25820map,
                                GeneratedMutableMap.map25821map,
                                GeneratedMutableMap.map25822map,
                                GeneratedMutableMap.map25823map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("lstr"),
                                AOPBuildInCallLCASE(AOPVariable("str")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22696map,
                                        GeneratedMutableMap.map22697map,
                                        GeneratedMutableMap.map22698map,
                                        GeneratedMutableMap.map22699map,
                                        GeneratedMutableMap.map22700map,
                                        GeneratedMutableMap.map22701map,
                                        GeneratedMutableMap.map22702map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map25926map,
                                GeneratedMutableMap.map25927map,
                                GeneratedMutableMap.map25928map,
                                GeneratedMutableMap.map25929map,
                                GeneratedMutableMap.map25930map,
                                GeneratedMutableMap.map25931map,
                                GeneratedMutableMap.map25932map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("lstr"),
                                AOPBuildInCallLCASE(AOPVariable("str")),
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
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/str>", "str", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map25926map,
                                GeneratedMutableMap.map25927map,
                                GeneratedMutableMap.map25928map,
                                GeneratedMutableMap.map25929map,
                                GeneratedMutableMap.map25930map,
                                GeneratedMutableMap.map25931map,
                                GeneratedMutableMap.map25932map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("lstr"),
                                AOPBuildInCallLCASE(AOPVariable("str")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map22703map,
                                        GeneratedMutableMap.map22704map,
                                        GeneratedMutableMap.map22705map,
                                        GeneratedMutableMap.map22706map,
                                        GeneratedMutableMap.map22707map,
                                        GeneratedMutableMap.map22708map,
                                        GeneratedMutableMap.map22709map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("lstr")
                        ), listOf(
                                GeneratedMutableMap.map25933map,
                                GeneratedMutableMap.map25934map,
                                GeneratedMutableMap.map25935map,
                                GeneratedMutableMap.map25820map,
                                GeneratedMutableMap.map25821map,
                                GeneratedMutableMap.map25936map,
                                GeneratedMutableMap.map25937map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("lstr"),
                                AOPBuildInCallLCASE(AOPVariable("str")),
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
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("lstr")
                        ), listOf(
                                GeneratedMutableMap.map25933map,
                                GeneratedMutableMap.map25934map,
                                GeneratedMutableMap.map25935map,
                                GeneratedMutableMap.map25820map,
                                GeneratedMutableMap.map25821map,
                                GeneratedMutableMap.map25936map,
                                GeneratedMutableMap.map25937map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("y"), AOPVariable("x")),
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27197map,
                                GeneratedMutableMap.map27198map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27200map,
                                GeneratedMutableMap.map27201map,
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27197map,
                                GeneratedMutableMap.map27198map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27200map,
                                GeneratedMutableMap.map27201map,
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("y"), AOPVariable("x")),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27205map,
                                GeneratedMutableMap.map27206map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27208map,
                                GeneratedMutableMap.map27209map,
                                GeneratedMutableMap.map27210map,
                                GeneratedMutableMap.map27211map,
                                GeneratedMutableMap.map27212map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27205map,
                                GeneratedMutableMap.map27206map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27208map,
                                GeneratedMutableMap.map27209map,
                                GeneratedMutableMap.map27210map,
                                GeneratedMutableMap.map27211map,
                                GeneratedMutableMap.map27212map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("sum"),
                                AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27197map,
                                GeneratedMutableMap.map27198map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27477map,
                                GeneratedMutableMap.map27478map,
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27197map,
                                GeneratedMutableMap.map27198map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27477map,
                                GeneratedMutableMap.map27478map,
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27205map,
                                GeneratedMutableMap.map27206map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27479map,
                                GeneratedMutableMap.map27480map,
                                GeneratedMutableMap.map27210map,
                                GeneratedMutableMap.map27211map,
                                GeneratedMutableMap.map27212map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27205map,
                                GeneratedMutableMap.map27206map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27479map,
                                GeneratedMutableMap.map27480map,
                                GeneratedMutableMap.map27210map,
                                GeneratedMutableMap.map27211map,
                                GeneratedMutableMap.map27212map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27553map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27555map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s1>", "<http://example.org/str>", "l", true, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27555map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                LOPValues(listOf(
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map27554map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27556map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/s1>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("l"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27556map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27627map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27629map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s4>", "<http://example.org/str>", "l", true, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27629map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                LOPValues(listOf(
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map27628map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27630map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/s4>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("l"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27630map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallSHA1(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27553map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27701map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallSHA1(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s1>", "<http://example.org/str>", "l", true, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27701map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallSHA1(AOPVariable("l")),
                                LOPValues(listOf(
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map27554map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27702map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallSHA1(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/s1>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("l"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27702map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallSHA1(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27773map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27775map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallSHA1(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s8>", "<http://example.org/str>", "\"食\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s8>", "<http://example.org/str>", "l", true, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27775map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallSHA1(AOPVariable("l")),
                                LOPValues(listOf(
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map27774map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27776map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallSHA1(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s8>", "<http://example.org/str>", "\"食\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/s8>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("l"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27776map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallSHA256(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27553map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27847map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallSHA256(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s1>", "<http://example.org/str>", "l", true, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27847map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallSHA256(AOPVariable("l")),
                                LOPValues(listOf(
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map27554map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27848map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallSHA256(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/s1>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("l"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27848map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallSHA256(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27773map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27919map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallSHA256(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s8>", "<http://example.org/str>", "\"食\""))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s8>", "<http://example.org/str>", "l", true, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27919map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallSHA256(AOPVariable("l")),
                                LOPValues(listOf(
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map27774map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27920map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("hash"),
                                AOPBuildInCallSHA256(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s8>", "<http://example.org/str>", "\"食\""))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/s8>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("l"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27920map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallMINUTES(AOPVariable("date")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map28088map,
                                        GeneratedMutableMap.map28089map,
                                        GeneratedMutableMap.map28090map,
                                        GeneratedMutableMap.map28091map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28096map,
                                GeneratedMutableMap.map28097map,
                                GeneratedMutableMap.map28098map,
                                GeneratedMutableMap.map28099map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallMINUTES(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/date>", "date", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28096map,
                                GeneratedMutableMap.map28097map,
                                GeneratedMutableMap.map28098map,
                                GeneratedMutableMap.map28099map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallMINUTES(AOPVariable("date")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("date")
                                ), listOf(
                                        GeneratedMutableMap.map28092map,
                                        GeneratedMutableMap.map28093map,
                                        GeneratedMutableMap.map28094map,
                                        GeneratedMutableMap.map28095map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28100map,
                                GeneratedMutableMap.map28101map,
                                GeneratedMutableMap.map28102map,
                                GeneratedMutableMap.map28103map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallMINUTES(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/date>"), AOPVariable("date"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28100map,
                                GeneratedMutableMap.map28101map,
                                GeneratedMutableMap.map28102map,
                                GeneratedMutableMap.map28103map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallSECONDS(AOPVariable("date")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map28088map,
                                        GeneratedMutableMap.map28089map,
                                        GeneratedMutableMap.map28090map,
                                        GeneratedMutableMap.map28091map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28186map,
                                GeneratedMutableMap.map28187map,
                                GeneratedMutableMap.map28188map,
                                GeneratedMutableMap.map28189map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallSECONDS(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/date>", "date", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28186map,
                                GeneratedMutableMap.map28187map,
                                GeneratedMutableMap.map28188map,
                                GeneratedMutableMap.map28189map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallSECONDS(AOPVariable("date")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("date")
                                ), listOf(
                                        GeneratedMutableMap.map28092map,
                                        GeneratedMutableMap.map28093map,
                                        GeneratedMutableMap.map28094map,
                                        GeneratedMutableMap.map28095map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28190map,
                                GeneratedMutableMap.map28191map,
                                GeneratedMutableMap.map28192map,
                                GeneratedMutableMap.map28193map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallSECONDS(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/date>"), AOPVariable("date"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28190map,
                                GeneratedMutableMap.map28191map,
                                GeneratedMutableMap.map28192map,
                                GeneratedMutableMap.map28193map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallHOURS(AOPVariable("date")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map28088map,
                                        GeneratedMutableMap.map28089map,
                                        GeneratedMutableMap.map28090map,
                                        GeneratedMutableMap.map28091map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28313map,
                                GeneratedMutableMap.map28314map,
                                GeneratedMutableMap.map28315map,
                                GeneratedMutableMap.map28316map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallHOURS(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/date>", "date", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28313map,
                                GeneratedMutableMap.map28314map,
                                GeneratedMutableMap.map28315map,
                                GeneratedMutableMap.map28316map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallHOURS(AOPVariable("date")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("date")
                                ), listOf(
                                        GeneratedMutableMap.map28092map,
                                        GeneratedMutableMap.map28093map,
                                        GeneratedMutableMap.map28094map,
                                        GeneratedMutableMap.map28095map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28317map,
                                GeneratedMutableMap.map28318map,
                                GeneratedMutableMap.map28319map,
                                GeneratedMutableMap.map28320map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallHOURS(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/date>"), AOPVariable("date"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28317map,
                                GeneratedMutableMap.map28318map,
                                GeneratedMutableMap.map28319map,
                                GeneratedMutableMap.map28320map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallMONTH(AOPVariable("date")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map28088map,
                                        GeneratedMutableMap.map28089map,
                                        GeneratedMutableMap.map28090map,
                                        GeneratedMutableMap.map28091map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28440map,
                                GeneratedMutableMap.map28441map,
                                GeneratedMutableMap.map28442map,
                                GeneratedMutableMap.map28099map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallMONTH(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/date>", "date", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28440map,
                                GeneratedMutableMap.map28441map,
                                GeneratedMutableMap.map28442map,
                                GeneratedMutableMap.map28099map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallMONTH(AOPVariable("date")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("date")
                                ), listOf(
                                        GeneratedMutableMap.map28092map,
                                        GeneratedMutableMap.map28093map,
                                        GeneratedMutableMap.map28094map,
                                        GeneratedMutableMap.map28095map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28443map,
                                GeneratedMutableMap.map28444map,
                                GeneratedMutableMap.map28445map,
                                GeneratedMutableMap.map28103map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallMONTH(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/date>"), AOPVariable("date"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28443map,
                                GeneratedMutableMap.map28444map,
                                GeneratedMutableMap.map28445map,
                                GeneratedMutableMap.map28103map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallYEAR(AOPVariable("date")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map28088map,
                                        GeneratedMutableMap.map28089map,
                                        GeneratedMutableMap.map28090map,
                                        GeneratedMutableMap.map28091map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28563map,
                                GeneratedMutableMap.map28564map,
                                GeneratedMutableMap.map28565map,
                                GeneratedMutableMap.map28566map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallYEAR(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/date>", "date", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28563map,
                                GeneratedMutableMap.map28564map,
                                GeneratedMutableMap.map28565map,
                                GeneratedMutableMap.map28566map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallYEAR(AOPVariable("date")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("date")
                                ), listOf(
                                        GeneratedMutableMap.map28092map,
                                        GeneratedMutableMap.map28093map,
                                        GeneratedMutableMap.map28094map,
                                        GeneratedMutableMap.map28095map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28567map,
                                GeneratedMutableMap.map28568map,
                                GeneratedMutableMap.map28569map,
                                GeneratedMutableMap.map28570map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallYEAR(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/date>"), AOPVariable("date"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28567map,
                                GeneratedMutableMap.map28568map,
                                GeneratedMutableMap.map28569map,
                                GeneratedMutableMap.map28570map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallDAY(AOPVariable("date")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map28088map,
                                        GeneratedMutableMap.map28089map,
                                        GeneratedMutableMap.map28090map,
                                        GeneratedMutableMap.map28091map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28690map,
                                GeneratedMutableMap.map28691map,
                                GeneratedMutableMap.map28692map,
                                GeneratedMutableMap.map28316map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallDAY(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/date>", "date", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28690map,
                                GeneratedMutableMap.map28691map,
                                GeneratedMutableMap.map28692map,
                                GeneratedMutableMap.map28316map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallDAY(AOPVariable("date")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("date")
                                ), listOf(
                                        GeneratedMutableMap.map28092map,
                                        GeneratedMutableMap.map28093map,
                                        GeneratedMutableMap.map28094map,
                                        GeneratedMutableMap.map28095map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28693map,
                                GeneratedMutableMap.map28694map,
                                GeneratedMutableMap.map28695map,
                                GeneratedMutableMap.map28320map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallDAY(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/date>"), AOPVariable("date"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28693map,
                                GeneratedMutableMap.map28694map,
                                GeneratedMutableMap.map28695map,
                                GeneratedMutableMap.map28320map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPBind(
                        dictionary,
                        AOPVariable("x"),
                        AOPBuildInCallTIMEZONE(AOPVariable("date")),
                        POPValues(dictionary, listOf(
                                "s",
                                "date"
                            ), listOf(
                                GeneratedMutableMap.map28088map,
                                GeneratedMutableMap.map28089map,
                                GeneratedMutableMap.map28090map,
                                GeneratedMutableMap.map28091map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28776map,
                            GeneratedMutableMap.map28777map,
                            GeneratedMutableMap.map28778map,
                            GeneratedMutableMap.map28779map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/timezone-01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallTZ(AOPVariable("date")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map28088map,
                                        GeneratedMutableMap.map28089map,
                                        GeneratedMutableMap.map28090map,
                                        GeneratedMutableMap.map28091map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28895map,
                                GeneratedMutableMap.map28896map,
                                GeneratedMutableMap.map28897map,
                                GeneratedMutableMap.map28779map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("x"),
                                AOPBuildInCallTZ(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/date>", "date", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28895map,
                                GeneratedMutableMap.map28896map,
                                GeneratedMutableMap.map28897map,
                                GeneratedMutableMap.map28779map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallTZ(AOPVariable("date")),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("date")
                                ), listOf(
                                        GeneratedMutableMap.map28092map,
                                        GeneratedMutableMap.map28093map,
                                        GeneratedMutableMap.map28094map,
                                        GeneratedMutableMap.map28095map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28898map,
                                GeneratedMutableMap.map28899map,
                                GeneratedMutableMap.map28900map,
                                GeneratedMutableMap.map28901map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("x"),
                                AOPBuildInCallTZ(AOPVariable("date")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/date>"), AOPVariable("date"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28898map,
                                GeneratedMutableMap.map28899map,
                                GeneratedMutableMap.map28900map,
                                GeneratedMutableMap.map28901map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("b2"),
                                AOPBuildInCallBNODE1(AOPVariable("s2")),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "s1",
                                        "b",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map30962map,
                                        GeneratedMutableMap.map30964map,
                                        GeneratedMutableMap.map30976map,
                                        GeneratedMutableMap.map30978map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31060map,
                                GeneratedMutableMap.map31061map,
                                GeneratedMutableMap.map31062map,
                                GeneratedMutableMap.map31063map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("b2"),
                                AOPBuildInCallBNODE1(AOPVariable("s2")),
                                POPFilter(
                                        dictionary,
                                        AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))),
                                        POPFilter(
                                                dictionary,
                                                AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
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
                                                        false)

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31060map,
                                GeneratedMutableMap.map31061map,
                                GeneratedMutableMap.map31062map,
                                GeneratedMutableMap.map31063map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("b2"),
                                AOPBuildInCallBNODE1(AOPVariable("s2")),
                                LOPValues(listOf(
                                        AOPVariable("a"),
                                        AOPVariable("s1"),
                                        AOPVariable("b"),
                                        AOPVariable("s2")
                                ), listOf(
                                        GeneratedMutableMap.map31011map,
                                        GeneratedMutableMap.map31013map,
                                        GeneratedMutableMap.map31025map,
                                        GeneratedMutableMap.map31027map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1"),
                                AOPVariable("b"),
                                AOPVariable("s2"),
                                AOPVariable("b2")
                        ), listOf(
                                GeneratedMutableMap.map31064map,
                                GeneratedMutableMap.map31065map,
                                GeneratedMutableMap.map31066map,
                                GeneratedMutableMap.map31067map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("b2"),
                                AOPBuildInCallBNODE1(AOPVariable("s2")),
                                LOPFilter(
                                        AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))),
                                        LOPFilter(
                                                AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
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
                                                        false)

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1"),
                                AOPVariable("b"),
                                AOPVariable("s2"),
                                AOPVariable("b2")
                        ), listOf(
                                GeneratedMutableMap.map31064map,
                                GeneratedMutableMap.map31065map,
                                GeneratedMutableMap.map31066map,
                                GeneratedMutableMap.map31067map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("b1"),
                                AOPBuildInCallBNODE1(AOPVariable("s1")),
                                POPValues(dictionary, listOf(
                                        "a",
                                        "s1",
                                        "b",
                                        "s2",
                                        "b2"
                                ), listOf(
                                        GeneratedMutableMap.map31060map,
                                        GeneratedMutableMap.map31061map,
                                        GeneratedMutableMap.map31062map,
                                        GeneratedMutableMap.map31063map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2",
                                "b2",
                                "b1"
                        ), listOf(
                                GeneratedMutableMap.map31068map,
                                GeneratedMutableMap.map31069map,
                                GeneratedMutableMap.map31070map,
                                GeneratedMutableMap.map31071map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("b1"),
                                AOPBuildInCallBNODE1(AOPVariable("s1")),
                                POPBind(
                                        dictionary,
                                        AOPVariable("b2"),
                                        AOPBuildInCallBNODE1(AOPVariable("s2")),
                                        POPFilter(
                                                dictionary,
                                                AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))),
                                                POPFilter(
                                                        dictionary,
                                                        AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
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
                                                                false)

                                                )

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2",
                                "b2",
                                "b1"
                        ), listOf(
                                GeneratedMutableMap.map31068map,
                                GeneratedMutableMap.map31069map,
                                GeneratedMutableMap.map31070map,
                                GeneratedMutableMap.map31071map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("b1"),
                                AOPBuildInCallBNODE1(AOPVariable("s1")),
                                LOPValues(listOf(
                                        AOPVariable("a"),
                                        AOPVariable("s1"),
                                        AOPVariable("b"),
                                        AOPVariable("s2"),
                                        AOPVariable("b2")
                                ), listOf(
                                        GeneratedMutableMap.map31064map,
                                        GeneratedMutableMap.map31065map,
                                        GeneratedMutableMap.map31066map,
                                        GeneratedMutableMap.map31067map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1"),
                                AOPVariable("b"),
                                AOPVariable("s2"),
                                AOPVariable("b2"),
                                AOPVariable("b1")
                        ), listOf(
                                GeneratedMutableMap.map31072map,
                                GeneratedMutableMap.map31073map,
                                GeneratedMutableMap.map31074map,
                                GeneratedMutableMap.map31075map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("b1"),
                                AOPBuildInCallBNODE1(AOPVariable("s1")),
                                LOPBind(
                                        AOPVariable("b2"),
                                        AOPBuildInCallBNODE1(AOPVariable("s2")),
                                        LOPFilter(
                                                AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))),
                                                LOPFilter(
                                                        AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
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
                                                                false)

                                                )

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1"),
                                AOPVariable("b"),
                                AOPVariable("s2"),
                                AOPVariable("b2"),
                                AOPVariable("b1")
                        ), listOf(
                                GeneratedMutableMap.map31072map,
                                GeneratedMutableMap.map31073map,
                                GeneratedMutableMap.map31074map,
                                GeneratedMutableMap.map31075map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("b2"),
                                AOPBuildInCallBNODE0(),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5895map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31159map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("b1"),
                                AOPBuildInCallBNODE0(),
                                POPValues(dictionary, listOf(
                                        "b2"
                                ), listOf(
                                        GeneratedMutableMap.map31159map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2",
                                "b1"
                        ), listOf(
                                GeneratedMutableMap.map31160map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("b2"),
                                AOPBuildInCallBNODE0(),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5895map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31162map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("b1"),
                                AOPBuildInCallBNODE0(),
                                POPValues(dictionary, listOf(
                                        "b2"
                                ), listOf(
                                        GeneratedMutableMap.map31162map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2",
                                "b1"
                        ), listOf(
                                GeneratedMutableMap.map31163map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("n"),
                                AOPDateTime("\"2020-02-26T09:35:12Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5895map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "n"
                        ), listOf(
                                GeneratedMutableMap.map31436map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/now01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("iri"),
                                AOPBuildInCallIRI(AOPSimpleLiteral("\"", "iri"), "http://example.org/"),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5895map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "iri"
                        ), listOf(
                                GeneratedMutableMap.map31526map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/iri01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("uri"),
                                AOPBuildInCallURI(AOPSimpleLiteral("\"", "uri"), "http://example.org/"),
                                POPValues(dictionary, listOf(
                                        "iri"
                                ), listOf(
                                        GeneratedMutableMap.map31526map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "iri",
                                "uri"
                        ), listOf(
                                GeneratedMutableMap.map31527map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/iri01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("integer"),
                                AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map31739map,
                                        GeneratedMutableMap.map31740map,
                                        GeneratedMutableMap.map31741map,
                                        GeneratedMutableMap.map31742map,
                                        GeneratedMutableMap.map31743map,
                                        GeneratedMutableMap.map31744map,
                                        GeneratedMutableMap.map31745map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map31753map,
                                GeneratedMutableMap.map31754map,
                                GeneratedMutableMap.map31755map,
                                GeneratedMutableMap.map31756map,
                                GeneratedMutableMap.map31757map,
                                GeneratedMutableMap.map31758map,
                                GeneratedMutableMap.map31759map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("integer"),
                                AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)),
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
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map31753map,
                                GeneratedMutableMap.map31754map,
                                GeneratedMutableMap.map31755map,
                                GeneratedMutableMap.map31756map,
                                GeneratedMutableMap.map31757map,
                                GeneratedMutableMap.map31758map,
                                GeneratedMutableMap.map31759map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("integer"),
                                AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map31746map,
                                        GeneratedMutableMap.map31747map,
                                        GeneratedMutableMap.map31748map,
                                        GeneratedMutableMap.map31749map,
                                        GeneratedMutableMap.map31750map,
                                        GeneratedMutableMap.map31751map,
                                        GeneratedMutableMap.map31752map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("integer")
                        ), listOf(
                                GeneratedMutableMap.map31760map,
                                GeneratedMutableMap.map31761map,
                                GeneratedMutableMap.map31762map,
                                GeneratedMutableMap.map31763map,
                                GeneratedMutableMap.map31764map,
                                GeneratedMutableMap.map31765map,
                                GeneratedMutableMap.map31766map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("integer"),
                                AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)),
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
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("integer")
                        ), listOf(
                                GeneratedMutableMap.map31760map,
                                GeneratedMutableMap.map31761map,
                                GeneratedMutableMap.map31762map,
                                GeneratedMutableMap.map31763map,
                                GeneratedMutableMap.map31764map,
                                GeneratedMutableMap.map31765map,
                                GeneratedMutableMap.map31766map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("error"),
                                AOPBuildInCallIF(AOPDivision(AOPInteger(0), AOPInteger(1)), AOPBoolean(false), AOPBoolean(true)),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5895map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "error"
                        ), listOf(
                                GeneratedMutableMap.map31842map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("eq"),
                                AOPEQ(AOPVariable("y"), AOPVariable("z")),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34845map,
                                        GeneratedMutableMap.map34846map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "eq"
                        ), listOf(
                                GeneratedMutableMap.map34849map,
                                GeneratedMutableMap.map34850map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("eq"),
                                AOPEQ(AOPVariable("y"), AOPVariable("z")),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "eq"
                        ), listOf(
                                GeneratedMutableMap.map34849map,
                                GeneratedMutableMap.map34850map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("eq"),
                                AOPEQ(AOPVariable("y"), AOPVariable("z")),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map34847map,
                                        GeneratedMutableMap.map34848map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z"),
                                AOPVariable("eq")
                        ), listOf(
                                GeneratedMutableMap.map34851map,
                                GeneratedMutableMap.map34852map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("eq"),
                                AOPEQ(AOPVariable("y"), AOPVariable("z")),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z"),
                                AOPVariable("eq")
                        ), listOf(
                                GeneratedMutableMap.map34851map,
                                GeneratedMutableMap.map34852map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("z"), AOPVariable("y")),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34845map,
                                        GeneratedMutableMap.map34968map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map34971map,
                                GeneratedMutableMap.map34972map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("z"), AOPVariable("y")),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map34971map,
                                GeneratedMutableMap.map34972map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("z"), AOPVariable("y")),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map34847map,
                                        GeneratedMutableMap.map34970map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map34973map,
                                GeneratedMutableMap.map34974map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("z"), AOPVariable("y")),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map34973map,
                                GeneratedMutableMap.map34974map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPBind(
                        dictionary,
                        AOPVariable("twice"),
                        AOPMultiplication(AOPVariable("sum"), AOPInteger(2)),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map34846map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "twice"
                        ), listOf(
                            GeneratedMutableMap.map35075map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/project-expression/projexp03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPBind(
                        dictionary,
                        AOPVariable("sum"),
                        AOPAddition(AOPVariable("z"), AOPVariable("y")),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "twice"
                            ), listOf(
                                GeneratedMutableMap.map35075map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "twice",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map35076map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/project-expression/projexp03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("y"), AOPVariable("y")),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map34844map,
                                        GeneratedMutableMap.map35188map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map35189map,
                                GeneratedMutableMap.map35190map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map35189map,
                                GeneratedMutableMap.map35190map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("y"), AOPVariable("y")),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map34842map,
                                        GeneratedMutableMap.map34843map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map34848map,
                                GeneratedMutableMap.map35191map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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

                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map34848map,
                                GeneratedMutableMap.map35191map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map35283map,
                                        GeneratedMutableMap.map35284map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35286map,
                                GeneratedMutableMap.map35287map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "<http://www.example.org/schema#a>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "l", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35286map,
                                GeneratedMutableMap.map35287map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map34842map,
                                        GeneratedMutableMap.map35285map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35288map,
                                GeneratedMutableMap.map35289map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "<http://www.example.org/schema#a>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("l"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35288map,
                                GeneratedMutableMap.map35289map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("m")),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map35283map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35369map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("m")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "l", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35369map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("m")),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map34842map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35370map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("m")),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("l"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35370map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map35463map,
                                        GeneratedMutableMap.map35464map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35466map,
                                GeneratedMutableMap.map35467map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
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
                                        true)

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35466map,
                                GeneratedMutableMap.map35467map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("l")
                                ), listOf(
                                        GeneratedMutableMap.map34848map,
                                        GeneratedMutableMap.map35465map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35468map,
                                GeneratedMutableMap.map35469map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
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
                                        true)

                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35468map,
                                GeneratedMutableMap.map35469map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("FullName"),
                                AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L")),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "F",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map37156map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map37158map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("FullName"),
                                AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L")),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map37158map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("FullName"),
                                AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L")),
                                LOPValues(listOf(
                                        AOPVariable("P"),
                                        AOPVariable("F"),
                                        AOPVariable("L")
                                ), listOf(
                                        GeneratedMutableMap.map37157map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("F"),
                                AOPVariable("L"),
                                AOPVariable("FullName")
                        ), listOf(
                                GeneratedMutableMap.map37159map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("FullName"),
                                AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L")),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("F"),
                                AOPVariable("L"),
                                AOPVariable("FullName")
                        ), listOf(
                                GeneratedMutableMap.map37159map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("p"),
                                AOPIri("http://xmlns.com/foaf/0.1/name"),
                                POPValues(dictionary, listOf(
                                        "FullName",
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map37162map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "FullName",
                                "s",
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map37164map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPBind(
                                dictionary,
                                AOPVariable("p"),
                                AOPIri("http://xmlns.com/foaf/0.1/name"),
                                POPRename(
                                        dictionary,
                                        AOPVariable("s"),
                                        AOPVariable("P"),
                                        POPProjection(
                                                dictionary,
                                                mutableListOf(
                                                        AOPVariable("P"),
                                                        AOPVariable("FullName")
                                                ),
                                                POPBind(
                                                        dictionary,
                                                        AOPVariable("FullName"),
                                                        AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L")),
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
                                                                false)

                                                )

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "FullName",
                                "s",
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map37164map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("p"),
                                AOPIri("http://xmlns.com/foaf/0.1/name"),
                                LOPValues(listOf(
                                        AOPVariable("FullName"),
                                        AOPVariable("s")
                                ), listOf(
                                        GeneratedMutableMap.map37163map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("FullName"),
                                AOPVariable("s"),
                                AOPVariable("p")
                        ), listOf(
                                GeneratedMutableMap.map37165map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("p"),
                                AOPIri("http://xmlns.com/foaf/0.1/name"),
                                LOPRename(
                                        AOPVariable("s"),
                                        AOPVariable("P"),
                                        LOPProjection(
                                                mutableListOf(
                                                        AOPVariable("P"),
                                                        AOPVariable("FullName")
                                                ),
                                                LOPBind(
                                                        AOPVariable("FullName"),
                                                        AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L")),
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
                                                                false)

                                                )

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("FullName"),
                                AOPVariable("s"),
                                AOPVariable("p")
                        ), listOf(
                                GeneratedMutableMap.map37165map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
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
