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


class GeneratedPOPProjectionTest {
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1014map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1014map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("P"),
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1168map,
                                        GeneratedMutableMap.map1169map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1168map,
                                GeneratedMutableMap.map1169map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("P"),
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#f1214",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1449map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1168map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "#f1780",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map2002map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1014map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("P"),
                                        AOPVariable("C")
                                ),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#f2043",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map2276map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1168map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("O12"),
                                        AOPVariable("C")
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("sum")
                                ),
                                POPValues(dictionary, listOf(
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map3346map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map3346map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("sum")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "sum"
                                ), listOf(
                                        GeneratedMutableMap.map3533map,
                                        GeneratedMutableMap.map3534map,
                                        GeneratedMutableMap.map3535map,
                                        GeneratedMutableMap.map3536map,
                                        GeneratedMutableMap.map3537map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map3533map,
                                GeneratedMutableMap.map3534map,
                                GeneratedMutableMap.map3535map,
                                GeneratedMutableMap.map3536map,
                                GeneratedMutableMap.map3537map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("avg")
                                ),
                                POPValues(dictionary, listOf(
                                        "avg"
                                ), listOf(
                                        GeneratedMutableMap.map3670map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "avg"
                        ), listOf(
                                GeneratedMutableMap.map3670map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("avg")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#f3715",
                                        "avg"
                                ), listOf(
                                        GeneratedMutableMap.map3988map,
                                        GeneratedMutableMap.map3989map,
                                        GeneratedMutableMap.map3990map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "avg"
                        ), listOf(
                                GeneratedMutableMap.map3991map,
                                GeneratedMutableMap.map3992map,
                                GeneratedMutableMap.map3993map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("min")
                                ),
                                POPValues(dictionary, listOf(
                                        "min"
                                ), listOf(
                                        GeneratedMutableMap.map4116map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "min"
                        ), listOf(
                                GeneratedMutableMap.map4116map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("min")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "min"
                                ), listOf(
                                        GeneratedMutableMap.map4272map,
                                        GeneratedMutableMap.map4273map,
                                        GeneratedMutableMap.map4274map,
                                        GeneratedMutableMap.map4275map,
                                        GeneratedMutableMap.map4276map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "min"
                        ), listOf(
                                GeneratedMutableMap.map4272map,
                                GeneratedMutableMap.map4273map,
                                GeneratedMutableMap.map4274map,
                                GeneratedMutableMap.map4275map,
                                GeneratedMutableMap.map4276map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("max")
                                ),
                                POPValues(dictionary, listOf(
                                        "max"
                                ), listOf(
                                        GeneratedMutableMap.map4425map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "max"
                        ), listOf(
                                GeneratedMutableMap.map4425map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("max")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "max"
                                ), listOf(
                                        GeneratedMutableMap.map4578map,
                                        GeneratedMutableMap.map4579map,
                                        GeneratedMutableMap.map4580map,
                                        GeneratedMutableMap.map4581map,
                                        GeneratedMutableMap.map4582map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "max"
                        ), listOf(
                                GeneratedMutableMap.map4578map,
                                GeneratedMutableMap.map4579map,
                                GeneratedMutableMap.map4580map,
                                GeneratedMutableMap.map4581map,
                                GeneratedMutableMap.map4582map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("sample")
                        ),
                        POPValues(dictionary, listOf(
                                "sample"
                            ), listOf(
                                GeneratedMutableMap.map4730map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sample"
                        ), listOf(
                            GeneratedMutableMap.map4730map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-sample-01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("g"),
                            AOPVariable("avg"),
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "g",
                                "c",
                                "avg"
                            ), listOf(
                                GeneratedMutableMap.map5030map,
                                GeneratedMutableMap.map5031map,
                                GeneratedMutableMap.map5032map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "g",
                            "avg",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map5033map,
                            GeneratedMutableMap.map5034map,
                            GeneratedMutableMap.map5035map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("max")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "max"
                            ), listOf(
                                GeneratedMutableMap.map5142map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "max"
                        ), listOf(
                            GeneratedMutableMap.map5142map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-empty-group.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("z")
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
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6800map,
                                GeneratedMutableMap.map6801map,
                                GeneratedMutableMap.map6802map,
                                GeneratedMutableMap.map6803map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("z")
                                ),
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
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6800map,
                                GeneratedMutableMap.map6801map,
                                GeneratedMutableMap.map6802map,
                                GeneratedMutableMap.map6803map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("z")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map6804map,
                                GeneratedMutableMap.map6805map,
                                GeneratedMutableMap.map6806map,
                                GeneratedMutableMap.map6807map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("z")
                                ),
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
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map6804map,
                                GeneratedMutableMap.map6805map,
                                GeneratedMutableMap.map6806map,
                                GeneratedMutableMap.map6807map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("z"),
                                        AOPVariable("z2")
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
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6960map,
                                GeneratedMutableMap.map6961map,
                                GeneratedMutableMap.map6962map,
                                GeneratedMutableMap.map6963map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("z"),
                                        AOPVariable("z2")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6960map,
                                GeneratedMutableMap.map6961map,
                                GeneratedMutableMap.map6962map,
                                GeneratedMutableMap.map6963map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("z"),
                                        AOPVariable("z2")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("z"),
                                AOPVariable("z2")
                        ), listOf(
                                GeneratedMutableMap.map6964map,
                                GeneratedMutableMap.map6965map,
                                GeneratedMutableMap.map6966map,
                                GeneratedMutableMap.map6967map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("z"),
                                        AOPVariable("z2")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("z"),
                                AOPVariable("z2")
                        ), listOf(
                                GeneratedMutableMap.map6964map,
                                GeneratedMutableMap.map6965map,
                                GeneratedMutableMap.map6966map,
                                GeneratedMutableMap.map6967map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("z"),
                            AOPVariable("s1")
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
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s1"
                        ), listOf(
                            GeneratedMutableMap.map7293map,
                            GeneratedMutableMap.map7294map,
                            GeneratedMutableMap.map7295map,
                            GeneratedMutableMap.map7296map,
                            GeneratedMutableMap.map7297map,
                            GeneratedMutableMap.map7298map,
                            GeneratedMutableMap.map7299map,
                            GeneratedMutableMap.map7300map,
                            GeneratedMutableMap.map7301map,
                            GeneratedMutableMap.map7302map,
                            GeneratedMutableMap.map7303map,
                            GeneratedMutableMap.map7304map,
                            GeneratedMutableMap.map7305map,
                            GeneratedMutableMap.map7306map,
                            GeneratedMutableMap.map7307map,
                            GeneratedMutableMap.map7308map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map7359map,
                                        GeneratedMutableMap.map7360map,
                                        GeneratedMutableMap.map7361map,
                                        GeneratedMutableMap.map7362map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7359map,
                                GeneratedMutableMap.map7360map,
                                GeneratedMutableMap.map7361map,
                                GeneratedMutableMap.map7362map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                POPBindUndefined(
                                        dictionary,
                                        AOPVariable("z"),
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
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7359map,
                                GeneratedMutableMap.map7360map,
                                GeneratedMutableMap.map7361map,
                                GeneratedMutableMap.map7362map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map7364map,
                                        GeneratedMutableMap.map7365map,
                                        GeneratedMutableMap.map7366map,
                                        GeneratedMutableMap.map7367map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7364map,
                                GeneratedMutableMap.map7365map,
                                GeneratedMutableMap.map7366map,
                                GeneratedMutableMap.map7367map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
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
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                        }()

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7364map,
                                GeneratedMutableMap.map7365map,
                                GeneratedMutableMap.map7366map,
                                GeneratedMutableMap.map7367map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map7609map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7609map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                POPFilter(
                                        dictionary,
                                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
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

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7609map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map7613map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7613map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                LOPFilter(
                                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
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

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7613map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
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
                        ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("v"),
                                        AOPVariable("z")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map8393map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8393map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("v"),
                                        AOPVariable("z")
                                ),
                                POPFilter(
                                        dictionary,
                                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
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

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8393map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("v"),
                                        AOPVariable("z")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("v"),
                                        AOPVariable("z")
                                ), listOf(
                                        GeneratedMutableMap.map8397map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map8397map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("v"),
                                        AOPVariable("z")
                                ),
                                LOPFilter(
                                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
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

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map8397map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("book"),
                                        AOPVariable("title"),
                                        AOPVariable("price")
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8588map,
                                        GeneratedMutableMap.map8589map
                                )
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("book"),
                                        AOPVariable("title"),
                                        AOPVariable("price")
                                ),
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
                                        false)

                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("book"),
                                        AOPVariable("title"),
                                        AOPVariable("price")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("book"),
                                        AOPVariable("title"),
                                        AOPVariable("price")
                                ), listOf(
                                        GeneratedMutableMap.map8590map,
                                        GeneratedMutableMap.map8591map
                                )
                                )
                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("book"),
                                        AOPVariable("title"),
                                        AOPVariable("price")
                                ),
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
                                        false)

                        ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o")
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o")
                                ),
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o")
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
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map8669map,
                                GeneratedMutableMap.map8670map,
                                GeneratedMutableMap.map8671map,
                                GeneratedMutableMap.map8672map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o")
                                ),
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

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map8669map,
                                GeneratedMutableMap.map8670map,
                                GeneratedMutableMap.map8671map,
                                GeneratedMutableMap.map8672map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
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
                                        false)

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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o1"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8802map,
                                GeneratedMutableMap.map8803map,
                                GeneratedMutableMap.map8804map,
                                GeneratedMutableMap.map8805map,
                                GeneratedMutableMap.map8806map,
                                GeneratedMutableMap.map8807map,
                                GeneratedMutableMap.map8808map,
                                GeneratedMutableMap.map8809map,
                                GeneratedMutableMap.map8810map,
                                GeneratedMutableMap.map8811map,
                                GeneratedMutableMap.map8812map,
                                GeneratedMutableMap.map8813map,
                                GeneratedMutableMap.map8814map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o1"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8802map,
                                GeneratedMutableMap.map8803map,
                                GeneratedMutableMap.map8804map,
                                GeneratedMutableMap.map8805map,
                                GeneratedMutableMap.map8806map,
                                GeneratedMutableMap.map8807map,
                                GeneratedMutableMap.map8808map,
                                GeneratedMutableMap.map8809map,
                                GeneratedMutableMap.map8810map,
                                GeneratedMutableMap.map8811map,
                                GeneratedMutableMap.map8812map,
                                GeneratedMutableMap.map8813map,
                                GeneratedMutableMap.map8814map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p1"),
                                        AOPVariable("o1")
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p1"),
                                        AOPVariable("o1")
                                ),
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p1"),
                                        AOPVariable("o1")
                                ),
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
                                )
                        ),
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p1"),
                                        AOPVariable("o1")
                                ),
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

                        ),
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
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
                                        true)

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
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o1"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8802map,
                                GeneratedMutableMap.map8803map,
                                GeneratedMutableMap.map8804map,
                                GeneratedMutableMap.map9170map,
                                GeneratedMutableMap.map9171map,
                                GeneratedMutableMap.map9172map,
                                GeneratedMutableMap.map9173map,
                                GeneratedMutableMap.map9174map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("o1"),
                                        AOPVariable("o2")
                                ),
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
                                        true)

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o1"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8802map,
                                GeneratedMutableMap.map8803map,
                                GeneratedMutableMap.map8804map,
                                GeneratedMutableMap.map9170map,
                                GeneratedMutableMap.map9171map,
                                GeneratedMutableMap.map9172map,
                                GeneratedMutableMap.map9173map,
                                GeneratedMutableMap.map9174map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("book"),
                                        AOPVariable("title"),
                                        AOPVariable("price")
                                ),
                                POPValues(dictionary, listOf(
                                        "book",
                                        "title",
                                        "price"
                                ), listOf(
                                        GeneratedMutableMap.map8588map
                                )
                                )
                        ),
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map9723map,
                                        GeneratedMutableMap.map9724map,
                                        GeneratedMutableMap.map9725map,
                                        GeneratedMutableMap.map9726map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9723map,
                                GeneratedMutableMap.map9724map,
                                GeneratedMutableMap.map9725map,
                                GeneratedMutableMap.map9726map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                POPRename(
                                        dictionary,
                                        AOPVariable("o"),
                                        AOPVariable("o"),
                                        POPRename(
                                                dictionary,
                                                AOPVariable("p"),
                                                AOPVariable("p"),
                                                POPRename(
                                                        dictionary,
                                                        AOPVariable("s"),
                                                        AOPVariable("s"),
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                        }()

                                                )

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9723map,
                                GeneratedMutableMap.map9724map,
                                GeneratedMutableMap.map9725map,
                                GeneratedMutableMap.map9726map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map9727map,
                                        GeneratedMutableMap.map9728map,
                                        GeneratedMutableMap.map9729map,
                                        GeneratedMutableMap.map9730map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9727map,
                                GeneratedMutableMap.map9728map,
                                GeneratedMutableMap.map9729map,
                                GeneratedMutableMap.map9730map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                LOPRename(
                                        AOPVariable("o"),
                                        AOPVariable("o"),
                                        LOPRename(
                                                AOPVariable("p"),
                                                AOPVariable("p"),
                                                LOPRename(
                                                        AOPVariable("s"),
                                                        AOPVariable("s"),
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                        }()

                                                )

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9727map,
                                GeneratedMutableMap.map9728map,
                                GeneratedMutableMap.map9729map,
                                GeneratedMutableMap.map9730map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map15815map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15815map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/ns#myBanana>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/ns#banana>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/ns#banana>", false, true, true, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15815map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x")
                                ), listOf(
                                        GeneratedMutableMap.map15816map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15816map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/ns#myBanana>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/ns#banana>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://example.org/ns#banana>"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15816map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#c"
                                ), listOf(
                                        GeneratedMutableMap.map15855map,
                                        GeneratedMutableMap.map15856map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15859map,
                                GeneratedMutableMap.map15859map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/ns#a1>", "<http://example.org/ns#b1>", "_:c1"))
                                    graph.addData(1L, listOf("<http://example.org/ns#a1>", "<http://example.org/ns#b1>", "_:c2"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://example.org/ns#b1>", "#c", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15859map,
                                GeneratedMutableMap.map15859map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("#c")
                                ), listOf(
                                        GeneratedMutableMap.map15857map,
                                        GeneratedMutableMap.map15858map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15860map,
                                GeneratedMutableMap.map15860map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/ns#a1>", "<http://example.org/ns#b1>", "_:c1"))
                                    graph.addData(1L, listOf("<http://example.org/ns#a1>", "<http://example.org/ns#b1>", "_:c2"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://example.org/ns#b1>"), AOPVariable("#c"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15860map,
                                GeneratedMutableMap.map15860map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map15902map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15902map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/x>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>", false, true, true, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15902map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x")
                                ), listOf(
                                        GeneratedMutableMap.map15903map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15903map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/x/x>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://example.org/x/c>"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15903map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map15941map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15941map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16122map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16122map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map16247map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map16247map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/ns#d>", "<http://www.w3.org/2000/01/rdf-schema#range>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/ns#d>", "<http://www.w3.org/2000/01/rdf-schema#range>", "x", true, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map16247map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x")
                                ), listOf(
                                        GeneratedMutableMap.map16248map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map16248map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/ns#d>", "<http://www.w3.org/2000/01/rdf-schema#range>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/ns#d>"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#range>"), AOPVariable("x"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map16248map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16388map
                            )
                        )
                    ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("f")
                                ),
                                POPValues(dictionary, listOf(
                                        "f"
                                ), listOf(
                                        GeneratedMutableMap.map16433map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "f"
                        ), listOf(
                                GeneratedMutableMap.map16433map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("f")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/ns#favourite-fruit>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>", "<http://example.org/ns#apple>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/ns#favourite-fruit>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>", "f", true, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "f"
                        ), listOf(
                                GeneratedMutableMap.map16433map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("f")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("f")
                                ), listOf(
                                        GeneratedMutableMap.map16434map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("f")
                        ), listOf(
                                GeneratedMutableMap.map16434map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("f")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/ns#favourite-fruit>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>", "<http://example.org/ns#apple>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/ns#favourite-fruit>"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>"), AOPVariable("f"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("f")
                        ), listOf(
                                GeneratedMutableMap.map16434map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map16665map,
                                        GeneratedMutableMap.map16666map
                                )
                                )
                        ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ),
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
                                        false)

                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map16667map,
                                        GeneratedMutableMap.map16668map
                                )
                                )
                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ),
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
                                        false)

                        ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16711map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16711map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q1.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("p"),
                                        AOPVariable("v")
                                ),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map17236map
                                )
                                )
                        ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("p"),
                                        AOPVariable("v")
                                ),
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
                                        false)

                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("p"),
                                        AOPVariable("v")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("p"),
                                        AOPVariable("v")
                                ), listOf(
                                        GeneratedMutableMap.map17240map
                                )
                                )
                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("p"),
                                        AOPVariable("v")
                                ),
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
                                        false)

                        ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map17286map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map17286map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://xmlns.com/foaf/0.1/name>", "\"name\"@en"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://xmlns.com/foaf/0.1/name>", "\"name\"@en", false, true, true, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map17286map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x")
                                ), listOf(
                                        GeneratedMutableMap.map17287map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map17287map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#b>", "<http://xmlns.com/foaf/0.1/name>", "\"name\"@en"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"), AOPVariable.calculate("\"name\"@en"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map17287map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("z")
                                ),
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
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6800map,
                                GeneratedMutableMap.map6801map,
                                GeneratedMutableMap.map6802map,
                                GeneratedMutableMap.map6803map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("z")
                                ),
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
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map6804map,
                                GeneratedMutableMap.map6805map,
                                GeneratedMutableMap.map6806map,
                                GeneratedMutableMap.map6807map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("z"),
                                        AOPVariable("z2")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6960map,
                                GeneratedMutableMap.map6961map,
                                GeneratedMutableMap.map6962map,
                                GeneratedMutableMap.map6963map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("z"),
                                        AOPVariable("z2")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("z"),
                                AOPVariable("z2")
                        ), listOf(
                                GeneratedMutableMap.map6964map,
                                GeneratedMutableMap.map6965map,
                                GeneratedMutableMap.map6966map,
                                GeneratedMutableMap.map6967map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                POPBindUndefined(
                                        dictionary,
                                        AOPVariable("z"),
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
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7359map,
                                GeneratedMutableMap.map7360map,
                                GeneratedMutableMap.map7361map,
                                GeneratedMutableMap.map7362map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                LOPBind(
                                        AOPVariable("z"),
                                        AOPUndef(),
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
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7364map,
                                GeneratedMutableMap.map7365map,
                                GeneratedMutableMap.map7366map,
                                GeneratedMutableMap.map7367map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                POPFilter(
                                        dictionary,
                                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
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

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7609map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
                                LOPFilter(
                                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
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

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7613map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("z")
                                ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y1"),
                                        AOPVariable("Y2")
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "Y1",
                                        "Y2"
                                ), listOf(
                                        GeneratedMutableMap.map18989map,
                                        GeneratedMutableMap.map18990map
                                )
                                )
                        ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y1"),
                                        AOPVariable("Y2")
                                ),
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
                                        false)

                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y1"),
                                        AOPVariable("Y2")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y1"),
                                        AOPVariable("Y2")
                                ), listOf(
                                        GeneratedMutableMap.map18991map,
                                        GeneratedMutableMap.map18992map
                                )
                                )
                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y1"),
                                        AOPVariable("Y2")
                                ),
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
                                        false)

                        ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                        POPValues(dictionary, listOf(
                                "#a"
                            ), listOf(
                                GeneratedMutableMap.map19035map,
                                GeneratedMutableMap.map19036map,
                                GeneratedMutableMap.map19037map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5965map,
                            GeneratedMutableMap.map5965map,
                            GeneratedMutableMap.map5965map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-05.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y"),
                                        AOPVariable("Z")
                                ),
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "X",
                                        "Y",
                                        "Z"
                                ), listOf(
                                        GeneratedMutableMap.map19227map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map19229map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y"),
                                        AOPVariable("Z")
                                ),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map19229map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y"),
                                        AOPVariable("Z")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("#aa"),
                                        AOPVariable("X"),
                                        AOPVariable("Y"),
                                        AOPVariable("Z")
                                ), listOf(
                                        GeneratedMutableMap.map19228map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y"),
                                AOPVariable("Z")
                        ), listOf(
                                GeneratedMutableMap.map19230map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y"),
                                        AOPVariable("Z")
                                ),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y"),
                                AOPVariable("Z")
                        ), listOf(
                                GeneratedMutableMap.map19230map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map19292map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19294map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19294map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("X"),
                                        AOPVariable("#a"),
                                        AOPVariable("Y")
                                ), listOf(
                                        GeneratedMutableMap.map19293map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19295map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19295map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
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
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19372map,
                                GeneratedMutableMap.map19373map,
                                GeneratedMutableMap.map19374map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
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
                                        false)

                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19372map,
                                GeneratedMutableMap.map19373map,
                                GeneratedMutableMap.map19374map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19375map,
                                GeneratedMutableMap.map19376map,
                                GeneratedMutableMap.map19377map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("X"),
                                        AOPVariable("Y")
                                ),
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
                                        false)

                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19375map,
                                GeneratedMutableMap.map19376map,
                                GeneratedMutableMap.map19377map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map19558map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map19558map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-12.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("a"),
                            AOPVariable("b"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19639map
                            )
                        )
                    ),
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("parent")
                                ),
                                POPValues(dictionary, listOf(
                                        "parent",
                                        "child"
                                ), listOf(
                                        GeneratedMutableMap.map19729map,
                                        GeneratedMutableMap.map19730map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "parent"
                        ), listOf(
                                GeneratedMutableMap.map19733map,
                                GeneratedMutableMap.map19734map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("parent")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#Bob>", "<http://example.org/test#hasChild>", "<http://example.org/test#Charlie>"))
                                    graph.addData(1L, listOf("<http://example.org/test#Dudley>", "<http://example.org/test#hasChild>", "<http://example.org/test#Alice>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "parent", "<http://example.org/test#hasChild>", "child", false, true, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "parent"
                        ), listOf(
                                GeneratedMutableMap.map19733map,
                                GeneratedMutableMap.map19734map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("parent")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("parent"),
                                        AOPVariable("child")
                                ), listOf(
                                        GeneratedMutableMap.map19731map,
                                        GeneratedMutableMap.map19732map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("parent")
                        ), listOf(
                                GeneratedMutableMap.map19735map,
                                GeneratedMutableMap.map19736map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("parent")
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/test#Bob>", "<http://example.org/test#hasChild>", "<http://example.org/test#Charlie>"))
                                    graph.addData(1L, listOf("<http://example.org/test#Dudley>", "<http://example.org/test#hasChild>", "<http://example.org/test#Alice>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("parent"), AOPVariable.calculate("<http://example.org/test#hasChild>"), AOPVariable("child"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("parent")
                        ), listOf(
                                GeneratedMutableMap.map19735map,
                                GeneratedMutableMap.map19736map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str",
                                        "str1"
                                ), listOf(
                                        GeneratedMutableMap.map23000map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23002map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23002map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str"),
                                        AOPVariable("str1")
                                ), listOf(
                                        GeneratedMutableMap.map23001map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23003map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23003map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str",
                                        "str1"
                                ), listOf(
                                        GeneratedMutableMap.map23276map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23278map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23278map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str"),
                                        AOPVariable("str1")
                                ), listOf(
                                        GeneratedMutableMap.map23277map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23279map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23279map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23517map,
                                GeneratedMutableMap.map23518map,
                                GeneratedMutableMap.map23519map,
                                GeneratedMutableMap.map23520map,
                                GeneratedMutableMap.map23521map,
                                GeneratedMutableMap.map23522map,
                                GeneratedMutableMap.map23523map,
                                GeneratedMutableMap.map23524map,
                                GeneratedMutableMap.map23525map,
                                GeneratedMutableMap.map23526map,
                                GeneratedMutableMap.map23002map,
                                GeneratedMutableMap.map23527map,
                                GeneratedMutableMap.map23528map,
                                GeneratedMutableMap.map23529map,
                                GeneratedMutableMap.map23530map,
                                GeneratedMutableMap.map23531map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23517map,
                                GeneratedMutableMap.map23518map,
                                GeneratedMutableMap.map23519map,
                                GeneratedMutableMap.map23520map,
                                GeneratedMutableMap.map23521map,
                                GeneratedMutableMap.map23522map,
                                GeneratedMutableMap.map23523map,
                                GeneratedMutableMap.map23524map,
                                GeneratedMutableMap.map23525map,
                                GeneratedMutableMap.map23526map,
                                GeneratedMutableMap.map23002map,
                                GeneratedMutableMap.map23527map,
                                GeneratedMutableMap.map23528map,
                                GeneratedMutableMap.map23529map,
                                GeneratedMutableMap.map23530map,
                                GeneratedMutableMap.map23531map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23532map,
                                GeneratedMutableMap.map23533map,
                                GeneratedMutableMap.map23534map,
                                GeneratedMutableMap.map23535map,
                                GeneratedMutableMap.map23536map,
                                GeneratedMutableMap.map23537map,
                                GeneratedMutableMap.map23538map,
                                GeneratedMutableMap.map23539map,
                                GeneratedMutableMap.map23540map,
                                GeneratedMutableMap.map23541map,
                                GeneratedMutableMap.map23003map,
                                GeneratedMutableMap.map23542map,
                                GeneratedMutableMap.map23543map,
                                GeneratedMutableMap.map23544map,
                                GeneratedMutableMap.map23545map,
                                GeneratedMutableMap.map23546map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23532map,
                                GeneratedMutableMap.map23533map,
                                GeneratedMutableMap.map23534map,
                                GeneratedMutableMap.map23535map,
                                GeneratedMutableMap.map23536map,
                                GeneratedMutableMap.map23537map,
                                GeneratedMutableMap.map23538map,
                                GeneratedMutableMap.map23539map,
                                GeneratedMutableMap.map23540map,
                                GeneratedMutableMap.map23541map,
                                GeneratedMutableMap.map23003map,
                                GeneratedMutableMap.map23542map,
                                GeneratedMutableMap.map23543map,
                                GeneratedMutableMap.map23544map,
                                GeneratedMutableMap.map23545map,
                                GeneratedMutableMap.map23546map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map23800map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23801map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23801map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str"),
                                        AOPVariable("s2")
                                ), listOf(
                                        GeneratedMutableMap.map23001map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map23003map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map23003map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map24071map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map24073map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map24073map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str"),
                                        AOPVariable("s2")
                                ), listOf(
                                        GeneratedMutableMap.map24072map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map24074map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("s2")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map24074map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23517map,
                                GeneratedMutableMap.map23518map,
                                GeneratedMutableMap.map23519map,
                                GeneratedMutableMap.map23520map,
                                GeneratedMutableMap.map23521map,
                                GeneratedMutableMap.map23522map,
                                GeneratedMutableMap.map23523map,
                                GeneratedMutableMap.map23524map,
                                GeneratedMutableMap.map23525map,
                                GeneratedMutableMap.map24253map,
                                GeneratedMutableMap.map23002map,
                                GeneratedMutableMap.map24254map,
                                GeneratedMutableMap.map24255map,
                                GeneratedMutableMap.map24256map,
                                GeneratedMutableMap.map23530map,
                                GeneratedMutableMap.map23531map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23517map,
                                GeneratedMutableMap.map23518map,
                                GeneratedMutableMap.map23519map,
                                GeneratedMutableMap.map23520map,
                                GeneratedMutableMap.map23521map,
                                GeneratedMutableMap.map23522map,
                                GeneratedMutableMap.map23523map,
                                GeneratedMutableMap.map23524map,
                                GeneratedMutableMap.map23525map,
                                GeneratedMutableMap.map24253map,
                                GeneratedMutableMap.map23002map,
                                GeneratedMutableMap.map24254map,
                                GeneratedMutableMap.map24255map,
                                GeneratedMutableMap.map24256map,
                                GeneratedMutableMap.map23530map,
                                GeneratedMutableMap.map23531map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23532map,
                                GeneratedMutableMap.map23533map,
                                GeneratedMutableMap.map23534map,
                                GeneratedMutableMap.map23535map,
                                GeneratedMutableMap.map23536map,
                                GeneratedMutableMap.map23537map,
                                GeneratedMutableMap.map23538map,
                                GeneratedMutableMap.map23539map,
                                GeneratedMutableMap.map23540map,
                                GeneratedMutableMap.map24257map,
                                GeneratedMutableMap.map23003map,
                                GeneratedMutableMap.map24258map,
                                GeneratedMutableMap.map24259map,
                                GeneratedMutableMap.map24260map,
                                GeneratedMutableMap.map23545map,
                                GeneratedMutableMap.map23546map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str1")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23532map,
                                GeneratedMutableMap.map23533map,
                                GeneratedMutableMap.map23534map,
                                GeneratedMutableMap.map23535map,
                                GeneratedMutableMap.map23536map,
                                GeneratedMutableMap.map23537map,
                                GeneratedMutableMap.map23538map,
                                GeneratedMutableMap.map23539map,
                                GeneratedMutableMap.map23540map,
                                GeneratedMutableMap.map24257map,
                                GeneratedMutableMap.map23003map,
                                GeneratedMutableMap.map24258map,
                                GeneratedMutableMap.map24259map,
                                GeneratedMutableMap.map24260map,
                                GeneratedMutableMap.map23545map,
                                GeneratedMutableMap.map23546map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map24444map,
                                        GeneratedMutableMap.map24445map,
                                        GeneratedMutableMap.map24446map,
                                        GeneratedMutableMap.map24447map,
                                        GeneratedMutableMap.map24448map
                                )
                                )
                        ),
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                POPFilter(
                                        dictionary,
                                        AOPBuildInCallIsNUMERIC(AOPVariable("num")),
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
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "num", false, false, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("num")
                                ), listOf(
                                        GeneratedMutableMap.map23473map,
                                        GeneratedMutableMap.map23474map,
                                        GeneratedMutableMap.map23475map,
                                        GeneratedMutableMap.map23476map,
                                        GeneratedMutableMap.map23477map
                                )
                                )
                        ),
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                LOPFilter(
                                        AOPBuildInCallIsNUMERIC(AOPVariable("num")),
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
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("num"), graphName, false)
                                        }()

                                )

                        ),
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map24459map,
                                        GeneratedMutableMap.map24460map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num"
                        ), listOf(
                                GeneratedMutableMap.map24459map,
                                GeneratedMutableMap.map24460map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                POPFilter(
                                        dictionary,
                                        AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num"
                        ), listOf(
                                GeneratedMutableMap.map24459map,
                                GeneratedMutableMap.map24460map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ), listOf(
                                        GeneratedMutableMap.map24464map,
                                        GeneratedMutableMap.map24465map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num")
                        ), listOf(
                                GeneratedMutableMap.map24464map,
                                GeneratedMutableMap.map24465map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num")
                                ),
                                LOPFilter(
                                        AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num")
                        ), listOf(
                                GeneratedMutableMap.map24464map,
                                GeneratedMutableMap.map24465map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("ceil")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("ceil")
                                ),
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
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("ceil")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("ceil")
                                ),
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
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("floor")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("floor")
                                ),
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
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("floor")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("floor")
                                ),
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
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("round")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("round")
                                ),
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
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("round")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("num"),
                                        AOPVariable("round")
                                ),
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
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("str")
                                ),
                                POPValues(dictionary, listOf(
                                        "str1",
                                        "str2",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map24952map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24954map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("str")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24954map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("str")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("str1"),
                                        AOPVariable("str2"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map24953map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map24955map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("str")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map24955map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("str")
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
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map25769map,
                                GeneratedMutableMap.map25770map,
                                GeneratedMutableMap.map25771map,
                                GeneratedMutableMap.map25772map,
                                GeneratedMutableMap.map25773map,
                                GeneratedMutableMap.map25774map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25776map,
                                GeneratedMutableMap.map25777map,
                                GeneratedMutableMap.map25778map,
                                GeneratedMutableMap.map25779map,
                                GeneratedMutableMap.map25780map,
                                GeneratedMutableMap.map25781map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25782map,
                                GeneratedMutableMap.map25783map,
                                GeneratedMutableMap.map25784map,
                                GeneratedMutableMap.map25785map,
                                GeneratedMutableMap.map25786map,
                                GeneratedMutableMap.map25787map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25788map,
                                GeneratedMutableMap.map25789map,
                                GeneratedMutableMap.map25790map,
                                GeneratedMutableMap.map25791map,
                                GeneratedMutableMap.map25792map,
                                GeneratedMutableMap.map25793map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25794map,
                                GeneratedMutableMap.map25795map,
                                GeneratedMutableMap.map25796map,
                                GeneratedMutableMap.map25797map,
                                GeneratedMutableMap.map25798map,
                                GeneratedMutableMap.map25799map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25800map,
                                GeneratedMutableMap.map25801map,
                                GeneratedMutableMap.map25802map,
                                GeneratedMutableMap.map25803map,
                                GeneratedMutableMap.map25804map,
                                GeneratedMutableMap.map25805map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("str")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map25769map,
                                GeneratedMutableMap.map25770map,
                                GeneratedMutableMap.map25771map,
                                GeneratedMutableMap.map25772map,
                                GeneratedMutableMap.map25773map,
                                GeneratedMutableMap.map25774map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25776map,
                                GeneratedMutableMap.map25777map,
                                GeneratedMutableMap.map25778map,
                                GeneratedMutableMap.map25779map,
                                GeneratedMutableMap.map25780map,
                                GeneratedMutableMap.map25781map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25782map,
                                GeneratedMutableMap.map25783map,
                                GeneratedMutableMap.map25784map,
                                GeneratedMutableMap.map25785map,
                                GeneratedMutableMap.map25786map,
                                GeneratedMutableMap.map25787map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25788map,
                                GeneratedMutableMap.map25789map,
                                GeneratedMutableMap.map25790map,
                                GeneratedMutableMap.map25791map,
                                GeneratedMutableMap.map25792map,
                                GeneratedMutableMap.map25793map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25794map,
                                GeneratedMutableMap.map25795map,
                                GeneratedMutableMap.map25796map,
                                GeneratedMutableMap.map25797map,
                                GeneratedMutableMap.map25798map,
                                GeneratedMutableMap.map25799map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25800map,
                                GeneratedMutableMap.map25801map,
                                GeneratedMutableMap.map25802map,
                                GeneratedMutableMap.map25803map,
                                GeneratedMutableMap.map25804map,
                                GeneratedMutableMap.map25805map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map,
                                GeneratedMutableMap.map25775map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("str")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map25806map,
                                GeneratedMutableMap.map25807map,
                                GeneratedMutableMap.map25808map,
                                GeneratedMutableMap.map25809map,
                                GeneratedMutableMap.map25810map,
                                GeneratedMutableMap.map25811map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25813map,
                                GeneratedMutableMap.map25814map,
                                GeneratedMutableMap.map25815map,
                                GeneratedMutableMap.map25816map,
                                GeneratedMutableMap.map25817map,
                                GeneratedMutableMap.map25818map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25819map,
                                GeneratedMutableMap.map25820map,
                                GeneratedMutableMap.map25821map,
                                GeneratedMutableMap.map25822map,
                                GeneratedMutableMap.map25823map,
                                GeneratedMutableMap.map25824map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25825map,
                                GeneratedMutableMap.map25826map,
                                GeneratedMutableMap.map25827map,
                                GeneratedMutableMap.map25828map,
                                GeneratedMutableMap.map25829map,
                                GeneratedMutableMap.map25830map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25831map,
                                GeneratedMutableMap.map25832map,
                                GeneratedMutableMap.map25833map,
                                GeneratedMutableMap.map25834map,
                                GeneratedMutableMap.map25835map,
                                GeneratedMutableMap.map25836map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25837map,
                                GeneratedMutableMap.map25838map,
                                GeneratedMutableMap.map25839map,
                                GeneratedMutableMap.map25840map,
                                GeneratedMutableMap.map25841map,
                                GeneratedMutableMap.map25842map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("str")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map25806map,
                                GeneratedMutableMap.map25807map,
                                GeneratedMutableMap.map25808map,
                                GeneratedMutableMap.map25809map,
                                GeneratedMutableMap.map25810map,
                                GeneratedMutableMap.map25811map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25813map,
                                GeneratedMutableMap.map25814map,
                                GeneratedMutableMap.map25815map,
                                GeneratedMutableMap.map25816map,
                                GeneratedMutableMap.map25817map,
                                GeneratedMutableMap.map25818map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25819map,
                                GeneratedMutableMap.map25820map,
                                GeneratedMutableMap.map25821map,
                                GeneratedMutableMap.map25822map,
                                GeneratedMutableMap.map25823map,
                                GeneratedMutableMap.map25824map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25825map,
                                GeneratedMutableMap.map25826map,
                                GeneratedMutableMap.map25827map,
                                GeneratedMutableMap.map25828map,
                                GeneratedMutableMap.map25829map,
                                GeneratedMutableMap.map25830map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25831map,
                                GeneratedMutableMap.map25832map,
                                GeneratedMutableMap.map25833map,
                                GeneratedMutableMap.map25834map,
                                GeneratedMutableMap.map25835map,
                                GeneratedMutableMap.map25836map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25837map,
                                GeneratedMutableMap.map25838map,
                                GeneratedMutableMap.map25839map,
                                GeneratedMutableMap.map25840map,
                                GeneratedMutableMap.map25841map,
                                GeneratedMutableMap.map25842map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map,
                                GeneratedMutableMap.map25812map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("str"),
                                        AOPVariable("len")
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
                        ),
                        POPValues(dictionary, listOf(
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map26018map,
                                GeneratedMutableMap.map26019map,
                                GeneratedMutableMap.map26020map,
                                GeneratedMutableMap.map26021map,
                                GeneratedMutableMap.map26022map,
                                GeneratedMutableMap.map26023map,
                                GeneratedMutableMap.map26024map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("str"),
                                        AOPVariable("len")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map26018map,
                                GeneratedMutableMap.map26019map,
                                GeneratedMutableMap.map26020map,
                                GeneratedMutableMap.map26021map,
                                GeneratedMutableMap.map26022map,
                                GeneratedMutableMap.map26023map,
                                GeneratedMutableMap.map26024map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("str"),
                                        AOPVariable("len")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("str"),
                                AOPVariable("len")
                        ), listOf(
                                GeneratedMutableMap.map26025map,
                                GeneratedMutableMap.map26026map,
                                GeneratedMutableMap.map26027map,
                                GeneratedMutableMap.map26028map,
                                GeneratedMutableMap.map26029map,
                                GeneratedMutableMap.map26030map,
                                GeneratedMutableMap.map26031map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("str"),
                                        AOPVariable("len")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("str"),
                                AOPVariable("len")
                        ), listOf(
                                GeneratedMutableMap.map26025map,
                                GeneratedMutableMap.map26026map,
                                GeneratedMutableMap.map26027map,
                                GeneratedMutableMap.map26028map,
                                GeneratedMutableMap.map26029map,
                                GeneratedMutableMap.map26030map,
                                GeneratedMutableMap.map26031map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("ustr")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map26139map,
                                GeneratedMutableMap.map26140map,
                                GeneratedMutableMap.map26141map,
                                GeneratedMutableMap.map26142map,
                                GeneratedMutableMap.map26143map,
                                GeneratedMutableMap.map26144map,
                                GeneratedMutableMap.map26145map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("ustr")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map26139map,
                                GeneratedMutableMap.map26140map,
                                GeneratedMutableMap.map26141map,
                                GeneratedMutableMap.map26142map,
                                GeneratedMutableMap.map26143map,
                                GeneratedMutableMap.map26144map,
                                GeneratedMutableMap.map26145map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("ustr")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("ustr")
                        ), listOf(
                                GeneratedMutableMap.map26146map,
                                GeneratedMutableMap.map26147map,
                                GeneratedMutableMap.map22995map,
                                GeneratedMutableMap.map22996map,
                                GeneratedMutableMap.map22997map,
                                GeneratedMutableMap.map26148map,
                                GeneratedMutableMap.map22999map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("ustr")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("ustr")
                        ), listOf(
                                GeneratedMutableMap.map26146map,
                                GeneratedMutableMap.map26147map,
                                GeneratedMutableMap.map22995map,
                                GeneratedMutableMap.map22996map,
                                GeneratedMutableMap.map22997map,
                                GeneratedMutableMap.map26148map,
                                GeneratedMutableMap.map22999map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("lstr")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map26254map,
                                GeneratedMutableMap.map26255map,
                                GeneratedMutableMap.map26256map,
                                GeneratedMutableMap.map26257map,
                                GeneratedMutableMap.map26258map,
                                GeneratedMutableMap.map26259map,
                                GeneratedMutableMap.map26260map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("lstr")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map26254map,
                                GeneratedMutableMap.map26255map,
                                GeneratedMutableMap.map26256map,
                                GeneratedMutableMap.map26257map,
                                GeneratedMutableMap.map26258map,
                                GeneratedMutableMap.map26259map,
                                GeneratedMutableMap.map26260map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("lstr")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("lstr")
                        ), listOf(
                                GeneratedMutableMap.map22993map,
                                GeneratedMutableMap.map22994map,
                                GeneratedMutableMap.map26261map,
                                GeneratedMutableMap.map22996map,
                                GeneratedMutableMap.map22997map,
                                GeneratedMutableMap.map22998map,
                                GeneratedMutableMap.map26262map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("lstr")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("lstr")
                        ), listOf(
                                GeneratedMutableMap.map22993map,
                                GeneratedMutableMap.map22994map,
                                GeneratedMutableMap.map26261map,
                                GeneratedMutableMap.map22996map,
                                GeneratedMutableMap.map22997map,
                                GeneratedMutableMap.map22998map,
                                GeneratedMutableMap.map26262map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22987map,
                                        GeneratedMutableMap.map22991map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22987map,
                                GeneratedMutableMap.map22991map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                POPFilter(
                                        dictionary,
                                        AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
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
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22987map,
                                GeneratedMutableMap.map22991map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map22994map,
                                        GeneratedMutableMap.map22998map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map22994map,
                                GeneratedMutableMap.map22998map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                LOPFilter(
                                        AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
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
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map22994map,
                                GeneratedMutableMap.map22998map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map27009map,
                                        GeneratedMutableMap.map27016map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map27019map,
                                GeneratedMutableMap.map22990map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                POPFilter(
                                        dictionary,
                                        AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
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
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "str", false, false, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map27019map,
                                GeneratedMutableMap.map22990map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map23475map,
                                        GeneratedMutableMap.map23482map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map24463map,
                                GeneratedMutableMap.map22997map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                LOPFilter(
                                        AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
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
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("str"), graphName, false)
                                        }()

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map24463map,
                                GeneratedMutableMap.map22997map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map27017map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22991map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                POPFilter(
                                        dictionary,
                                        AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc")),
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
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "str", false, false, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22991map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("str")
                                ), listOf(
                                        GeneratedMutableMap.map23483map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map22998map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("str")
                                ),
                                LOPFilter(
                                        AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc")),
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
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("str"), graphName, false)
                                        }()

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map22998map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27538map,
                                GeneratedMutableMap.map27539map,
                                GeneratedMutableMap.map27540map,
                                GeneratedMutableMap.map27541map,
                                GeneratedMutableMap.map27542map,
                                GeneratedMutableMap.map27543map,
                                GeneratedMutableMap.map27544map,
                                GeneratedMutableMap.map27545map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27538map,
                                GeneratedMutableMap.map27539map,
                                GeneratedMutableMap.map27540map,
                                GeneratedMutableMap.map27541map,
                                GeneratedMutableMap.map27542map,
                                GeneratedMutableMap.map27543map,
                                GeneratedMutableMap.map27544map,
                                GeneratedMutableMap.map27545map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27546map,
                                GeneratedMutableMap.map27547map,
                                GeneratedMutableMap.map27548map,
                                GeneratedMutableMap.map27549map,
                                GeneratedMutableMap.map27550map,
                                GeneratedMutableMap.map27551map,
                                GeneratedMutableMap.map27552map,
                                GeneratedMutableMap.map27553map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27546map,
                                GeneratedMutableMap.map27547map,
                                GeneratedMutableMap.map27548map,
                                GeneratedMutableMap.map27549map,
                                GeneratedMutableMap.map27550map,
                                GeneratedMutableMap.map27551map,
                                GeneratedMutableMap.map27552map,
                                GeneratedMutableMap.map27553map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27538map,
                                GeneratedMutableMap.map27809map,
                                GeneratedMutableMap.map27540map,
                                GeneratedMutableMap.map27541map,
                                GeneratedMutableMap.map27810map,
                                GeneratedMutableMap.map27543map,
                                GeneratedMutableMap.map27544map,
                                GeneratedMutableMap.map27545map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27538map,
                                GeneratedMutableMap.map27809map,
                                GeneratedMutableMap.map27540map,
                                GeneratedMutableMap.map27541map,
                                GeneratedMutableMap.map27810map,
                                GeneratedMutableMap.map27543map,
                                GeneratedMutableMap.map27544map,
                                GeneratedMutableMap.map27545map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27546map,
                                GeneratedMutableMap.map27811map,
                                GeneratedMutableMap.map27548map,
                                GeneratedMutableMap.map27549map,
                                GeneratedMutableMap.map27812map,
                                GeneratedMutableMap.map27551map,
                                GeneratedMutableMap.map27552map,
                                GeneratedMutableMap.map27553map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27546map,
                                GeneratedMutableMap.map27811map,
                                GeneratedMutableMap.map27548map,
                                GeneratedMutableMap.map27549map,
                                GeneratedMutableMap.map27812map,
                                GeneratedMutableMap.map27551map,
                                GeneratedMutableMap.map27552map,
                                GeneratedMutableMap.map27553map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map27884map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27886map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27886map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("l"),
                                        AOPVariable("hash")
                                ), listOf(
                                        GeneratedMutableMap.map27885map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27887map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27887map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map27960map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27962map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27962map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("l"),
                                        AOPVariable("hash")
                                ), listOf(
                                        GeneratedMutableMap.map27961map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27963map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27963map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map28034map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28036map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28036map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("l"),
                                        AOPVariable("hash")
                                ), listOf(
                                        GeneratedMutableMap.map28035map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28037map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28037map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map28110map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28112map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28112map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("l"),
                                        AOPVariable("hash")
                                ), listOf(
                                        GeneratedMutableMap.map28111map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28113map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28113map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map28184map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28186map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28186map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("l"),
                                        AOPVariable("hash")
                                ), listOf(
                                        GeneratedMutableMap.map28185map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28187map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28187map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map28258map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28260map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map28260map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("l"),
                                        AOPVariable("hash")
                                ), listOf(
                                        GeneratedMutableMap.map28259map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28261map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("hash")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map28261map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28445map,
                                GeneratedMutableMap.map28446map,
                                GeneratedMutableMap.map28447map,
                                GeneratedMutableMap.map28448map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28445map,
                                GeneratedMutableMap.map28446map,
                                GeneratedMutableMap.map28447map,
                                GeneratedMutableMap.map28448map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28449map,
                                GeneratedMutableMap.map28450map,
                                GeneratedMutableMap.map28451map,
                                GeneratedMutableMap.map28452map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28449map,
                                GeneratedMutableMap.map28450map,
                                GeneratedMutableMap.map28451map,
                                GeneratedMutableMap.map28452map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28537map,
                                GeneratedMutableMap.map28538map,
                                GeneratedMutableMap.map28539map,
                                GeneratedMutableMap.map28540map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28537map,
                                GeneratedMutableMap.map28538map,
                                GeneratedMutableMap.map28539map,
                                GeneratedMutableMap.map28540map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28541map,
                                GeneratedMutableMap.map28542map,
                                GeneratedMutableMap.map28543map,
                                GeneratedMutableMap.map28544map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28541map,
                                GeneratedMutableMap.map28542map,
                                GeneratedMutableMap.map28543map,
                                GeneratedMutableMap.map28544map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28665map,
                                GeneratedMutableMap.map28666map,
                                GeneratedMutableMap.map28667map,
                                GeneratedMutableMap.map28668map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28665map,
                                GeneratedMutableMap.map28666map,
                                GeneratedMutableMap.map28667map,
                                GeneratedMutableMap.map28668map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28669map,
                                GeneratedMutableMap.map28670map,
                                GeneratedMutableMap.map28671map,
                                GeneratedMutableMap.map28672map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28669map,
                                GeneratedMutableMap.map28670map,
                                GeneratedMutableMap.map28671map,
                                GeneratedMutableMap.map28672map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28792map,
                                GeneratedMutableMap.map28793map,
                                GeneratedMutableMap.map28794map,
                                GeneratedMutableMap.map28448map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28792map,
                                GeneratedMutableMap.map28793map,
                                GeneratedMutableMap.map28794map,
                                GeneratedMutableMap.map28448map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28795map,
                                GeneratedMutableMap.map28796map,
                                GeneratedMutableMap.map28797map,
                                GeneratedMutableMap.map28452map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28795map,
                                GeneratedMutableMap.map28796map,
                                GeneratedMutableMap.map28797map,
                                GeneratedMutableMap.map28452map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28919map,
                                GeneratedMutableMap.map28920map,
                                GeneratedMutableMap.map28921map,
                                GeneratedMutableMap.map28922map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28919map,
                                GeneratedMutableMap.map28920map,
                                GeneratedMutableMap.map28921map,
                                GeneratedMutableMap.map28922map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28923map,
                                GeneratedMutableMap.map28924map,
                                GeneratedMutableMap.map28925map,
                                GeneratedMutableMap.map28926map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28923map,
                                GeneratedMutableMap.map28924map,
                                GeneratedMutableMap.map28925map,
                                GeneratedMutableMap.map28926map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map29046map,
                                GeneratedMutableMap.map29047map,
                                GeneratedMutableMap.map29048map,
                                GeneratedMutableMap.map28668map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map29046map,
                                GeneratedMutableMap.map29047map,
                                GeneratedMutableMap.map29048map,
                                GeneratedMutableMap.map28668map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map29049map,
                                GeneratedMutableMap.map29050map,
                                GeneratedMutableMap.map29051map,
                                GeneratedMutableMap.map28672map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map29049map,
                                GeneratedMutableMap.map29050map,
                                GeneratedMutableMap.map29051map,
                                GeneratedMutableMap.map28672map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
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
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map29131map,
                            GeneratedMutableMap.map29132map,
                            GeneratedMutableMap.map29133map,
                            GeneratedMutableMap.map29134map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/timezone-01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map29254map,
                                GeneratedMutableMap.map29255map,
                                GeneratedMutableMap.map29256map,
                                GeneratedMutableMap.map29134map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map29254map,
                                GeneratedMutableMap.map29255map,
                                GeneratedMutableMap.map29256map,
                                GeneratedMutableMap.map29134map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map29257map,
                                GeneratedMutableMap.map29258map,
                                GeneratedMutableMap.map29259map,
                                GeneratedMutableMap.map29260map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("x")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map29257map,
                                GeneratedMutableMap.map29258map,
                                GeneratedMutableMap.map29259map,
                                GeneratedMutableMap.map29260map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s1"),
                                        AOPVariable("s2"),
                                        AOPVariable("b1"),
                                        AOPVariable("b2")
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
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "s2",
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31431map,
                                GeneratedMutableMap.map31432map,
                                GeneratedMutableMap.map31433map,
                                GeneratedMutableMap.map31434map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s1"),
                                        AOPVariable("s2"),
                                        AOPVariable("b1"),
                                        AOPVariable("b2")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "s2",
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31431map,
                                GeneratedMutableMap.map31432map,
                                GeneratedMutableMap.map31433map,
                                GeneratedMutableMap.map31434map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s1"),
                                        AOPVariable("s2"),
                                        AOPVariable("b1"),
                                        AOPVariable("b2")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("s2"),
                                AOPVariable("b1"),
                                AOPVariable("b2")
                        ), listOf(
                                GeneratedMutableMap.map31435map,
                                GeneratedMutableMap.map31436map,
                                GeneratedMutableMap.map31437map,
                                GeneratedMutableMap.map31438map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s1"),
                                        AOPVariable("s2"),
                                        AOPVariable("b1"),
                                        AOPVariable("b2")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("s2"),
                                AOPVariable("b1"),
                                AOPVariable("b2")
                        ), listOf(
                                GeneratedMutableMap.map31435map,
                                GeneratedMutableMap.map31436map,
                                GeneratedMutableMap.map31437map,
                                GeneratedMutableMap.map31438map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("b1"),
                                        AOPVariable("b2")
                                ),
                                POPValues(dictionary, listOf(
                                        "b2",
                                        "b1"
                                ), listOf(
                                        GeneratedMutableMap.map31515map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31516map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("b1"),
                                        AOPVariable("b2")
                                ),
                                POPValues(dictionary, listOf(
                                        "b2",
                                        "b1"
                                ), listOf(
                                        GeneratedMutableMap.map31518map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31519map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("n")
                                ),
                                POPValues(dictionary, listOf(
                                        "n"
                                ), listOf(
                                        GeneratedMutableMap.map31791map
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("uri"),
                                        AOPVariable("iri")
                                ),
                                POPValues(dictionary, listOf(
                                        "iri",
                                        "uri"
                                ), listOf(
                                        GeneratedMutableMap.map31882map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "uri",
                                "iri"
                        ), listOf(
                                GeneratedMutableMap.map31883map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/iri01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("integer")
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
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map32123map,
                                GeneratedMutableMap.map32124map,
                                GeneratedMutableMap.map32125map,
                                GeneratedMutableMap.map32126map,
                                GeneratedMutableMap.map32127map,
                                GeneratedMutableMap.map32128map,
                                GeneratedMutableMap.map32129map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("integer")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map32123map,
                                GeneratedMutableMap.map32124map,
                                GeneratedMutableMap.map32125map,
                                GeneratedMutableMap.map32126map,
                                GeneratedMutableMap.map32127map,
                                GeneratedMutableMap.map32128map,
                                GeneratedMutableMap.map32129map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("integer")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("integer")
                        ), listOf(
                                GeneratedMutableMap.map32130map,
                                GeneratedMutableMap.map32131map,
                                GeneratedMutableMap.map32132map,
                                GeneratedMutableMap.map32133map,
                                GeneratedMutableMap.map32134map,
                                GeneratedMutableMap.map32135map,
                                GeneratedMutableMap.map32136map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("o"),
                                        AOPVariable("integer")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("integer")
                        ), listOf(
                                GeneratedMutableMap.map32130map,
                                GeneratedMutableMap.map32131map,
                                GeneratedMutableMap.map32132map,
                                GeneratedMutableMap.map32133map,
                                GeneratedMutableMap.map32134map,
                                GeneratedMutableMap.map32135map,
                                GeneratedMutableMap.map32136map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("error")
                                ),
                                POPValues(dictionary, listOf(
                                        "error"
                                ), listOf(
                                        GeneratedMutableMap.map32198map
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s")
                                ),
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map32785map,
                                        GeneratedMutableMap.map32786map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map32785map,
                                GeneratedMutableMap.map32786map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("w"),
                                        AOPVariable("S")
                                ),
                                POPValues(dictionary, listOf(
                                        "w",
                                        "S"
                                ), listOf(
                                        GeneratedMutableMap.map32926map,
                                        GeneratedMutableMap.map32927map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "w",
                                "S"
                        ), listOf(
                                GeneratedMutableMap.map32926map,
                                GeneratedMutableMap.map32927map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("w")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "w"
                                ), listOf(
                                        GeneratedMutableMap.map32918map,
                                        GeneratedMutableMap.map33077map,
                                        GeneratedMutableMap.map33078map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map32918map,
                                GeneratedMutableMap.map33077map,
                                GeneratedMutableMap.map33078map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("p2"),
                                        AOPVariable("o2")
                                ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                ),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5965map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                        ), listOf(
                                GeneratedMutableMap.map5965map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>", true, true, true, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                        ), listOf(
                                GeneratedMutableMap.map5965map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                ),
                                LOPValues(listOf(
                                ), listOf(
                                        GeneratedMutableMap.map33543map
                                )
                                )
                        ),
                        LOPValues(listOf(
                        ), listOf(
                                GeneratedMutableMap.map33543map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                ),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable.calculate("<http://example.org/s1>"), AOPVariable.calculate("<http://example.org/p1>"), AOPVariable.calculate("<http://example.org/s2>"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                        ), listOf(
                                GeneratedMutableMap.map33543map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("eq")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("eq")
                                ),
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
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("eq")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("eq")
                                ),
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
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("sum")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("sum")
                                ),
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
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("sum")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("z"),
                                        AOPVariable("sum")
                                ),
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
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum"),
                            AOPVariable("twice")
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
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "sum",
                            "twice"
                        ), listOf(
                            GeneratedMutableMap.map35483map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/project-expression/projexp03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y"),
                                        AOPVariable("sum")
                                ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
                                ),
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
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
                                ),
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
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l",
                                        "dt"
                                ), listOf(
                                        GeneratedMutableMap.map35780map
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
                                ),
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
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
                                ), listOf(
                                        GeneratedMutableMap.map35781map
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("l"),
                                        AOPVariable("dt")
                                ),
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
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("dt")
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35885map,
                                GeneratedMutableMap.map35886map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("dt")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35885map,
                                GeneratedMutableMap.map35886map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("dt")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35887map,
                                GeneratedMutableMap.map35888map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("dt")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35887map,
                                GeneratedMutableMap.map35888map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("O")
                                ),
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map37371map,
                                        GeneratedMutableMap.map37372map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37371map,
                                GeneratedMutableMap.map37372map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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

                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37371map,
                                GeneratedMutableMap.map37372map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("O")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map37375map,
                                        GeneratedMutableMap.map37376map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37375map,
                                GeneratedMutableMap.map37376map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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

                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37375map,
                                GeneratedMutableMap.map37376map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("L")
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
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37379map,
                                GeneratedMutableMap.map37379map,
                                GeneratedMutableMap.map37380map,
                                GeneratedMutableMap.map37381map,
                                GeneratedMutableMap.map37382map,
                                GeneratedMutableMap.map37383map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("L")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37379map,
                                GeneratedMutableMap.map37379map,
                                GeneratedMutableMap.map37380map,
                                GeneratedMutableMap.map37381map,
                                GeneratedMutableMap.map37382map,
                                GeneratedMutableMap.map37383map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("L")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37384map,
                                GeneratedMutableMap.map37384map,
                                GeneratedMutableMap.map37385map,
                                GeneratedMutableMap.map37386map,
                                GeneratedMutableMap.map37387map,
                                GeneratedMutableMap.map37388map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("L")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37384map,
                                GeneratedMutableMap.map37384map,
                                GeneratedMutableMap.map37385map,
                                GeneratedMutableMap.map37386map,
                                GeneratedMutableMap.map37387map,
                                GeneratedMutableMap.map37388map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("P"),
                                        AOPVariable("FullName")
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
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map37586map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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

                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map37586map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("P"),
                                        AOPVariable("FullName")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("FullName")
                        ), listOf(
                                GeneratedMutableMap.map37587map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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

                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("FullName")
                        ), listOf(
                                GeneratedMutableMap.map37587map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map37592map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map37592map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                POPRename(
                                        dictionary,
                                        AOPVariable("o"),
                                        AOPVariable("FullName"),
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

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map37592map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ), listOf(
                                        GeneratedMutableMap.map37593map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map37593map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("o")
                                ),
                                LOPRename(
                                        AOPVariable("o"),
                                        AOPVariable("FullName"),
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

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map37593map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("L")
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
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37379map,
                                GeneratedMutableMap.map37379map,
                                GeneratedMutableMap.map37380map,
                                GeneratedMutableMap.map37381map,
                                GeneratedMutableMap.map37382map,
                                GeneratedMutableMap.map37383map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("L")
                                ),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37379map,
                                GeneratedMutableMap.map37379map,
                                GeneratedMutableMap.map37380map,
                                GeneratedMutableMap.map37381map,
                                GeneratedMutableMap.map37382map,
                                GeneratedMutableMap.map37383map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("L")
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37384map,
                                GeneratedMutableMap.map37384map,
                                GeneratedMutableMap.map37385map,
                                GeneratedMutableMap.map37386map,
                                GeneratedMutableMap.map37387map,
                                GeneratedMutableMap.map37388map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("L")
                                ),
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

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37384map,
                                GeneratedMutableMap.map37384map,
                                GeneratedMutableMap.map37385map,
                                GeneratedMutableMap.map37386map,
                                GeneratedMutableMap.map37387map,
                                GeneratedMutableMap.map37388map
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
