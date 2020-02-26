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
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2",
                                "O12"
                        ), listOf(
                                GeneratedMutableMap.map2818map,
                                GeneratedMutableMap.map2819map,
                                GeneratedMutableMap.map2820map,
                                GeneratedMutableMap.map2821map,
                                GeneratedMutableMap.map2822map,
                                GeneratedMutableMap.map2823map,
                                GeneratedMutableMap.map2824map,
                                GeneratedMutableMap.map2825map,
                                GeneratedMutableMap.map2826map
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
                                GeneratedMutableMap.map2818map,
                                GeneratedMutableMap.map2819map,
                                GeneratedMutableMap.map2820map,
                                GeneratedMutableMap.map2821map,
                                GeneratedMutableMap.map2822map,
                                GeneratedMutableMap.map2823map,
                                GeneratedMutableMap.map2824map,
                                GeneratedMutableMap.map2825map,
                                GeneratedMutableMap.map2826map
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O1"),
                                AOPVariable("O2"),
                                AOPVariable("O12")
                        ), listOf(
                                GeneratedMutableMap.map2827map,
                                GeneratedMutableMap.map2828map,
                                GeneratedMutableMap.map2829map,
                                GeneratedMutableMap.map2830map,
                                GeneratedMutableMap.map2831map,
                                GeneratedMutableMap.map2832map,
                                GeneratedMutableMap.map2833map,
                                GeneratedMutableMap.map2834map,
                                GeneratedMutableMap.map2835map
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
                                GeneratedMutableMap.map2827map,
                                GeneratedMutableMap.map2828map,
                                GeneratedMutableMap.map2829map,
                                GeneratedMutableMap.map2830map,
                                GeneratedMutableMap.map2831map,
                                GeneratedMutableMap.map2832map,
                                GeneratedMutableMap.map2833map,
                                GeneratedMutableMap.map2834map,
                                GeneratedMutableMap.map2835map
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
                                        GeneratedMutableMap.map6784map,
                                        GeneratedMutableMap.map6785map,
                                        GeneratedMutableMap.map6786map,
                                        GeneratedMutableMap.map6787map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6792map,
                                GeneratedMutableMap.map6793map,
                                GeneratedMutableMap.map6794map,
                                GeneratedMutableMap.map6795map
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
                                GeneratedMutableMap.map6792map,
                                GeneratedMutableMap.map6793map,
                                GeneratedMutableMap.map6794map,
                                GeneratedMutableMap.map6795map
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
                                        GeneratedMutableMap.map6788map,
                                        GeneratedMutableMap.map6789map,
                                        GeneratedMutableMap.map6790map,
                                        GeneratedMutableMap.map6791map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map6796map,
                                GeneratedMutableMap.map6797map,
                                GeneratedMutableMap.map6798map,
                                GeneratedMutableMap.map6799map
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
                                GeneratedMutableMap.map6796map,
                                GeneratedMutableMap.map6797map,
                                GeneratedMutableMap.map6798map,
                                GeneratedMutableMap.map6799map
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
                                        GeneratedMutableMap.map6792map,
                                        GeneratedMutableMap.map6793map,
                                        GeneratedMutableMap.map6794map,
                                        GeneratedMutableMap.map6795map
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
                                GeneratedMutableMap.map6952map,
                                GeneratedMutableMap.map6953map,
                                GeneratedMutableMap.map6954map,
                                GeneratedMutableMap.map6955map
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
                                GeneratedMutableMap.map6952map,
                                GeneratedMutableMap.map6953map,
                                GeneratedMutableMap.map6954map,
                                GeneratedMutableMap.map6955map
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
                                        GeneratedMutableMap.map6796map,
                                        GeneratedMutableMap.map6797map,
                                        GeneratedMutableMap.map6798map,
                                        GeneratedMutableMap.map6799map
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
                                GeneratedMutableMap.map6956map,
                                GeneratedMutableMap.map6957map,
                                GeneratedMutableMap.map6958map,
                                GeneratedMutableMap.map6959map
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
                                GeneratedMutableMap.map6956map,
                                GeneratedMutableMap.map6957map,
                                GeneratedMutableMap.map6958map,
                                GeneratedMutableMap.map6959map
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
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7280map,
                            GeneratedMutableMap.map7281map,
                            GeneratedMutableMap.map7282map,
                            GeneratedMutableMap.map7283map,
                            GeneratedMutableMap.map7268map,
                            GeneratedMutableMap.map7284map,
                            GeneratedMutableMap.map7285map,
                            GeneratedMutableMap.map7286map,
                            GeneratedMutableMap.map7287map,
                            GeneratedMutableMap.map7273map,
                            GeneratedMutableMap.map7288map,
                            GeneratedMutableMap.map7289map,
                            GeneratedMutableMap.map7290map,
                            GeneratedMutableMap.map7291map,
                            GeneratedMutableMap.map7278map,
                            GeneratedMutableMap.map7292map
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
                                        GeneratedMutableMap.map6784map,
                                        GeneratedMutableMap.map6785map,
                                        GeneratedMutableMap.map6786map,
                                        GeneratedMutableMap.map6787map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7608map,
                                GeneratedMutableMap.map7609map,
                                GeneratedMutableMap.map7610map,
                                GeneratedMutableMap.map7611map
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
                                GeneratedMutableMap.map7608map,
                                GeneratedMutableMap.map7609map,
                                GeneratedMutableMap.map7610map,
                                GeneratedMutableMap.map7611map
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
                                        GeneratedMutableMap.map6788map,
                                        GeneratedMutableMap.map6789map,
                                        GeneratedMutableMap.map6790map,
                                        GeneratedMutableMap.map6791map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7612map,
                                GeneratedMutableMap.map7613map,
                                GeneratedMutableMap.map7614map,
                                GeneratedMutableMap.map7615map
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
                                GeneratedMutableMap.map7612map,
                                GeneratedMutableMap.map7613map,
                                GeneratedMutableMap.map7614map,
                                GeneratedMutableMap.map7615map
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
                                        GeneratedMutableMap.map5965map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7792map
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
                                        GeneratedMutableMap.map5965map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7792map
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
                                        GeneratedMutableMap.map8216map,
                                        GeneratedMutableMap.map8217map,
                                        GeneratedMutableMap.map8218map,
                                        GeneratedMutableMap.map8219map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8224map,
                                GeneratedMutableMap.map8225map,
                                GeneratedMutableMap.map8226map,
                                GeneratedMutableMap.map8227map
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
                                GeneratedMutableMap.map8224map,
                                GeneratedMutableMap.map8225map,
                                GeneratedMutableMap.map8226map,
                                GeneratedMutableMap.map8227map
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
                                        GeneratedMutableMap.map8220map,
                                        GeneratedMutableMap.map8221map,
                                        GeneratedMutableMap.map8222map,
                                        GeneratedMutableMap.map8223map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map8228map,
                                GeneratedMutableMap.map8229map,
                                GeneratedMutableMap.map8230map,
                                GeneratedMutableMap.map8231map
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
                                GeneratedMutableMap.map8228map,
                                GeneratedMutableMap.map8229map,
                                GeneratedMutableMap.map8230map,
                                GeneratedMutableMap.map8231map
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
                                        GeneratedMutableMap.map8216map,
                                        GeneratedMutableMap.map8217map,
                                        GeneratedMutableMap.map8218map,
                                        GeneratedMutableMap.map8219map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8390map,
                                GeneratedMutableMap.map8391map,
                                GeneratedMutableMap.map8392map,
                                GeneratedMutableMap.map8393map
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
                                GeneratedMutableMap.map8390map,
                                GeneratedMutableMap.map8391map,
                                GeneratedMutableMap.map8392map,
                                GeneratedMutableMap.map8393map
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
                                        GeneratedMutableMap.map8220map,
                                        GeneratedMutableMap.map8221map,
                                        GeneratedMutableMap.map8222map,
                                        GeneratedMutableMap.map8223map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map8394map,
                                GeneratedMutableMap.map8395map,
                                GeneratedMutableMap.map8396map,
                                GeneratedMutableMap.map8397map
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
                                GeneratedMutableMap.map8394map,
                                GeneratedMutableMap.map8395map,
                                GeneratedMutableMap.map8396map,
                                GeneratedMutableMap.map8397map
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
                                GeneratedMutableMap.map6792map,
                                GeneratedMutableMap.map6793map,
                                GeneratedMutableMap.map6794map,
                                GeneratedMutableMap.map6795map
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
                                GeneratedMutableMap.map6796map,
                                GeneratedMutableMap.map6797map,
                                GeneratedMutableMap.map6798map,
                                GeneratedMutableMap.map6799map
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
                                GeneratedMutableMap.map6952map,
                                GeneratedMutableMap.map6953map,
                                GeneratedMutableMap.map6954map,
                                GeneratedMutableMap.map6955map
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
                                GeneratedMutableMap.map6956map,
                                GeneratedMutableMap.map6957map,
                                GeneratedMutableMap.map6958map,
                                GeneratedMutableMap.map6959map
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
                                GeneratedMutableMap.map7608map,
                                GeneratedMutableMap.map7609map,
                                GeneratedMutableMap.map7610map,
                                GeneratedMutableMap.map7611map
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
                                GeneratedMutableMap.map7612map,
                                GeneratedMutableMap.map7613map,
                                GeneratedMutableMap.map7614map,
                                GeneratedMutableMap.map7615map
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
                                        GeneratedMutableMap.map22987map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23000map
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
                                GeneratedMutableMap.map23000map
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
                                        GeneratedMutableMap.map22994map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23001map
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
                                GeneratedMutableMap.map23001map
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
                                        GeneratedMutableMap.map22987map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23276map
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
                                GeneratedMutableMap.map23276map
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
                                        GeneratedMutableMap.map22994map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23277map
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
                                GeneratedMutableMap.map23277map
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
                                        GeneratedMutableMap.map23453map,
                                        GeneratedMutableMap.map23454map,
                                        GeneratedMutableMap.map23455map,
                                        GeneratedMutableMap.map23456map,
                                        GeneratedMutableMap.map23457map,
                                        GeneratedMutableMap.map23458map,
                                        GeneratedMutableMap.map23459map,
                                        GeneratedMutableMap.map23460map,
                                        GeneratedMutableMap.map23461map,
                                        GeneratedMutableMap.map23462map,
                                        GeneratedMutableMap.map23463map,
                                        GeneratedMutableMap.map23464map,
                                        GeneratedMutableMap.map23465map,
                                        GeneratedMutableMap.map23466map,
                                        GeneratedMutableMap.map23467map,
                                        GeneratedMutableMap.map23468map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23485map,
                                GeneratedMutableMap.map23486map,
                                GeneratedMutableMap.map23487map,
                                GeneratedMutableMap.map23488map,
                                GeneratedMutableMap.map23489map,
                                GeneratedMutableMap.map23490map,
                                GeneratedMutableMap.map23491map,
                                GeneratedMutableMap.map23492map,
                                GeneratedMutableMap.map23493map,
                                GeneratedMutableMap.map23494map,
                                GeneratedMutableMap.map23495map,
                                GeneratedMutableMap.map23496map,
                                GeneratedMutableMap.map23497map,
                                GeneratedMutableMap.map23498map,
                                GeneratedMutableMap.map23499map,
                                GeneratedMutableMap.map23500map
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
                                GeneratedMutableMap.map23485map,
                                GeneratedMutableMap.map23486map,
                                GeneratedMutableMap.map23487map,
                                GeneratedMutableMap.map23488map,
                                GeneratedMutableMap.map23489map,
                                GeneratedMutableMap.map23490map,
                                GeneratedMutableMap.map23491map,
                                GeneratedMutableMap.map23492map,
                                GeneratedMutableMap.map23493map,
                                GeneratedMutableMap.map23494map,
                                GeneratedMutableMap.map23495map,
                                GeneratedMutableMap.map23496map,
                                GeneratedMutableMap.map23497map,
                                GeneratedMutableMap.map23498map,
                                GeneratedMutableMap.map23499map,
                                GeneratedMutableMap.map23500map
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
                                        GeneratedMutableMap.map23469map,
                                        GeneratedMutableMap.map23470map,
                                        GeneratedMutableMap.map23471map,
                                        GeneratedMutableMap.map23472map,
                                        GeneratedMutableMap.map23473map,
                                        GeneratedMutableMap.map23474map,
                                        GeneratedMutableMap.map23475map,
                                        GeneratedMutableMap.map23476map,
                                        GeneratedMutableMap.map23477map,
                                        GeneratedMutableMap.map23478map,
                                        GeneratedMutableMap.map23479map,
                                        GeneratedMutableMap.map23480map,
                                        GeneratedMutableMap.map23481map,
                                        GeneratedMutableMap.map23482map,
                                        GeneratedMutableMap.map23483map,
                                        GeneratedMutableMap.map23484map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23501map,
                                GeneratedMutableMap.map23502map,
                                GeneratedMutableMap.map23503map,
                                GeneratedMutableMap.map23504map,
                                GeneratedMutableMap.map23505map,
                                GeneratedMutableMap.map23506map,
                                GeneratedMutableMap.map23507map,
                                GeneratedMutableMap.map23508map,
                                GeneratedMutableMap.map23509map,
                                GeneratedMutableMap.map23510map,
                                GeneratedMutableMap.map23511map,
                                GeneratedMutableMap.map23512map,
                                GeneratedMutableMap.map23513map,
                                GeneratedMutableMap.map23514map,
                                GeneratedMutableMap.map23515map,
                                GeneratedMutableMap.map23516map
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
                                GeneratedMutableMap.map23501map,
                                GeneratedMutableMap.map23502map,
                                GeneratedMutableMap.map23503map,
                                GeneratedMutableMap.map23504map,
                                GeneratedMutableMap.map23505map,
                                GeneratedMutableMap.map23506map,
                                GeneratedMutableMap.map23507map,
                                GeneratedMutableMap.map23508map,
                                GeneratedMutableMap.map23509map,
                                GeneratedMutableMap.map23510map,
                                GeneratedMutableMap.map23511map,
                                GeneratedMutableMap.map23512map,
                                GeneratedMutableMap.map23513map,
                                GeneratedMutableMap.map23514map,
                                GeneratedMutableMap.map23515map,
                                GeneratedMutableMap.map23516map
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
                                        GeneratedMutableMap.map22987map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23800map
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
                                GeneratedMutableMap.map23800map
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
                                        GeneratedMutableMap.map22994map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map23001map
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
                                GeneratedMutableMap.map23001map
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
                                        GeneratedMutableMap.map22987map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map24071map
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
                                GeneratedMutableMap.map24071map
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
                                        GeneratedMutableMap.map22994map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map24072map
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
                                GeneratedMutableMap.map24072map
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
                                        GeneratedMutableMap.map23453map,
                                        GeneratedMutableMap.map23454map,
                                        GeneratedMutableMap.map23455map,
                                        GeneratedMutableMap.map23456map,
                                        GeneratedMutableMap.map23457map,
                                        GeneratedMutableMap.map23458map,
                                        GeneratedMutableMap.map23459map,
                                        GeneratedMutableMap.map23460map,
                                        GeneratedMutableMap.map23461map,
                                        GeneratedMutableMap.map23462map,
                                        GeneratedMutableMap.map23463map,
                                        GeneratedMutableMap.map23464map,
                                        GeneratedMutableMap.map23465map,
                                        GeneratedMutableMap.map23466map,
                                        GeneratedMutableMap.map23467map,
                                        GeneratedMutableMap.map23468map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23485map,
                                GeneratedMutableMap.map23486map,
                                GeneratedMutableMap.map23487map,
                                GeneratedMutableMap.map23488map,
                                GeneratedMutableMap.map23489map,
                                GeneratedMutableMap.map23490map,
                                GeneratedMutableMap.map23491map,
                                GeneratedMutableMap.map23492map,
                                GeneratedMutableMap.map23493map,
                                GeneratedMutableMap.map24245map,
                                GeneratedMutableMap.map23495map,
                                GeneratedMutableMap.map24246map,
                                GeneratedMutableMap.map24247map,
                                GeneratedMutableMap.map24248map,
                                GeneratedMutableMap.map23499map,
                                GeneratedMutableMap.map23500map
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
                                GeneratedMutableMap.map23485map,
                                GeneratedMutableMap.map23486map,
                                GeneratedMutableMap.map23487map,
                                GeneratedMutableMap.map23488map,
                                GeneratedMutableMap.map23489map,
                                GeneratedMutableMap.map23490map,
                                GeneratedMutableMap.map23491map,
                                GeneratedMutableMap.map23492map,
                                GeneratedMutableMap.map23493map,
                                GeneratedMutableMap.map24245map,
                                GeneratedMutableMap.map23495map,
                                GeneratedMutableMap.map24246map,
                                GeneratedMutableMap.map24247map,
                                GeneratedMutableMap.map24248map,
                                GeneratedMutableMap.map23499map,
                                GeneratedMutableMap.map23500map
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
                                        GeneratedMutableMap.map23469map,
                                        GeneratedMutableMap.map23470map,
                                        GeneratedMutableMap.map23471map,
                                        GeneratedMutableMap.map23472map,
                                        GeneratedMutableMap.map23473map,
                                        GeneratedMutableMap.map23474map,
                                        GeneratedMutableMap.map23475map,
                                        GeneratedMutableMap.map23476map,
                                        GeneratedMutableMap.map23477map,
                                        GeneratedMutableMap.map23478map,
                                        GeneratedMutableMap.map23479map,
                                        GeneratedMutableMap.map23480map,
                                        GeneratedMutableMap.map23481map,
                                        GeneratedMutableMap.map23482map,
                                        GeneratedMutableMap.map23483map,
                                        GeneratedMutableMap.map23484map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23501map,
                                GeneratedMutableMap.map23502map,
                                GeneratedMutableMap.map23503map,
                                GeneratedMutableMap.map23504map,
                                GeneratedMutableMap.map23505map,
                                GeneratedMutableMap.map23506map,
                                GeneratedMutableMap.map23507map,
                                GeneratedMutableMap.map23508map,
                                GeneratedMutableMap.map23509map,
                                GeneratedMutableMap.map24249map,
                                GeneratedMutableMap.map23511map,
                                GeneratedMutableMap.map24250map,
                                GeneratedMutableMap.map24251map,
                                GeneratedMutableMap.map24252map,
                                GeneratedMutableMap.map23515map,
                                GeneratedMutableMap.map23516map
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
                                GeneratedMutableMap.map23501map,
                                GeneratedMutableMap.map23502map,
                                GeneratedMutableMap.map23503map,
                                GeneratedMutableMap.map23504map,
                                GeneratedMutableMap.map23505map,
                                GeneratedMutableMap.map23506map,
                                GeneratedMutableMap.map23507map,
                                GeneratedMutableMap.map23508map,
                                GeneratedMutableMap.map23509map,
                                GeneratedMutableMap.map24249map,
                                GeneratedMutableMap.map23511map,
                                GeneratedMutableMap.map24250map,
                                GeneratedMutableMap.map24251map,
                                GeneratedMutableMap.map24252map,
                                GeneratedMutableMap.map23515map,
                                GeneratedMutableMap.map23516map
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
                                        GeneratedMutableMap.map24456map,
                                        GeneratedMutableMap.map24457map,
                                        GeneratedMutableMap.map24458map,
                                        GeneratedMutableMap.map24459map,
                                        GeneratedMutableMap.map24460map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "ceil"
                        ), listOf(
                                GeneratedMutableMap.map24658map,
                                GeneratedMutableMap.map24659map,
                                GeneratedMutableMap.map24660map,
                                GeneratedMutableMap.map24661map,
                                GeneratedMutableMap.map24662map
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
                                GeneratedMutableMap.map24658map,
                                GeneratedMutableMap.map24659map,
                                GeneratedMutableMap.map24660map,
                                GeneratedMutableMap.map24661map,
                                GeneratedMutableMap.map24662map
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
                                        GeneratedMutableMap.map24461map,
                                        GeneratedMutableMap.map24462map,
                                        GeneratedMutableMap.map24463map,
                                        GeneratedMutableMap.map24464map,
                                        GeneratedMutableMap.map24465map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("ceil")
                        ), listOf(
                                GeneratedMutableMap.map24663map,
                                GeneratedMutableMap.map24664map,
                                GeneratedMutableMap.map24665map,
                                GeneratedMutableMap.map24666map,
                                GeneratedMutableMap.map24667map
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
                                GeneratedMutableMap.map24663map,
                                GeneratedMutableMap.map24664map,
                                GeneratedMutableMap.map24665map,
                                GeneratedMutableMap.map24666map,
                                GeneratedMutableMap.map24667map
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
                                        GeneratedMutableMap.map24456map,
                                        GeneratedMutableMap.map24457map,
                                        GeneratedMutableMap.map24458map,
                                        GeneratedMutableMap.map24459map,
                                        GeneratedMutableMap.map24460map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "floor"
                        ), listOf(
                                GeneratedMutableMap.map24752map,
                                GeneratedMutableMap.map24753map,
                                GeneratedMutableMap.map24754map,
                                GeneratedMutableMap.map24755map,
                                GeneratedMutableMap.map24756map
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
                                GeneratedMutableMap.map24752map,
                                GeneratedMutableMap.map24753map,
                                GeneratedMutableMap.map24754map,
                                GeneratedMutableMap.map24755map,
                                GeneratedMutableMap.map24756map
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
                                        GeneratedMutableMap.map24461map,
                                        GeneratedMutableMap.map24462map,
                                        GeneratedMutableMap.map24463map,
                                        GeneratedMutableMap.map24464map,
                                        GeneratedMutableMap.map24465map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("floor")
                        ), listOf(
                                GeneratedMutableMap.map24663map,
                                GeneratedMutableMap.map24757map,
                                GeneratedMutableMap.map24758map,
                                GeneratedMutableMap.map24666map,
                                GeneratedMutableMap.map24759map
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
                                GeneratedMutableMap.map24663map,
                                GeneratedMutableMap.map24757map,
                                GeneratedMutableMap.map24758map,
                                GeneratedMutableMap.map24666map,
                                GeneratedMutableMap.map24759map
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
                                        GeneratedMutableMap.map24456map,
                                        GeneratedMutableMap.map24457map,
                                        GeneratedMutableMap.map24458map,
                                        GeneratedMutableMap.map24459map,
                                        GeneratedMutableMap.map24460map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "round"
                        ), listOf(
                                GeneratedMutableMap.map24844map,
                                GeneratedMutableMap.map24845map,
                                GeneratedMutableMap.map24846map,
                                GeneratedMutableMap.map24847map,
                                GeneratedMutableMap.map24848map
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
                                GeneratedMutableMap.map24844map,
                                GeneratedMutableMap.map24845map,
                                GeneratedMutableMap.map24846map,
                                GeneratedMutableMap.map24847map,
                                GeneratedMutableMap.map24848map
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
                                        GeneratedMutableMap.map24461map,
                                        GeneratedMutableMap.map24462map,
                                        GeneratedMutableMap.map24463map,
                                        GeneratedMutableMap.map24464map,
                                        GeneratedMutableMap.map24465map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("round")
                        ), listOf(
                                GeneratedMutableMap.map24663map,
                                GeneratedMutableMap.map24757map,
                                GeneratedMutableMap.map24758map,
                                GeneratedMutableMap.map24666map,
                                GeneratedMutableMap.map24667map
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
                                GeneratedMutableMap.map24663map,
                                GeneratedMutableMap.map24757map,
                                GeneratedMutableMap.map24758map,
                                GeneratedMutableMap.map24666map,
                                GeneratedMutableMap.map24667map
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
                                        GeneratedMutableMap.map24950map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24952map
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
                                GeneratedMutableMap.map24952map
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
                                        GeneratedMutableMap.map24951map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("str1"),
                                AOPVariable("str2"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map24953map
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
                                GeneratedMutableMap.map24953map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map25671map,
                                GeneratedMutableMap.map25672map,
                                GeneratedMutableMap.map25673map,
                                GeneratedMutableMap.map25674map,
                                GeneratedMutableMap.map25675map,
                                GeneratedMutableMap.map25676map,
                                GeneratedMutableMap.map25677map,
                                GeneratedMutableMap.map25678map,
                                GeneratedMutableMap.map25679map,
                                GeneratedMutableMap.map25680map,
                                GeneratedMutableMap.map25681map,
                                GeneratedMutableMap.map25682map,
                                GeneratedMutableMap.map25683map,
                                GeneratedMutableMap.map25684map,
                                GeneratedMutableMap.map25685map,
                                GeneratedMutableMap.map25686map,
                                GeneratedMutableMap.map25687map,
                                GeneratedMutableMap.map25688map,
                                GeneratedMutableMap.map25689map,
                                GeneratedMutableMap.map25690map,
                                GeneratedMutableMap.map25691map,
                                GeneratedMutableMap.map25692map,
                                GeneratedMutableMap.map25693map,
                                GeneratedMutableMap.map25694map,
                                GeneratedMutableMap.map25695map,
                                GeneratedMutableMap.map25696map,
                                GeneratedMutableMap.map25697map,
                                GeneratedMutableMap.map25698map,
                                GeneratedMutableMap.map25699map,
                                GeneratedMutableMap.map25700map,
                                GeneratedMutableMap.map25701map,
                                GeneratedMutableMap.map25702map,
                                GeneratedMutableMap.map25703map,
                                GeneratedMutableMap.map25704map,
                                GeneratedMutableMap.map25705map,
                                GeneratedMutableMap.map25706map,
                                GeneratedMutableMap.map25707map,
                                GeneratedMutableMap.map25708map,
                                GeneratedMutableMap.map25709map,
                                GeneratedMutableMap.map25710map,
                                GeneratedMutableMap.map25711map,
                                GeneratedMutableMap.map25712map,
                                GeneratedMutableMap.map25713map,
                                GeneratedMutableMap.map25714map,
                                GeneratedMutableMap.map25715map,
                                GeneratedMutableMap.map25716map,
                                GeneratedMutableMap.map25717map,
                                GeneratedMutableMap.map25718map,
                                GeneratedMutableMap.map25719map
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
                                GeneratedMutableMap.map25671map,
                                GeneratedMutableMap.map25672map,
                                GeneratedMutableMap.map25673map,
                                GeneratedMutableMap.map25674map,
                                GeneratedMutableMap.map25675map,
                                GeneratedMutableMap.map25676map,
                                GeneratedMutableMap.map25677map,
                                GeneratedMutableMap.map25678map,
                                GeneratedMutableMap.map25679map,
                                GeneratedMutableMap.map25680map,
                                GeneratedMutableMap.map25681map,
                                GeneratedMutableMap.map25682map,
                                GeneratedMutableMap.map25683map,
                                GeneratedMutableMap.map25684map,
                                GeneratedMutableMap.map25685map,
                                GeneratedMutableMap.map25686map,
                                GeneratedMutableMap.map25687map,
                                GeneratedMutableMap.map25688map,
                                GeneratedMutableMap.map25689map,
                                GeneratedMutableMap.map25690map,
                                GeneratedMutableMap.map25691map,
                                GeneratedMutableMap.map25692map,
                                GeneratedMutableMap.map25693map,
                                GeneratedMutableMap.map25694map,
                                GeneratedMutableMap.map25695map,
                                GeneratedMutableMap.map25696map,
                                GeneratedMutableMap.map25697map,
                                GeneratedMutableMap.map25698map,
                                GeneratedMutableMap.map25699map,
                                GeneratedMutableMap.map25700map,
                                GeneratedMutableMap.map25701map,
                                GeneratedMutableMap.map25702map,
                                GeneratedMutableMap.map25703map,
                                GeneratedMutableMap.map25704map,
                                GeneratedMutableMap.map25705map,
                                GeneratedMutableMap.map25706map,
                                GeneratedMutableMap.map25707map,
                                GeneratedMutableMap.map25708map,
                                GeneratedMutableMap.map25709map,
                                GeneratedMutableMap.map25710map,
                                GeneratedMutableMap.map25711map,
                                GeneratedMutableMap.map25712map,
                                GeneratedMutableMap.map25713map,
                                GeneratedMutableMap.map25714map,
                                GeneratedMutableMap.map25715map,
                                GeneratedMutableMap.map25716map,
                                GeneratedMutableMap.map25717map,
                                GeneratedMutableMap.map25718map,
                                GeneratedMutableMap.map25719map
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("str1"),
                                AOPVariable("s2"),
                                AOPVariable("str2"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map25720map,
                                GeneratedMutableMap.map25721map,
                                GeneratedMutableMap.map25722map,
                                GeneratedMutableMap.map25723map,
                                GeneratedMutableMap.map25724map,
                                GeneratedMutableMap.map25725map,
                                GeneratedMutableMap.map25726map,
                                GeneratedMutableMap.map25727map,
                                GeneratedMutableMap.map25728map,
                                GeneratedMutableMap.map25729map,
                                GeneratedMutableMap.map25730map,
                                GeneratedMutableMap.map25731map,
                                GeneratedMutableMap.map25732map,
                                GeneratedMutableMap.map25733map,
                                GeneratedMutableMap.map25734map,
                                GeneratedMutableMap.map25735map,
                                GeneratedMutableMap.map25736map,
                                GeneratedMutableMap.map25737map,
                                GeneratedMutableMap.map25738map,
                                GeneratedMutableMap.map25739map,
                                GeneratedMutableMap.map25740map,
                                GeneratedMutableMap.map25741map,
                                GeneratedMutableMap.map25742map,
                                GeneratedMutableMap.map25743map,
                                GeneratedMutableMap.map25744map,
                                GeneratedMutableMap.map25745map,
                                GeneratedMutableMap.map25746map,
                                GeneratedMutableMap.map25747map,
                                GeneratedMutableMap.map25748map,
                                GeneratedMutableMap.map25749map,
                                GeneratedMutableMap.map25750map,
                                GeneratedMutableMap.map25751map,
                                GeneratedMutableMap.map25752map,
                                GeneratedMutableMap.map25753map,
                                GeneratedMutableMap.map25754map,
                                GeneratedMutableMap.map25755map,
                                GeneratedMutableMap.map25756map,
                                GeneratedMutableMap.map25757map,
                                GeneratedMutableMap.map25758map,
                                GeneratedMutableMap.map25759map,
                                GeneratedMutableMap.map25760map,
                                GeneratedMutableMap.map25761map,
                                GeneratedMutableMap.map25762map,
                                GeneratedMutableMap.map25763map,
                                GeneratedMutableMap.map25764map,
                                GeneratedMutableMap.map25765map,
                                GeneratedMutableMap.map25766map,
                                GeneratedMutableMap.map25767map,
                                GeneratedMutableMap.map25768map
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
                                GeneratedMutableMap.map25720map,
                                GeneratedMutableMap.map25721map,
                                GeneratedMutableMap.map25722map,
                                GeneratedMutableMap.map25723map,
                                GeneratedMutableMap.map25724map,
                                GeneratedMutableMap.map25725map,
                                GeneratedMutableMap.map25726map,
                                GeneratedMutableMap.map25727map,
                                GeneratedMutableMap.map25728map,
                                GeneratedMutableMap.map25729map,
                                GeneratedMutableMap.map25730map,
                                GeneratedMutableMap.map25731map,
                                GeneratedMutableMap.map25732map,
                                GeneratedMutableMap.map25733map,
                                GeneratedMutableMap.map25734map,
                                GeneratedMutableMap.map25735map,
                                GeneratedMutableMap.map25736map,
                                GeneratedMutableMap.map25737map,
                                GeneratedMutableMap.map25738map,
                                GeneratedMutableMap.map25739map,
                                GeneratedMutableMap.map25740map,
                                GeneratedMutableMap.map25741map,
                                GeneratedMutableMap.map25742map,
                                GeneratedMutableMap.map25743map,
                                GeneratedMutableMap.map25744map,
                                GeneratedMutableMap.map25745map,
                                GeneratedMutableMap.map25746map,
                                GeneratedMutableMap.map25747map,
                                GeneratedMutableMap.map25748map,
                                GeneratedMutableMap.map25749map,
                                GeneratedMutableMap.map25750map,
                                GeneratedMutableMap.map25751map,
                                GeneratedMutableMap.map25752map,
                                GeneratedMutableMap.map25753map,
                                GeneratedMutableMap.map25754map,
                                GeneratedMutableMap.map25755map,
                                GeneratedMutableMap.map25756map,
                                GeneratedMutableMap.map25757map,
                                GeneratedMutableMap.map25758map,
                                GeneratedMutableMap.map25759map,
                                GeneratedMutableMap.map25760map,
                                GeneratedMutableMap.map25761map,
                                GeneratedMutableMap.map25762map,
                                GeneratedMutableMap.map25763map,
                                GeneratedMutableMap.map25764map,
                                GeneratedMutableMap.map25765map,
                                GeneratedMutableMap.map25766map,
                                GeneratedMutableMap.map25767map,
                                GeneratedMutableMap.map25768map
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
                                        GeneratedMutableMap.map22986map,
                                        GeneratedMutableMap.map22987map,
                                        GeneratedMutableMap.map22988map,
                                        GeneratedMutableMap.map22989map,
                                        GeneratedMutableMap.map22990map,
                                        GeneratedMutableMap.map22991map,
                                        GeneratedMutableMap.map22992map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map26004map,
                                GeneratedMutableMap.map26005map,
                                GeneratedMutableMap.map26006map,
                                GeneratedMutableMap.map26007map,
                                GeneratedMutableMap.map26008map,
                                GeneratedMutableMap.map26009map,
                                GeneratedMutableMap.map26010map
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
                                GeneratedMutableMap.map26004map,
                                GeneratedMutableMap.map26005map,
                                GeneratedMutableMap.map26006map,
                                GeneratedMutableMap.map26007map,
                                GeneratedMutableMap.map26008map,
                                GeneratedMutableMap.map26009map,
                                GeneratedMutableMap.map26010map
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
                                        GeneratedMutableMap.map22993map,
                                        GeneratedMutableMap.map22994map,
                                        GeneratedMutableMap.map22995map,
                                        GeneratedMutableMap.map22996map,
                                        GeneratedMutableMap.map22997map,
                                        GeneratedMutableMap.map22998map,
                                        GeneratedMutableMap.map22999map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("len")
                        ), listOf(
                                GeneratedMutableMap.map26011map,
                                GeneratedMutableMap.map26012map,
                                GeneratedMutableMap.map26013map,
                                GeneratedMutableMap.map26014map,
                                GeneratedMutableMap.map26015map,
                                GeneratedMutableMap.map26016map,
                                GeneratedMutableMap.map26017map
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
                                GeneratedMutableMap.map26011map,
                                GeneratedMutableMap.map26012map,
                                GeneratedMutableMap.map26013map,
                                GeneratedMutableMap.map26014map,
                                GeneratedMutableMap.map26015map,
                                GeneratedMutableMap.map26016map,
                                GeneratedMutableMap.map26017map
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
                                        GeneratedMutableMap.map22986map,
                                        GeneratedMutableMap.map22987map,
                                        GeneratedMutableMap.map22988map,
                                        GeneratedMutableMap.map22989map,
                                        GeneratedMutableMap.map22990map,
                                        GeneratedMutableMap.map22991map,
                                        GeneratedMutableMap.map22992map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map26125map,
                                GeneratedMutableMap.map26126map,
                                GeneratedMutableMap.map26127map,
                                GeneratedMutableMap.map26128map,
                                GeneratedMutableMap.map26129map,
                                GeneratedMutableMap.map26130map,
                                GeneratedMutableMap.map26131map
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
                                GeneratedMutableMap.map26125map,
                                GeneratedMutableMap.map26126map,
                                GeneratedMutableMap.map26127map,
                                GeneratedMutableMap.map26128map,
                                GeneratedMutableMap.map26129map,
                                GeneratedMutableMap.map26130map,
                                GeneratedMutableMap.map26131map
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
                                        GeneratedMutableMap.map22993map,
                                        GeneratedMutableMap.map22994map,
                                        GeneratedMutableMap.map22995map,
                                        GeneratedMutableMap.map22996map,
                                        GeneratedMutableMap.map22997map,
                                        GeneratedMutableMap.map22998map,
                                        GeneratedMutableMap.map22999map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("ustr")
                        ), listOf(
                                GeneratedMutableMap.map26132map,
                                GeneratedMutableMap.map26133map,
                                GeneratedMutableMap.map26134map,
                                GeneratedMutableMap.map26135map,
                                GeneratedMutableMap.map26136map,
                                GeneratedMutableMap.map26137map,
                                GeneratedMutableMap.map26138map
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
                                GeneratedMutableMap.map26132map,
                                GeneratedMutableMap.map26133map,
                                GeneratedMutableMap.map26134map,
                                GeneratedMutableMap.map26135map,
                                GeneratedMutableMap.map26136map,
                                GeneratedMutableMap.map26137map,
                                GeneratedMutableMap.map26138map
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
                                        GeneratedMutableMap.map22986map,
                                        GeneratedMutableMap.map22987map,
                                        GeneratedMutableMap.map22988map,
                                        GeneratedMutableMap.map22989map,
                                        GeneratedMutableMap.map22990map,
                                        GeneratedMutableMap.map22991map,
                                        GeneratedMutableMap.map22992map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map26242map,
                                GeneratedMutableMap.map26243map,
                                GeneratedMutableMap.map26244map,
                                GeneratedMutableMap.map26245map,
                                GeneratedMutableMap.map26246map,
                                GeneratedMutableMap.map26247map,
                                GeneratedMutableMap.map26248map
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
                                GeneratedMutableMap.map26242map,
                                GeneratedMutableMap.map26243map,
                                GeneratedMutableMap.map26244map,
                                GeneratedMutableMap.map26245map,
                                GeneratedMutableMap.map26246map,
                                GeneratedMutableMap.map26247map,
                                GeneratedMutableMap.map26248map
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
                                        GeneratedMutableMap.map22993map,
                                        GeneratedMutableMap.map22994map,
                                        GeneratedMutableMap.map22995map,
                                        GeneratedMutableMap.map22996map,
                                        GeneratedMutableMap.map22997map,
                                        GeneratedMutableMap.map22998map,
                                        GeneratedMutableMap.map22999map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("lstr")
                        ), listOf(
                                GeneratedMutableMap.map26249map,
                                GeneratedMutableMap.map26250map,
                                GeneratedMutableMap.map26251map,
                                GeneratedMutableMap.map26135map,
                                GeneratedMutableMap.map26136map,
                                GeneratedMutableMap.map26252map,
                                GeneratedMutableMap.map26253map
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
                                GeneratedMutableMap.map26249map,
                                GeneratedMutableMap.map26250map,
                                GeneratedMutableMap.map26251map,
                                GeneratedMutableMap.map26135map,
                                GeneratedMutableMap.map26136map,
                                GeneratedMutableMap.map26252map,
                                GeneratedMutableMap.map26253map
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
                                GeneratedMutableMap.map27525map,
                                GeneratedMutableMap.map27526map,
                                GeneratedMutableMap.map27527map,
                                GeneratedMutableMap.map27528map,
                                GeneratedMutableMap.map27529map
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
                                GeneratedMutableMap.map27533map,
                                GeneratedMutableMap.map27534map,
                                GeneratedMutableMap.map27535map,
                                GeneratedMutableMap.map27536map,
                                GeneratedMutableMap.map27537map
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
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
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
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27882map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27884map
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
                                GeneratedMutableMap.map27884map
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
                                        GeneratedMutableMap.map27883map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27885map
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
                                GeneratedMutableMap.map27885map
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
                                        GeneratedMutableMap.map27958map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27960map
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
                                GeneratedMutableMap.map27960map
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
                                        GeneratedMutableMap.map27959map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27961map
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
                                GeneratedMutableMap.map27961map
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
                                        GeneratedMutableMap.map27882map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28034map
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
                                GeneratedMutableMap.map28034map
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
                                        GeneratedMutableMap.map27883map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28035map
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
                                GeneratedMutableMap.map28035map
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
                                        GeneratedMutableMap.map28108map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28110map
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
                                GeneratedMutableMap.map28110map
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
                                        GeneratedMutableMap.map28109map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28111map
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
                                GeneratedMutableMap.map28111map
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
                                        GeneratedMutableMap.map27882map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28184map
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
                                GeneratedMutableMap.map28184map
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
                                        GeneratedMutableMap.map27883map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28185map
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
                                GeneratedMutableMap.map28185map
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
                                        GeneratedMutableMap.map28108map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28258map
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
                                GeneratedMutableMap.map28258map
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
                                        GeneratedMutableMap.map28109map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28259map
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
                                GeneratedMutableMap.map28259map
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
                                        GeneratedMutableMap.map28429map,
                                        GeneratedMutableMap.map28430map,
                                        GeneratedMutableMap.map28431map,
                                        GeneratedMutableMap.map28432map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28437map,
                                GeneratedMutableMap.map28438map,
                                GeneratedMutableMap.map28439map,
                                GeneratedMutableMap.map28440map
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
                                GeneratedMutableMap.map28437map,
                                GeneratedMutableMap.map28438map,
                                GeneratedMutableMap.map28439map,
                                GeneratedMutableMap.map28440map
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
                                        GeneratedMutableMap.map28433map,
                                        GeneratedMutableMap.map28434map,
                                        GeneratedMutableMap.map28435map,
                                        GeneratedMutableMap.map28436map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28441map,
                                GeneratedMutableMap.map28442map,
                                GeneratedMutableMap.map28443map,
                                GeneratedMutableMap.map28444map
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
                                GeneratedMutableMap.map28441map,
                                GeneratedMutableMap.map28442map,
                                GeneratedMutableMap.map28443map,
                                GeneratedMutableMap.map28444map
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
                                        GeneratedMutableMap.map28429map,
                                        GeneratedMutableMap.map28430map,
                                        GeneratedMutableMap.map28431map,
                                        GeneratedMutableMap.map28432map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28529map,
                                GeneratedMutableMap.map28530map,
                                GeneratedMutableMap.map28531map,
                                GeneratedMutableMap.map28532map
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
                                GeneratedMutableMap.map28529map,
                                GeneratedMutableMap.map28530map,
                                GeneratedMutableMap.map28531map,
                                GeneratedMutableMap.map28532map
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
                                        GeneratedMutableMap.map28433map,
                                        GeneratedMutableMap.map28434map,
                                        GeneratedMutableMap.map28435map,
                                        GeneratedMutableMap.map28436map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28533map,
                                GeneratedMutableMap.map28534map,
                                GeneratedMutableMap.map28535map,
                                GeneratedMutableMap.map28536map
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
                                GeneratedMutableMap.map28533map,
                                GeneratedMutableMap.map28534map,
                                GeneratedMutableMap.map28535map,
                                GeneratedMutableMap.map28536map
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
                                        GeneratedMutableMap.map28429map,
                                        GeneratedMutableMap.map28430map,
                                        GeneratedMutableMap.map28431map,
                                        GeneratedMutableMap.map28432map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28657map,
                                GeneratedMutableMap.map28658map,
                                GeneratedMutableMap.map28659map,
                                GeneratedMutableMap.map28660map
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
                                GeneratedMutableMap.map28657map,
                                GeneratedMutableMap.map28658map,
                                GeneratedMutableMap.map28659map,
                                GeneratedMutableMap.map28660map
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
                                        GeneratedMutableMap.map28433map,
                                        GeneratedMutableMap.map28434map,
                                        GeneratedMutableMap.map28435map,
                                        GeneratedMutableMap.map28436map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28661map,
                                GeneratedMutableMap.map28662map,
                                GeneratedMutableMap.map28663map,
                                GeneratedMutableMap.map28664map
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
                                GeneratedMutableMap.map28661map,
                                GeneratedMutableMap.map28662map,
                                GeneratedMutableMap.map28663map,
                                GeneratedMutableMap.map28664map
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
                                        GeneratedMutableMap.map28429map,
                                        GeneratedMutableMap.map28430map,
                                        GeneratedMutableMap.map28431map,
                                        GeneratedMutableMap.map28432map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28786map,
                                GeneratedMutableMap.map28787map,
                                GeneratedMutableMap.map28788map,
                                GeneratedMutableMap.map28440map
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
                                GeneratedMutableMap.map28786map,
                                GeneratedMutableMap.map28787map,
                                GeneratedMutableMap.map28788map,
                                GeneratedMutableMap.map28440map
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
                                        GeneratedMutableMap.map28433map,
                                        GeneratedMutableMap.map28434map,
                                        GeneratedMutableMap.map28435map,
                                        GeneratedMutableMap.map28436map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28789map,
                                GeneratedMutableMap.map28790map,
                                GeneratedMutableMap.map28791map,
                                GeneratedMutableMap.map28444map
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
                                GeneratedMutableMap.map28789map,
                                GeneratedMutableMap.map28790map,
                                GeneratedMutableMap.map28791map,
                                GeneratedMutableMap.map28444map
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
                                        GeneratedMutableMap.map28429map,
                                        GeneratedMutableMap.map28430map,
                                        GeneratedMutableMap.map28431map,
                                        GeneratedMutableMap.map28432map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28911map,
                                GeneratedMutableMap.map28912map,
                                GeneratedMutableMap.map28913map,
                                GeneratedMutableMap.map28914map
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
                                GeneratedMutableMap.map28911map,
                                GeneratedMutableMap.map28912map,
                                GeneratedMutableMap.map28913map,
                                GeneratedMutableMap.map28914map
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
                                        GeneratedMutableMap.map28433map,
                                        GeneratedMutableMap.map28434map,
                                        GeneratedMutableMap.map28435map,
                                        GeneratedMutableMap.map28436map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28915map,
                                GeneratedMutableMap.map28916map,
                                GeneratedMutableMap.map28917map,
                                GeneratedMutableMap.map28918map
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
                                GeneratedMutableMap.map28915map,
                                GeneratedMutableMap.map28916map,
                                GeneratedMutableMap.map28917map,
                                GeneratedMutableMap.map28918map
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
                                        GeneratedMutableMap.map28429map,
                                        GeneratedMutableMap.map28430map,
                                        GeneratedMutableMap.map28431map,
                                        GeneratedMutableMap.map28432map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map29040map,
                                GeneratedMutableMap.map29041map,
                                GeneratedMutableMap.map29042map,
                                GeneratedMutableMap.map28660map
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
                                GeneratedMutableMap.map29040map,
                                GeneratedMutableMap.map29041map,
                                GeneratedMutableMap.map29042map,
                                GeneratedMutableMap.map28660map
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
                                        GeneratedMutableMap.map28433map,
                                        GeneratedMutableMap.map28434map,
                                        GeneratedMutableMap.map28435map,
                                        GeneratedMutableMap.map28436map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map29043map,
                                GeneratedMutableMap.map29044map,
                                GeneratedMutableMap.map29045map,
                                GeneratedMutableMap.map28664map
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
                                GeneratedMutableMap.map29043map,
                                GeneratedMutableMap.map29044map,
                                GeneratedMutableMap.map29045map,
                                GeneratedMutableMap.map28664map
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
                                GeneratedMutableMap.map28429map,
                                GeneratedMutableMap.map28430map,
                                GeneratedMutableMap.map28431map,
                                GeneratedMutableMap.map28432map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map29127map,
                            GeneratedMutableMap.map29128map,
                            GeneratedMutableMap.map29129map,
                            GeneratedMutableMap.map29130map
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
                                        GeneratedMutableMap.map28429map,
                                        GeneratedMutableMap.map28430map,
                                        GeneratedMutableMap.map28431map,
                                        GeneratedMutableMap.map28432map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map29247map,
                                GeneratedMutableMap.map29248map,
                                GeneratedMutableMap.map29249map,
                                GeneratedMutableMap.map29130map
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
                                GeneratedMutableMap.map29247map,
                                GeneratedMutableMap.map29248map,
                                GeneratedMutableMap.map29249map,
                                GeneratedMutableMap.map29130map
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
                                        GeneratedMutableMap.map28433map,
                                        GeneratedMutableMap.map28434map,
                                        GeneratedMutableMap.map28435map,
                                        GeneratedMutableMap.map28436map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map29250map,
                                GeneratedMutableMap.map29251map,
                                GeneratedMutableMap.map29252map,
                                GeneratedMutableMap.map29253map
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
                                GeneratedMutableMap.map29250map,
                                GeneratedMutableMap.map29251map,
                                GeneratedMutableMap.map29252map,
                                GeneratedMutableMap.map29253map
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
                                        GeneratedMutableMap.map31317map,
                                        GeneratedMutableMap.map31319map,
                                        GeneratedMutableMap.map31331map,
                                        GeneratedMutableMap.map31333map
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
                                GeneratedMutableMap.map31415map,
                                GeneratedMutableMap.map31416map,
                                GeneratedMutableMap.map31417map,
                                GeneratedMutableMap.map31418map
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
                                GeneratedMutableMap.map31415map,
                                GeneratedMutableMap.map31416map,
                                GeneratedMutableMap.map31417map,
                                GeneratedMutableMap.map31418map
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
                                        GeneratedMutableMap.map31366map,
                                        GeneratedMutableMap.map31368map,
                                        GeneratedMutableMap.map31380map,
                                        GeneratedMutableMap.map31382map
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
                                GeneratedMutableMap.map31419map,
                                GeneratedMutableMap.map31420map,
                                GeneratedMutableMap.map31421map,
                                GeneratedMutableMap.map31422map
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
                                GeneratedMutableMap.map31419map,
                                GeneratedMutableMap.map31420map,
                                GeneratedMutableMap.map31421map,
                                GeneratedMutableMap.map31422map
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
                                        GeneratedMutableMap.map31415map,
                                        GeneratedMutableMap.map31416map,
                                        GeneratedMutableMap.map31417map,
                                        GeneratedMutableMap.map31418map
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
                                GeneratedMutableMap.map31423map,
                                GeneratedMutableMap.map31424map,
                                GeneratedMutableMap.map31425map,
                                GeneratedMutableMap.map31426map
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
                                GeneratedMutableMap.map31423map,
                                GeneratedMutableMap.map31424map,
                                GeneratedMutableMap.map31425map,
                                GeneratedMutableMap.map31426map
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
                                        GeneratedMutableMap.map31419map,
                                        GeneratedMutableMap.map31420map,
                                        GeneratedMutableMap.map31421map,
                                        GeneratedMutableMap.map31422map
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
                                GeneratedMutableMap.map31427map,
                                GeneratedMutableMap.map31428map,
                                GeneratedMutableMap.map31429map,
                                GeneratedMutableMap.map31430map
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
                                GeneratedMutableMap.map31427map,
                                GeneratedMutableMap.map31428map,
                                GeneratedMutableMap.map31429map,
                                GeneratedMutableMap.map31430map
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
                                        GeneratedMutableMap.map5965map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31514map
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
                                        GeneratedMutableMap.map31514map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2",
                                "b1"
                        ), listOf(
                                GeneratedMutableMap.map31515map
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
                                        GeneratedMutableMap.map5965map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31517map
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
                                        GeneratedMutableMap.map31517map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2",
                                "b1"
                        ), listOf(
                                GeneratedMutableMap.map31518map
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
                                AOPDateTime("\"2020-02-26T09:52:23Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5965map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "n"
                        ), listOf(
                                GeneratedMutableMap.map31791map
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
                                        GeneratedMutableMap.map5965map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "iri"
                        ), listOf(
                                GeneratedMutableMap.map31881map
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
                                        GeneratedMutableMap.map31881map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "iri",
                                "uri"
                        ), listOf(
                                GeneratedMutableMap.map31882map
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
                                        GeneratedMutableMap.map32095map,
                                        GeneratedMutableMap.map32096map,
                                        GeneratedMutableMap.map32097map,
                                        GeneratedMutableMap.map32098map,
                                        GeneratedMutableMap.map32099map,
                                        GeneratedMutableMap.map32100map,
                                        GeneratedMutableMap.map32101map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map32109map,
                                GeneratedMutableMap.map32110map,
                                GeneratedMutableMap.map32111map,
                                GeneratedMutableMap.map32112map,
                                GeneratedMutableMap.map32113map,
                                GeneratedMutableMap.map32114map,
                                GeneratedMutableMap.map32115map
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
                                GeneratedMutableMap.map32109map,
                                GeneratedMutableMap.map32110map,
                                GeneratedMutableMap.map32111map,
                                GeneratedMutableMap.map32112map,
                                GeneratedMutableMap.map32113map,
                                GeneratedMutableMap.map32114map,
                                GeneratedMutableMap.map32115map
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
                                        GeneratedMutableMap.map32102map,
                                        GeneratedMutableMap.map32103map,
                                        GeneratedMutableMap.map32104map,
                                        GeneratedMutableMap.map32105map,
                                        GeneratedMutableMap.map32106map,
                                        GeneratedMutableMap.map32107map,
                                        GeneratedMutableMap.map32108map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("integer")
                        ), listOf(
                                GeneratedMutableMap.map32116map,
                                GeneratedMutableMap.map32117map,
                                GeneratedMutableMap.map32118map,
                                GeneratedMutableMap.map32119map,
                                GeneratedMutableMap.map32120map,
                                GeneratedMutableMap.map32121map,
                                GeneratedMutableMap.map32122map
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
                                GeneratedMutableMap.map32116map,
                                GeneratedMutableMap.map32117map,
                                GeneratedMutableMap.map32118map,
                                GeneratedMutableMap.map32119map,
                                GeneratedMutableMap.map32120map,
                                GeneratedMutableMap.map32121map,
                                GeneratedMutableMap.map32122map
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
                                        GeneratedMutableMap.map5965map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "error"
                        ), listOf(
                                GeneratedMutableMap.map32198map
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
                                        GeneratedMutableMap.map35240map,
                                        GeneratedMutableMap.map35241map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "eq"
                        ), listOf(
                                GeneratedMutableMap.map35246map,
                                GeneratedMutableMap.map35247map
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
                                GeneratedMutableMap.map35246map,
                                GeneratedMutableMap.map35247map
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
                                        GeneratedMutableMap.map35243map,
                                        GeneratedMutableMap.map35244map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z"),
                                AOPVariable("eq")
                        ), listOf(
                                GeneratedMutableMap.map35248map,
                                GeneratedMutableMap.map35249map
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
                                GeneratedMutableMap.map35248map,
                                GeneratedMutableMap.map35249map
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
                                        GeneratedMutableMap.map35240map,
                                        GeneratedMutableMap.map35372map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map35374map,
                                GeneratedMutableMap.map35375map
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
                                GeneratedMutableMap.map35374map,
                                GeneratedMutableMap.map35375map
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
                                        GeneratedMutableMap.map35243map,
                                        GeneratedMutableMap.map35373map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map35376map,
                                GeneratedMutableMap.map35377map
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
                                GeneratedMutableMap.map35376map,
                                GeneratedMutableMap.map35377map
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
                                GeneratedMutableMap.map35241map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "twice"
                        ), listOf(
                            GeneratedMutableMap.map35481map
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
                                GeneratedMutableMap.map35481map
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
                            GeneratedMutableMap.map35482map
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
                                        GeneratedMutableMap.map35235map,
                                        GeneratedMutableMap.map35595map
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
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("y"), AOPVariable("y")),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map35236map,
                                        GeneratedMutableMap.map35242map
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
                        POPBind(
                                dictionary,
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map35692map,
                                        GeneratedMutableMap.map35693map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35695map,
                                GeneratedMutableMap.map35696map
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
                                GeneratedMutableMap.map35695map,
                                GeneratedMutableMap.map35696map
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
                                        GeneratedMutableMap.map35236map,
                                        GeneratedMutableMap.map35694map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35697map,
                                GeneratedMutableMap.map35698map
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
                                GeneratedMutableMap.map35697map,
                                GeneratedMutableMap.map35698map
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
                                        GeneratedMutableMap.map35692map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35780map
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
                                GeneratedMutableMap.map35780map
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
                                        GeneratedMutableMap.map35236map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35781map
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
                                GeneratedMutableMap.map35781map
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
                                        GeneratedMutableMap.map35877map,
                                        GeneratedMutableMap.map35878map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35881map,
                                GeneratedMutableMap.map35882map
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
                                GeneratedMutableMap.map35881map,
                                GeneratedMutableMap.map35882map
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
                                        GeneratedMutableMap.map35244map,
                                        GeneratedMutableMap.map35879map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35883map,
                                GeneratedMutableMap.map35884map
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
                                GeneratedMutableMap.map35883map,
                                GeneratedMutableMap.map35884map
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
                                        GeneratedMutableMap.map37582map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map37584map
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
                                GeneratedMutableMap.map37584map
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
                                        GeneratedMutableMap.map37583map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("F"),
                                AOPVariable("L"),
                                AOPVariable("FullName")
                        ), listOf(
                                GeneratedMutableMap.map37585map
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
                                GeneratedMutableMap.map37585map
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
                                        GeneratedMutableMap.map37588map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "FullName",
                                "s",
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map37590map
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
                                GeneratedMutableMap.map37590map
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
                                        GeneratedMutableMap.map37589map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("FullName"),
                                AOPVariable("s"),
                                AOPVariable("p")
                        ), listOf(
                                GeneratedMutableMap.map37591map
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
                                GeneratedMutableMap.map37591map
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
