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
                                        GeneratedMutableMap.map973map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map973map
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
                                        GeneratedMutableMap.map1125map,
                                        GeneratedMutableMap.map1126map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1125map,
                                GeneratedMutableMap.map1126map
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
                                        "#f1170",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1404map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1125map
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
                                        "#f1730",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1951map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map973map
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
                                        "#f1991",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map2223map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1125map
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
                                        GeneratedMutableMap.map3259map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map3259map
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
                                        GeneratedMutableMap.map3432map,
                                        GeneratedMutableMap.map3433map,
                                        GeneratedMutableMap.map3434map,
                                        GeneratedMutableMap.map3435map,
                                        GeneratedMutableMap.map3436map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map3432map,
                                GeneratedMutableMap.map3433map,
                                GeneratedMutableMap.map3434map,
                                GeneratedMutableMap.map3435map,
                                GeneratedMutableMap.map3436map
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
                                        GeneratedMutableMap.map3568map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "avg"
                        ), listOf(
                                GeneratedMutableMap.map3568map
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
                                        "#f3612",
                                        "avg"
                                ), listOf(
                                        GeneratedMutableMap.map3884map,
                                        GeneratedMutableMap.map3885map,
                                        GeneratedMutableMap.map3886map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "avg"
                        ), listOf(
                                GeneratedMutableMap.map3887map,
                                GeneratedMutableMap.map3888map,
                                GeneratedMutableMap.map3889map
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
                                        GeneratedMutableMap.map4011map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "min"
                        ), listOf(
                                GeneratedMutableMap.map4011map
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
                                        GeneratedMutableMap.map4164map,
                                        GeneratedMutableMap.map4165map,
                                        GeneratedMutableMap.map4166map,
                                        GeneratedMutableMap.map4167map,
                                        GeneratedMutableMap.map4168map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "min"
                        ), listOf(
                                GeneratedMutableMap.map4164map,
                                GeneratedMutableMap.map4165map,
                                GeneratedMutableMap.map4166map,
                                GeneratedMutableMap.map4167map,
                                GeneratedMutableMap.map4168map
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
                                        GeneratedMutableMap.map4316map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "max"
                        ), listOf(
                                GeneratedMutableMap.map4316map
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
                                        GeneratedMutableMap.map4468map,
                                        GeneratedMutableMap.map4469map,
                                        GeneratedMutableMap.map4470map,
                                        GeneratedMutableMap.map4471map,
                                        GeneratedMutableMap.map4472map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "max"
                        ), listOf(
                                GeneratedMutableMap.map4468map,
                                GeneratedMutableMap.map4469map,
                                GeneratedMutableMap.map4470map,
                                GeneratedMutableMap.map4471map,
                                GeneratedMutableMap.map4472map
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
                                GeneratedMutableMap.map4620map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sample"
                        ), listOf(
                            GeneratedMutableMap.map4620map
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
                                GeneratedMutableMap.map4920map,
                                GeneratedMutableMap.map4921map,
                                GeneratedMutableMap.map4922map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "g",
                            "avg",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map4923map,
                            GeneratedMutableMap.map4924map,
                            GeneratedMutableMap.map4925map
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
                                GeneratedMutableMap.map5032map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "max"
                        ), listOf(
                            GeneratedMutableMap.map5032map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-empty-group.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("count")
                        ),
                        POPValues(dictionary, listOf(
                                "count"
                            ), listOf(
                                GeneratedMutableMap.map6480map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "count"
                        ), listOf(
                            GeneratedMutableMap.map6480map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode2.ru */
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
                                        GeneratedMutableMap.map6653map,
                                        GeneratedMutableMap.map6654map,
                                        GeneratedMutableMap.map6655map,
                                        GeneratedMutableMap.map6656map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6657map,
                                GeneratedMutableMap.map6658map,
                                GeneratedMutableMap.map6659map,
                                GeneratedMutableMap.map6660map
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
                                GeneratedMutableMap.map6657map,
                                GeneratedMutableMap.map6658map,
                                GeneratedMutableMap.map6659map,
                                GeneratedMutableMap.map6660map
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
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6657map,
                                GeneratedMutableMap.map6658map,
                                GeneratedMutableMap.map6659map,
                                GeneratedMutableMap.map6660map
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
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6657map,
                                GeneratedMutableMap.map6658map,
                                GeneratedMutableMap.map6659map,
                                GeneratedMutableMap.map6660map
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
                                        GeneratedMutableMap.map6804map,
                                        GeneratedMutableMap.map6805map,
                                        GeneratedMutableMap.map6806map,
                                        GeneratedMutableMap.map6807map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6808map,
                                GeneratedMutableMap.map6809map,
                                GeneratedMutableMap.map6810map,
                                GeneratedMutableMap.map6811map
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
                                GeneratedMutableMap.map6808map,
                                GeneratedMutableMap.map6809map,
                                GeneratedMutableMap.map6810map,
                                GeneratedMutableMap.map6811map
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
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6808map,
                                GeneratedMutableMap.map6809map,
                                GeneratedMutableMap.map6810map,
                                GeneratedMutableMap.map6811map
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
                        POPValues(dictionary, listOf(
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6808map,
                                GeneratedMutableMap.map6809map,
                                GeneratedMutableMap.map6810map,
                                GeneratedMutableMap.map6811map
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
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s1"
                        ), listOf(
                            GeneratedMutableMap.map7137map,
                            GeneratedMutableMap.map7138map,
                            GeneratedMutableMap.map7139map,
                            GeneratedMutableMap.map7140map,
                            GeneratedMutableMap.map7141map,
                            GeneratedMutableMap.map7142map,
                            GeneratedMutableMap.map7143map,
                            GeneratedMutableMap.map7144map,
                            GeneratedMutableMap.map7145map,
                            GeneratedMutableMap.map7146map,
                            GeneratedMutableMap.map7147map,
                            GeneratedMutableMap.map7148map,
                            GeneratedMutableMap.map7149map,
                            GeneratedMutableMap.map7150map,
                            GeneratedMutableMap.map7151map,
                            GeneratedMutableMap.map7152map
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
                                        GeneratedMutableMap.map7202map,
                                        GeneratedMutableMap.map7203map,
                                        GeneratedMutableMap.map7204map,
                                        GeneratedMutableMap.map7205map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map
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
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map7202map,
                                        GeneratedMutableMap.map7203map,
                                        GeneratedMutableMap.map7204map,
                                        GeneratedMutableMap.map7205map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map
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
                                        GeneratedMutableMap.map7447map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7447map
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
                                GeneratedMutableMap.map7447map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map7447map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7447map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7447map
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
                        LOPProjection(
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
                        ),
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
                                        GeneratedMutableMap.map8211map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8211map
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
                                GeneratedMutableMap.map8211map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "v",
                                        "z"
                                ), listOf(
                                        GeneratedMutableMap.map8211map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8211map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8211map
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
                                        GeneratedMutableMap.map8394map,
                                        GeneratedMutableMap.map8395map
                                )
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
                        LOPProjection(
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
                                        GeneratedMutableMap.map8394map,
                                        GeneratedMutableMap.map8395map
                                )
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
                                        GeneratedMutableMap.map8458map,
                                        GeneratedMutableMap.map8459map,
                                        GeneratedMutableMap.map8460map,
                                        GeneratedMutableMap.map8461map,
                                        GeneratedMutableMap.map8462map
                                )
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
                                GeneratedMutableMap.map8452map,
                                GeneratedMutableMap.map8453map,
                                GeneratedMutableMap.map8454map,
                                GeneratedMutableMap.map8455map,
                                GeneratedMutableMap.map8456map
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
                                        GeneratedMutableMap.map8553map,
                                        GeneratedMutableMap.map8554map,
                                        GeneratedMutableMap.map8555map,
                                        GeneratedMutableMap.map8556map,
                                        GeneratedMutableMap.map8557map
                                )
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
                                GeneratedMutableMap.map8553map,
                                GeneratedMutableMap.map8554map,
                                GeneratedMutableMap.map8555map,
                                GeneratedMutableMap.map8556map,
                                GeneratedMutableMap.map8557map
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
                                GeneratedMutableMap.map8539map,
                                GeneratedMutableMap.map8540map,
                                GeneratedMutableMap.map8541map,
                                GeneratedMutableMap.map8886map,
                                GeneratedMutableMap.map8887map,
                                GeneratedMutableMap.map8888map,
                                GeneratedMutableMap.map8889map,
                                GeneratedMutableMap.map8890map
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
                                        GeneratedMutableMap.map8394map
                                )
                                )
                        ),
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
                                        GeneratedMutableMap.map9437map,
                                        GeneratedMutableMap.map9438map,
                                        GeneratedMutableMap.map9439map,
                                        GeneratedMutableMap.map9440map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9437map,
                                GeneratedMutableMap.map9438map,
                                GeneratedMutableMap.map9439map,
                                GeneratedMutableMap.map9440map
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
                                GeneratedMutableMap.map9437map,
                                GeneratedMutableMap.map9438map,
                                GeneratedMutableMap.map9439map,
                                GeneratedMutableMap.map9440map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map9437map,
                                        GeneratedMutableMap.map9438map,
                                        GeneratedMutableMap.map9439map,
                                        GeneratedMutableMap.map9440map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9437map,
                                GeneratedMutableMap.map9438map,
                                GeneratedMutableMap.map9439map,
                                GeneratedMutableMap.map9440map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9437map,
                                GeneratedMutableMap.map9438map,
                                GeneratedMutableMap.map9439map,
                                GeneratedMutableMap.map9440map
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
                        LOPProjection(
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
                        LOPProjection(
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
                        LOPProjection(
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map15362map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15362map
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
                                GeneratedMutableMap.map15362map
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
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map15362map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15362map
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
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15362map
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
                                        GeneratedMutableMap.map15400map,
                                        GeneratedMutableMap.map15401map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15402map,
                                GeneratedMutableMap.map15402map
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
                                GeneratedMutableMap.map15402map,
                                GeneratedMutableMap.map15402map
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
                                POPValues(dictionary, listOf(
                                        "x",
                                        "#c"
                                ), listOf(
                                        GeneratedMutableMap.map15400map,
                                        GeneratedMutableMap.map15401map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15402map,
                                GeneratedMutableMap.map15402map
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
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15402map,
                                GeneratedMutableMap.map15402map
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
                                        GeneratedMutableMap.map15443map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15443map
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
                                GeneratedMutableMap.map15443map
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
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map15443map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15443map
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
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15443map
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
                                GeneratedMutableMap.map15481map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15481map
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
                                GeneratedMutableMap.map15662map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map15662map
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
                                        GeneratedMutableMap.map15786map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15786map
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
                                GeneratedMutableMap.map15786map
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
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map15786map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15786map
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
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15786map
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
                                GeneratedMutableMap.map15926map
                            )
                        )
                    ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("f")
                                ),
                                POPValues(dictionary, listOf(
                                        "f"
                                ), listOf(
                                        GeneratedMutableMap.map15970map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "f"
                        ), listOf(
                                GeneratedMutableMap.map15970map
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
                                GeneratedMutableMap.map15970map
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
                                POPValues(dictionary, listOf(
                                        "f"
                                ), listOf(
                                        GeneratedMutableMap.map15970map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "f"
                        ), listOf(
                                GeneratedMutableMap.map15970map
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
                        POPValues(dictionary, listOf(
                                "f"
                        ), listOf(
                                GeneratedMutableMap.map15970map
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
                                        GeneratedMutableMap.map16186map,
                                        GeneratedMutableMap.map16187map
                                )
                                )
                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ),
                                POPValues(dictionary, listOf(
                                        "x",
                                        "y"
                                ), listOf(
                                        GeneratedMutableMap.map16186map,
                                        GeneratedMutableMap.map16187map
                                )
                                )
                        ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16229map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16229map
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
                                        GeneratedMutableMap.map16750map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map16750map
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
                                POPValues(dictionary, listOf(
                                        "p",
                                        "v"
                                ), listOf(
                                        GeneratedMutableMap.map16750map
                                )
                                )
                        ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("x")
                                ),
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map16794map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map16794map
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
                                GeneratedMutableMap.map16794map
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
                                POPValues(dictionary, listOf(
                                        "x"
                                ), listOf(
                                        GeneratedMutableMap.map16794map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map16794map
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
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map16794map
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
                                GeneratedMutableMap.map6657map,
                                GeneratedMutableMap.map6658map,
                                GeneratedMutableMap.map6659map,
                                GeneratedMutableMap.map6660map
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
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6657map,
                                GeneratedMutableMap.map6658map,
                                GeneratedMutableMap.map6659map,
                                GeneratedMutableMap.map6660map
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
                                GeneratedMutableMap.map6808map,
                                GeneratedMutableMap.map6809map,
                                GeneratedMutableMap.map6810map,
                                GeneratedMutableMap.map6811map
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
                        POPValues(dictionary, listOf(
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6808map,
                                GeneratedMutableMap.map6809map,
                                GeneratedMutableMap.map6810map,
                                GeneratedMutableMap.map6811map
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
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map
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
                                GeneratedMutableMap.map7447map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7447map
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
                                        GeneratedMutableMap.map18453map,
                                        GeneratedMutableMap.map18454map
                                )
                                )
                        ),
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
                        LOPProjection(
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
                                        GeneratedMutableMap.map18453map,
                                        GeneratedMutableMap.map18454map
                                )
                                )
                        ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                        POPValues(dictionary, listOf(
                                "#a"
                            ), listOf(
                                GeneratedMutableMap.map18497map,
                                GeneratedMutableMap.map18498map,
                                GeneratedMutableMap.map18499map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5828map,
                            GeneratedMutableMap.map5828map,
                            GeneratedMutableMap.map5828map
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
                                        GeneratedMutableMap.map18676map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18677map
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
                                GeneratedMutableMap.map18677map
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
                                POPValues(dictionary, listOf(
                                        "#aa",
                                        "X",
                                        "Y",
                                        "Z"
                                ), listOf(
                                        GeneratedMutableMap.map18676map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18677map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18677map
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
                                        GeneratedMutableMap.map18735map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18736map
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
                                GeneratedMutableMap.map18736map
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
                                POPValues(dictionary, listOf(
                                        "X",
                                        "#a",
                                        "Y"
                                ), listOf(
                                        GeneratedMutableMap.map18735map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18736map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18736map
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
                                        GeneratedMutableMap.map18799map,
                                        GeneratedMutableMap.map18800map,
                                        GeneratedMutableMap.map18801map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18802map,
                                GeneratedMutableMap.map18803map,
                                GeneratedMutableMap.map18804map
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
                                GeneratedMutableMap.map18802map,
                                GeneratedMutableMap.map18803map,
                                GeneratedMutableMap.map18804map
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
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18802map,
                                GeneratedMutableMap.map18803map,
                                GeneratedMutableMap.map18804map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18802map,
                                GeneratedMutableMap.map18803map,
                                GeneratedMutableMap.map18804map
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
                                GeneratedMutableMap.map18985map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map18985map
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
                                GeneratedMutableMap.map19066map
                            )
                        )
                    ),
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
                                        GeneratedMutableMap.map19154map,
                                        GeneratedMutableMap.map19155map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "parent"
                        ), listOf(
                                GeneratedMutableMap.map19156map,
                                GeneratedMutableMap.map19157map
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
                                GeneratedMutableMap.map19156map,
                                GeneratedMutableMap.map19157map
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
                                POPValues(dictionary, listOf(
                                        "parent",
                                        "child"
                                ), listOf(
                                        GeneratedMutableMap.map19154map,
                                        GeneratedMutableMap.map19155map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "parent"
                        ), listOf(
                                GeneratedMutableMap.map19156map,
                                GeneratedMutableMap.map19157map
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
                        POPValues(dictionary, listOf(
                                "parent"
                        ), listOf(
                                GeneratedMutableMap.map19156map,
                                GeneratedMutableMap.map19157map
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
                                        GeneratedMutableMap.map22413map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22414map
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
                                GeneratedMutableMap.map22414map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str",
                                        "str1"
                                ), listOf(
                                        GeneratedMutableMap.map22413map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22414map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22414map
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
                                        GeneratedMutableMap.map22685map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22686map
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
                                GeneratedMutableMap.map22686map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str",
                                        "str1"
                                ), listOf(
                                        GeneratedMutableMap.map22685map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22686map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22686map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22890map,
                                GeneratedMutableMap.map22891map,
                                GeneratedMutableMap.map22892map,
                                GeneratedMutableMap.map22893map,
                                GeneratedMutableMap.map22894map,
                                GeneratedMutableMap.map22895map,
                                GeneratedMutableMap.map22896map,
                                GeneratedMutableMap.map22897map,
                                GeneratedMutableMap.map22898map,
                                GeneratedMutableMap.map22899map,
                                GeneratedMutableMap.map22414map,
                                GeneratedMutableMap.map22900map,
                                GeneratedMutableMap.map22901map,
                                GeneratedMutableMap.map22902map,
                                GeneratedMutableMap.map22903map,
                                GeneratedMutableMap.map22904map
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
                                GeneratedMutableMap.map22890map,
                                GeneratedMutableMap.map22891map,
                                GeneratedMutableMap.map22892map,
                                GeneratedMutableMap.map22893map,
                                GeneratedMutableMap.map22894map,
                                GeneratedMutableMap.map22895map,
                                GeneratedMutableMap.map22896map,
                                GeneratedMutableMap.map22897map,
                                GeneratedMutableMap.map22898map,
                                GeneratedMutableMap.map22899map,
                                GeneratedMutableMap.map22414map,
                                GeneratedMutableMap.map22900map,
                                GeneratedMutableMap.map22901map,
                                GeneratedMutableMap.map22902map,
                                GeneratedMutableMap.map22903map,
                                GeneratedMutableMap.map22904map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22890map,
                                GeneratedMutableMap.map22891map,
                                GeneratedMutableMap.map22892map,
                                GeneratedMutableMap.map22893map,
                                GeneratedMutableMap.map22894map,
                                GeneratedMutableMap.map22895map,
                                GeneratedMutableMap.map22896map,
                                GeneratedMutableMap.map22897map,
                                GeneratedMutableMap.map22898map,
                                GeneratedMutableMap.map22899map,
                                GeneratedMutableMap.map22414map,
                                GeneratedMutableMap.map22900map,
                                GeneratedMutableMap.map22901map,
                                GeneratedMutableMap.map22902map,
                                GeneratedMutableMap.map22903map,
                                GeneratedMutableMap.map22904map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22890map,
                                GeneratedMutableMap.map22891map,
                                GeneratedMutableMap.map22892map,
                                GeneratedMutableMap.map22893map,
                                GeneratedMutableMap.map22894map,
                                GeneratedMutableMap.map22895map,
                                GeneratedMutableMap.map22896map,
                                GeneratedMutableMap.map22897map,
                                GeneratedMutableMap.map22898map,
                                GeneratedMutableMap.map22899map,
                                GeneratedMutableMap.map22414map,
                                GeneratedMutableMap.map22900map,
                                GeneratedMutableMap.map22901map,
                                GeneratedMutableMap.map22902map,
                                GeneratedMutableMap.map22903map,
                                GeneratedMutableMap.map22904map
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
                                        GeneratedMutableMap.map23157map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23158map
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
                                GeneratedMutableMap.map23158map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map23157map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23158map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23158map
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
                                        GeneratedMutableMap.map23426map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23427map
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
                                GeneratedMutableMap.map23427map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str",
                                        "s2"
                                ), listOf(
                                        GeneratedMutableMap.map23426map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23427map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23427map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22890map,
                                GeneratedMutableMap.map22891map,
                                GeneratedMutableMap.map22892map,
                                GeneratedMutableMap.map22893map,
                                GeneratedMutableMap.map22894map,
                                GeneratedMutableMap.map22895map,
                                GeneratedMutableMap.map22896map,
                                GeneratedMutableMap.map22897map,
                                GeneratedMutableMap.map22898map,
                                GeneratedMutableMap.map23600map,
                                GeneratedMutableMap.map22414map,
                                GeneratedMutableMap.map23601map,
                                GeneratedMutableMap.map23602map,
                                GeneratedMutableMap.map23603map,
                                GeneratedMutableMap.map22903map,
                                GeneratedMutableMap.map22904map
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
                                GeneratedMutableMap.map22890map,
                                GeneratedMutableMap.map22891map,
                                GeneratedMutableMap.map22892map,
                                GeneratedMutableMap.map22893map,
                                GeneratedMutableMap.map22894map,
                                GeneratedMutableMap.map22895map,
                                GeneratedMutableMap.map22896map,
                                GeneratedMutableMap.map22897map,
                                GeneratedMutableMap.map22898map,
                                GeneratedMutableMap.map23600map,
                                GeneratedMutableMap.map22414map,
                                GeneratedMutableMap.map23601map,
                                GeneratedMutableMap.map23602map,
                                GeneratedMutableMap.map23603map,
                                GeneratedMutableMap.map22903map,
                                GeneratedMutableMap.map22904map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22890map,
                                GeneratedMutableMap.map22891map,
                                GeneratedMutableMap.map22892map,
                                GeneratedMutableMap.map22893map,
                                GeneratedMutableMap.map22894map,
                                GeneratedMutableMap.map22895map,
                                GeneratedMutableMap.map22896map,
                                GeneratedMutableMap.map22897map,
                                GeneratedMutableMap.map22898map,
                                GeneratedMutableMap.map23600map,
                                GeneratedMutableMap.map22414map,
                                GeneratedMutableMap.map23601map,
                                GeneratedMutableMap.map23602map,
                                GeneratedMutableMap.map23603map,
                                GeneratedMutableMap.map22903map,
                                GeneratedMutableMap.map22904map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22890map,
                                GeneratedMutableMap.map22891map,
                                GeneratedMutableMap.map22892map,
                                GeneratedMutableMap.map22893map,
                                GeneratedMutableMap.map22894map,
                                GeneratedMutableMap.map22895map,
                                GeneratedMutableMap.map22896map,
                                GeneratedMutableMap.map22897map,
                                GeneratedMutableMap.map22898map,
                                GeneratedMutableMap.map23600map,
                                GeneratedMutableMap.map22414map,
                                GeneratedMutableMap.map23601map,
                                GeneratedMutableMap.map23602map,
                                GeneratedMutableMap.map23603map,
                                GeneratedMutableMap.map22903map,
                                GeneratedMutableMap.map22904map
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
                                        GeneratedMutableMap.map23786map,
                                        GeneratedMutableMap.map23787map,
                                        GeneratedMutableMap.map23788map,
                                        GeneratedMutableMap.map23789map,
                                        GeneratedMutableMap.map23790map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map23798map,
                                GeneratedMutableMap.map23799map,
                                GeneratedMutableMap.map23800map,
                                GeneratedMutableMap.map23801map,
                                GeneratedMutableMap.map23802map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map23786map,
                                        GeneratedMutableMap.map23787map,
                                        GeneratedMutableMap.map23788map,
                                        GeneratedMutableMap.map23789map,
                                        GeneratedMutableMap.map23790map
                                )
                                )
                        ),
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
                                        GeneratedMutableMap.map23801map,
                                        GeneratedMutableMap.map23802map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num"
                        ), listOf(
                                GeneratedMutableMap.map23801map,
                                GeneratedMutableMap.map23802map
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
                                GeneratedMutableMap.map23801map,
                                GeneratedMutableMap.map23802map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map23801map,
                                        GeneratedMutableMap.map23802map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num"
                        ), listOf(
                                GeneratedMutableMap.map23801map,
                                GeneratedMutableMap.map23802map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "num"
                        ), listOf(
                                GeneratedMutableMap.map23801map,
                                GeneratedMutableMap.map23802map
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
                                        GeneratedMutableMap.map23993map,
                                        GeneratedMutableMap.map23994map,
                                        GeneratedMutableMap.map23995map,
                                        GeneratedMutableMap.map23996map,
                                        GeneratedMutableMap.map23997map
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
                        LOPProjection(
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
                                        GeneratedMutableMap.map23993map,
                                        GeneratedMutableMap.map23994map,
                                        GeneratedMutableMap.map23995map,
                                        GeneratedMutableMap.map23996map,
                                        GeneratedMutableMap.map23997map
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
                                        GeneratedMutableMap.map24081map,
                                        GeneratedMutableMap.map24082map,
                                        GeneratedMutableMap.map24083map,
                                        GeneratedMutableMap.map24084map,
                                        GeneratedMutableMap.map24085map
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
                        LOPProjection(
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
                                        GeneratedMutableMap.map24081map,
                                        GeneratedMutableMap.map24082map,
                                        GeneratedMutableMap.map24083map,
                                        GeneratedMutableMap.map24084map,
                                        GeneratedMutableMap.map24085map
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
                                        GeneratedMutableMap.map24169map,
                                        GeneratedMutableMap.map24170map,
                                        GeneratedMutableMap.map24171map,
                                        GeneratedMutableMap.map24172map,
                                        GeneratedMutableMap.map24173map
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
                        LOPProjection(
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
                                        GeneratedMutableMap.map24169map,
                                        GeneratedMutableMap.map24170map,
                                        GeneratedMutableMap.map24171map,
                                        GeneratedMutableMap.map24172map,
                                        GeneratedMutableMap.map24173map
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
                                        GeneratedMutableMap.map24271map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24272map
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
                                GeneratedMutableMap.map24272map
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
                                POPValues(dictionary, listOf(
                                        "str1",
                                        "str2",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map24271map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24272map
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
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24272map
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
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24975map,
                                GeneratedMutableMap.map24976map,
                                GeneratedMutableMap.map24977map,
                                GeneratedMutableMap.map24978map,
                                GeneratedMutableMap.map24979map,
                                GeneratedMutableMap.map24980map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24982map,
                                GeneratedMutableMap.map24983map,
                                GeneratedMutableMap.map24984map,
                                GeneratedMutableMap.map24985map,
                                GeneratedMutableMap.map24986map,
                                GeneratedMutableMap.map24987map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24988map,
                                GeneratedMutableMap.map24989map,
                                GeneratedMutableMap.map24990map,
                                GeneratedMutableMap.map24991map,
                                GeneratedMutableMap.map24992map,
                                GeneratedMutableMap.map24993map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24994map,
                                GeneratedMutableMap.map24995map,
                                GeneratedMutableMap.map24996map,
                                GeneratedMutableMap.map24997map,
                                GeneratedMutableMap.map24998map,
                                GeneratedMutableMap.map24999map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map25000map,
                                GeneratedMutableMap.map25001map,
                                GeneratedMutableMap.map25002map,
                                GeneratedMutableMap.map25003map,
                                GeneratedMutableMap.map25004map,
                                GeneratedMutableMap.map25005map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map25006map,
                                GeneratedMutableMap.map25007map,
                                GeneratedMutableMap.map25008map,
                                GeneratedMutableMap.map25009map,
                                GeneratedMutableMap.map25010map,
                                GeneratedMutableMap.map25011map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map
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
                                GeneratedMutableMap.map24975map,
                                GeneratedMutableMap.map24976map,
                                GeneratedMutableMap.map24977map,
                                GeneratedMutableMap.map24978map,
                                GeneratedMutableMap.map24979map,
                                GeneratedMutableMap.map24980map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24982map,
                                GeneratedMutableMap.map24983map,
                                GeneratedMutableMap.map24984map,
                                GeneratedMutableMap.map24985map,
                                GeneratedMutableMap.map24986map,
                                GeneratedMutableMap.map24987map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24988map,
                                GeneratedMutableMap.map24989map,
                                GeneratedMutableMap.map24990map,
                                GeneratedMutableMap.map24991map,
                                GeneratedMutableMap.map24992map,
                                GeneratedMutableMap.map24993map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24994map,
                                GeneratedMutableMap.map24995map,
                                GeneratedMutableMap.map24996map,
                                GeneratedMutableMap.map24997map,
                                GeneratedMutableMap.map24998map,
                                GeneratedMutableMap.map24999map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map25000map,
                                GeneratedMutableMap.map25001map,
                                GeneratedMutableMap.map25002map,
                                GeneratedMutableMap.map25003map,
                                GeneratedMutableMap.map25004map,
                                GeneratedMutableMap.map25005map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map25006map,
                                GeneratedMutableMap.map25007map,
                                GeneratedMutableMap.map25008map,
                                GeneratedMutableMap.map25009map,
                                GeneratedMutableMap.map25010map,
                                GeneratedMutableMap.map25011map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map
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
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24975map,
                                GeneratedMutableMap.map24976map,
                                GeneratedMutableMap.map24977map,
                                GeneratedMutableMap.map24978map,
                                GeneratedMutableMap.map24979map,
                                GeneratedMutableMap.map24980map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24982map,
                                GeneratedMutableMap.map24983map,
                                GeneratedMutableMap.map24984map,
                                GeneratedMutableMap.map24985map,
                                GeneratedMutableMap.map24986map,
                                GeneratedMutableMap.map24987map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24988map,
                                GeneratedMutableMap.map24989map,
                                GeneratedMutableMap.map24990map,
                                GeneratedMutableMap.map24991map,
                                GeneratedMutableMap.map24992map,
                                GeneratedMutableMap.map24993map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24994map,
                                GeneratedMutableMap.map24995map,
                                GeneratedMutableMap.map24996map,
                                GeneratedMutableMap.map24997map,
                                GeneratedMutableMap.map24998map,
                                GeneratedMutableMap.map24999map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map25000map,
                                GeneratedMutableMap.map25001map,
                                GeneratedMutableMap.map25002map,
                                GeneratedMutableMap.map25003map,
                                GeneratedMutableMap.map25004map,
                                GeneratedMutableMap.map25005map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map25006map,
                                GeneratedMutableMap.map25007map,
                                GeneratedMutableMap.map25008map,
                                GeneratedMutableMap.map25009map,
                                GeneratedMutableMap.map25010map,
                                GeneratedMutableMap.map25011map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map
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
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24975map,
                                GeneratedMutableMap.map24976map,
                                GeneratedMutableMap.map24977map,
                                GeneratedMutableMap.map24978map,
                                GeneratedMutableMap.map24979map,
                                GeneratedMutableMap.map24980map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24982map,
                                GeneratedMutableMap.map24983map,
                                GeneratedMutableMap.map24984map,
                                GeneratedMutableMap.map24985map,
                                GeneratedMutableMap.map24986map,
                                GeneratedMutableMap.map24987map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24988map,
                                GeneratedMutableMap.map24989map,
                                GeneratedMutableMap.map24990map,
                                GeneratedMutableMap.map24991map,
                                GeneratedMutableMap.map24992map,
                                GeneratedMutableMap.map24993map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24994map,
                                GeneratedMutableMap.map24995map,
                                GeneratedMutableMap.map24996map,
                                GeneratedMutableMap.map24997map,
                                GeneratedMutableMap.map24998map,
                                GeneratedMutableMap.map24999map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map25000map,
                                GeneratedMutableMap.map25001map,
                                GeneratedMutableMap.map25002map,
                                GeneratedMutableMap.map25003map,
                                GeneratedMutableMap.map25004map,
                                GeneratedMutableMap.map25005map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map25006map,
                                GeneratedMutableMap.map25007map,
                                GeneratedMutableMap.map25008map,
                                GeneratedMutableMap.map25009map,
                                GeneratedMutableMap.map25010map,
                                GeneratedMutableMap.map25011map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map,
                                GeneratedMutableMap.map24981map
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
                                        GeneratedMutableMap.map25172map,
                                        GeneratedMutableMap.map25173map,
                                        GeneratedMutableMap.map25174map,
                                        GeneratedMutableMap.map25175map,
                                        GeneratedMutableMap.map25176map,
                                        GeneratedMutableMap.map25177map,
                                        GeneratedMutableMap.map25178map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map25179map,
                                GeneratedMutableMap.map25180map,
                                GeneratedMutableMap.map25181map,
                                GeneratedMutableMap.map25182map,
                                GeneratedMutableMap.map25183map,
                                GeneratedMutableMap.map25184map,
                                GeneratedMutableMap.map25185map
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
                                GeneratedMutableMap.map25179map,
                                GeneratedMutableMap.map25180map,
                                GeneratedMutableMap.map25181map,
                                GeneratedMutableMap.map25182map,
                                GeneratedMutableMap.map25183map,
                                GeneratedMutableMap.map25184map,
                                GeneratedMutableMap.map25185map
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
                        ),
                        POPValues(dictionary, listOf(
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map25179map,
                                GeneratedMutableMap.map25180map,
                                GeneratedMutableMap.map25181map,
                                GeneratedMutableMap.map25182map,
                                GeneratedMutableMap.map25183map,
                                GeneratedMutableMap.map25184map,
                                GeneratedMutableMap.map25185map
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
                        POPValues(dictionary, listOf(
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map25179map,
                                GeneratedMutableMap.map25180map,
                                GeneratedMutableMap.map25181map,
                                GeneratedMutableMap.map25182map,
                                GeneratedMutableMap.map25183map,
                                GeneratedMutableMap.map25184map,
                                GeneratedMutableMap.map25185map
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
                                        GeneratedMutableMap.map25278map,
                                        GeneratedMutableMap.map25279map,
                                        GeneratedMutableMap.map25280map,
                                        GeneratedMutableMap.map25281map,
                                        GeneratedMutableMap.map25282map,
                                        GeneratedMutableMap.map25283map,
                                        GeneratedMutableMap.map25284map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map25285map,
                                GeneratedMutableMap.map25286map,
                                GeneratedMutableMap.map25287map,
                                GeneratedMutableMap.map25288map,
                                GeneratedMutableMap.map25289map,
                                GeneratedMutableMap.map25290map,
                                GeneratedMutableMap.map25291map
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
                                GeneratedMutableMap.map25285map,
                                GeneratedMutableMap.map25286map,
                                GeneratedMutableMap.map25287map,
                                GeneratedMutableMap.map25288map,
                                GeneratedMutableMap.map25289map,
                                GeneratedMutableMap.map25290map,
                                GeneratedMutableMap.map25291map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map25285map,
                                GeneratedMutableMap.map25286map,
                                GeneratedMutableMap.map25287map,
                                GeneratedMutableMap.map25288map,
                                GeneratedMutableMap.map25289map,
                                GeneratedMutableMap.map25290map,
                                GeneratedMutableMap.map25291map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map25285map,
                                GeneratedMutableMap.map25286map,
                                GeneratedMutableMap.map25287map,
                                GeneratedMutableMap.map25288map,
                                GeneratedMutableMap.map25289map,
                                GeneratedMutableMap.map25290map,
                                GeneratedMutableMap.map25291map
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
                                        GeneratedMutableMap.map25384map,
                                        GeneratedMutableMap.map25385map,
                                        GeneratedMutableMap.map25386map,
                                        GeneratedMutableMap.map25387map,
                                        GeneratedMutableMap.map25388map,
                                        GeneratedMutableMap.map25389map,
                                        GeneratedMutableMap.map25390map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map25391map,
                                GeneratedMutableMap.map25392map,
                                GeneratedMutableMap.map25393map,
                                GeneratedMutableMap.map25394map,
                                GeneratedMutableMap.map25395map,
                                GeneratedMutableMap.map25396map,
                                GeneratedMutableMap.map25397map
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
                                GeneratedMutableMap.map25391map,
                                GeneratedMutableMap.map25392map,
                                GeneratedMutableMap.map25393map,
                                GeneratedMutableMap.map25394map,
                                GeneratedMutableMap.map25395map,
                                GeneratedMutableMap.map25396map,
                                GeneratedMutableMap.map25397map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map25391map,
                                GeneratedMutableMap.map25392map,
                                GeneratedMutableMap.map25393map,
                                GeneratedMutableMap.map25394map,
                                GeneratedMutableMap.map25395map,
                                GeneratedMutableMap.map25396map,
                                GeneratedMutableMap.map25397map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map25391map,
                                GeneratedMutableMap.map25392map,
                                GeneratedMutableMap.map25393map,
                                GeneratedMutableMap.map25394map,
                                GeneratedMutableMap.map25395map,
                                GeneratedMutableMap.map25396map,
                                GeneratedMutableMap.map25397map
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
                                        GeneratedMutableMap.map22407map,
                                        GeneratedMutableMap.map22411map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22407map,
                                GeneratedMutableMap.map22411map
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
                                GeneratedMutableMap.map22407map,
                                GeneratedMutableMap.map22411map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map22407map,
                                        GeneratedMutableMap.map22411map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22407map,
                                GeneratedMutableMap.map22411map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22407map,
                                GeneratedMutableMap.map22411map
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
                                        GeneratedMutableMap.map26141map,
                                        GeneratedMutableMap.map26148map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map26151map,
                                GeneratedMutableMap.map22410map
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
                                GeneratedMutableMap.map26151map,
                                GeneratedMutableMap.map22410map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map26141map,
                                        GeneratedMutableMap.map26148map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map26151map,
                                GeneratedMutableMap.map22410map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map26151map,
                                GeneratedMutableMap.map22410map
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
                                        GeneratedMutableMap.map26149map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22411map
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
                                GeneratedMutableMap.map22411map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map26149map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22411map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22411map
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26632map,
                                GeneratedMutableMap.map26633map,
                                GeneratedMutableMap.map26634map,
                                GeneratedMutableMap.map26635map,
                                GeneratedMutableMap.map26636map,
                                GeneratedMutableMap.map26637map,
                                GeneratedMutableMap.map26638map,
                                GeneratedMutableMap.map26639map
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
                                GeneratedMutableMap.map26632map,
                                GeneratedMutableMap.map26633map,
                                GeneratedMutableMap.map26634map,
                                GeneratedMutableMap.map26635map,
                                GeneratedMutableMap.map26636map,
                                GeneratedMutableMap.map26637map,
                                GeneratedMutableMap.map26638map,
                                GeneratedMutableMap.map26639map
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26632map,
                                GeneratedMutableMap.map26633map,
                                GeneratedMutableMap.map26634map,
                                GeneratedMutableMap.map26635map,
                                GeneratedMutableMap.map26636map,
                                GeneratedMutableMap.map26637map,
                                GeneratedMutableMap.map26638map,
                                GeneratedMutableMap.map26639map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26632map,
                                GeneratedMutableMap.map26633map,
                                GeneratedMutableMap.map26634map,
                                GeneratedMutableMap.map26635map,
                                GeneratedMutableMap.map26636map,
                                GeneratedMutableMap.map26637map,
                                GeneratedMutableMap.map26638map,
                                GeneratedMutableMap.map26639map
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26632map,
                                GeneratedMutableMap.map26890map,
                                GeneratedMutableMap.map26634map,
                                GeneratedMutableMap.map26635map,
                                GeneratedMutableMap.map26891map,
                                GeneratedMutableMap.map26637map,
                                GeneratedMutableMap.map26638map,
                                GeneratedMutableMap.map26639map
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
                                GeneratedMutableMap.map26632map,
                                GeneratedMutableMap.map26890map,
                                GeneratedMutableMap.map26634map,
                                GeneratedMutableMap.map26635map,
                                GeneratedMutableMap.map26891map,
                                GeneratedMutableMap.map26637map,
                                GeneratedMutableMap.map26638map,
                                GeneratedMutableMap.map26639map
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26632map,
                                GeneratedMutableMap.map26890map,
                                GeneratedMutableMap.map26634map,
                                GeneratedMutableMap.map26635map,
                                GeneratedMutableMap.map26891map,
                                GeneratedMutableMap.map26637map,
                                GeneratedMutableMap.map26638map,
                                GeneratedMutableMap.map26639map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map26632map,
                                GeneratedMutableMap.map26890map,
                                GeneratedMutableMap.map26634map,
                                GeneratedMutableMap.map26635map,
                                GeneratedMutableMap.map26891map,
                                GeneratedMutableMap.map26637map,
                                GeneratedMutableMap.map26638map,
                                GeneratedMutableMap.map26639map
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
                                        GeneratedMutableMap.map26961map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map26962map
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
                                GeneratedMutableMap.map26962map
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
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map26961map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map26962map
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
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map26962map
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
                                        GeneratedMutableMap.map27032map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27033map
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
                                GeneratedMutableMap.map27033map
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
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map27032map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27033map
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
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27033map
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
                                        GeneratedMutableMap.map27102map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27103map
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
                                GeneratedMutableMap.map27103map
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
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map27102map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27103map
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
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27103map
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
                                        GeneratedMutableMap.map27173map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27174map
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
                                GeneratedMutableMap.map27174map
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
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map27173map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27174map
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
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27174map
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
                                        GeneratedMutableMap.map27243map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27244map
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
                                GeneratedMutableMap.map27244map
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
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map27243map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27244map
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
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27244map
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
                                        GeneratedMutableMap.map27313map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27314map
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
                                GeneratedMutableMap.map27314map
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
                                POPValues(dictionary, listOf(
                                        "l",
                                        "hash"
                                ), listOf(
                                        GeneratedMutableMap.map27313map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27314map
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
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27314map
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
                                        GeneratedMutableMap.map27484map,
                                        GeneratedMutableMap.map27485map,
                                        GeneratedMutableMap.map27486map,
                                        GeneratedMutableMap.map27487map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27488map,
                                GeneratedMutableMap.map27489map,
                                GeneratedMutableMap.map27490map,
                                GeneratedMutableMap.map27491map
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
                                GeneratedMutableMap.map27488map,
                                GeneratedMutableMap.map27489map,
                                GeneratedMutableMap.map27490map,
                                GeneratedMutableMap.map27491map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27488map,
                                GeneratedMutableMap.map27489map,
                                GeneratedMutableMap.map27490map,
                                GeneratedMutableMap.map27491map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27488map,
                                GeneratedMutableMap.map27489map,
                                GeneratedMutableMap.map27490map,
                                GeneratedMutableMap.map27491map
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
                                        GeneratedMutableMap.map27566map,
                                        GeneratedMutableMap.map27567map,
                                        GeneratedMutableMap.map27568map,
                                        GeneratedMutableMap.map27569map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27570map,
                                GeneratedMutableMap.map27571map,
                                GeneratedMutableMap.map27572map,
                                GeneratedMutableMap.map27573map
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
                                GeneratedMutableMap.map27570map,
                                GeneratedMutableMap.map27571map,
                                GeneratedMutableMap.map27572map,
                                GeneratedMutableMap.map27573map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27570map,
                                GeneratedMutableMap.map27571map,
                                GeneratedMutableMap.map27572map,
                                GeneratedMutableMap.map27573map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27570map,
                                GeneratedMutableMap.map27571map,
                                GeneratedMutableMap.map27572map,
                                GeneratedMutableMap.map27573map
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
                                        GeneratedMutableMap.map27685map,
                                        GeneratedMutableMap.map27686map,
                                        GeneratedMutableMap.map27687map,
                                        GeneratedMutableMap.map27688map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27689map,
                                GeneratedMutableMap.map27690map,
                                GeneratedMutableMap.map27691map,
                                GeneratedMutableMap.map27692map
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
                                GeneratedMutableMap.map27689map,
                                GeneratedMutableMap.map27690map,
                                GeneratedMutableMap.map27691map,
                                GeneratedMutableMap.map27692map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27689map,
                                GeneratedMutableMap.map27690map,
                                GeneratedMutableMap.map27691map,
                                GeneratedMutableMap.map27692map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27689map,
                                GeneratedMutableMap.map27690map,
                                GeneratedMutableMap.map27691map,
                                GeneratedMutableMap.map27692map
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
                                        GeneratedMutableMap.map27804map,
                                        GeneratedMutableMap.map27805map,
                                        GeneratedMutableMap.map27806map,
                                        GeneratedMutableMap.map27487map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27809map,
                                GeneratedMutableMap.map27491map
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
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27809map,
                                GeneratedMutableMap.map27491map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27809map,
                                GeneratedMutableMap.map27491map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27807map,
                                GeneratedMutableMap.map27808map,
                                GeneratedMutableMap.map27809map,
                                GeneratedMutableMap.map27491map
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
                                        GeneratedMutableMap.map27921map,
                                        GeneratedMutableMap.map27922map,
                                        GeneratedMutableMap.map27923map,
                                        GeneratedMutableMap.map27924map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27925map,
                                GeneratedMutableMap.map27926map,
                                GeneratedMutableMap.map27927map,
                                GeneratedMutableMap.map27928map
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
                                GeneratedMutableMap.map27925map,
                                GeneratedMutableMap.map27926map,
                                GeneratedMutableMap.map27927map,
                                GeneratedMutableMap.map27928map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27925map,
                                GeneratedMutableMap.map27926map,
                                GeneratedMutableMap.map27927map,
                                GeneratedMutableMap.map27928map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27925map,
                                GeneratedMutableMap.map27926map,
                                GeneratedMutableMap.map27927map,
                                GeneratedMutableMap.map27928map
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
                                        GeneratedMutableMap.map28040map,
                                        GeneratedMutableMap.map28041map,
                                        GeneratedMutableMap.map28042map,
                                        GeneratedMutableMap.map27688map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28043map,
                                GeneratedMutableMap.map28044map,
                                GeneratedMutableMap.map28045map,
                                GeneratedMutableMap.map27692map
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
                                GeneratedMutableMap.map28043map,
                                GeneratedMutableMap.map28044map,
                                GeneratedMutableMap.map28045map,
                                GeneratedMutableMap.map27692map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28043map,
                                GeneratedMutableMap.map28044map,
                                GeneratedMutableMap.map28045map,
                                GeneratedMutableMap.map27692map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28043map,
                                GeneratedMutableMap.map28044map,
                                GeneratedMutableMap.map28045map,
                                GeneratedMutableMap.map27692map
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
                                GeneratedMutableMap.map28120map,
                                GeneratedMutableMap.map28121map,
                                GeneratedMutableMap.map28122map,
                                GeneratedMutableMap.map28123map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28124map,
                            GeneratedMutableMap.map28125map,
                            GeneratedMutableMap.map28126map,
                            GeneratedMutableMap.map28127map
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
                                        GeneratedMutableMap.map28239map,
                                        GeneratedMutableMap.map28240map,
                                        GeneratedMutableMap.map28241map,
                                        GeneratedMutableMap.map28123map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28242map,
                                GeneratedMutableMap.map28243map,
                                GeneratedMutableMap.map28244map,
                                GeneratedMutableMap.map28127map
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
                                GeneratedMutableMap.map28242map,
                                GeneratedMutableMap.map28243map,
                                GeneratedMutableMap.map28244map,
                                GeneratedMutableMap.map28127map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28242map,
                                GeneratedMutableMap.map28243map,
                                GeneratedMutableMap.map28244map,
                                GeneratedMutableMap.map28127map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28242map,
                                GeneratedMutableMap.map28243map,
                                GeneratedMutableMap.map28244map,
                                GeneratedMutableMap.map28127map
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
                                        GeneratedMutableMap.map30349map,
                                        GeneratedMutableMap.map30350map,
                                        GeneratedMutableMap.map30351map,
                                        GeneratedMutableMap.map30352map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "s2",
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map30353map,
                                GeneratedMutableMap.map30354map,
                                GeneratedMutableMap.map30355map,
                                GeneratedMutableMap.map30356map
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
                                GeneratedMutableMap.map30353map,
                                GeneratedMutableMap.map30354map,
                                GeneratedMutableMap.map30355map,
                                GeneratedMutableMap.map30356map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "s2",
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map30353map,
                                GeneratedMutableMap.map30354map,
                                GeneratedMutableMap.map30355map,
                                GeneratedMutableMap.map30356map
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
                        POPValues(dictionary, listOf(
                                "s1",
                                "s2",
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map30353map,
                                GeneratedMutableMap.map30354map,
                                GeneratedMutableMap.map30355map,
                                GeneratedMutableMap.map30356map
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
                                        GeneratedMutableMap.map30433map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map30434map
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
                                        GeneratedMutableMap.map30436map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map30437map
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
                                        GeneratedMutableMap.map30709map
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
                                        GeneratedMutableMap.map30800map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "uri",
                                "iri"
                        ), listOf(
                                GeneratedMutableMap.map30801map
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
                                        GeneratedMutableMap.map31019map,
                                        GeneratedMutableMap.map31020map,
                                        GeneratedMutableMap.map31021map,
                                        GeneratedMutableMap.map31022map,
                                        GeneratedMutableMap.map31023map,
                                        GeneratedMutableMap.map31024map,
                                        GeneratedMutableMap.map31025map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map31026map,
                                GeneratedMutableMap.map31027map,
                                GeneratedMutableMap.map31028map,
                                GeneratedMutableMap.map31029map,
                                GeneratedMutableMap.map31030map,
                                GeneratedMutableMap.map31031map,
                                GeneratedMutableMap.map31032map
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
                                GeneratedMutableMap.map31026map,
                                GeneratedMutableMap.map31027map,
                                GeneratedMutableMap.map31028map,
                                GeneratedMutableMap.map31029map,
                                GeneratedMutableMap.map31030map,
                                GeneratedMutableMap.map31031map,
                                GeneratedMutableMap.map31032map
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
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map31026map,
                                GeneratedMutableMap.map31027map,
                                GeneratedMutableMap.map31028map,
                                GeneratedMutableMap.map31029map,
                                GeneratedMutableMap.map31030map,
                                GeneratedMutableMap.map31031map,
                                GeneratedMutableMap.map31032map
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
                        POPValues(dictionary, listOf(
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map31026map,
                                GeneratedMutableMap.map31027map,
                                GeneratedMutableMap.map31028map,
                                GeneratedMutableMap.map31029map,
                                GeneratedMutableMap.map31030map,
                                GeneratedMutableMap.map31031map,
                                GeneratedMutableMap.map31032map
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
                                        GeneratedMutableMap.map31094map
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s")
                                ),
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map31678map,
                                        GeneratedMutableMap.map31679map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map31678map,
                                GeneratedMutableMap.map31679map
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
                                        GeneratedMutableMap.map31812map,
                                        GeneratedMutableMap.map31813map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "w",
                                "S"
                        ), listOf(
                                GeneratedMutableMap.map31812map,
                                GeneratedMutableMap.map31813map
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
                                        GeneratedMutableMap.map31809map,
                                        GeneratedMutableMap.map31955map,
                                        GeneratedMutableMap.map31956map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map31809map,
                                GeneratedMutableMap.map31955map,
                                GeneratedMutableMap.map31956map
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
                        LOPProjection(
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
                        LOPProjection(
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                ),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5828map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                        ), listOf(
                                GeneratedMutableMap.map5828map
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
                                GeneratedMutableMap.map5828map
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
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5828map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                        ), listOf(
                                GeneratedMutableMap.map5828map
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
                        POPValues(dictionary, listOf(
                        ), listOf(
                                GeneratedMutableMap.map5828map
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
                                        GeneratedMutableMap.map34082map,
                                        GeneratedMutableMap.map34083map
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
                        LOPProjection(
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
                                        GeneratedMutableMap.map34082map,
                                        GeneratedMutableMap.map34083map
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
                                        GeneratedMutableMap.map34200map,
                                        GeneratedMutableMap.map34201map
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
                        LOPProjection(
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
                                        GeneratedMutableMap.map34200map,
                                        GeneratedMutableMap.map34201map
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
                                GeneratedMutableMap.map34303map
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
                            GeneratedMutableMap.map34304map
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
                        LOPProjection(
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
                                        GeneratedMutableMap.map34511map,
                                        GeneratedMutableMap.map34512map
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
                        LOPProjection(
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
                                        GeneratedMutableMap.map34511map,
                                        GeneratedMutableMap.map34512map
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
                                        GeneratedMutableMap.map34592map
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
                                GeneratedMutableMap.map34592map
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
                                POPValues(dictionary, listOf(
                                        "x",
                                        "l",
                                        "dt"
                                ), listOf(
                                        GeneratedMutableMap.map34592map
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
                                        GeneratedMutableMap.map34686map,
                                        GeneratedMutableMap.map34687map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34688map,
                                GeneratedMutableMap.map34689map
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
                                GeneratedMutableMap.map34688map,
                                GeneratedMutableMap.map34689map
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
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34688map,
                                GeneratedMutableMap.map34689map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map34688map,
                                GeneratedMutableMap.map34689map
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
                                        GeneratedMutableMap.map36108map,
                                        GeneratedMutableMap.map36109map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map
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
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map
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
                                POPValues(dictionary, listOf(
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36108map,
                                        GeneratedMutableMap.map36109map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map
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
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map
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
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36143map,
                                GeneratedMutableMap.map36144map,
                                GeneratedMutableMap.map36145map,
                                GeneratedMutableMap.map36146map
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36143map,
                                GeneratedMutableMap.map36144map,
                                GeneratedMutableMap.map36145map,
                                GeneratedMutableMap.map36146map
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
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36143map,
                                GeneratedMutableMap.map36144map,
                                GeneratedMutableMap.map36145map,
                                GeneratedMutableMap.map36146map
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36143map,
                                GeneratedMutableMap.map36144map,
                                GeneratedMutableMap.map36145map,
                                GeneratedMutableMap.map36146map
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
                                        GeneratedMutableMap.map36332map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map36333map
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
                                GeneratedMutableMap.map36333map
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
                                POPValues(dictionary, listOf(
                                        "P",
                                        "F",
                                        "L",
                                        "FullName"
                                ), listOf(
                                        GeneratedMutableMap.map36332map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map36333map
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
                        POPValues(dictionary, listOf(
                                "P",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map36333map
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
                                        GeneratedMutableMap.map36336map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map36336map
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
                                GeneratedMutableMap.map36336map
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
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "o"
                                ), listOf(
                                        GeneratedMutableMap.map36336map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map36336map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map36336map
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
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36143map,
                                GeneratedMutableMap.map36144map,
                                GeneratedMutableMap.map36145map,
                                GeneratedMutableMap.map36146map
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36143map,
                                GeneratedMutableMap.map36144map,
                                GeneratedMutableMap.map36145map,
                                GeneratedMutableMap.map36146map
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
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36143map,
                                GeneratedMutableMap.map36144map,
                                GeneratedMutableMap.map36145map,
                                GeneratedMutableMap.map36146map
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36142map,
                                GeneratedMutableMap.map36143map,
                                GeneratedMutableMap.map36144map,
                                GeneratedMutableMap.map36145map,
                                GeneratedMutableMap.map36146map
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
