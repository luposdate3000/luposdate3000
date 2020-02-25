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
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2",
                                "O12"
                        ), listOf(
                                GeneratedMutableMap.map2749map,
                                GeneratedMutableMap.map2750map,
                                GeneratedMutableMap.map2751map,
                                GeneratedMutableMap.map2752map,
                                GeneratedMutableMap.map2753map,
                                GeneratedMutableMap.map2754map,
                                GeneratedMutableMap.map2755map,
                                GeneratedMutableMap.map2756map,
                                GeneratedMutableMap.map2757map
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
                                GeneratedMutableMap.map2749map,
                                GeneratedMutableMap.map2750map,
                                GeneratedMutableMap.map2751map,
                                GeneratedMutableMap.map2752map,
                                GeneratedMutableMap.map2753map,
                                GeneratedMutableMap.map2754map,
                                GeneratedMutableMap.map2755map,
                                GeneratedMutableMap.map2756map,
                                GeneratedMutableMap.map2757map
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
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2",
                                "O12"
                        ), listOf(
                                GeneratedMutableMap.map2749map,
                                GeneratedMutableMap.map2750map,
                                GeneratedMutableMap.map2751map,
                                GeneratedMutableMap.map2752map,
                                GeneratedMutableMap.map2753map,
                                GeneratedMutableMap.map2754map,
                                GeneratedMutableMap.map2755map,
                                GeneratedMutableMap.map2756map,
                                GeneratedMutableMap.map2757map
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
                        POPValues(dictionary, listOf(
                                "S",
                                "O1",
                                "O2",
                                "O12"
                        ), listOf(
                                GeneratedMutableMap.map2749map,
                                GeneratedMutableMap.map2750map,
                                GeneratedMutableMap.map2751map,
                                GeneratedMutableMap.map2752map,
                                GeneratedMutableMap.map2753map,
                                GeneratedMutableMap.map2754map,
                                GeneratedMutableMap.map2755map,
                                GeneratedMutableMap.map2756map,
                                GeneratedMutableMap.map2757map
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
                                        GeneratedMutableMap.map6649map,
                                        GeneratedMutableMap.map6650map,
                                        GeneratedMutableMap.map6651map,
                                        GeneratedMutableMap.map6652map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6653map,
                                GeneratedMutableMap.map6654map,
                                GeneratedMutableMap.map6655map,
                                GeneratedMutableMap.map6656map
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
                                GeneratedMutableMap.map6653map,
                                GeneratedMutableMap.map6654map,
                                GeneratedMutableMap.map6655map,
                                GeneratedMutableMap.map6656map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6653map,
                                GeneratedMutableMap.map6654map,
                                GeneratedMutableMap.map6655map,
                                GeneratedMutableMap.map6656map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6653map,
                                GeneratedMutableMap.map6654map,
                                GeneratedMutableMap.map6655map,
                                GeneratedMutableMap.map6656map
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
                                        GeneratedMutableMap.map6653map,
                                        GeneratedMutableMap.map6654map,
                                        GeneratedMutableMap.map6655map,
                                        GeneratedMutableMap.map6656map
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
                                GeneratedMutableMap.map6804map,
                                GeneratedMutableMap.map6805map,
                                GeneratedMutableMap.map6806map,
                                GeneratedMutableMap.map6807map
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
                                GeneratedMutableMap.map6804map,
                                GeneratedMutableMap.map6805map,
                                GeneratedMutableMap.map6806map,
                                GeneratedMutableMap.map6807map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map6653map,
                                        GeneratedMutableMap.map6654map,
                                        GeneratedMutableMap.map6655map,
                                        GeneratedMutableMap.map6656map
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
                                GeneratedMutableMap.map6804map,
                                GeneratedMutableMap.map6805map,
                                GeneratedMutableMap.map6806map,
                                GeneratedMutableMap.map6807map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6804map,
                                GeneratedMutableMap.map6805map,
                                GeneratedMutableMap.map6806map,
                                GeneratedMutableMap.map6807map
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
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7124map,
                            GeneratedMutableMap.map7125map,
                            GeneratedMutableMap.map7126map,
                            GeneratedMutableMap.map7127map,
                            GeneratedMutableMap.map7112map,
                            GeneratedMutableMap.map7128map,
                            GeneratedMutableMap.map7129map,
                            GeneratedMutableMap.map7130map,
                            GeneratedMutableMap.map7131map,
                            GeneratedMutableMap.map7117map,
                            GeneratedMutableMap.map7132map,
                            GeneratedMutableMap.map7133map,
                            GeneratedMutableMap.map7134map,
                            GeneratedMutableMap.map7135map,
                            GeneratedMutableMap.map7122map,
                            GeneratedMutableMap.map7136map
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
                                        GeneratedMutableMap.map6649map,
                                        GeneratedMutableMap.map6650map,
                                        GeneratedMutableMap.map6651map,
                                        GeneratedMutableMap.map6652map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7446map,
                                GeneratedMutableMap.map7447map,
                                GeneratedMutableMap.map7448map,
                                GeneratedMutableMap.map7449map
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
                                GeneratedMutableMap.map7446map,
                                GeneratedMutableMap.map7447map,
                                GeneratedMutableMap.map7448map,
                                GeneratedMutableMap.map7449map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7446map,
                                GeneratedMutableMap.map7447map,
                                GeneratedMutableMap.map7448map,
                                GeneratedMutableMap.map7449map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7446map,
                                GeneratedMutableMap.map7447map,
                                GeneratedMutableMap.map7448map,
                                GeneratedMutableMap.map7449map
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
                                        GeneratedMutableMap.map5828map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7624map
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
                                        GeneratedMutableMap.map5828map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7624map
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
                                        GeneratedMutableMap.map8044map,
                                        GeneratedMutableMap.map8045map,
                                        GeneratedMutableMap.map8046map,
                                        GeneratedMutableMap.map8047map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8048map,
                                GeneratedMutableMap.map8049map,
                                GeneratedMutableMap.map8050map,
                                GeneratedMutableMap.map8051map
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
                                GeneratedMutableMap.map8048map,
                                GeneratedMutableMap.map8049map,
                                GeneratedMutableMap.map8050map,
                                GeneratedMutableMap.map8051map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map8044map,
                                        GeneratedMutableMap.map8045map,
                                        GeneratedMutableMap.map8046map,
                                        GeneratedMutableMap.map8047map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8048map,
                                GeneratedMutableMap.map8049map,
                                GeneratedMutableMap.map8050map,
                                GeneratedMutableMap.map8051map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8048map,
                                GeneratedMutableMap.map8049map,
                                GeneratedMutableMap.map8050map,
                                GeneratedMutableMap.map8051map
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
                                        GeneratedMutableMap.map8044map,
                                        GeneratedMutableMap.map8045map,
                                        GeneratedMutableMap.map8046map,
                                        GeneratedMutableMap.map8047map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8208map,
                                GeneratedMutableMap.map8209map,
                                GeneratedMutableMap.map8210map,
                                GeneratedMutableMap.map8211map
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
                                GeneratedMutableMap.map8208map,
                                GeneratedMutableMap.map8209map,
                                GeneratedMutableMap.map8210map,
                                GeneratedMutableMap.map8211map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map8044map,
                                        GeneratedMutableMap.map8045map,
                                        GeneratedMutableMap.map8046map,
                                        GeneratedMutableMap.map8047map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8208map,
                                GeneratedMutableMap.map8209map,
                                GeneratedMutableMap.map8210map,
                                GeneratedMutableMap.map8211map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8208map,
                                GeneratedMutableMap.map8209map,
                                GeneratedMutableMap.map8210map,
                                GeneratedMutableMap.map8211map
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
                                GeneratedMutableMap.map6653map,
                                GeneratedMutableMap.map6654map,
                                GeneratedMutableMap.map6655map,
                                GeneratedMutableMap.map6656map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6653map,
                                GeneratedMutableMap.map6654map,
                                GeneratedMutableMap.map6655map,
                                GeneratedMutableMap.map6656map
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
                                GeneratedMutableMap.map6804map,
                                GeneratedMutableMap.map6805map,
                                GeneratedMutableMap.map6806map,
                                GeneratedMutableMap.map6807map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6804map,
                                GeneratedMutableMap.map6805map,
                                GeneratedMutableMap.map6806map,
                                GeneratedMutableMap.map6807map
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
                                GeneratedMutableMap.map7446map,
                                GeneratedMutableMap.map7447map,
                                GeneratedMutableMap.map7448map,
                                GeneratedMutableMap.map7449map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7446map,
                                GeneratedMutableMap.map7447map,
                                GeneratedMutableMap.map7448map,
                                GeneratedMutableMap.map7449map
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
                                        GeneratedMutableMap.map22407map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22413map
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
                                GeneratedMutableMap.map22413map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22407map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22413map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22413map
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
                                        GeneratedMutableMap.map22407map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22685map
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
                                GeneratedMutableMap.map22685map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22407map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22685map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22685map
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
                                        GeneratedMutableMap.map22858map,
                                        GeneratedMutableMap.map22859map,
                                        GeneratedMutableMap.map22860map,
                                        GeneratedMutableMap.map22861map,
                                        GeneratedMutableMap.map22862map,
                                        GeneratedMutableMap.map22863map,
                                        GeneratedMutableMap.map22864map,
                                        GeneratedMutableMap.map22865map,
                                        GeneratedMutableMap.map22866map,
                                        GeneratedMutableMap.map22867map,
                                        GeneratedMutableMap.map22868map,
                                        GeneratedMutableMap.map22869map,
                                        GeneratedMutableMap.map22870map,
                                        GeneratedMutableMap.map22871map,
                                        GeneratedMutableMap.map22872map,
                                        GeneratedMutableMap.map22873map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22874map,
                                GeneratedMutableMap.map22875map,
                                GeneratedMutableMap.map22876map,
                                GeneratedMutableMap.map22877map,
                                GeneratedMutableMap.map22878map,
                                GeneratedMutableMap.map22879map,
                                GeneratedMutableMap.map22880map,
                                GeneratedMutableMap.map22881map,
                                GeneratedMutableMap.map22882map,
                                GeneratedMutableMap.map22883map,
                                GeneratedMutableMap.map22884map,
                                GeneratedMutableMap.map22885map,
                                GeneratedMutableMap.map22886map,
                                GeneratedMutableMap.map22887map,
                                GeneratedMutableMap.map22888map,
                                GeneratedMutableMap.map22889map
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
                                GeneratedMutableMap.map22874map,
                                GeneratedMutableMap.map22875map,
                                GeneratedMutableMap.map22876map,
                                GeneratedMutableMap.map22877map,
                                GeneratedMutableMap.map22878map,
                                GeneratedMutableMap.map22879map,
                                GeneratedMutableMap.map22880map,
                                GeneratedMutableMap.map22881map,
                                GeneratedMutableMap.map22882map,
                                GeneratedMutableMap.map22883map,
                                GeneratedMutableMap.map22884map,
                                GeneratedMutableMap.map22885map,
                                GeneratedMutableMap.map22886map,
                                GeneratedMutableMap.map22887map,
                                GeneratedMutableMap.map22888map,
                                GeneratedMutableMap.map22889map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map22858map,
                                        GeneratedMutableMap.map22859map,
                                        GeneratedMutableMap.map22860map,
                                        GeneratedMutableMap.map22861map,
                                        GeneratedMutableMap.map22862map,
                                        GeneratedMutableMap.map22863map,
                                        GeneratedMutableMap.map22864map,
                                        GeneratedMutableMap.map22865map,
                                        GeneratedMutableMap.map22866map,
                                        GeneratedMutableMap.map22867map,
                                        GeneratedMutableMap.map22868map,
                                        GeneratedMutableMap.map22869map,
                                        GeneratedMutableMap.map22870map,
                                        GeneratedMutableMap.map22871map,
                                        GeneratedMutableMap.map22872map,
                                        GeneratedMutableMap.map22873map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22874map,
                                GeneratedMutableMap.map22875map,
                                GeneratedMutableMap.map22876map,
                                GeneratedMutableMap.map22877map,
                                GeneratedMutableMap.map22878map,
                                GeneratedMutableMap.map22879map,
                                GeneratedMutableMap.map22880map,
                                GeneratedMutableMap.map22881map,
                                GeneratedMutableMap.map22882map,
                                GeneratedMutableMap.map22883map,
                                GeneratedMutableMap.map22884map,
                                GeneratedMutableMap.map22885map,
                                GeneratedMutableMap.map22886map,
                                GeneratedMutableMap.map22887map,
                                GeneratedMutableMap.map22888map,
                                GeneratedMutableMap.map22889map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22874map,
                                GeneratedMutableMap.map22875map,
                                GeneratedMutableMap.map22876map,
                                GeneratedMutableMap.map22877map,
                                GeneratedMutableMap.map22878map,
                                GeneratedMutableMap.map22879map,
                                GeneratedMutableMap.map22880map,
                                GeneratedMutableMap.map22881map,
                                GeneratedMutableMap.map22882map,
                                GeneratedMutableMap.map22883map,
                                GeneratedMutableMap.map22884map,
                                GeneratedMutableMap.map22885map,
                                GeneratedMutableMap.map22886map,
                                GeneratedMutableMap.map22887map,
                                GeneratedMutableMap.map22888map,
                                GeneratedMutableMap.map22889map
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
                                        GeneratedMutableMap.map22407map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23157map
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
                                GeneratedMutableMap.map23157map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22407map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23157map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23157map
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
                                        GeneratedMutableMap.map22407map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23426map
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
                                GeneratedMutableMap.map23426map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22407map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23426map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23426map
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
                                        GeneratedMutableMap.map22858map,
                                        GeneratedMutableMap.map22859map,
                                        GeneratedMutableMap.map22860map,
                                        GeneratedMutableMap.map22861map,
                                        GeneratedMutableMap.map22862map,
                                        GeneratedMutableMap.map22863map,
                                        GeneratedMutableMap.map22864map,
                                        GeneratedMutableMap.map22865map,
                                        GeneratedMutableMap.map22866map,
                                        GeneratedMutableMap.map22867map,
                                        GeneratedMutableMap.map22868map,
                                        GeneratedMutableMap.map22869map,
                                        GeneratedMutableMap.map22870map,
                                        GeneratedMutableMap.map22871map,
                                        GeneratedMutableMap.map22872map,
                                        GeneratedMutableMap.map22873map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22874map,
                                GeneratedMutableMap.map22875map,
                                GeneratedMutableMap.map22876map,
                                GeneratedMutableMap.map22877map,
                                GeneratedMutableMap.map22878map,
                                GeneratedMutableMap.map22879map,
                                GeneratedMutableMap.map22880map,
                                GeneratedMutableMap.map22881map,
                                GeneratedMutableMap.map22882map,
                                GeneratedMutableMap.map23596map,
                                GeneratedMutableMap.map22884map,
                                GeneratedMutableMap.map23597map,
                                GeneratedMutableMap.map23598map,
                                GeneratedMutableMap.map23599map,
                                GeneratedMutableMap.map22888map,
                                GeneratedMutableMap.map22889map
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
                                GeneratedMutableMap.map22874map,
                                GeneratedMutableMap.map22875map,
                                GeneratedMutableMap.map22876map,
                                GeneratedMutableMap.map22877map,
                                GeneratedMutableMap.map22878map,
                                GeneratedMutableMap.map22879map,
                                GeneratedMutableMap.map22880map,
                                GeneratedMutableMap.map22881map,
                                GeneratedMutableMap.map22882map,
                                GeneratedMutableMap.map23596map,
                                GeneratedMutableMap.map22884map,
                                GeneratedMutableMap.map23597map,
                                GeneratedMutableMap.map23598map,
                                GeneratedMutableMap.map23599map,
                                GeneratedMutableMap.map22888map,
                                GeneratedMutableMap.map22889map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map22858map,
                                        GeneratedMutableMap.map22859map,
                                        GeneratedMutableMap.map22860map,
                                        GeneratedMutableMap.map22861map,
                                        GeneratedMutableMap.map22862map,
                                        GeneratedMutableMap.map22863map,
                                        GeneratedMutableMap.map22864map,
                                        GeneratedMutableMap.map22865map,
                                        GeneratedMutableMap.map22866map,
                                        GeneratedMutableMap.map22867map,
                                        GeneratedMutableMap.map22868map,
                                        GeneratedMutableMap.map22869map,
                                        GeneratedMutableMap.map22870map,
                                        GeneratedMutableMap.map22871map,
                                        GeneratedMutableMap.map22872map,
                                        GeneratedMutableMap.map22873map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22874map,
                                GeneratedMutableMap.map22875map,
                                GeneratedMutableMap.map22876map,
                                GeneratedMutableMap.map22877map,
                                GeneratedMutableMap.map22878map,
                                GeneratedMutableMap.map22879map,
                                GeneratedMutableMap.map22880map,
                                GeneratedMutableMap.map22881map,
                                GeneratedMutableMap.map22882map,
                                GeneratedMutableMap.map23596map,
                                GeneratedMutableMap.map22884map,
                                GeneratedMutableMap.map23597map,
                                GeneratedMutableMap.map23598map,
                                GeneratedMutableMap.map23599map,
                                GeneratedMutableMap.map22888map,
                                GeneratedMutableMap.map22889map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22874map,
                                GeneratedMutableMap.map22875map,
                                GeneratedMutableMap.map22876map,
                                GeneratedMutableMap.map22877map,
                                GeneratedMutableMap.map22878map,
                                GeneratedMutableMap.map22879map,
                                GeneratedMutableMap.map22880map,
                                GeneratedMutableMap.map22881map,
                                GeneratedMutableMap.map22882map,
                                GeneratedMutableMap.map23596map,
                                GeneratedMutableMap.map22884map,
                                GeneratedMutableMap.map23597map,
                                GeneratedMutableMap.map23598map,
                                GeneratedMutableMap.map23599map,
                                GeneratedMutableMap.map22888map,
                                GeneratedMutableMap.map22889map
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
                                        GeneratedMutableMap.map23798map,
                                        GeneratedMutableMap.map23799map,
                                        GeneratedMutableMap.map23800map,
                                        GeneratedMutableMap.map23801map,
                                        GeneratedMutableMap.map23802map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "ceil"
                        ), listOf(
                                GeneratedMutableMap.map23993map,
                                GeneratedMutableMap.map23994map,
                                GeneratedMutableMap.map23995map,
                                GeneratedMutableMap.map23996map,
                                GeneratedMutableMap.map23997map
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
                                GeneratedMutableMap.map23993map,
                                GeneratedMutableMap.map23994map,
                                GeneratedMutableMap.map23995map,
                                GeneratedMutableMap.map23996map,
                                GeneratedMutableMap.map23997map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map23798map,
                                        GeneratedMutableMap.map23799map,
                                        GeneratedMutableMap.map23800map,
                                        GeneratedMutableMap.map23801map,
                                        GeneratedMutableMap.map23802map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "ceil"
                        ), listOf(
                                GeneratedMutableMap.map23993map,
                                GeneratedMutableMap.map23994map,
                                GeneratedMutableMap.map23995map,
                                GeneratedMutableMap.map23996map,
                                GeneratedMutableMap.map23997map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "ceil"
                        ), listOf(
                                GeneratedMutableMap.map23993map,
                                GeneratedMutableMap.map23994map,
                                GeneratedMutableMap.map23995map,
                                GeneratedMutableMap.map23996map,
                                GeneratedMutableMap.map23997map
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
                                        GeneratedMutableMap.map23798map,
                                        GeneratedMutableMap.map23799map,
                                        GeneratedMutableMap.map23800map,
                                        GeneratedMutableMap.map23801map,
                                        GeneratedMutableMap.map23802map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "floor"
                        ), listOf(
                                GeneratedMutableMap.map24081map,
                                GeneratedMutableMap.map24082map,
                                GeneratedMutableMap.map24083map,
                                GeneratedMutableMap.map24084map,
                                GeneratedMutableMap.map24085map
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
                                GeneratedMutableMap.map24081map,
                                GeneratedMutableMap.map24082map,
                                GeneratedMutableMap.map24083map,
                                GeneratedMutableMap.map24084map,
                                GeneratedMutableMap.map24085map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map23798map,
                                        GeneratedMutableMap.map23799map,
                                        GeneratedMutableMap.map23800map,
                                        GeneratedMutableMap.map23801map,
                                        GeneratedMutableMap.map23802map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "floor"
                        ), listOf(
                                GeneratedMutableMap.map24081map,
                                GeneratedMutableMap.map24082map,
                                GeneratedMutableMap.map24083map,
                                GeneratedMutableMap.map24084map,
                                GeneratedMutableMap.map24085map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "floor"
                        ), listOf(
                                GeneratedMutableMap.map24081map,
                                GeneratedMutableMap.map24082map,
                                GeneratedMutableMap.map24083map,
                                GeneratedMutableMap.map24084map,
                                GeneratedMutableMap.map24085map
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
                                        GeneratedMutableMap.map23798map,
                                        GeneratedMutableMap.map23799map,
                                        GeneratedMutableMap.map23800map,
                                        GeneratedMutableMap.map23801map,
                                        GeneratedMutableMap.map23802map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "round"
                        ), listOf(
                                GeneratedMutableMap.map24169map,
                                GeneratedMutableMap.map24170map,
                                GeneratedMutableMap.map24171map,
                                GeneratedMutableMap.map24172map,
                                GeneratedMutableMap.map24173map
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
                                GeneratedMutableMap.map24169map,
                                GeneratedMutableMap.map24170map,
                                GeneratedMutableMap.map24171map,
                                GeneratedMutableMap.map24172map,
                                GeneratedMutableMap.map24173map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map23798map,
                                        GeneratedMutableMap.map23799map,
                                        GeneratedMutableMap.map23800map,
                                        GeneratedMutableMap.map23801map,
                                        GeneratedMutableMap.map23802map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "round"
                        ), listOf(
                                GeneratedMutableMap.map24169map,
                                GeneratedMutableMap.map24170map,
                                GeneratedMutableMap.map24171map,
                                GeneratedMutableMap.map24172map,
                                GeneratedMutableMap.map24173map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "round"
                        ), listOf(
                                GeneratedMutableMap.map24169map,
                                GeneratedMutableMap.map24170map,
                                GeneratedMutableMap.map24171map,
                                GeneratedMutableMap.map24172map,
                                GeneratedMutableMap.map24173map
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
                                        GeneratedMutableMap.map24270map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24271map
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
                                GeneratedMutableMap.map24271map
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
                                POPValues(dictionary, listOf(
                                        "str1",
                                        "str2"
                                ), listOf(
                                        GeneratedMutableMap.map24270map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24271map
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
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24271map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24926map,
                                GeneratedMutableMap.map24927map,
                                GeneratedMutableMap.map24928map,
                                GeneratedMutableMap.map24929map,
                                GeneratedMutableMap.map24930map,
                                GeneratedMutableMap.map24931map,
                                GeneratedMutableMap.map24932map,
                                GeneratedMutableMap.map24933map,
                                GeneratedMutableMap.map24934map,
                                GeneratedMutableMap.map24935map,
                                GeneratedMutableMap.map24936map,
                                GeneratedMutableMap.map24937map,
                                GeneratedMutableMap.map24938map,
                                GeneratedMutableMap.map24939map,
                                GeneratedMutableMap.map24940map,
                                GeneratedMutableMap.map24941map,
                                GeneratedMutableMap.map24942map,
                                GeneratedMutableMap.map24943map,
                                GeneratedMutableMap.map24944map,
                                GeneratedMutableMap.map24945map,
                                GeneratedMutableMap.map24946map,
                                GeneratedMutableMap.map24947map,
                                GeneratedMutableMap.map24948map,
                                GeneratedMutableMap.map24949map,
                                GeneratedMutableMap.map24950map,
                                GeneratedMutableMap.map24951map,
                                GeneratedMutableMap.map24952map,
                                GeneratedMutableMap.map24953map,
                                GeneratedMutableMap.map24954map,
                                GeneratedMutableMap.map24955map,
                                GeneratedMutableMap.map24956map,
                                GeneratedMutableMap.map24957map,
                                GeneratedMutableMap.map24958map,
                                GeneratedMutableMap.map24959map,
                                GeneratedMutableMap.map24960map,
                                GeneratedMutableMap.map24961map,
                                GeneratedMutableMap.map24962map,
                                GeneratedMutableMap.map24963map,
                                GeneratedMutableMap.map24964map,
                                GeneratedMutableMap.map24965map,
                                GeneratedMutableMap.map24966map,
                                GeneratedMutableMap.map24967map,
                                GeneratedMutableMap.map24968map,
                                GeneratedMutableMap.map24969map,
                                GeneratedMutableMap.map24970map,
                                GeneratedMutableMap.map24971map,
                                GeneratedMutableMap.map24972map,
                                GeneratedMutableMap.map24973map,
                                GeneratedMutableMap.map24974map
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
                                GeneratedMutableMap.map24926map,
                                GeneratedMutableMap.map24927map,
                                GeneratedMutableMap.map24928map,
                                GeneratedMutableMap.map24929map,
                                GeneratedMutableMap.map24930map,
                                GeneratedMutableMap.map24931map,
                                GeneratedMutableMap.map24932map,
                                GeneratedMutableMap.map24933map,
                                GeneratedMutableMap.map24934map,
                                GeneratedMutableMap.map24935map,
                                GeneratedMutableMap.map24936map,
                                GeneratedMutableMap.map24937map,
                                GeneratedMutableMap.map24938map,
                                GeneratedMutableMap.map24939map,
                                GeneratedMutableMap.map24940map,
                                GeneratedMutableMap.map24941map,
                                GeneratedMutableMap.map24942map,
                                GeneratedMutableMap.map24943map,
                                GeneratedMutableMap.map24944map,
                                GeneratedMutableMap.map24945map,
                                GeneratedMutableMap.map24946map,
                                GeneratedMutableMap.map24947map,
                                GeneratedMutableMap.map24948map,
                                GeneratedMutableMap.map24949map,
                                GeneratedMutableMap.map24950map,
                                GeneratedMutableMap.map24951map,
                                GeneratedMutableMap.map24952map,
                                GeneratedMutableMap.map24953map,
                                GeneratedMutableMap.map24954map,
                                GeneratedMutableMap.map24955map,
                                GeneratedMutableMap.map24956map,
                                GeneratedMutableMap.map24957map,
                                GeneratedMutableMap.map24958map,
                                GeneratedMutableMap.map24959map,
                                GeneratedMutableMap.map24960map,
                                GeneratedMutableMap.map24961map,
                                GeneratedMutableMap.map24962map,
                                GeneratedMutableMap.map24963map,
                                GeneratedMutableMap.map24964map,
                                GeneratedMutableMap.map24965map,
                                GeneratedMutableMap.map24966map,
                                GeneratedMutableMap.map24967map,
                                GeneratedMutableMap.map24968map,
                                GeneratedMutableMap.map24969map,
                                GeneratedMutableMap.map24970map,
                                GeneratedMutableMap.map24971map,
                                GeneratedMutableMap.map24972map,
                                GeneratedMutableMap.map24973map,
                                GeneratedMutableMap.map24974map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24926map,
                                GeneratedMutableMap.map24927map,
                                GeneratedMutableMap.map24928map,
                                GeneratedMutableMap.map24929map,
                                GeneratedMutableMap.map24930map,
                                GeneratedMutableMap.map24931map,
                                GeneratedMutableMap.map24932map,
                                GeneratedMutableMap.map24933map,
                                GeneratedMutableMap.map24934map,
                                GeneratedMutableMap.map24935map,
                                GeneratedMutableMap.map24936map,
                                GeneratedMutableMap.map24937map,
                                GeneratedMutableMap.map24938map,
                                GeneratedMutableMap.map24939map,
                                GeneratedMutableMap.map24940map,
                                GeneratedMutableMap.map24941map,
                                GeneratedMutableMap.map24942map,
                                GeneratedMutableMap.map24943map,
                                GeneratedMutableMap.map24944map,
                                GeneratedMutableMap.map24945map,
                                GeneratedMutableMap.map24946map,
                                GeneratedMutableMap.map24947map,
                                GeneratedMutableMap.map24948map,
                                GeneratedMutableMap.map24949map,
                                GeneratedMutableMap.map24950map,
                                GeneratedMutableMap.map24951map,
                                GeneratedMutableMap.map24952map,
                                GeneratedMutableMap.map24953map,
                                GeneratedMutableMap.map24954map,
                                GeneratedMutableMap.map24955map,
                                GeneratedMutableMap.map24956map,
                                GeneratedMutableMap.map24957map,
                                GeneratedMutableMap.map24958map,
                                GeneratedMutableMap.map24959map,
                                GeneratedMutableMap.map24960map,
                                GeneratedMutableMap.map24961map,
                                GeneratedMutableMap.map24962map,
                                GeneratedMutableMap.map24963map,
                                GeneratedMutableMap.map24964map,
                                GeneratedMutableMap.map24965map,
                                GeneratedMutableMap.map24966map,
                                GeneratedMutableMap.map24967map,
                                GeneratedMutableMap.map24968map,
                                GeneratedMutableMap.map24969map,
                                GeneratedMutableMap.map24970map,
                                GeneratedMutableMap.map24971map,
                                GeneratedMutableMap.map24972map,
                                GeneratedMutableMap.map24973map,
                                GeneratedMutableMap.map24974map
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
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24926map,
                                GeneratedMutableMap.map24927map,
                                GeneratedMutableMap.map24928map,
                                GeneratedMutableMap.map24929map,
                                GeneratedMutableMap.map24930map,
                                GeneratedMutableMap.map24931map,
                                GeneratedMutableMap.map24932map,
                                GeneratedMutableMap.map24933map,
                                GeneratedMutableMap.map24934map,
                                GeneratedMutableMap.map24935map,
                                GeneratedMutableMap.map24936map,
                                GeneratedMutableMap.map24937map,
                                GeneratedMutableMap.map24938map,
                                GeneratedMutableMap.map24939map,
                                GeneratedMutableMap.map24940map,
                                GeneratedMutableMap.map24941map,
                                GeneratedMutableMap.map24942map,
                                GeneratedMutableMap.map24943map,
                                GeneratedMutableMap.map24944map,
                                GeneratedMutableMap.map24945map,
                                GeneratedMutableMap.map24946map,
                                GeneratedMutableMap.map24947map,
                                GeneratedMutableMap.map24948map,
                                GeneratedMutableMap.map24949map,
                                GeneratedMutableMap.map24950map,
                                GeneratedMutableMap.map24951map,
                                GeneratedMutableMap.map24952map,
                                GeneratedMutableMap.map24953map,
                                GeneratedMutableMap.map24954map,
                                GeneratedMutableMap.map24955map,
                                GeneratedMutableMap.map24956map,
                                GeneratedMutableMap.map24957map,
                                GeneratedMutableMap.map24958map,
                                GeneratedMutableMap.map24959map,
                                GeneratedMutableMap.map24960map,
                                GeneratedMutableMap.map24961map,
                                GeneratedMutableMap.map24962map,
                                GeneratedMutableMap.map24963map,
                                GeneratedMutableMap.map24964map,
                                GeneratedMutableMap.map24965map,
                                GeneratedMutableMap.map24966map,
                                GeneratedMutableMap.map24967map,
                                GeneratedMutableMap.map24968map,
                                GeneratedMutableMap.map24969map,
                                GeneratedMutableMap.map24970map,
                                GeneratedMutableMap.map24971map,
                                GeneratedMutableMap.map24972map,
                                GeneratedMutableMap.map24973map,
                                GeneratedMutableMap.map24974map
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
                                        GeneratedMutableMap.map22406map,
                                        GeneratedMutableMap.map22407map,
                                        GeneratedMutableMap.map22408map,
                                        GeneratedMutableMap.map22409map,
                                        GeneratedMutableMap.map22410map,
                                        GeneratedMutableMap.map22411map,
                                        GeneratedMutableMap.map22412map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map25172map,
                                GeneratedMutableMap.map25173map,
                                GeneratedMutableMap.map25174map,
                                GeneratedMutableMap.map25175map,
                                GeneratedMutableMap.map25176map,
                                GeneratedMutableMap.map25177map,
                                GeneratedMutableMap.map25178map
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
                                GeneratedMutableMap.map25172map,
                                GeneratedMutableMap.map25173map,
                                GeneratedMutableMap.map25174map,
                                GeneratedMutableMap.map25175map,
                                GeneratedMutableMap.map25176map,
                                GeneratedMutableMap.map25177map,
                                GeneratedMutableMap.map25178map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22406map,
                                        GeneratedMutableMap.map22407map,
                                        GeneratedMutableMap.map22408map,
                                        GeneratedMutableMap.map22409map,
                                        GeneratedMutableMap.map22410map,
                                        GeneratedMutableMap.map22411map,
                                        GeneratedMutableMap.map22412map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map25172map,
                                GeneratedMutableMap.map25173map,
                                GeneratedMutableMap.map25174map,
                                GeneratedMutableMap.map25175map,
                                GeneratedMutableMap.map25176map,
                                GeneratedMutableMap.map25177map,
                                GeneratedMutableMap.map25178map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map25172map,
                                GeneratedMutableMap.map25173map,
                                GeneratedMutableMap.map25174map,
                                GeneratedMutableMap.map25175map,
                                GeneratedMutableMap.map25176map,
                                GeneratedMutableMap.map25177map,
                                GeneratedMutableMap.map25178map
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
                                        GeneratedMutableMap.map22406map,
                                        GeneratedMutableMap.map22407map,
                                        GeneratedMutableMap.map22408map,
                                        GeneratedMutableMap.map22409map,
                                        GeneratedMutableMap.map22410map,
                                        GeneratedMutableMap.map22411map,
                                        GeneratedMutableMap.map22412map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map25278map,
                                GeneratedMutableMap.map25279map,
                                GeneratedMutableMap.map25280map,
                                GeneratedMutableMap.map25281map,
                                GeneratedMutableMap.map25282map,
                                GeneratedMutableMap.map25283map,
                                GeneratedMutableMap.map25284map
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
                                GeneratedMutableMap.map25278map,
                                GeneratedMutableMap.map25279map,
                                GeneratedMutableMap.map25280map,
                                GeneratedMutableMap.map25281map,
                                GeneratedMutableMap.map25282map,
                                GeneratedMutableMap.map25283map,
                                GeneratedMutableMap.map25284map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22406map,
                                        GeneratedMutableMap.map22407map,
                                        GeneratedMutableMap.map22408map,
                                        GeneratedMutableMap.map22409map,
                                        GeneratedMutableMap.map22410map,
                                        GeneratedMutableMap.map22411map,
                                        GeneratedMutableMap.map22412map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map25278map,
                                GeneratedMutableMap.map25279map,
                                GeneratedMutableMap.map25280map,
                                GeneratedMutableMap.map25281map,
                                GeneratedMutableMap.map25282map,
                                GeneratedMutableMap.map25283map,
                                GeneratedMutableMap.map25284map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map25278map,
                                GeneratedMutableMap.map25279map,
                                GeneratedMutableMap.map25280map,
                                GeneratedMutableMap.map25281map,
                                GeneratedMutableMap.map25282map,
                                GeneratedMutableMap.map25283map,
                                GeneratedMutableMap.map25284map
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
                                        GeneratedMutableMap.map22406map,
                                        GeneratedMutableMap.map22407map,
                                        GeneratedMutableMap.map22408map,
                                        GeneratedMutableMap.map22409map,
                                        GeneratedMutableMap.map22410map,
                                        GeneratedMutableMap.map22411map,
                                        GeneratedMutableMap.map22412map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map25384map,
                                GeneratedMutableMap.map25385map,
                                GeneratedMutableMap.map25386map,
                                GeneratedMutableMap.map25387map,
                                GeneratedMutableMap.map25388map,
                                GeneratedMutableMap.map25389map,
                                GeneratedMutableMap.map25390map
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
                                GeneratedMutableMap.map25384map,
                                GeneratedMutableMap.map25385map,
                                GeneratedMutableMap.map25386map,
                                GeneratedMutableMap.map25387map,
                                GeneratedMutableMap.map25388map,
                                GeneratedMutableMap.map25389map,
                                GeneratedMutableMap.map25390map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22406map,
                                        GeneratedMutableMap.map22407map,
                                        GeneratedMutableMap.map22408map,
                                        GeneratedMutableMap.map22409map,
                                        GeneratedMutableMap.map22410map,
                                        GeneratedMutableMap.map22411map,
                                        GeneratedMutableMap.map22412map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map25384map,
                                GeneratedMutableMap.map25385map,
                                GeneratedMutableMap.map25386map,
                                GeneratedMutableMap.map25387map,
                                GeneratedMutableMap.map25388map,
                                GeneratedMutableMap.map25389map,
                                GeneratedMutableMap.map25390map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map25384map,
                                GeneratedMutableMap.map25385map,
                                GeneratedMutableMap.map25386map,
                                GeneratedMutableMap.map25387map,
                                GeneratedMutableMap.map25388map,
                                GeneratedMutableMap.map25389map,
                                GeneratedMutableMap.map25390map
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
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map
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
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("y"), AOPVariable("x")),
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
                                GeneratedMutableMap.map26627map,
                                GeneratedMutableMap.map26628map,
                                GeneratedMutableMap.map26629map,
                                GeneratedMutableMap.map26630map,
                                GeneratedMutableMap.map26631map
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
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
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
                        POPBind(
                                dictionary,
                                AOPVariable("hash"),
                                AOPBuildInCallMD5(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map26960map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map26961map
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
                                GeneratedMutableMap.map26961map
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
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map26960map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map26961map
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
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map26961map
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
                                        GeneratedMutableMap.map27031map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27032map
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
                                GeneratedMutableMap.map27032map
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
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27031map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27032map
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
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27032map
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
                                        GeneratedMutableMap.map26960map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27102map
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
                                GeneratedMutableMap.map27102map
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
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map26960map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27102map
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
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27102map
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
                                        GeneratedMutableMap.map27172map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27173map
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
                                GeneratedMutableMap.map27173map
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
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27172map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27173map
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
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27173map
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
                                        GeneratedMutableMap.map26960map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27243map
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
                                GeneratedMutableMap.map27243map
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
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map26960map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27243map
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
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27243map
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
                                        GeneratedMutableMap.map27172map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27313map
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
                                GeneratedMutableMap.map27313map
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
                                POPValues(dictionary, listOf(
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map27172map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27313map
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
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27313map
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
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27484map,
                                GeneratedMutableMap.map27485map,
                                GeneratedMutableMap.map27486map,
                                GeneratedMutableMap.map27487map
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
                                GeneratedMutableMap.map27484map,
                                GeneratedMutableMap.map27485map,
                                GeneratedMutableMap.map27486map,
                                GeneratedMutableMap.map27487map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27484map,
                                GeneratedMutableMap.map27485map,
                                GeneratedMutableMap.map27486map,
                                GeneratedMutableMap.map27487map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27484map,
                                GeneratedMutableMap.map27485map,
                                GeneratedMutableMap.map27486map,
                                GeneratedMutableMap.map27487map
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
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27566map,
                                GeneratedMutableMap.map27567map,
                                GeneratedMutableMap.map27568map,
                                GeneratedMutableMap.map27569map
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
                                GeneratedMutableMap.map27566map,
                                GeneratedMutableMap.map27567map,
                                GeneratedMutableMap.map27568map,
                                GeneratedMutableMap.map27569map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27566map,
                                GeneratedMutableMap.map27567map,
                                GeneratedMutableMap.map27568map,
                                GeneratedMutableMap.map27569map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27566map,
                                GeneratedMutableMap.map27567map,
                                GeneratedMutableMap.map27568map,
                                GeneratedMutableMap.map27569map
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
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27685map,
                                GeneratedMutableMap.map27686map,
                                GeneratedMutableMap.map27687map,
                                GeneratedMutableMap.map27688map
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
                                GeneratedMutableMap.map27685map,
                                GeneratedMutableMap.map27686map,
                                GeneratedMutableMap.map27687map,
                                GeneratedMutableMap.map27688map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27685map,
                                GeneratedMutableMap.map27686map,
                                GeneratedMutableMap.map27687map,
                                GeneratedMutableMap.map27688map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27685map,
                                GeneratedMutableMap.map27686map,
                                GeneratedMutableMap.map27687map,
                                GeneratedMutableMap.map27688map
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
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27804map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27487map
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
                                GeneratedMutableMap.map27804map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27487map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27804map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27487map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27804map,
                                GeneratedMutableMap.map27805map,
                                GeneratedMutableMap.map27806map,
                                GeneratedMutableMap.map27487map
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
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27921map,
                                GeneratedMutableMap.map27922map,
                                GeneratedMutableMap.map27923map,
                                GeneratedMutableMap.map27924map
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
                                GeneratedMutableMap.map27921map,
                                GeneratedMutableMap.map27922map,
                                GeneratedMutableMap.map27923map,
                                GeneratedMutableMap.map27924map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27921map,
                                GeneratedMutableMap.map27922map,
                                GeneratedMutableMap.map27923map,
                                GeneratedMutableMap.map27924map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27921map,
                                GeneratedMutableMap.map27922map,
                                GeneratedMutableMap.map27923map,
                                GeneratedMutableMap.map27924map
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
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28040map,
                                GeneratedMutableMap.map28041map,
                                GeneratedMutableMap.map28042map,
                                GeneratedMutableMap.map27688map
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
                                GeneratedMutableMap.map28040map,
                                GeneratedMutableMap.map28041map,
                                GeneratedMutableMap.map28042map,
                                GeneratedMutableMap.map27688map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28040map,
                                GeneratedMutableMap.map28041map,
                                GeneratedMutableMap.map28042map,
                                GeneratedMutableMap.map27688map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28040map,
                                GeneratedMutableMap.map28041map,
                                GeneratedMutableMap.map28042map,
                                GeneratedMutableMap.map27688map
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
                                GeneratedMutableMap.map27480map,
                                GeneratedMutableMap.map27481map,
                                GeneratedMutableMap.map27482map,
                                GeneratedMutableMap.map27483map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28120map,
                            GeneratedMutableMap.map28121map,
                            GeneratedMutableMap.map28122map,
                            GeneratedMutableMap.map28123map
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
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28239map,
                                GeneratedMutableMap.map28240map,
                                GeneratedMutableMap.map28241map,
                                GeneratedMutableMap.map28123map
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
                                GeneratedMutableMap.map28239map,
                                GeneratedMutableMap.map28240map,
                                GeneratedMutableMap.map28241map,
                                GeneratedMutableMap.map28123map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "date"
                                ), listOf(
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27481map,
                                        GeneratedMutableMap.map27482map,
                                        GeneratedMutableMap.map27483map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28239map,
                                GeneratedMutableMap.map28240map,
                                GeneratedMutableMap.map28241map,
                                GeneratedMutableMap.map28123map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28239map,
                                GeneratedMutableMap.map28240map,
                                GeneratedMutableMap.map28241map,
                                GeneratedMutableMap.map28123map
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
                                        GeneratedMutableMap.map30296map,
                                        GeneratedMutableMap.map30298map,
                                        GeneratedMutableMap.map30310map,
                                        GeneratedMutableMap.map30312map
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
                                GeneratedMutableMap.map30345map,
                                GeneratedMutableMap.map30346map,
                                GeneratedMutableMap.map30347map,
                                GeneratedMutableMap.map30348map
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
                                GeneratedMutableMap.map30345map,
                                GeneratedMutableMap.map30346map,
                                GeneratedMutableMap.map30347map,
                                GeneratedMutableMap.map30348map
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
                                POPValues(dictionary, listOf(
                                        "a",
                                        "s1",
                                        "b",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map30296map,
                                        GeneratedMutableMap.map30298map,
                                        GeneratedMutableMap.map30310map,
                                        GeneratedMutableMap.map30312map
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
                                GeneratedMutableMap.map30345map,
                                GeneratedMutableMap.map30346map,
                                GeneratedMutableMap.map30347map,
                                GeneratedMutableMap.map30348map
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
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map30345map,
                                GeneratedMutableMap.map30346map,
                                GeneratedMutableMap.map30347map,
                                GeneratedMutableMap.map30348map
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
                                        GeneratedMutableMap.map30345map,
                                        GeneratedMutableMap.map30346map,
                                        GeneratedMutableMap.map30347map,
                                        GeneratedMutableMap.map30348map
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
                                GeneratedMutableMap.map30349map,
                                GeneratedMutableMap.map30350map,
                                GeneratedMutableMap.map30351map,
                                GeneratedMutableMap.map30352map
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
                                GeneratedMutableMap.map30349map,
                                GeneratedMutableMap.map30350map,
                                GeneratedMutableMap.map30351map,
                                GeneratedMutableMap.map30352map
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
                                POPValues(dictionary, listOf(
                                        "a",
                                        "s1",
                                        "b",
                                        "s2",
                                        "b2"
                                ), listOf(
                                        GeneratedMutableMap.map30345map,
                                        GeneratedMutableMap.map30346map,
                                        GeneratedMutableMap.map30347map,
                                        GeneratedMutableMap.map30348map
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
                                GeneratedMutableMap.map30349map,
                                GeneratedMutableMap.map30350map,
                                GeneratedMutableMap.map30351map,
                                GeneratedMutableMap.map30352map
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
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2",
                                "b2",
                                "b1"
                        ), listOf(
                                GeneratedMutableMap.map30349map,
                                GeneratedMutableMap.map30350map,
                                GeneratedMutableMap.map30351map,
                                GeneratedMutableMap.map30352map
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
                                        GeneratedMutableMap.map5828map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map30432map
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
                                        GeneratedMutableMap.map30432map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2",
                                "b1"
                        ), listOf(
                                GeneratedMutableMap.map30433map
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
                                        GeneratedMutableMap.map5828map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map30435map
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
                                        GeneratedMutableMap.map30435map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b2",
                                "b1"
                        ), listOf(
                                GeneratedMutableMap.map30436map
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
                                AOPDateTime("\"2020-02-25T13:21:51Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5828map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "n"
                        ), listOf(
                                GeneratedMutableMap.map30709map
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
                                        GeneratedMutableMap.map5828map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "iri"
                        ), listOf(
                                GeneratedMutableMap.map30799map
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
                                        GeneratedMutableMap.map30799map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "iri",
                                "uri"
                        ), listOf(
                                GeneratedMutableMap.map30800map
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
                                        GeneratedMutableMap.map31012map,
                                        GeneratedMutableMap.map31013map,
                                        GeneratedMutableMap.map31014map,
                                        GeneratedMutableMap.map31015map,
                                        GeneratedMutableMap.map31016map,
                                        GeneratedMutableMap.map31017map,
                                        GeneratedMutableMap.map31018map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map31019map,
                                GeneratedMutableMap.map31020map,
                                GeneratedMutableMap.map31021map,
                                GeneratedMutableMap.map31022map,
                                GeneratedMutableMap.map31023map,
                                GeneratedMutableMap.map31024map,
                                GeneratedMutableMap.map31025map
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
                                GeneratedMutableMap.map31019map,
                                GeneratedMutableMap.map31020map,
                                GeneratedMutableMap.map31021map,
                                GeneratedMutableMap.map31022map,
                                GeneratedMutableMap.map31023map,
                                GeneratedMutableMap.map31024map,
                                GeneratedMutableMap.map31025map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map31012map,
                                        GeneratedMutableMap.map31013map,
                                        GeneratedMutableMap.map31014map,
                                        GeneratedMutableMap.map31015map,
                                        GeneratedMutableMap.map31016map,
                                        GeneratedMutableMap.map31017map,
                                        GeneratedMutableMap.map31018map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map31019map,
                                GeneratedMutableMap.map31020map,
                                GeneratedMutableMap.map31021map,
                                GeneratedMutableMap.map31022map,
                                GeneratedMutableMap.map31023map,
                                GeneratedMutableMap.map31024map,
                                GeneratedMutableMap.map31025map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map31019map,
                                GeneratedMutableMap.map31020map,
                                GeneratedMutableMap.map31021map,
                                GeneratedMutableMap.map31022map,
                                GeneratedMutableMap.map31023map,
                                GeneratedMutableMap.map31024map,
                                GeneratedMutableMap.map31025map
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
                                        GeneratedMutableMap.map5828map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "error"
                        ), listOf(
                                GeneratedMutableMap.map31094map
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
                                        GeneratedMutableMap.map34080map,
                                        GeneratedMutableMap.map34081map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "eq"
                        ), listOf(
                                GeneratedMutableMap.map34082map,
                                GeneratedMutableMap.map34083map
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
                                GeneratedMutableMap.map34082map,
                                GeneratedMutableMap.map34083map
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
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34080map,
                                        GeneratedMutableMap.map34081map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "eq"
                        ), listOf(
                                GeneratedMutableMap.map34082map,
                                GeneratedMutableMap.map34083map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "eq"
                        ), listOf(
                                GeneratedMutableMap.map34082map,
                                GeneratedMutableMap.map34083map
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
                                        GeneratedMutableMap.map34080map,
                                        GeneratedMutableMap.map34199map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map34200map,
                                GeneratedMutableMap.map34201map
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
                                GeneratedMutableMap.map34200map,
                                GeneratedMutableMap.map34201map
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
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map34080map,
                                        GeneratedMutableMap.map34199map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map34200map,
                                GeneratedMutableMap.map34201map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map34200map,
                                GeneratedMutableMap.map34201map
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
                                GeneratedMutableMap.map34081map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "twice"
                        ), listOf(
                            GeneratedMutableMap.map34302map
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
                                GeneratedMutableMap.map34302map
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
                            GeneratedMutableMap.map34303map
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
                                        GeneratedMutableMap.map34077map,
                                        GeneratedMutableMap.map34415map
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
                        LOPBind(
                                AOPVariable("sum"),
                                AOPAddition(AOPVariable("y"), AOPVariable("y")),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map34077map,
                                        GeneratedMutableMap.map34415map
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
                        POPBind(
                                dictionary,
                                AOPVariable("dt"),
                                AOPBuildInCallDATATYPE(AOPVariable("l")),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map34509map,
                                        GeneratedMutableMap.map34510map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34511map,
                                GeneratedMutableMap.map34512map
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
                                GeneratedMutableMap.map34511map,
                                GeneratedMutableMap.map34512map
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
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map34509map,
                                        GeneratedMutableMap.map34510map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34511map,
                                GeneratedMutableMap.map34512map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34511map,
                                GeneratedMutableMap.map34512map
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
                                        GeneratedMutableMap.map34509map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34592map
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
                                GeneratedMutableMap.map34592map
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
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map34509map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34592map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34592map
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
                                        GeneratedMutableMap.map34684map,
                                        GeneratedMutableMap.map34685map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34686map,
                                GeneratedMutableMap.map34687map
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
                                GeneratedMutableMap.map34686map,
                                GeneratedMutableMap.map34687map
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
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y",
                                        "l"
                                ), listOf(
                                        GeneratedMutableMap.map34684map,
                                        GeneratedMutableMap.map34685map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34686map,
                                GeneratedMutableMap.map34687map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34686map,
                                GeneratedMutableMap.map34687map
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
                                        GeneratedMutableMap.map36331map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map36332map
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
                                GeneratedMutableMap.map36332map
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
                                POPValues(dictionary, listOf(
                                        "P",
                                        "F",
                                        "L"
                                ), listOf(
                                        GeneratedMutableMap.map36331map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map36332map
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
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map36332map
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
                                        GeneratedMutableMap.map36334map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "FullName",
                                "s",
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map36335map
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
                                GeneratedMutableMap.map36335map
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
                                POPValues(dictionary, listOf(
                                        "FullName",
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map36334map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "FullName",
                                "s",
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map36335map
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
                        POPValues(dictionary, listOf(
                                "FullName",
                                "s",
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map36335map
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
